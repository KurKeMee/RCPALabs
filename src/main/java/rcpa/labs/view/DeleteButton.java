package rcpa.labs.view;

import rcpa.labs.model.ButtonData;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeleteButton extends JButton {

    private ButtonData data;

    public DeleteButton(){};

    public DeleteButton(ButtonData data) {
        this.data = data;
        super.setText(data.getLabel());
        addEventListener();
    }

    private void addEventListener() {
        this.addActionListener(e->{
            Pattern pattern = Pattern.compile(".$");
            Matcher matcher = pattern.matcher(data.getLabel());
            String str = matcher.replaceAll("");

            data.setLabel(str);
            this.setText(data.getLabel());
        });
    }

    public void setButtonData(ButtonData data) {
        this.data = data;
        this.setText(data.getLabel());
        addEventListener();
    }
}
