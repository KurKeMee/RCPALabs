package rcpa.labs.model;

import rcpa.labs.view.AddButton;

import javax.swing.*;
import java.util.function.Supplier;

public enum ButtonType{
    ADD_BUTTON (AddButton::new);

    private final Supplier<JButton> supplier;

    ButtonType(Supplier<JButton> supplier) {
        this.supplier = supplier;
    }

    public JButton create(){
        return supplier.get();
    }
}
