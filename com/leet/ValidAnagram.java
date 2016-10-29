package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given two strings s and t, write a function to determine if t is an anagram of s.
//
//For example,
//s = "anagram", t = "nagaram", return true.
//s = "rat", t = "car", return false.
//
//Note:
//You may assume the string contains only lowercase alphabets.
//
//Follow up:
//What if the inputs contain unicode characters? How would you adapt your solution to such case?
	
//Amazon, Uber, Yelp
//Easy
public class ValidAnagram {

	public ValidAnagram() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String s = "";
		String t = "";
		boolean bRet = isAnagram(s, t);
		
		if (bRet) {
			System.out.println(" Is Anagram !");
		} else {
			System.out.println(" Is NOT Anagram !");			
		}
		
	}
	
	
	//Accepted, better
    public boolean isAnagram(String s, String t) {
        if (s == null ^ t == null) return false;
        if (s == null && t == null) return true;
        int ns = s.length();
        int nt = t.length();
        if (ns != nt) return false;
        if (ns == 0 && nt == 0) return true;
        int[] carr = new int[26];
        int i;
        
        for (i=0; i<ns; i++) carr[s.charAt(i)-'a']++;
        
        for (i=0; i<nt; i++) {
            carr[t.charAt(i)-'a']--;
            if (carr[t.charAt(i)-'a'] < 0) return false;
        }
        
        return true;
    }
	
    
	//AC
    public boolean isAnagramA(String s, String t) {
        int nLen = s.length();
        List<Character> lstS = new ArrayList<Character>();
        List<Integer> lstCharCnt = new ArrayList<Integer>();
        int i;
        int nIdx;
        int nTmpCnt;
        
        if (s.length() != t.length()) return false;
                
        
        for (i = 0; i < nLen; i++) {
        	nIdx = lstS.indexOf(s.charAt(i));
        	
        	if (nIdx == -1) {
        		lstCharCnt.add(1);
        		lstS.add(s.charAt(i));
        	} else {
        		lstCharCnt.set(nIdx, lstCharCnt.get(nIdx) + 1);
        	}
        }
        
        
        for (i = 0; i < nLen; i++) {
        	nIdx = lstS.indexOf(t.charAt(i));
        	
        	if (nIdx == -1) {
        		return false;
        	} else {
        		nTmpCnt = lstCharCnt.get(nIdx);
        		if (nTmpCnt < 1) return false;
        		
        		lstCharCnt.set(nIdx, nTmpCnt - 1);
        	}
        }
        
        return true;
    }
	
	
}
