package rcpa.labs.model;

import rcpa.labs.view.IntegrationTable;
import rcpa.labs.view.LabPanel;

import javax.swing.*;

public class ButtonData {
    private int id;
    private String label;
    private IntegrationTable linkedTable;
    private LabPanel parentPanel;

    public ButtonData(int id, String label, LabPanel parentPanel) {
        this.parentPanel = parentPanel;
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

    public LabPanel getParentPanel() {
        return parentPanel;
    }

    public void setLinkedTable(IntegrationTable linkedTable) {
        this.linkedTable = linkedTable;
    }

    public IntegrationTable getLinkedTable() {
        return linkedTable;
    }
}
