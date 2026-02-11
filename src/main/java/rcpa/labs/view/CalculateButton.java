package rcpa.labs.view;

import rcpa.labs.config.Configuration;
import rcpa.labs.model.Button;
import rcpa.labs.model.ButtonData;

import javax.swing.*;
import java.awt.*;

/**
 * @author Ivan Monin
 * @author Danila Kokarev
 *
 * Класс кнопки вычисления
 * Наследуется от Button {@link Button}
 */
public class CalculateButton extends Button {

    /**
     * Конструктор CalculateButton
     */
    public CalculateButton() {
        super();
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setForeground(Color.BLACK);
        this.setBackground(new Color(250,210,1));
    }


    /**
     * Метод назначения действия кнопки {@link JButton#addActionListener(java.awt.event.ActionListener)}
     * При нажатии происходит вычисление результата интегрирования в выбранной строке
     * @see IntegrationTable#countResult(boolean, LabPanel)
     */
    private void addEventListener() {
        this.addActionListener(e->{
            if(getButtonData().getLinkedTable().getTableSelectedRow()!=-1) {
                getButtonData().getLinkedTable().countResult(false, getButtonData().getParentPanel());
                getButtonData().getParentPanel().isCalculateRowSuccess();
            }
            else{
                getButtonData().getParentPanel().isRowNoSelected();
            }
        });
    }

    /**
     * Переопределенный метод установки данных кнопки {@link Button#setButtonData(ButtonData)}
     *
     * @param data - данные кнопки
     * @see CalculateButton#addEventListener()
     */
    @Override
    public void setButtonData(ButtonData data) {
        super.setButtonData(data);
        addEventListener();
    }
}
