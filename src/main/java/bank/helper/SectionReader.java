package main.java.bank.helper;

public class SectionReader {
    private int ptr;
    private String input;
    public SectionReader(String input){
        ptr = 0;
        this.input = input;
    }
    public String readSection(){
        if(ptr > input.length() - 1 || ptr == input.length())
            return null;
        while(ptr < input.length() && input.charAt(ptr) != '{')
            ptr++;
        var res = "" + input.charAt(ptr);
        ptr++;
        int opened = 1;
        while (ptr < input.length() && opened > 0)
        {
            char ch = input.charAt(ptr);
            if(ch == '{')
                opened++;
            if(ch == '}')
                opened--;
            res += ch;
            ptr++;
        }
        return res;
    }
}
