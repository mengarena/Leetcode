package com.leet;

//Given two strings S and T, determine if they are both one edit distance apart.

//Uber, Facebook
public class OneEditDistance {

	public OneEditDistance() {
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		System.out.println(isOneEditDistance("ab", "acb"));
	}

	//ACC
	public boolean isOneEditDistanceM(String s, String t) {
	    for (int i = 0; i < Math.min(s.length(), t.length()); i++) { 
	    	if (s.charAt(i) != t.charAt(i)) {
	    		if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
	    			return s.substring(i + 1).equals(t.substring(i + 1));
			else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
				return s.substring(i).equals(t.substring(i + 1));	        	
			else // s is longer than t, so the only possibility is deleting one char from s
				return t.substring(i).equals(s.substring(i + 1));
	    	}
	    } 
	    
	    //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t 
	    return Math.abs(s.length() - t.length()) == 1;        
	}
	
	//ACC
	public boolean isOneEditDistanceK(String s, String t) {
	    if(Math.abs(s.length()-t.length()) > 1) return false;
	    if(s.length() == t.length()) return isOneModify(s,t);
	    if(s.length() > t.length()) return isOneDel(s,t);
	    return isOneDel(t,s);
	}
	public boolean isOneDel(String s,String t){
	    for(int i=0,j=0;i<s.length() && j<t.length();i++,j++){
	        if(s.charAt(i) != t.charAt(j)){
	            return s.substring(i+1).equals(t.substring(j));
	        }
	    }
	    return true;
	}
	public boolean isOneModify(String s,String t){
	    int diff =0;
	    for(int i=0;i<s.length();i++){
	        if(s.charAt(i) != t.charAt(i)) diff++;
	    }
	    return diff==1;
	}
	
	
	
	//AC: 30%
    public boolean isOneEditDistance(String s, String t) {
        if (s == null && t == null) return false;
        if (s == null || s.length() == 0) {
        	if (t.length() == 1) return true;
        	return false;
        }
        
        if (t == null || t.length() == 0) {
        	if (s.length() == 1) return true;
        	return false;
        }
        
        if (s.length() == t.length()) {
        	int nDiffCount = 0;
        	
        	for (int i=0; i<s.length(); i++) {
        		if (s.charAt(i) != t.charAt(i)) {
        			nDiffCount++;
        			if (nDiffCount > 1) break;
        		}
        		
        	}
        	
        	if (nDiffCount == 1) return true;
        	
        	return false;
        }
        
        if (Math.abs(s.length()-t.length())==1) {
        	if (s.length() > t.length()) {
                return isEditable(s, t);
        	} else {
                return isEditable(t, s);
        	}
        	
        } else {
        	return false;
        }
    }

    
    private boolean isEditable(String s, String t) {
    	int i=0; 
    	int j=0;
    	
    	int ns = s.length();
    	int nt = t.length();
    	int nDiffCnt = 0;
    	
    	while (i < ns && j < nt) {
    		if (s.charAt(i) == t.charAt(j)) {
    			i++;
    			j++;
    		} else {
    			nDiffCnt++;
    			
    			if (nDiffCnt > 1) return false;
    			
    			i++;
    		}
    	}
    	
    	return true;
    }
}
