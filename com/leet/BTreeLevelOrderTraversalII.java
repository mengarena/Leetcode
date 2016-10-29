package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
//		(ie, from left to right, level by level from leaf to root).
//
//For example:
//Given binary tree {3,9,20,#,#,15,7},
//    3
//   / \
//  9  20
//    /  \
//   15   7
//return its bottom-up level order traversal as:
//[
//  [15,7],
//  [9,20],
//  [3]
//]
//confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

//Easy
public class BTreeLevelOrderTraversalII {

	public BTreeLevelOrderTraversalII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
	//	root.left.left = null; root.left.right = null;
	//	root.right = null;
		root.right = new TreeNode(20);
		root.left.left = null; root.left.right = null;
		root.right.left = new TreeNode(15);  root.right.left.left = null;  root.right.left.right = null;
		root.right.right = new TreeNode(7);  root.right.right.left = null;  root.right.right.right = null;
		
		List<List<Integer>> lstlstTree = new ArrayList<List<Integer>>();
		lstlstTree = levelOrderBottom(root);
		
		System.out.println("Result:");
		for (int i=0; i<lstlstTree.size(); i++) {
			List<Integer> lstLevel = lstlstTree.get(i);
			System.out.print("Level " + (i+1) + ":    ");
			for (Integer nVal:lstLevel) {
				System.out.print(nVal + ",");
			}
			System.out.println("");
		}
	}
	
	
	//Accepted:  Better
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
    	List<List<Integer>> lstlstTree = new ArrayList<List<Integer>>();
    	List<Integer> lstTmp = new ArrayList<Integer>();
    	List<TreeNode> lstLayer = new ArrayList<TreeNode>();
    	int i;
    	
    	if (root == null) return lstlstTree;

    	lstLayer.add(root);
		    	
    	while (!lstLayer.isEmpty()) {
    		int n = lstLayer.size();
    		lstTmp = new ArrayList<Integer>();
    		
    		for (i=0; i<n; i++) {
    			TreeNode tnTmp = lstLayer.remove(0);
    			lstTmp.add(tnTmp.val);
    			if (tnTmp.left != null) lstLayer.add(tnTmp.left);
    			if (tnTmp.right != null) lstLayer.add(tnTmp.right);
    		}
    		
    		lstlstTree.add(0, lstTmp);
    	}

    	return lstlstTree;
    }
	
	
	
	//Submitted version
    public List<List<Integer>> levelOrderBottomAC(TreeNode root) {
    	int i;
    	List<Integer> lstLeft;
    	List<Integer> lstRight;
    	int nLeftDepth = 0;
    	int nRightDepth = 0;
    	List<List<Integer>> lstlstTree = new ArrayList<List<Integer>>();
    	List<Integer> lstTmp = new ArrayList<Integer>();
    	
    	if (root == null) {
    		return lstlstTree;
    	}
    	
    	if (root.left == null && root.right == null) {
    		List<Integer> lstLevel = new ArrayList<Integer>();
    		lstLevel.add(root.val);
    		lstlstTree.add(lstLevel);
    		return lstlstTree;
    	}
    	
    	List<List<Integer>> lstlstTreeLeft = levelOrderBottom(root.left);
    	List<List<Integer>> lstlstTreeRight = levelOrderBottom(root.right);
    	
    	nLeftDepth = lstlstTreeLeft.size();
    	nRightDepth = lstlstTreeRight.size();
    	
    	lstTmp.add(root.val);
    	
    	//Merge the node values at same levels between left and right sub tree 
    	if (nLeftDepth >= nRightDepth) {
    		
    		for (i=0; i<nRightDepth; i++) {
    			lstLeft = lstlstTreeLeft.get(nLeftDepth-i-1);
    			lstRight = lstlstTreeRight.get(nRightDepth-i-1);
    			for (Integer nVal:lstRight) lstLeft.add(nVal);
    			lstlstTreeLeft.set(nLeftDepth-i-1, lstLeft);
    		}

    		lstlstTreeLeft.add(lstTmp);

    		return lstlstTreeLeft;
    	} else {
    		
    		for (i=0; i<nLeftDepth; i++) {
    			lstLeft = lstlstTreeLeft.get(nLeftDepth-i-1);
    			lstRight = lstlstTreeRight.get(nRightDepth-i-1);
    			for (Integer nVal:lstRight) lstLeft.add(nVal);
    			lstlstTreeRight.set(nRightDepth-i-1, lstLeft);
    		}

    		lstlstTreeRight.add(lstTmp);
  		
    		return lstlstTreeRight;
    		
    	}
    	
    }


  /*
     public List<List<Integer>> levelOrderBottom(TreeNode root) {
    	int i;
    	List<Integer> lstLeft;
    	List<Integer> lstRight;
    	int nLeftDepth = 0;
    	int nRightDepth = 0;
    	List<List<Integer>> lstlstTree = new ArrayList<List<Integer>>();
    	List<Integer> lstTmp = new ArrayList<Integer>();
    	
    	if (root == null) {
    		return lstlstTree;
    	}
    	
    	if (root.left == null && root.right == null) {
    		List<Integer> lstLevel = new ArrayList<Integer>();
    		lstLevel.add(root.val);
    		lstlstTree.add(lstLevel);
    		return lstlstTree;
    	}
    	
    	List<List<Integer>> lstlstTreeLeft = levelOrderBottom(root.left);
    	List<List<Integer>> lstlstTreeRight = levelOrderBottom(root.right);
    	
    	nLeftDepth = lstlstTreeLeft.size();
    	nRightDepth = lstlstTreeRight.size();
    	
    	lstTmp.add(root.val);
    	lstlstTree.add(lstTmp);
    	
    	//Merge the node values at same levels between left and right sub tree 
    	if (nLeftDepth >= nRightDepth) {
    		for (i=0; i<nRightDepth; i++) {
    			lstLeft = lstlstTreeLeft.get(nLeftDepth-i-1);
    			lstRight = lstlstTreeRight.get(nRightDepth-i-1);
    			for (Integer nVal:lstRight) lstLeft.add(nVal);
    			lstlstTree.add(0,lstLeft);
    		}
    		
    		for (i=nLeftDepth-nRightDepth-1; i>=0; i--) {
    			lstLeft = lstlstTreeLeft.get(i);
    			lstlstTree.add(0,lstLeft);
    		}
    	} else {
    		for (i=0; i<nLeftDepth; i++) {
    			lstLeft = lstlstTreeLeft.get(nLeftDepth-i-1);
    			lstRight = lstlstTreeRight.get(nRightDepth-i-1);
    			for (Integer nVal:lstRight) lstLeft.add(nVal);
    			lstlstTree.add(0,lstLeft);
    		}
    		
    		for (i=nRightDepth-nLeftDepth-1; i>=0; i--) {
    			lstRight = lstlstTreeRight.get(i);
    			lstlstTree.add(0,lstRight);
    		}
    		
    	}
    	
    	return lstlstTree;
    }
  
    */ 
    
  /* Works, Not very efficient  
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
    	int i;
    	List<Integer> lstLeft;
    	List<Integer> lstRight;
    	int nLeftDepth = 0;
    	int nRightDepth = 0;
    	List<List<Integer>> lstlstTree = new ArrayList<List<Integer>>();
 
    	if (root == null) {
    		return lstlstTree;
    	}
    	
    	if (root.left == null && root.right == null) {
    		List<Integer> lstLevel = new ArrayList<Integer>();
    		lstLevel.add(root.val);
    		lstlstTree.add(lstLevel);
    		return lstlstTree;
    	}
    	
    	List<List<Integer>> lstlstTreeLeft = new ArrayList<List<Integer>>();
    	List<List<Integer>> lstlstTreeRight = new ArrayList<List<Integer>>();
    	
    	lstlstTreeLeft = levelOrderBottom(root.left);
    	lstlstTreeRight = levelOrderBottom(root.right);
    	
    	if (lstlstTreeLeft != null) nLeftDepth = lstlstTreeLeft.size();
    	if (lstlstTreeRight != null) nRightDepth = lstlstTreeRight.size();
    	
    	List<Integer> lstTmp = new ArrayList<Integer>();
    	lstTmp.add(root.val);
    	lstlstTree.add(lstTmp);
    	
    	//Merge the node values at same levels between left and right sub tree 
    	if (nLeftDepth >= nRightDepth) {
    		for (i=0; i<nRightDepth; i++) {
    			lstLeft = lstlstTreeLeft.get(nLeftDepth-i-1);
    			lstRight = lstlstTreeRight.get(nRightDepth-i-1);
    			lstTmp = new ArrayList<Integer>();
    			for (Integer nVal:lstLeft) lstTmp.add(nVal);
    			for (Integer nVal:lstRight) lstTmp.add(nVal);
    			lstlstTree.add(0,lstTmp);
    		}
    		
    		for (i=nLeftDepth-nRightDepth-1; i>=0; i--) {
    			lstLeft = lstlstTreeLeft.get(i);
    			lstTmp = new ArrayList<Integer>();
    			for (Integer nVal:lstLeft) lstTmp.add(nVal);
    			lstlstTree.add(0,lstTmp);
    		}
    	} else {
    		for (i=0; i<nLeftDepth; i++) {
    			lstLeft = lstlstTreeLeft.get(nLeftDepth-i-1);
    			lstRight = lstlstTreeRight.get(nRightDepth-i-1);
    			lstTmp = new ArrayList<Integer>();
    			for (Integer nVal:lstLeft) lstTmp.add(nVal);
    			for (Integer nVal:lstRight) lstTmp.add(nVal);
    			lstlstTree.add(0,lstTmp);
    		}
    		
    		for (i=nRightDepth-nLeftDepth-1; i>=0; i--) {
    			lstRight = lstlstTreeRight.get(i);
    			lstTmp = new ArrayList<Integer>();
    			for (Integer nVal:lstRight) lstTmp.add(nVal);
    			lstlstTree.add(0,lstTmp);
    		}
    		
    	}
    	
    	return lstlstTree;
    }
 */ 
  
    
  /*
     public List<List<Integer>> levelOrderBottom(TreeNode root) {
    	int i;
    	List<Integer> lstLeft;
    	List<Integer> lstRight;
    	int nLeftDepth = 0;
    	int nRightDepth = 0;
    	List<List<Integer>> lstlstTree = new ArrayList<List<Integer>>();
 
    	if (root == null) {
    		return lstlstTree;
    	}
    	
    	if (root.left == null && root.right == null) {
    		List<Integer> lstLevel = new ArrayList<Integer>();
    		lstLevel.add(root.val);
    		lstlstTree.add(lstLevel);
    		return lstlstTree;
    	}
    	
    	List<List<Integer>> lstlstTreeLeft = new ArrayList<List<Integer>>();
    	List<List<Integer>> lstlstTreeRight = new ArrayList<List<Integer>>();
    	
    	lstlstTreeLeft = levelOrderBottom(root.left);
    	lstlstTreeRight = levelOrderBottom(root.right);
    	
    	if (lstlstTreeLeft != null) nLeftDepth = lstlstTreeLeft.size();
    	if (lstlstTreeRight != null) nRightDepth = lstlstTreeRight.size();
    	
    	List<Integer> lstTmp = new ArrayList<Integer>();
    	lstTmp.add(root.val);
    	lstlstTree.add(lstTmp);
    	
    	//Merge the node values at same levels between left and right sub tree 
    	if (nLeftDepth >= nRightDepth) {
    		for (i=0; i<nRightDepth; i++) {
    			lstLeft = lstlstTreeLeft.get(nLeftDepth-i-1);
    			lstRight = lstlstTreeRight.get(nRightDepth-i-1);
    			lstTmp = new ArrayList<Integer>();
    			for (Integer nVal:lstLeft) lstTmp.add(nVal);
    			for (Integer nVal:lstRight) lstTmp.add(nVal);
    			lstlstTree.add(0,lstTmp);
    		}
    		
    		for (i=nLeftDepth-nRightDepth-1; i>=0; i--) {
    			lstLeft = lstlstTreeLeft.get(i);
    			lstTmp = new ArrayList<Integer>();
    			for (Integer nVal:lstLeft) lstTmp.add(nVal);
    			lstlstTree.add(0,lstTmp);
    		}
    	} else {
    		for (i=0; i<nLeftDepth; i++) {
    			lstLeft = lstlstTreeLeft.get(nLeftDepth-i-1);
    			lstRight = lstlstTreeRight.get(nRightDepth-i-1);
    			lstTmp = new ArrayList<Integer>();
    			for (Integer nVal:lstLeft) lstTmp.add(nVal);
    			for (Integer nVal:lstRight) lstTmp.add(nVal);
    			lstlstTree.add(0,lstTmp);
    		}
    		
    		for (i=nRightDepth-nLeftDepth-1; i>=0; i--) {
    			lstRight = lstlstTreeRight.get(i);
    			lstTmp = new ArrayList<Integer>();
    			for (Integer nVal:lstRight) lstTmp.add(nVal);
    			lstlstTree.add(0,lstTmp);
    		}
    		
    	}
    	
    	return lstlstTree;

    }
  
   
   
   * */  
}
