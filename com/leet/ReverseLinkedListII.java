package com.leet;

//Reverse a linked list from position m to n. Do it in-place and in one-pass.
//
//For example:
//Given 1->2->3->4->5->NULL, m = 2 and n = 4,
//
//return 1->4->3->2->5->NULL.
//
//Note:
//Given m, n satisfy the following condition:
//1 ¡Ü m ¡Ü n ¡Ü length of list.

public class ReverseLinkedListII {

	public ReverseLinkedListII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = null;
		
		int m = 1;
		int n = 5;
		
		ListNode newHead = reverseBetween(head,m,n);
		while (newHead != null) {
			System.out.print(newHead.val + ",");
			newHead = newHead.next;
		}
	}
	
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode newHead = null;
        
        if (head == null) return null;
        if (m == n) return head;
        
        int i=1;
        ListNode lnStartNode = head;
        ListNode lnPrevStartNode = null;
        ListNode lnPrevNode = null;
        ListNode lnCurNode = null;
        ListNode lnNextNode = null;
        
        if (m > 1) newHead = head;
        
        //Move to the mth node
        while (i < m) {
        	lnPrevStartNode = lnStartNode;
        	lnStartNode = lnStartNode.next;
        	i++;
        }
        
        lnPrevNode = lnStartNode;
        lnCurNode = lnPrevNode.next;
        
        //Node A, B, C:   B's Next should point to A,  C's Next should point to B ...
        while (i < n) {
        	lnNextNode = lnCurNode.next;
        	
        	lnCurNode.next = lnPrevNode;
        	lnPrevNode = lnCurNode;
        	lnCurNode = lnNextNode;
        	i++;
        }
        
        lnStartNode.next = lnCurNode;
        if (lnPrevStartNode == null) {
        	lnPrevStartNode = lnPrevNode;
        	newHead = lnPrevStartNode;
        } else {
        	lnPrevStartNode.next = lnPrevNode;
        }
        
        return newHead;
    }
	
}
