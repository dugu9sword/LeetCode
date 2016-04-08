public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n=triangle.size();
        int[] min=new int[n];
        int[] tmp=new int[n];
        List<Integer> row=triangle.get(n-1);
        for(int i=0;i<n;i++)
            min[i]=row.get(i);
        for(int x=n-2;x>=0;x--){
            row=triangle.get(x);
            for(int i=0;i<=x;i++)
                tmp[i]=row.get(i)+(min[i]<min[i+1]?min[i]:min[i+1]);
            for(int i=0;i<=x;i++)
                min[i]=tmp[i];
        }
        return min[0];
    }
}
