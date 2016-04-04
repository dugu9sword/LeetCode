/**
 * 最初的做法是把s1倒转为s2,然后用动态规划寻找s1和s2的最大公共子串。
 * 这样是不对的，因为这样找到的是s1中两个未必连续的倒转序列，比如abdeba中的ab和ba。
 */
public class Solution {
    
    private int start=0,maxlen=0;
    private char[] str;
    
    public String longestPalindrome(String s) {
        str=s.toCharArray();
        for(int i=0;i<s.length();i++){
            update(i,i);
            update(i,i+1);
        }
        return s.substring(start,start+maxlen);
        //return start+" "+maxlen;
    }
    
    private void update(int c1,int c2){
        while(c1>-1 && c2<str.length && str[c1]==str[c2]){
            c1--;
            c2++;
        };
        c1++;
        c2--;
        if(c2-c1+1>maxlen){
            maxlen=c2-c1+1;
            start=c1;
        }
    }
}
