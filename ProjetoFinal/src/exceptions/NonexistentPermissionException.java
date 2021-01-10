package exceptions;


public class NonexistentPermissionException extends Exception {

    public NonexistentPermissionException() {
        super("Permissão inexistente.");
    }

    public NonexistentPermissionException(String message) {
        super(message);
    }
}

