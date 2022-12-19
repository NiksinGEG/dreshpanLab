package main.java.bank.exceptions;

import main.java.bank.entity.enums.CrudOperations;

public class CrudOperationException extends RuntimeException{
    private final String className;
    private final CrudOperations operation;
    public CrudOperationException(String message, Class<?> entityClass, CrudOperations operation) {
        super(message);
        this.className = entityClass.getSimpleName();
        this.operation = operation;
    }
    public CrudOperationException(String message, String className, CrudOperations operation) {
        super(message);
        this.className = className;
        this.operation = operation;
    }

    @Override
    public String getMessage() {
        return "Ошибка при выполнении метода \"" + operation.toString() + "\" над сущностью " + className + ": " + super.getMessage();
    }
}
