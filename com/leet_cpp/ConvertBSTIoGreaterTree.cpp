/*

538. Convert BST to Greater Tree

Given a Binary Search Tree (BST), convert it to a Greater Tree 
such that every key of the original BST is changed to the original key plus sum of all keys greater than 
the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13

Easy

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

    // 97%
    TreeNode* convertBST(TreeNode* root) {
        stack<TreeNode*> stk;
        
        inOrder(root, stk);
        
        int sum = 0;
        
        while (!stk.empty()) {
            TreeNode* node = stk.top();
            stk.pop();
            sum += node->val;
            node->val = sum;
        }
        
        return root;
    }
    
    void inOrder(TreeNode* root, stack<TreeNode*>& stk) {
        if (!root) return;
        
        inOrder(root->left, stk);
        stk.push(root);
        inOrder(root->right, stk);
    }
};
