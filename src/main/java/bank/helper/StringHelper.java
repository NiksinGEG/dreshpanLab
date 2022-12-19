package main.java.bank.helper;

import java.util.Collection;
import java.util.StringJoiner;

public class StringHelper {
    public static <T> String fromCollectionToString(Collection<T> collection){
        if(collection == null || collection.size() == 0) return "[]";
        StringJoiner res = new StringJoiner(",\n");
        for(T item : collection) {
            res.add(item.toString());
        }
        return res.toString();
    }
}
