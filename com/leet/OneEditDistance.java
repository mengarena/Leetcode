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
