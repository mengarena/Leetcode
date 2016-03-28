package com.leet;

//Given a binary tree, find the length of the longest consecutive sequence path.
//
//The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
//The longest consecutive path need to be from parent to child (cannot be the reverse).
//
//For example,
//   1
//    \
//     3
//    / \
//   2   4
//        \
//         5
//Longest consecutive sequence path is 3-4-5, so return 3.
//		
//   2
//    \
//     3
//    / 
//   2    
//  / 
// 1
//Longest consecutive sequence path is 2-3,not3-2-1, so return 2.


//Google
public class BinaryTreeLongestConsecutiveSequence {

	public BinaryTreeLongestConsecutiveSequence() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(4);
		root.right.right.right = new TreeNode(5);

//		TreeNode root = new TreeNode(2);
//		root.right = new TreeNode(3);
//		root.right.left = new TreeNode(2);
//		root.right.left.left = new TreeNode(1);
		
		System.out.println(longestConsecutive(root));
	}
	
	
	//Accepted: 15%,  3ms
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
                
        int nMaxLeft = longestConsecutiveHelper(root.left, root, 1);
        int nMaxRight = longestConsecutiveHelper(root.right, root, 1);
        
        return Math.max(nMaxLeft, nMaxRight);
    }	
    
    
    private int longestConsecutiveHelper(TreeNode root, TreeNode parent, int nCurMaxLong) {
        if (root == null) return nCurMaxLong;
        int nLeft, nRight;
                	
        if (root.val - parent.val == 1) {
        	nLeft = longestConsecutiveHelper(root.left, root, nCurMaxLong+1);
        	nRight = longestConsecutiveHelper(root.right, root, nCurMaxLong+1);
        	return Math.max(nLeft, nRight);
        } else {
        	nLeft = longestConsecutiveHelper(root.left, root, 1);   //1 -- Reset length 
        	nRight = longestConsecutiveHelper(root.right, root, 1);  
        	return Math.max(nCurMaxLong, Math.max(nLeft, nRight));
        }
        
        
    }
}
