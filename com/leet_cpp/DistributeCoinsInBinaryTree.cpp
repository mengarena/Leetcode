/*

979. Distribute Coins in Binary Tree

Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another.
(The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.

Example 1:
Input: [3,0,0]
Output: 2
Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.

Example 2:
Input: [0,3,0]
Output: 3
Explanation: From the left child of the root, we move two coins to the root [taking two moves].
Then, we move one coin from the root of the tree to the right child.

Example 3:
Input: [1,0,2]
Output: 2

Example 4:
Input: [1,0,0,null,3]
Output: 4
 
Note:
1<= N <= 100
0 <= node.val <= N

Medium

Google, Amazon, Microsoft
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

    // 98%
    // Accumulate the needed moves to the root node
    // (#move could be positive or negative:  positive means the root has more to give out, negative means it need from others)
    // whatever the direction of move is on its left and right children, the total move is their sum
    int distributeCoins(TreeNode* root) {
        if (!root || (!root->left && !root->right)) return 0;
        int sum = 0;

        if (root->left) {
            sum += distributeCoins(root->left);  // moves within the left sub tree
            if (root->left->val != 1) {
                sum += abs(root->left->val - 1);
                root->val += root->left->val-1;
            }
        }
        
        if (root->right) {
            sum += distributeCoins(root->right);
            if (root->right->val != 1) {
                sum += abs(root->right->val - 1);
                root->val += root->right->val-1;
            }            
        }
        
        return sum;
    }
};
