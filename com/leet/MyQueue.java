package com.leet;

import java.util.Stack;

//Implement Queue using Stacks

//Implement the following operations of a queue using stacks.
//
//push(x) -- Push element x to the back of queue.
//pop() -- Removes the element from in front of queue.
//peek() -- Get the front element.
//empty() -- Return whether the queue is empty.
//
//Notes:
//You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
//Depending on your language, stack may not be supported natively. 
//You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
//You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).


//Microsoft, Bloomberg
//Easy
public class MyQueue {

	Stack<Integer> skOrg = new Stack<Integer>();
	Stack<Integer> skBack = new Stack<Integer>();
	
    // Push element x to the back of queue.
    public void push(int x) {
    	skOrg.push(x);    	
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (skOrg.isEmpty() == true) return;
        
        skBack.clear();
        
        while (!skOrg.isEmpty()) {
        	skBack.push(skOrg.pop());
        }
        
        skBack.pop();
        
        while (!skBack.isEmpty()) {
        	skOrg.push(skBack.pop());
        }
    }

    // Get the front element.
    public int peek() {
        int nRet;
        
        skBack.clear();
        
        while (!skOrg.isEmpty()) {
        	skBack.push(skOrg.pop());
        }
        
        nRet = skBack.peek();
        
        while (!skBack.isEmpty()) {
        	skOrg.push(skBack.pop());
        }
        
        return nRet;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        boolean bEmpty = skOrg.isEmpty();
        
        return bEmpty;
    }

    
//    public void push(int x) {
//        if (skOrg.isEmpty() == true) {
//        	skOrg.push(x);
//        } else {
//        	skBack.clear();
//        	
//        	while (!skOrg.isEmpty()) {
//        		skBack.push(skOrg.pop());
//        	}
//        	
//        	skOrg.push(x);
//        	while (!skBack.isEmpty()) {
//        		skOrg.push(skBack.pop());
//        	}
//        }
//    }
    
    
}
