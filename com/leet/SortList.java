package com.leet;

import java.util.ArrayList;
import java.util.List;

//Sort a linked list in O(n log n) time using constant space complexity.

public class SortList {

	public SortList() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		ListNode head = new ListNode(3);
		head.next = null; //new ListNode(2);
		//head.next.next = null; //new ListNode(4);
//		head.next.next.next = new ListNode(1);
//		head.next.next.next.next = new ListNode(8);
//		head.next.next.next.next.next = new ListNode(7);
//		head.next.next.next.next.next.next = new ListNode(6);
//		head.next.next.next.next.next.next.next = null;
		
		ListNode newHead = sortList(head);
		while (newHead != null) {
			System.out.print(newHead.val + ",");
			newHead = newHead.next;
		}
		
		System.out.println();

	}
	

    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        ListNode lnFast = head;
        ListNode lnSlow = head;
        
        while (lnFast.next != null && lnFast.next.next != null) {
        	lnSlow = lnSlow.next;
        	lnFast = lnFast.next.next;
        }
        
        ListNode newHead = null;
         
        if (lnFast.next == null) {
        	newHead = sortList(head, lnFast);
        } else {
        	newHead = sortList(head, lnFast.next);        	
        }
            	
    	return newHead;        
    }
    
    
    private ListNode sortList(ListNode head, ListNode tail) {
    	ListNode lnFast = head;
    	ListNode lnSlow = head;
    	
    	if (head == null || head == tail || head.next == null) return head;
    	
        while (lnFast.next != tail && lnFast.next.next != tail) {
        	lnSlow = lnSlow.next;
        	lnFast = lnFast.next.next;
        }
        
        ListNode left = null;
        ListNode right = null;

        ListNode head2 = lnSlow.next;
        
        lnSlow.next = null;
        
    	left = sortList(head, lnSlow);
    	right = sortList(head2, tail);

    	ListNode newHead = null;
    	ListNode tmpNode = null;
    	
    	//Merge
    	while (left != null && right != null) {
    	    if (left.val <= right.val) {
    	    	if (newHead == null) {
    	    		newHead = left;
    	    		tmpNode = left;
    	    	} else {
    	    		tmpNode.next = left;
    	    		tmpNode = tmpNode.next;
    	    	}
    	    	
    	    	left = left.next;
    	    } else {
    	    	if (newHead == null) {
    	    		newHead = right;
    	    		tmpNode = right;
    	    	} else {
    	    		tmpNode.next = right;
    	    		tmpNode = tmpNode.next;
    	    	}
    	    	right = right.next;
    	    }
    	}
    	
    	if (left != null) {
    		while (left != null) {
    			tmpNode.next = left;
    			tmpNode = tmpNode.next;
    			
    			left = left.next; 
    		}
    	}
    	
    	tmpNode.next = right;
    	
        return newHead;
    }
    
    
/* Works, but not time efficient	
    public ListNode sortList(ListNode head) {
        if (head == null) return null;
        List<ListNode> lstNode = new ArrayList<ListNode>();
        int i;
               
        lstNode.add(head);
        head = head.next;
        
        while (head != null) {
        	int nInsertPos = getInsertPos(lstNode, head.val);
        	lstNode.add(nInsertPos, head);
        	head = head.next;
        }
                
        ListNode newHead = lstNode.get(0);
        ListNode tmpNode = newHead;
        for (i=1; i<lstNode.size(); i++) {
        	tmpNode.next = lstNode.get(i);
        	tmpNode = tmpNode.next;
        }
        
        tmpNode.next = null;
        
        return newHead;
    }
	
    
    private int getInsertPos(List<ListNode> lstNode, int nVal) {
    	if (lstNode == null) return 0;
    	int i = 0;
    	int j = lstNode.size() - 1;
    	int nMid;
    	
    	while (i <= j) {
    		nMid = (i+j)/2;
    		
    		if (lstNode.get(nMid).val >= nVal) {
    			j = nMid - 1;
    		} else if (lstNode.get(nMid).val < nVal) {
    			i = nMid + 1;
    		}
    	}
    	
    	return i;
    }
*/    
    
}
