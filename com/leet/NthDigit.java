package com.leet;

//Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
//
//Note:
//n is positive and will fit within the range of a 32-bit signed integer (n < 231).
//
//Example 1:
//
//Input:
//3
//
//Output:
//3
//Example 2:
//
//Input:
//11
//
//Output:
//0
//
//Explanation:
//The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.


//Google
//Easy
public class NthDigit {

	public NthDigit() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int n = 1000000000;
		
		int digit = findNthDigit(n);
		
		System.out.println(digit);
	}
	
	
	//ACC:  86%
	//Pay attention: if use int for totalCnt, it will overflow
	//#digit=1:   1~9      9
	//#digit=2:   10~99    90
	//#digit=3:   100~999  900
	//...
    public int findNthDigit(int n) {
        int bitCnt = 1;
        long totalCnt = 0;
        int numCnt = 0;
        int digitCnt = 0;
        int base;
        int number;
        long added = 0;
        
        while (totalCnt < n) {
            added = 9*(long)Math.pow(10, bitCnt-1)*bitCnt;
            totalCnt += (long) added;
            bitCnt++;
        }
        
        if (totalCnt == n) return 9;
        
        bitCnt--;

        base = ((int)Math.pow(10, bitCnt))/10;    //base = 1, 10, 100, 1000...

        totalCnt = totalCnt - added;   //Return to the previous range
        numCnt = (int)((n-totalCnt)/bitCnt);    //ith number from base
        digitCnt = (int)((n-totalCnt) % bitCnt);  //if the required digit is in the middle of a number, digitCnt is the remained #digits in the next number
        
        
        number = base + numCnt - 1;
        
        if (digitCnt == 0) {
            return number % 10;   
        } else {
            number++;
        
            String sNum = String.valueOf(number);
            return sNum.charAt(digitCnt-1) - '0';
        }
    }
    
    
    //ACC:  86%
    public int findNthDigitK(int n) {
        int start = 1;
        int digitCnt = 1;
        long count = 9;
        
        while (n > digitCnt * count) {
            n = n - (int) (digitCnt*count);
            start = start*10;
            digitCnt++;
            count = count*10;
        }
        
        int number = start + (n-1)/digitCnt;    //The number where the wanted nth digit is in
        String sNum = Integer.toString(number);
        return sNum.charAt((n-1) % digitCnt) - '0';   //The wanted digit
    }
    
    
    //ACC: 37%
    public int findNthDigitA(int n) {
        int start = 1;
        int digitCnt = 1;
        long count = 9;
        
        while (n > digitCnt * count) {
            n = n - (int) (digitCnt*count);
            start = start*10;
            digitCnt++;
            count = count*10;
        }
        
        int number = start + n/digitCnt - 1;
        int remained = n % digitCnt;
        
        if (remained == 0) {
            return number % 10;
        } else {
            String sNum = Integer.toString(number+1);
            return sNum.charAt(remained-1) - '0';
        }
        
    }
    
    

}
