package rcpa.labs.view;

import rcpa.labs.config.Configuration;
import rcpa.labs.model.Button;
import rcpa.labs.model.ButtonData;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ivan Monin
 * @author Danila Kokarev
 *
 * Класс кнопки вычисления
 * Наследуется от Button {@link Button}
 */
public class DeleteButton extends Button {

    /**
     * Конструктор DeleteButton
     */
    public DeleteButton(){
        super();
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setForeground(Color.BLACK);
        this.setBackground(new Color(255,179,0));
    }


    /**
     * Метод назначения действия кнопки {@link JButton#addActionListener(java.awt.event.ActionListener)}
     * При нажатии происходит удаление выбранной строки
     * @see IntegrationTable#deleteRow(int)
     */
    private void addEventListener() {
        this.addActionListener(e->{
           int rowId = getButtonData().getLinkedTable().getTableSelectedRow();
           if(rowId != -1) {
               getButtonData().getLinkedTable().deleteRow(rowId);
           }
        });
    }

    /**
     * Переопределенный метод установки данных кнопки {@link Button#setButtonData(ButtonData)}
     *
     * @param data - данные кнопки
     * @see DeleteButton#addEventListener()
     */
    @Override
    public void setButtonData(ButtonData data) {
        super.setButtonData(data);
        addEventListener();
    }
}
