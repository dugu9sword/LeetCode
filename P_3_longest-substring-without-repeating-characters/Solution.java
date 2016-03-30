/**
 * 更好的方法：
 * 从左向右扫描，每次更新当前字母最后出现的位置，将当前位置减去最后出现的位置就是当前的无重复字母串的长度
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        boolean[] flag=new boolean[256];
        for(int i=0;i<flag.length;i++)
            flag[i]=false;
        
        int ret=0,cur=0;
        int start=0;
        int end=0;
        
        while(end!=s.length()){
            if(flag[s.charAt(end)]){
                while(s.charAt(start)!=s.charAt(end)){
                    flag[s.charAt(start)]=false;
                    cur--;
                    start++;
                }
                start++;
            }else{
                flag[s.charAt(end)]=true;
                cur++;
            }
            end++;
            ret=ret<cur?cur:ret;
        }
        //ret=ret<cur?cur:ret;
        return ret;
    }
}
