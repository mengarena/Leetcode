/*

545. Boundary of Binary Tree

Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. 
Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
(The values of the nodes may still be duplicates.)

Left boundary is defined as the path from root to the left-most node. 
Right boundary is defined as the path from root to the right-most node. 
If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. 
Note this definition only applies to the input binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. 
If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

Example 1

Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].
 

Example 2

Input:
    ____1_____
   /          \
  2            3
 / \          / 
4   5        6   
   / \      / \
  7   8    9  10  
       
Ouput:
[1,2,4,7,8,9,10,6,3]

Explanation:
The left boundary are node 1,2,4. (4 is the left-most node according to definition)
The leaves are node 4,7,8,9,10.
The right boundary are node 1,3,6,10. (10 is the right-most node).
So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].

Medium

Amazon, Apple
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