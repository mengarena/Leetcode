package com.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
//
//push(x) -- Push element x onto stack.
//pop() -- Removes the element on top of the stack.
//top() -- Get the top element.
//getMin() -- Retrieve the minimum element in the stack.

//Google, Uber, Amazon, Bloomberg, Zenefits
public class MinStack {

	//ACC
    private Stack<Integer> onlyMin = null;
    private Stack<Integer> normal = null;
    
    /** initialize your data structure here. */
    public MinStack() {
        onlyMin = new Stack<Integer>();
        normal = new Stack<Integer>();
    }
    
    public void push(int x) {
        if (onlyMin.isEmpty()) {
            onlyMin.push(x);
            normal.push(x);
        } else {
            if (x <= onlyMin.peek()) {
                onlyMin.push(x);
            } 
            
            normal.push(x);
        }
    }
    
    public void pop() {
        if (onlyMin.isEmpty()) return;
        
        //Here, must add "intValue()", otherwise, it compares two Integer objects, although their numerical values are the same, the two object is not the same
        if (onlyMin.peek().intValue() == normal.peek().intValue()) {   
            onlyMin.pop();
            normal.pop();
        } else {
            normal.pop();
        }
    }
    
    public int top() {
        return normal.peek();
    }
    
    public int getMin() {
        if (onlyMin.isEmpty()) return 0;
        return onlyMin.peek();
    }


}



/*  ACC:
 public class MinStack {

	private Stack<Integer> minStk = new Stack<Integer>();
	private List<Integer> minLst = new ArrayList<Integer>();    //Save the minimal value at the moment

    public void push(int x) {
        minStk.push(x);
        
        if (minLst.isEmpty()) {
        	minLst.add(x);
        } else {
        	if (minLst.get(0) >= x) minLst.add(0, x);
        }
    }

    public void pop() {
    	if (minStk.isEmpty() == false) {    		
    		int nVal = minStk.pop();
    		if (nVal == minLst.get(0)) {
    			minLst.remove(0);
    		}
    	}
    }

    public int top() {
    	return minStk.peek();
    }

    public int getMin() {
        return minLst.get(0);
    }

}
 */
