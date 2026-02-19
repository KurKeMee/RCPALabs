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
    public String lowBorder;


    /**
     * Верхняя граница интегрирования
     */
    public String highBorder;


    /**
     * Шаг интегрирования
     */
    public String stepIntegration;


    /**
     * Результат вычисления
     */
    public String result;

    public RecIntegral(String lowBorder, String highBorder, String stepIntegration, String result) {
        this.lowBorder = lowBorder;
        this.highBorder = highBorder;
        this.stepIntegration = stepIntegration;
        this.result = result;
    }

    public String[] getStringArray() {
        return new String[]{lowBorder, highBorder, stepIntegration, result};
    }
}
