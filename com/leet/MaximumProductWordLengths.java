package com.leet;

//Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. 
//You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
//
//Example 1:
//Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
//Return 16
//The two words can be "abcw", "xtfn".
//
//Example 2:
//Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
//Return 4
//The two words can be "ab", "cd".
//
//Example 3:
//Given ["a", "aa", "aaa", "aaaa"]
//Return 0
//No such pair of words.

//Google
public class MaximumProductWordLengths {

	public MaximumProductWordLengths() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		String[] words = {"a", "aa", "aaa", "aaaa"};

		System.out.println("Max Product = " + maxProduct(words));
	}
	
	
    public int maxProduct(String[] words) {
        int nProduct = 0;
        if (words == null || words.length == 0) return 0;
        int n = words.length;
        int[] nBitWords = new int[n];
        int i, j;
        
        for (i=0; i<n; i++) {
        	//Convert words[i] into an integer
        	for (j=0; j<words[i].length(); j++) {
        		nBitWords[i] |= 1 << (words[i].charAt(j) - 'a');
        	}
        	
        	for (j=0; j<i; j++) {
        		if ((nBitWords[i] & nBitWords[j]) == 0) {
        			nProduct = Math.max(nProduct, words[i].length() *   words[j].length());
        		}
        	}
        }
        
        return nProduct;
    }
	
}
