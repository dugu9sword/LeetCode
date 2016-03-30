public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] nums_sorted=new int[nums.length];
        System.arraycopy(nums,0,nums_sorted,0,nums.length);
        Arrays.sort(nums_sorted);
        int start=0;
        int end=nums_sorted.length;
        while(start<end){
            while(nums_sorted[start]+nums_sorted[--end]>target);
            if(nums_sorted[end]+nums_sorted[start]==target)
                break;
            while(nums_sorted[++start]+nums_sorted[end]<target);
            if(nums_sorted[end]+nums_sorted[start]==target)
                break;
        }
        
        int[] ret=new int[2];
        int index=0;
        int a=nums_sorted[start];
        int b=nums_sorted[end];
        for(int i=0;i<nums.length;i++)
            if(nums[i]==a || nums[i]==b)
                ret[index++]=i;
        return ret;
    }
}
