/**
 * 1、对于两个对称的情况，可以转化为一个情况处理。
 * 2、二分法首先确定可以允许的范围[m,n]，接着取left=m、right=n为初始值，取current=(left+right+1)/2为当前值，然后left=current+1或者right=current-1。current中的+1是因为整除号本身进行了部分情况的舍去操作，要在括号里补偿，否则right永远取不到（设想right=left+1），范围是[left,right)。
 */
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // point 1
        if(nums1.length>nums2.length){
            int[] tmp=nums1;
            nums1=nums2;
            nums2=tmp;
        }
        int m=nums1.length,n=nums2.length;
        if(m==0)
            return (nums2[(n-1)/2]+nums2[n/2]+0.0f)/2;
        int left=0,right=m;
        int i=0,j=0;
        do{
            // point 2
            i=(left+right+1)/2;
            j=(m+n)/2-i;
            if(i>0 && j<n && nums1[i-1]>nums2[j])
                right=i-1;
            else if(i<m && j>0 && nums2[j-1]>nums1[i])
                left=i+1;
            else
                break;
        }while(left<=right);
        int left1=i==0?Integer.MIN_VALUE:nums1[i-1];
        int right1=i==m?Integer.MAX_VALUE:nums1[i];
        int left2=j==0?Integer.MIN_VALUE:nums2[j-1];
        int right2=j==n?Integer.MAX_VALUE:nums2[j];
        if((m+n)%2==1)
            return right1<right2?right1:right2;
        else
            return ((left1>left2?left1:left2)+(right1<right2?right1:right2)+0.0f)/2;
    }
}
