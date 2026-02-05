package rcpa.labs.view;

import rcpa.labs.config.Configuration;
import rcpa.labs.model.ButtonType;
import rcpa.labs.repository.ButtonRepository;
import rcpa.labs.service.LabMaster;

import javax.swing.*;
import java.awt.*;

import static rcpa.labs.config.Configuration.*;

/**
 * @author Ivan Monin
 * @author Danila Kokarev
 *
 * Класс для отрисовки панели на окне
 */
public class LabPanel extends JPanel {

    /**
     * Переменная хранящая экземпляр класса LabMaster
     *
     * @see LabMaster
     */
    private final LabMaster labMaster;


    /**
     * Переменная для хранения экземпляра класса ButtonRepository
     *
     * @see ButtonRepository
     */
    private final ButtonRepository buttonRepository;


    /**
     * Конструктор с инициализацией элементов на панели
     *
     * @param frame - передаваемый параметр окна
     */
    public LabPanel(JFrame frame) {
        this.labMaster = LabMaster.getLabMaster();
        this.buttonRepository = ButtonRepository.getButtonRepository(this);

        initPanel(frame);
        chooseLab();
    }

    /**
     * Метод инициализации панели
     * @param frame - передаваемый параметр окна
     */
    private void initPanel(JFrame frame){
        this.setBounds(0, 0, Configuration.LAB_WIDTH, Configuration.LAB_HEIGHT);
        setLayout(null);

        frame.setSize(LAB_WIDTH, LAB_HEIGHT);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Метод выбора лабораторной работы
     */
    private void chooseLab(){
        if (labMaster.getLab() == LAB1) {
            this.add(new IntegrationTable(
                    new String[]{
                            "↓ гр. интегрирования",
                            "↑ гр. интегрирования",
                            "Шаг интегрирования",
                            "Результат"
                    }, 300, 20));

            buttonRepository.addNewButton(ButtonType.ADD_BUTTON,
                                            "Добавить",
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
            buttonRepository.addNewButton(ButtonType.DELETE_BUTTON,
                                        "Удалить",
                                        START_BUTTON_POSITION_X,
                                     START_BUTTON_POSITION_Y + 3*BUTTON_SPACING,
                                        BUTTON_WIDTH,
                                        BUTTON_HEIGHT);
            buttonRepository.addNewButton(ButtonType.CALCULATE_BUTTON,
                                        "Вычислить",
                                        START_BUTTON_POSITION_X + 3*BUTTON_SPACING,
                                        START_BUTTON_POSITION_Y,
                                        BUTTON_WIDTH,
                                        BUTTON_HEIGHT);
            for (int i = 0; i < 3; i++) {
                JTextField text = new JTextField();
                text.setBounds(START_FIELD_POSITION_X, START_FIELD_POSITION_Y + (i * FIELD_SPACING), TEXT_WIDTH, TEXT_HEIGHT);
                this.add(text);

                if (i == 0) {
                    JLabel label = new JLabel("↓ гр. интегрирования :");
                    label.setFont(new Font("Arial", Font.PLAIN, 15));
                    label.setBounds(START_LABEL_POSITION_X, START_LABEL_POSITION_Y, LABEL_WIDTH, LABEL_HEIGHT);
                    this.add(label);
                } else if (i == 1) {
                    JLabel label = new JLabel("↑ гр. интегрирования :");
                    label.setFont(new Font("Arial", Font.PLAIN, 15));
                    label.setBounds(START_LABEL_POSITION_X, START_LABEL_POSITION_Y + (i * LABEL_SPACING), LABEL_WIDTH, LABEL_HEIGHT);
                    this.add(label);
                } else {
                    JLabel resultLabel = new JLabel("Шаг интегрирования:");
                    resultLabel.setFont(new Font("Arial", Font.PLAIN, 15));
                    resultLabel.setBounds(START_LABEL_POSITION_X, START_LABEL_POSITION_Y + (i * LABEL_SPACING), LABEL_WIDTH, LABEL_HEIGHT);
                    this.add(resultLabel);
                }
            }
        }

        buttonRepository.getAllButtons().forEach(this::add);
    }

    /**
     * Метод для отрисовки панели
     * @param g - the Graphics context in which to paint
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        labMaster.renderFrame(g);
    }
}
