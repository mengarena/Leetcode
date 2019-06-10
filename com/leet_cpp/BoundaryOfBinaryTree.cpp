/*

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

    // 89%
    vector<int> boundaryOfBinaryTree(TreeNode* root) {
        vector<int> ans;
        if (!root) return ans;
        ans.push_back(root->val);
        if (!root->left && !root->right) return ans;
        
        // Keep going left, when no left to go, switch to right if exists
        TreeNode* tmp = root->left;
        while (tmp) {
            if (!tmp->left && !tmp->right) break;
            if (tmp->left) {
                ans.push_back(tmp->val);
                tmp = tmp->left;
            } else {
                ans.push_back(tmp->val);
                tmp = tmp->right;
            }
        }
        
        // Get all the leaves in any order (as long as left comes first)
        addLeafs(root, ans);
        
        stack<int> stk;
        
        // Same as traverse left tree, keep going right, when no right to go, switch to left if exists
        tmp = root->right;
        while (tmp) {
            if (!tmp->left && !tmp->right) break;
            if (tmp->right) {
                stk.push(tmp->val);
                tmp = tmp->right;
            } else {
                stk.push(tmp->val);
                tmp = tmp->left;
            }
        }
        
        while (!stk.empty()) {
            ans.push_back(stk.top());
            stk.pop();
        }
        
        return ans;
    }
    
    
    // Only add the leaf nodes, in InOrder
    void addLeafs(TreeNode* root, vector<int>& ans) {
        if (!root) return;
        if (!root->left && !root->right) {
            ans.push_back(root->val);
            return;
        }
        
        addLeafs(root->left, ans);
        addLeafs(root->right, ans);
    }

};
