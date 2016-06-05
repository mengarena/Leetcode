package com.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Given a binary tree, return the preorder traversal of its nodes' values.
//
//For example:
//Given binary tree {1,#,2,3},
//   1
//    \
//     2
//    /
//   3
//return [1,2,3].
//		
//Note: Recursive solution is trivial, could you do it iteratively?
		
public class BinaryTreePreorderTraversal {

	public BinaryTreePreorderTraversal() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right  = new TreeNode(3);
		root.left.left = new TreeNode(4); root.left.left.left = null; root.left.left.right = null;
		
		root.left.right = null;
		
		root.right.left = new TreeNode(5);   root.right.left.left = null;  root.right.left.right = null;
		root.right.right = new TreeNode(6);
		root.right.right.left = null;
		root.right.right.right = new TreeNode(7);   root.right.right.right.left = null;  root.right.right.right.right = null;
		
		List<Integer> lstNode = preorderTraversal(root);
		for (Integer nVal:lstNode) System.out.print(nVal + ",");
		
		
	}
	
	
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> lstNode = new ArrayList<Integer>();
        Stack<TreeNode> stkTree = new Stack<TreeNode>();
        
        if (root == null) return lstNode;
        
        stkTree.push(root);
        
        while (!stkTree.isEmpty()) {
        	TreeNode tn = stkTree.pop();
        	lstNode.add(tn.val);
        	
        	if (tn.right != null) stkTree.push(tn.right);
        	if (tn.left != null) stkTree.push(tn.left);
        }
        
        return lstNode;
    }
	
	
    //Recursive
    public List<Integer> preorderTraversalA(TreeNode root) {
        List<Integer> lstPre = new ArrayList<Integer>();
        if (root == null) return lstPre;
    
        List<Integer> lstRight = null;
        
        if (root.left != null) {
            lstPre = preorderTraversal(root.left);
        }
        
        if (root.right != null) {
            lstRight = preorderTraversal(root.right);
        }
        
        lstPre.add(0, root.val);
        
        if (lstRight != null) {
            for (int val:lstRight) lstPre.add(val);
        }
        
        return lstPre;
    }
    
}
