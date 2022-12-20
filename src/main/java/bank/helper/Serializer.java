package main.java.bank.helper;

import main.java.bank.base.BaseEntity;
import main.java.bank.entity.Employee;
import main.java.bank.entity.PaymentAccount;

import javax.print.attribute.standard.MediaSize;
import java.lang.reflect.Field;
import java.sql.Struct;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class Serializer {
    private static String key;
    private static String value;
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
                if(fields[i].get(object).getClass() == Employee.class) continue;
                if(fields[i].get(object).getClass() == PaymentAccount.class) continue;
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

    private static void deserializeString(String serializedString) throws Exception{
        HashMap<String, String> res = new HashMap<String, String>();
        int i = 0;
        serializedString = serializedString.replaceAll("\\s+","");
        while (i < serializedString.length() && serializedString.charAt(i) != ':') i++;
        var keyy = serializedString.substring(0, i);
        if(keyy.equals("id") && isOtherEntity){
            isOtherEntity = false;
            keyy = otherEntityName + keyy;
        }
        var valuee = serializedString.substring(i + 1, serializedString.length() - 1);
        if(valuee == ""){
            isOtherEntity = true;
            otherEntityName = keyy;
        }
        key = keyy;
        value = valuee;
    }

    private static HashMap<String, String> deserializee(String serializedObject) throws Exception {
        HashMap<String, String> res = new HashMap<>();
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
            {
                deserializeString(trim);
                res.put(key, value);
            }

            startPos = endPos;
        }
        return res;
    }
    public static HashMap<String, String> deserialize(String serializedObjects)throws Exception{
        HashMap<String, String> res;
        SectionReader sr = new SectionReader(serializedObjects);
        String section = sr.readSection();
        res = Serializer.deserializee(section);
        return res;
    }
}
