package com.leet;

import java.util.List;

//Given a sequence of words, check whether it forms a valid word square.
//
//A sequence of words forms a valid word square 
//if the kth row and column read the exact same string, where 0 ¡Ü k < max(numRows, numColumns).
//
//Note:
//The number of words given is at least 1 and does not exceed 500.
//Word length will be at least 1 and does not exceed 500.
//Each word contains only lowercase English alphabet a-z.
//
//Example 1:
//
//Input:
//[
//  "abcd",
//  "bnrt",
//  "crmy",
//  "dtye"
//]
//
//Output:
//true
//
//Explanation:
//The first row and first column both read "abcd".
//The second row and second column both read "bnrt".
//The third row and third column both read "crmy".
//The fourth row and fourth column both read "dtye".
//
//Therefore, it is a valid word square.
//
//Example 2:
//
//Input:
//[
//  "abcd",
//  "bnrt",
//  "crm",
//  "dt"
//]
//
//Output:
//true
//
//Explanation:
//The first row and first column both read "abcd".
//The second row and second column both read "bnrt".
//The third row and third column both read "crm".
//The fourth row and fourth column both read "dt".
//
//Therefore, it is a valid word square.
//
//Example 3:
//
//Input:
//[
//  "ball",
//  "area",
//  "read",
//  "lady"
//]
//
//Output:
//false
//
//Explanation:
//The third row reads "read" while the third column reads "lead".
//
//Therefore, it is NOT a valid word square.
//

//Google
public class ValidWordSquare {

	public ValidWordSquare() {
		// TODO Auto-generated constructor stub
	}

	//ACC
    public boolean validWordSquare(List<String> words) {
        if (words == null || words.isEmpty()) return true;
        int n = words.size();
        int k;
        int m = Integer.MIN_VALUE;
        
        for (k=0; k<n; k++) {
            m = Math.max(m, words.get(k).length());    
        }
        
        m = Math.max(m, n);
        
        for (k=0; k<m; k++) {
            String sColumn = getString(words, k);
            
            //Compare kth row string and column string
            if (!sColumn.equals(words.get(k))) return false;
        }
        
        return true;
    }
    
    //This function form the string from column
    private String getString(List<String> words, int k) {
        StringBuilder sb = new StringBuilder();
        
        for (String word:words) {
            if (word.length() > k) {
                sb.append(word.charAt(k));
            }
        }
        
        return sb.toString();
    }

}
