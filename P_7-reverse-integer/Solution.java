public class Solution {
    
    private int[] digits=new int[10];
    private int len=0;
    private int[] max={
        2,1,4,7,4,8,3,6,4,7
    };
    
    public int reverse(int x) {
        if(x!=Integer.MIN_VALUE){
            boolean unsigned=x>=0;
            max[9]=unsigned?7:8;
            x=unsigned?x:-x;
            while(x!=0){
                digits[len++]=x%10;
                x/=10;
            }
            if(len==max.length){
                int i=-1;
                while(++i<len && digits[i]==max[i]);
                if(digits[i]>max[i])
                    return 0;
            }
            int i=-1;
            int ret=0;
            while(++i<len)
                ret=ret*10+digits[i];
            return unsigned?ret:-ret;
        }else
            return 0;
    }
}
