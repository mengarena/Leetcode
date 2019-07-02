package com.leet;

import java.util.Arrays;

//Given a positive integer (in the form of an array of digits). 
//We are allowed to swap one pair of digits in the given number. 
//We need to return the smallest possible integer that can be obtained. 
//		Note, it should be a valid integer, i.e, should not contain leading 0's.
//
//For example:-
//
//93561 returns 13569
//596 returns 569
//10234 returns 10234
//120 returns 102
//10091 returns 10019
//98761111 returns 18761119


public class FindSmallestIntegerBySwappingAPairOfDigits {

	public FindSmallestIntegerBySwappingAPairOfDigits() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
//		int[] narrInput = {9,3,5,6,1};
//		int[] narrInput = {5,9,6};
//		int[] narrInput = {1,0,2,3,4};
//		int[] narrInput = {1,2,0};
//		int[] narrInput = {1,0,0,9,1};
		int[] narrInput = {9,8,7,6,1,1,1,1};
		
		swapDigits(narrInput);
		
		for (int i:narrInput) System.out.print(i + ",");
		
		System.out.println();
	}
	
	//Strategy
//	As a preparation, we loop through the digits, and mark the last positions of the digits in an array[10] (call it last)
//	(including 0s). That is O(n).
//
//	Next, we start to iterate through digits from left to right. 
//	For each position we try to find the smallest digit whose last position is greater than our current position 
//	(position constraint). 
//	Also that digit must be smaller than the current digit.
//
//	If we are in the first position we start the loop on last from 1 (otherwise from 0), 
//	just until the value of the current digit (not including).
//
//	If we find such a digit (concerning the position constraint), we swap (and break the loop). 
//	If we don't, we go forward to the next digit. The cost is at most O(n*9), which is O(n).
//
//	The total cost is O(n) + O(n)*O(9) = O(n).
//
//	How does the algorithm work on the examples:
//
//	93561 ->   it can swap in the first cycle
//
//	596   ->   skipping the first cycle, 
//	           then finds '6' because of the position constraint 
//	           (comparing position '1' with last[5] = 0, last[6] = 2)
//
//	10234 ->   does not find anything because of the position constraint
//
//	93218910471211292416 -> finds the last occurrence of '1' to replace '9'
//
//	98761111 -> it can swap in the first cycle
//	            (last[1] = 7, so it will change the last occurrence)
//
//	555555555555555555596 -> iterates until the '9', then you skip last[5]
//	            but finds last[6] as a good swap
//
//	120 ->      at pos 0 (1) cannot find a non-zero element less than 1, so skip
//	            at pos 1 (2) can find 0 on position 2, so we swap
//	            
//	Once again, here we do one iteration on the digits (for pre-parsing the data), then one iteration for finding the MSB. 
//	In the second iteration we iterate on last, which is constant size (at most 9).
//
//	You can further optimize the algorithm by adding keeping track of the minimum value when it is worth to 
//	start the loop on last, but that's an optimization already. 
//	The prevoius version contained that, check the history if you're interested :)

	//Complexity:  O(n)
	public void swapDigits(int[] narrDigits) {
		if (narrDigits == null || narrDigits.length <= 1) return;
		int n = narrDigits.length;
		int[] last = new int[10];
		int i,j;
		int start;
		int tmp;
		
		Arrays.fill(last, -1);
		
		//Find the last position of each digit (0-9)
		for (i=0; i<n; i++) {
			last[narrDigits[i]] = i;
		}
		
		for (i=0; i<n; i++) {
			if (i==0) {  //For the first position (i.e. 0th), should check digits 1-9
				start = 1;   //Start index in last[]
			} else {     //For other positions, check from 0
				start = 0;
			}
			
			//For the digit at current position, to find a smallest digit which is smaller than current digit and 
			//that digit's last position is larger than current position
			for (j=start; j<narrDigits[i]; j++) {  //For digit smaller than current digit
				if (last[j] > i) {      //For position larger than current position
					tmp = narrDigits[i];
					narrDigits[i] = narrDigits[last[j]];
					narrDigits[last[j]] = tmp;
					return;
				}
			}
		}
	}
}
