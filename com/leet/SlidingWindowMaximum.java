package com.leet;

import java.util.Deque;
import java.util.LinkedList;

//Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
//You can only see the k numbers in the window. Each time the sliding window moves right by one position.
//
//For example,
//Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
//
//Window position                Max
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
//Therefore, return the max sliding window as [3,3,5,5,6,7].
//
//Note: 
//You may assume k is always valid, ie: 1 ¡Ü k ¡Ü input array's size for non-empty array.
//
//Follow up:
//Could you solve it in linear time?
//
//Hint:
//
//How about using a data structure such as deque (double-ended queue)?
//The queue size need not be the same as the window¡¯s size.
//Remove redundant elements and the queue should store only elements that need to be considered.


public class SlidingWindowMaximum {

	public SlidingWindowMaximum() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//int[] nums = {1,3,-1,-3,5,3,6,7};
		int[] nums = {9,8,7,6,5,4,3,2,1,0};
		int k = 3;
		int[] maxWins = maxSlidingWindow(nums, k);
		for (int i=0; i<maxWins.length; i++) System.out.print(maxWins[i] + ",");
		System.out.println();
	}
	
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];
        int n = nums.length;
        int i;
        Deque<Integer> dq = new LinkedList<Integer>();
        Deque<Integer> dqIdx = new LinkedList<Integer>();
        
        dq.push(nums[0]);
        dqIdx.push(0);
        
        for (i=1; i<Math.min(k, n); i++) {
            if (nums[i] <= dq.peekLast()) {
            	dq.addLast(nums[i]);
            	dqIdx.addLast(i);
            } else {
            	while (!dq.isEmpty() && nums[i] > dq.peekLast()) {
            		dq.pollLast();
            		dqIdx.pollLast();
            	}
            	
            	dq.addLast(nums[i]);
            	dqIdx.addLast(i);
            }
        }
        
        if (n <= k) {
        	int[] carrRet = new int[1];
        	carrRet[0] = dq.pollFirst();
        	return carrRet;
        }
        
        int[] carrRet = new int[n-k+1];
        int nHeadIdx = 0;
        
        carrRet[0] = dq.peekFirst();
        
        for (i=k; i<n; i++) {
        	nHeadIdx = dqIdx.peekFirst();
        	if (i-nHeadIdx+1 > k) {
        		dqIdx.removeFirst();
        		dq.removeFirst();
        	}
        	
        	while (!dq.isEmpty() && nums[i] > dq.peekLast()) {
        		dq.pollLast();
        		dqIdx.pollLast();
        	}
        	
        	dq.addLast(nums[i]);
        	dqIdx.addLast(i);
        	
        	carrRet[i-(k-1)] = dq.peekFirst();
        }
        
        return carrRet;
    }	
}
