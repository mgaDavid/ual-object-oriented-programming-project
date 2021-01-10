package exceptions;


class DriverWithoutPermissionException extends Exception {

    public DriverWithoutPermissionException() { super("Condutor sem permissões."); }

    public DriverWithoutPermissionException(String message) { super(message); }
}