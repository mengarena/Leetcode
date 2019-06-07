/*

1038. Binary Search Tree to Greater Sum Tree

Given the root of a binary search tree with distinct values, 
modify it so that every node has a new value equal to the sum of the values of 
the original tree that are greater than or equal to node.val.

As a reminder, a binary search tree is a tree that satisfies these constraints:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 
Example 1:
Input: [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 
Note:
The number of nodes in the tree is between 1 and 100.
Each node will have value between 0 and 100.
The given tree is a binary search tree.

Medium

Amazon
*/


/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:

    // Method 1: InOrder to get original values, and then cumulative sum from the last to first, the cumulative sum will be the new value for the node
    // Method 2: Right In order, i.e. put the right first, then root, then left
    
    // 100%
    TreeNode* bstToGst(TreeNode* root) {
        if (!root) return root;
        
        stack<TreeNode*> stk;
        
        TreeNode* tmp = root;
        while (tmp) {
            stk.push(tmp);
            tmp = tmp->right;
        }
        
        int prevSum = 0;
        while (!stk.empty()) {
            TreeNode* tmp = stk.top();
            stk.pop();
            prevSum += tmp->val;
            tmp->val = prevSum;
            if (tmp->left) {
                TreeNode *tmp1 = tmp->left;
                while (tmp1) {
                    stk.push(tmp1);
                    tmp1 = tmp1->right;
                }
            }
        }
        
        return root;
    }
};
