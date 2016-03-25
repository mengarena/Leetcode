package com.leet;

//Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
//
//Note:
//Given target value is a floating point.
//You are guaranteed to have only one unique value in the BST that is closest to the target.

public class ClosestBinarySearchTreeValue {

	public ClosestBinarySearchTreeValue() {
		// TODO Auto-generated constructor stub
	}


    public void run() {
    	TreeNode root = new TreeNode(2);
    	root.left = new TreeNode(1);
    	
    	System.out.println(closestValue(root, 2147483647.0));
    }
    
    
    //Accepted
    public int closestValue(TreeNode root, double target) {
        double fDiff = Double.MAX_VALUE;
        int nClosestVal = 0;
        double fTmpDiff = 0;
        
        while (root != null) {
            if (root.val > target) {
                fTmpDiff = root.val - target;  
                if (fTmpDiff < fDiff) {
                    fDiff = fTmpDiff;
                    nClosestVal = root.val;
                }
                root = root.left;
            } else if (root.val < target) {
                fTmpDiff= target - root.val; 
                if (fTmpDiff < fDiff) {
                    fDiff = fTmpDiff;
                    nClosestVal = root.val;
                }
                root = root.right;
            } else {
                return root.val;
            }
        }
        
        return nClosestVal;
    }
    
}
