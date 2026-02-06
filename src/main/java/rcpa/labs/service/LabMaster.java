package rcpa.labs.service;

import java.awt.*;

import static rcpa.labs.config.Configuration.*;

/**
 * @author Ivan Monin
 * @author Danila Kokarev
 *
 * Класс для рендеринга окна
 */
public class LabMaster {

    /**
     * Флаги для сообщений
     */
    public boolean isFieldEmpty = false;
    public boolean isTopBorderEmpty = false;
    public boolean isBottomBorderEmpty = false;
    public boolean isStepFieldEmpty = false;
    public boolean isLessThanZeroOrEqualToZero = false;
    public boolean isSomethingGoWrong = false;
    public boolean isRowNoSelected = false;
    public boolean isTopSmallerBottom = false;
    public boolean isAddNewRowSuccess = false;
    public boolean isDeleteRowSuccess = false;
    public boolean isCalculateRowSuccess = false;

    /**
     * Переменная для хранения экземпляра класса LabMaster
     */
    private static LabMaster instance;


    /**
     * Переменная-состояния программы
     * Необходима для переключения между лабораторными работами
     */
    private byte lab=LAB1;


    /**
     * Переменная для задержки перед исчезновением сообщения
     */
    private int logMessageExists =0;


    /**
     * Переменная для изменения прозрачности сообщения
     */
    private float currentLogMessageTextAlpha = 1;


    /**
     * Конструктор объявлен как private для паттерна Singleton
     *
     * @see LabMaster#getLabMaster()
     */
    private LabMaster(){}

    /**
     * Метод для получения единственного экземпляра класса
     *
     * @see LabMaster#LabMaster()
     * @return LabMaster - возвращает экземпляр класса LabMaster
     */
    public static synchronized LabMaster getLabMaster() {
        if (instance == null) {
            instance = new LabMaster();
        }
        return instance;
    }

    /**
     * Метод для отрисовки окна
     * @param graphics - графика передаваемая с панели
     */
    public void renderFrame(Graphics graphics) {
        graphics.setColor(new Color(240,230,140));
        graphics.fillRect(0,0,LAB_WIDTH,LAB_HEIGHT);
        graphics.setFont(new Font("Arial", Font.PLAIN, 50));
        graphics.setColor(Color.BLACK);
        graphics.drawString("e^-x", 50,500);
        if(lab == LAB1){
            checkLogMessage(graphics);
        }
        else if(lab == LAB2){
            /*graphics.setFont(new Font("Arial", Font.PLAIN, 40));
            graphics.setColor(Color.BLACK);
            graphics.drawString("Lab2",100,100);*/
        }
    }

    /**
     * Выводит сообщение на экран
     * @param g - графика
     * @param message - текст сообщения
     * @param color - цвет сообщения
     */
    private void showLogMessage(Graphics g, String message,Color color) {
        logMessageExists++;
        if(logMessageExists > 60 && currentLogMessageTextAlpha>0) {
            currentLogMessageTextAlpha-= 0.05F;
        }

        g.setColor(new Color(color.getRed()/ 255.0f,
                            color.getGreen()/ 255.0f,
                            color.getBlue()/ 255.0f,
                            currentLogMessageTextAlpha));

        g.fillRoundRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT,30,30);
        g.setColor(new Color(255/255.0f,
                            255/255.0f,
                            255/255.0f,
                                currentLogMessageTextAlpha));
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString(message, RECT_X + 10, RECT_Y + 20);

        if (currentLogMessageTextAlpha <= 0) {
            currentLogMessageTextAlpha = 1;
            logMessageExists = 0;

            isFieldEmpty = false;
            isTopBorderEmpty = false;
            isBottomBorderEmpty = false;
            isStepFieldEmpty = false;
            isLessThanZeroOrEqualToZero = false;
            isSomethingGoWrong = false;
            isRowNoSelected = false;
            isTopSmallerBottom = false;
            isAddNewRowSuccess = false;
            isDeleteRowSuccess = false;
            isCalculateRowSuccess = false;
        }
    }

    /**
     * Метод проверки наличия включенных флагов
     * @param graphics - графика
     */
    private void checkLogMessage(Graphics graphics){
        if(isFieldEmpty){
            showLogMessage(graphics,FIELDS_EMPTY,Color.red);
        }
        else if(isTopSmallerBottom){
            showLogMessage(graphics,TOP_BIGGER_BOTTOM,Color.red);
        }
        else if(isSomethingGoWrong){
            showLogMessage(graphics,SOMETHING_GO_WRONG,Color.red);
        }
        else if(isRowNoSelected){
            showLogMessage(graphics,ROW_NO_SELECTED,Color.red);
        }
        else if(isBottomBorderEmpty){
            showLogMessage(graphics,BOTTOM_BORDER_EMPTY,Color.orange);
        }
        else if(isTopBorderEmpty){
            showLogMessage(graphics,TOP_BORDER_EMPTY,Color.orange);
        }
        else if(isStepFieldEmpty){
            showLogMessage(graphics,STEP_FIELD_EMPTY,Color.orange);
        }
        else if (isLessThanZeroOrEqualToZero) {
            showLogMessage(graphics, LESS_THAN_ZERO_OR_EQUAL_TO_ZERO, Color.orange);
        }
        else if(isAddNewRowSuccess){
            showLogMessage(graphics,ADD_NEW_ROW_SUCCESS,Color.green);
        }
        else if(isCalculateRowSuccess){
            showLogMessage(graphics,CALCULATE_ROW_SUCCESS,Color.green);
        }
        else if(isDeleteRowSuccess){
            showLogMessage(graphics,DELETE_ROW_SUCCESS,Color.green);
        }
    }

    /**
     * Метод получения текущего состояния программы
     * @return byte - возвращает номер текущей лабораторной работы
     */
    public byte getLab(){
        return lab;
    }
}
