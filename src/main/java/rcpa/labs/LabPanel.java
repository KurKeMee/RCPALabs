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
            text1.setBounds(110,20, TEXT_WIDTH, TEXT_HEIGHT);
            this.add(text1);

            JTextField text2 = new JTextField();
            text2.setBounds(110, 100, TEXT_WIDTH, TEXT_HEIGHT);
            this.add(text2);

            JTextField resultText = new JTextField();
            resultText.setBounds(110, 180, TEXT_WIDTH, TEXT_HEIGHT);
            this.add(resultText);

            JButton button1 = new JButton("Нажать");
            button1.setBounds(20, 50, BUTTON_WIDTH, BUTTON_HEIGHT);
            this.add(button1);

            JButton button2 = new JButton("Нажать");
            button2.setBounds(20, 130, BUTTON_WIDTH, BUTTON_HEIGHT);
            this.add(button2);

            JButton buttonResult = new JButton("Нажать");
            buttonResult.setBounds(20, 210, BUTTON_WIDTH, BUTTON_HEIGHT);
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
