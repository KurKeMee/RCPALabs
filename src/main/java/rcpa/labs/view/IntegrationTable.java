package rcpa.labs.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.util.Arrays;

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
     * Конструктор IntegrationTable
     * Переопределяет метод {@link DefaultTableModel#isCellEditable(int,int)} для запрета на редактирование ячеек
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
     * Переопределяется метод {@link JTable#prepareRenderer(TableCellRenderer,int,int) для раскрашивания строк
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
     * @see IntegrationTable#IntegrationTable(String[],int,int, LabPanel)
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
        table.setBackground(new Color(205,164,52));

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
                            if (comp instanceof CalculateButton) {
                                ((CalculateButton) comp).buttonVisible(true);
                            }
                            if (comp instanceof DeleteButton) {
                                ((DeleteButton) comp).buttonVisible(true);
                            }
                            if(comp instanceof CalculateTrapButton) {
                                ((CalculateTrapButton) comp).buttonVisible(true);
                            }
                        });
            }
            else{
                Arrays.stream(parentPanel.getComponents())
                        .filter(comp -> comp instanceof DeleteButton ||
                                comp instanceof CalculateButton ||
                                comp instanceof CalculateTrapButton)
                        .forEach(comp -> {
                            if (comp instanceof CalculateButton) {
                                ((CalculateButton) comp).buttonVisible(false);
                            }
                            if (comp instanceof DeleteButton) {
                                ((DeleteButton) comp).buttonVisible(false);
                            }
                            if(comp instanceof CalculateTrapButton) {
                                ((CalculateTrapButton) comp).buttonVisible(true);
                            }
                        });
            }
        });
    }

    /**
     * Метод добавления новой строки в таблицу
     * @param data - входные данные с полей ввода
     * @see IntegrationTable#integrationResult(double, double, double) - вычисляет значение интеграла
     * @see DefaultTableModel необходим для добавления новой строки
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
        } catch (NumberFormatException e) {
            parentPanel.isSomethingGoWrong();
        }


        String[] newData = Arrays.copyOf(data, data.length + 1);

        newData[newData.length - 1] = "";

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(newData);
    }

/**
 * Метод подсчета результата интегрирования
 * Использует данные выбранной строки {@link IntegrationTable#getTableSelectedRow()}
 */
public void countResult(boolean trap){
    int selectedRow = getTableSelectedRow();
    DefaultTableModel model = (DefaultTableModel) table.getModel();

    double bottomBorder = Double.parseDouble(model.getValueAt(selectedRow, 0).toString());
    double topBorder = Double.parseDouble(model.getValueAt(selectedRow, 1).toString());
    double stepIntegration = Double.parseDouble(model.getValueAt(selectedRow, 2).toString());

    if(trap) {
        model.setValueAt(integrationResultTrap(bottomBorder, topBorder, stepIntegration), selectedRow, 3);
    }
    else{
        model.setValueAt(integrationResult(bottomBorder, topBorder, stepIntegration), selectedRow, 3);
    }
}

/**
 * Метод удаления выбранной строки
 * @param id - передаваемый параметр id строки
 */
public void deleteRow(int id){
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.removeRow(id);
}

/**
 * Метод вычисления интеграла на основе входных данных
 * @param lowBorder     - нижняя граница интегрирования
 * @param highBorder    - верхняя граница интегрирования
 * @param step          - шаг интегрирования
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
     * @param lowBorder     - нижняя граница интегрирования
     * @param highBorder    - верхняя граница интегрирования
     * @param step          - шаг интегрирования
     * @return String       - результат интегрирования
     */
    public String integrationResultTrap(double lowBorder, double highBorder, double step) {
        double sum = 0.0;
        double x = lowBorder;

        while (x < highBorder) {
            double nextX = Math.min(x + step, highBorder);
            sum += (nextX - x) * (Math.exp(-x) + Math.exp(-nextX)) / 2;
            x = nextX;
        }

        System.out.println(sum);
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
}
