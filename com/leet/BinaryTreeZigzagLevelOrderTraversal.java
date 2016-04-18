package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a binary tree, return the zigzag level order traversal of its nodes' values. 
//(ie, from left to right, then right to left for the next level and alternate between).
//
//For example:
//Given binary tree {3,9,20,#,#,15,7},
//    3
//   / \
//  9  20
//    /  \
//   15   7
//return its zigzag level order traversal as:
//[
//  [3],
//  [20,9],
//  [15,7]
//]
//confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


//Linkedin, Microsoft, Bloomberg
public class BinaryTreeZigzagLevelOrderTraversal {

	public BinaryTreeZigzagLevelOrderTraversal() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right  = new TreeNode(20);
		root.left.left = null; //new TreeNode(4); root.left.left.left = null; root.left.left.right = null;
		
		root.left.right = null;
		
		root.right.left = new TreeNode(15);   root.right.left.left = null;  root.right.left.right = null;
		root.right.right = new TreeNode(7);
		root.right.right.left = null;
		root.right.right.right = null; //new TreeNode(7);   root.right.right.right.left = null;  root.right.right.right.right = null;
		
		List<List<Integer>> lstlstZigZag = zigzagLevelOrder(root);
		
		for (List<Integer> lstZigZag:lstlstZigZag) {
			System.out.print("[");
			for (Integer nVal:lstZigZag) System.out.print(nVal + ",");
			System.out.println("]");
		}
		
	}
	
	
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    	List<List<Integer>> lstlstZigZag = new ArrayList<List<Integer>>();
    	int nLevel = 1;
    	List<TreeNode> lstTnLevel = new ArrayList<TreeNode>();
    	List<Integer> lstLevel = new ArrayList<Integer>();
    	int i,n;
    	
    	if (root == null) return lstlstZigZag;
    	
    	lstTnLevel.add(root);
    	
    	//Level traversal
    	while (lstTnLevel.size() != 0) {
    		nLevel++;
    		n = lstTnLevel.size();
    		
    		lstLevel = new ArrayList<Integer>();
    		for (i=0; i<n; i++) {
    			TreeNode tnTmp= lstTnLevel.remove(0);
    			if (tnTmp.left != null) lstTnLevel.add(tnTmp.left);
    			if (tnTmp.right != null) lstTnLevel.add(tnTmp.right);
    			
    			if (nLevel % 2 == 1) {
    				lstLevel.add(0, tnTmp.val);
    			} else {
    				lstLevel.add(tnTmp.val);
    			}
    		}
    		
    		if (lstLevel.size() > 0) lstlstZigZag.add(lstLevel);
    	}
    	
    	
    	return lstlstZigZag;
    }
	
}
