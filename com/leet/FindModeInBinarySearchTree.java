/*
501. Find Mode in Binary Search Tree

Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.

For example:
Given BST [1,null,2,2],
   1
    \
     2
    /
   2
   
return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? 
(Assume that the implicit stack space incurred due to recursion does not count).
*/

//Easy
//Google

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
    private int maxCount = 0;
    private int curCount = 0;
    private int modeCount = 0;
    private int curValue = 0;
    private int[] mode;
    
    public int[] findMode(TreeNode root) {
        inorder(root);
        mode = new int[modeCount];
        curCount = 0;
        modeCount = 0;
        inorder(root);
        return mode;
    }
    
    private void handleValue(int val) {
        if (val != curValue) {
            curValue = val;
            curCount = 0;
        }
        
        curCount++;
        
        if (curCount > maxCount) {
            maxCount = curCount;
            modeCount = 1;
        } else if (curCount == maxCount) {
            if (mode != null) {
                mode[modeCount] = val;
            }
            
            modeCount++;
        }
    }
    
    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        handleValue(root.val);
        inorder(root.right);
    }
}
