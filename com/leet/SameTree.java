package com.leet;

//Given two binary trees, write a function to check if they are equal or not.
//
//Two binary trees are considered equal if they are structurally identical and the nodes have the same value.

//Bloomberg
//Easy
public class SameTree {
	// Definition for a binary tree node.
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	public SameTree() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}
	
    public boolean isSameTree(TreeNode p, TreeNode q) {        
        if (p == null && q == null) return true;
        if (p == null && q != null) return false;
        if (p != null && q == null) return false;
                
        if (p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)) {
        	return true;
        } else {
        	return false;
        }
        
    }
	
}
