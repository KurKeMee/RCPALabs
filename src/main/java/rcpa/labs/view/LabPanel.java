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
 * @author Ivan Monin
 * @author Danila Kokarev
 *
 * Класс для отрисовки панели на окне
 */
public class LabPanel extends JPanel implements ActionListener {

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
        Timer timer = new Timer(MILLISECONDS_PER_FRAME,this);
        this.labMaster = LabMaster.getLabMaster();
        this.buttonRepository = ButtonRepository.getButtonRepository(this);

        initPanel(frame);
        chooseLab();
        timer.start();
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
     * Метод выбора лабораторной работы и добавления компонентов на панель
     */
    private void chooseLab(){
        if (labMaster.getLab() == LAB1) {
            this.add(new IntegrationTable(
                    new String[]{
                            "↓ гр. интегрирования",
                            "↑ гр. интегрирования",
                            "Шаг интегрирования",
                            "Результат"
                    }, 300, 20,this));

            buttonRepository.addNewButton(ButtonType.ADD_BUTTON,
                                            "Добавить",
                                            ADD_BUTTON_POSITION_X,
                                            ADD_BUTTON_POSITION_Y,
                                            BUTTON_WIDTH,
                                            BUTTON_HEIGHT);
            buttonRepository.addNewButton(ButtonType.DELETE_BUTTON,
                                        "Удалить",
                                        DELETE_BUTTON_POSITION_X,
                                        DELETE_BUTTON_POSITION_Y,
                                        BUTTON_WIDTH,
                                        BUTTON_HEIGHT);
            buttonRepository.addNewButton(ButtonType.CALCULATE_BUTTON,
                                        "Вычислить",
                                        CALCULATE_BUTTON_POSITION_X,
                                        CALCULATE_BUTTON_POSITION_Y,
                                        BUTTON_WIDTH,
                                        BUTTON_HEIGHT);
            buttonRepository.addNewButton(ButtonType.CALCULATE_TRAP_BUTTON,
                                        "Метод трапеции",
                                            CALCULATE_TRAP_BUTTON_POSITION_X,
                                            CALCULATE_TRAP_BUTTON_POSITION_Y,
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
     * Метод включения флага "Все поля пустые"
     *
     * @see LabMaster
     */
    public void isFieldEmpty (){
        labMaster.isFieldEmpty=true;
    }

    /**
     * Метод включения флага "Поле верхняя граница не заполнено"
     *
     * @see LabMaster
     */
    public void isTopBorderEmpty (){
        labMaster.isTopBorderEmpty=true;
    }

    /**
     * Метод включения флага "Поле нижняя граница не заполнено"
     *
     * @see LabMaster
     */
    public void isBottomBorderEmpty (){
        labMaster.isBottomBorderEmpty=true;
    }

    /**
     * Метод включения флага "Поле шаг интегрирования не заполнено"
     *
     * @see LabMaster
     */
    public void isStepFieldEmpty (){
        labMaster.isStepFieldEmpty=true;
    }

    /**
     * Метод включения флага "Что-то пошло не так"
     *
     * @see LabMaster
     */
    public void isSomethingGoWrong (){
        labMaster.isSomethingGoWrong=true;
    }

    /**
     * Метод включения флага "Не выбрано ни одной строки"
     *
     * @see LabMaster
     */
    public void isRowNoSelected (){
        labMaster.isRowNoSelected=true;
    }

    /**
     * Метод включения флага "Верхняя граница меньше нижней границы"
     *
     * @see LabMaster
     */
    public void isTopSmallerBottom(){
        labMaster.isTopSmallerBottom =true;
    }

    /**
     * Метод включения флага "Строка успешно добавлена"
     *
     * @see LabMaster
     */
    public void isAddNewRowSuccess (){
        labMaster.isAddNewRowSuccess=true;
    }

    /**
     * Метод включения флага "Строка успешно удалена"
     *
     * @see LabMaster
     */
    public void isDeleteRowSuccess (){
        labMaster.isDeleteRowSuccess=true;
    }

    /**
     * Метод включения флага "Значение успешно вычислено"
     *
     * @see LabMaster
     */
    public void isCalculateRowSuccess (){
        labMaster.isCalculateRowSuccess=true;
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

    /**
     * Метод для перерисовки окна
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
