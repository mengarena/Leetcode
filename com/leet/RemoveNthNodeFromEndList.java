package com.leet;

//Given a linked list, remove the nth node from the end of list and return its head.
//
//For example,
//
//   Given linked list: 1->2->3->4->5, and n = 2.
//
//   After removing the second node from the end, the linked list becomes 1->2->3->5.
//   
//Note:
//Given n will always be valid.
//Try to do this in one pass.

public class RemoveNthNodeFromEndList {

	public RemoveNthNodeFromEndList() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = null;
//		head.next.next = new ListNode(3);   head.next.next.next = null;
//		head.next.next.next = new ListNode(4);
//		head.next.next.next.next = new ListNode(5);
		
		ListNode newHead= removeNthFromEnd(head, 1);
		
		while (newHead != null) {
			System.out.print(newHead.val + ",");
			newHead = newHead.next;
		}
	}
	
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newHead = head;
        ListNode lnFast = head, lnSlow = head, lnSlower = head;
        int i;
        
        if (head == null) return null;
        
        for (i=0; i<n; i++) {
        	lnFast = lnFast.next;
        	if (lnFast == null) break;
        }
        
        if (lnFast == null) {
        	if (i==n-1) {
        		return newHead.next;
        	} else {
        		return newHead;
        	}
        }
        
        while (lnFast != null) {
        	lnFast = lnFast.next;
        	lnSlower = lnSlow;
        	lnSlow = lnSlow.next;
        }
        
        lnSlower.next = lnSlow.next;
        
        return newHead;
    }
	
}
