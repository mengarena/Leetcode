package com.leet;

//Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class ConvertSortedListBinarySearchTree {

	public ConvertSortedListBinarySearchTree() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		head.next.next.next.next.next.next = new ListNode(7);
		head.next.next.next.next.next.next.next = new ListNode(8);
		head.next.next.next.next.next.next.next.next = null;
		
		TreeNode tnBST = sortedListToBST(head);
		
		System.out.println("----");
		
		
	}
	
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        TreeNode root = null;

        root = sortedListToBST(head, null);
        
        return root;
    }
	
    
    //Construct BST from node begin to end: [begin, end)
    public TreeNode sortedListToBST(ListNode begin, ListNode end) {
    	TreeNode tnRoot;
    	ListNode fast = begin;
    	ListNode slow = begin;
    	
    	if (begin == end) {
    		return null;
    	} else if (begin.next == end) {
    		tnRoot = new TreeNode(begin.val);
    		tnRoot.left = null;
    		tnRoot.right = null;
    		return tnRoot;
    	}
   	
        //Find the middle point (slow will finally point to the middle point)
        while (fast != end && fast.next != end) {
        	fast = fast.next.next;
        	slow = slow.next;
        }
    	
        tnRoot = new TreeNode(slow.val);
        tnRoot.left = sortedListToBST(begin, slow);
        
        if (slow.next != end) {
        	tnRoot.right = sortedListToBST(slow.next, end);
        } else {
        	tnRoot.right = null;
        }
        
    	return tnRoot;
    }
}
