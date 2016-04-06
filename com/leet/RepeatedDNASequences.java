package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". 
//When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
//
//Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
//
//For example,
//
//Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
//
//Return:
//["AAAAACCCCC", "CCCCCAAAAA"].

//Linkedin
public class RepeatedDNASequences {

	public RepeatedDNASequences() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";		
		String s = "AAAAAAAAAAA";
		//String s = "CGACGCAATTTAGAACGGGCCGCACTGCAACCATTGCTCAGACAACGCATGAGTTAAATTTCACAAGTGATAGTGGCTTGCGAGACGTGGGTTGGTGGTAGCGTACGCCCGCTATTCGCCCCTAACGTGACGGGATTATAAGGTCGCTTCCCGGAATGCGCAGACGAGTCTCCGGTTTAGCCTAGACGTCTCACGCGCGCAAGGCGTCAGTTCTCACTATCTCGCACAGGTGTATTCTATTAGTTATGGGTTCTCACTACAGTCGGTTACTTCCTCATCCATTTCTGCATACGGGTCAACTAACAGTGTCATGGGGTATTGGGAAGGATGCGTTTTTAAACCCTCTCAGTAGCGCGAGGATGCCCACAAATACGACGGCGGCCACGGATCTAATGCGAAGCTAGCGACGCTTTCCAGCAACGAGCGCCCCACTTATGACTGCGTGGGGCGCTCCGCTTTCCTAGAGAACATAGATGGTGTTTTCGAATCGTAACCACTTA";
		
		List<String> lstDnaSeq = findRepeatedDnaSequences(s);
		
		for (String sDnaSeq:lstDnaSeq) System.out.println(sDnaSeq);
	}

	//Strategy:  Convert String into integer and operate integer
    public List<String> findRepeatedDnaSequences(String s) {
    	List<String> lstDnaSeq = new ArrayList<String>();
        if (s == null || s.isEmpty()) return lstDnaSeq;
        int n = s.length();
        int i=0;
        HashSet<Integer> hmDnaSeq = new HashSet<Integer>();
       
        while (i+10 <= n) {
        	String sTmp = s.substring(i, i+10);
        	int nSequenceVal = getIntFromString(sTmp);
        	
        	if (!hmDnaSeq.contains(nSequenceVal)) {
        		hmDnaSeq.add(nSequenceVal);
        	} else {
        		if (!lstDnaSeq.contains(sTmp)) lstDnaSeq.add(sTmp);
        	}
        	
        	i++;
        }
                
        return lstDnaSeq;
    }	
	
    
    private int getIntFromString(String s) {
    	int nRet = 0;
    	
    	for (int i=0; i<s.length(); i++) {
    		nRet = nRet << 2 | getIntFromChar(s.charAt(i));
    	}
    	
    	return nRet;
    }
    
    private int getIntFromChar(char c) {
    	if (c == 'A') {
    		return 0;
    	} else if (c == 'C') {
    		return 1;
    	} else if (c == 'G') {
    		return 2;
    	} else if (c == 'T') {
    		return 3;
    	} else {
    		return 0;
    	}
    }
    
    
    
/* Works, but Exceed Time Limit	
    public List<String> findRepeatedDnaSequences(String s) {
    	List<String> lstDnaSeq = new ArrayList<String>();
        if (s == null || s.isEmpty() || s.length() < 20) return lstDnaSeq;
        int n = s.length();
        int i=0;
        HashMap<String, String> hmDnaSeq = new HashMap<String, String>();
       
        
        while (i+10 <= n) {
        	System.out.println(i + " , " + n);
        	String sTmp = s.substring(i, i+10);
        	String sNew = s.substring(i+10);
        	
        	if (sNew.length() < 10) break;
        	
        	if (sNew.indexOf(sTmp) != -1) {
        		hmDnaSeq.put(sTmp, sTmp);
        	}

        	i++;
        }
        
        Set<String> setDna = hmDnaSeq.keySet();
        
        if (setDna != null) {
        	for (String sDnaSeq:setDna) lstDnaSeq.add(sDnaSeq);
        }
        
        return lstDnaSeq;
    }
*/	
	
}
