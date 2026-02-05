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
     * Переменная для хранения экземпляра класса LabMaster
     */
    private static LabMaster instance;


    /**
     * Переменная-состояния программы
     * Необходима для переключения между лабораторными работами
     */
    private byte lab=LAB1;


    /**
     * Конструктор объявлен как private для паттерна Singleton
     *
     * @see LabMaster#getLabMaster()
     */
    private LabMaster(){}

    private String currentMessage = "";

    private int messageType = 0;

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
        if(lab == LAB1){
            //graphics.setColor(Color.BLACK);
            Color rectColor;
            if (messageType == 0) {
                rectColor = Color.RED;
            } else if (messageType == 1) {
                rectColor = Color.YELLOW;
            } else {
                rectColor = Color.GREEN;
            }

            graphics.setColor(rectColor);
            graphics.fillRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);

            graphics.setColor(Color.BLACK);
            graphics.drawRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);

            graphics.setFont(new Font("Arial", Font.PLAIN, 12));
            graphics.drawString(currentMessage, RECT_X + 10, RECT_Y + 20);
            //color = new Color(255, 200, 200);
            //graphics.setColor(Color);
        }
        else if(lab == LAB2){
            /*graphics.setFont(new Font("Arial", Font.PLAIN, 40));
            graphics.setColor(Color.BLACK);
            graphics.drawString("Lab2",100,100);*/
        }
    }

    /**
     * Устанавливает ошибку (красный)
     * @param message - текст
     */
    public void showError(String message) {
        this.currentMessage = message;
        this.messageType = 0;
    }

    /**
     * Устанавливает предупреждение (желтый)
     * @param message - текст
     */
    public void showWarning(String message) {
        this.currentMessage = message;
        this.messageType = 1;
    }

    /**
     * Устанавливает сообщение об успехе (зеленый)
     * @param message - текст
     */
    public void showSuccess(String message) {
        this.currentMessage = message;
        this.messageType = 2;
    }
    /**
     * Метод получения текущего состояния программы
     * @return byte - возвращает номер текущей лабораторной работы
     */
    public byte getLab(){
        return lab;
    }
}
