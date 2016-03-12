package com.leet;

//Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
//
//For example,
//Given 1->2->3->3->4->4->5, return 1->2->5.
//Given 1->1->1->2->3, return 2->3.

public class RemoveDuplicatesSortedListII {

	public RemoveDuplicatesSortedListII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		ListNode head = new ListNode(2);  
		head.next = null; //new ListNode(2);
		//head.next.next = null; //new ListNode(2);
	//	head.next.next.next = new ListNode(4);
	//	head.next.next.next.next = new ListNode(4);	
	//	head.next.next.next.next.next = new ListNode(4);	
	//	head.next.next.next.next.next.next = new ListNode(4);
	//	head.next.next.next.next.next.next.next = null;		
		
		ListNode nonDuplicate = deleteDuplicates(head);
		System.out.println("Non Duplicate: ");
		while (nonDuplicate != null) {
			System.out.print(nonDuplicate.val + ",");
			nonDuplicate = nonDuplicate.next;
		}
		
	}
	
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        
        ListNode newHead = null;
        ListNode pCur = head;
        ListNode pPrev = null;
        ListNode pNext = pCur.next;
        int nCnt = 1;
        
        while (pNext != null) {
        	        	
        	if (pCur.val == pNext.val) {
        		nCnt = nCnt + 1;
        		pNext = pNext.next;
        	} else {
        		if (nCnt == 1) {
        			if (newHead == null) newHead = pCur;
        			if (pPrev == null) {
        				pPrev = pCur;
        			} else {
        				pPrev.next = pCur;
        				pPrev = pCur;
        			}
        		}
        		
        		pCur = pNext;
        		nCnt = 1;
        		
        		pNext = pCur.next;
        	}
        	
        }
        
        if (pPrev != null) {
        	if (nCnt > 1) {
        		pPrev.next = null;
        	} else {
        		pPrev.next = pCur;
        	}
        } else {
        	if (nCnt == 1) {
        		pPrev = pCur;
        		newHead = pCur;
        	}
        }
        
        return newHead;
    }
	
}
