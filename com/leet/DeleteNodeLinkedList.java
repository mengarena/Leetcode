package com.leet;

//Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
//
//Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, 
//the linked list should become 1 -> 2 -> 4 after calling your function.

//Apple,Adobe,Microsoft
//Easy
public class DeleteNodeLinkedList {

	//Definition for singly-linked list.
	public class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) { val = x; }
	}
	 
	public DeleteNodeLinkedList() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}

    public void deleteNode(ListNode node) {
        if (node == null) return;
        
        if (node.next == null) {
        	node = null;
        	return;
        }
        
        node.val = node.next.val;
        node.next = node.next.next;
    }	

}
