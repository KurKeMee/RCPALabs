package rcpa.labs;

import rcpa.labs.config.Configuration;
import rcpa.labs.service.LabMaster;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame(Configuration.LAB_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(Configuration.LAB_WIDTH, Configuration.LAB_HEIGHT);
        frame.setLayout(null);
        frame.add(new LabPanel(frame));
        frame.setVisible(true);
    }
}