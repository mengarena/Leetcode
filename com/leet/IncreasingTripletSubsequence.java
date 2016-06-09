package com.leet;

//Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
//
//Formally the function should:
//Return true if there exists i, j, k 
//such that arr[i] < arr[j] < arr[k] given 0 ¡Ü i < j < k ¡Ü n-1 else return false.
//Your algorithm should run in O(n) time complexity and O(1) space complexity.
//
//Examples:
//Given [1, 2, 3, 4, 5],
//return true.
//
//Given [5, 4, 3, 2, 1],
//return false.

//Facebook
public class IncreasingTripletSubsequence {

	public IncreasingTripletSubsequence() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
	//	int[] nums = {5,4,3,2,1};
	//	int[] nums = {1,2,3,4,5};
	//	int[] nums = {3,2,6,4,1,5,7};
	//	int[] nums = {1,0,0,2,0,0,-1,-1,-1,3};
		int[] nums = {3,6,5,2,1,6};
		
		System.out.println("Triplet Subsequence = " + increasingTriplet(nums));
	}
	
	
	//ACC
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int n = nums.length;
        int i;
        int first = nums[0];
        int second = Integer.MAX_VALUE;

        i = 1;
        while (i < n && nums[i] < first) {
            first = nums[i];
            i++;
        }
        
        if (i == n) return false;
        
        if (nums[i] > first) {
            second = nums[i];
            i++;
        }
        
        while (i < n-1) {
            if (nums[i] > second) {
                return true;
            } 
            
            if (nums[i] > first && nums[i] < second) {
                second = nums[i];
            } else if (nums[i] < first) {
                if (nums[i+1] > nums[i]) {   //Key Step:  only when occurs two new elements which meets first second, then shift original first
                    
                    if (nums[i+1] > second) return true;
                    
                    first = nums[i];
                    second = Integer.MAX_VALUE;
                }
            } 

            i++;
        }
        
        if (nums[n-1] > second) return true;
        
        return false;
    }
	
	
    //ACC
	//Strategy: Use dynamic programming
	//Record the previous smallest number which is greater than one of its previous numbers
    public boolean increasingTripletA(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int n = nums.length;
        int i;
        boolean bSmallest = false;
        int nSmallest = 0;
        boolean bSecondLastSmall = false;
        int nSecondLastSmall = 0;
        
        if (nums[1] > nums[0]) {
        	bSmallest = true;
        	bSecondLastSmall = true;
        	nSmallest = nums[0];
        	nSecondLastSmall = nums[1];
        } else {
        	bSmallest = true;
        	nSmallest = nums[1];
        }
        
        for (i=2; i<n-1; i++) {
        	if (bSmallest == true && bSecondLastSmall == true) {
        		if (nums[i] > nSecondLastSmall) {
        			return true;
        		} else if (nums[i] < nSecondLastSmall && nums[i] > nSmallest) {
        			nSecondLastSmall = nums[i];
        		} else if (nums[i] < nSmallest) {
        			if (nums[i] < nums[i+1]) {   //Only when both smallest and secondLastSmall could be possibly replaced, replace
            			if (nums[i+1] > nSecondLastSmall) {
            				return true;
            			} 

            			nSmallest = nums[i];
            			bSecondLastSmall = false;
            			nSecondLastSmall = 0;
        			}
        			
        		}
        	} else {  //bSecondLastSmall == false
        		if (nums[i] > nSmallest) {
        			nSecondLastSmall = nums[i];
        			bSecondLastSmall = true;
        		} else if (nums[i] < nSmallest) {
        			if (nums[i] < nums[i+1]) {
        				nSmallest = nums[i];
        				bSmallest = true;        
        			}
        		}
        	}
        }
        
        if (bSecondLastSmall == true && nums[n-1] > nSecondLastSmall) return true;
        
        return false;
    }
	
}
