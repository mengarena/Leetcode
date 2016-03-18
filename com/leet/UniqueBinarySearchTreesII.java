package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
//
//For example,
//Given n = 3, your program should return all 5 unique BST's shown below.
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3
//confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ (below).

//OJ's Binary Tree Serialization:
//The serialization of a binary tree follows a level order traversal, 
//where '#' signifies a path terminator where no node exists below.
//
//Here's an example:
//   1
//  / \
// 2   3
//    /
//   4
//    \
//     5
//The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".

public class UniqueBinarySearchTreesII {

	public UniqueBinarySearchTreesII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int n = 3;
		
		List<TreeNode> lstTrees = generateTrees(7);
		
		System.out.println("#Tree = " + lstTrees.size());
	}
	
	
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> lstTrees = new ArrayList<TreeNode>();
        TreeNode root = null;
        //int nLeft, nRight, nRoot;
        //int i;
        if (n == 0) return lstTrees;
        
        if (n == 1) {
        	root = new TreeNode(n);
        	root.left = null; 
        	root.right = null;
        	lstTrees.add(root);
        	return lstTrees;
        }
               
        lstTrees = generateTrees(1, n);
 
         return lstTrees;
        
    }
	
    
    public List<TreeNode> generateTrees(int nStart, int nEnd) {
    	List<TreeNode> lstTrees = new ArrayList<TreeNode>();
    	int n = nEnd-nStart+1;
    	int nLeft, nRight;
    	TreeNode tnRoot;
    	
    	if (n < 1) {
    		return lstTrees;
    	} else if (n == 1) {
    		tnRoot = new TreeNode(nStart);
    		tnRoot.left = null;
    		tnRoot.right = null;
    		lstTrees.add(tnRoot);
    		return lstTrees;
    	} else if (n == 2) {
    		tnRoot = new TreeNode(nStart);
    		tnRoot.left = null;
    		tnRoot.right = new TreeNode(nEnd);
    		tnRoot.right.left = null; tnRoot.right.right = null;
    		lstTrees.add(tnRoot);
    		
    		tnRoot = new TreeNode(nEnd);
    		tnRoot.left = new TreeNode(nStart);
    		tnRoot.left.left = null; tnRoot.left.right = null;
    		tnRoot.right = null;
    		lstTrees.add(tnRoot);
    		
    		return lstTrees;
    	} 
    	
		nLeft = -1;
		nRight = n-1-nLeft;
		while (nRight > 0) {
			List<TreeNode> lstLeftTree = generateTrees(nStart, nStart+nLeft);
			List<TreeNode> lstRightTree = generateTrees(nStart+nLeft+2, nEnd);
			
    		tnRoot = new TreeNode(nStart+nLeft+1);
    		
    		int nLeftCnt = lstLeftTree.size();
    		int nRightCnt = lstRightTree.size();
    		
    		if (nLeftCnt == 0 && nRightCnt == 0) {
				tnRoot.left = null;
				tnRoot.right = null;
				lstTrees.add(tnRoot);        			
    		} else if (nLeftCnt == 0 && nRightCnt > 0) {
    			for (TreeNode tnRight:lstRightTree) {
    				tnRoot = new TreeNode(nStart+nLeft+1);  //Important
    				tnRoot.left = null;
    				tnRoot.right = tnRight;
    				lstTrees.add(tnRoot);
    			}        			
    		} else if (nLeftCnt > 0 && nRightCnt == 0) {
    			for (TreeNode tnLeft:lstLeftTree) {
    				tnRoot = new TreeNode(nStart+nLeft+1);  //Important
    				tnRoot.left = tnLeft;
    				tnRoot.right = null;
    				lstTrees.add(tnRoot);
    			}        			
    		} else {
        		for (TreeNode tnLeft:lstLeftTree) {
        			for (TreeNode tnRight:lstRightTree) {
        				tnRoot = new TreeNode(nStart+nLeft+1);  //Important
        				tnRoot.left = tnLeft;
        				tnRoot.right = tnRight;
        				lstTrees.add(tnRoot);
        			}
        		}        			
    		}    			
    		
    		nLeft = nLeft + 1;
    		nRight = nRight - 1;
		}
    		
    	
    	return lstTrees;
    }
	
}
