package com.leet;

//Given a binary tree, count the number of uni-value subtrees.
//
//A Uni-value subtree means all nodes of the subtree have the same value.
//
//For example:
//	
//Given binary tree,
//              5
//             / \
//            1   5
//           / \   \
//          5   5   5
//          
//return 4.

public class CountUnivalueSubtrees {

	public CountUnivalueSubtrees() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		TreeNode root = new TreeNode(5);
//		root.left = new TreeNode(1);
//		root.right = new TreeNode(1);
//		root.left.left = new TreeNode(5);
//		root.left.right = new TreeNode(5);		
//		root.right.right = new TreeNode(5);

		
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(1);
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(5);
		root.left.right = new TreeNode(5);		
		root.right.right = new TreeNode(5);
		
		System.out.println(countUnivalSubtrees(root));
	}

	
	//AC:   Use int[1] to record the count
	//
	//Actually it is postorder traversal
    public int countUnivalSubtrees(TreeNode root) {
    	int count[] = new int[1];
    	countUnivalSubtrees(root, count);
    	return count[0];
    }
        
    //Check whether the subtree rooted at "root" is unique value tree or not
    private boolean countUnivalSubtrees(TreeNode root, int[] count) {
    	if (root == null) return true;
    	
    	//Check left/right subtree, whether they are unique-value tree
    	boolean bLeftUnique = countUnivalSubtrees(root.left, count);
    	boolean bRightUnique = countUnivalSubtrees(root.right, count);
    	
    	if (bLeftUnique == true && bRightUnique == true) {
    		//If both left and right are unique-value tree, check whether the left and right tree's unique value equals to the root,
    		//If equals to the root, than this "root" based tree is unique-value tree
    		if (root.left != null && root.left.val != root.val) return false;
    		if (root.right != null && root.right.val != root.val) return false;
    		
    		count[0]++;   //Accumulate unique-value tree number
    		
    		return true;
    	} else {  
    		//Left or right is not unique, so this "root" based tree is not unique-value tree
    		return false;
    	}
    	
    }

    
//	if ((root!= null && root.left.val != root.val) || (root.right != null && root.right.val != root.val)) {
//		return countUnivalSubtrees(root.left, root, 1) + countUnivalSubtrees(root.right, root, 1);
//	} else {
//		return countUnivalSubtrees(root.left, root, nDepth+1) + countUnivalSubtrees(root.right, root, nDepth+1);
//	}
  
    
/*
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        
        return 1+countUnivalSubtreesHelper(root.left, root) + countUnivalSubtreesHelper(root.right, root);
    }
    
    private int countUnivalSubtreesHelper(TreeNode root, TreeNode parent) {
    	if (root == null) return 0;
    	
    	if (root.val == parent.val) {
    		return countUnivalSubtreesHelper(root.left, root) + countUnivalSubtreesHelper(root.right, root);
    	} else {
    		return 1 + countUnivalSubtreesHelper(root.left, root) + countUnivalSubtreesHelper(root.right, root);
    	}    	
    }
*/    
    
}
