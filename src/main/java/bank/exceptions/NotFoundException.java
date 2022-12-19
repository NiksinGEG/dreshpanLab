package main.java.bank.exceptions;

public class NotFoundException extends RuntimeException{
    private final int id;
    private final Class<?> entityClass;
    public NotFoundException(int id, Class<?> entityClass) {
        this.id = id;
        this.entityClass = entityClass;
    }

    @Override
    public String getMessage() {
        return "Объект класса " + entityClass.getSimpleName() + " с id=" + id + " не найден";
    }
}
