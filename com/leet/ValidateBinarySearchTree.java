package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a binary tree, determine if it is a valid binary search tree (BST).
//
//Assume a BST is defined as follows:
//
//The left subtree of a node contains only nodes with keys less than the node's key.
//The right subtree of a node contains only nodes with keys greater than the node's key.
//Both the left and right subtrees must also be binary search trees.
//confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

//Microsoft, Amazon, Bloomberg
public class ValidateBinarySearchTree {

	public ValidateBinarySearchTree() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(1);
		root.right  = new TreeNode(5);
		root.left.left = new TreeNode(0); root.left.left.left = null; root.left.left.right = null;
		
		root.left.right = new TreeNode(2); 
		root.left.right.left = null; 
		root.left.right.right = new TreeNode(3);  root.left.right.right.left = null;  root.left.right.right.right = null;
		
		
		root.right.left = new TreeNode(4);   root.right.left.left = null;  root.right.left.right = null;
		root.right.right = new TreeNode(6);
		root.right.right.left = null;
		root.right.right.right = null; //new TreeNode(7);   root.right.right.right.left = null;  root.right.right.right.right = null;
		
		System.out.println(isValidBST(root));
	}
	
	
	//ACC: 33%
	//Strategy:  left sub tree should be BST;  right sub tree should be BST;  the right most node of left sub tree should be < root.val; the left most node of the right sub tree should be > root.val
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        TreeNode leftRight = root.left;
        
        while (leftRight != null) {
            if (leftRight.right == null) break;
            leftRight = leftRight.right;
        }
        
        TreeNode rightLeft = root.right;
        
        while (rightLeft != null) {
            if (rightLeft.left == null) break;
            rightLeft = rightLeft.left;
        }
        
        if ((leftRight == null || leftRight.val < root.val) && (rightLeft == null || rightLeft.val > root.val) && isValidBST(root.left) && isValidBST(root.right)) return true;
        
        return false;
    }	
	
	
	
    //ACC
    public boolean isValidBSTA(TreeNode root) {
        List<Integer> lstTree = new ArrayList<Integer>();
        
        return InOrderTravse(root, lstTree);
    }
    
    
    public boolean InOrderTravse(TreeNode root, List<Integer> lstNode) {
    	if (root == null) return true;
    	
    	if (root.left != null) {
    		if (InOrderTravse(root.left, lstNode) == false) return false;
    	}
    	
    	if (lstNode.isEmpty()) {
    		lstNode.add(root.val);
    	} else {
    		if (lstNode.get(lstNode.size()-1) >= root.val) return false;
    		lstNode.add(root.val);	
    	}
    	
    	
    	if (root.right != null) {
    		if (InOrderTravse(root.right, lstNode) == false) return false;
    	}
    	
    	return true;
    }
	
	
/* Accepted. Not very efficient	
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        List<Integer> lstTree = new ArrayList<Integer>();
        InOrderTravse(root, lstTree);
        
        for (int i=1; i<lstTree.size(); i++) {
        	if (lstTree.get(i) <= lstTree.get(i-1)) return false;
        }
     
        return true;
    }
    
    
    public void InOrderTravse(TreeNode root, List<Integer> lstNode) {
    	if (root == null) return;
    	
    	if (root.left != null) {
    		InOrderTravse(root.left, lstNode);
    	}
    	
    	lstNode.add(root.val);
    	
    	if (root.right != null) {
    		InOrderTravse(root.right, lstNode);
    	}
    	
    }
*/    
    
    
}
