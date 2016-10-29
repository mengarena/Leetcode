package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
//
//"abc" -> "bcd" -> ... -> "xyz"
//Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
//
//For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
//Return:
//
//[
//  ["abc","bcd","xyz"],
//  ["az","ba"],
//  ["acef"],
//  ["a","z"]
//]
//		
//Note: For the return value, each inner list's elements must follow the lexicographic order.

//Google, Uber
//Easy
public class GroupShiftedStrings {

	public GroupShiftedStrings() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		String[] strings = {"bcd", "abc",  "acef", "xyz", "az", "ba", "a", "z"};
			
		List<List<String>> lstlstGroups = groupStrings(strings);
		
		System.out.println();
	}

    //AC: 90%
    public List<List<String>> groupStrings(String[] strings) {              
        int n = strings.length;
        int i;
        
        Arrays.sort(strings);
        
        Map<String, List<String>> hm = new HashMap<String, List<String>>();   //Key, String
       
        for (i=0; i<n; i++) {
        	String sShifted = shiftString(strings[i]);   //Convert every string to key whose starts with 'a'
        	if (hm.containsKey(sShifted)) {
        		hm.get(sShifted).add(strings[i]);
        	} else {
        		List<String> lstGroup = new ArrayList<String>();
        		lstGroup.add(strings[i]);
        		hm.put(sShifted, lstGroup);
        	}
        }
        
    
        return new ArrayList<List<String>>(hm.values());
    }
	
    
    private String shiftString(String s) {
    	if (s.charAt(0) == 'a') return s;
    	int nDiff = s.charAt(0) - 'a';   //For each character, how much to convert
    	StringBuilder sb = new StringBuilder();
    	
    	for (int i=0; i<s.length(); i++) {
    		int c = s.charAt(i) - nDiff;
    		
    		if (c < 'a') {
    			c = c + 26;
    		}
    		
    		sb.append((char)c);
    	}
    	
    	return sb.toString();
    }
    
    
    
    
	//AC: better  10%
    public List<List<String>> groupStringsB(String[] strings) {      
        Arrays.sort(strings, new Comparator<String>() {
        	public int compare(String a, String b) {
        		if (a.length() != b.length()) return a.length() - b.length();
        		return  a.compareTo(b);
        	}
        });
        
        int n = strings.length;
        int i,j;
        boolean bExist = false;
        int nSize = 0;
        List<List<String>> lstlstGroups = new ArrayList<List<String>>();
        
        for (i=0; i<n; i++) {
        	bExist = false;

        	nSize = lstlstGroups.size();
        	
        	for (j=0; j<nSize; j++) {
        		String stmp = lstlstGroups.get(j).get(0);
        		if (strings[i].length() > stmp.length()) {
        			continue;
        		} else if (strings[i].length() < stmp.length()) {
        			break;
        		}
        		
        		if (isShiftable(stmp, strings[i])) {
        			lstlstGroups.get(j).add(strings[i]);
        			bExist = true;
        			break;
        		}
        		
        	}
        	        	
        	if (bExist == false) {
        		List<String> lstGroup = new ArrayList<String>();
        		lstGroup.add(strings[i]);
        		lstlstGroups.add(lstGroup);
        	}
        }
                
        return lstlstGroups;
    }	
	
	
    
	//AC, but not efficient 5%
    public List<List<String>> groupStringsA(String[] strings) {      
        Arrays.sort(strings, new Comparator<String>() {
        	public int compare(String a, String b) {
        		if (a.length() != b.length()) return a.length() - b.length();
        		return  a.compareTo(b);
        	}
        });
        
        int n = strings.length;
        int i;
        boolean bExist = false;
        Map<String, List<String>> hm = new HashMap<String, List<String>>();
        
        for (i=0; i<n; i++) {
        	bExist = false;
        	Set<String> keys = hm.keySet();
        	
        	for (String key: keys) {
        		if (isShiftable(key, strings[i])) {
        			List<String> lstGroup = hm.get(key);
        			lstGroup.add(strings[i]);
        			hm.put(key, lstGroup);
        			bExist = true;
        			break;
        		}
        	}
        	
        	if (bExist == false) {
        		List<String> lstGroup = new ArrayList<String>();
        		lstGroup.add(strings[i]);
        		hm.put(strings[i], lstGroup);
        	}
        }
        
        List<List<String>> lstlstGroups = new ArrayList<List<String>>(hm.values());
        
        return lstlstGroups;
    }	
    
    
    private boolean isShiftable(String s, String t) {
    	if (s.length() != t.length()) return false;
    	if (s.length() <= 1) return true;
    	
    	int nDiff = (s.charAt(0) - t.charAt(0) + 26) % 26;
    	
    	for (int i=1; i<s.length(); i++) {
    		if (((s.charAt(i) - t.charAt(i) + 26) % 26) != nDiff) return false;
    	}
    	
    	return true;
    }
}
