package rcpa.labs.view;

import rcpa.labs.config.Configuration;
import rcpa.labs.model.Button;
import rcpa.labs.model.ButtonData;
import rcpa.labs.service.LabMaster;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Ivan Monin
 * @author Danila Kokarev
 *
 * Класс кнопки добавления в таблицу {@link IntegrationTable}
 * Наследуется от Button {@link Button}
 */
public class AddButton extends Button {

    /**
     * Конструктор AddButton
     */
    public AddButton() {
        super();
    }


    /**
     * Метод назначения действия кнопки {@link JButton#addActionListener(java.awt.event.ActionListener)}
     */
    private void addEventListener() {
        this.addActionListener(_ -> {
            ArrayList<JTextField> textFields =
                    Arrays.stream(super.getButtonData().getParentPanel().getComponents())
                                                            .filter(comp -> comp instanceof JTextField)
                                                            .map(tf -> (JTextField) tf)
                                                            .collect(Collectors.toCollection(ArrayList::new));

            String[] values = textFields.stream().map(tf -> tf.getText()).toArray(String[]::new);
            super.getButtonData().getLinkedTable().addRow(values);
        });
    }


    /**
     * Переопределенный метод установки данных кнопки {@link Button#setButtonData(ButtonData)}
     *
     * @param data - данные кнопки
     * @see AddButton#addEventListener()
     */
    @Override
    public void setButtonData(ButtonData data) {
        super.setButtonData(data);
        addEventListener();
    }
}
