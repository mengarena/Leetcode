package com.leet;

import java.util.ArrayList;
import java.util.List;


//A full binary tree (sometimes proper binary tree or 2-tree) is a tree in which every node other than the leaves has two children. 
//A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.

//Given a complete binary tree, count the number of nodes.
//
//Definition of a complete binary tree from Wikipedia:
//In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. 
//It can have between 1 and 2h nodes inclusive at the last level h.


public class CountCompleteTreeNodes {

	public CountCompleteTreeNodes() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		TreeNode root = new TreeNode(1);
		root.left = null; //new TreeNode(2);
		root.right  = null; //new TreeNode(3);
	//	root.left.left = null; //new TreeNode(4); root.left.left.left = null; root.left.left.right = null;
		
	//	root.left.right = null; //new TreeNode(41); root.left.right.left = null; root.left.right.right = null;;
		
		//root.right.left = null; //new TreeNode(5);   root.right.left.left = null;  root.right.left.right = null;
		//root.right.right =null; // new TreeNode(6);  root.right.right.left = null;  root.right.right.right = null;
		
//		root.right.right.right = new TreeNode(7);   root.right.right.right.left = null;  root.right.right.right.right = null;

		System.out.println("#Node = " + countNodes(root));
	}

	
	public int countNodes(TreeNode root) {
		if (root == null) return 0;
		int nLeftLevel = 0;
		int nRightLevel = 0;
		TreeNode left = root;
		TreeNode right = root;
		
		while (left != null) {
			nLeftLevel++;
			left = left.left;
		}
		
		while (right != null) {
			nRightLevel++;
			right = right.right;
		}
		
	    if (nLeftLevel == nRightLevel) return (2 << (nLeftLevel-1)) - 1;    
//	    if (nLeftLevel == nRightLevel) return (int)Math.pow(2, nLeftLevel) - 1;  //This code is correct! But use this, will cause exceeding time limit!
	    
	    return 1 + countNodes(root.left) + countNodes(root.right);
	
	}
	
	
/*	Works, exceed time limit
	public int countNodes(TreeNode root) {
		List<TreeNode> lstNodes = new ArrayList<TreeNode>();
		if (root == null) return 0;
		
		SearchNode(root, lstNodes);
		
		return lstNodes.size();
	}
	
	
	private void SearchNode(TreeNode root, List<TreeNode> lstNodes) {
		if (root != null) lstNodes.add(root);
		
		if (root.left != null) SearchNode(root.left, lstNodes);
		if (root.right != null) SearchNode(root.right, lstNodes);		
	}
*/
	
	
/*	Works, exceed time limit
    public int countNodes(TreeNode root) {
    	if (root == null) return 0;
        int nTotalLevel = 0;
        TreeNode tnTmp = root;
        int i;
        int nTotalNodeCnt = 0;
        
        while (tnTmp != null) {
        	nTotalLevel++;
        	tnTmp = tnTmp.left;
        }
        
        if (nTotalLevel == 1) return 1;
        
        List<TreeNode> lst2ndLastLevel = new ArrayList<TreeNode>();
        
        getLevelNode(root, nTotalLevel-1, lst2ndLastLevel);
        
        for (i=0; i<lst2ndLastLevel.size(); i++) {
        	if (lst2ndLastLevel.get(i).left != null) nTotalNodeCnt++;
        	if (lst2ndLastLevel.get(i).right != null) nTotalNodeCnt++;        	
        }
        
        for (i=1; i<=nTotalLevel-1; i++) {
        	nTotalNodeCnt += Math.pow(2, i-1);
        }
        
        return nTotalNodeCnt;
        
    }
    
    
    private void getLevelNode(TreeNode root, int nLevelCnt, List<TreeNode> lst2ndLastLevel) {
    	if (nLevelCnt == 1) {
    		lst2ndLastLevel.add(root);
    		return;
    	}
    	
    	if (root.left != null)  getLevelNode(root.left, nLevelCnt-1, lst2ndLastLevel);
    	if (root.right != null)  getLevelNode(root.right, nLevelCnt-1, lst2ndLastLevel);    	
    }
*/    
	
}
