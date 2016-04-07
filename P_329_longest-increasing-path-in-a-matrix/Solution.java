/*
 * DP+BFS：搜索+避免重复计算
 * 二维数组max中，max[i][j]表示matrix[i][j]出发的最大路径长度
 * 		若max[i][j]=0，则表示该数值还未被计算
 * 		若max[i][j]=-1，则表示该数值准备被计算（在BFS中用到）
 * 		若max[i][j]>0，则表示该数值已经被计算好
 * 1、从矩阵中找出第一个没有被计算的点
 * 2、放入队列，BFS，把周围比它大、未计算的点放入队列，搜索下去，直到找不到为止
 * 3、从队列中，倒序取出数字，如果max[i][j]周围没有准备被计算的点（值为-1），max[i][j]={1，周围比它大的点的值+1}
 * 4、反复刷新队列，直到队列中所有点数值被计算好
 * 5、取出下一个没有被计算的点……
 * 
 */
public class Solution {
    
    int max[][];
    int x,y;
    int m,n;
    int queue[][];
    int header,tailer;
    
    int D[][]={
        {-1,0},{1,0},{0,-1},{0,1}
    };
    
    public int longestIncreasingPath(int[][] matrix) {
        m=matrix.length;
        if(0==m)return 0;
        n=matrix[0].length;
        if(0==n)return 0;
        max=new int[m][n];
        queue=new int[m*n][2];
        x=0;
        y=0;
        int tmpx,tmpy;
        int ans=0;
        
        while(hasNext()){
            header=0;
            tailer=0;
            queue[header][0]=x;
            queue[header][1]=y;
            while(header<=tailer){
                tmpx=queue[header][0];
                tmpy=queue[header][1];
                header++;
                
                for(int i=0;i<D.length;i++)
                    if(tmpx+D[i][0]>=0 && tmpx+D[i][0]<m && tmpy+D[i][1]>=0 && tmpy+D[i][1]<n
                        && matrix[tmpx+D[i][0]][tmpy+D[i][1]]>matrix[tmpx][tmpy]
                        && max[tmpx+D[i][0]][tmpy+D[i][1]]==0){
                        max[tmpx+D[i][0]][tmpy+D[i][1]]=-1;
                        tailer++;
                        queue[tailer][0]=tmpx+D[i][0];
                        queue[tailer][1]=tmpy+D[i][1];
                    }
            }
            
            /*
            System.out.print("queue:");
            for(int i=0;i<header;i++)
                System.out.print("("+queue[i][0]+","+queue[i][1]+")   ");
            System.out.println();
            */
            boolean notallhandled=true;
            while(notallhandled){
                s:while(--header>=0){
                    tmpx=queue[header][0];
                    tmpy=queue[header][1];
                    if(max[tmpx][tmpy]>0)
                        continue;
                    
                    int tmpmax=1;

                    for(int i=0;i<D.length;i++)
                        if(tmpx+D[i][0]>=0 && tmpx+D[i][0]<m && tmpy+D[i][1]>=0 && tmpy+D[i][1]<n
                            && matrix[tmpx+D[i][0]][tmpy+D[i][1]]>matrix[tmpx][tmpy]){
                                if(max[tmpx+D[i][0]][tmpy+D[i][1]]==-1)
                                    continue s;
                                //System.out.println("("+tmpx+","+tmpy+")"+" Compare "+tmpmax+" with "+(max[tmpx+D[i][0]][tmpy+D[i][1]]+1));
                                if(tmpmax<max[tmpx+D[i][0]][tmpy+D[i][1]]+1)
                                    tmpmax=max[tmpx+D[i][0]][tmpy+D[i][1]]+1;
                        }
                    max[tmpx][tmpy]=tmpmax;
                    //System.out.println("("+tmpx+","+tmpy+")"+"="+tmpmax);
                }
                
                notallhandled=false;
                for(int i=0;i<tailer+1;i++)
                    if(max[queue[i][0]][queue[i][1]]==-1){
                        notallhandled=true;
                        break;
                    }
                header=tailer+1;
            }
            if(ans<max[queue[0][0]][queue[0][1]])
                ans=max[queue[0][0]][queue[0][1]];
            //show();
        }
        return ans;
    }
    
    public boolean hasNext(){
        while(x<m && 0!=max[x][y]){
            y++;
            x=y<n?x:x+1;
            y=y<n?y:0;
        }
        return x<m;
    }
    
    public void show(){
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print(max[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("======");
    }
}
