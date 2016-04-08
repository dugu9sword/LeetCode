public class Solution {
    public int lengthOfLastWord(String s) {
        char[] str=s.toCharArray();
        int p=str.length-1;
        int start,end;
        while(p>-1 && str[p]==' ')
            p--;
        end=p;
        while(p>-1 && str[p]!=' ')
            p--;
        start=p;
        return end-start;
    }
}
