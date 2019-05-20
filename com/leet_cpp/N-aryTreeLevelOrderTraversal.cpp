/*

429. N-ary Tree Level Order Traversal

Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example, given a 3-ary tree:
           1
         / | \
       3   2  4
      / \
     5   6
     
We should return its level order traversal:

[
     [1],
     [3,2,4],
     [5,6]
]
 

Note:

The depth of the tree is at most 1000.
The total number of nodes is at most 5000.

Easy
*/

/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/


class Solution {
public:
    // 97%
    vector<vector<int>> levelOrder(Node* root) {
        vector<vector<int>> ans;
        if (!root) return ans;
        queue<Node*> level;
        
        level.push(root);
        while (!level.empty()) {
            int cnt = level.size();
            vector<int> levelval(cnt);
            for (int i=0; i<cnt; ++i) {
                Node* tmp = level.front();
                level.pop();
                levelval[i] = tmp->val;
                if (!tmp->children.empty()) {
                    for (auto child:tmp->children) {
                        if (child) level.push(child);
                    }
                }
            }
            ans.push_back(levelval);
        }
        return ans;
    }
};
