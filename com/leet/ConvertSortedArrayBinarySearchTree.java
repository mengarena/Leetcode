package com.leet;

//Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

public class ConvertSortedArrayBinarySearchTree {

	public ConvertSortedArrayBinarySearchTree() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}

	//Binary Search Tree:  left <= root <= right
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int n = nums.length;
        TreeNode root = null;

        root = ConstructBST(nums, 0, n-1);
        
        return root;
    }
    
    
    public TreeNode ConstructBST(int[] nums, int nStart, int nEnd) {
    	if (nEnd < nStart) return null;
    	
    	TreeNode tnTmpRoot = null;
    	
    	if (nEnd == nStart) {
    		tnTmpRoot = new TreeNode(nums[nStart]);
    		tnTmpRoot.left = null;
    		tnTmpRoot.right = null;
    		return tnTmpRoot;
    	}
    	
    	int nMiddlePos = (nStart+nEnd)/2;
    	tnTmpRoot = new TreeNode(nums[nMiddlePos]);
    	tnTmpRoot.left = ConstructBST(nums, nStart, nMiddlePos-1);
    	tnTmpRoot.right = ConstructBST(nums, nMiddlePos+1, nEnd);
    	
    	return tnTmpRoot;
    }
	
}
