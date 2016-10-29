package com.leet;

//Given an array of integers A and let n to be its length.
//
//Assume Bk to be an array obtained by rotating the array A k positions clock-wise, 
//we define a "rotation function" F on A as follow:
//
//F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
//
//Calculate the maximum value of F(0), F(1), ..., F(n-1).
//
//Note:
//n is guaranteed to be less than 105.
//
//Example:
//
//A = [4, 3, 2, 6]
//
//F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
//F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
//F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
//F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
//
//So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.


//Amazon
//Easy
public class RotateFunction {

	public RotateFunction() {
		// TODO Auto-generated constructor stub
	}

	//ACC: 7ms
	//Strategy:  say array a,b,c,d,e   (Its  basic sum a+b+c..+e = nSum)
	//Rotate 0:  0*a+1*b+2*c+...+4*e  = Base
 	//Rotate 1:  0*e+1*a+2*b+...+4*d  
	//---Difference between Roate 1 - Rotate 0 = -4e + a+b+..+e = nSum - 5e = nSum - ne
	//---Sum of Rotate 1 = Base + nSum - ne
	//Rotate 2:  0*d+1*e+2*a+...+4*c    = Base + 2*nSum - ne - nd
	//.....
    public int maxRotateFunction(int[] A) {
        if (A == null || A.length <= 1) return 0;
        int n = A.length;
        
        long nSum = 0;
        int i;
        long min = Integer.MAX_VALUE;
        long max = 0;
        long nBase = 0;
        long tmp = 0;   //The part except the Base in the sum
        
        for (i=0; i<n; i++) {
            min = Math.min(min, A[i]);
            nSum += A[i];
            nBase += i*A[i];
        }
        
        for (i=1; i<n; i++) {
            tmp = tmp + nSum - n*A[n-i];
            max = Math.max(max, tmp);    
        }
        
        return (int) (nBase + max);
    }
}
