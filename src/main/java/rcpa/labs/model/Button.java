package rcpa.labs.model;

import javax.swing.*;

/**
 * @author Ivan Monin
 * @author Danila Kokarev
 *
 * Суперкласс для кастомных кнопок
 * Наследуется от JButton {@link JButton}
 */
public class Button extends JButton {

    /**
     * Переменная для хранения данных кнопки
     * @see ButtonData
     */
    private ButtonData data;


    /**
     * Конструктор Button
     */
    public Button(){}

    /**
     * Метод установки данных кнопки {@link #data}
     *
     * @see JButton#setText(String)
     * @param data - данные кнопки
     */
    public void setButtonData(ButtonData data) {
        this.data = data;
        this.setText(data.getLabel());
    }

    /**
     * Метод получения данных кнопки
     * @return ButtonData - возвращает данные кнопки {@link #data}
     */
    public ButtonData getButtonData() {
        return data;
    }
}
