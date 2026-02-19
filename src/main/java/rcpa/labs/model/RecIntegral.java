package rcpa.labs.model;

/**
 * @author Ivan Monin
 * @author Danila Kokarev
 *
 * Класс, представляющий запись в таблице
 * Содержит данные для одной строки таблицы
 */
public class RecIntegral {

    /**
     * Нижняя граница интегрирования
     */
    private String lowBorder;


    /**
     * Верхняя граница интегрирования
     */
    private String highBorder;


    /**
     * Шаг интегрирования
     */
    private String stepIntegration;


    /**
     * Результат вычисления
     */
    private String result;

    /**
     * Конструктор записи таблицы
     * @param lowBorder       - нижняя граница
     * @param highBorder      - верхняя граница
     * @param stepIntegration - шаг интегрирования
     * @param result          - результат
     */
    public RecIntegral(String lowBorder, String highBorder, String stepIntegration, String result) {
        this.lowBorder = lowBorder;
        this.highBorder = highBorder;
        this.stepIntegration = stepIntegration;
        this.result = result;
    }

    /**
     *  Метод преобразования записи в массив строк
     * @return String[]{lowBorder, highBorder, stepIntegration, result}
     */
    public String[] getStringArray() {
        return new String[]{lowBorder, highBorder, stepIntegration, result};
    }

    public String getLowBorder() {
        return lowBorder;
    }

    public void setLowBorder(String lowBorder) {
        this.lowBorder = lowBorder;
    }

    public String getStepIntegration() {
        return stepIntegration;
    }

    public void setStepIntegration(String stepIntegration) {
        this.stepIntegration = stepIntegration;
    }

    public String getHighBorder() {
        return highBorder;
    }

    public void setHighBorder(String highBorder) {
        this.highBorder = highBorder;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
