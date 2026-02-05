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
        }
        else if(lab == LAB2){
            /*graphics.setFont(new Font("Arial", Font.PLAIN, 40));
            graphics.setColor(Color.BLACK);
            graphics.drawString("Lab2",100,100);*/
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
