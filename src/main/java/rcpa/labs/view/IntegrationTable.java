package rcpa.labs.view;

import rcpa.labs.config.Configuration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
     * @see IntegrationTable#initTable(int, int)
     */
    public IntegrationTable(String[] columns, int x, int y) {
        super(new JTable(new DefaultTableModel(columns, 0)){
            @Override public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        initTable(x,y);
    }

    /**
     * Метод инициализации таблицы
     * @param x - расположение таблицы по горизонтали
     * @param y - расположение таблицы по вертикали
     * @see IntegrationTable#IntegrationTable(String[],int,int)
     */
    public void initTable(int x, int y) {
        table = (JTable) this.getViewport().getView();
        table.setRowHeight(ROW_HEIGHT);
        table.setIntercellSpacing(new Dimension(INTERCELL_SPACING, INTERCELL_SPACING));
        table.setGridColor(Color.BLACK);
        table.setShowVerticalLines(false);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        this.setBounds(x,y,300,400);
    }

    /**
     * Метод добавления новой строки в таблицу
     * @param data - входные данные с полей ввода
     * @see IntegrationTable#integrationResult(double, double, double) - вычисляет значение интеграла
     * @see DefaultTableModel необходим для добавления новой строки
     */
    public void addRow(String[] data){

        if(this.table.getColumnCount() != data.length+1){
            //TODO: добавить исключение, если кол-во данных != кол-во столбцов
            System.out.println(this.table.getColumnCount());
            return;
        }

        String[] newData = Arrays.copyOf(data, data.length+1);

        newData[newData.length-1] = integrationResult(Double.parseDouble(newData[0]),
                                                      Double.parseDouble(newData[1]),
                                                      Double.parseDouble(newData[2]));

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(newData);
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
            sum += Math.cos(x) * step;
            x += step;
        }
        System.out.println(sum);
        return Double.toString(sum);
    }

    /**
     * Метод получения таблицы
     * @return JTable - возвращает таблицу {@link #table}
     */
    public JTable getTable(){
        return this.table;
    }
}
