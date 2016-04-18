package com.leet;

import java.util.Comparator;
import java.util.PriorityQueue;

//Median is the middle value in an ordered integer list. 
//If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
//
//Examples: 
//[2,3,4] , the median is 3
//
//[2,3], the median is (2 + 3) / 2 = 2.5
//
//Design a data structure that supports the following two operations:
//
//void addNum(int num) - Add a integer number from the data stream to the data structure.
//double findMedian() - Return the median of all elements so far.
//
//For example:
//
//add(1)
//add(2)
//findMedian() -> 1.5
//add(3) 
//findMedian() -> 2


//Google
public class FindMedianFromDataStream {

	public FindMedianFromDataStream() {
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		addNum(-1); System.out.println(findMedian());
		addNum(-2); System.out.println(findMedian());
		addNum(-3); System.out.println(findMedian());
		addNum(-4); System.out.println(findMedian());
		addNum(-5); System.out.println(findMedian());
	}
	

	//ACC:  56%
	private PriorityQueue<Integer> quLarge = new PriorityQueue<Integer>();  //Smallest on top
	
	private PriorityQueue<Integer> quSmall = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
    	public int compare(Integer n1, Integer n2) {    
    		return n2 - n1;    //Largest on top
    	}		
	});
		
	
    // Adds a number into the data structure.
    public void addNum(int num) {
    	if (quLarge.size() == 0) {
    		quLarge.offer(num);
    		return;
    	} 
    	
    	if (quSmall.size() == 0) {
    		if (num <= quLarge.peek()) {
    			quSmall.offer(num);
    		} else {
    			quSmall.offer(quLarge.poll());
    			quLarge.offer(num);
    		}
    		
    		return;
    	}
    	
    	if (num >= quLarge.peek()) {
    		if (quLarge.size() - quSmall.size() == 0) {
    			quLarge.offer(num);
    		} else {  //quLarge.size() - quSmall.size() == 1
    			quSmall.offer(quLarge.poll());
    			quLarge.offer(num);
    		}
    	} else if (num >= quSmall.peek()) {
    		if (quLarge.size() - quSmall.size() == 0) {
    			quLarge.offer(num);
    		} else {  //quLarge.size() - quSmall.size() == 1
    			quSmall.offer(num);
    		}
    		
    	} else if (num < quSmall.peek()) {
    		if (quLarge.size() - quSmall.size() == 0) {
    			quLarge.offer(quSmall.poll());
    			quSmall.offer(num);
    		} else {  //quLarge.size() - quSmall.size() == 1
    			quSmall.offer(num);
    		}
    	}
    	
    }

    // Returns the median of current data stream
    public double findMedian() {
    	if (quLarge.isEmpty()) return 0;
    	
        if (quLarge.size() > quSmall.size()) {
        	return quLarge.peek();
        } else {
        	return (quLarge.peek() + quSmall.peek())/2.0;
        }
    }
};

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();

