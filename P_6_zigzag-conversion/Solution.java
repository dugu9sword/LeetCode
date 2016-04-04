/**
 * 索引的zigzag示意图 
 * 0				 2n-2				 4n-4
 * 1			2n-3 2n-1			4n-5 4n-3
 * 2		2n-4	 2n			4n-6	 4n-2
 * ......
 * n-2	n			 3n-2	3n
 * n-1				 3n-1
 * 对于第row行，第k列的值s，有
 *   / row=0时，k*(2n-2)
 *   |                 / k为奇数 (k+1)/2*(2n-2)-row
 * s=| row在[1,n-2] -> |    （这里的/是除号，不是整除号，这两个式子可以合并，见代码）
 * 	 |                 \ k为偶数 k/2*(2n-2)+row
 *   \ row=n-1时，k*(2n-1)+n-1
 */
public class Solution {
    
    private int row=0,k=0,n=0,gap=0,len=0;
    
    public String convert(String s, int numRows) {
        if(numRows==1)
            return s;
        n=numRows;
        gap=2*n-2;
        len=s.length();
        
        char[] sold=s.toCharArray();
        char[] snew=new char[len];
        int i=-1;
        while(++i<len)
            snew[i]=sold[next()];
        
        return new String(snew);
    }
    
    public int next(){
        int ret=0;
        if(row==0 || row==n-1)
            ret=k*gap+row;
        else if(row==n)
            return -1;
        else
            ret=(k+1)/2*gap+row-k%2*2*row;

        if(ret<len){
            k++;
            return ret;   
        }else{
            row++;
            k=0;
            return next();
        }
    }
}
