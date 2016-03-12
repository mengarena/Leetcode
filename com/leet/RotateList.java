package com.leet;

//Given a list, rotate the list to the right by k places, where k is non-negative.
//
//For example:
//Given 1->2->3->4->5->NULL and k = 2,
//return 4->5->1->2->3->NULL.

public class RotateList {

	public RotateList() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		
		ListNode newHead = rotateRight(head, 5);
		
		while (newHead != null) {
			System.out.print(newHead.val + ",");
			newHead = newHead.next;
		}
	}
	

    public ListNode rotateRight(ListNode head, int k) {
        int nLen = 0;
        ListNode tmpHead = head;
        int nK=0;
        int i = 0;
        if (head == null) return null;
        
        while (tmpHead != null) {
        	nLen++;
        	tmpHead = tmpHead.next;
        }
        
        nK = k % nLen;
        
        if (nK == 0) return head;
        
        ListNode fast = head;
        ListNode slow = head;
        ListNode fastS = null;
        ListNode slowS = null;
        
        while (i<nK) {
        	fast = fast.next;
        	i++;
        }
        
        while (fast != null) {
        	fastS = fast;
        	fast = fast.next;
        	slowS = slow;
        	slow = slow.next;
        }
        
        fastS.next = head;
        slowS.next = null;
        
        return slow;
    }
	
}
