/*

965. Univalued Binary Tree

A binary tree is univalued if every node in the tree has the same value.

Return true if and only if the given tree is univalued.

Example 1:
Input: [1,1,1,1,1,null,1]
Output: true

Example 2:
Input: [2,2,2,5,2]
Output: false
 
Note:
The number of nodes in the given tree will be in the range [1, 100].
Each node's value will be an integer in the range [0, 99].


Easy

Google, Box
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

    // 100%
    bool isUnivalTree(TreeNode* root) {
        if (!root) return true;
        
        bool left = true;
        bool right = true;
        
        if (root->left) {
            if (!isUnivalTree(root->left) || root->val != root->left->val) left = false;
        }
        
        if (root->right) {
            if (!isUnivalTree(root->right) || root->val != root->right->val) right = false;
        }
        
        return left && right;
    }
};
