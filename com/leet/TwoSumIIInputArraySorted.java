package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
//
//The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. 
//		Please note that your returned answers (both index1 and index2) are not zero-based.
//
//You may assume that each input would have exactly one solution.
//
//Input: numbers={2, 7, 11, 15}, target=9
//Output: index1=1, index2=2

public class TwoSumIIInputArraySorted {

	public TwoSumIIInputArraySorted() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] numbers = {2,7,11,15};
		
		int[] narrIdx = twoSum(numbers, 9);
		
		System.out.println(narrIdx[0] + "," + narrIdx[1]);
	}
	
    public int[] twoSum(int[] numbers, int target) {
        int[] narrIdx = new int[2];
        if (numbers == null || numbers.length < 2) return narrIdx;
        int n = numbers.length;
        int i = 0, j = n-1;
        int nSum = 0;
        
        while (i < j) {
        	nSum = numbers[i] + numbers[j];
        	if (nSum == target) {
        		narrIdx[0] = i+1;   //Index is 1-based, not 0
        		narrIdx[1] = j+1;
        		break;
        	} else if (nSum < target) {
        		i++;
        	} else {
        		j--;
        	}
        }
        
        return narrIdx;
    }
}
