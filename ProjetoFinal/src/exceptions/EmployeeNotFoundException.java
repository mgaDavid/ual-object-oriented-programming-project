package exceptions;

public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException() { super("Funcionário inexistente"); }

    public EmployeeNotFoundException(String message) { super(message); }
}