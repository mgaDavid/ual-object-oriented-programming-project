package exceptions;

public class NotAManagerException extends Exception {

    public NotAManagerException() {
        super("Funcionário incorreto.");
    }

    public NotAManagerException(String message) {
        super(message);
    }
}
