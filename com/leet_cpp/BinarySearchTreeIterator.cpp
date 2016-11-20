//173. Binary Search Tree Iterator
//
//Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
//
//Calling next() will return the next smallest number in the BST.
//
//Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
//
//LinkedIn, Google, Facebook, Microsoft

/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class BSTIterator {
private:
    stack<TreeNode*> stkTree;
    
public:
    BSTIterator(TreeNode *root) {
        if (root == NULL) return;
        
        setStack(root);
    }

    /** @return whether we have a next smallest number */
    bool hasNext() {
        return !stkTree.empty();
    }

    /** @return the next smallest number */
    int next() {
        TreeNode* tmp = stkTree.top();
        
        stkTree.pop();
        
        setStack(tmp->right);
        
        return tmp->val;
    }
    
    void setStack(TreeNode *root) {
        if (root == NULL) return;
        
        while (root != NULL) {
            stkTree.push(root);
            root = root->left;
        }
    }
};

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = BSTIterator(root);
 * while (i.hasNext()) cout << i.next();
 */
 