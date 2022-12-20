package main.java.bank.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileHelper {
    static String rootDir = "C:\\Users\\DICK-PC\\Desktop\\Уник\\dreshpanLab\\src\\main\\java\\bank";

    public static String get(String destination) throws Exception {
        File file = new File(rootDir + destination);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder res = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) res.append(line);
        reader.close();
        return res.toString();
    }

    public static void send(String destination, String toSend) throws Exception {
        File file = new File(rootDir + destination);
        FileWriter writer = new FileWriter(file, false);
        writer.write(toSend);
        writer.close();
    }

}
