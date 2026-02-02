package rcpa.labs.repository;

import rcpa.labs.model.ButtonData;
import rcpa.labs.model.ButtonType;
import rcpa.labs.view.AddButton;
import rcpa.labs.view.DeleteButton;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ButtonRepository {

    private static ButtonRepository instance;
    private HashMap<ButtonData, JButton> buttons;
    private int lastId=0;

    private ButtonRepository() {
        buttons = new HashMap<>();
    }

    public static synchronized ButtonRepository getButtonRepository() {
        if (instance == null) {
            instance = new ButtonRepository();
        }
        return instance;
    }

    public void addNewButton(ButtonType type) {
        addNewButton(type,"");
    }

    public void addNewButton(ButtonType type,
                             int x,
                             int y,
                             int width,
                             int height) {
        addNewButton(type,"");
        JButton but = getButton(lastId-1);
        but.setBounds(x,y,width,height);
    }

    public void addNewButton(ButtonType type,
                             String text,
                             int x,
                             int y,
                             int width,
                             int height) {
        addNewButton(type,text);
        JButton but = getButton(lastId-1);
        but.setBounds(x,y,width,height);
    }

    public void addNewButton(ButtonType type,
                             String text) {
        ButtonData newBD = new ButtonData(this.lastId++, text);
        JButton button = type.create();

        if(button instanceof AddButton){
            ((AddButton) button).setButtonData(newBD);
        } else if (button instanceof DeleteButton) {
            ((DeleteButton) button).setButtonData(newBD);
        }

        buttons.put(newBD, button);
    }

    public JButton getButton(int id) {
        return buttons.entrySet()
                .stream()
                .filter(e->e.getKey().getId() == id)
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    public List<JButton> getAllButtons() {
        return new ArrayList<>(buttons.values());
    }
}
