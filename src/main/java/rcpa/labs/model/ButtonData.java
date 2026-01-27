package rcpa.labs.model;

public class ButtonData {
    private int id;
    private String label;

    public ButtonData(int id, String label) {
        this.id = id;
        this.label = label;
    }


    public int getId() {
        return id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
