package rcpa.labs.view;

import rcpa.labs.config.Configuration;
import rcpa.labs.model.Button;
import rcpa.labs.model.ButtonData;
import rcpa.labs.service.LabMaster;

import javax.swing.*;
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
    public DeleteButton(){ super(); }


    /**
     * Метод назначения действия кнопки
     */
    private void addEventListener() {
        this.addActionListener(e->{
            Pattern pattern = Pattern.compile(".$");
            Matcher matcher = pattern.matcher(getButtonData().getLabel());
            String str = matcher.replaceAll("");

            getButtonData().setLabel(str);
            this.setText(getButtonData().getLabel());
            LabMaster.getLabMaster().showSuccess("Символ удален!");
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
