package com.leet;

//Given a sorted linked list, delete all duplicates such that each element appear only once.
//
//For example,
//Given 1->1->2, return 1->2.
//Given 1->1->2->3->3, return 1->2->3.
		
public class RemoveDuplicatesSortedList {
	//Definition for singly-linked list.
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public RemoveDuplicatesSortedList() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		ListNode head = new ListNode(1);  
		head.next = null;
//		head.next = new ListNode(1);
//		head.next.next = new ListNode(2);
//		head.next.next.next = new ListNode(3);
//		head.next.next.next.next = new ListNode(3);	
		
		ListNode nonDuplicate = deleteDuplicates(head);
		System.out.println("Non Duplicate: ");
		while (nonDuplicate != null) {
			System.out.print(nonDuplicate.val + ",");
			nonDuplicate = nonDuplicate.next;
		}
	}
	
    public ListNode deleteDuplicates(ListNode head) {
    	ListNode pCur = head;
    	ListNode lnNonDuplicate = pCur;
        ListNode pNext;
        
        if (head == null) return null;
        
        int nCurVal = head.val;
        int nNextVal;
        
        pNext = pCur.next;
        
        while (pNext != null) {
        	nNextVal = pNext.val;
        	
        	if (nNextVal == nCurVal) {
        		pNext = pNext.next;
        	} else {
        		pCur.next = pNext;
        		pCur = pNext;
        		nCurVal = nNextVal;
        		pNext = pNext.next;
        	}
        }
        
        pCur.next = null;
        
        return lnNonDuplicate;
    }
	
}
