package exceptions;

public class InvalidTimeException extends Exception {

    public InvalidTimeException() { System.out.println("Must be valid value of time ");}

    public InvalidTimeException(String msg) {System.out.println(msg);}
}
