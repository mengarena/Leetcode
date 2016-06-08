package com.leet;

//Given a singly linked list, group all odd nodes together followed by the even nodes. 
//Please note here we are talking about the node number and not the value in the nodes.
//
//You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
//
//Example:
//Given 1->2->3->4->5->NULL,
//return 1->3->5->2->4->NULL.
//
//Note:
//The relative order inside both the even and odd groups should remain as it was in the input. 
//The first node is considered odd, the second node even and so on ...

public class OddEvenLinkedList {

	public OddEvenLinkedList() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		ListNode head = new ListNode(2);
		head.next = new ListNode(5);
		head.next.next = new ListNode(4);
		head.next.next.next = null; //new ListNode(1);
//		head.next.next.next.next = new ListNode(3);
//		head.next.next.next.next.next = new ListNode(7);
//		head.next.next.next.next.next.next = null;
		
		ListNode newHead = oddEvenList(head);
		while (newHead != null) {
			System.out.print(newHead.val + ",");
			newHead = newHead.next;
		}
	
	}
	

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode lnOdd = head;
        ListNode lnEven = head.next;
        ListNode newHead = head;
        ListNode evenHead = lnEven;
        ListNode tmp = head.next.next;
        int cnt = 2;
        
        while (tmp != null) {
            cnt++;
            
            if (cnt % 2 == 1) {
                lnOdd.next = tmp;
                lnOdd = lnOdd.next;
            } else {
                lnEven.next = tmp;
                lnEven = lnEven.next;
            }
            
            tmp = tmp.next;
        }
        
        lnEven.next = null;
        lnOdd.next = evenHead;
        return newHead;
    }	
	
    
	//ACC
    public ListNode oddEvenListA(ListNode head) {
        if (head == null) return null;
        ListNode newHead = head;
        ListNode lnPointOdd = head;
        ListNode lnPointEven = null;
        ListNode lnPointEvenHead = null;
        
        //Form one odd link, one even link
        while (lnPointOdd != null && lnPointOdd.next != null) {
        	if (lnPointEvenHead == null) {
        		lnPointEvenHead = lnPointOdd.next;
        		lnPointEven = lnPointEvenHead;
        	} else {
        		lnPointEven.next = lnPointOdd.next;
        		lnPointEven = lnPointEven.next;
        	}
        	
        	if (lnPointOdd.next.next != null) {
        		lnPointOdd.next = lnPointOdd.next.next;
        		lnPointOdd = lnPointOdd.next;
        	} else {
        		break;
        	}
        	
        }
        
        //Attch even link to the end of odd link
        if (lnPointOdd == null) {
        	lnPointOdd = lnPointEvenHead;
        	if (lnPointEven != null) lnPointEven.next = null;
        } else if (lnPointOdd.next == null || lnPointOdd.next.next == null) {
        	lnPointOdd.next = lnPointEvenHead;
        	if (lnPointEven != null) lnPointEven.next = null;
        }
        
        return newHead;
    }

}
