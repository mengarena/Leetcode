package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a binary tree, return the level order traversal of its nodes' values. (i.e, from left to right, level by level).
//
//For example:
//Given binary tree {3,9,20,#,#,15,7},
//    3
//   / \
//  9  20
//    /  \
//   15   7
//return its level order traversal as:
//[
//  [3],
//  [9,20],
//  [15,7]
//]
//confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


//Facebook, Uber, Linkedin, Microsoft, Amazon, Bloomberg
public class BTreeLevelOrderTraversal {

	public BTreeLevelOrderTraversal() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}
	
	//Accepted: Better
    public List<List<Integer>> levelOrder(TreeNode root) {
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
    		
    		lstlstTree.add(lstTmp);
    	}

    	return lstlstTree;
    }
    
    
    //Accepted
    public List<List<Integer>> levelOrderAC(TreeNode root) {
    	List<List<Integer>> lstlstTree = new ArrayList<List<Integer>>();
    	List<Integer> lstTmp = new ArrayList<Integer>();
    	int i;
    	
    	if (root == null) return lstlstTree;

		lstTmp.add(root.val);
		
    	if (root.left == null && root.right == null) {
    		lstlstTree.add(lstTmp);
    		return lstlstTree;
    	}
    	
    	List<List<Integer>> lstlstTreeLeft = levelOrder(root.left);
    	List<List<Integer>> lstlstTreeRight = levelOrder(root.right);
    	
    	int nLeftDepth = lstlstTreeLeft.size();
    	int nRightDepth = lstlstTreeRight.size();

    	if (nLeftDepth >= nRightDepth) {
	    	for (i=0; i<nRightDepth; i++) {
	    		List<Integer> lstLeft = lstlstTreeLeft.get(i);
	    		List<Integer> lstRight = lstlstTreeRight.get(i);
	    		for (Integer nVal:lstRight) lstLeft.add(nVal);
	    		lstlstTreeLeft.set(i, lstLeft);
	    	}
    		lstlstTreeLeft.add(0, lstTmp);
    		return lstlstTreeLeft;
    		
    	} else {
	    	for (i=0; i<nLeftDepth; i++) {
	    		List<Integer> lstLeft = lstlstTreeLeft.get(i);
	    		List<Integer> lstRight = lstlstTreeRight.get(i);
	    		for (int j=0; j<lstLeft.size(); j++) lstRight.add(j, lstLeft.get(j));
	    		lstlstTreeRight.set(i, lstRight);
	    	}
	    	
	    	lstlstTreeRight.add(0, lstTmp);
    		return lstlstTreeRight;
    		
    	}
    	
     }

    
 /* Submitted   
    public List<List<Integer>> levelOrder(TreeNode root) {
    	List<List<Integer>> lstlstTree = new ArrayList<List<Integer>>();
    	List<Integer> lstTmp = new ArrayList<Integer>();
    	int i;
    	
    	if (root == null) return lstlstTree;

		lstTmp.add(root.val);
		
    	if (root.left == null && root.right == null) {
    		lstlstTree.add(lstTmp);
    		return lstlstTree;
    	}
    	
    	List<List<Integer>> lstlstTreeLeft = levelOrder(root.left);
    	List<List<Integer>> lstlstTreeRight = levelOrder(root.right);
    	
    	int nLeftDepth = lstlstTreeLeft.size();
    	int nRightDepth = lstlstTreeRight.size();
    	int nMinDepth = Math.min(nLeftDepth, nRightDepth);
    	
    	for (i=0; i<nMinDepth; i++) {
    		List<Integer> lstLeft = lstlstTreeLeft.get(i);
    		List<Integer> lstRight = lstlstTreeRight.get(i);
    		for (Integer nVal:lstRight) lstLeft.add(nVal);
    		lstlstTreeLeft.set(i, lstLeft);
    	}
    	
    	lstlstTreeLeft.add(0, lstTmp);
    	
    	if (nLeftDepth < nRightDepth) {
    		for (i=nMinDepth; i<nRightDepth; i++) lstlstTreeLeft.add(lstlstTreeRight.get(i));
    	}
    	
    	return lstlstTreeLeft;
    }
*/

}
