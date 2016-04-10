package com.leet;

import java.util.Stack;

//Given a binary tree, find the maximum path sum.
//
//For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
//The path does not need to go through the root.
//
//For example:
//Given the below binary tree,
//
//       1
//      / \
//     2   3
//Return 6.

//Microsoft
public class BinaryTreeMaximumPathSum {

	public BinaryTreeMaximumPathSum() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);
		root.right.left = new TreeNode(7);
		root.right.right = new TreeNode(8);
		
		int maxSum = maxPathSum(root);
		
		System.out.println(maxSum);
	}
	
	
	//ACC:  24%
	private static int maxSum  = Integer.MIN_VALUE;
	
    public int maxPathSum(TreeNode root) {
    	maxSum = Integer.MIN_VALUE;
    	
    	maxPathSumHelper(root);
        
        return maxSum;
    }

	
    private int maxPathSumHelper(TreeNode root) {
    	if (root == null) return 0;
    	
    	int leftSum = Math.max(0, maxPathSumHelper(root.left));
    	int rightSum = Math.max(0, maxPathSumHelper(root.right));
    	
    	maxSum = Math.max(maxSum, root.val + leftSum + rightSum);   //Max sum of the sub tree rooted at "root"
    
    	return root.val + Math.max(leftSum, rightSum);     //Max sum of one path (root+left child  or root+right child)
    }
    
    
    
	
	
	//ACC:  1%
    public int maxPathSumAA(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        int maxSum = Integer.MIN_VALUE;
        TreeNode tnTmp = null;
        TreeNode tnPrev = null;
        
        stk.push(root);
        
        while (root.left != null) {
        	stk.push(root.left);
        	root = root.left;
        }
        
        while (!stk.isEmpty()) {
            if (stk.peek().right != null && stk.peek().right != tnPrev) {   //stk.peek().right != tnPrev  prevents loop to process the right again and again
            	tnTmp = stk.peek().right;
            	stk.push(tnTmp);
            	
            	while (tnTmp.left != null) {
            		stk.push(tnTmp.left);
            		tnTmp = tnTmp.left;
            	}
            	
            	continue;
            } 
            	
            tnTmp = stk.pop(); 
            tnPrev = tnTmp;
        	
        	if (tnTmp.left == null && tnTmp.right == null) {
        		maxSum = Math.max(maxSum, tnTmp.val);
        	} else if (tnTmp.left != null && tnTmp.right == null) {
        		if (tnTmp.left.val < 0) {
        			maxSum = Math.max(maxSum, tnTmp.val);
        		} else {
        			maxSum = Math.max(maxSum, tnTmp.val + tnTmp.left.val);
        			tnTmp.val = tnTmp.val + tnTmp.left.val;
        		}
        	} else if (tnTmp.left == null && tnTmp.right != null) {
        		if (tnTmp.right.val < 0) {
        			maxSum = Math.max(maxSum, tnTmp.val);
        		} else {
        			maxSum = Math.max(maxSum, tnTmp.val + tnTmp.right.val);
        			tnTmp.val = tnTmp.val + tnTmp.right.val;
        		}
        	} else {  //tnTmp.left != null && tnTmp.right != null
        		int tmpMax = Math.max(tnTmp.left.val, tnTmp.right.val);
        		if (tmpMax >= 0) {
        			if (tnTmp.left.val >= 0 && tnTmp.right.val >= 0) {
        				maxSum = Math.max(maxSum, tnTmp.val + tnTmp.left.val + tnTmp.right.val);
        			} else {
        				maxSum = Math.max(maxSum, tmpMax + tnTmp.val);
        			}
        			tnTmp.val = tmpMax+tnTmp.val;
        		} else {
        			maxSum = Math.max(maxSum, tnTmp.val);
        		}
        	}
        }
        
        return maxSum;
    }
    
    
    
//    private void getRoots(TreeNode root) {
//    	if (root == null) return;
//    
//        stk.push(root);
//      
//        if (root.left != null) getRoots(root.left);
//        if (root.right != null) getRoots(root.right);
//    }
//
//    
//    private void getRootsB(TreeNode root) {
//    	
//    }
    
    
    
    
    //ACC: 2%
    //
    //Strategy:  push all node onto a stack from root to leaf, in this way, the bottom of the tree will be on the top of the stack
    //Then pop the node to calculate the max sum of the path goes through this node (as root), and also change the node's value to remember the max on its left or right path (including the node itself)
    //If both left and right children are negative, the node keeps its own value
    public int maxPathSumA(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        int maxSum = Integer.MIN_VALUE;
        TreeNode tnTmp = null;
        
        getRootsA(root, stk);
        
        while (!stk.isEmpty()) {
        	tnTmp = stk.pop();
        	        	
        	if (tnTmp.left == null && tnTmp.right == null) {
        		maxSum = Math.max(maxSum, tnTmp.val);
        	} else if (tnTmp.left != null && tnTmp.right == null) {
        		if (tnTmp.left.val < 0) {
        			maxSum = Math.max(maxSum, tnTmp.val);
        		} else {
        			maxSum = Math.max(maxSum, tnTmp.val + tnTmp.left.val);
        			tnTmp.val = tnTmp.val + tnTmp.left.val;
        		}
        	} else if (tnTmp.left == null && tnTmp.right != null) {
        		if (tnTmp.right.val < 0) {
        			maxSum = Math.max(maxSum, tnTmp.val);
        		} else {
        			maxSum = Math.max(maxSum, tnTmp.val + tnTmp.right.val);
        			tnTmp.val = tnTmp.val + tnTmp.right.val;
        		}
        	} else {  //tnTmp.left != null && tnTmp.right != null
        		int tmpMax = Math.max(tnTmp.left.val, tnTmp.right.val);
        		if (tmpMax >= 0) {
        			if (tnTmp.left.val >= 0 && tnTmp.right.val >= 0) {
        				maxSum = Math.max(maxSum, tnTmp.val + tnTmp.left.val + tnTmp.right.val);
        			} else {
        				maxSum = Math.max(maxSum, tmpMax + tnTmp.val);
        			}
        			tnTmp.val = tmpMax+tnTmp.val;
        		} else {
        			maxSum = Math.max(maxSum, tnTmp.val);
        		}
        	}
        }
        
        return maxSum;
    }
    
    
    private void getRootsA(TreeNode root, Stack<TreeNode> stk) {
    	if (root == null) return;
    
        stk.push(root);
      
        if (root.left != null) getRootsA(root.left, stk);
        if (root.right != null) getRootsA(root.right, stk);
    }
    
    
}
