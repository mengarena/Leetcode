package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
//
//For example:
//Given the below binary tree and sum = 22,
//              5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \    / \
//        7    2  5   1
//return
//[
//   [5,4,11,2],
//   [5,8,4,5]
//]

//Bloomberg
public class PathSumII {

	public PathSumII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		TreeNode root = new TreeNode(5);
//		root.left = new TreeNode(4);
//		root.right  = new TreeNode(8);
//		root.left.left = new TreeNode(11); 
//		root.left.left.left = new TreeNode(7);  root.left.left.left.left = null; root.left.left.left.right = null;
//		root.left.left.right = new TreeNode(2);  root.left.left.right.left = null; root.left.left.right.right = null;
//		
//		root.left.right = null;
//		
//		root.right.left = new TreeNode(13);   root.right.left.left = null;  root.right.left.right = null;
//		root.right.right = new TreeNode(4);
//		root.right.right.left = new TreeNode(5);  root.right.right.left.left = null; root.right.right.left.right = null;
//		root.right.right.right = new TreeNode(1);   root.right.right.right.left = null;  root.right.right.right.right = null;

		
		TreeNode root = new TreeNode(-2);
		root.left = null;
		root.right  = new TreeNode(-3);  root.right.left = null; root.right.right = null;
//		root.left.left = new TreeNode(11); 
//		root.left.left.left = new TreeNode(7);  root.left.left.left.left = null; root.left.left.left.right = null;
//		root.left.left.right = new TreeNode(2);  root.left.left.right.left = null; root.left.left.right.right = null;
//		
//		root.left.right = null;
//		
//		root.right.left = new TreeNode(13);   root.right.left.left = null;  root.right.left.right = null;
//		root.right.right = new TreeNode(4);
//		root.right.right.left = new TreeNode(5);  root.right.right.left.left = null; root.right.right.left.right = null;
//		root.right.right.right = new TreeNode(1);   root.right.right.right.left = null;  root.right.right.right.right = null;
		
		
		int sum = -5;
		
		List<List<Integer>> lstlstPathSum = pathSum(root, sum);
		
		for (List<Integer> lstPathSum:lstlstPathSum) {
			System.out.print("[");
			for (Integer nVal:lstPathSum) System.out.print(nVal + ",");
			System.out.println("]");
		}
		
	}
	
	
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	List<List<Integer>> lstlstPathSum = new ArrayList<List<Integer>>();
    	
    	if (root == null) return lstlstPathSum;
    	if (root.val != sum && root.left == null && root.right == null) return lstlstPathSum;
    	
    	if (sum == root.val && root.left == null && root.right == null) {
    		List<Integer> lstPathSum = new ArrayList<Integer>();
    		lstPathSum.add(root.val);
    		lstlstPathSum.add(lstPathSum);
    		return lstlstPathSum;
    	}
    	
    	List<List<Integer>> lstlstRemainedLeft = pathSum(root.left, sum-root.val);
    	List<List<Integer>> lstlstRemainedRight = pathSum(root.right, sum-root.val);
    	
    	if (lstlstRemainedLeft.size() > 0) {
    		for (List<Integer> lstRemainedLeft:lstlstRemainedLeft) {
    			lstRemainedLeft.add(0, root.val);
    			lstlstPathSum.add(lstRemainedLeft);
    		}
    	}
    	
    	if (lstlstRemainedRight.size() > 0) {
    		for (List<Integer> lstRemainedRight:lstlstRemainedRight) {
    			lstRemainedRight.add(0, root.val);
    			lstlstPathSum.add(lstRemainedRight);
    		}    		
    	}
    	
    	return lstlstPathSum;
    }
	
}
