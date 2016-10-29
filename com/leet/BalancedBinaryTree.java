package com.leet;

//Given a binary tree, determine if it is height-balanced.
//
//For this problem, a height-balanced binary tree is defined as a binary tree in which 
//the depth of the two subtrees of every node never differ by more than 1.


//Bloomberg
//Easy
public class BalancedBinaryTree {
	//Definition for a binary tree node.
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	public BalancedBinaryTree() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}
	
	//AC
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        
        if (!isBalanced(root.left)) return false;

        if (!isBalanced(root.right)) return false;
        
        int nLeftHeight = getHeight(root.left);
        int nRightHeight = getHeight(root.right);
        if (Math.abs(nLeftHeight-nRightHeight) <= 1) return true;
        
        return false;
    }
    
    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        
        int nLeftHeight = getHeight(root.left);
        int nRightHeight = getHeight(root.right);
        
        return Math.max(nLeftHeight, nRightHeight) + 1;
    }

	
/*	AC
    public boolean isBalanced(TreeNode root) {
        int nLeftDepth = 0;
        int nRightDepth = 0;
        
        if (root == null) return true;
        
        boolean bIsLeftBalanced = isBalanced(root.left);
        if (bIsLeftBalanced == false) return false;
        
        boolean bIsRightBalanced = isBalanced(root.right);
        if (bIsRightBalanced == false) return false;
        
        nLeftDepth = getDepth(root.left);
        nRightDepth = getDepth(root.right);
        
        if (Math.abs(nLeftDepth - nRightDepth) > 1) return false;
                
        return true;
    }
    
    public int getDepth(TreeNode root) {
    	int nDepthLeft;
    	int nDepthRight;
    	int nDepth;
    	
    	if (root != null) {
    		nDepthLeft = 1 + getDepth(root.left);
    		nDepthRight = 1 + getDepth(root.right);
    	} else {
    		nDepthLeft = 0;
    		nDepthRight = 0;
    	}
    	
    	nDepth = Math.max(nDepthLeft, nDepthRight);
    	
    	return nDepth;
    }
*/		
}
