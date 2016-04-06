package com.leet;

//Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
//
//For example:
//
//Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
//
//Follow up:
//Could you do it without any loop/recursion in O(1) runtime?
//
//Hint:
//
//A naive implementation of the above process is trivial. Could you come up with other methods?
//What are all the possible results?
//How do they occur, periodically or randomly?
//You may find this Wikipedia article useful.


//Microsoft
public class AddDigits {

	public AddDigits() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int num = 12;
		
		int nRet = addDigits(num);
		
		System.out.println("Add Digits Result: " + nRet);
	}
	
	//Accepted: Better
	public int addDigits(int num) {
		if (num <= 9) return num;
		
		int nRet = num % 9;
		
		if (nRet == 0) {
			return 9;
		} else {
			return nRet;
		}
	}
	
	
/*	
	//Accepted
    public int addDigits(int num) {
    	int nRet = 0;
    	int nRemainder = num % 10;
    	int nQuotient = num / 10;
    	    	
    	nRet = nRemainder;
    	
    	while (nQuotient >= 10) {
    		nRemainder = nQuotient % 10;
    		nQuotient = nQuotient / 10;
    		nRet = (nRet + nRemainder) % 9;
    	}
    	
    	nRet = nRet + nQuotient;

    	if (nRet >= 10) {
    		nRet = nRet % 9;
    		
    		if (nRet == 0) nRet = 9;
    	}
    	
    	return nRet;
    }	
*/
    
}
