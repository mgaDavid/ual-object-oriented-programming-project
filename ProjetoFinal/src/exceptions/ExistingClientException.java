package exceptions;

public class ExistingClientException extends Exception {

    public ExistingClientException() {
        super("Cliente existente.");
    }

    public ExistingClientException(String message) {
        super(message);
    }
}
