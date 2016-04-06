package com.leet;

//Given preorder and inorder traversal of a tree, construct the binary tree.
//
//Note:
//You may assume that duplicates do not exist in the tree.


//Bloomberg
public class ConstructBinaryTreePreorderInorderTraversal {

	public ConstructBinaryTreePreorderInorderTraversal() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] preorder = {1,2,3};
		int[] inorder = {2,3,1};
		
		TreeNode root = buildTree(preorder, inorder);
		
		System.out.println("----");
	}
	
	//The first element in preorder is the root; find this root in Inorder and partition into left/right sub trees
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = null;
        
        if (inorder == null || preorder == null || inorder.length == 0 || preorder.length == 0 || inorder.length != preorder.length) return null;
        int n = inorder.length;
        
        root = buildTree(inorder, 0, n-1, preorder, 1, n-1);
                
        return root;
        
    }
    
    public TreeNode buildTree(int[] inorder, int nInStart, int nInEnd, int[] preorder, int nPreStart, int nPreEnd) {
    	TreeNode root = null;
    	
    	if (nInStart > nInEnd || nInStart < 0 || nInEnd < 0 || nPreStart > nPreEnd || nPreStart < 0 || nPreEnd < 0 || nInEnd - nInStart != nPreEnd - nPreStart) return null;
    	
    	int i, nInRootIdx = -1;
    	
    	for (i=nInStart; i<=nInEnd; i++) {
    		if (inorder[i] == preorder[nPreStart]) {
    			nInRootIdx = i;
    			break;
    		}
    	}
    	
    	if (nInRootIdx == -1) return null;
    	
    	root = new TreeNode(preorder[nPreStart]);
    	
    	if (nInStart < nInRootIdx) {
    		root.left = buildTree(inorder, nInStart, nInRootIdx-1, preorder, nPreStart+1, nInRootIdx-nInStart+nPreStart);
    	} else {
    		root.left = null;
    	}
    	
    	if (nInRootIdx < nInEnd) {
    		root.right = buildTree(inorder, nInRootIdx+1, nInEnd, preorder,nPreEnd-nInEnd+nInRootIdx+1, nPreEnd);
    	} else {
    		root.right = null;
    	}
    	
    	return root;
    }
	
}
