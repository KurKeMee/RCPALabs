package rcpa.labs.view;

import rcpa.labs.config.Configuration;
import rcpa.labs.model.Button;
import rcpa.labs.model.ButtonData;

import javax.swing.*;

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
    public CalculateButton() { super(); }


    /**
     * Метод назначения действия кнопки
     */
    private void addEventListener() {
        this.addActionListener(e->{
            getButtonData().setLabel(getButtonData().getLabel());
            this.setText(getButtonData().getLabel());
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
