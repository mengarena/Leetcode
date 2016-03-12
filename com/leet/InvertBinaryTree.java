package com.leet;

//Invert a binary tree.
//
//     4
//   /   \
//  2     7
// / \   / \
//1   3 6   9
//
//to
//
//     4
//   /   \
//  7     2
// / \   / \
//9   6 3   1
//Trivia:
//This problem was inspired by this original tweet by Max Howell:
//Google: 90% of our engineers use the software you wrote (Homebrew), 
//but you can¡¯t invert a binary tree on a whiteboard so fuck off.

public class InvertBinaryTree {

	
	// Definition for a binary tree node.
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
		
	public InvertBinaryTree() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		TreeNode root = new TreeNode(4);
		
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(1);
		root.left.left.left = null;  root.left.left.right = null;
		root.left.right = new TreeNode(3);
		root.left.right.left = null;  root.left.right.right = null;

		root.right = new TreeNode(7);
		root.right.left = new TreeNode(6);
		root.right.left.left = null;  root.right.left.right = null;
		root.right.right = new TreeNode(9);
		root.right.right.left = null;  root.right.right.right = null;
		
		root = new TreeNode(4);
		root.left = null;
		root.right = null;
	
		TreeNode newroot = invertTree(root);
		
		System.out.println("------");
	}
	
	
	
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = null;
        TreeNode right = null;
        
        if (root.left != null) right = invertTree(root.left);
        if (root.right != null) left = invertTree(root.right);
        
        root.left = left;
        root.right = right;
        return root;
    }	
	
}
