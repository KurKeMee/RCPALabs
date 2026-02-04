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
}
