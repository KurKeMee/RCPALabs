package rcpa.labs.view;

import rcpa.labs.model.Button;
import rcpa.labs.model.ButtonData;
import rcpa.labs.service.LabMaster;

import javax.swing.border.AbstractBorder;
import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
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
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setForeground(Color.BLACK);
        this.setBackground(new Color(255,244,79));
    }


    /**
     * Метод назначения действия кнопки {@link JButton#addActionListener(java.awt.event.ActionListener)}
     * При нажатии кнопки берутся значения полей и передаются в IntegrationTable для добавления новой строки
     * @see IntegrationTable#addRow(String[],LabPanel)
     */
    private void addEventListener() {
        this.addActionListener(_ -> {
            ArrayList<JTextField> textFields =
                    Arrays.stream(super.getButtonData().getParentPanel().getComponents())
                            .filter(comp -> comp instanceof JTextField)
                            .map(tf -> (JTextField) tf)
                            .collect(Collectors.toCollection(ArrayList::new));

            String[] values = textFields.stream().map(tf -> tf.getText()).toArray(String[]::new);
            if (Arrays.stream(values).toList().stream().allMatch(String::isEmpty)) {
                getButtonData().getParentPanel().isFieldEmpty();
            } else if (values[0].isEmpty()) {
                getButtonData().getParentPanel().isBottomBorderEmpty();
            } else if (values[1].isEmpty()) {
                getButtonData().getParentPanel().isTopBorderEmpty();
            } else if (values[2].isEmpty()) {
                getButtonData().getParentPanel().isStepFieldEmpty();
            } else {
                super.getButtonData().getLinkedTable().addRow(values, getButtonData().getParentPanel());
                getButtonData().getParentPanel().isAddNewRowSuccess();
            }
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
