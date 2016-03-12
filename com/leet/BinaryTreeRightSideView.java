package com.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Given a binary tree, imagine yourself standing on the right side of it, 
//return the values of the nodes you can see ordered from top to bottom.
//
//For example:
//Given the following binary tree,
//   1            <---
// /   \
//2     3         <---
// \     \
//  5     4       <---
//You should return [1, 3, 4].

public class BinaryTreeRightSideView {

	public BinaryTreeRightSideView() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = null; 
		root.left.right = new TreeNode(5);   root.left.right.left = null;  root.left.right.right = null;

		root.right = new TreeNode(3);
		root.right.left = null;
		root.right.right = new TreeNode(4);  root.right.right.left = null;  root.right.right.right = null;
		
		List<Integer> lstRightTopBottom = rightSideView(root);
		
		for (Integer nVal: lstRightTopBottom) System.out.print(nVal + ",");
		
	}
	
	
    public List<Integer> rightSideView(TreeNode root) {
    	List<Integer> lstRightTopBottom = new ArrayList<Integer>();
    	
    	if (root == null) return lstRightTopBottom;

    	List<List<Integer>> lstlstTreeLayer = new ArrayList<List<Integer>>();
    	
    	List<TreeNode> lstLayer = new ArrayList<TreeNode>();
    	
    	lstLayer.add(root);
    	
    	lstlstTreeLayer = getTreeLayer(lstLayer);
    	
    	for (List<Integer> lstLayerTmp:lstlstTreeLayer) {
    		lstRightTopBottom.add(lstLayerTmp.get(0));
    	}
    	
    	return lstRightTopBottom;
    }
	
    //lstLayer stores the node on one layer
    //For each node, replace the node with its left/right children; after processing each node on current layer, lstLayer will only contain
    //the nodes on next layer
    public List<List<Integer>> getTreeLayer(List<TreeNode> lstLayer) {
    	List<List<Integer>> lstlstTreeLayer = new ArrayList<List<Integer>>();
    	int n = lstLayer.size();
    	int i;
    	
    	if (n == 0) return lstlstTreeLayer;

    	List<Integer> lstTmp = new ArrayList<Integer>();

    	for (i=0; i<n; i++) {
    		TreeNode tnTmp = lstLayer.remove(0);
    		lstTmp.add(0, tnTmp.val);   //It reverse lstLayer
    		
    		if (tnTmp.left != null)  lstLayer.add(tnTmp.left);
    		if (tnTmp.right != null)  lstLayer.add(tnTmp.right);
    	}
    	
    	
    	lstlstTreeLayer = getTreeLayer(lstLayer);
    	
    	lstlstTreeLayer.add(0, lstTmp); 
    	
    	return lstlstTreeLayer;
    }
    
    
}
