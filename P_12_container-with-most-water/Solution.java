/*
 * 对于(left,right)的组合，如果height[left]<height[right]，
 * 那么(left,left+1)、(left,left+2)、...、(left,right-1)都不可能符合，
 * 因此将left抛弃，考虑(left+1,right)区间。
 */
public class Solution {
    public int maxArea(int[] height) {
        int left=0,right=height.length-1,maxValue=0;
        while(left<right)
            if(height[right]>height[left]){
                maxValue=Math.max(maxValue,(right-left)*height[left]);
                left++;
            }else{
                maxValue=Math.max(maxValue,(right-left)*height[right]);
                right--;
            }
        return maxValue;
    }
}
