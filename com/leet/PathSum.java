package com.leet;

//Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that 
//adding up all the values along the path equals the given sum.
//
//For example:
//Given the below binary tree and sum = 22,
//              5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \      \
//        7    2      1
//return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.

//Microsoft
//Easy
public class PathSum {

	public PathSum() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.left.left = null; root.left.right = null;
		root.right.left = new TreeNode(15);  root.right.left.left = null;  root.right.left.right = null;
		root.right.right = new TreeNode(7);  root.right.right.left = null;  root.right.right.right = null;
		
		int n = 30;
		boolean bRet = hasPathSum(root, n);
		if (bRet) {
			System.out.println("Has Path for value " + n );
		} else {
			System.out.println("Has NO Path for value "+ n);
		}
	}
	
    public boolean hasPathSum(TreeNode root, int sum) {
        
        if (root == null) return false;
        
        if (root.left == null && root.right == null && root.val == sum) return true;
        
        if (root.left == null && root.right == null && root.val != sum) return false;
        
        
        boolean bLeft = hasPathSum(root.left, sum-root.val);
        boolean bRight = hasPathSum(root.right, sum-root.val);
        
        return bLeft || bRight;        
    }
	
}
