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
 * Класс кнопки вычисления интеграла методом трапеции {@link IntegrationTable}
 * Наследуется от Button {@link Button}
 * При нажатии вычисляет интеграл для выбранной строки таблицы методом трапеций
 */
public class CalculateTrapButton extends Button {

    /**
     * Конструктор CalculateTrapButton
     */
    public CalculateTrapButton() {
        super();
        this.setFont(new Font("Arial", Font.BOLD, 14));
        this.setForeground(Color.BLACK);
        this.setBackground(new Color(255,244,79));
    }


    /**
     *  Метод назначения действия кнопки {@link JButton#addActionListener(java.awt.event.ActionListener)}
     *  При нажатии происходит вычисление результата интегрирования в выбранной строке
     *  @see IntegrationTable#countResult(boolean, LabPanel)
     */
    private void addEventListener() {
        this.addActionListener(e->{
            if(getButtonData().getLinkedTable().getTableSelectedRow()!=-1) {
                getButtonData().getLinkedTable().countResult(true, getButtonData().getParentPanel());
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
     * @see CalculateTrapButton#addEventListener()
     */
    @Override
    public void setButtonData(ButtonData data) {
        super.setButtonData(data);
        addEventListener();
    }
}
