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



    //ACC:  95%    O(logn + n/2)  n = number of nodes   (logn is the height)
    //
    //Idea: 
    //Basically my solution contains 2 steps.
    //(1) Firstly, we need to find the height of the binary tree and count the nodes above the last level.
    //(2) Then we should find a way to count the nodes on the last level.
    //
    //Here I used a kind of binary search. We define the "midNode" of the last level as a node following 
    //the path "root->left->right->right->...->last level".
    //
    //If midNode is null, then it means we should count the nodes on the last level in the left subtree.
    //
    //If midNode is not null, then we add half of the last level nodes to our result and 
    //then count the nodes on the last level in the right subtree.
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int height = 1;
        TreeNode tmp = root;

        while (tmp.left != null) {    //O(logn)
            tmp = tmp.left;
            height++;
        }
        
        if (height == 1) return 1;
        
        return ((1 << (height-1)) - 1) + countLastLevel(root, height);
    }
    
    //This part, O(n/2), i.e. it basically the worst case, it visits the half of the tree
    private int countLastLevel(TreeNode root, int height) {
        if (height == 2) {
            if (root.right != null) return 2;
            else if (root.left != null) return 1;
            else return 0;
        }
        
        TreeNode midNode = root.left;
        int curHeight = 2;
        
        while (curHeight < height) {
            curHeight++;
            midNode = midNode.right;
        }
        
        if (midNode == null) return countLastLevel(root.left, height-1);
        
        return (1 << (height-2)) + countLastLevel(root.right, height-1);
    }



	
	//Previously accepted, NOW  TLE
    public int countNodes(TreeNode root) {
		if (root == null) return 0;
        TreeNode tnTmp = root;
        int leftCnt = 0;
        int rightCnt = 0;
        
        while (tnTmp != null) {
            leftCnt++;
            tnTmp = tnTmp.left;
        }
        
        tnTmp = root;
        while (tnTmp != null) {
            rightCnt++;
            tnTmp = tnTmp.right;
        }
        
        if (leftCnt == rightCnt) {
            return (1 << leftCnt) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
	
	
    //ACC
	public int countNodesA(TreeNode root) {
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
