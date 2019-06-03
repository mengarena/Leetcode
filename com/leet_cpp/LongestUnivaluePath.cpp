/*

687. Longest Univalue Path

Given a binary tree, find the length of the longest path where each node in the path has the same value. 
This path may or may not pass through the root.

The length of path between two nodes is represented by the number of edges between them.

Example 1:

Input:
              5
             / \
            4   5
           / \   \
          1   1   5
Output: 2

Example 2:

Input:
              1
             / \
            4   5
           / \   \
          4   4   5
Output: 2

Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.

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
    
    // 95%
    // Attention: Path could include both left and right, not necessary one leg
    // Final result requires length, not number of nodes
    int longestUnivaluePath(TreeNode* root) {
        if (!root) return 0;
        int maxLen = 0;
        
        postOrder(root, maxLen);
        return maxLen-1;
    }
    
    int postOrder(TreeNode* root, int& maxLen) {
        if (!root) return 0;
        
        int leftLen = postOrder(root->left, maxLen);
        int rightLen = postOrder(root->right, maxLen);
        int myLen = 1;
        int finalLeftLen = 0;
        int finalRightLen = 0;
        
        if (root->left && root->val == root->left->val) {
            finalLeftLen = leftLen;
        }
        
        if (root->right && root->val == root->right->val) {
            finalRightLen = rightLen;
        }
        
        maxLen = max(maxLen, 1 + finalLeftLen + finalRightLen);

        return myLen + max(finalLeftLen, finalRightLen);
    }
};
