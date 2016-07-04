package com.leet;

//Given a non-negative number represented as a singly linked list of digits, plus one to the number.
//
//The digits are stored such that the most significant digit is at the head of the list.
//
//Example:
//	
//Input:
//1->2->3
//
//Output:
//1->2->4


//Google
public class PlusOneLinkedList {

	public PlusOneLinkedList() {
		// TODO Auto-generated constructor stub
	}

	//ACC:  1ms
	//1) Reverse   2) Plus one   3) Reverse
    public ListNode plusOne(ListNode head) {
        ListNode newHead = reverse(head);
        ListNode tmp = newHead;
        ListNode prevTmp = null;
        int carry = 1;
        int remainedVal = 0;
        
        while (tmp != null) {
            remainedVal = tmp.val + carry;
            tmp.val = remainedVal % 10;
            carry = remainedVal / 10;
            prevTmp = tmp;
            tmp = tmp.next;
        }
        
        if (carry != 0) {
            prevTmp.next = new ListNode(carry);
            prevTmp.next.next = null;
        }
        
        ListNode newnewHead = reverse(newHead);
        return newnewHead;
    }
    
    
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode tmp = reverse(head.next);
        
        head.next.next = head;
        head.next = null;
        
        return tmp;
    }
}
