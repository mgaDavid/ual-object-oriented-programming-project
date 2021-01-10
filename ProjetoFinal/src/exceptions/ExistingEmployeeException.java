package exceptions;


public class ExistingEmployeeException extends Exception {

    public ExistingEmployeeException() {
        super("Funcionário existente.");
    }

    public ExistingEmployeeException(String message) {
        super(message);
    }
}
