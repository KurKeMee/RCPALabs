package rcpa.labs.view;

import rcpa.labs.config.Configuration;
import rcpa.labs.model.Button;
import rcpa.labs.model.ButtonData;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * @author Ivan Monin
 * @author Danila Kokarev
 *
 * Класс кнопки очистки
 * Наследуется от Button {@link Button}
 */
public class ClearTableButton extends Button {

    /**
     * Конструктор ClearTableButton
     */
    public ClearTableButton() {
        super();
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setForeground(Color.BLACK);
        this.setBackground(new Color(250,210,1));
    }


    /**
     * Метод назначения действия кнопки {@link JButton#addActionListener(java.awt.event.ActionListener)}
     * При нажатии происходит очистка таблицы и скрытие кнопок вычисления
     */
    private void addEventListener() {
        this.addActionListener(e->{
            getButtonData().getLinkedTable().clearTable();

            Component[] components = getButtonData().getParentPanel().getComponents();
            for (Component comp : components) {
                if (comp instanceof DeleteButton ||
                        comp instanceof CalculateButton ||
                        comp instanceof CalculateTrapButton) {
                    ((Button) comp).buttonVisible(false);
                }
            }
        });
    }

    /**
     * Переопределенный метод установки данных кнопки {@link Button#setButtonData(ButtonData)}
     *
     * @param data - данные кнопки
     * @see ClearTableButton#addEventListener()
     */
    @Override
    public void setButtonData(ButtonData data) {
        super.setButtonData(data);
        addEventListener();
    }
}
