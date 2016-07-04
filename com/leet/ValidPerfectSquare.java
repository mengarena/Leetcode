package com.leet;

//Given a positive integer num, write a function which returns True if num is a perfect square else False.
//
//Note: Do not use any built-in library function such as sqrt.
//
//Example 1:
//
//Input: 16
//Returns: True
//Example 2:
//
//Input: 14
//Returns: False


//Linkedin
public class ValidPerfectSquare {

	public ValidPerfectSquare() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC: 34%
	//Binary search
    public boolean isPerfectSquare(int num) {
        if (num < 0) return false;
        if (num <= 1) return true;
        int n = num >> 1;
        int i = 2, j = n;
        int mid;
        
        while (i <= j) {
            mid = i + (j-i)/2;
            if (mid*mid == num) return true;
            
            if (mid <= num/mid) {   //=should be there  (and mid = num/mid  is NOT the same as mid*mid == num, say num = 5
                i = mid+1;
            } else {
                j = mid-1;
            }
        }
        
        return false;
    }
	
	
    //ACC:  17%
    //Reason:  
    //This is a math problem£º
    //1 = 1
    //4 = 1 + 3
    //9 = 1 + 3 + 5
    //16 = 1 + 3 + 5 + 7
    //25 = 1 + 3 + 5 + 7 + 9
    //36 = 1 + 3 + 5 + 7 + 9 + 11
    //....
    //so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = nn
    
    public boolean isPerfectSquareK(int num) {
        int i = 1;
        
        while (num > 0) {
            num -= i;
            i += 2;
        }
        
        return num == 0;
     }
    
    
    
	//ACC: 17%
	//Strategy: To chech how many bits (binary) in num, which could be used to decide the lower and upper bound for searching for the sqrt
    public boolean isPerfectSquareB(int num) {
        if (num < 0) return false;
        if (num <= 1) return true;
        int n = 0;
        int tmp = num;
        
        while (tmp > 0) {
            tmp = tmp >> 1;
            n++;
        }
        
        int low = (n-1)/2;
        int high = (n-1)/2+1;
        
        low = 1 << low;
        high = 1 << high;
        
        for (int i=low; i<=high; i++) {
            if (i*i == num) return true;
        }
        
        return false;
    }
	
	
	//ACC:  2%
    public boolean isPerfectSquareA(int num) {
        if (num < 0) return false;
        if (num <= 1) return true;
        int n = num >> 1;
        
        for (int i=2; i<=n; i++) {
            if (i*i == num) return true;
        }
        
        return false;
    }
}
