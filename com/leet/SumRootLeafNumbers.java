package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
//
//An example is the root-to-leaf path 1->2->3 which represents the number 123.
//
//Find the total sum of all root-to-leaf numbers.
//
//For example,
//
//    1
//   / \
//  2   3
//The root-to-leaf path 1->2 represents the number 12.
//The root-to-leaf path 1->3 represents the number 13.
//
//Return the sum = 12 + 13 = 25.

public class SumRootLeafNumbers {

	public SumRootLeafNumbers() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right  = new TreeNode(3);
		root.left.left = new TreeNode(4); root.left.left.left = null; root.left.left.right = null;
		
		root.left.right = null;
		
		root.right.left = new TreeNode(5);   root.right.left.left = null;  root.right.left.right = null;
		root.right.right = new TreeNode(6);
		root.right.right.left = null;
		root.right.right.right = new TreeNode(7);   root.right.right.right.left = null;  root.right.right.right.right = null;

		System.out.println("Sum: " + sumNumbers(root));
	}
	

    public int sumNumbers(TreeNode root) {
        int nSum = 0;
        
        nSum = getPathSum(root, 0);
              
        return nSum;
    }
    
    
    public int getPathSum(TreeNode root, int nLevelBase) {
    	
    	if (root == null) return 0;
    	
    	if (root.left == null && root.right == null) {
    		return nLevelBase + root.val;
    	}

    	nLevelBase = (nLevelBase + root.val)*10;
    	int nLeftSub = getPathSum(root.left, nLevelBase);
    	int nRightSub = getPathSum(root.right, nLevelBase);
    	
    	return nLeftSub + nRightSub;
    }

    
 /* Works  
    public List<String> getPathSum(TreeNode root) {
    	List<String> lstPathSum = new ArrayList<String>();
    	List<String> lstPathSumLeft = new ArrayList<String>();
    	List<String> lstPathSumRight = new ArrayList<String>();
    	
    	if (root == null) return lstPathSum;
    	
    	if (root.left == null && root.right == null) {
    		lstPathSum.add(root.val + "");
    		return lstPathSum;
    	}
    	
    	if (root.left != null) {
    		lstPathSumLeft = getPathSum(root.left);
    		for (String sVal:lstPathSumLeft) {
    			lstPathSum.add(root.val + sVal);
    		}
    	}
    	
    	if (root.right != null) {
    		lstPathSumRight = getPathSum(root.right);
    		for (String sVal:lstPathSumRight) {
    			lstPathSum.add(root.val + sVal);
    		}
    	}
    	
    	return lstPathSum;
    }
   
  */ 
}
