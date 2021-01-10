package exceptions;


public class NonexistentCategoryException extends Exception {

    public NonexistentCategoryException() {
        super("Categoria inexistente.");
    }

    public NonexistentCategoryException(String message) {
        super(message);
    }
}

