package rcpa.labs.service;

public class IntegrationTask extends Thread{
    private final double startX;
    private final double endX;
    private final double step;
    private double partialSum = 0;
    private final boolean useTrapMethod;

    public IntegrationTask(double startX, double endX, double step, boolean useTrapMethod) {
        this.startX = startX;
        this.endX = endX;
        this.step = step;
        this.useTrapMethod = useTrapMethod;
    }

    @Override
    public void run() {
        double x = startX;
        while (x < endX) {
            double nextX = Math.min(x + step, endX);
            if (useTrapMethod) {
                partialSum += (nextX - x) * (Math.exp(-x) + Math.exp(-nextX)) / 2;
            } else {
                partialSum += Math.exp(-x) * step;
            }
            x = nextX;

            if (Thread.interrupted()) {
                return;
            }
        }
    }

    public Double getPartialSum() {
        return partialSum;
    }
}
