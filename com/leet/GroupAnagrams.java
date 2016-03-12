package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Given an array of strings, group anagrams together.
//
//For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
//Return:
//
//[
//  ["ate", "eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//]
//Note:
//For the return value, each inner list's elements must follow the lexicographic order.
//All inputs will be in lower-case.

public class GroupAnagrams {

	public GroupAnagrams() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		String[] strs = {"eat", "ate", "tea", "tan",  "nat", "bat"};
//		String[] strs = {"tea", "tan", };

		List<List<String>> lstlstGroup = groupAnagrams(strs);
		
		for (List<String> lstGroup:lstlstGroup) {
			System.out.print("[");
			for (String sGroup:lstGroup) {
				System.out.print(sGroup+",");
			}
			System.out.println("]");
		}
		
	}

    public List<List<String>> groupAnagrams(String[] strs) {
    	List<List<String>> lstlstGroup = new ArrayList<List<String>>();
    	if (strs == null || strs.length == 0) return lstlstGroup;
    	int n = strs.length;
    	int i;
    	
    	HashMap<String, List<String>> hMap = new HashMap<String, List<String>>();
    	
    	Arrays.sort(strs);
    	
    	for (i=0; i<n; i++) {
    		char[] carr = strs[i].toCharArray();
    		Arrays.sort(carr);
    		String sNew = new String(carr);
    		List<String> lstStr = new ArrayList<String>();
    		
    		if (hMap.containsKey(sNew)) {
    			lstStr = hMap.get(sNew);
    			lstStr.add(strs[i]);
    			hMap.replace(sNew, lstStr);
    		} else {
    			lstStr.add(strs[i]);
    			hMap.put(sNew, lstStr);
    		}
    	}
    	
    	Set<String> setGroup = hMap.keySet();
    	Iterator<String> it = setGroup.iterator();
    	
    	while (it.hasNext()) lstlstGroup.add(hMap.get(it.next()));
    	
    	return lstlstGroup;
    }
	
 /*   
    public List<List<String>> groupAnagrams(String[] strs) {
    	List<List<String>> lstlstGroup = new ArrayList<List<String>>();
    	if (strs == null || strs.length == 0) return lstlstGroup;
    	int n = strs.length;
    	int i;
    	int nIdx;
    	List<HashMap<Character, Integer>> lstMap = new ArrayList<HashMap<Character, Integer>>();
    	
    	Arrays.sort(strs);
    	    	
    	for (i=0; i<n; i++) {
    		HashMap<Character, Integer> hMap = getMap(strs[i]);
    		List<String> lstGroup = new ArrayList<String>();
    		    		
    		nIdx = -1;
    		
    		for (int j=0; j<lstMap.size(); j++) {
    			if (isMapSame(hMap, lstMap.get(j))) {
    				nIdx = j;
    				break;
    			}
    		}
    		    		
    		if (nIdx == -1) {
    			lstMap.add(hMap);
    			lstGroup.add(strs[i]);
    			lstlstGroup.add(lstGroup);
    		} else {
    			lstGroup = lstlstGroup.get(nIdx);
    			lstGroup.add(strs[i]);
    			lstlstGroup.set(nIdx, lstGroup);
    		}
    		
    	}
    	
    	
    	return lstlstGroup;
    }
 */ 	
	
    public HashMap<Character, Integer> getMap(String sStr) {
    	HashMap<Character, Integer> hMap = new HashMap<Character, Integer>();
    	
    	for (int i=0; i<sStr.length(); i++) {
    		if (hMap.containsKey(sStr.charAt(i))) {
    			hMap.put(sStr.charAt(i), hMap.get(sStr.charAt(i))+1);
    		} else {
    			hMap.put(sStr.charAt(i), 1);
    		}
    	}
    
    	return hMap;
    }
  
    
    public boolean isMapSame(HashMap<Character, Integer> hMapA, HashMap<Character, Integer> hMapB) {
    	Set<Character> setKeyA = hMapA.keySet();
    	Set<Character> setKeyB = hMapB.keySet();
    	
    	if (setKeyA.size() != setKeyB.size()) return false;
    	
    	Iterator<Character> itA = setKeyA.iterator();
    	while (itA.hasNext()) {
    		Character keyA = itA.next();
    		if (!hMapB.containsKey(keyA)) return false;
    		if (hMapA.get(keyA) != hMapB.get(keyA)) return false;
    	}
    	
    	return true;
    }
    
}
