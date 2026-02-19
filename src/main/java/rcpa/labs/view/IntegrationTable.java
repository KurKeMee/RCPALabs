package rcpa.labs.view;

import rcpa.labs.exceptions.OutOfRangeException;
import rcpa.labs.model.Button;
import rcpa.labs.model.RecIntegral;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;

import static rcpa.labs.config.Configuration.*;

/**
 * @author Ivan Monin
 * @author Danila Kokarev
 *
 * Класс реализации таблицы интеграции
 * Наследуется от JScrollPane {@link JScrollPane}
 */
public class IntegrationTable extends JScrollPane {

    /**
     * Переменная для хранения созданной таблицы
     *
     * @see JTable
     */
    private JTable table;


    /**
     * Список записей таблицы для хранения данных
     * @see RecIntegral
     */
    private ArrayList<RecIntegral> tableRows = new ArrayList<>();

    /**
     * Конструктор IntegrationTable
     * Переопределяет метод {@link DefaultTableModel#isCellEditable(int, int)} для запрета на редактирование ячеек
     *
     * @param columns - массив строк, состоящий из заголовков таблицы
     * @param x       - расположение таблицы по горизонтали
     * @param y       - расположение таблицы по вертикали
     * @see IntegrationTable#initTable(int, int, LabPanel)
     */
    public IntegrationTable(String[] columns, int x, int y, LabPanel parentPanel) {
        super(createTable(columns));
        initTable(x,y, parentPanel);
    }

    /**
     * Метод создания таблицы
     * Переопределяется метод {@link JTable#prepareRenderer(TableCellRenderer, int, int) для раскрашивания строк
     * с чередованием
     * Переопределяется метод {@link JTable#isCellEditable(int, int)}
     * @param columns - заголовки таблицы
     * @return JTable - возвращается таблица
     */
    private static JTable createTable(String[] columns) {
        return new JTable(new DefaultTableModel(columns, 0)) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);

                if (c instanceof JComponent) {
                    ((JComponent) c).setOpaque(true);
                }

                if (!isRowSelected(row)) {
                    if (row % 2 == 0) {
                        c.setBackground(new Color(255,207,72));
                    } else {
                        c.setBackground(new Color(255,219,88));
                    }
                } else {
                    c.setBackground(new Color(255,186,0));
                    c.setForeground(Color.BLACK);
                }

                ((JLabel) c).setHorizontalAlignment(JLabel.CENTER);

                return c;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 3;
            }
        };
    }

    /**
     * Метод инициализации таблицы
     * @param x - расположение таблицы по горизонтали
     * @param y - расположение таблицы по вертикали
     * @see IntegrationTable#IntegrationTable(String[], int, int, LabPanel)
     */
    private void initTable(int x, int y, LabPanel parentPanel) {
        table = (JTable) this.getViewport().getView();
        table.setRowHeight(ROW_HEIGHT);
        table.setGridColor(Color.BLACK);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        this.setBounds(x,y,400,400);

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setForeground(Color.DARK_GRAY);

        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(244,169,0));
        table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setReorderingAllowed(false);

        table.getSelectionModel().addListSelectionListener(e -> {
            if(table.getSelectedRow() > -1) {
                Arrays.stream(parentPanel.getComponents())
                        .filter(comp -> comp instanceof DeleteButton ||
                                comp instanceof CalculateButton ||
                                comp instanceof CalculateTrapButton)
                        .forEach(comp -> {
                            ((Button) comp).buttonVisible(true);
                        });
            }
            else{
                Arrays.stream(parentPanel.getComponents())
                        .filter(comp -> comp instanceof DeleteButton ||
                                comp instanceof CalculateButton ||
                                comp instanceof CalculateTrapButton)
                        .forEach(comp -> {
                            ((Button) comp).buttonVisible(false);
                        });
            }
        });

        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();

                    TableModel model = (TableModel)e.getSource();
                    Object newValue = model.getValueAt(row, column);
                    try {
                        if (Double.parseDouble(newValue.toString()) < MIN_VALUE ||
                                Double.parseDouble(newValue.toString()) > MAX_VALUE) {
                            throw new OutOfRangeException(Double.parseDouble(newValue.toString()));
                        }
                    }
                    catch (OutOfRangeException ev){
                        parentPanel.isValueOutOfRange();
                    }
                        switch (column) {
                            case 0:
                                tableRows.get(row).setLowBorder(newValue.toString());
                                break;
                            case 1:
                                tableRows.get(row).setHighBorder(newValue.toString());
                                break;
                            case 2:
                                tableRows.get(row).setStepIntegration(newValue.toString());
                            default:
                                break;
                        }
                }
            }
        });
    }

    /**
     * Метод добавления новой строки в таблицу
     * @param data - входные данные с полей ввода
     * @see IntegrationTable#integrationResult(double, double, double) - вычисляет значение интеграла
     */
    public void addRow(String[] data, LabPanel parentPanel){
        if(this.table.getColumnCount() != data.length+1){
            parentPanel.isSomethingGoWrong();
        }

        try {
            double bottomBorder = Double.parseDouble(data[0]);
            double topBorder = Double.parseDouble(data[1]);
            double stepIntegration = Double.parseDouble(data[2]);
            if (bottomBorder >= topBorder) {
                parentPanel.isTopSmallerBottom();
                return;
            }
            if (stepIntegration <= 0) {
                parentPanel.isLessThanZeroOrEqualToZero();
                return;
            }
            if (bottomBorder < MIN_VALUE || bottomBorder > MAX_VALUE) {
                throw new OutOfRangeException(bottomBorder);
            }
            if (topBorder > MAX_VALUE) {
                throw new OutOfRangeException(topBorder);
            }
            if (stepIntegration < MIN_VALUE || stepIntegration > MAX_VALUE) {
                throw new OutOfRangeException(stepIntegration);
            }
        } catch (NumberFormatException e) {
            parentPanel.isSomethingGoWrong();
            return;
        } catch (OutOfRangeException e) {
            parentPanel.isValueOutOfRange();
            return;
        }


        String[] newData = Arrays.copyOf(data, data.length + 1);

        newData[newData.length - 1] = "";

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(newData);

        tableRows.add(new RecIntegral(newData[0],newData[1],newData[2], ""));
        parentPanel.isAddNewRowSuccess();
    }

    /**
     * Метод подсчета результата интегрирования
     * Использует данные выбранной строки {@link IntegrationTable#getTableSelectedRow()}
     */
    public void countResult(boolean trap, LabPanel parentPanel) {
        int selectedRow = getTableSelectedRow();

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        try {
            double bottomBorder = Double.parseDouble(model.getValueAt(selectedRow, 0).toString());
            double topBorder = Double.parseDouble(model.getValueAt(selectedRow, 1).toString());
            double stepIntegration = Double.parseDouble(model.getValueAt(selectedRow, 2).toString());

            if (stepIntegration <= 0) {
                parentPanel.isLessThanZeroOrEqualToZero();
                return;
            }
            if (bottomBorder >= topBorder) {
                parentPanel.isTopSmallerBottom();
                return;
            }

            String result;
            if (trap) {
                result = integrationResultTrap(bottomBorder, topBorder, stepIntegration);
            } else {
                result = integrationResult(bottomBorder, topBorder, stepIntegration);
            }
            model.setValueAt(result, selectedRow, 3);
            tableRows.get(selectedRow).setResult(result);
        } catch (NumberFormatException e) {
            parentPanel.isSomethingGoWrong();
        }

    }

    /**
     * Метод очистки таблицы, удаляет все строки
     */
    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.getDataVector().removeAllElements();
        table.setBackground(new Color(238,238,238));
    }

    /**
     * Метод заполнения данных в таблицу
     */
    public void fillTable() {
        clearTable();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < tableRows.size(); i++) {
            String[] arr = tableRows.get(i).getStringArray();
            model.addRow(arr);
        }
    }

    /**
     * Метод удаления выбранной строки
     *
     * @param id - передаваемый параметр id строки
     */
    public void deleteRow(int id) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(id);
        tableRows.remove(id);
    }

    /**
     * Метод левых прямоугольников
     *
     * @param lowBorder  - нижняя граница интегрирования
     * @param highBorder - верхняя граница интегрирования
     * @param step       - шаг интегрирования
     * @return String       - результат интегрирования
     */
    public String integrationResult(double lowBorder, double highBorder, double step) {
        double sum = 0.0;
        double x = lowBorder;

        while (x < highBorder) {
            sum += Math.exp(-x) * step;
            x += step;
        }
        System.out.println(sum);
        return Double.toString(sum);
    }

    /**
     * Метод вычисления интеграла методом трапеции на основе входных данных
     *
     * @param lowBorder  - нижняя граница интегрирования
     * @param highBorder - верхняя граница интегрирования
     * @param step       - шаг интегрирования
     * @return String    - результат интегрирования
     */
    public String integrationResultTrap(double lowBorder, double highBorder, double step) {
        double sum = 0.0;
        double x = lowBorder;

        System.out.println(highBorder);
        while (x < highBorder) {
            double nextX = Math.min(x + step, highBorder);
            sum += (nextX - x) * (Math.exp(-x) + Math.exp(-nextX)) / 2;
            x = nextX;
        }

        return Double.toString(sum);
    }

    /**
     * Метод получения id выбранной строки
     * @return int - возвращает id строки
     */
    public int getTableSelectedRow(){return table.getSelectedRow();}

    /**
     * Метод получения таблицы
     * @return JTable - возвращает таблицу {@link #table}
     */
    public JTable getTable(){
        return this.table;
    }

    /**
     * Метод получения списка записей таблицы
     * @return ArrayList<RecIntegral> - список записей
     */
    public ArrayList<RecIntegral> getTableRows() {
        return tableRows;
    }

    /**
     * Метод установки списка записей таблицы
     * @param tableRows - новый список записей
     */
    public void setTableRows(ArrayList<RecIntegral> tableRows) {
        this.tableRows = tableRows;
    }
}
