package com.leet;

//Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
//
//Note: Do not modify the linked list.
//
//Follow up:
//Can you solve it without using extra space?
//		

public class LinkedListCycleII {

	public LinkedListCycleII() {
		// TODO Auto-generated constructor stub
	}



	public void run() {
		
	}
	
	/*
	 *  x:  Steps from head to cycle head
	 *  d:	Steps from the cycle head to meeting point between slow and fast pointers
	 *  y:  Length of cycle   (==> y-d are the remaining steps from the meeting point to the cycle head)
	 *  
	 *  Slow pointer: move one step each time;  Fast pointer: move two steps each time
	 *  
	 *  First time meeting between slow and fast pointers:
	 *      S_slow = x + d;    S_fast = 2(x+d)
	 *      
	 *  S_fast - S_slow = n*y;
	 *  
	 *  ==> x+d = ny ==> x = ny - d = (y-d)  + (n-1)y 
	 *  
	 *  i.e.  After slow and fast pointers meet, if there are two pointers, one (p1) moves from the head, the other (p2) moves from the meeting point, 
	 *  after p1 arrives at the cycle head, p2 will also be there (p2 could have moved n-1 cycles before comes to this cycle head) 
	 */
    public ListNode detectCycle(ListNode head) {
    	ListNode lnCycleHead = null;
    	
		if (head == null || head.next == null) return lnCycleHead;
		
		ListNode slow = head;
		ListNode fast = head;
		ListNode newSlow = head;
		boolean bCycle = false;
		
		//First, find the meeting point between slow and fast pointers
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			
			if (slow == fast) {
				bCycle = true;
				break;
			}
		}
		
		if (bCycle == false) return null;
		
		//Move two pointer, one from the meeting point, the other from the beginning, when these two pointer meet, it will be the cycle head.
		while (slow != newSlow) {
			slow = slow.next;
			newSlow = newSlow.next;
		}
	
    	return newSlow;
    }
	
	
}
