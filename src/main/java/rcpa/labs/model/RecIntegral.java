package rcpa.labs.model;

public class RecIntegral {
    public String lowBorder;
    public String highBorder;
    public String stepIntegration;
    public String result;

    public RecIntegral(String lowBorder, String highBorder, String stepIntegration, String result) {
        this.lowBorder = lowBorder;
        this.highBorder = highBorder;
        this.stepIntegration = stepIntegration;
        this.result = result;
    }

    public String[] getStringArray() {
        return new String[]{lowBorder, highBorder, stepIntegration, result};
    }
}
