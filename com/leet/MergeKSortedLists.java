package com.leet;

import java.util.Comparator;
import java.util.PriorityQueue;

//Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.


//Google, Facebook, Linkedin, Uber, Airbnb, Twitter, Amazon, Microsoft
public class MergeKSortedLists {

	public MergeKSortedLists() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		ListNode ln = new ListNode(1);
		ListNode[] lists = new ListNode[1];
		lists[0] = ln;
		
		ListNode mergedLn = mergeKLists(lists);
		
		System.out.println();
	}
	

	//ACC: 24%
	//
	//Time complexity:  m*logn  (m = total number of node;  n = number of lists)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int n = lists.length;
        int i;
        ListNode mergedLN = null;
        ListNode mergedPtr = null;
        
        PriorityQueue<ListNode> qu = new PriorityQueue<ListNode>(11, new Comparator<ListNode>() {
        	public int compare(ListNode ln1, ListNode ln2) {
        		return ln1.val - ln2.val;
        	}
        });
        
        
        //Time Complexity:  n
        for (i=0; i<n; i++) {
        	ListNode tmp = lists[i];
        	
        	//Only put the head of the list into queue,  NOT the whole list, in this way, the priorityQueue (i.e. the tree) could be small
        	//Other node of each list will be put in later in the while loop below
        	if (tmp != null) qu.offer(tmp);   //If don't use this, use below, time out
        	
        	//while (tmp != null) {    //If use this, will timeout:  Reason:   The tree will be too big and too deep, it will consuse much more time to maintain (update)
        	//	qu.offer(tmp);
        	//	tmp = tmp.next;
        	//}
        }
        
        
        //Time Complexity:  m * lgn   (m is the number of total node)
        while (!qu.isEmpty()) {
        	if (mergedPtr == null) {
        		mergedPtr = qu.poll();
        		mergedLN = mergedPtr;
        	} else {
        		mergedPtr.next = qu.poll();
        		mergedPtr = mergedPtr.next;
        	}
        	
        	//Get next node from the corresponding list and put into queue
        	if (mergedPtr.next != null) qu.offer(mergedPtr.next);   //If don't use this, use the above commented part, time out
        }
        
        return mergedLN;
    }
	
	
    
    
	//Exceed time limit
    public ListNode mergeKListsA(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int n = lists.length;
        int i;
        ListNode mergedLN = null;
        ListNode mergedPtr = null;
        
        PriorityQueue<ListNode> qu = new PriorityQueue<ListNode>(11, new Comparator<ListNode>() {
        	public int compare(ListNode ln1, ListNode ln2) {
        		return ln1.val - ln2.val;
        	}
        });
        
        for (i=0; i<n; i++) {
        	ListNode tmp = lists[i];
        	
        	while (tmp != null) {
        		qu.offer(tmp);
        		tmp = tmp.next;
        	}
        }
        
        while (!qu.isEmpty()) {
        	if (mergedPtr == null) {
        		mergedPtr = qu.poll();
        		mergedLN = mergedPtr;
        	} else {
        		mergedPtr.next = qu.poll();
        		mergedPtr = mergedPtr.next;
        	}
        }
        
        return mergedLN;
    }	
	
    
    
/*	
	//Works, but Exceeded time limit
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int n = lists.length
        int i;
        ListNode mergedLN = null;
        ListNode mergedPtr = null;
        int nullCnt = 0;
    	
    	int minVal = Integer.MAX_VALUE;
    	int minIdx = -1;
    	
        while (true) {
        	nullCnt = 0;
        	minVal = Integer.MAX_VALUE;
        	minIdx = -1;
        	
	        for (i=0; i<n; i++) {        	
	        	if (lists[i] == null) {
	        		nullCnt++;           
	        		continue;
	        	}
	        	
	        	if (minVal > lists[i].val) {
	        		minVal = lists[i].val;
	        		minIdx = i;
	        	}
	        }
	        
            if (nullCnt+1 == n || nullCnt == n) {
            	break;
            }
            
            if (mergedPtr == null) {
            	mergedPtr = lists[minIdx];
            	mergedLN = mergedPtr;
            } else {
            	mergedPtr.next = lists[minIdx];
            	mergedPtr = mergedPtr.next;
            }
            
            lists[minIdx] = lists[minIdx].next;
            
        }
        
        if (nullCnt + 1 == n) {
        	if (mergedPtr == null) {
        		mergedPtr = lists[minIdx];
        		mergedLN = mergedPtr;
        	} else {
        	    mergedPtr.next = lists[minIdx];
        	}
        }
        
        return mergedLN;
    }
*/
    
}
