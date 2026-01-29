package rcpa.labs.view;

import rcpa.labs.config.Configuration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.Arrays;

import static rcpa.labs.config.Configuration.*;

public class IntegrationTable extends JScrollPane {

    private JTable table;

    public IntegrationTable(String[] columns, int x, int y) {
        super(new JTable(new DefaultTableModel(columns, 0)){
            @Override public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
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

    public JTable getTable(){
        return this.table;
    }
}
