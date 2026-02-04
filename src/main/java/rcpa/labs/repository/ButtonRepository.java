package rcpa.labs.repository;

import rcpa.labs.model.Button;
import rcpa.labs.model.ButtonData;
import rcpa.labs.model.ButtonType;
import rcpa.labs.view.AddButton;
import rcpa.labs.view.IntegrationTable;
import rcpa.labs.view.LabPanel;

import javax.swing.*;
import java.util.*;

/**
 * @author Ivan Monin
 * @author Danila Kokarev
 *
 * Класс для хранения кнопок
 */
public class ButtonRepository {

    /**
     * Переменная для хранения экземпляра класса ButtonRepository
     *
     * @see ButtonRepository
     */
    private static ButtonRepository instance;


    /**
     * Переменная для хранения родительской панели
     *
     * @see LabPanel
     */
    private LabPanel parentPanel;


    /**
     * Массив для хранения кнопок
     *
     * @see ArrayList
     * @see Button
     */
    private ArrayList<Button> buttons;


    /**
     * Переменная для хранения последнего идентификатора кнопки
     */
    private int lastId = 0;


    /**
     * Конструктор класса ButtonRepository
     * Конструктор объявлен private для паттерна Singleton
     *
     * @param parentPanel - ссылка на родительскую панель
     * @see ButtonRepository#getButtonRepository(LabPanel)
     */
    private ButtonRepository(LabPanel parentPanel) {
        this.parentPanel = parentPanel;
        buttons = new ArrayList<>();
    }

    /**
     * Метод для получения единственного экземпляра класса
     * Параметр synchronized необходим для исключения ситуации множественного создания экземляров класса
     *
     * @param parentPanel - ссылка на родительскую панель
     * @see ButtonRepository#ButtonRepository(LabPanel)
     * @return ButtonRepository - возвращает единственный экземпляр класса
     */
    public static synchronized ButtonRepository getButtonRepository(LabPanel parentPanel) {
        if (instance == null) {
            instance = new ButtonRepository(parentPanel);
        }
        return instance;
    }

    /**
     * Метод добавления кнопки без текста
     * 
     * @param type - тип создаваемой кнопки
     * @see ButtonRepository#addNewButton(ButtonType, String)
     */
    public void addNewButton(ButtonType type) {
        addNewButton(type, "");
    }

    /**
     * Метод добаления кнопки без текста с расположением и размером {@link JButton#setBounds(int, int, int, int)}
     *
     * @param type      - тип создаваемой кнопки
     * @param x         - расположение кнопки по горизонтали
     * @param y         - расположение кнопки по вертикали
     * @param width     - ширина кнопки
     * @param height    - высота кнопки
     * @see ButtonRepository#addNewButton(ButtonType, String)
     */
    public void addNewButton(ButtonType type,
                             int x,
                             int y,
                             int width,
                             int height) {
        addNewButton(type, "");
        JButton but = getButton(lastId - 1);
        but.setBounds(x, y, width, height);
    }

    /**
     * Метод добавления кнопки с текстом, расположением и размером {@link JButton#setBounds(int, int, int, int)}
     * @param type      - тип создаваемой кнопки
     * @param text      - текст кнопки
     * @param x         - расположение кнопки по горизонтали
     * @param y         - расположение кнопки по вертикали
     * @param width     - ширина кнопки
     * @param height    - высота кнопки
     * @see ButtonRepository#addNewButton(ButtonType, String)
     */
    public void addNewButton(ButtonType type,
                             String text,
                             int x,
                             int y,
                             int width,
                             int height) {
        addNewButton(type, text);
        JButton but = getButton(lastId - 1);
        but.setBounds(x, y, width, height);
    }

    /**
     * Метод добавления кнопки в репозиторий {@link #buttons}
     *
     * @param type - тип создаваемой кнопки
     * @param text - текст кнопки
     *
     * @see ButtonData#setLinkedTable(IntegrationTable)
     * @see AddButton#setButtonData(ButtonData)
     * @see ButtonType#create()
     */
    public void addNewButton(ButtonType type,
                             String text) {
        ButtonData newBD = new ButtonData(this.lastId++, text, this.parentPanel);

        newBD.setLinkedTable(Arrays.stream(parentPanel.getComponents())
                                                        .filter(comp -> comp instanceof IntegrationTable)
                                                        .map(pane -> (IntegrationTable) pane)
                                                        .findFirst()
                                                        .get());

        Button button = type.create();

        button.setButtonData(newBD);

        buttons.add(button);
    }

    /**
     * Метод получения кнопки по id
     * @param id - идентификтор кнопки {@link ButtonData#getId()}
     * @return Button - возвращает кнопку
     */
    public Button getButton(int id) {
        return buttons.stream()
                .filter(e -> e.getButtonData().getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод получения всех кнопок
     * @return List<Button> - возвращет список кнопок {@link #buttons}
     */
    public List<Button> getAllButtons() {
        return buttons;
    }
}
