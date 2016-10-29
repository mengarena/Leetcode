package com.leet;

//Write an algorithm to determine if a number is "happy".
//
//A happy number is a number defined by the following process: Starting with any positive integer, 
//replace the number by the sum of the squares of its digits, 
//and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. 
//Those numbers for which this process ends in 1 are happy numbers.
//
//Example: 19 is a happy number
//
//1^2 + 9^2 = 82
//8^2 + 2^2 = 68
//6^2 + 8^2 = 100
//1^2 + 0^2 + 0^2 = 1


//Uber, Airbnb, Twitter
//Easy
public class HappyNumber {

	public HappyNumber() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC: 84%
	public int getSquareSum(int n) {
		int sum = 0, tmp;
		
		while (n > 0) {
			tmp = n % 10;
			sum += tmp*tmp;
			n = n/10;
		}
		
		return sum;
	}
	
	//Based on Floyd Cycle detection algorithm, use two pointers to detect cycle
	//If there is a cycle, fast, slow points will meet in the cycle
	//If is valid happy number, fast and slow ends at 1
	public boolean isHappy(int n) {
		int fast = n, slow = n;
		
		do {
			slow = getSquareSum(slow);   //Slow move one step
			fast = getSquareSum(fast);   //Fast move two steps
			fast = getSquareSum(fast);
		} while (fast != slow);
		
		if (slow == 1) return true;
		return false;
	}
	
	
	
	
	//Works, but not as good as the above one
    public boolean isHappyA(int n) {
     	boolean bHappy = false;
    	int nNewNumber = 0;
    	
    	nNewNumber = getSquareSum(n);
    	
    	while (nNewNumber >= 10) {
    		nNewNumber = getSquareSum(nNewNumber);
    	}
    	
    	if (nNewNumber == 1) {
    		bHappy = true;
    	} else {
    		bHappy = false;
    	}
    	
    	return bHappy;
    }
    
    
    //Calculate the square sum of the digits of a number
    public int getSquareSumA(int n) {
    	int nRemainder = 0;
    	int nQuotient = 0;
    	
    	int nNewNumber = 0;
    	
    	nRemainder = n % 10;
    	nQuotient = n / 10;
    	
    	nNewNumber = nNewNumber + nRemainder*nRemainder;
    	
    	while (nQuotient >= 10) {
    		nRemainder = nQuotient % 10;
    		nQuotient = nQuotient / 10;
    		
    		nNewNumber = nNewNumber + nRemainder*nRemainder;
    	}
    	
    	if (nQuotient < 10) {
    		nNewNumber = nNewNumber + nQuotient*nQuotient;
    	}
    	
    	return nNewNumber;
    }    

}
