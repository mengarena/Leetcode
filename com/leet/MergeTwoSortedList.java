package com.leet;

//Merge two sorted linked lists and return it as a new list. 
//The new list should be made by splicing together the nodes of the first two lists.


//Linkedin, Amazon, Microsoft, Apple
//Easy
public class MergeTwoSortedList {
	//Definition for singly-linked list.
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

	public MergeTwoSortedList() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		ListNode head1 = new ListNode(1);  
		head1.next = null;
		
//		head1.next = new ListNode(2);
//		head1.next.next = new ListNode(5);
//		head1.next.next.next = null;
		
	//	head1.next.next.next = new ListNode(3);
	//	head1.next.next.next.next = new ListNode(3);	
	//	head1.next.next.next.next.next = null;

		ListNode head2 = null; //new ListNode(2);  
		//head2.next = null;
		
	//	head2.next = new ListNode(4);
	//	head2.next.next = null;
		
	//	head2.next.next = new ListNode(3);
	//	head2.next.next.next = new ListNode(4);
	//	head2.next.next.next.next = new ListNode(5);	
	//	head2.next.next.next.next.next = null;
		
		ListNode newHead = mergeTwoLists(head1, head2);
		
		System.out.println("Merged: ");
		while (newHead != null) {
			System.out.print(newHead.val + ",");
			newHead = newHead.next;
		}
		
	}
	
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	ListNode mergedLN = null;
    	ListNode p1 = l1;
    	ListNode p2 = l2;
    	ListNode pMerged = null;
    	
    	if (p1 == null) return p2;
    	if (p2 == null) return p1;
    	
    	int nVal1 = p1.val;
    	int nVal2 = p2.val;
    	
    	while (p1 != null && p2 != null) {
    		 
    		if (nVal1 <= nVal2) {
    			if (pMerged == null) {
    				pMerged = p1;
    			} else {
    				pMerged.next = p1;
    				pMerged = pMerged.next;
    			}
    			
    			p1 = p1.next;
    			
    			if (p1 != null) nVal1 = p1.val;
    		} else {
    			if (pMerged == null) {
    				pMerged = p2;
    			} else {
    				pMerged.next = p2;
    				pMerged = pMerged.next;    				
    			}
    			
    			p2 = p2.next;
    			
    			if (p2 != null) nVal2 = p2.val;
    		}
    		
    		if (mergedLN == null) mergedLN = pMerged;
    		    		    		
    	}
    	
    	if (p1 == null) {
    		pMerged.next = p2;
    	}
    	
    	if (p2 == null) {
    		pMerged.next = p1;
    	}
    	
    	return mergedLN;
    }
	
}
