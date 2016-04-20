package com.leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Write a function to generate the generalized abbreviations of a word.
//
//Example:
//Given word = "word", return the following list (order does not matter):
//["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
		
//Google
public class GeneralizedAbbreviation {

	public GeneralizedAbbreviation() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//String word = "intelligence";
		String word = "word";
		//String word = "word";
		
		List<String> lstWords = generateAbbreviations(word);
		
		System.out.println();
	}
	

	//AC: 75%
	//Each character has two possibility: abbreviated or not abbreviated
	//When processing each abbreviated character, abbreviation count accumulated
	public List<String> generateAbbreviations(String word) {
	    List<String> result = new ArrayList<>();

	    generateAbbreviationsHelper(word.toCharArray(), result, 0, "", 0);
	    
	    return result;
	}

	
	private void generateAbbreviationsHelper(char[] carr, List<String> result, int nPos, String scur, int nCount) {  //nCount = continuously abbred #character
		if (nPos == carr.length) {
			if (nCount > 0) scur = scur + nCount;
			result.add(scur);
		} else {
			//Abbreviate the character at current position and process next position
            //abbreviation count increase
			generateAbbreviationsHelper(carr, result, nPos+1, scur, nCount+1);   
			
			//Don't abbreviate the character at current position, and process next position
			//If before this position, there is abbreviation part, put the count at this position and also append the character into the string so far
			//reset count to 0
			generateAbbreviationsHelper(carr, result, nPos+1, scur + (nCount > 0 ? nCount + "" : "") + carr[nPos], 0);   
			
		}
	
	}
	
	//AC: 24%
	//Strategy:   for abbr a word, there are 2^n possibility, n is the length of the word
	//So we know that we will have 2^n result
	//Each character could be abbreviated, or not abbreviated
	//We could use integer or long to represent the 2^n, each bit could represent abbreviated or not, say 0 is not, 1 is abbreviated
	//Example, for "word",  1000 means 1ord,  0100 means w1rd
	//For consecutive digits, sum up
	//
	//** Obviously, this solution has limitations, the length of the word could not exceed length of long or integer
	//
	//** This code is from others
	public List<String> generateAbbreviationsB(String word) {
	    long size = 1 << word.length();   //2^n
	    List<String> result = new ArrayList<>();
	    for (long i = 0; i < size; i++) {
	        result.add(generateString(word, i));
	    }
	    return result;
	}

	public String generateString(String word, long number) {
	    StringBuilder sb = new StringBuilder();
	    int consecutiveOne = 0;
	    for (int i = 0; i < word.length(); i++) {
	        long bit = (number >> i) & 1;
	        if (bit == 1) {
	            consecutiveOne++;
	        } else {
	            if (consecutiveOne != 0) {
	                sb.append(consecutiveOne);
	                consecutiveOne = 0;
	            }
	            sb.append(word.charAt(i));
	        }
	    }
	    if (consecutiveOne != 0) {
	        sb.append(consecutiveOne);
	    }
	    return sb.toString();
	}
	
	
	//Accepted:  9%
    public List<String> generateAbbreviationsA(String word) {
    	List<String> lstAbbr = new ArrayList<String>();
        int n = word.length();
        
    	if (n == 0) {
    		lstAbbr.add("");
    	} else {
    		lstAbbr.add("" + n);   //No character exist, all are abbreviated
    	}
    	
    	//Keep current position's character, the part before it is abbreviated, the part after it is generating its own abbre
    	//At least one character will exist
    	for (int i=0; i<n; i++) {  //Keep character at each position
    		String sLeft = i > 0 ? i+"" : "";  //The part before current position is abbreviated
    		
    		List<String> lstRight = generateAbbreviationsA(word.substring(i+1));
    		
    		for (String sRight:lstRight) {
    			lstAbbr.add(sLeft + word.substring(i, i+1) + sRight);
    		}
    	}
    	
        return lstAbbr;
    }

    
    
        
}
