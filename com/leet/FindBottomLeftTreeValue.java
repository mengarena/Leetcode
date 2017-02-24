/*
Given a binary tree, find the leftmost value in the last row of the tree.

Example 1:
Input:

    2
   / \
  1   3

Output:
1

Example 2: 
Input:

        1
       / \
      2   3
     /   / \
    4   5   6
       /
      7

Output:
7
Note: You may assume the tree (i.e., the given root node) is not NULL.

*/

//Medium
//Microsoft

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
    public int findBottomLeftValue(TreeNode root) {
        if (root.left == null && root.right == null) return root.val;
        List<TreeNode> level = new ArrayList<>();
        int size = 0;
        int ans = 0;

        level.add(root);
        
        while (!level.isEmpty()) {
            size = level.size();
            
            for (int i=0; i<size; i++) {
                TreeNode tmp = level.remove(0);
                if (i == 0) ans = tmp.val;
                
                if (tmp.left != null) level.add(tmp.left);
                if (tmp.right != null) level.add(tmp.right);
            }
        }
        
        return ans;
    }
}

