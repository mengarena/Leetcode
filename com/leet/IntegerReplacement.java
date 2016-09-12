package com.leet;

import java.util.HashMap;
import java.util.Map;

//Given a positive integer n and you can do operations as follow:
//
//If n is even, replace n with n/2.
//If n is odd, you can replace n with either n + 1 or n - 1.
//What is the minimum number of replacements needed for n to become 1?
//
//Example 1:
//
//Input:
//8
//
//Output:
//3
//
//Explanation:
//8 -> 4 -> 2 -> 1
//Example 2:
//
//Input:
//7
//
//Output:
//4
//
//Explanation:
//7 -> 8 -> 4 -> 2 -> 1
//or
//7 -> 6 -> 3 -> 2 -> 1
//
//UPDATE:
//We have fixed the error in Integer Replacement. For input n = 3, the answer should be 2, not 3. 
//Please try submitting again, thanks. 
//We will rejudge your submissions after the contest finished and adjust your ranking accordingly.

//Baidu
public class IntegerReplacement {

	public IntegerReplacement() {
		// TODO Auto-generated constructor stub
	}

	//ACC:  7ms
    public int integerReplacement(int n) {
        if (n <= 1) return 0;
        if (n == 2) return 1;
        
        if (n % 2 == 0) {
            return integerReplacement(n/2)+1;
        } else {
        	//Here choose the bigger between n+1 and n-1
        	//Here need to consider the case when n = Integer.MAX_VALUE
        	//So, for n+1, use (n-1)/2+1 instead, which goes two steps
            return Math.min(integerReplacement((n-1)/2+1)+2, integerReplacement(n-1)+1);
        }
        
    }
    
    
    //ACC:  6ms
    Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
    
    public int integerReplacementA(int n) {
        if (hm.containsKey(n)) return hm.get(n);
        
        if (n <= 1) {
            hm.put(n, 0);
            return 0;
        }
        
        if (n == 2) {
            hm.put(n, 1);
            return 1;
        }
        
        int count = 0;
        
        if (n % 2 == 0) {
            count = integerReplacement(n/2)+1;
        } else {
            count = Math.min(integerReplacement((n-1)/2+1)+2, integerReplacement(n-1)+1);
        }
        
        hm.put(n, count);
        return count;
    }

}
