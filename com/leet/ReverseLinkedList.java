package com.leet;

//Reverse a singly linked list.
//
//click to show more hints.
//
//Hint:
//A linked list can be reversed either iteratively or recursively. Could you implement both?

//Facebook, Uber, Amazon, Uber, Twitter, Zenefits, Microsoft, Snapchat, Apple, Yahoo, Bloomberg, Yelp, Adobe
//Easy
public class ReverseLinkedList {
	//Definition for singly-linked list.
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public ReverseLinkedList() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		ListNode root = new ListNode(3); root.next = null;
//		root.next = new ListNode(4);
//		root.next.next = new ListNode(5);
//		root.next.next.next = null;
		
		ListNode reversed = reverseList(root);
		
		System.out.println("Reversed:");
		while (reversed != null) {
			System.out.print(reversed.val);
			reversed = reversed.next;
		}
	}
		
    
    //ACC
    public ListNode reverseListA(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode next = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return next;
    }
    
    //ACC
    public ListNode reverseListAA(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode lnPrev = head;
        ListNode lnCurr = lnPrev.next;
        ListNode lnNext = null;
        
        while (lnCurr != null) {
            if (lnNext == null) head.next = null;
            
            lnNext = lnCurr.next;
            lnCurr.next = lnPrev;
            
            lnPrev = lnCurr;
            lnCurr = lnNext;
        }
        
        return lnPrev;
    }
    
    
    
    
    public ListNode reverseList(ListNode head) {
        ListNode reversed = null;
        ListNode pPrev = head;
        ListNode pCur = null;
        ListNode pNext = null;
        
        if (head == null) return null;
        
        pCur = pPrev.next;
        
        while (pCur != null) {
        	 pNext = pCur.next;
        	 pCur.next = pPrev;
        	 if (pPrev == head) pPrev.next = null;
        	 pPrev = pCur;
        	 pCur = pNext;
        }
        
        reversed = pPrev;
        
        return reversed;
    }
    
    
    /* Recursive
    public ListNode reverseList(ListNode head) {
        return reverseListInt(head, null);
    }

    public ListNode reverseListInt(ListNode head, ListNode newHead) {
        if(head == null)
            return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return reverseListInt(next, head);
    }
    */
}
