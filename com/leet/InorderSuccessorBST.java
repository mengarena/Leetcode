package com.leet;

import java.util.Stack;

//Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
//
//Note: If the given node has no in-order successor in the tree, return null.
//		
		
//Microsoft		
public class InorderSuccessorBST {

	public InorderSuccessorBST() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(6);
		
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(7);
		
		TreeNode p = root.left.right;
		
		TreeNode tn = inorderSuccessor(root, p);
		
		if (tn == null) {
			System.out.println("null");
		} else {
		    System.out.println(tn.val);
		}
	}

	
	//AC: 40%
	//Utilize the property of BST and utilize that P is a valid node (not a value)
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return null;

        TreeNode tmp = null;
        
        if (p.right != null) {
            tmp = p.right;
            while (tmp.left != null) tmp = tmp.left;
            return tmp;
        }
        
        while (root != p) {
        	if (root.val > p.val) {  //p in left
        		tmp = root;
        		root = root.left;
        	} else {  //p in right
        		root = root.right;   //when move to right, the successor will still be its (grand) root's (in case right not exist) successor
        	}
        }
        
        return tmp;
    }
    
    
    //OR
//    The idea is to compare root's value with p's value if root is not null, and consider the following two cases:
//
//    	root.val > p.val. In this case, root can be a possible answer, so we store the root node first and call it res. 
//    	However, we don't know if there is anymore node on root's left that is larger than p.val. 
//    	So we move root to its left and check again.
//
//    	root.val <= p.val. In this case, root cannot be p's inorder successor, 
//    	neither can root's left child. So we only need to consider root's right child, 
//    	thus we move root to its right and check again.
//
//    	We continuously move root until exhausted. To this point, we only need to return the res in case 1.    
    
    public TreeNode inorderSuccessorB(TreeNode root, TreeNode p) {
        TreeNode tmp = null;
                
        while (root != null) {
        	if (root.val > p.val) {  //p in left
        		tmp = root;
        		root = root.left;
        	} else {  //p in right
        		root = root.right;   //when move to right, the successor will still be its (grand) root's (in case right not exist) successor
        	}
        }
        
        return tmp;
    }
    
    
    
    
	//AC: 4%
    public TreeNode inorderSuccessorA(TreeNode root, TreeNode p) {
        if (root == null) return null;
        if (root.left == null && root.right == null && root != p) return null;
        Stack<TreeNode> stkTree = new Stack<TreeNode>();
        TreeNode prev = null;
        TreeNode tnCur = null;
        TreeNode tmp = null;
        
        tmp = root;
        while (tmp != null) {
        	stkTree.push(tmp);
        	tmp = tmp.left;
        }
        
        while (!stkTree.isEmpty()) {
        	tnCur = stkTree.pop();
        	
        	if (prev != null && prev == p) return tnCur;
        	
        	prev = tnCur;
        	
        	if (tnCur.right != null) {
        		stkTree.push(tnCur.right);
        		
        		tmp = tnCur.right;
        		
        		while (tmp.left != null) {
        			stkTree.push(tmp.left);
        			tmp = tmp.left;
        		}
        	}
        }
        
        return null;
    }
    
 
}
