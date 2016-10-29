package com.leet;

//Write a function that takes a string as input and reverse only the vowels of a string.
//
//Example 1:
//Given s = "hello", return "holle".
//
//Example 2:
//Given s = "leetcode", return "leotcede".

//Google
//Easy
public class ReverseVowelsOfString {

	public ReverseVowelsOfString() {
		// TODO Auto-generated constructor stub
	}

	//ACC
    public String reverseVowels(String s) {
        if (s == null || s.length() <= 1) return s;
        int n = s.length();
        char[] carr = s.toCharArray();
        int left = 0, right = n-1;
        char tmp;
        
        while (left < right) {
        	while (left < right && !isVowel(carr[left])) left++;
        	
        	while (right > left && !isVowel(carr[right])) right--;
        	
        	if (left < right) {
        		tmp = carr[right];
        		carr[right] = carr[left];
        		carr[left] = tmp;
        	}
        	
        	left++;
        	right--;
        }
        
        return new String(carr);
    }
    
    private boolean isVowel(char c) {
    	return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U');
    }
}
