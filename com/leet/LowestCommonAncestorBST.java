package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
//
//According to the definition of LCA on Wikipedia: ¡°The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).¡±
//
//      _______6______
//     /               \
//  ___2__           ___8__
// /      \         /      \
// 0      _4        7       9
//       /  \
//       3   5
//For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

public class LowestCommonAncestorBST {
	 //Definition for a binary tree node.
	 public class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	 }
	
	public LowestCommonAncestorBST() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		TreeNode root = new TreeNode(6);
		root.left = new TreeNode(2);  
		root.left.left = new TreeNode(0);
		root.left.left.left = null;  root.left.left.right = null;
		root.left.right = new TreeNode(4);
		root.left.right.left = new TreeNode(3);  root.left.right.left.left = null;  root.left.right.left.right = null;
		root.left.right.right = new TreeNode(5);  root.left.right.right.left = null;  root.left.right.right.right = null;
		
		root.right = new TreeNode(8);
		root.right.left = new TreeNode(7);   root.right.left.left = null;  root.right.left.right = null;
		root.right.right = new TreeNode(9);  root.right.right.left = null;  root.right.right.right = null;
		
	//    TreeNode trElement = new TreeNode(7);
		
	//	List<TreeNode> lstPath = findPath(root, trElement);
		
		TreeNode trP = new TreeNode(2);
		TreeNode trQ = new TreeNode(8);
		
		TreeNode ret = lowestCommonAncestor(root, trP, trQ);
		
		System.out.println("------" + ret.val);
	}


	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) return root;
        if ((root.val - p.val) * (root.val - q.val) < 0) return root;
        
        if ((root.val - p.val) + (root.val - q.val) < 0) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return lowestCommonAncestor(root.left, p, q);
        }
	}
	
	
}
