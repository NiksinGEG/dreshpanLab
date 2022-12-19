package main.java.bank.base;

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

    public void add(TypeEntity entity) throws Exception{
        if(entities.contains(entity))
            throw new Exception("А вот и нет, в коллекции уже есть такая сушность");
        var count = entities.size();
        if(entity.id > count)
            throw new Exception("Ключик такой уже существует");
        entity.id = count + 1;
        entities.add(entity);
    }
    public TypeEntity update(TypeEntity entity) throws Exception{
        var savedEntity = get(entity.id);
        if(savedEntity == null)
            throw new Exception("Нет такой сущности с id = " + entity.id);
        savedEntity = entity;
        return  get(entity.id);
    }
    public void delete(TypeEntity entity) throws Exception{
        if(!entities.contains(entity))
            throw new Exception("А нет такой сущности, пригодной для удаления");
        entities.remove(entity);
    }
    public void delete(int id) throws Exception{
        var entityToDelete = entities.stream()
                .filter((entity) -> entity.id == id)
                .findFirst()
                .orElse(null);
        if(entityToDelete == null)
            throw new Exception("Сущности, подлежащей удалению, с id = " + id + " не существует");
        entities.remove(entityToDelete);
    }
}
