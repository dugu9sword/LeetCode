/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(-1);
        ListNode tail=head;
        while(null!=l1 && null!=l2){
            if(l1.val<l2.val){
                tail.next=new ListNode(l1.val);
                l1=l1.next;
            }else{
                tail.next=new ListNode(l2.val);
                l2=l2.next;
            }
            tail=tail.next;
        }
        while(l2!=null){
            tail.next=new ListNode(l2.val);
            l2=l2.next;
            tail=tail.next;
        }
        while(l1!=null){
            tail.next=new ListNode(l1.val);
            l1=l1.next;
            tail=tail.next;
        }
        head=head.next;
        return head;
    }
}
