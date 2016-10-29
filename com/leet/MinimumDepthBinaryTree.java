package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a binary tree, find its minimum depth.
//
//The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

//Easy
public class MinimumDepthBinaryTree {

	public MinimumDepthBinaryTree() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = null; //new TreeNode(20);
		root.left.left = null; root.left.right = null;
		//root.right.left = new TreeNode(15);  root.right.left.left = null;  root.right.left.right = null;
		//root.right.right = new TreeNode(7);  root.right.right.left = null;  root.right.right.right = null;
		
		System.out.println("Min Depth = " + minDepth(root));
	}
	
	//AC
	public int minDepth(TreeNode root) {
		int nDepth = 0;
		if (root == null) return 0;
		
		List<TreeNode> lstLevel = new ArrayList<TreeNode>();
		lstLevel.add(root);
		
		while (!lstLevel.isEmpty()) {
			int nCount = lstLevel.size();
			
			nDepth++;
			
			for (int i=0; i<nCount; i++) {
				TreeNode tnTmp = lstLevel.remove(0);
				if (tnTmp.left == null && tnTmp.right == null) {
					return nDepth;
				} else {
					if (tnTmp.left != null) lstLevel.add(tnTmp.left);
					if (tnTmp.right != null) lstLevel.add(tnTmp.right);
				}
			}
		}
		
		return nDepth;
		
	}
	
	//AC Leaf:  both its left and right children should be null
    public int minDepthA(TreeNode root) {
        int nMinDepth = 0;
        
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        
        int nLeftDepth = 0;
        int nRightDepth = 0;
        
        if (root.left == null && root.right != null) {
        	nMinDepth = 1 + minDepth(root.right);
        } else if (root.left != null && root.right == null) {
        	nMinDepth = 1 + minDepth(root.left);
        } else {
        	nLeftDepth = minDepth(root.left);
        	nRightDepth = minDepth(root.right);
        
        	nMinDepth = 1 + Math.min(nLeftDepth, nRightDepth);
        }
        
        return nMinDepth;
    }
	
}
