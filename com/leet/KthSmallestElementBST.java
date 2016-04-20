package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
//
//Note: 
//You may assume k is always valid, 1 ¡Ü k ¡Ü BST's total elements.
//
//Follow up:
//What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
//How would you optimize the kthSmallest routine?
//
//Hint:
//
//Try to utilize the property of a BST.
//What if you could modify the BST node's structure?
//The optimal runtime complexity is O(height of BST).

//Google, Bloomberg
public class KthSmallestElementBST {

	public KthSmallestElementBST() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(2);
		root.right  = new TreeNode(4);
		root.left.left = new TreeNode(1); root.left.left.left = null; root.left.left.right = null;
		
		root.left.right = null; //new TreeNode(3); root.left.right.left = null; root.left.right.right = null;
		
		root.right.left = null; //new TreeNode(5);   root.right.left.left = null;  root.right.left.right = null;
		root.right.right = null; //new TreeNode(6);
		//root.right.right.left = null;
		//root.right.right.right = null; //new TreeNode(7);   root.right.right.right.left = null;  root.right.right.right.right = null;

		System.out.println("3th Order = " + kthSmallest(root, 2));
	}
	
	
	//ACC; 46%
    public int kthSmallest(TreeNode root, int k) {
        int cnt = getCount(root.left);
        
        if (k <= cnt) {
        	return kthSmallest(root.left, k);
        } else if (k > cnt + 1) {
        	return kthSmallest(root.right, k-1-cnt);
        } else {  //k = cnt + 1
        	return root.val;
        }
    }	
    
    public int getCount(TreeNode root) {
    	if (root == null) return 0;
    	
    	return 1 + getCount(root.left) + getCount(root.right);
    }
	
	
	//ACC:  46%
	static int cnt = 0;
	static int num = 0;
	
    public int kthSmallestB(TreeNode root, int k) {
    	cnt = k;
    	getNodeB(root);
    	
    	return num;
    }	
	
    public void getNodeB(TreeNode root) {
    	
    	if (root == null) return;
    	
    	if (root.left != null) getNodeB(root.left);
    	
    	cnt--;
    	if (cnt == 0) {
    		num = root.val;
    		return;
    	}
    	
		if (root.right != null) getNodeB(root.right);
    }  
	
	//ACC: but not good
	//BST:  left <= root <= right
    public int kthSmallestA(TreeNode root, int k) {
    	List<Integer> lstTN = new ArrayList<Integer>();
    	getNodeA(root, lstTN);
    	
    	return lstTN.get(k-1);
    }

	
    public void getNodeA(TreeNode root, List<Integer> lstTN) {
    	
    	if (root == null) return;
    	
    	if (root.left != null) getNodeA(root.left, lstTN);
    	
    	lstTN.add(root.val);
    	
		if (root.right != null) getNodeA(root.right, lstTN);
    }
    
    
}
