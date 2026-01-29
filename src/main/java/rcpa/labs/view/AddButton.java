package rcpa.labs.view;

import rcpa.labs.config.Configuration;
import rcpa.labs.model.ButtonData;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


public class AddButton extends JButton{

    private ButtonData data;

    public AddButton() {}

    public AddButton(ButtonData data) {
        this.data = data;
        super.setText(data.getLabel());
        addEventListener();
    }

    private void addEventListener() {
        this.addActionListener(e -> {
            ArrayList<JTextField> textFields =
                    Arrays.stream(data.getParentPanel().getComponents())
                                                            .filter(comp -> comp instanceof JTextField)
                                                            .map(tf -> (JTextField) tf)
                                                            .collect(Collectors.toCollection(ArrayList::new));

            String[] values = textFields.stream().map(tf -> tf.getText()).toArray(String[]::new);
            data.getLinkedTable().addRow(values);
        });
    }

    public void setButtonData(ButtonData data) {
        this.data = data;
        this.setText(data.getLabel());
        addEventListener();
    }
}
