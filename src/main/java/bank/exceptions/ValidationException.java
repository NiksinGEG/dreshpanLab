package main.java.bank.exceptions;

public class ValidationException extends RuntimeException{
    private final String fieldName;
    private final Object value;
    public ValidationException(String objectName, String fieldName, Object value) {
        super(objectName);
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public String getMessage() {
        return "Ошибка валидации " + super.getMessage() + ". Поле "
                + fieldName + " имело значение " + value.toString() + ".";
    }
}
