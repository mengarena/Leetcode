package com.leet;

//Given a linked list, determine if it has a cycle in it.
//
//Follow up:
//Can you solve it without using extra space?

//Bloomberg
public class LinkedListCycle {

	public LinkedListCycle() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		
	}
	

	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) return false;
		
		ListNode slow = head;
		ListNode fast = head;
		boolean bCycle = false;
		
		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			
			if (slow == fast) {
				bCycle = true;
				break;
			}
		}
		
		return bCycle;
    }
	
}
