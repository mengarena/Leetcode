package com.leet;

import java.util.Stack;

//Two elements of a binary search tree (BST) are swapped by mistake.
//
//Recover the tree without changing its structure.
//
//Note:
//A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
//confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
//
//
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

public class RecoverBinarySearchTree {

	public RecoverBinarySearchTree() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(7); 
		root.left.right = new TreeNode(3);
		
		root.right = new TreeNode(6);
		root.right.left = new TreeNode(5);
		root.right.right = new TreeNode(1);
		
		recoverTree(root);
		
		InorderPrint(root);
		
		System.out.println();
	}
	
	private void InorderPrint(TreeNode root) {
		if (root == null) return;
		InorderPrint(root.left);
		System.out.print(root.val + ",");
		InorderPrint(root.right);
	}
	
	
	//AC: Morris Travsal
    //Example: 1 5* 3 4 2* 6 7
    //Example: 1 2 3 6* 5 4* 7
    //Example: 1 6* 3 4 5 2* 7
    //(Node with * is the problematic node)
    //
    //Inorder traverse
    //Strategy: First time when meet previous > current, previous is the first mistaken node, current could be possibly the second mistaken node
    //If there is another set previous > current later, that "current" will be the second mistaken node
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        TreeNode pre = null, firstMis = null, secondMis = null, tmp = null;
        
        while (root != null) {
            if (root.left != null) {
            	
                tmp = root.left;
                
                //Find root's left sub tree's right most node
                //Try to connect the rifht most node to root, because in inorder traversal, these two nodes are consecutive
                while (tmp.right != null && tmp.right != root) tmp = tmp.right;
                
                if (tmp.right == null) {   //Connect the rifht most node to root
                    tmp.right = root;
                    root = root.left;
                } else {  
                	//After have visited all the nodes in left nodes in root's left sub tree, the loop still goes to the root node in the outer "while"
                	//So it finally traversal again to it (i.e. root)'s left sub tree's right most node,
                	//So it takes this chance to remove the connection between the right most node and root to recover the tree to its original
                    if (pre != null && pre.val > root.val) {
                        if (firstMis == null) {
                            firstMis = pre;
                            secondMis = root;
                        } else {
                            secondMis = root;
                        }
                    }
                    
                    tmp.right = null;
                    pre = root;
                    root = root.right;
                    
                }
                
            } else {
                if (pre != null && pre.val > root.val) {
                    if (firstMis == null) {
                        firstMis = pre;
                        secondMis = root;
                    } else {
                        secondMis = root;
                    }
                }
                pre = root;
                root = root.right;
            }
        }
        
        //Recover by swapping the values of the two mistaken nodes
        if (firstMis != null && secondMis != null) {
            int nVal = firstMis.val;
            firstMis.val = secondMis.val;
            secondMis.val = nVal;
        }
        
        return;
    }

	
	
    //AC: Recursion solution, actually the space complexity will be O(lgN) or O(n) in worst case
    //This trategy is swapping value of the problematic nodes
    public void recoverTreeA(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stkMis = new Stack<TreeNode>();
        
        inorderTree(root, stkMis);
        
        if (stkMis.size() == 2) {
            TreeNode tnMis2 = stkMis.pop();
            TreeNode tnMis1 = stkMis.pop();
            int nTmp = tnMis1.val;
            tnMis1.val = tnMis2.val;
            tnMis2.val = nTmp;
        }
        
        return;
    }
    
    //Example: 1 5* 3 4 2* 6 7
    //Example: 1 2 3 6* 5 4* 7
    //Example: 1 6* 3 4 5 2* 7
    //(Node with * is the problematic node)
    //
    //Inorder traverse
    //Strategy: First time when meet previous > current, previous is the first mistaken node, current could be possibly the second mistaken node
    //If there is another set previous > current later, that "current" will be the second mistaken node
    private void inorderTree(TreeNode root, Stack<TreeNode> stkMis) {
    	if (root == null) return;
    	
    	inorderTree(root.left, stkMis);
    	
    	if (!stkMis.isEmpty()) {
    		if (stkMis.peek().val > root.val) {
    			if (stkMis.size() == 2) {
    				stkMis.pop();
    				stkMis.push(root);
    			} else if (stkMis.size() == 1) {
    				stkMis.push(root);
    			}
    		} else {
    			if (stkMis.size() < 2) {
    			    stkMis.pop();
    			    stkMis.push(root);
    			}
    		}
    	} else {
    		stkMis.push(root);
    	}
    	
    	inorderTree(root.right, stkMis);
    }
}
