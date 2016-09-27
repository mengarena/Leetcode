package com.leet;

//Given a non-negative integer num represented as a string, 
//remove k digits from the number so that the new number is the smallest possible.
//
//Note:
//The length of num is less than 10002 and will be ¡Ý k.
//The given num does not contain any leading zero.
//
//Example 1:
//
//Input: num = "1432219", k = 3
//Output: "1219"
//Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
//
//
//Example 2:
//
//Input: num = "10200", k = 1
//Output: "200"
//Explanation: Remove the leading 1 and the number is 200. 
//Note that the output must not contain leading zeroes.
//
//
//Example 3:
//
//Input: num = "10", k = 2
//Output: "0"
//Explanation: Remove all the digits from the number and it is left with nothing which is 0.

//Google
public class RemoveKDigits {

	public RemoveKDigits() {
		// TODO Auto-generated constructor stub
	}

	//ACC:  17%
	//Strategy:  search through all the digits, find the first pair Position(i, i+1), if the digit at i is larger than the digit at i+1,
	//           This is the place where remove one digit should happen (i.e. remove the digit at position i
	//           If no such kind of pair (i.e. all digits are in ascending order), then remove the last one
	//
	//           After removing one digit, try to remove the leading zeros
	//           If all removed, should return "0"
    public String removeKdigits(String num, int k) {
        if (num == null || num.isEmpty()) return "";
        int n = num.length();
        if (k >= n) return "0";
        int i;
        boolean bFind = false;
        int zeroCnt = 0;
        
        while (k > 0) {
            n = num.length();
            bFind = false;
            
            for (i=1; i<n; i++) {
                if (num.charAt(i) < num.charAt(i-1)) {
                    bFind = true;
                    num = num.substring(0, i-1) + num.substring(i);
                    break;
                }    
            }
            
            if (bFind == false) num = num.substring(0, n-1);
            
            zeroCnt = 0;
            while (zeroCnt < n-1 && num.charAt(zeroCnt) == '0') zeroCnt++;
            
            if (zeroCnt < n-1) {
                num = num.substring(zeroCnt);
            } else {
                num = "";
            }
            
            if (num.isEmpty()) {
                break;
            }
            
            k--;
        }
        
        if (num.isEmpty()) num = "0";
        
        return num;
    }

}
