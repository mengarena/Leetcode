package com.leet;

//Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
//
//The input string does not contain leading or trailing spaces and the words are always separated by a single space.
//
//For example,
//Given s = "the sky is blue",
//return "blue is sky the".
//
//Could you do it in-place without allocating extra space?
//
//Related problem: Rotate Array


//Amazon, Microsoft
public class ReverseWordsStringII {

	public ReverseWordsStringII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String s = "the sky is blue";
		//String s = "Rufeng meng is OK";
		char[] ss = s.toCharArray();
		
		reverseWords(ss);
		
		System.out.println(new String(ss));
	}
	
	
	//AC: 20%
	//Strategy: 
	//First, reverse the whole string
	//Second, reverse each word
    public void reverseWords(char[] s) {
        if (s == null || s.length < 2) return;
        int n = s.length;
        int i, j;
        
        //Reverse the string        
        reverse(s, 0, n-1);
        
        i = 0;
        j = 1;
        
        //Reverse each word
        while (j < n) {
            while (j < n && s[j] != ' ') j++;
            reverse(s, i, j-1);
            
            i = j+1;
            j++;
        }
    }
    
    
    /* Exchange a, b
       a = a ^ b;
       b = b ^ a;
       a = a ^ b;  
     */
    private void reverse(char[] s, int start, int end) {
        for (int i=start; i<start + (end-start+1)/2; i++) {
            s[i] = (char) (s[i] ^ s[end-(i-start)]);
            s[end-(i-start)] = (char) (s[end-(i-start)] ^ s[i]);
            s[i] = (char) (s[i] ^ s[end-(i-start)]);
        }
    }
    
    
    
    
    
    
    
    
/*
 
    public void reverseWords(char[] s) {
        if (s == null) return;
        int n = s.length;
        if (n == 1) return;
        int i, j;
        
        //Reverse the string
        for (i=0; i<n/2; i++) {
            //Exchange tow corresponding character
            s[i] = (char) (s[i] ^ s[n-1-i]);
            s[n-1-i] = (char) (s[n-1-i] ^ s[i]);
            s[i] = (char) (s[i] ^ s[n-1-i]);
        }
        
        i = 0;
        j = 1;
        
        while (j < n) {
            while (j < n && s[j] != ' ') j++;
            reverse(s, i, j-1);
            
            i = j+1;
            j++;
        }
    }
    
    private void reverse(char[] s, int nStart, int nEnd) {
        for (int i=nStart; i<nStart + (nEnd-nStart+1)/2; i++) {
            s[i] = (char) (s[i] ^ s[nEnd-(i-nStart)]);
            s[nEnd-(i-nStart)] = (char) (s[nEnd-(i-nStart)] ^ s[i]);
            s[i] = (char) (s[i] ^ s[nEnd-(i-nStart)]);
        }
    }     
 */
    
    
    
    
    
}
