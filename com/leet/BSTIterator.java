package com.leet;

//Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
//
//Calling next() will return the next smallest number in the BST.
//
//Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
//
//Binary Search Tree:  left < root < right
//

import java.util.Stack;

public class BSTIterator {

	private Stack<Integer> stkTree = new Stack<Integer>();
	
    public BSTIterator(TreeNode root) {
        if (root == null) return;
    	
        setStack(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stkTree.isEmpty(); 
    }

    /** @return the next smallest number */
    public int next() {
        return stkTree.pop();
    }
    
    private void setStack(TreeNode root) {
    	if (root.right != null) setStack(root.right);
    	
    	stkTree.push(root.val);
    	
    	if (root.left != null) setStack(root.left);
    }
    
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
