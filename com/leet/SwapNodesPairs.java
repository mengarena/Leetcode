package com.leet;

//Given a linked list, swap every two adjacent nodes and return its head.
//
//For example,
//Given 1->2->3->4, you should return the list as 2->1->4->3.
//
//Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.

//Microsoft, Bloomberg
public class SwapNodesPairs {

	public SwapNodesPairs() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = null; //new ListNode(4);
		//head.next.next.next.next = null;
		
		ListNode swappedHead = swapPairs(head);
		
		while (swappedHead != null) {
			System.out.print(swappedHead.val + ",");
			swappedHead = swappedHead.next;
		}
	}
	

    public ListNode swapPairs(ListNode head) {
        ListNode lnSwapHead = null;
        
        if (head == null) return null;
        if (head.next == null) return head;
        
        ListNode lnPrePrev = head.next;
        ListNode lnPrev = head;
        ListNode lnPost = head.next;
        
        while (lnPost != null) {
        	if (lnSwapHead == null) {
        		lnSwapHead = lnPost;
        	} else {
        		lnPrePrev.next = lnPost;
        	}
        	
        	lnPrev.next = lnPost.next;
    		lnPost.next = lnPrev;

    		
    		lnPrePrev= lnPost.next;
    		lnPrev = lnPost.next.next;
    		if (lnPrev != null) {
    			lnPost = lnPrev.next;
    		} else {
    			lnPost = null;
    		}
        }
        
        return lnSwapHead;
    }
	
}
