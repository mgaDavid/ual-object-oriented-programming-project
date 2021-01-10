package exceptions;

public class ExistingLocalException extends Exception {

    public ExistingLocalException() { super("Local existente."); }

    public ExistingLocalException(String message) { super(message); }
}