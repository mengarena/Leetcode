package com.leet;

//You are given two linked lists representing two non-negative numbers. 
//The digits are stored in reverse order and each of their nodes contain a single digit. 
//Add the two numbers and return it as a linked list.
//
//Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//Output: 7 -> 0 -> 8

public class AddTwoNumbers {

	class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
	}
	
	public AddTwoNumbers() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		ListNode l1 = new ListNode(9);
		l1.next = new ListNode(8);
		l1.next.next = null;
		
		ListNode l2 = new ListNode(1);
		l2.next = null;
		
		ListNode lstSum = addTwoNumbers(l1, l2);
		
		String sSum = "";
		
		while (lstSum != null) {
			if (sSum.length() == 0) {
				sSum = sSum + lstSum.val;
			} else {
				sSum = sSum + "->" + lstSum.val;
			}
			
			lstSum = lstSum.next;
		}
		
		System.out.println("Sum:  " + sSum);
		
		return;
	}
	

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	int nSum = 0;
    	ListNode lstNodeHead = null;
    	ListNode lstNode = null;
    	int nOverflow = 0;
    	
    	if (l1 == null) return l2;
    	if (l2 == null) return l1;
    	
    	nSum = l1.val + l2.val;
    	if (nSum >= 10) {
    		lstNode = new ListNode(nSum % 10);
    		nOverflow = 1;
    	} else {
    		lstNode = new ListNode(nSum);
    	}

		lstNode.next = null;
    	
    	lstNodeHead = lstNode;
    	
    	l1 = l1.next;
    	l2 = l2.next;
    	
    	while (l1 != null && l2 != null) {
        	nSum = l1.val + l2.val + nOverflow;
        	if (nSum >= 10) {
        		lstNode.next = new ListNode(nSum % 10);
        		lstNode = lstNode.next;
        		lstNode.next = null;
        		nOverflow = 1;
        	} else {
        		lstNode.next = new ListNode(nSum);
        		lstNode = lstNode.next;
        		lstNode.next = null;
        		nOverflow = 0;
        	}

        	l1 = l1.next;
        	l2 = l2.next;
    	}
    	    	
    	
        while (l1 != null) {
        	nSum = l1.val + nOverflow;

        	if (nSum >= 10) {
        		lstNode.next = new ListNode(nSum % 10);
        		lstNode = lstNode.next;
        		lstNode.next = null;
        		nOverflow = 1;
        	} else {       		
        		lstNode.next = new ListNode(nSum);
        		lstNode.next.next = l1.next;   //Don't need to calculate anymore, just add the remaining part of l1
        		nOverflow = 0;
        		break;        		
        	}

        	l1 = l1.next;
        }
    	
        while (l2 != null) {
        	nSum = l2.val + nOverflow;

        	if (nSum >= 10) {
        		lstNode.next = new ListNode(nSum % 10);
        		lstNode = lstNode.next;
        		lstNode.next = null;
        		nOverflow = 1;
        	} else {
        		lstNode.next = new ListNode(nSum);
        		lstNode.next.next = l2.next;  //Don't need to calculate anymore, just add the remaining part of l2
        		nOverflow = 0;
        		break;
        		
        	}

        	l2 = l2.next;
        }
        
		if (nOverflow > 0) {
			lstNode.next = new ListNode(nOverflow);
			lstNode.next.next = null;
		}
       
        
    	return lstNodeHead;
    }
	
	
//	lstNode = lstNode.next;
//	lstNode.next = null;
//	nOverflow = 0;

    
    public ListNode addTwoNumbersAA(ListNode l1, ListNode l2) {
    	int nSum = 0;
    	ListNode lstNodeHead = null;
    	ListNode lstNode = null;
    	int nDigitCnt = 0;
    	int nNum1 = 0;
    	int nNum2 = 0;
    	int nRemainder = 0;
    	int nQuotient = 0;
    	
    	while (l1 != null) {
    		nNum1 = nNum1 + l1.val*(int) Math.pow(10, nDigitCnt);
    		nDigitCnt = nDigitCnt + 1;
    		l1 = l1.next;
    	}
    	
    	nDigitCnt = 0;
    	
    	while (l2 != null) {
    		nNum2 = nNum2 + l2.val*(int) Math.pow(10, nDigitCnt);
    		
    		if (Integer.MAX_VALUE - nNum2 < nNum1) return null;
    		
    		nDigitCnt = nDigitCnt + 1;
    		l2 = l2.next;
    	}
    	
    	nSum = nNum1 + nNum2;
    	
    	nQuotient = nSum / 10;
    	nRemainder = nSum % 10;
    	
    	lstNode = new ListNode(nRemainder);
    	lstNode.next = null;
    	lstNodeHead = lstNode;
    	
    	while (nQuotient >= 10) {
    		nRemainder = nQuotient % 10;
    		nQuotient = nQuotient / 10;
    		
    		lstNode.next = new ListNode(nRemainder);
    		lstNode = lstNode.next;
    		lstNode.next = null;
    	}
    	
    	if (nQuotient > 0) {
    		lstNode.next = new ListNode(nQuotient);
    		lstNode.next.next = null;
    		
    	}
    	
    	return lstNodeHead;
    }
	
}
