/*

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
