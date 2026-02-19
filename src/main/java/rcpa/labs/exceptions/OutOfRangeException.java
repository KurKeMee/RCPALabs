package rcpa.labs.exceptions;

public class OutOfRangeException extends Exception {
    public OutOfRangeException(Double value) {
        super("Out of range: " + value);
    }
}
