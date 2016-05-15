package com.leet;

import java.util.LinkedList;
import java.util.Queue;
// Moving Average from Data Stream

//Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
//
//For example,
//MovingAverage m = new MovingAverage(3);
//m.next(1) = 1
//m.next(10) = (1 + 10) / 2
//m.next(3) = (1 + 10 + 3) / 3
//m.next(5) = (10 + 3 + 5) / 3

//Easy
//Google
public class MovingAverage {

	private Queue<Integer> myQu = new LinkedList<Integer>();
	private int size = 0;
	private int sum = 0;
	
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
    }
    
    public double next(int val) {
        if (myQu.size() < size) {
        	myQu.offer(val);
        	sum += val;
        } else {
        	sum = sum - myQu.remove();
        	sum += val;
        }
        
        return sum*1.0/size;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
