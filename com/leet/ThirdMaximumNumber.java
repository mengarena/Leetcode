package com.leet;

//Given a non-empty array of integers, return the third maximum number in this array. 
//If it does not exist, return the maximum number. The time complexity must be in O(n).
//
//Example 1:
//	
//Input: [3, 2, 1]
//
//Output: 1
//
//Explanation: The third maximum is 1.
//
//
//Example 2:
//
//Input: [1, 2]
//
//Output: 2
//
//Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
//
//Example 3:
//
//Input: [2, 2, 3, 1]
//
//Output: 1
//
//Explanation: Note that the third maximum here means the third maximum distinct number.
//Both numbers with value 2 are both considered as second maximum.

//Amazon
//Easy
public class ThirdMaximumNumber {

	public ThirdMaximumNumber() {
		// TODO Auto-generated constructor stub
	}

	//ACC
    public int thirdMax(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        boolean bSecond = false;
        int third = Integer.MIN_VALUE;
        boolean bThird = false;
        int i;
        
        first = nums[0];
        
        for (i=1; i<n; i++) {
            if (nums[i] > first) {
                
                if (bThird == true) {
                    third = second;
                    second = first;
                } else {
                    if (bSecond == true) {
                        third = second;
                        bThird = true;
                        
                        second = first;
                    } else {
                        second = first;
                        bSecond = true;
                    }
                }
                
                first = nums[i];
            } else if (nums[i] < first) {
                if (bSecond == false) {
                    second = nums[i];
                    bSecond = true;
                } else {
                    if (nums[i] > second) {
                       third = second;
                       bThird = true;
                       
                       second = nums[i];
                    } else if (nums[i] < second) {
                        if (bThird == false) {
                            third = nums[i];
                            bThird = true;
                        } else {
                            if (nums[i] > third) third = nums[i];
                        }
                    } 
                }
            }
        }
        
        
        if (bThird) {
            return third;
        } else {
            return first;
        }
    
    }

}
