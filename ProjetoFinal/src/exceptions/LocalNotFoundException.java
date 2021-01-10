package exceptions;

public class LocalNotFoundException extends Exception {

    public LocalNotFoundException() { super("Local inexistente."); }

    public LocalNotFoundException(String message) { super(message); }
}