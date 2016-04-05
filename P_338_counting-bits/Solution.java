public class Solution {
    public int[] countBits(int num) {
        int[] ret=new int[num+1];
        ret[0]=0;
        int lower=1;
        while(true){
            if(2*lower-1<num){
                for(int i=0;i<lower;i++)
                    ret[lower+i]=ret[i]+1;
                lower*=2;
            }else{
                for(int i=0;i<num-lower+1;i++)
                    ret[lower+i]=ret[i]+1;
                break;
            }
        }
        return ret;
    }
}
