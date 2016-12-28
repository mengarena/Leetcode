package com.leet;

import java.util.Stack;

//Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, 
//flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
//
//For example:
//Given a binary tree {1,2,3,4,5},
//    1
//   / \
//  2   3
// / \
//4   5
//
//return the root of the binary tree [4,5,2,#,#,3,1].
//   4
//  / \
// 5   2
//    / \
//   3   1  
//   
//confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.

public class BinaryTreeUpsideDown {

	public BinaryTreeUpsideDown() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		
		TreeNode newRoot = upsideDownBinaryTree(root);
		
		System.out.println();
	}


    //ACC:  59%
    //Turn rules:  
    //  1) original right -> left;  original left -> root; original root -> right
    //  2) The top sub tree (root, left, right) is put to the bottom at the right side
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return root;
        
        TreeNode orgRoot = root;
 //       TreeNode orgLeft = root.left;
        TreeNode orgRight = root.right;
        
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        
        TreeNode newRight = newRoot;
        while (newRight.right != null) {
            newRight = newRight.right;
        }
        
        newRight.left = orgRight;
        newRight.right = orgRoot;
        
        orgRoot.left = null;
        orgRoot.right = null;
        
        return newRoot;
    }



    //ACC
    public TreeNode upsideDownBinaryTreeK(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return root;
        
        TreeNode tmpRoot = root;
        TreeNode tmpLeft = root.left;
        TreeNode tmpRight = root.right;
        TreeNode tmpLeftLeft = null;
        
        while (tmpLeft != null) {
            tmpLeftLeft = tmpLeft.left;
            
            tmpLeft.left = tmpRight;
            tmpRight = tmpLeft.right;
            tmpLeft.right = tmpRoot;
            
            tmpRoot = tmpLeft;
            tmpLeft = tmpLeftLeft;
        }

        root.left = null;
        root.right = null;
         
        return tmpRoot;
    }


  
    //Accepted:  95%
	//root --> right child;  right child-->left child; left child --> root
	//Process along left slope
    public TreeNode upsideDownBinaryTreeA(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return root;
        
        TreeNode tmpRight = root.right;
        TreeNode tmpLeft = root.left;
        
        TreeNode tmpLeftLeft = null;
        TreeNode tmpRoot = root;
        
        while (tmpLeft != null) { 
        	tmpLeftLeft = tmpLeft.left;
        	
        	tmpLeft.left = tmpRight;
        	tmpRight = tmpLeft.right;
        	tmpLeft.right = tmpRoot;
        	
        	tmpRoot = tmpLeft;
        	
        	tmpLeft = tmpLeftLeft;
        	
        	//Important:
        	//After flip, the original root become the right most node of the new tree, need to set its left/right children to null
        	//Otherwise, a chain will exist, and cause Exceeding Memory Limit
        	if (root.left != null || root.right != null) {
        	    root.left = null;
        	    root.right = null;
        	}
        }
        
        return tmpRoot;
    }

    
    
    
    
    
    //Correct, but Memory Limit Exceed
    //Left-most become top root
    //root --> right child;  left child-->left child;  right child --> root
    private TreeNode upSideDownA(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return root;
        
        Stack<TreeNode> stkNodes = new Stack<TreeNode>();
        TreeNode tnNewRoot = null;
        TreeNode tmp = null;
        TreeNode tmpParent = null;
        TreeNode tmpRight = null;
        
        while (root != null) {       	
        	stkNodes.push(root);
        	root = root.left;
        }
        
        while (!stkNodes.isEmpty()) {
        	tmp = stkNodes.pop();
        	if (tnNewRoot == null) tnNewRoot = tmp;
        	
        	tmpRight = upSideDownA(tmp.right);
        	
        	if (tmpParent != null) {
        		tmpParent.left = tmpRight;
        		tmpParent.right = tmp;
        	}
        	
        	tmpParent = tmp;
        }
        
        return tnNewRoot;
    }
    
    
    private TreeNode swapLeftRight(TreeNode root) {
    	if (root == null || (root.left == null && root.right == null)) return root;
    	
    	TreeNode tnLeft = swapLeftRight(root.left);
    	TreeNode tnRight = swapLeftRight(root.right);
    	
    	root.left = tnRight;
    	root.right = tnLeft;
    	
    	return root;
    }
}
