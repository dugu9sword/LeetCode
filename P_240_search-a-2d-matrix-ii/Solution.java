public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length==0 || matrix[0].length==0)
            return false;
        return search(matrix,0,matrix.length-1,0,matrix[0].length-1,target);
    }
    
    public boolean search(int[][] matrix,int xs,int xe,int ys,int ye,int target){
        //System.out.println("x range from "+xs+" to "+xe+",y range from "+ys+" to "+ye);
        if(xs==xe && ys==ye)
            return matrix[xs][ys]==target;
        int x1=xs,x2=xs,y1=ys,y2=ys;
        while(x2<=xe){
            if(matrix[x2][ys]<target)
                x2++;
            else if(matrix[x2][ys]>target)
                break;
            else
                return true;
        }
        x2--;
        x2=x2>=xs?x2:xs;
        while(y2<=ye){
            if(matrix[xs][y2]<target)
                y2++;
            else if(matrix[xs][y2]>target)
                break;
            else
                return true;
        }
        y2--;
        y2=y2>ys?y2:ys;
        while(x1<=x2){
            if(matrix[x1][y2]<target)
                x1++;
            else if(matrix[x1][y2]>target)
                break;
            else
                return true;
        }
        x1=x1<=x2?x1:x2;
        while(y1<=y2){
            if(matrix[x2][y1]<target)
                y1++;
            else if(matrix[x2][y1]>target)
                break;
            else
                return true;
        }
        y1=y1<=y2?y1:y2;
        return search(matrix,x1,x2,y1,y2,target);
    }
}
