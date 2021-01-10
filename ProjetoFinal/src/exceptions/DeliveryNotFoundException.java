package exceptions;


public class DeliveryNotFoundException extends Exception {

    public DeliveryNotFoundException() { super("Entrega inexistente."); }

    public DeliveryNotFoundException(String message) { super(message); }
}
