package main.java.bank.base;

import main.java.bank.entity.enums.CrudOperations;
import main.java.bank.exceptions.CrudOperationException;

import java.util.LinkedList;

public class EntityContainer <TypeEntity extends BaseEntity>{

    private LinkedList<TypeEntity> entities;
    public EntityContainer(){
        entities = new LinkedList<>();
    }

    public LinkedList<TypeEntity> get(){
        return entities;
    }
    public TypeEntity get(int id){
        return entities.stream()
                .filter((entity) -> entity.id == id)
                .findFirst()
                .orElse(null);
    }

    public void add(TypeEntity entity) throws CrudOperationException {
        if(entities.contains(entity))
            throw new CrudOperationException("Коллекция уже содержит данный элемент", entity.getClass(), CrudOperations.Create);
        var count = entities.size();
        if(entity.id < count)
            throw new CrudOperationException("В коллекции уже существует элемент с таким id", entity.getClass(), CrudOperations.Create);
        entity.id = count + 1;
        entities.add(entity);
    }
    public TypeEntity update(TypeEntity entity) throws CrudOperationException{
        var savedEntity = get(entity.id);
        if(savedEntity == null)
            throw new CrudOperationException("В коллекции нет такой сущности", entity.getClass(), CrudOperations.Update);
        savedEntity = entity;
        return  get(entity.id);
    }
    public void delete(TypeEntity entity) throws CrudOperationException{
        if(!entities.contains(entity))
            throw new CrudOperationException("В коллекции нет такого элемента для удаления", entity.getClass(), CrudOperations.Delete);
        entities.remove(entity);
    }
    public void delete(int id) throws CrudOperationException{
        var entityToDelete = entities.stream()
                .filter((entity) -> entity.id == id)
                .findFirst()
                .orElse(null);
        if(entityToDelete == null)
            throw new CrudOperationException("В коллекции нет такого id элемента для удаления", "", CrudOperations.Delete);
        entities.remove(entityToDelete);
    }
}
