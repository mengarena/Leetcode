package com.leet;

//Given a non-empty string containing an out-of-order English representation of digits 0-9, 
//output the digits in ascending order.
//
//Note:
//1. Input contains only lowercase English letters.
//2. Input is guaranteed to be valid and can be transformed to its original digits. 
//That means invalid inputs such as "abc" or "zerone" are not permitted.
//3. Input length is less than 50,000.
//
//Example 1:
//
//Input: "owoztneoer"
//
//Output: "012"
//
//Example 2:
//
//Input: "fviefuro"
//
//Output: "45"

	
public class ReconstructOriginalDigitsFromEnglish {

	public ReconstructOriginalDigitsFromEnglish() {
		// TODO Auto-generated constructor stub
	}

	//ACC:
	//Strategy: The key is to find the unique character in the remained english digits
	//The order could be: zero (z), two (w), four (u), six (x), seven (s), eight (g), five (v), three (r), one (o), nine (i)
    public String originalDigits(String s) {
        if (s == null || s.length() < 3) return "";
        int[] digitCount = new int[10]; //Result digits, the count for each digit
        int[] charCount = new int[26];
        int i,j;
        StringBuilder sb = new StringBuilder();
        int tmp;
        
        for (i=0; i<s.length(); i++) {
            charCount[s.charAt(i)-'a']++;
        }
        
        //Process "zero"
        tmp = charCount['z'-'a'];
        if (tmp > 0) {
            digitCount[0] = tmp;
            
            charCount['z'-'a'] = charCount['z'-'a'] - tmp;
            charCount['e'-'a'] = charCount['e'-'a'] - tmp;
            charCount['r'-'a'] = charCount['r'-'a'] - tmp;
            charCount['o'-'a'] = charCount['o'-'a'] - tmp;
        }
        
        //Process "two"
        tmp = charCount['w'-'a'];
        if (tmp > 0) {
            digitCount[2] = tmp;
            
            charCount['t'-'a'] = charCount['t'-'a'] - tmp;
            charCount['w'-'a'] = charCount['w'-'a'] - tmp;
            charCount['o'-'a'] = charCount['o'-'a'] - tmp;
        }        
        
        //Process "four"
        tmp = charCount['u'-'a'];
        if (tmp > 0) {
            digitCount[4] = tmp;
            
            charCount['f'-'a'] = charCount['f'-'a'] - tmp;
            charCount['o'-'a'] = charCount['o'-'a'] - tmp;
            charCount['u'-'a'] = charCount['u'-'a'] - tmp;
            charCount['r'-'a'] = charCount['r'-'a'] - tmp;
        }          
        
        //Process "six"
        tmp = charCount['x'-'a'];
        if (tmp > 0) {
            digitCount[6] = tmp;
            
            charCount['s'-'a'] = charCount['s'-'a'] - tmp;
            charCount['i'-'a'] = charCount['i'-'a'] - tmp;
            charCount['x'-'a'] = charCount['x'-'a'] - tmp;
        }          
        
        //Process "seven"
        tmp = charCount['s'-'a'];
        if (tmp > 0) {
            digitCount[7] = tmp;
            
            charCount['s'-'a'] = charCount['s'-'a'] - tmp;
            charCount['e'-'a'] = charCount['e'-'a'] - tmp*2;
            charCount['v'-'a'] = charCount['v'-'a'] - tmp;
            charCount['n'-'a'] = charCount['n'-'a'] - tmp;
        }          
        
        //Process "eight"
        tmp = charCount['g'-'a'];
        if (tmp > 0) {
            digitCount[8] = tmp;
            
            charCount['e'-'a'] = charCount['e'-'a'] - tmp;
            charCount['i'-'a'] = charCount['i'-'a'] - tmp;
            charCount['g'-'a'] = charCount['g'-'a'] - tmp;
            charCount['h'-'a'] = charCount['h'-'a'] - tmp;
            charCount['t'-'a'] = charCount['t'-'a'] - tmp;
        }   
        
        //Process "five"
        tmp = charCount['v'-'a'];
        if (tmp > 0) {
            digitCount[5] = tmp;
            
            charCount['f'-'a'] = charCount['f'-'a'] - tmp;
            charCount['i'-'a'] = charCount['i'-'a'] - tmp;
            charCount['v'-'a'] = charCount['v'-'a'] - tmp;
            charCount['e'-'a'] = charCount['e'-'a'] - tmp;
        }  
        
        //Process "three"
        tmp = charCount['r'-'a'];
        if (tmp > 0) {
            digitCount[3] = tmp;
            
            charCount['t'-'a'] = charCount['t'-'a'] - tmp;
            charCount['h'-'a'] = charCount['h'-'a'] - tmp;
            charCount['r'-'a'] = charCount['r'-'a'] - tmp;
            charCount['e'-'a'] = charCount['e'-'a'] - tmp*2;
        }
  
        //Process "one"
        tmp = charCount['o'-'a'];
        if (tmp > 0) {
            digitCount[1] = tmp;
            
            charCount['o'-'a'] = charCount['o'-'a'] - tmp;
            charCount['n'-'a'] = charCount['n'-'a'] - tmp;
            charCount['e'-'a'] = charCount['e'-'a'] - tmp;
        }    
   
        //Process "nine"
        tmp = charCount['i'-'a'];
        if (tmp > 0) {
            digitCount[9] = tmp;
            
            charCount['n'-'a'] = charCount['n'-'a'] - tmp*2;
            charCount['i'-'a'] = charCount['i'-'a'] - tmp;
            charCount['e'-'a'] = charCount['e'-'a'] - tmp;
        }     
   
        for (i=0; i<=9; i++) {
            for (j=0; j<digitCount[i]; j++) {
                sb.append(i);
            }
        }
        
        return sb.toString();
    }

}
