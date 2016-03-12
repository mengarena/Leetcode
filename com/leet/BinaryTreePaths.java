package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a binary tree, return all root-to-leaf paths.
//
//For example, given the following binary tree:
//
//   1
// /   \
//2     3
// \
//  5
//All root-to-leaf paths are:
//
//["1->2->5", "1->3"]

public class BinaryTreePaths {

	public BinaryTreePaths() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		TreeNode root = new TreeNode(3);  
		root.left = null; //new TreeNode(9);
		//root.left.left = null; root.left.right = null;
		//root.right = null;
		root.right = new TreeNode(20);
		root.right.left = null; root.right.right = null;
//		root.right.left = new TreeNode(15);  root.right.left.left = null;  root.right.left.right = null;
//		root.right.right = new TreeNode(7);  root.right.right.left = null;  root.right.right.right = null;
		
		List<String> lstPaths = binaryTreePaths(root);
		for (String sPath:lstPaths) System.out.println(sPath);
	}
	

	public List<String> binaryTreePaths(TreeNode root) {
        List<String> lstPaths = new ArrayList<String>();
        
        if (root == null) return lstPaths;
        if (root.left == null && root.right == null) {
        	lstPaths.add(root.val + "");
        	return lstPaths;
        }
        
        List<String> lstLeft = new ArrayList<String>();
        if (root.left != null) {
        	lstLeft = binaryTreePaths(root.left);
        	for (String sLeft:lstLeft) lstPaths.add(root.val + "->" + sLeft);
        } 
        
        List<String> lstRight = new ArrayList<String>();
        if (root.right != null) {
        	lstRight = binaryTreePaths(root.right);
        	for (String sRight:lstRight) lstPaths.add(root.val + "->" + sRight);
        }
                
        return lstPaths;
    }
	
}
