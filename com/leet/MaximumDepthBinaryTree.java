package com.leet;

//Given a binary tree, find its maximum depth.
//
//The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

public class MaximumDepthBinaryTree {

	public class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}

	public MaximumDepthBinaryTree() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		TreeNode tr = new TreeNode(1);
		
		tr.left = new TreeNode(2);
		tr.right = new TreeNode(3);
		
		tr.left.left = new TreeNode(4);
		tr.left.right = new TreeNode(5);
		tr.left.right.left = null;
		tr.left.right.right = null;
		
		tr.left.left.left = new TreeNode(8);
		tr.left.left.left.left = null;
		tr.left.left.left.right = null;
		
		tr.right.left = new TreeNode(6);
		tr.right.left.left = null;
		tr.right.left.right = null;
		
		tr.right.right = new TreeNode(7);
		tr.right.right.left = null;
		tr.right.right.right = null;
		
		System.out.println("Max Depth = " + maxDepth(tr));
	}

	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */
	
	
	
	
    public int maxDepth(TreeNode root) {
        int nDepth = 0;
        int nLeftDepth = 0;
        int nRightDepth = 0;
        
        if (root == null) return 0;
        
        if (root.left != null) nLeftDepth = maxDepth(root.left);
        if (root.right != null) nRightDepth = maxDepth(root.right);
        
        nDepth = 1 + Math.max(nLeftDepth, nRightDepth);
        
        return nDepth;
    }	
	
}
