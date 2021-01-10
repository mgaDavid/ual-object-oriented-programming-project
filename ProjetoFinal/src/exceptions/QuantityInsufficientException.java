package exceptions;


class QuantityInsufficientException extends Exception {

    public QuantityInsufficientException() { super("Quantidade Insuficiente."); }

    public QuantityInsufficientException(String message) { super(message); }
}