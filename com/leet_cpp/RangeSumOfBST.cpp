/*

938. Range Sum of BST

Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.

 

Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32
Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23
 

Note:

The number of nodes in the tree is at most 10000.
The final answer is guaranteed to be less than 2^31.

Easy

Google
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

    // 79%
    int rangeSumBST(TreeNode* root, int L, int R) {
        if (!root || L > R) return 0;
        
        if (root->val < L) {
            return rangeSumBST(root->right, L, R);
        }
        
        if (root->val > R) {
            return rangeSumBST(root->left, L, R);
        }
        
        return root->val + rangeSumBST(root->left, L, R) + rangeSumBST(root->right, L, R);
    }


    // 10%
    int rangeSumBST(TreeNode* root, int L, int R) {
        int sum = 0;
        
        inOrder(root, L, R, sum);
        return sum;
    }
    
    void inOrder(TreeNode* root, int L, int R, int& sum) {
        if (!root) return;
        
        inOrder(root->left, L, R, sum);
        if (root->val >= L && root->val <= R) sum += root->val;
        inOrder(root->right, L, R, sum);
    }
};