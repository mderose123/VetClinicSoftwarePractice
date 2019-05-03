package exceptions;

public class InvalidTimeException extends Exception {

    public InvalidTimeException() {}

    public InvalidTimeException(String msg) {System.out.println(msg);}
}
