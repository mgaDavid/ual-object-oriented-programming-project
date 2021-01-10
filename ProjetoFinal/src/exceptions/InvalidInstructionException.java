package exceptions;

public class InvalidInstructionException extends Exception{

    public InvalidInstructionException() {
        super("Instrução inválida.");
    }

    public InvalidInstructionException(String message) {
        super(message);
    }
}
