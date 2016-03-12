package com.leet;

//Given inorder and postorder traversal of a tree, construct the binary tree.
//
//Note:
//You may assume that duplicates do not exist in the tree.

public class ConstructBinaryTreeInorderPostorderTraversal {

	public ConstructBinaryTreeInorderPostorderTraversal() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] inorder = {1,2,3};
		int[] postorder = {3,2,1};
		
		TreeNode root = buildTree(inorder, postorder);
		
		System.out.println("----");
	}
	
	
	//The last element in postorder is the root, then find this root in inorder to divide the inorder into left and right sub trees around the root
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode root = null;
        
        if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0 || inorder.length != postorder.length) return null;
        int n = inorder.length;
        
        root = buildTree(inorder, 0, n-1, postorder, 0, n-1);
                
    	return root;
    }
	
    
    public TreeNode buildTree(int[] inorder, int nInStart, int nInEnd, int[] postorder, int nPostStart, int nPostEnd) {
    	TreeNode root = null;
    	
        int i;
        int nInRootIdx = -1;
    	
        if (nInStart > nInEnd || nInStart  < 0 || nInEnd < 0 || nPostStart > nPostEnd || nPostStart < 0 || nPostEnd < 0 || nInEnd - nInStart != nPostEnd - nPostStart) return null;
        
        for (i=nInStart; i<=nInEnd; i++) {
        	if (inorder[i] == postorder[nPostEnd]) {
        		nInRootIdx = i;
        		break;
        	}
        }
        
        if (nInRootIdx == -1) return null;
        
        root = new TreeNode(postorder[nPostEnd]);
        
        if (nInStart < nInRootIdx) {
        	root.left = buildTree(inorder, nInStart, nInRootIdx-1, postorder, nPostStart, nInRootIdx-1-nInStart+nPostStart);
    	} else {
    		root.left = null;
    	}
        
        if (nInRootIdx < nInEnd) {
        	root.right = buildTree(inorder, nInRootIdx+1, nInEnd, postorder, nPostEnd-nInEnd + nInRootIdx, nPostEnd-1);
        } else {
        	root.right = null;
        }
        
        return root;
    }
	
}
