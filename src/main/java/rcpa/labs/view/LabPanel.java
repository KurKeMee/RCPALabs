package rcpa.labs.view;

import rcpa.labs.config.Configuration;
import rcpa.labs.model.ButtonType;
import rcpa.labs.repository.ButtonRepository;
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
    private final ButtonRepository buttonRepository;

    public int count=0;

    /**
     * Конструктор
     * @param frame - передаваемый параметр окна
     */
    public LabPanel(JFrame frame) {
        this.labMaster = LabMaster.getLabMaster();
        this.buttonRepository = ButtonRepository.getButtonRepository();

        this.setBounds(0,0, Configuration.LAB_WIDTH, Configuration.LAB_HEIGHT);
        setLayout(null);

        frame.setSize(LAB_WIDTH,LAB_HEIGHT);
        frame.setLocationRelativeTo(null);

        if(labMaster.getLab() == LAB1) {
            buttonRepository.addNewButton(ButtonType.ADD_BUTTON,
                                            START_BUTTON_POSITION_X,
                                            START_BUTTON_POSITION_Y,
                                            BUTTON_WIDTH,
                                            BUTTON_HEIGHT);
            buttonRepository.addNewButton(ButtonType.ADD_BUTTON,
                                        "С текстом",
                                        START_BUTTON_POSITION_X,
                                        START_BUTTON_POSITION_Y+BUTTON_SPACING,
                                        BUTTON_WIDTH,
                                        BUTTON_HEIGHT);
            for (int i = 0; i < 3; i++) {
                JTextField text = new JTextField(count);
                text.setBounds(START_FIELD_POSITION_X, START_FIELD_POSITION_Y + (i * FIELD_SPACING), TEXT_WIDTH, TEXT_HEIGHT);
                this.add(text);

                //JButton button = new JButton("Нажать");
                //int count =0;
                //button.setBounds(START_BUTTON_POSITION_X, START_BUTTON_POSITION_Y + (i * BUTTON_SPACING), BUTTON_WIDTH, BUTTON_HEIGHT);
                //this.add(button);

                if (i < 2) {
                    JLabel label = new JLabel("Значение " + (i + 1) + ":");
                    label.setFont(new Font("Arial", Font.PLAIN, 15));
                    label.setBounds(START_LABEL_POSITION_X, START_LABEL_POSITION_Y + (i * LABEL_SPACING), LABEL_WIDTH, LABEL_HEIGHT);
                    this.add(label);
                }
                else {
                    JLabel resultLabel = new JLabel("Результат:");
                    resultLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                    resultLabel.setBounds(START_LABEL_POSITION_X, START_LABEL_POSITION_Y + (i * LABEL_SPACING), LABEL_WIDTH, LABEL_HEIGHT);
                    this.add(resultLabel);
                }
            }

            buttonRepository.getAllButtons().forEach(this::add);
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
