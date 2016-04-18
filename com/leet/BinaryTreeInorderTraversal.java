package com.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Given a binary tree, return the inorder traversal of its nodes' values.
//
//For example:
//Given binary tree {1,#,2,3},
//   1
//    \
//     2
//    /
//   3
//return [1,3,2].
//
//Note: Recursive solution is trivial, could you do it iteratively?
//
//confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
//		

//Microsoft
public class BinaryTreeInorderTraversal {

	public BinaryTreeInorderTraversal() {
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
		
		List<Integer> lstNode = inorderTraversal(root);
		for (Integer nVal:lstNode) System.out.print(nVal + ",");
	}
	
	
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> lstNode = new ArrayList<Integer>();
        Stack<TreeNode> stkTree = new Stack<TreeNode>();
        TreeNode tmp = null;
        
        if (root == null) return lstNode;
       
        while (root != null) {
        	stkTree.push(root);
        	root = root.left;
        }

        while (!stkTree.isEmpty()) {
        	tmp = stkTree.pop();
        	lstNode.add(tmp.val);
        	
        	if (tmp.right != null) {
        		tmp = tmp.right;
        		while (tmp != null) {
        			stkTree.push(tmp);
        			tmp = tmp.left;
        		}
        		
        	}
        }
              
        return lstNode;
    }
	
	
	
    public List<Integer> inorderTraversalA(TreeNode root) {
        List<Integer> lstNode = new ArrayList<Integer>();
        Stack<TreeNode> stkTree = new Stack<TreeNode>();
        
        if (root == null) return lstNode;
        
        TreeNode tn = root;
        
        while (tn != null || !stkTree.isEmpty()) {
        	if (tn != null) {
        		stkTree.push(tn);
        		tn = tn.left;
        	} else {
        		tn = stkTree.pop();
        		lstNode.add(tn.val);
        		tn = tn.right;
        	}
        }
              
        return lstNode;
    }
	
}
