package com.leet;

import java.util.*;

//You are playing the following Bulls and Cows game with your friend: 
//You write down a number and ask your friend to guess what the number is. 
//Each time your friend makes a guess, 
//you provide a hint that indicates how many digits in said guess match your secret number exactly in 
//both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). 
//Your friend will use successive guesses and hints to eventually derive the secret number.
//
//For example:
//
//Secret number:  "1807"
//Friend's guess: "7810"
//
//Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
//Write a function to return a hint according to the secret number and friend's guess,
//use A to indicate the bulls and B to indicate the cows. In the above example, your function should return "1A3B".
//
//Please note that both secret number and friend's guess may contain duplicate digits, for example:
//
//Secret number:  "1123"
//Friend's guess: "0111"
//In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
//You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.

public class BullsAndCows {

	public BullsAndCows() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String sSecret = "1112";
		String sGuess = "11123";
		
		System.out.println(sSecret + " AND " + sGuess + " has " + getHint(sSecret, sGuess));
	}
	
    public String getHint(String secret, String guess) {
        String sHint = "";
        int n;
        int i;
        int nDigitSecret, nDigitGuess;
        int nBull = 0;
        int nCow = 0;
        int nKey, nCountSecret, nCountGuess;
        Map<Integer, Integer> mapNumSecret = new HashMap<Integer, Integer>();
        Map<Integer, Integer> mapNumGuess = new HashMap<Integer, Integer>();
        
        if (secret == null || guess == null) return "";
        if (secret.length() != guess.length()) return "";
        
        n = secret.length();
        
        for (i=0; i<n; i++) {
        	nDigitSecret = secret.charAt(i) - '0';
        	nDigitGuess = guess.charAt(i) - '0';
        	if (nDigitSecret == nDigitGuess) {
        		nBull = nBull + 1;
        	} else {
        		if (mapNumSecret.containsKey(nDigitSecret)) {
        			mapNumSecret.replace(nDigitSecret, mapNumSecret.get(nDigitSecret) + 1);
        		} else {
        			mapNumSecret.put(nDigitSecret, 1);
        		}
        		
        		if (mapNumGuess.containsKey(nDigitGuess)) {
        			mapNumGuess.replace(nDigitGuess, mapNumGuess.get(nDigitGuess) + 1);
        		} else {
        			mapNumGuess.put(nDigitGuess, 1);
        		}
        		
        	}
        }
        
        //Calculate #cows
        Set<Integer> setKeys = mapNumGuess.keySet();
        for (Iterator<Integer> it = setKeys.iterator(); it.hasNext(); ) {
        	nKey = it.next();
        	if (mapNumSecret.containsKey(nKey)) {
        		nCountGuess = mapNumGuess.get(nKey);
        		nCountSecret = mapNumSecret.get(nKey);
        		nCow = nCow + Math.min(nCountGuess, nCountSecret);
        	}
        	
        }
        
        sHint = nBull + "A" + nCow + "B";
        
        return sHint;
        
    }
	
	
}
