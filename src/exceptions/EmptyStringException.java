package exceptions;

public class EmptyStringException extends Exception {

    public EmptyStringException() {}

    public EmptyStringException(String message) {
        System.out.println(message);
    }
}
