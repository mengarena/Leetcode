package com.leet;

//Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
//
//For example,
//"A man, a plan, a canal: Panama" is a palindrome.
//"race a car" is not a palindrome.
//
//Note:
//Have you consider that the string might be empty? This is a good question to ask during an interview.
//
//For the purpose of this problem, we define empty string as valid palindrome.

public class ValidPalindrome {

	public ValidPalindrome() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String s = "0P";
		boolean bRet = isPalindrome(s);
		
		if (bRet) {
			System.out.println(s + " IS Palindrome !");
		} else {
			System.out.println(s + " IS NOT Palindrome !");			
		}
	}

    
	public boolean isPalindrome(String s) {
        if (s == null) return false;
        int nLen = s.length();
        if (nLen <= 1) return true;
        int i = 0, j = nLen-1;
        char c1, c2;
        
        while (i < j) {
        	c1 = s.charAt(i);
        	c2 = s.charAt(j);
        	
        	if (((c1 >= 'a' && c1 <= 'z') || (c1 >= 'A' && c1 <= 'Z') || (c1 >= '0' && c1 <= '9')) && 
        	    ((c2 >= 'a' && c2 <= 'z') || (c2 >= 'A' && c2 <= 'Z') || (c2 >= '0' && c2 <= '9'))) {
        		
        		if (c1 > '9' && c2 > '9') {
        			if (Math.abs(c1-c2) != 0 && Math.abs(c1-c2) != 32) return false;
        		} else if (c1 <= '9' && c2 <= '9') {
        			if (c1 - c2 != 0) return false;
        		} else {
        			return false;
        		}
        		
        		i = i + 1;
        		j = j - 1;
        		
        	} else if ((c1 >= 'a' && c1 <= 'z') || (c1 >= 'A' && c1 <= 'Z') || (c1 >= '0' && c1 <= '9'))  {
        		j = j - 1;
        	} else if ((c2 >= 'a' && c2 <= 'z') || (c2 >= 'A' && c2 <= 'Z')  || (c2 >= '0' && c2 <= '9')) {
        		i = i + 1;
        	} else {
        		i = i + 1;
        		j = j - 1;
        	}
        }
        
        return true;
    }

/*	
	//Works, not efficient enough when string is extremely long
    public boolean isPalindrome(String s) {
        if (s == null) return false;
        if (s.length() <= 1) return true;
    	
        String sNew = s.toLowerCase();
        int nOrgLen = sNew.length();
        int i,n;
        int nActualLen = 0;
        String sPure = "";
        	
        for (i=0; i<nOrgLen; i++) {
        	if (sNew.charAt(i) >= 'a' && sNew.charAt(i) <= 'z') sPure = sPure + sNew.charAt(i);
        }
        
        nActualLen = sPure.length();
        n = nActualLen/2;
        
        for (i=0; i<n; i++) {
        	if (sPure.charAt(i) != sPure.charAt(nActualLen-1-i)) return false;
        }
        
    	return true;
    }
*/    
	
}
