package com.leet;

//Sort a linked list using insertion sort.

public class InsertionSortList {

	public InsertionSortList() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		ListNode head = null; //new ListNode(24);
//		head.next = new ListNode(34);
//		head.next.next = null; //new ListNode(9);
//		head.next.next.next = new ListNode(64);
//		head.next.next.next.next = new ListNode(7);
//		head.next.next.next.next.next = new ListNode(23);
//		head.next.next.next.next.next.next = new ListNode(34);
//		head.next.next.next.next.next.next.next = new ListNode(47);
//		head.next.next.next.next.next.next.next.next = null;
		
		ListNode newHead = insertionSortList(head);
		while (newHead != null) {
			System.out.print(newHead.val + ",");
			newHead = newHead.next;
		}
	}
	
    public ListNode insertionSortList(ListNode head) {
        ListNode newHead = head;
        ListNode lnPre = null;
        ListNode lnCur = null;
        ListNode lnNext = null;
        ListNode lnMove = null;
        ListNode lnMovePre = null;
        
        if (head == null || head.next == null) return head;
        
        lnPre = head;
        lnCur = head.next;
        
        //Compare lnCur with the element before (i.e. the element starts from newHead to current position
        while (lnCur != null) {
        	lnNext = lnCur.next;
        	lnPre.next = lnNext;
        	
        	lnMovePre = null;
        	lnMove = newHead;
        	
        	//Move the lnCur to its position
        	while (lnMove != lnNext) {
        		if (lnCur.val <= lnMove.val) {
        			if (lnMovePre == null) {
        				newHead = lnCur;
        				newHead.next = lnMove;
        			} else {
        				lnMovePre.next = lnCur;
        				lnCur.next = lnMove;
        			}
        			
        			break;
        		}
        		
        		lnMovePre = lnMove;
        		lnMove = lnMove.next;
        	}
        	
        	if (lnMove == lnNext) {   //lnCur is original in right position, no move happen
        		lnPre.next = lnCur;
        		lnCur.next = lnNext;
        		lnPre = lnCur;
        		lnCur = lnNext;
        	} else {
        		lnCur = lnNext;
        	}
        }
        
        return newHead;
    	
    }
	
}
