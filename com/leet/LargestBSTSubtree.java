package com.leet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

//Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
//
//Note:
//A subtree must include all of its descendants.
//Here's an example:
//
//    10
//    / \
//   5  15
//  / \   \ 
// 1   8   7
// 
//The Largest BST Subtree in this case is the highlighted one. 
//The return value is the subtree's size, which is 3.
//		
//Hint:
//
//You can recursively use algorithm similar to 98. 
//Validate Binary Search Tree at each node of the tree, which will result in O(nlogn) time complexity.
//
//Follow up:
//Can you figure out ways to solve it with O(n) time complexity?


//Microsoft
public class LargestBSTSubtree {

	public LargestBSTSubtree() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}
	
	
	
	
	class Result {
		int nSize;   //Size of subtree
		int nLower;  //min value of the node in subtree
		int nUpper;  //max value of the node in subtree
		
		Result(int size, int lower, int upper) {
			nSize = size;
			nLower = lower;
			nUpper = upper;
		}
	}
	
	
	//Complexity:  O(n)
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;

        Result ret = traverse(root);
        
        return Math.abs(ret.nSize);
    } 
 
    //When the subtree rooted at "root" is not a BST,
    //It uses the max size of its left/right valid BST as the max size at "root"
    //But, since it uses its left/right subtrees, not the subtree rooted at this node, it use negative to represent
	private Result traverse(TreeNode root) {
		if (root == null) return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
		
		Result left = traverse(root.left);
		Result right = traverse(root.right);
		
		//Subtree rooted at "root" is not a valid BST, it uses its left/right subtree's max valid size as its valid BST size
		if (left.nSize < 0 || right.nSize < 0 || root.val <= left.nUpper || root.val >= right.nLower) {
			return new Result(Math.max(Math.abs(left.nSize), Math.abs(right.nSize))*(-1), 0, 0);
		}
		
		//Valid BST rooted at "root"
		return new Result(left.nSize + right.nSize + 1, Math.min(root.val, left.nLower), Math.max(root.val, right.nUpper));
	}   
    
	
	
	
	
  
   //Works  //AC: 13%
   private static int nMax = 1;
	
   public int largestBSTSubtreeA(TreeNode root) {
        if (root == null) return 0;

        traverse(root);
        
        return nMax;
    }
	   
	private Result traverseA(TreeNode root) {
		if (root == null) return new Result(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
		
		Result left = traverse(root.left);
		Result right = traverse(root.right);
		
		if (left.nSize == -1 || right.nSize == -1 || root.val <= left.nUpper || root.val >= right.nLower) {
			return new Result(-1, 0, 0);
		}
		
		int nSize = left.nSize + right.nSize + 1;
		nMax = Math.max(nMax, nSize);
		
		return new Result(nSize, Math.min(root.val, left.nLower), Math.max(root.val, right.nUpper));
	}
    
	
	
	
	
	//AC:  10%    
    public int largestBSTSubtreeB(TreeNode root) {
        if (root == null) return 0;
        TreeNode tmp = null;
        int nMax = 1;
        
        Queue<TreeNode> quTree = new LinkedList<TreeNode>();
        
        quTree.offer(root);
        
        //Level Traversal
        while (!quTree.isEmpty()) {
        	tmp = quTree.poll();
        	
        	List<Integer> lstTree = new ArrayList<Integer>();
        	
        	if ((tmp.left != null || tmp.right != null) && isValidBST(tmp, lstTree)) {
        	    if (tmp == root) return lstTree.size();
        	    
        	    nMax = Math.max(nMax, lstTree.size());
        	}
        	
        	if (tmp.left != null) quTree.offer(tmp.left);
        	if (tmp.right != null) quTree.offer(tmp.right);
        }
        
        return nMax;
    }    
    
    
    public boolean isValidBST(TreeNode root, List<Integer> lstNode) {
    	if (root == null) return true;
    	
    	if (root.left != null) {
    		if (isValidBST(root.left, lstNode) == false) return false;
    	}
    	
    	if (lstNode.isEmpty()) {
    		lstNode.add(root.val);
    	} else {
    		if (lstNode.get(lstNode.size()-1) >= root.val) return false;
    		lstNode.add(root.val);	
    	}
    	
    	if (root.right != null) {
    		if (isValidBST(root.right, lstNode) == false) return false;
    	}
    	
    	return true;
    }

}
