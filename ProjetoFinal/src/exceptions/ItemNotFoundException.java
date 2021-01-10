package exceptions;

public class ItemNotFoundException extends Exception {

    public ItemNotFoundException() { super("Item inexistente."); }

    public ItemNotFoundException(String message) { super(message); }
}