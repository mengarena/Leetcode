package com.leet;

//Given a singly linked list L: L0¡úL1¡ú¡­¡úLn-1¡úLn,
//reorder it to: L0¡úLn¡úL1¡úLn-1¡úL2¡úLn-2¡ú¡­
//
//You must do this in-place without altering the nodes' values.
//
//For example,
//Given {1,2,3,4}, reorder it to {1,4,2,3}.

public class ReorderList {

	class MyNode {
		ListNode head;
	}
	
	public ReorderList() {
		// TODO Auto-generated constructor stub
	}

	
	public void run() {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(6);
		head.next.next.next.next.next.next = new ListNode(7);
		head.next.next.next.next.next.next.next = new ListNode(8);
		head.next.next.next.next.next.next.next.next = new ListNode(9);
		head.next.next.next.next.next.next.next.next.next = null;
		
		reorderList(head);
		
		while (head != null) {
			System.out.print(head.val + ",");
			head = head.next;
		}
	}
	
	//AC
	//Strategy:
	//  1) Find the middle point by using the fast/slow pointer
	//  2) reverse the second half after the middle point
	//  3) Use two points (one from head, one from the middle point) to merge the second half into first half
    public void reorderList(ListNode head) {
    	if (head == null) return;
        ListNode lnFast = head;
        ListNode lnSlow = head;
        ListNode lnRightHalfHead = null;
        ListNode lnUnmovedTail = null;
        int nCount = 0;
        
        //Find the middle point
        while (lnFast.next != null && lnFast.next.next != null) {
        	lnSlow = lnSlow.next;
        	lnFast = lnFast.next.next;
        	nCount++;
        }
        
        if (nCount == 0) return;
        
        if (lnFast.next == null) {  //Total node number: odd; middle node don't need to change
        	lnRightHalfHead = lnSlow.next;
        	lnUnmovedTail = lnSlow;
        } else {  //total node number: even: the middle pair don't need to change
        	lnRightHalfHead = lnSlow.next.next;
        	lnUnmovedTail = lnSlow.next;
        }
        
        //ListNode right = reverse(lnRightHalfHead);   //Using this will cause stack overflow
        
        MyNode myHead = new MyNode();     //Using this part is OK

        reverseA(lnRightHalfHead, myHead);
        
        ListNode right = myHead.head;
        
        ListNode left = head;
        
        for (int i=0; i<nCount; i++) {
        	lnUnmovedTail.next = right.next;
        	
        	right.next = left.next;
        	left.next = right;
        	
        	left = left.next.next;
        	right = lnUnmovedTail.next;
        }
        
    }
    
    
    //Using this will cause stack overflow
    private ListNode reverse(ListNode root) {
    	if (root == null || root.next == null) return root;
    	
    	ListNode tmp = reverse(root.next);
    	root.next.next = root;
    	root.next = null;
    	
    	return tmp;
    }
    
    
    private ListNode reverseA(ListNode head, MyNode myHead) {
    
    	if (head.next != null && head.next.next != null) {
    		ListNode tail = reverseA(head.next.next, myHead);
    		tail.next = head.next;
    		tail = tail.next;
    		
    		tail.next = head;
    		tail = tail.next;
    		tail.next = null;
    		return tail;
    	} else if (head.next == null) {
    		myHead.head = head;
    		return head;
    	} else {
    		myHead.head = head.next;
    		ListNode newHead = head.next;
    		newHead.next = head;
    		head.next = null;
    		return head;
    	}
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //For #nNodeCount, only nNodeCount-1 operations are needed
    private ListNode reverseList(ListNode head, int nNodeCount) {
    	ListNode newHead = null;
    	ListNode myHead = head;
    	ListNode tmp = null;
    	ListNode lnPrev = null;
    	
    	if (nNodeCount == 1) return head;
    	
    	for (int i=1; i<nNodeCount; i++) {
    		tmp = myHead.next;
    		if (lnPrev == null) {
    			newHead = tmp;
    			lnPrev = tmp;
    		} else {
    			lnPrev.next = tmp;
    			lnPrev = lnPrev.next;
    		}
    		myHead.next = tmp.next;
    		tmp.next = myHead;
    	}
    	    	
    	newHead = reverseList(newHead, nNodeCount-1);
    	
    	return newHead;
    }
   
    private ListNode reversedd(ListNode head) {
        
    	if (head.next != null && head.next.next != null) {
    		ListNode newHead = head.next.next;
    		ListNode next = head.next;

    		ListNode tail = reversedd(newHead);
    		head.next = newHead;
    		tail.next = next;
    		tail = tail.next;
    		
    		tail.next = head;
    		tail = tail.next;
    		tail.next = null;
    		head = newHead;
    		return tail;
    	} else if (head.next == null) {
    		return head;
    	} else {
    		ListNode newHead = head.next;
    		newHead.next = head;
    		ListNode tail = head;
    		tail.next = null;
    		head = newHead;
    		return tail;
    	}
    	
    }
    
	
}
