package rcpa.labs.model;

import rcpa.labs.view.IntegrationTable;
import rcpa.labs.view.LabPanel;

import javax.swing.*;

/**
 * @author Ivan Monin
 * @author Danila Kokarev
 *
 * Класс для хранения параметров кнопки
 */
public class ButtonData {

    /**
     * Переменная для хранения идентификатора кнопки
     */
    private int id;


    /**
     * Переменная для хранения текста кнопки
     *
     * @see String
     */
    private String label;


    /**
     * Переменная для хранения привязнной таблицы
     *
     * @see IntegrationTable
     */
    private IntegrationTable linkedTable;


    /**
     * Перемення для хранения родительской панели
     *
     * @see LabPanel
     */
    private LabPanel parentPanel;


    /**
     * Переменная для хранения координаты x
     */
    private int x=-1;


    /**
     * Переменная для хранения координаты y
     */
    private int y=-1;


    /**
     * Видимость кнопки
     */
    private boolean visible;

    /**
     * Конструктор ButtonData
     * @param id            - идентификатор кнопки
     * @param label         - текст кнопки
     * @param parentPanel   - родительская панель {@link LabPanel}
     */
    public ButtonData(int id, String label, LabPanel parentPanel) {
        this.parentPanel = parentPanel;
        this.id = id;
        this.label = label;
    }

    /**
     * Метод получения идентификатора
     * @return int - возвращает идентификатор {@link #id}
     */
    public int getId() {
        return id;
    }

    /**
     * Метод устновки текста {@link #label}
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Метод получения текста
     * @return String - возвращает текст {@link #label}
     */
    public String getLabel() {
        return label;
    }

    /**
     * Метод получения родительской панели
     * @return LabPanel - возвращает родительскую панель {@link #parentPanel}
     */
    public LabPanel getParentPanel() {
        return parentPanel;
    }

    /**
     * Метод устновки присваиваемой таблицы {@link #linkedTable}
     */
    public void setLinkedTable(IntegrationTable linkedTable) {
        this.linkedTable = linkedTable;
    }

    /**
     * Метод получения присвоенной таблицы
     * @return IntegrationTable - возвращает таблицу {@link #linkedTable}
     */
    public IntegrationTable getLinkedTable() {
        return linkedTable;
    }

    /**
     * Метод для получения координаты X
     * @return int - возвращает координату x {@link #x}
     */
    public int getX() {
        return x;
    }

    /**
     * Метод для получения координаты y
     * @return int - возвращает координату y {@link #y}
     */
    public int getY() {
        return y;
    }

    /**
     * Метод для установки значения координаты x
     * @param x - параметр координаты x {@link #x}
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Метод для установки значения координаты y
     * @param y - параметр координаты y {@link #y}
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Метод для получения информации о видимости кнопки
     * @return boolean - возвращется текущая видимость кнопки {@link #visible}
     */
    public boolean isButtonVisible() {
        return visible;
    }

    /**
     * Метод для установки видимости кнопки
     * @param visible - параметр видимости кнопки {@link #visible}
     */
    public void setButtonVisible(boolean visible) {
        this.visible = visible;
    }
}
