package rcpa.labs.model;

import rcpa.labs.view.AddButton;
import rcpa.labs.view.CalculateButton;
import rcpa.labs.view.DeleteButton;

import javax.swing.*;
import java.util.function.Supplier;

public enum ButtonType{
    ADD_BUTTON (AddButton::new),
    DELETE_BUTTON(DeleteButton::new),
    CALCULATE_BUTTON(CalculateButton::new);

    private final Supplier<JButton> supplier;

    ButtonType(Supplier<JButton> supplier) {
        this.supplier = supplier;
    }

    public JButton create(){
        return supplier.get();
    }
}
