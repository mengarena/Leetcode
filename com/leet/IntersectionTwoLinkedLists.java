package com.leet;

//Write a program to find the node at which the intersection of two singly linked lists begins.
//
//
//For example, the following two linked lists:
//
//A:          a1 ¡ú a2
//                   ¨K
//                     c1 ¡ú c2 ¡ú c3
//                   ¨J            
//B:     b1 ¡ú b2 ¡ú b3
//begin to intersect at node c1.
//
//
//Notes:
//
//If the two linked lists have no intersection at all, return null.
//The linked lists must retain their original structure after the function returns.
//You may assume there are no cycles anywhere in the entire linked structure.
//Your code should preferably run in O(n) time and use only O(1) memory.

//Amazon, Microsoft, Bloomberg
public class IntersectionTwoLinkedLists {

	//Definition for singly-linked list.
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
			val = x;
			next = null;
		}
	}
	
	public IntersectionTwoLinkedLists() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}
	
	//The intersection part only occurs at the end. The intersection does not only occupy the middle part of each list
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    	ListNode newHeadA = headA;
    	ListNode newHeadB = headB;
    	int i;
    	
    	if (headA == null || headB == null) return null;
    	
    	int nHeadLenA = 0;
    	int nHeadLenB = 0;
    	
    	while (newHeadA != null) {
    		nHeadLenA = nHeadLenA + 1;
    		newHeadA = newHeadA.next;
    	}
    	
    	while (newHeadB != null) {
    		nHeadLenB = nHeadLenB + 1;
    		newHeadB = newHeadB.next;
    	}
 
    	newHeadA = headA;
    	newHeadB = headB;
    	
    	if (nHeadLenA > nHeadLenB) {
    		for (i=1; i<=nHeadLenA-nHeadLenB; i++) newHeadA = newHeadA.next;  
    	} else if (nHeadLenA < nHeadLenB) {
    		for (i=1; i<=nHeadLenB-nHeadLenA; i++) newHeadB = newHeadB.next;
    	}
    	
    	while (newHeadA != null) {
    		if (newHeadA.val == newHeadB.val) {
    			return newHeadA;
    		}
    		
    		newHeadA = newHeadA.next;
    		newHeadB = newHeadB.next;
    	}
    	
    	return null;
    }
	
}
