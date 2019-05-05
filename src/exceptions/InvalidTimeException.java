package exceptions;

public class InvalidTimeException extends RuntimeException {

    public InvalidTimeException() {}

    public InvalidTimeException(String msg) {System.out.println(msg);}
}
