/*
 * 方法1 - 分治，纯位运算：java优先级 () , >>> , & , && , = 
 * 方法2 - 一个个算，见下面的被注释的代码
 * 
 * 如果多次调用该方法，如何提高效率？
 * 答案：新建一个HashMap<Byte,Byte> cache，在cache中存储一个byte的reverse后的值。
 * 对于每一个Integer，将其分为4个byte，每个byte分别reverse（如果多次调用，第一次reverse会存入cache，
 * 多次reverse只需要直接从cache里取即可，免去计算），然后再拼接byte。
 * 注释：其实这样的效率并不高，反而变慢了。可能是由于加入cache后，函数调用、分支预测、哈希查表本身耗时、
 * 哈希冲突、内存读写等，消耗更多的时间。
 *
 */
public class Solution {
    
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int ret=n;
        ret = ret >>> 16 | ret<<16;
        ret = (ret & 0xff00ff00) >>> 8 | (ret & 0x00ff00ff) << 8;
        ret = (ret & 0xf0f0f0f0) >>> 4 | (ret & 0x0f0f0f0f) << 4;
        ret = (ret & 0xcccccccc) >>> 2 | (ret & 0x33333333) << 2;
        ret = (ret & 0xaaaaaaaa) >>> 1 | (ret & 0x55555555) << 1;
        return ret;
    }
}


/*
public class Solution {
    
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int[] BIT=new int[32];
        for(int i=0;i<32;i++)
            BIT[i]=1<<i;
        int ret=0;
        for(int i=0;i<32;i++)
            if((BIT[i] & n)!=0)
                ret |= BIT[31-i];
        return ret;
    }
}
*/