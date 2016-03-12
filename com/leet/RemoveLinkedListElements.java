package com.leet;

//Remove all elements from a linked list of integers that have value val.
//
//Example
//Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
//Return: 1 --> 2 --> 3 --> 4 --> 5

public class RemoveLinkedListElements {

	public RemoveLinkedListElements() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		ListNode head = new ListNode(2);
		head.next = new ListNode(1);
		head.next.next = new ListNode(2);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(2);
		head.next.next.next.next.next = new ListNode(4);
		head.next.next.next.next.next.next = new ListNode(2);
		
		ListNode newHead = removeElements(head, 2);
	
		while (newHead != null) {
			System.out.print(newHead.val);
			newHead = newHead.next;
		}
	}
	
    public ListNode removeElements(ListNode head, int val) {
        ListNode lnNewHead = null;
        ListNode pCur = null;
        ListNode pNext = head;
        
        if (head == null) return null;
        
        while (pNext != null) {
        	if (pNext.val != val) {
        		if (pCur == null) {
        			pCur = pNext;
        		} else {
        			pCur.next = pNext;
        			pCur = pNext;
        		}
        		if (lnNewHead == null) lnNewHead = pCur;
        		pNext = pNext.next;
        	} else {
        		pNext = pNext.next;
        	}
        }
        
        if (pCur != null) pCur.next = null;
        
        return lnNewHead;
    }
		
}
