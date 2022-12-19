package main.java.bank.exceptions;

public class ObjectAccessException extends RuntimeException{
    private final String objectName;
    private final String reason;
    public ObjectAccessException(String objectName, String reason) {
        this.objectName = objectName;
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return "Ошибка доступа к объекту \"" + objectName + "\" по причине " + reason;
    }
}
