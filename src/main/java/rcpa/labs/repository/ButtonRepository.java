package rcpa.labs.repository;

import rcpa.labs.model.ButtonData;
import rcpa.labs.model.ButtonType;
import rcpa.labs.view.AddButton;
import rcpa.labs.view.IntegrationTable;
import rcpa.labs.view.LabPanel;

import javax.swing.*;
import java.util.*;

public class ButtonRepository {

    private static ButtonRepository instance;
    private LabPanel parentPanel;
    private HashMap<ButtonData, JButton> buttons;
    private int lastId = 0;

    private ButtonRepository(LabPanel parentPanel) {
        this.parentPanel = parentPanel;
        buttons = new HashMap<>();
    }

    public static synchronized ButtonRepository getButtonRepository(LabPanel parentPanel) {
        if (instance == null) {
            instance = new ButtonRepository(parentPanel);
        }
        return instance;
    }

    public void addNewButton(ButtonType type) {
        addNewButton(type, "");
    }

    public void addNewButton(ButtonType type,
                             int x,
                             int y,
                             int width,
                             int height) {
        addNewButton(type, "");
        JButton but = getButton(lastId - 1);
        but.setBounds(x, y, width, height);
    }

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

    public void addNewButton(ButtonType type,
                             String text) {
        ButtonData newBD = new ButtonData(this.lastId++, text, this.parentPanel);

        newBD.setLinkedTable(Arrays.stream(parentPanel.getComponents())
                                                        .filter(comp -> comp instanceof IntegrationTable)
                                                        .map(pane -> (IntegrationTable) pane)
                                                        .findFirst()
                                                        .get());

        JButton button = type.create();

        if (button instanceof AddButton) {
            ((AddButton) button).setButtonData(newBD);
        }

        buttons.put(newBD, button);
    }

    public JButton getButton(int id) {
        return buttons.entrySet()
                .stream()
                .filter(e -> e.getKey().getId() == id)
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    public List<JButton> getAllButtons() {
        return new ArrayList<>(buttons.values());
    }
}
