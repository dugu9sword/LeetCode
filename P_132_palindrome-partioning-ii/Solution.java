/**
 * 核心思路：
 * 	min[k]表示0～k间最小的cut数目。
 * 	使用动态规划，如果字符串的i~j位是回文数，那么min[j]=取小(min[j],min[i-1]+1)
 * 
 * 下面这个算法是
 * 	1、从左向右扫描字符串
 * 	2、扫描到第i位时，从i位向左右延展，寻找以之为中心的回文
 * 	3、把i位前的min值扩展到i位后对称位置
 * 	4、此时第i位后的min值还没有确定，以第x为例，要在i不断右移过程中，不断更新，直到i越过了x
 * 算法2和3是
 * 	1、从左向右扫描字符串
 * 	2、扫描到第i位时，以第i位为末尾向前延展，寻找以之为末尾的回文
 * 	3、把i位前的min值扩展到第i位
 * 	4、此时第i位的min在多次比较中确定了
 * 	注释：算法2和3的区别
 * 		算法2在i指针右移的过程中，动态生成回文表palin（palin[i][j]表示第i到j个是否字符构成回文数），同时动态更新min
 * 		算法3先生成回文表，再更新min
 * 
 * 总之，指针i经过min数组内某个位置后，这个位置的值就确定了。
 * 
 * 算法4用的BFS+DP，naive，速度慢
 */
public class Solution {

    char[] str;
    int len;
    int[] min;

    public int minCut(String string) {
        str=string.toCharArray();
        len=string.length();
        if(len==0)return 0;
        min=new int[len];
        for(int i=0;i<len;i++)
            min[i]=i;
        
        for(int i=0;i<len;i++){
            for(int j=0;j<i+1 && j<len-i && str[i-j]==str[i+j];j++)
                min[i+j]=j<i?Math.min(min[i-j-1]+1,min[i+j]):0;
            for(int j=0;j<i && j<len-i && str[i-1-j]==str[i+j];j++)
                min[i+j]=j<i-1?Math.min(min[i-j-2]+1,min[i+j]):0;
        }
        
        return min[len-1];
    }

}

/*
 * 算法2
public int minCut(String s) {
    char[] c = s.toCharArray();
    int n = c.length;
    int[] cut = new int[n];
    boolean[][] pal = new boolean[n][n];

    for(int i = 0; i < n; i++) {
        int min = i;
        for(int j = 0; j <= i; j++) {
            if(c[j] == c[i] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
                pal[j][i] = true;  
                min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
            }
        }
        cut[i] = min;
    }
    return cut[n - 1];
}
*/

/*
 * 算法3
public class Solution {

    char[] str;
    int len;
    boolean[][] palin;
    int[] min;

    public int minCut(String string) {
        str=string.toCharArray();
        len=string.length();
        if(len==0)return 0;
        palin=new boolean[len][len];
        min=new int[len];
        for(int i=0;i<len;i++){
            Arrays.fill(palin[i], false);
            min[i]=i;
        }

        for(int i=0;i<len;i++){
            initPalin(i,i);
            initPalin(i,i+1);
        }
        
        for(int i=0;i<len;i++)
            for(int j=0;j<=i;j++)
                if(palin[j][i])
                    min[i]=j>0?Math.min(min[j-1]+1,min[i]):0;

        return min[len-1];
    }

    public void initPalin(int p,int q){
        while(p>=0 && p<len && q>=0 && q<len && str[p]==str[q]){
            palin[p][q]=true;
            p--;
            q++;
        }
    }

}
*/

/*
 * 算法4
 * TIME LIMIT EXCEEDED的解法，使用BFS+DP
public class Solution {

    long _start,_end;
    void _s(){_start=System.currentTimeMillis();}
    void _e(){_end=System.currentTimeMillis();System.out.println("Time: "+(_end-_start));}

    char[] str;
    int len;
    boolean[][] palin;
    boolean[][] unvisited;
    int[] min;

    public int minCut(String string) {
        _s();
        str=string.toCharArray();
        len=string.length();
        if(len==0)return 0;
        palin=new boolean[len][len];
        unvisited=new boolean[len][len];
        min=new int[len+1];
        for(int i=0;i<len;i++){
            Arrays.fill(palin[i], false);
            Arrays.fill(unvisited[i],true);
        }
        Arrays.fill(min,8888);
        min[len]=0;

        for(int i=0;i<len;i++){
            initPalin(i,i);
            initPalin(i,i+1);
        }
        _e();

        //BFS+DP
        _s();
        int header=0,tailer=-1;
        int[][] queue=new int[len*(len+1)/2+1][2];
        //初始化队列的开头
        for(int i=0;i<len;i++){
            if(palin[0][i] && unvisited[0][i]){
                tailer++;
                queue[tailer][0]=0;
                queue[tailer][1]=i;
                unvisited[0][i]=false;
            }
        }
        _e();

        //正向BFS，生成完整的队列
        _s();
        int s,t;
        ArrayList<Integer> list;
        while(header<=tailer){
            s=queue[header][0];
            t=queue[header][1];

            for(int i=t+1;i<len;i++){
                if(unvisited[t+1][i] && palin[t+1][i]){
                    tailer++;
                    queue[tailer][0]=t+1;
                    queue[tailer][1]=i;
                    unvisited[t+1][i]=false;
                }
            }

            header++;
        }
        _e();


        //逆向DP，生成min
        _s();
        int tmp;
        while(--header>=0){
            s=queue[header][0];
            t=queue[header][1];
            tmp=min[t+1]+1;
            //System.out.println("Now s = "+s+" t = "+t+" with min[s] ="+min[s]+" and min[t] = "+min[t]);
            min[s]=min[s]<tmp?min[s]:tmp;
        }
        _e();

        return min[0]-1;
    }

    public void initPalin(int p,int q){
        while(p>=0 && p<len && q>=0 && q<len && str[p]==str[q]){
            palin[p][q]=true;
            p--;
            q++;
        }
    }

    public void show(){
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++)
                System.out.print(palin[i][j]+" ");
            System.out.println();
        }
    }
}

*/
