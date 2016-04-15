/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
Better Solution：

public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> list = new ArrayList<Integer>();

    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode cur = root;

    while(cur!=null || !stack.empty()){
        while(cur!=null){
            stack.add(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        list.add(cur.val);
        cur = cur.right;
    }

    return list;
}
 
 
 
 
 */
public class Solution {
    
    static final int NN=0;
    static final int YN=1;
    static final int YY=2;
    
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> ret=new ArrayList<Integer>();
        if(root==null)
            return ret;
        Stack<TreeNode> stack=new Stack<TreeNode>();
        int[] visited=new int[1000];
        int vp=0;
        visited[vp]=NN;
        stack.push(root);
        while(vp!=-1){
            //System.out.println(stack.peek().val+":"+vp+"="+visited[vp]);
            if(visited[vp]==NN){
                visited[vp]++;
                if(stack.peek().left!=null){
                    stack.push(stack.peek().left);
                    visited[++vp]=NN;
                }
            }else if(visited[vp]==YN){
                ret.add(stack.peek().val);
                visited[vp]++;
                if(stack.peek().right!=null){
                    stack.push(stack.peek().right);
                    visited[++vp]=NN;
                }
            }else if(visited[vp]==YY){
                stack.pop();
                vp--;
            }
        }
        return ret;
    }
}