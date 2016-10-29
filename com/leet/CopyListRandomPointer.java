package com.leet;

import java.util.HashMap;
import java.util.Map;

//A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
//
//Return a deep copy of the list.


//Uber, Microsoft, Amazon, Bloomberg
//Hard
public class CopyListRandomPointer {

	class RandomListNode {
		 int label;
		 RandomListNode next, random;
		 RandomListNode(int x) { this.label = x; }
    };
		 
	public CopyListRandomPointer() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}
	
	//AC:  Faster than map copy
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        RandomListNode newHead = null;
        RandomListNode tmpNode = null;
        RandomListNode copiedNode = null;
        
        //For a new linked list like:
        //A->A'->B->B'->C->C'->NULL (X' is new created node for the resultant list)
        tmpNode = head;
        while (tmpNode != null) {
            copiedNode = new RandomListNode(tmpNode.label);
            copiedNode.next = tmpNode.next;
            tmpNode.next = copiedNode;
            tmpNode = tmpNode.next.next;
        }
        
        //Set random for the new nodes
        //If A random points to C; then A' random points to C' (i.e. the next of random of A)
        tmpNode = head;
        while (tmpNode != null) {
            if (tmpNode.random != null) {
                tmpNode.next.random = tmpNode.random.next;  
                //tmpNode.next is new created node; tmpNode.random.next is also new created node
            }
            
            tmpNode = tmpNode.next.next;
        }
        
        
        newHead = head.next;
        tmpNode = newHead;
        
        //Detach the two linked list
        //Tail looks like:  -->X->X'->NULL
        while (tmpNode.next != null) {
            head.next = head.next.next;
            head = head.next;
            
            tmpNode.next = head.next;
            tmpNode = tmpNode.next;
        }
        
        //Important for the tail
        head.next = null;
        
        return newHead;
    }

	
	
	//AC:   Deep copy map
    public RandomListNode copyRandomListA(RandomListNode head) {
        if (head == null) return null;
        Map<RandomListNode, RandomListNode> hmRL = new HashMap<RandomListNode, RandomListNode>();   //Old node, New node
        
        RandomListNode tmp = head;

        //Create all nodes
        while (tmp != null) {
            hmRL.put(tmp, new RandomListNode(tmp.label));
            tmp = tmp.next;
        }
        
        //Set up relationship
        for (Map.Entry<RandomListNode, RandomListNode> entry:hmRL.entrySet()) {
            entry.getValue().next = hmRL.get(entry.getKey().next);
            entry.getValue().random = hmRL.get(entry.getKey().random);
        }
        
        return hmRL.get(head);

    }
	
}
