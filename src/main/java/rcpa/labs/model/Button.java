package rcpa.labs.model;

import javax.swing.*;

import static rcpa.labs.config.Configuration.BUTTON_HEIGHT;
import static rcpa.labs.config.Configuration.BUTTON_WIDTH;

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
        if(data.getX()!=-1 && data.getY()!=-1){
            this.setBounds(data.getX(),data.getY(),BUTTON_WIDTH,BUTTON_HEIGHT);
        }
    }

    /**
     * Метод получения данных кнопки
     * @return ButtonData - возвращает данные кнопки {@link #data}
     */
    public ButtonData getButtonData() {
        return data;
    }

    /**
     * Метод для изменения отображения кнопки
     * @param visible - параметр для смены отображения
     */
    public void buttonVisible(boolean visible){
        if(visible){
            data.setButtonVisible(true);
            this.setVisible(true);
        }
        else{
            data.setButtonVisible(false);
            this.setVisible(false);
        }
    }
}
