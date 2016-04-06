public class Solution {
    public boolean isPalindrome(int x) {
        int ret=0,c=0;
        int xx=x>0?x:-x;
        while(x!=0){
            c=x%10;
            ret=ret*10+c;
            x/=10;
        }
        System.out.println(x);
        return xx==ret;
    }
}
