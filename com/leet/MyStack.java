package com.leet;

import java.util.LinkedList;
import java.util.Queue;

//Implement Stack using Queues

//Implement the following operations of a stack using queues.
//
//push(x) -- Push element x onto stack.
//pop() -- Removes the element on top of the stack.
//top() -- Get the top element.
//empty() -- Return whether the stack is empty.
//
//Notes:
//You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
//Depending on your language, queue may not be supported natively. 
//You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
//You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
//
//Update (2015-06-11):
//The class name of the Java function had been updated to MyStack instead of Stack.

//Bloomberg
//Easy
public class MyStack {
	
	private Queue<Integer> orgQueue = new LinkedList<Integer>();
	private Queue<Integer> backQueue = new LinkedList<Integer>();
	
    // Push element x onto stack.
    public void push(int x) {
    	orgQueue.offer(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        backQueue.clear();
        int nSize = orgQueue.size();
        
        for (int i=0; i<nSize-1; i++) backQueue.offer(orgQueue.poll());
       
        orgQueue.poll();
        
        for (int i=0; i<nSize-1; i++) orgQueue.offer(backQueue.poll());
    }

    // Get the top element.
    public int top() {
        backQueue.clear();
        int nSize = orgQueue.size();
        int nTop;
        
        for (int i=0; i<nSize-1; i++) backQueue.offer(orgQueue.poll());
       
        nTop = orgQueue.poll();
        
        for (int i=0; i<nSize-1; i++) orgQueue.offer(backQueue.poll());
        
        orgQueue.offer(nTop);
        
        return nTop; 
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return orgQueue.isEmpty();
    }

}
