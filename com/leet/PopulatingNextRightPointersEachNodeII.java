package com.leet;

import java.util.ArrayList;
import java.util.List;

//Follow up for problem "Populating Next Right Pointers in Each Node".
//
//What if the given tree could be any binary tree? Would your previous solution still work?
//
//Note:
//
//You may only use constant extra space.
//For example,
//Given the following binary tree,
//         1
//       /  \
//      2    3
//     / \    \
//    4   5    7
//After calling your function, the tree should look like:
//         1 -> NULL
//       /  \
//      2 -> 3 -> NULL
//     / \    \
//    4-> 5 -> 7 -> NULL
    

public class PopulatingNextRightPointersEachNodeII {

	public PopulatingNextRightPointersEachNodeII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}
	
	//Process level by level
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        List<TreeLinkNode> lstTree = new ArrayList<TreeLinkNode>();
        
        if (root.left == null && root.right == null) root.next = null;
        
        int n;
        
        lstTree.add(root);
        
        while (!lstTree.isEmpty()) {
        	n = lstTree.size();
        	
        	TreeLinkNode tlnCur = null;
        	TreeLinkNode tlnPrev = null;
        	
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
