package com.leet;

import java.util.PriorityQueue;

//You are given an integer X. You must choose two adjacent digits and replace them with the larger of these two digits.
//
//For example, from the integer X = 233614, you can obtain:
//33614 (by replacing 23 with 3);
//23614 (by replacing 33 with 3 or 36 with 6);
//23364 (by replacing 61 with 6 or 14 with 4);
//
//You want to find the smallest number that can be obtained from X by replacing two adjacent digits with the larger of the two. 
//In the above example, the smallest such number is 23364.
//
//Write a function:
//
//class Solution {public int solution (int X);}
//
//that, given a positive integer X, returns the smallest number that can be obtained 
//from X by replacing two adjacent digits with the larger of the two.
//
//For example, given X = 233614, the function should return 23364, as explained above.
//
//Assume that:
//
//X is an integer within the range [10..1,000,000,000].
//In your solution, focus on correctness. 
//The performance of your solution will not be the focus of the assessment.

//Google
public class ReplaceTwoAdjacentDigitsWithLargerOne {

	public ReplaceTwoAdjacentDigitsWithLargerOne() {
		// TODO Auto-generated constructor stub
	}

	
	//If an integer abcdef,  if the digits are in ascending order, for example, c < d,  
	//then should not replace, otherwise, always will get a bigger one than not replacing (i.e. abdxx > abcxx)
	//The replacement should happen at place where c > d,  or at the last pair of c < d
	//In continuous descing order, replace the first c > d pair
	public int obtainSmallest(int num){

		String strNum = String.valueOf(num);
		PriorityQueue<Integer> queue = new PriorityQueue<>();
			
		for(int i=0; i<strNum.length()-1; i++){
			String newString = i == 0 ? "" : strNum.substring(0,i);
			if(strNum.charAt(i) > strNum.charAt(i+1)){
				newString += strNum.charAt(i) + strNum.substring(i+2, strNum.length());
			}else{
				newString += strNum.charAt(i+1) + strNum.substring(i+2, strNum.length());
			}
			queue.add(Integer.valueOf(newString));
		}
		
		return queue.poll();
		
	}

}
