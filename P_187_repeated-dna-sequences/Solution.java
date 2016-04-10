/*
 * excuse me? 穷举……
 * 下面的算法是动规（动规你妹啊，复杂度是n^2）,实际上穷举+Hash，复杂度只有n。 
 */
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<String> repeated=new HashSet<String>(),set=new HashSet<String>();
        String tmp;
        for(int i=0;i<s.length()-9;i++){
            tmp=s.substring(i,i+10);
            if(!set.add(tmp))
                repeated.add(tmp);
        }
        return new ArrayList(repeated);
    }
}

/*
 * 
public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<Integer> endIndex=new ArrayList<Integer>();
        HashSet<String> set=new HashSet<String>();
        List<String> ret=new ArrayList<String>();
        char[] str=s.toCharArray();
        int len=str.length;
        byte[][] dp=new byte[len][len];
        for(int i=0;i<len;i++)
            for(int j=0;j<=i;j++){
                if(0==i || 0==j)
                    dp[i][j]=1;
                else if(str[i]==str[j]){
                    dp[i][j]=(byte)(dp[i-1][j-1]+1);
                    if(dp[i][j]>=10 && i!=j)
                        endIndex.add(i);
                }else
                    dp[i][j]=0;
            }
        for(int end:endIndex)
            set.add(s.substring(end-9,end+1));
        Iterator iterator=set.iterator();
        while(iterator.hasNext())
            ret.add((String)iterator.next());
        return ret;
    }
}
*/
