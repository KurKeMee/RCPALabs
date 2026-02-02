package rcpa.labs.view;

import rcpa.labs.model.ButtonData;

import javax.swing.*;

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
            data.setLabel(data.getLabel()+'1');
            this.setText(data.getLabel());
        });
    }

    public void setButtonData(ButtonData data) {
        this.data = data;
        this.setText(data.getLabel());
        addEventListener();
    }
}
