package com.leet;

//The thief has found himself a new place for his thievery again. 
//There is only one entrance to this area, called the "root." 
//Besides the root, each house has one and only one parent house. 
//After a tour, the smart thief realized that "all houses in this place forms a binary tree". 
//It will automatically contact the police if two directly-linked houses were broken into on the same night.
//
//Determine the maximum amount of money the thief can rob tonight without alerting the police.
//
//Example 1:
//     3
//    / \
//   2   3
//    \   \ 
//     3   1
//Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
//Example 2:
//     3
//    / \
//   4   5
//  / \   \ 
// 1   3   1
//Maximum amount of money the thief can rob = 4 + 5 = 9.


//Uber
public class HouseRobberIII {

	public HouseRobberIII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		TreeNode root = new TreeNode(3);
//		root.left = new TreeNode(2);
//		root.right  = new TreeNode(3);
//		root.left.right = new TreeNode(3); 
//		root.right.right = new TreeNode(1);

		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(4);
		root.right  = new TreeNode(5);
		root.left.left = new TreeNode(1); 
		root.left.right = new TreeNode(3);		
		root.right.right = new TreeNode(1);
		
		System.out.println(rob(root));
	}
	
    public int rob(TreeNode root) {
    	return robHelper(root, false); 
    }
    
    private int robHelper(TreeNode root, boolean bParentRobbed) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
        	if (bParentRobbed) return 0;
        	return root.val;
        }
        
        int nCount = 0;
        
        if (bParentRobbed) {
        	nCount = robHelper(root.left, false) + robHelper(root.right, false);
        } else {
        	int nstrategy1 = robHelper(root.left, false) + robHelper(root.right, false);
        	int nstrategy2 = root.val + robHelper(root.left, true) + robHelper(root.right, true);
        	nCount = Math.max(nstrategy1, nstrategy2);
        }
        
        return nCount;
    }
	
}
