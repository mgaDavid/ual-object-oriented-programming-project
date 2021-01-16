package exceptions;

public class LoaderWithoutPermissionException extends Exception {

    public LoaderWithoutPermissionException() { super("Carregador sem permissões."); }

    public LoaderWithoutPermissionException(String message) { super(message); }
}
