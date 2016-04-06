package com.leet;

import java.util.Stack;

//Given a binary tree, flatten it to a linked list in-place.
//
//For example,
//Given
//
//         1
//        / \
//       2   5
//      / \   \
//     3   4   6
//The flattened tree should look like:
//   1
//    \
//     2
//      \
//       3
//        \
//         4
//          \
//           5
//            \
//             6

//Microsoft
public class FlattenBinaryTreeLinkedList {

	public FlattenBinaryTreeLinkedList() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right  = new TreeNode(5);
		root.left.left = new TreeNode(3); root.left.left.left = null; root.left.left.right = null;
		root.left.right = new TreeNode(4); root.left.right.left = null; root.left.right.right = null;
		root.left.right.left = new TreeNode(8);  root.left.right.left.left = null; root.left.right.left.right = null; 
		root.left.right.right = new TreeNode(9);  root.left.right.right.left = null; root.left.right.right.right = null; 
		
		root.right.left = null; 
		root.right.right = new TreeNode(6);
		root.right.right.left = null;
		root.right.right.right = null;
		
		flatten(root);
		
		while (root != null) {
			System.out.print(root.val + ",");
			root = root.right;
		}
	}
	

    public void flatten(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return;
        TreeNode tnPoint = root;
        Stack<TreeNode> stkTN = new Stack<TreeNode>();
     
        while (tnPoint != null || !stkTN.isEmpty()) {
        	if (tnPoint.right != null) stkTN.push(tnPoint.right);
        	
        	if (tnPoint.left != null) {
        		tnPoint.right = tnPoint.left;
        		tnPoint.left = null;
        	} else if (!stkTN.isEmpty()) {
        		TreeNode tnPointTmp = stkTN.pop();
        		tnPoint.right = tnPointTmp;
        	}
        	
        	tnPoint = tnPoint.right;
        }
    }
    
	/* Works, but not efficient enough
    public void flatten(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return;
        TreeNode tnNode = root;
        TreeNode tnPoint = null;
        Stack<TreeNode> stkTN = new Stack<TreeNode>();
        
        stkTN.push(root);
        
        while (!stkTN.isEmpty()) {
        	tnNode = stkTN.pop();
        	
        	if (tnPoint == null) {
        		tnPoint = tnNode;
        	} else {
        		tnPoint.right = tnNode;
        		tnPoint = tnPoint.right;
        	}
        	
        	if (tnNode.right != null) stkTN.push(tnNode.right);
        	if (tnNode.left != null) stkTN.push(tnNode.left);
        }
          
    }
    
    */
	
}
