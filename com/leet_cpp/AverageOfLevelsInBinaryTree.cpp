/*

637. Average of Levels in Binary Tree

Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
   
Output: [3, 14.5, 11]

Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].

Note:
The range of node's value is in the range of 32-bit signed integer.

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

    // 90%
    vector<double> averageOfLevels(TreeNode* root) {
        vector<double> ans;        
        if (!root) return ans;

        queue<TreeNode*> qu;
        qu.push(root);
        
        while (!qu.empty()) {
            long long sum = 0;
            int count = qu.size();
            for (int i = count-1; i >= 0; --i) {
                TreeNode* node = qu.front();
                qu.pop();
                sum += node->val;
                if (node->left) qu.push(node->left);
                if (node->right) qu.push(node->right);
            }
            
            ans.push_back(sum*1.0/count);
        }
        
        return ans;
    }
};
