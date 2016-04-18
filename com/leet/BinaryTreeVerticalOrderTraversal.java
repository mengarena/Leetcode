package com.leet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
//
//If two nodes are in the same row and column, the order should be from left to right.
//
//Examples:
//Given binary tree [3,9,20,null,null,15,7],
//    3
//   / \
//  9  20
//    /  \
//   15   7
//return its vertical order traversal as:
//[
//  [9],
//  [3,15],
//  [20],
//  [7]
//]
//Given binary tree [3,9,20,4,5,2,7],
//    _3_
//   /   \
//  9    20
// / \   / \
//4   5 2   7
//return its vertical order traversal as:
//[
//  [4],
//  [9],
//  [3,5,2],
//  [20],
//  [7]
//]

//Facebook
public class BinaryTreeVerticalOrderTraversal {

	public BinaryTreeVerticalOrderTraversal() {
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
//		TreeNode root = new TreeNode(3);
//		root.left = new TreeNode(9);
//		root.right = new TreeNode(20);
//		root.right.left = new TreeNode(15);
//		root.right.right = new TreeNode(7);
//		
		
//		TreeNode root = new TreeNode(3);
//		root.left = new TreeNode(9);
//		root.left.left = new TreeNode(4);
//		root.left.right = new TreeNode(5);
//		root.right = new TreeNode(20);
//		root.right.left = new TreeNode(2);
//		root.right.right = new TreeNode(7);
		
		TreeNode root = new TreeNode(1);
		root.right = new TreeNode(3);
		
		List<List<Integer>> lstlstVerOrder = verticalOrder(root);
		
		System.out.println();
	}
	
	
	
	//Accepted:   87%
	//First decide the most left and most right---how many List<Integer> should be in lstlstVerOrder
	//Second: Level traversal, which basically decide the (x,y) of each node
	private static int nLeftCnt = Integer.MAX_VALUE;
	private static int nRightCnt = Integer.MIN_VALUE;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> lstlstVerOrder = new ArrayList<>();
        if (root == null) return lstlstVerOrder;
        
        Queue<TreeNode> quRow = new LinkedList<TreeNode>();
        Queue<Integer> quCol = new LinkedList<Integer>();
        
        //Find leftCnt and RightCnt (i.e. the number of left node from root; the number of right node from root) (nLeftCnt < 0)
        inOrderTraversal(root, 0);
        
        for (int i=nLeftCnt; i<=nRightCnt; i++) lstlstVerOrder.add(new ArrayList<Integer>());  //Total number Math.abs(nLeftCnt) + nRightCnt
        
        quRow.offer(root);
        quCol.offer(Math.abs(nLeftCnt));   //So if the most left column is 0, the column of the root node will be Math.abs(nLeftCnt)
        
        //Level traversal
        while (!quRow.isEmpty() && !quCol.isEmpty()) {
        	TreeNode tnTmp = quRow.poll();
        	int nCol = quCol.poll();
        	
        	lstlstVerOrder.get(nCol).add(tnTmp.val);
        	
        	if (tnTmp.left != null) {
        		quRow.offer(tnTmp.left);
        		quCol.offer(nCol-1);
        	} 
        	
        	if (tnTmp.right != null) {
        		quRow.offer(tnTmp.right);
        		quCol.offer(nCol+1);
        	}
        }
        
        
        return lstlstVerOrder;
    }

    
    private void inOrderTraversal(TreeNode root, int nColumnIdx) {
    	if (root == null) return;
    	
    	inOrderTraversal(root.left, nColumnIdx-1);
    	
    	if (nLeftCnt > nColumnIdx) nLeftCnt = nColumnIdx;
    	if (nRightCnt < nColumnIdx) nRightCnt = nColumnIdx;
    	
    	inOrderTraversal(root.right, nColumnIdx+1);
    }
    
    

}
