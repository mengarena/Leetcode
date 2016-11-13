package com.leet;

//Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, 
//where a move is incrementing n - 1 elements by 1.
//
//Example:
//
//Input:
//[1,2,3]
//
//Output:
//3
//
//Explanation:
//Only three moves are needed (remember each move increments two elements):
//
//[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

//Indeed, Coursera
//Easy
public class MinimumMovesToEqualArrayElements {

	public MinimumMovesToEqualArrayElements() {
		// TODO Auto-generated constructor stub
	}

	//ACC:
	//Add 1 to n - 1 elements is the same as subtracting 1 from one element, w.r.t goal of making the elements in the array equal.
	//So, best way to do this is make all the elements in the array equal to the min element.
	//sum(array) - n * minimum
    public int minMoves(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int i;
        int count = 0;
        
        for (int j:nums) {
            min = Math.min(min, j);
        }
        
        for (i=0; i<n; i++) count += nums[i] - min;
        
        return count;
    }

}
