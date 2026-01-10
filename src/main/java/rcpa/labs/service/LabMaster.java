package rcpa.labs.service;

import java.awt.*;

import static rcpa.labs.config.Configuration.*;

/**
 * @authors Ivan Monin, Kokarev Danila
 *
 * Класс для рендеринга окна
 */
public class LabMaster {

    private static LabMaster instance;

    private byte lab=LAB1;

    /**
     * Метод для получения единственного экземпляра класса
     *
     * @return LabMaster
     */
    public static synchronized LabMaster getInstance() {
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
        if(lab == LAB1){
            graphics.setColor(Color.BLUE);
            graphics.fillRect(0, 0, LAB_WIDTH, LAB_HEIGHT);
            graphics.setFont(new Font("Arial", Font.PLAIN, 40));
            graphics.setColor(Color.BLACK);
            graphics.drawString("Lab1",100,100);
        }
        else if(lab == LAB2){
            graphics.setFont(new Font("Arial", Font.PLAIN, 40));
            graphics.setColor(Color.BLACK);
            graphics.drawString("Lab2",100,100);
        }
    }

    public byte getLab(){
        return lab;
    }
}
