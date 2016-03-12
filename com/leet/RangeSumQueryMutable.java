package com.leet;

//Given an integer array nums, find the sum of the elements between indices i and j (i ¡Ü j), inclusive.
//
//The update(i, val) function modifies nums by updating the element at index i to val.
//Example:
//Given nums = [1, 3, 5]
//
//sumRange(0, 2) -> 9
//update(1, 2)
//sumRange(0, 2) -> 8
//
//Note:
//The array is only modifiable by the update function.
//You may assume the number of calls to update and sumRange function is distributed evenly.


//Fenwick Tree
public class RangeSumQueryMutable {

	private int[] trees = null;
	private int[] nums = null;
	
	//Goes up
	private int parent(int i) {
		int nParent = i + (i & (-i));
		return nParent;
	}

	//Goes down
	private int next(int i) {
		int nNext = i - (i & (-i));
		return nNext;
	}
	
	private int sum(int nPos) {
		int nSum = 0;
		int nCur = nPos+1;
		
		while (nCur > 0) {
			nSum += trees[nCur];
			nCur = next(nCur);
		}
		
		return nSum;
	}
	
    public void NumArray(int[] nums) {
        if (nums == null || nums.length == 0) return;
        this.nums = nums;
        trees = new int[nums.length + 1];
        
        int nCur = 0;
        
        for (int i=0; i<nums.length; i++) {
        	nCur = i+1;
        	while (nCur <= nums.length) {
        		trees[nCur] += nums[i];
        		nCur = parent(nCur);
        	}
        }
    }

    void update(int i, int val) {
    	int nDiff = val - nums[i];
    	int nCur = i + 1;
    	
    	nums[i] = val;
    	
    	while (nCur <= nums.length) {
    		trees[nCur] += nDiff;
    		nCur = parent(nCur);
    	}
    }

    public int sumRange(int i, int j) {
        if (i <= 0) return sum(j);
        
        return sum(j) - sum(i-1);        
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);