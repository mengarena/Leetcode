package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

//A string such as "word" contains the following abbreviations:
//
//["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
//
//Given a target string and a set of strings in a dictionary, 
//find an abbreviation of this target string with the smallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.
//
//Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.
//
//Note:
//In the case of multiple answers as shown in the second example below, you may return any one of them.
//Assume length of target string = m, and dictionary size = n. You may assume that m ¡Ü 21, n ¡Ü 1000, and log2(n) + m ¡Ü 20.
//
//Examples:
//"apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")
//
//"apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").


//Google
//Hard
public class MinimumUniqueWordAbbreviation {

	public MinimumUniqueWordAbbreviation() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String target = "apple";
		String[] dictionary = {"plain", "amber", "blade"}; //{"blade"};   //
		
		String sRet = minAbbreviationA(target, dictionary);
		
		System.out.println(sRet);
	}
	
	
	
    public class Abbr implements Comparable<Abbr> {
        String sAbbr;
        int nLen;
        
        public Abbr(String sAbbr, int nLen) {
            this.sAbbr = sAbbr;
            this.nLen = nLen;
        }
        
        public int compareTo(Abbr o) {   //Small on top
            return this.nLen - o.nLen;
        }
    }
    
    //ACC: 166 ms
    public String minAbbreviation(String target, String[] dictionary) {
        if (dictionary == null || dictionary.length == 0) return ""+target.length();
        if (target == null || target.length() == 0) return "";
        
        PriorityQueue<Abbr> pq = new PriorityQueue<Abbr>();
        
        generateAbbr(pq, target, 0, "", 0, false);
        
        while (!pq.isEmpty()) {
            String sAbbr = pq.poll().sAbbr;
            
            boolean notMatched = true;
            
            for (String sDict:dictionary) {
                if (isValidAbbr(sDict, sAbbr)) {
                    notMatched = false;
                    break;
                }
            }
            
            if (notMatched) return sAbbr;
        }
        
        return "";
    }
    

    //Generate abbreviation for sWord
    //nLen is the length of sCur
    private void generateAbbr(PriorityQueue<Abbr> pq, String sWord, int nStartPos, String sCur, int nLen, boolean bPrevAbbr) {  //bPrevAbbr the previous part is abbr or not
        if (nStartPos == sWord.length()) {
            pq.offer(new Abbr(sCur, nLen));
            return;
        }
        
        //Directly append the character
        generateAbbr(pq, sWord, nStartPos+1, sCur+sWord.charAt(nStartPos), nLen+1, false);
        
        if (!bPrevAbbr) {
        	//Abbreviate different length
            for (int i=nStartPos; i<sWord.length(); i++) {
                generateAbbr(pq, sWord, i+1, sCur + (i-nStartPos+1), nLen+1, true);
            }
        }
    }
    
    
    //Same as ValidWordAbbreviation.java
    private boolean isValidAbbr(String sWord, String sAbbr) {
        int n = sWord.length();
        int m = sAbbr.length();
        int i = 0, j = 0;
        int startPos, endPos;
        char cW, cA;
        
        while (i < n && j < m) {
            cW = sWord.charAt(i);
            cA = sAbbr.charAt(j);
            
            if (cA >= '1' && cA <= '9') {
                startPos = j;
                endPos = j+1;
                
                while (endPos < m && sAbbr.charAt(endPos) >= '0' && sAbbr.charAt(endPos) <= '9') endPos++;
                
                i += Integer.parseInt(sAbbr.substring(startPos, endPos));
                j = endPos;
            } else {
                if (cW == cA) {
                    i++;
                    j++;
                } else {
                    return false;
                }
            }
        }
        
        return (i == n && j == m);
    }
	
	
	
	
	
	
	
	
	
	
	///////////////////////////////////////////
	
	
	//Function correct, Exceed Time Limit
    public String minAbbreviationB(String target, String[] dictionary) {
        if (dictionary == null || dictionary.length == 0) return ""+target.length();
        if (target == null || target.length() == 0) return "";
        
        Map<Integer, Set<String>> hmTargetAbbr = generateAbbr(target);
        Map<Integer, Set<String>> hmDictAbbr = new HashMap<Integer, Set<String>>();
        
        for (String sDict:dictionary) {
            Map<Integer, Set<String>> hmDictAbbrTmp = generateAbbr(sDict); 
            
            Set<Integer> setKeys = hmDictAbbrTmp.keySet();
            
            for (Integer key:setKeys) {
                Set<String> setDictAbbr = hmDictAbbrTmp.get(key);
                
                if (!hmDictAbbr.containsKey(key)) {
                    hmDictAbbr.put(key, new HashSet<String>());
                }
                
                for (String sDictAbbr:setDictAbbr) {
                    if (!hmDictAbbr.get(key).contains(sDictAbbr)) hmDictAbbr.get(key).add(sDictAbbr);
                }
            }
        }
        
        String sRet = "";

        for (int i=1; i<=target.length(); i++) {
            Set<String> setTargetAbbr = hmTargetAbbr.get(i);
            
            if (!hmDictAbbr.containsKey(i)) {
                sRet = setTargetAbbr.iterator().next();
                break;
            } else {
                for (String sTargetAbbr:setTargetAbbr) {
                    if (!hmDictAbbr.get(i).contains(sTargetAbbr)) return sTargetAbbr;
                }
            }
        }
        
        return sRet;
    }
    
    private int getActualLen(String s) {
        boolean bPrevDigit = false;
        int count = 0;
        
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                if (bPrevDigit == false) {
                    count++;
                    bPrevDigit = true;
                }
            } else {
                count++;
                bPrevDigit = false;
            }
        }
        
        return count;
    }
    
    private Map<Integer, Set<String>> generateAbbr(String word) {
        Map<Integer, Set<String>> hmAbbr = new HashMap<Integer, Set<String>>();
        
        generateAbbrHelper(word.toCharArray(), hmAbbr, 0, "", 0);
        
        return hmAbbr;
    }
    
    private void generateAbbrHelper(char[] carr, Map<Integer, Set<String>> hmAbbr, int nPos, String sCur, int nCount) {
        if (nPos == carr.length) {
            if (nCount > 0) sCur = sCur + nCount;
            
            int nLen = getActualLen(sCur);
            
            if (!hmAbbr.containsKey(nLen)) {
                Set<String> setAbbr = new HashSet<String>();
                setAbbr.add(sCur);
                hmAbbr.put(nLen, setAbbr);
            } else {
                if (!hmAbbr.get(nLen).contains(sCur)) hmAbbr.get(nLen).add(sCur);
            }
        } else {
            generateAbbrHelper(carr, hmAbbr, nPos+1, sCur, nCount+1);
            
            generateAbbrHelper(carr, hmAbbr, nPos+1, nCount == 0? sCur+carr[nPos]:sCur+nCount+carr[nPos], 0);
        }
    }
	
	
	
	
	
	//Function correct, Exceeded time limit
	
	//Brute Force
    public String minAbbreviationA(String target, String[] dictionary) {
        if (dictionary == null || dictionary.length == 0) return ""+target.length();
        if (target == null || target.length() == 0) return "";
        
        List<String> lstTargetAbbr = generateAbbrA(target);
        Set<String> setDictAbbr = new HashSet<String>();
        
        for (String sDict:dictionary) {
            List<String> lstDictAbbr = generateAbbrA(sDict);
            for (String sDictAbbr:lstDictAbbr) {
                if (!setDictAbbr.contains(sDictAbbr)) setDictAbbr.add(sDictAbbr);
            }
        }
        
        String sRet = "";
        int nRetLen = Integer.MAX_VALUE;
        int tmpLen = 0;
        
        for (String sTargetAbbr:lstTargetAbbr) {
            if (!setDictAbbr.contains(sTargetAbbr)) {
                tmpLen = getActualLen(sTargetAbbr);
                if (nRetLen > tmpLen) {
                    nRetLen = tmpLen;
                    sRet = sTargetAbbr;
                }
            }
        }
        
        return sRet;
    }
    
    //Calculate the length of the string, for example, length of "a23c" is 3
    private int getActualLenA(String s) {
        boolean bPrevDigit = false;
        int count = 0;
        
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                if (bPrevDigit == false) {
                    count++;
                    bPrevDigit = true;
                }
            } else {
                count++;
                bPrevDigit = false;
            }
        }
        
        return count;
    }
    
    //Get the abbreviate list of word
    private List<String> generateAbbrA(String word) {
        List<String> lstAbbr = new ArrayList<String>();
        
        generateAbbrHelperA(word.toCharArray(), lstAbbr, 0, "", 0);
        
        return lstAbbr;
    }
    
    private void generateAbbrHelperA(char[] carr, List<String> lstAbbr, int nPos, String sCur, int nCount) {
        if (nPos == carr.length) {
            if (nCount > 0) sCur = sCur + nCount;
            
            lstAbbr.add(sCur);
        } else {
        	generateAbbrHelperA(carr, lstAbbr, nPos+1, sCur, nCount+1);
            
        	generateAbbrHelperA(carr, lstAbbr, nPos+1, nCount == 0? sCur+carr[nPos]:sCur+nCount+carr[nPos], 0);
        }
    }


}
