import java.util.regex.*;

public class Solution {
    public boolean isNumber(String s) {
        Pattern pattern = Pattern.compile("\\s*[+-]?(\\d*\\.?\\d+|\\d+\\.?\\d*)(e[+-]?\\d+)?\\s*");
        Matcher matcher = pattern.matcher(s);
        boolean b= matcher.matches();
        return b;
    }
}
