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
 * Класс кнопки заполнения
 * Наследуется от Button {@link Button}
 * При нажатии восстанавливает данные таблицы из сохраненного состояния
 */
public class FillTableButton extends Button {

    /**
     * Конструктор FillTableButton
     */
    public FillTableButton() {
        super();
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setForeground(Color.BLACK);
        this.setBackground(new Color(250,210,1));
    }


    /**
     * Метод назначения действия кнопки {@link JButton#addActionListener(java.awt.event.ActionListener)}
     * При нажатии происходит заполнение таблицы данными из массива tableRows
     * @see IntegrationTable#fillTable()
     */
    private void addEventListener() {
        this.addActionListener(e->{
                getButtonData().getLinkedTable().fillTable();
        });
    }

    /**
     * Переопределенный метод установки данных кнопки {@link Button#setButtonData(ButtonData)}
     *
     * @param data - данные кнопки
     * @see FillTableButton#addEventListener()
     */
    @Override
    public void setButtonData(ButtonData data) {
        super.setButtonData(data);
        addEventListener();
    }
}
