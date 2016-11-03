package com.leet;

import java.util.PriorityQueue;

//Design a hit counter which counts the number of hits received in the past 5 minutes.
//
//Each function accepts a timestamp parameter (in seconds granularity) and 
//you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). 
//You may assume that the earliest timestamp starts at 1.
//
//It is possible that several hits arrive roughly at the same time.
//
//Example:
//	
//HitCounter counter = new HitCounter();
//
//// hit at timestamp 1.
//counter.hit(1);
//
//// hit at timestamp 2.
//counter.hit(2);
//
//// hit at timestamp 3.
//counter.hit(3);
//
//// get hits at timestamp 4, should return 3.
//counter.getHits(4);
//
//// hit at timestamp 300.
//counter.hit(300);
//
//// get hits at timestamp 300, should return 4.
//counter.getHits(300);
//
//// get hits at timestamp 301, should return 3.
//counter.getHits(301); 
//
//Follow up:
//What if the number of hits per second could be very large? Does your design scale?

//--- means: many hits has same timestamp, need to remember the count of hit for each timestamp, the solution below does not scale


//Google, Dropbox		
public class HitCounter {

	//ACC: 78%
    private PriorityQueue<Integer> pq = null;
    
    /** Initialize your data structure here. */
    public HitCounter() {
        pq = new PriorityQueue<Integer>();
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        pq.offer(timestamp);
        
        while (timestamp - pq.peek() >= 300) pq.poll(); 
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!pq.isEmpty() && timestamp - pq.peek() >= 300) pq.poll();
        return pq.size();
    }
}


//ACC:  Scalable
public class HitCounter {
    private int[] times;
    private int[] hits;
    /** Initialize your data structure here. */
    public HitCounter() {
        times = new int[300];
        hits = new int[300];
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int index = timestamp % 300;
        if (times[index] != timestamp) {
            times[index] = timestamp;
            hits[index] = 1;
        } else {
            hits[index]++;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int total = 0;
        for (int i = 0; i < 300; i++) {
            if (timestamp - times[i] < 300) {
                total += hits[i];
            }
        }
        return total;
    }
}






/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */