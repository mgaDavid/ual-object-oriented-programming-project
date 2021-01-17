package exceptions;


public class InvalidPermissionException extends Exception {

    public InvalidPermissionException() {
        super("Permissão inválida.");
    }

    public InvalidPermissionException(String message) {
        super(message);
    }
}
