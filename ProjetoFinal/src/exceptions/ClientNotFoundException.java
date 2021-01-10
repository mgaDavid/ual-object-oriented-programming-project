package exceptions;

public class ClientNotFoundException extends Exception {

    public ClientNotFoundException() {
        super("Cliente inexistente.");
    }

    public ClientNotFoundException(String message) {
        super(message);
    }
}
