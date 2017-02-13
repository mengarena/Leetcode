package com.leet;


//Given a non-negative integer n, count all numbers with unique digits, x, where 0 <= x < 10^n.
//
//Example:
//Given n = 2, return 91. (The answer should be the total numbers in the range of 0 <= x < 100, excluding [11,22,33,44,55,66,77,88,99])
//
//Hint:
//
//A direct way is to use the backtracking approach.
//Backtracking should contains three states which are (the current number, number of steps to get that number and a bitmask which represent which number is marked as visited so far in the current number). 
//Start with state (0,0,0) and count all valid number till we reach number of steps equals to 10n.
//This problem can also be solved using a dynamic programming approach and some knowledge of combinatorics.
//Let f(k) = count of numbers with unique digits with length equals k.
//f(1) = 10, ..., f(k) = 9 * 9 * 8 * ... (9 - k + 2) [The first factor is 9 because a number cannot start with 0].


public class CountNumbersWithUniqueDigits {

	public CountNumbersWithUniqueDigits() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC:
	//Strategy:   actually each such kind of number is a permutation of the digit 0~9
	//When the number >= 10, the first digit has 9 options.
    public int countNumbersWithUniqueDigits(int n) {
        if (n <= 0) return 1;
        int totalCnt = 10;   //n = 1
        
        for (int i=2; i<=n; i++) {
            
            int cnt = 1;
            
            for (int j=2; j<=i; j++) {
                cnt *= (10-j+1);
            }
            
            totalCnt += cnt*9;   //First digit has 9 options
        }
        
        return totalCnt;
    }
}
