package com.leet;

//Given a singly linked list, determine if it is a palindrome.
//
//Follow up:
//Could you do it in O(n) time and O(1) space?
	
//Amazon, Facebook
public class PalindromeLinkedList {

	public PalindromeLinkedList() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		ListNode head = new ListNode(1);    head.next = null;
//		head.next = new ListNode(2);
//		head.next.next = new ListNode(3);
//		head.next.next.next = new ListNode(4);
//		head.next.next.next.next = new ListNode(3);
//		head.next.next.next.next.next = new ListNode(2);
//		head.next.next.next.next.next.next = new ListNode(1);
//		head.next.next.next.next.next.next.next = null;
		
		boolean bRet = isPalindrome(head);
		if (bRet) {
			System.out.println("IS Palindrome !");
		} else {
			System.out.println("IS NOT Palindrome !");
		}
	}
	
	//Requirement:  time O(n),  memory O(1). Need to find the middle point and then reverse the second half linked list
    public boolean isPalindrome(ListNode head) {
        int i;
        int nLen = 0;
        
        if (head == null) return true;
        if (head.next == null) return true;
        
        ListNode newHead = head;
        ListNode pPrev = head;
        ListNode pCur = head;
        ListNode pNext = head;
        
        while (head != null) {
        	nLen = nLen + 1;
        	head = head.next;
        }
        
        int n = nLen/2 + (nLen % 2);
        
        for (i=0; i<n; i++) {
        	pPrev = pPrev.next;
        }
        
        //From middle point, reverse the link for 2nd half
        pCur = pPrev.next;
        
        if (pCur != null) {        	
        	pPrev.next = null;
        	for (i=0; i< nLen/2-1; i++) {
            	pNext = pCur.next;
            	pCur.next = pPrev;
            	pPrev = pCur;
            	pCur = pNext;
        	}
        }
        
        for (i = 0; i <nLen/2; i++) {
        	if (newHead.val != pPrev.val) return false;
        	newHead = newHead.next;
        	pPrev = pPrev.next;
        }
        
        return true;
    }
	
	
}
