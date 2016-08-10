package com.leet;

//Given a singly linked list, return a random node's value from the linked list. 
//Each node must have the same probability of being chosen.
//
//Follow up:
//What if the linked list is extremely large and its length is unknown to you? 
//Could you solve this efficiently without using extra space?
//
//Example:
//
//// Init a singly linked list [1,2,3].
//ListNode head = new ListNode(1);
//head.next = new ListNode(2);
//head.next.next = new ListNode(3);
//Solution solution = new Solution(head);
//
//// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
//solution.getRandom();

//Google
public class LinkedListRandomNode {

	public LinkedListRandomNode() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC:
	//Strategy:  Reservoir sampling  https://en.wikipedia.org/wiki/Reservoir_sampling
    private ListNode mHead = null;
    java.util.Random mRd = new java.util.Random();
    
    /** @param head The linked list's head. Note that the head is guanranteed to be not null, so it contains at least one node. */
    public LinkedListRandomNode(ListNode head) {  //The name in the question is: Solution
        mHead = head;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode cur = mHead;
        int size = 1;
        ListNode ret = mHead;
        
        while (cur != null) {
            if (mRd.nextInt(size) == 0) {   // = 0, mean probability 1/i, and select this one
                ret = cur;
            }
            
            size++;
            cur = cur.next;
        }
        
        return ret.val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */

