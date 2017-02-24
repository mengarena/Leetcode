/*
Median is the middle value in an ordered integer list. 
If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. 
Each time the sliding window moves right by one position. 
Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
 
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

*/

//Hard
//Google

public class Solution {
	
	//ACC
	//Strategy: Use a maxHeap and a minHeap to remember half of the window (if k is odd, minHeap has one more number than maxHeap)
	//The median will be the top element of minHeap (if k is odd), or the mean of the two top elements of maxHeap and minHeap
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return new double[0];
        int n = nums.length;
        boolean bSingle = false;

        if (k == 1) {
            double[] ans = new double[n];
            for (int i=0; i<n; i++) ans[i] = nums[i];
            return ans;
        }
        
        int minCount = 0;
        int maxCount = k/2;
        
        if (k % 2 == 0) {
            minCount = k/2;
        } else {
            minCount = k/2+1;
            bSingle = true;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(minCount);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(maxCount, Collections.reverseOrder());
        
        double[] ans = new double[n-k+1];
        int i;
        
        //Process the first window
        for (i=0; i<minCount; i++) minHeap.offer(nums[i]);
        for (i=0; i<maxCount; i++) {
            if (nums[i+minCount] > minHeap.peek()) {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(nums[i+minCount]);
            } else {
                maxHeap.offer(nums[i+minCount]);
            }
        }
        
        if (bSingle) {
            ans[0] = minHeap.peek(); 
        } else {
            ans[0] = minHeap.peek()/2.0 + maxHeap.peek()/2.0;   //Write like this to avoid overflow
        }
        
        //Process the remaining windoww
        for (i=k; i<n; i++) {
            if (minHeap.contains(nums[i-k])) {
                minHeap.remove(nums[i-k]);
                if (maxHeap.peek() > nums[i]) {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(nums[i]);
                } else {
                    minHeap.offer(nums[i]);
                }
            } else {
                maxHeap.remove(nums[i-k]);
                if (minHeap.peek() > nums[i]) {
                    maxHeap.offer(nums[i]);
                } else {
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(nums[i]);
                }
            }
            
            if (bSingle) {
                ans[i-k+1] = minHeap.peek();
            } else {
                ans[i-k+1] = minHeap.peek()/2.0 + maxHeap.peek()/2.0;
            }
        }
        
        return ans;
    }
}
