package com.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Given a binary tree
//
//struct TreeLinkNode {
//  TreeLinkNode *left;
//  TreeLinkNode *right;
//  TreeLinkNode *next;
//}
//
//Populate each next pointer to point to its next right node. 
//If there is no next right node, the next pointer should be set to NULL.
//
//Initially, all next pointers are set to NULL.
//
//Note:
//
//You may only use constant extra space.
//You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
//For example,
//Given the following perfect binary tree,
//     1
//   /  \
//  2    3
// / \  / \
//4  5  6  7
//After calling your function, the tree should look like:
//     1 -> NULL
//   /  \
//  2 -> 3 -> NULL
// / \  / \
//4->5->6->7 -> NULL


public class PopulatingNextRightPointersEachNode {

	public PopulatingNextRightPointersEachNode() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		
	}
	
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        List<TreeLinkNode> lstTree = new ArrayList<TreeLinkNode>();
        
        if (root.left == null && root.right == null) root.next = null;
        
        int n;
        
        lstTree.add(root);
        
        while (!lstTree.isEmpty()) {
        	n = lstTree.size();
        	
        	TreeLinkNode tlnPrev = null;
        	TreeLinkNode tlnCur = null;
        	
        	for (int i=n-1; i>=0; i--) {
        		tlnCur = lstTree.remove(i);
 
        		tlnCur.next = tlnPrev;
        		tlnPrev = tlnCur;
        		
        		if (tlnCur.right != null) lstTree.add(i, tlnCur.right);
        		if (tlnCur.left != null) lstTree.add(i, tlnCur.left);
        	}
        }
    	
        
    }
	
}
