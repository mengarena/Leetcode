/*

662. Maximum Width of Binary Tree

Given a binary tree, write a function to get the maximum width of the given tree. 
The width of a tree is the maximum width among all levels. 
The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes 
(the leftmost and right most non-null nodes in the level, 
where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).

Example 2:
Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).

Example 3:
Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).

Example 4:
Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).

Note: Answer will in the range of 32-bit signed integer.

Medium

Google, Uber, Amazon
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

    // 53%
    
    // In a tree, if root's index is x, then its left child has index of 2x, its right child has index of 2x+1
    int widthOfBinaryTree(TreeNode* root) {
        if (!root) return 0;
        queue<TreeNode*> level;
        queue<unsigned long> poss;
        unsigned long maxWidth = 0;
        
        poss.push(0);
        level.push(root);
        
        while (!level.empty()) {
            unsigned long minPos = ULONG_MAX;
            unsigned long maxPos = 0;
            int size = level.size();
            
            for (int i=0; i<size; ++i) {
                unsigned long pos = poss.front();
                poss.pop();
                minPos = min(minPos, pos);
                maxPos = max(maxPos, pos);
                
                TreeNode* tmp = level.front();
                level.pop();
                
                if (tmp->left) {
                    level.push(tmp->left);
                    poss.push(2*pos);
                }
                
                if (tmp->right) {
                    level.push(tmp->right);
                    poss.push(2*pos+1);
                }
            }
            
            maxWidth = max(maxWidth, maxPos-minPos+1);
        }
        
        return static_cast<int>(maxWidth);
    }
};
