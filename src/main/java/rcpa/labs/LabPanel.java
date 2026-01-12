package rcpa.labs;

import rcpa.labs.config.Configuration;
import rcpa.labs.service.LabMaster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static rcpa.labs.config.Configuration.*;

/**
 * @authors Ivan Monin, Kokarev Danila
 *
 * Класс для отрисовки панели на окне
 */
public class LabPanel extends JPanel implements ActionListener {

    /**
     * Переменная хранящая экземпляр класса LabMaster
     * @see LabMaster
     */
    private final LabMaster labMaster;

    /**
     * Конструктор
     * @param frame - передаваемый параметр окна
     */
    public LabPanel(JFrame frame) {
        this.labMaster = LabMaster.getInstance();

        this.setBounds(0,0, Configuration.LAB_WIDTH, Configuration.LAB_HEIGHT);
        setLayout(null);

        frame.setSize(LAB_WIDTH,LAB_HEIGHT);
        frame.setLocationRelativeTo(null);

        if(labMaster.getLab() == LAB1) {
            JTextField text1 = new JTextField();
            text1.setBounds(START_FIELD_POSITION_X,START_FIELD_POSITION_Y, TEXT_WIDTH, TEXT_HEIGHT);
            this.add(text1);

            JTextField text2 = new JTextField();
            text2.setBounds(START_FIELD_POSITION_X, START_FIELD_POSITION_Y + FIELD_SPACING, TEXT_WIDTH, TEXT_HEIGHT);
            this.add(text2);

            JTextField resultText = new JTextField();
            resultText.setBounds(START_FIELD_POSITION_X, START_FIELD_POSITION_Y + (2 * FIELD_SPACING), TEXT_WIDTH, TEXT_HEIGHT);
            this.add(resultText);

            JButton button1 = new JButton("Нажать");
            button1.setBounds(START_BUTTON_POSITION_X, START_BUTTON_POSITION_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
            this.add(button1);

            JButton button2 = new JButton("Нажать");
            button2.setBounds(START_BUTTON_POSITION_X, START_BUTTON_POSITION_Y + BUTTON_SPACING, BUTTON_WIDTH, BUTTON_HEIGHT);
            this.add(button2);

            JButton buttonResult = new JButton("Нажать");
            buttonResult.setBounds(START_BUTTON_POSITION_X, START_BUTTON_POSITION_Y + (2 * BUTTON_SPACING), BUTTON_WIDTH, BUTTON_HEIGHT);
            this.add(buttonResult);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        labMaster.renderFrame(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
