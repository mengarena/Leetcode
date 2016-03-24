package com.leet;

//Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
//
//If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
//
//You may not alter the values in the nodes, only nodes itself may be changed.
//
//Only constant memory is allowed.
//
//For example,
//Given this linked list: 1->2->3->4->5
//
//For k = 2, you should return: 2->1->4->3->5
//
//For k = 3, you should return: 3->2->1->4->5

public class ReverseNodesKGroup {

	public ReverseNodesKGroup() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		ListNode root = new ListNode(1);
		root.next = new ListNode(2);
//		root.next.next = new ListNode(3);
//		root.next.next.next = new ListNode(4);
//		root.next.next.next.next = new ListNode(5);
//		root.next.next.next.next.next = new ListNode(6);
//		root.next.next.next.next.next.next = new ListNode(7);
//		root.next.next.next.next.next.next.next = new ListNode(8);
//		root.next.next.next.next.next.next.next.next = new ListNode(9);
//		root.next.next.next.next.next.next.next.next.next = new ListNode(10);
		
		ListNode newRoot = reverseKGroup(root, 3);
		while (newRoot != null) {
			System.out.print(newRoot.val + "-->");
			newRoot = newRoot.next;
		}
		System.out.println();
	}
	
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        int nCount = 0;
        ListNode tmp = head;
        ListNode newHead = null;
        ListNode lastForReverse = null;
        ListNode remained = null;
        int nTmpCount = 0;
        
        //Find number of group to be reversed and also the head point of the remained part (the remained part does not need to be reversed)
        while (tmp != null) {
        	nTmpCount++;
        	if (nTmpCount % k == 0) {
        		nCount++;
        		nTmpCount = 0;
        		lastForReverse = tmp;
        		remained = tmp.next;
        	}
        	
        	tmp = tmp.next;
        }
        
        if (nCount == 0) return head;
        
        //Separate the original list into two part: to be reversed part (which has nCount groups) and remained part
        lastForReverse.next = null;
        
        //Reverse the whole part which should be reversed
        newHead = reverseList(head);
        
        //Here below, move the reversed groups to the head
        ListNode groupTail = null;
        ListNode groupTailN = null;   //Tail of next group (i.e. the group to be moved)
        tmp = newHead;
        
        for (int j=1; j<=k; j++) {
        	groupTail = tmp;
        	tmp = tmp.next;
        }
        
        //Move nCount-1 groups to the head
        for (int i=2; i<=nCount; i++) {
        	 groupTailN = tmp;
        	 for (int j=1; j<k; j++) groupTailN = groupTailN.next;
        	 
        	 tmp = groupTail.next;
        	 groupTail.next = groupTailN.next;
        	 groupTailN.next = newHead;
        	 newHead = tmp;
        	 
        	 tmp = groupTail.next;
        }
        
        //Attach the remained part
        groupTail.next = remained;
        return newHead;
    }
    
    
    private ListNode reverseList(ListNode root) {
    	if (root == null || root.next == null) return root;
    	
    	ListNode tmp = reverseList(root.next);
    	root.next.next = root;
    	root.next = null;
    	return tmp;
    }
	
}
