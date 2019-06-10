/*

427. Construct Quad Tree

https://leetcode.com/problems/construct-quad-tree/

We want to use quad trees to store an N x N boolean grid. Each cell in the grid can only be true or false. 
The root node represents the whole grid. 
For each node, it will be subdivided into four children nodes until the values in the region it represents are all the same.

Each node has another two boolean attributes : isLeaf and val. isLeaf is true if and only if the node is a leaf node. 
The val attribute for a leaf node contains the value of the region it represents.

Your task is to use a quad tree to represent a given grid. 
The following example may help you understand the problem better:

Given the 8 x 8 grid below, we want to construct the corresponding quad tree:

It can be divided according to the definition above:

The corresponding quad tree should be as following, where each node is represented as a (isLeaf, val) pair.

For the non-leaf nodes, val can be arbitrary, so it is represented as *.

Note:
N is less than 1000 and guaranteened to be a power of 2.
If you want to know more about the quad tree, you can refer to its wiki.

Easy

Uber, Apple
*/


/*
// Definition for a QuadTree node.
class Node {
public:
    bool val;
    bool isLeaf;
    Node* topLeft;
    Node* topRight;
    Node* bottomLeft;
    Node* bottomRight;

    Node() {}

    Node(bool _val, bool _isLeaf, Node* _topLeft, Node* _topRight, Node* _bottomLeft, Node* _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/
class Solution {
public:

    // 50%
    Node* construct(vector<vector<int>>& grid) {
        int n = grid.size();
        if (n == 0) return NULL;
        
        Node* tree = constructTree(grid, 0, 0, n);
        return tree;
    }
    
    Node* constructTree(vector<vector<int>>& grid, int x, int y, int n) {
        if (n == 1) {
            Node* single = new Node(grid[x][y] == 1, true, NULL, NULL, NULL, NULL);
            return single;
        }
        
        Node* topLeft = constructTree(grid, x, y, n/2);
        Node* topRight = constructTree(grid, x, y+n/2, n/2);
        Node* bottomLeft = constructTree(grid, x+n/2, y, n/2);
        Node* bottomRight = constructTree(grid, x+n/2, y+n/2, n/2);
        
        if (topLeft->val == topRight->val && topLeft->val == bottomLeft->val && topLeft->val == bottomRight->val &&
            topLeft->isLeaf && topRight->isLeaf && bottomLeft->isLeaf && bottomRight->isLeaf) {
            Node* root = new Node(topLeft->val, true, NULL, NULL, NULL, NULL);
            return root;
        } else {
            Node* root = new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
            return root;
        }
    }
};
