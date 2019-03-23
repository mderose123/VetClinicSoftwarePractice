package exceptions;

public class NullArgumentException extends Exception {

    public NullArgumentException() {
        System.out.println("Entered argument must not be null");
    }

    public NullArgumentException(String message) {
        System.out.println(message);
    }
}
