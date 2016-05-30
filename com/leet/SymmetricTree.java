package com.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
//
//For example, this binary tree is symmetric:
//
//    1
//   / \
//  2   2
// / \ / \
//3  4 4  3
//But the following is not:
//    1
//   / \
//  2   2
//   \   \
//   3    3
//Note:
//Bonus points if you could solve it both recursively and iteratively.
//
//confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
//
//
//OJ's Binary Tree Serialization:
//The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.
//
//Here's an example:
//   1
//  / \
// 2   3
//    /
//   4
//    \
//     5
//The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".

//Microsoft, Bloomberg
public class SymmetricTree {
	//Definition for a binary tree node.
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	public class TreeNodeInfo {
		 List<Integer> lstNode;
		 List<Integer> lstDepth;
	}
	
	public SymmetricTree() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);  root.left.left.left = null; root.left.left.right = null;
		root.left.right = new TreeNode(3); root.left.right.left = null; root.left.right.right = null;
		
		root.right.left = new TreeNode(3);  root.right.left.left = null; root.right.left.right = null;
		root.right.right = new TreeNode(3); root.right.right.left = null; root.right.right.right = null;
		
		boolean bRet = isSymmetric(root);
		
		if (bRet == true) {
			System.out.println("Is Symmetric !");
		} else {
			System.out.println("Is NOT Symmetric !");
		}
	}
	
	//Recursive
	public boolean isSymmetric(TreeNode root) {
		if (root == null) return true;
		
		return isEqual(root.left, root.right);
	}
	
	public boolean isEqual(TreeNode p, TreeNode q) {
		if (p == null && q == null) return true;
		if (p == null || q == null) return false;
		
		return (p.val == q.val) && isEqual(p.left, q.right) && isEqual(p.right, q.left);
	}
	
	
	//Interative
    public boolean isSymmetricA(TreeNode root) {
        if (root == null) return true;
        
        Stack<TreeNode> stkLeft = new Stack<TreeNode>();
        Stack<TreeNode> stkRight = new Stack<TreeNode>();
        if (root.left == null ^ root.right == null) return false;
        if (root.left == null && root.right == null) return true;
        TreeNode tnTmp1, tnTmp2;
        
        stkLeft.push(root.left);
        stkRight.push(root.right);

        while (!stkLeft.isEmpty() && !stkRight.isEmpty()) {
            tnTmp1 = stkLeft.pop();
            tnTmp2 = stkRight.pop();
            
            if (tnTmp1.val != tnTmp2.val) return false;
            
            if (tnTmp1.left != null && tnTmp2.right != null) {
                stkLeft.push(tnTmp1.left);
                stkRight.push(tnTmp2.right);
            } else if (tnTmp1.left == null ^ tnTmp2.right == null) {
                return false;
            } 
            
            if (tnTmp1.right != null && tnTmp2.left != null) {
                stkLeft.push(tnTmp1.right);
                stkRight.push(tnTmp2.left);
            } else if (tnTmp1.right == null ^ tnTmp2.left == null) {
                return false;
            }
        }
        
        if (stkLeft.isEmpty() ^ stkRight.isEmpty()) return false;
        
        return true;
    }
	
    
    
	
/* Works	
    public boolean isSymmetric(TreeNode root) {
        boolean bRet = true;
        
        if (root == null) return true;
        
        TreeNodeInfo lstTreeInfo = getMiddleOrder(root, 1);

        if (lstTreeInfo == null) return true;        

        int nSize = lstTreeInfo.lstNode.size();
        if (nSize == 1) return true;
        if (nSize % 2 == 0) return false;
        
        int nMiddle = (nSize-1)/2 + 1;
        int nLeft, nRight;
        int nLeftDepth, nRightDepth;
        
        for (int i=1; i<=nMiddle-1; i++) {
        	nLeft = lstTreeInfo.lstNode.get(nMiddle-i-1);
        	nRight = lstTreeInfo.lstNode.get(nMiddle+i-1);
        	nLeftDepth = lstTreeInfo.lstDepth.get(nMiddle-i-1);
        	nRightDepth = lstTreeInfo.lstDepth.get(nMiddle+i-1);

        	if (nLeft != nRight || nLeftDepth != nRightDepth) {
        		bRet = false;
        		break;
        	}
        }
                
        return bRet;
    }
	
    
	public TreeNodeInfo getMiddleOrder(TreeNode root, int nBaseDepth) {
		TreeNodeInfo trInfo = new TreeNodeInfo();
		trInfo.lstNode = new ArrayList<Integer>();
		trInfo.lstDepth = new ArrayList<Integer>();
				
		if (root == null) {
			return null;
		}
		
		TreeNodeInfo trInfoLeft = getMiddleOrder(root.left, nBaseDepth+1);
		int nRoot = root.val;
		TreeNodeInfo trInfoRight = getMiddleOrder(root.right, nBaseDepth+1);
		
		if (trInfoLeft != null) {
			for (Integer nVal:trInfoLeft.lstNode) trInfo.lstNode.add(nVal);
			for (Integer nDepth:trInfoLeft.lstDepth) trInfo.lstDepth.add(nDepth);
		} 
		
		trInfo.lstNode.add(nRoot);
		trInfo.lstDepth.add(nBaseDepth);
		
		if (trInfoRight != null) {
			for (Integer nVal:trInfoRight.lstNode) trInfo.lstNode.add(nVal);
			for (Integer nDepth:trInfoRight.lstDepth) trInfo.lstDepth.add(nDepth);			
		} 
	
		return trInfo;
	}
*/    
	
    
    
}
