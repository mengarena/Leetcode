package com.leet;

//Binary Search Tree Iterator

//Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
//
//Calling next() will return the next smallest number in the BST.
//
//Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
//
//Binary Search Tree:  left < root < right
//

import java.util.Stack;


//Google, Facebook, Linkedin, Microsoft
public class BSTIterator {

	//The reuirement is use O(h) memory (h is the height), so not all nodes are pushed into stack at once
	private Stack<TreeNode> stkTree = new Stack<TreeNode>();
	
    public BSTIterator(TreeNode root) {
        if (root == null) return;
    	
        setStack(root);   //Put the numbers in stack, smallest value on top
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stkTree.isEmpty(); 
    }

    /** @return the next smallest number */
    public int next() {
       TreeNode tmp = stkTree.pop();
       setStack(tmp.right);
       return tmp.val;
    }
    
    //Each time only put at most #h node into the stack
    private void setStack(TreeNode root) {
    	while (root != null) {
    		stkTree.push(root);
    		root = root.left;
    	}
    	
    }
    
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

/*
     public BSTIterator(TreeNode root) {
        if (root == null) return;
    	
        setStack(root);   //Put the numbers in stack, smallest value on top
    }

    public boolean hasNext() {
        return !stkTree.isEmpty(); 
    }


    public int next() {
        return stkTree.pop();
    }
    
    private void setStack(TreeNode root) {
    	if (root.right != null) setStack(root.right);
    	
    	stkTree.push(root.val);
    	
    	if (root.left != null) setStack(root.left);
    }
 * */
