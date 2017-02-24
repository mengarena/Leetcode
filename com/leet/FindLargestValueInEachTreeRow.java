/*
You need to find the largest value in each row of a binary tree.

Example:

Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        
        List<TreeNode> level = new ArrayList<>();
        int maxVal;
        int size, i;
        
        level.add(root);
        
        while (!level.isEmpty()) {
            maxVal = Integer.MIN_VALUE;
            size = level.size();
            
            for (i=0; i<size; i++) {
                TreeNode tmp = level.remove(0);
                maxVal = Math.max(maxVal, tmp.val);
                if (tmp.left != null) level.add(tmp.left);
                if (tmp.right != null) level.add(tmp.right);
            }
            
            ans.add(maxVal);
        }
        
        return ans;
    }
}

