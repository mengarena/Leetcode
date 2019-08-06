/*
Given the root of a tree, you are asked to find the most frequent subtree sum. 
The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node 
(including the node itself). 
So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.

Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
*/

//Medium
//Amazon

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
private:    
    
    int postorder(TreeNode* root, unordered_map<int, int>& mymap, int& maxCount) {
        if (!root) return 0;
        
        int sum = root->val + postorder(root->left, mymap, maxCount) + postorder(root->right, mymap, maxCount);
        
        mymap[sum]++;
        
        maxCount = max(maxCount, mymap[sum]);
        
        return sum;
    }

public:
    vector<int> findFrequentTreeSum(TreeNode* root) {
        int maxCount = 0;
        unordered_map<int, int> mymap;   //sum, frequency
        
        vector<int> ans;
        postorder(root, mymap, maxCount);
        
        for (auto it : mymap) {
            if (maxCount == it.second) {
                ans.push_back(it.first);
            }
        }
        
        return ans;
    }
     
};

