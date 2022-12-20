package main.java.bank.helper;

import main.java.bank.base.BaseEntity;

import javax.print.attribute.standard.MediaSize;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class Serializer {
    private static int depth = 0;
    private static boolean isOtherEntity = false;
    private static String otherEntityName = "";
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

    private static HashMap<String, String> deserializeString(String serializedString) throws Exception{
        HashMap<String, String> res = new HashMap<String, String>();
        int i = 0;
        serializedString = serializedString.replaceAll("\\s+","");
        while (i < serializedString.length() && serializedString.charAt(i) != ':') i++;
        var key = serializedString.substring(0, i);
        if(key.equals("id") && isOtherEntity){
            isOtherEntity = false;
            key = otherEntityName + key;
        }
        var value = serializedString.substring(i + 1, serializedString.length() - 1);
        if(value == ""){
            isOtherEntity = true;
            otherEntityName = key;
        }
        res.put(key, value);
        return res;
    }

    private static Collection<HashMap<String, String>> deserializee(String serializedObject) throws Exception {
        Collection<HashMap<String, String>> res = new LinkedList<>();
        int i = 0;
        while(i < serializedObject.length() && serializedObject.charAt(i) != '{')
            i++;
        int opened = 1;
        i++;
        var start = i;
        int startPos = i;
        int endPos = 0;
        while (i < serializedObject.length() && opened > 0)
        {
            char ch = serializedObject.charAt(i);
            if(ch == '{')
                opened++;
            if(ch == '}')
                opened--;
            i++;
        }
        if (opened > 0)
            throw new Exception("Ошибка сериализации: имеются не закрытые '{'");
        if(opened < 0)
            throw new Exception("Ошибка сериализации: имеются излишние закрывающие '}'");
        i = start;
        while(i != serializedObject.length())
        {
            var ch = serializedObject.charAt(i);
            while (ch != '\t' && ch != '}'){
                i++;
                ch = serializedObject.charAt(i);
            }
            endPos = i;
            i++;
            var trim = serializedObject.substring(startPos, endPos);
            if(trim.contains(":"))
                res.add(deserializeString(trim));
            startPos = endPos;
        }
        return res;
    }
    public static Collection<HashMap<String, String>> deserialize(String serializedObjects)throws Exception{
        Collection<HashMap<String, String>> res = new LinkedList<>();
        SectionReader sr = new SectionReader(serializedObjects);
        String section = sr.readSection();
        while(section != null) {
            res = Serializer.deserializee(section);
            section = sr.readSection();
        }
        return null;
    }
}
