package com.leet;

//Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
//
//You should preserve the original relative order of the nodes in each of the two partitions.
//
//For example,
//Given 1->4->3->2->5->2 and x = 3,
//return 1->2->2->4->3->5.

public class PartitionList {

	public PartitionList() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		ListNode head = new ListNode(1);
		head.next = null; //new ListNode(1);
		//head.next.next = null; //new ListNode(3);
		//head.next.next.next = null; //new ListNode(2);
		//head.next.next.next.next = new ListNode(5);
		//head.next.next.next.next.next = new ListNode(2);
		//head.next.next.next.next.next.next = null;
		
		int x = 0;
		
		ListNode newHead = partition(head, x);
		
		while (newHead != null) {
			System.out.print(newHead.val + ",");
			newHead = newHead.next;
		}
	}
	
	
    public ListNode partition(ListNode head, int x) {
        ListNode newHead = null;
        ListNode lnSmall = null;
        ListNode lnLarge = null;
        ListNode lnLargeHead = null;
        
        while (head != null) {
        	if (head.val < x) {
        		if (newHead == null) {
        			newHead = head;
        			lnSmall = newHead;
        		} else {
        			lnSmall.next = head;
        			lnSmall = lnSmall.next;
        		}
        	} else {
        		if (lnLargeHead == null) {
        			lnLargeHead = head;
        			lnLarge = lnLargeHead;
        		} else {
        			lnLarge.next = head;
        			lnLarge = lnLarge.next;
        		}
        	}
        	
        	head = head.next;
        }
        
        if (lnLarge != null) lnLarge.next = null;
        
        if (newHead == null) {
            newHead = lnLargeHead;
        } else {
            lnSmall.next = lnLargeHead;
        }

        return newHead;
    }
	
}
