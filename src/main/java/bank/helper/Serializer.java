package main.java.bank.helper;

import main.java.bank.base.BaseEntity;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class Serializer {
    private static int depth = 0;
    private static String serialize(Object object) throws Exception{
        var res = "";
        Field[] fields = object.getClass().getFields();
        for(int i = 0; i < fields.length; i++){

            for(int j = 0; j < depth;j++)
                res += "\t";
            if(fields[i].get(object) instanceof BaseEntity){
                depth++;
                res += "\t" + fields[i].getName() + " : {\n" + serialize(fields[i].get(object));
                depth--;
                i++;
            }
            if(fields[i].get(object) instanceof Collection<?>) continue;
            var value = fields[i].get(object);
            res+= "\t" + fields[i].getName() + " : " + (value == null ? "null" : value.toString()) + ",\n";
        }
        for(int j = 0; j < depth;j++)
            res += "\t";
        res += "},\n";
        return res;
    }
    public static <T> String serialize(Collection<T> objects) throws Exception{
        var res = "";
        for(Object object : objects) {
            res += "{\n";
            res += serialize(object);
        }
        return res;
    }
    private static HashMap<String, String> deserializee(String serializedObject) throws Exception {
        HashMap<String, String> res = new HashMap<>();
        return null;
    }
    public static Collection<HashMap<String, String>> deserialize(String serializedObjects)throws Exception{
        LinkedList<HashMap<String, String>> res = new LinkedList<>();
        SectionReader sr = new SectionReader(serializedObjects);
        String section = sr.readSection();
        while(section != null) {
            res.add(Serializer.deserializee(section));
            section = sr.readSection();
        }

        return null;
    }
}
