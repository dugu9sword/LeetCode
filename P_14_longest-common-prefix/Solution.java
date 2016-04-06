public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0)
            return "";
        int len=Integer.MAX_VALUE;
        for(int i=0;i<strs.length;i++)
            len=Math.min(len,strs[i].length());
        int i=-1;
        s:while(++i<len)
            for(int j=1;j<strs.length;j++)
                if(strs[0].charAt(i)!=strs[j].charAt(i))
                    break s;
        return strs[0].substring(0,i);
    }
}
