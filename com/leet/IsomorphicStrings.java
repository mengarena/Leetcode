package com.leet;


import java.util.HashMap;
import java.util.Map;

//Given two strings s and t, determine if they are isomorphic.
//
//Two strings are isomorphic if the characters in s can be replaced to get t.
//
//All occurrences of a character must be replaced with another character while preserving the order of characters. 
//No two characters may map to the same character but a character may map to itself.
//
//For example,
//Given "egg", "add", return true.
//
//Given "foo", "bar", return false.
//
//Given "paper", "title", return true.

//Linkedin
public class IsomorphicStrings {

	public IsomorphicStrings() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String s = "babb";
		String t = "dbdd";
		boolean bRet = isIsomorphic(s, t);
		
		if (bRet) {
			System.out.println(s + " AND " + t + " are Isomorphic !");
		} else {
			System.out.println(s + " AND " + t + " are NOT Isomorphic !");
		}
	}

    public boolean isIsomorphic(String s, String t) {
        int n;
        Map<Integer, Integer> letterMapS = new HashMap<Integer, Integer>();  //Character, last Position
        Map<Integer, Integer> letterMapT = new HashMap<Integer, Integer>();
        int nValS, nValT;
        int nIdxS, nIdxT;
        String sNew, tNew;

        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.length() == 0 && t.length() == 0) return true;
        if (s.length() == 1 && t.length() == 1) return true;
        if (s.length() != t.length()) return false;
        
        sNew = s;  //s.toLowerCase();
        tNew = t;  //t.toLowerCase();

        n = sNew.length();
        
        for (int i=0; i<n; i++) {
        	nValS = sNew.charAt(i) - 'a';  nValT = tNew.charAt(i) - 'a';
        	if (letterMapS.containsKey(nValS) && letterMapT.containsKey(nValT)) {
    			nIdxS = letterMapS.get(nValS);
    			nIdxT = letterMapT.get(nValT);
    			if (nIdxS != nIdxT) return false;
        	} else if (letterMapS.containsKey(nValS) || letterMapT.containsKey(nValT)) {
        		return false;
        	} else {
        		letterMapS.put(nValS, i);
        		letterMapT.put(nValT, i);
        	}
        }
                
        return true;
    }
	
}
