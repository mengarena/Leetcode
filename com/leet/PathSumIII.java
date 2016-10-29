package com.leet;

//You are given a binary tree in which each node contains an integer value.
//
//Find the number of paths that sum to a given value.
//
//The path does not need to start or end at the root or a leaf, 
//but it must go downwards (traveling only from parent nodes to child nodes).
//
//The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
//
//Example:
//
//root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
//
//      10
//     /  \
//    5   -3
//   / \    \
//  3   2   11
// / \   \
//3  -2   1
//
//Return 3. The paths that sum to 8 are:
//
//1.  5 -> 3
//2.  5 -> 2 -> 1
//3. -3 -> 11

public class PathSumIII {

	public PathSumIII() {
		// TODO Auto-generated constructor stub
	}

	//ACC: 
	//Time Complixity: Time Complexity should be O(N^2) for the worst case and O(NlogN) for balanced binary Tree.
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;

        return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int findPath(TreeNode root, int sum) {
        int ret = 0;
        if (root == null) return ret;
        if (root.val == sum) ret++;
        
        ret += findPath(root.left, sum-root.val);
        ret += findPath(root.right, sum-root.val);
        return ret;
    }

}
