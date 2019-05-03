package exceptions;

public class AppointmentBookedException extends Exception {

    public AppointmentBookedException() {}

    public AppointmentBookedException(String msg) {
        System.out.println(msg);
    }
}
