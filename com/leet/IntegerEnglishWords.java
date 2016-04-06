package com.leet;

//Convert a non-negative integer to its english words representation. 
//Given input is guaranteed to be less than 231 - 1.
//
//For example,
//123 -> "One Hundred Twenty Three"
//12345 -> "Twelve Thousand Three Hundred Forty Five"
//1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
//Hint:
//
//Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
//Group the number by thousands (3 digits). 
//You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
//There are many edge cases. What are some good test cases? 
//Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)

//Facebook
public class IntegerEnglishWords {

	private static final String SmallNumbers[] = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private static final String Tens[] = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	private static final String BigNumbers[] = {"Thousand", "Million", "Billion"};
	
	public IntegerEnglishWords() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int num = 1000010;
		System.out.println(num + "--> Words:" + numberToWords(num) + "#");
	}
	

    public String numberToWords(int num) {
    	String sWords = "";
        if (num == 0) {
        	sWords = "Zero";
        	return sWords;
        }
        
        int nRemained = 0;
        int nLevel = 0;
        String sLevel = "";
        
        while (num > 0) {
        	nRemained = num % 1000;
        	
        	if (nRemained == 0) {
            	num = num / 1000;
            	nLevel++;
            	continue;
        	}
        	
        	sLevel = convert2Words(nRemained);
        	if (nLevel >= 1 && nRemained > 0) sLevel = sLevel + " " + BigNumbers[nLevel-1];
        	
    		if (sWords.length() > 0) {
    			sWords = sLevel + " " + sWords; 
    		} else {
    			sWords = sLevel;
    		}
        	
        	num = num / 1000;
        	nLevel++;
        }
     
        return sWords;
    }
	
    
    private String convert2Words(int num) {  //0 <= num < 1000
    	String sWords = "";
    	
    	int nHundred = num / 100;
    	int nTeen = num % 100;
    	int nTen = nTeen / 10;
    	int nNumber = nTeen % 10;
    	    	
    	if (nHundred > 0) sWords = SmallNumbers[nHundred-1] + " " + "Hundred";
    	if (nTeen == 0) return sWords;
    	
    	if (nTeen < 20) {
    		if (nHundred > 0) {
    			sWords = sWords + " " + SmallNumbers[nTeen-1];
    		} else {
    			sWords = SmallNumbers[nTeen-1];
    		}
    	} else {
    		if (nHundred > 0) {
    			sWords = sWords + " " + Tens[nTen-2];
    		} else {
    			sWords = Tens[nTen-2];
    		}
    		
    		if (nNumber > 0) sWords = sWords + " " + SmallNumbers[nNumber-1];
    	}
    	
    	return sWords;
    }
    
}
