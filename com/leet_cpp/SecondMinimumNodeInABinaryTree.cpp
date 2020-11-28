/*

671. Second Minimum Node In a Binary Tree

Given a non-empty special binary tree consisting of nodes with the non-negative value, 
where each node in this tree has exactly two or zero sub-node. 
If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

Given such a binary tree, you need to output the second minimum value in the set made of 
all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
 
Example 2:
Input: 
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.

Easy
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

    // 94%
    int findSecondMinimumValue(TreeNode* root) {
        if (!root) return -1;
        if (!root->left && !root->right) return -1;

        int minLeft = findSpecifiedMin(root->left, root->val);
        int minRight = findSpecifiedMin(root->right, root->val);
        
        if (minLeft == -1 && minRight == -1) return -1;
        if (minLeft != -1 && minRight != -1) return min(minLeft, minRight);
        return max(minLeft, minRight);  // One of them is -1, the one large (i.e. not -1) is the answer
    }
    
    // Find the min value in the tree whose value is not "val"
    // If does not exist such a node, return -1;
    int findSpecifiedMin(TreeNode* root, int val) {
        if (!root->left && !root->right) {
            if (root->val == val)
                return -1;
            else
                return root->val;
        }
        
        int minLeft = -1;
        int minRight = -1;
       
        if (root->left->val != val) {  // If different, root->left->val will be the smallest on the left sub tree
            minLeft = root->left->val;
        } else {
            minLeft = findSpecifiedMin(root->left, val);
        }
        
        if (root->right->val != val) {
            minRight = root->right->val;
        } else {
            minRight = findSpecifiedMin(root->right, val);
        }
        
        if (minLeft != -1 && minRight != -1) {
            return min(minLeft, minRight);
        } else if (minLeft == -1 && minRight == -1) {
            return -1;
        } else {  // One of them is -1, the other is not
            return max(minLeft, minRight);
        }
    }
};
