package exceptions;

public class EmptyStringException extends Exception {

    public EmptyStringException() {
        System.out.println("String must not be empty or null");
    }

    public EmptyStringException(String message) {
        System.out.println(message);
    }
}
