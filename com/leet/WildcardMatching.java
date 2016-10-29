package com.leet;

//Implement wildcard pattern matching with support for '?' and '*'.
//
//'?' Matches any single character.
//'*' Matches any sequence of characters (including the empty sequence).
//
//The matching should cover the entire input string (not partial).
//
//The function prototype should be:
//bool isMatch(const char *s, const char *p)
//
//Some examples:
//isMatch("aa","a") → false
//isMatch("aa","aa") → true
//isMatch("aaa","aa") → false
//isMatch("aa", "*") → true
//isMatch("aa", "a*") → true
//isMatch("ab", "?*") → true
//isMatch("aab", "c*a*b") → false

//Google, Facebook, Snapchat, Twitter, Two Sigma
//Hard
public class WildcardMatching {

	public WildcardMatching() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		System.out.println(isMatch("abcefkdehkam", "abc*d*ka"));  //false
		System.out.println(isMatch("abcefkdehkaka", "abc*d*ka"));  //true
		System.out.println();
		System.out.println(isMatch("ab", ""));  //false
		System.out.println(isMatch("aa", "a"));  //false
		System.out.println(isMatch("aa", "aa"));  //true
		System.out.println(isMatch("aaa", "aa")); //false
		System.out.println();
		System.out.println(isMatch("aa", "*")); //true
		System.out.println(isMatch("aa", "a*")); //true
		System.out.println(isMatch("ab", "??")); //true
		System.out.println(isMatch("ab", "???")); //false
		System.out.println(isMatch("ab", "?*")); //true
		System.out.println();
		System.out.println(isMatch("aab", "c*a*b")); //false
		System.out.println(isMatch("aab", "a*c*b")); //false
		System.out.println(isMatch("aab", "a*a*b")); //true
		System.out.println();
		System.out.println(isMatch("ab", "*a")); //false
		System.out.println(isMatch("abc", "a*b")); //false
		System.out.println(isMatch("abc", "a*a")); //false
		System.out.println(isMatch("abc", "a*c")); //true
		System.out.println();
		System.out.println(isMatch("ab", "a*")); //true
		System.out.println(isMatch("abc", "?a*")); //false
		System.out.println(isMatch("abc", "a?*")); //true
		System.out.println(isMatch("abc", "?b?")); //true
		System.out.println(isMatch("abc", "?*b?")); //true
		System.out.println(isMatch("abc", "**a**c")); //true
		System.out.println(isMatch("abc", "**a**c?")); //false
		System.out.println();
		System.out.println(isMatch("abcede", "abc*d"));  //false
		System.out.println(isMatch("aabcd", "*ab*"));  //true
		System.out.println(isMatch("babcd", "*ab*"));  //true
	}
	
	
	//AC
    public boolean isMatch(String s, String p) {
        if ((s == null || s.isEmpty()) && (p == null || p.isEmpty())) return true;
        if ((s != null && !s.isEmpty()) && (p == null || p.isEmpty())) return false;
        int ns = s.length();
        int np = p.length();
        char[] carrs = s.toCharArray();
        char[] carrp = p.toCharArray();
        int i = 0, j = 0;
        int nstarPos = -1;
        int nsPos = -1;
        
        while (i < ns) {
        	if ((j < np) && (carrp[j] == '?' || carrp[j] == carrs[i])) {
        		i++;
        		j++;
        	} else if (j < np && carrp[j] == '*') {  
        		//When meets "*", save position of "*" in pattern
        		//And also save the corresponding position in s
        		//And advance position in p
        		nstarPos = j;
        		nsPos = i;
        		j++;
        	} else if (nstarPos != -1) {  //To here, means carrp[j] != carrs[i], so before proceeding on pattern, 
        		                          //must match those unmatched in s with the pattern by using the "*", so the position in pattern is fixed at nstarPos+1
        		//In case, for example, 
        		//s = d e h k a k a
        		//p = d *   k a
        		//So the "ka" in p could match first "ka" or second "ka" in s
        		//here below, j will always set to the position of 'k' in p
        		//It will first compare first "ka" in s with "ka" in p (i.e. * = "eh"), and nsPos will be at the first "k" in s
        		//After compared the first "ka", p ends; while s does not ends
        		//So the while loop come to here again
        		//And it then try to compare second "ka" in s with "ka" in p (i.e. * = "ehka")
        		j=nstarPos+1;  //The position after the "*"
        		nsPos++;
        		i=nsPos;
        	} else {  //carrp[j] != carrs[i] and no "*" before current position in p, so not matched
        	    return false;
        	}
        }
        
        //If s ends, while p has not end
        //If all tail of p are "*" valid, otherwise, invalid
    	for (;j<np; j++) {
    		if (carrp[j] != '*') return false;
    	}
        
        return true;
    }
	
	
    
    
    
    
    
    
/*    
    public boolean isMatch(String s, String p) {
        if ((s == null || s.isEmpty()) && (p == null || p.isEmpty())) return true;
        if ((s != null && !s.isEmpty()) && (p == null || p.isEmpty())) return false;
    	StringBuilder sb = new StringBuilder();
    	StringBuilder sbs = new StringBuilder();
    	int ns = s.length();
    	int np = p.length();
        int npLetterCnt = 0;
        int npQueCnt = 0;
        int npStarCnt = 0;
        char[] carrp = p.toCharArray();
        int i;
        
        for (i=0; i<np; i++) {
        	if (carrp[i] == '*') {
        		npStarCnt++;
        	} else if (carrp[i] == '?') {
        		npQueCnt++;
        		sbs.append("?");
        	} else {
        		npLetterCnt++;
        		sb.append(carrp[i]);
        		sbs.append(carrp[i]);
        	}
        }
        
        String sValid = sb.toString();
        String sValidQue = sbs.toString();
        
        if (npLetterCnt > ns) return false;
        
        if (npLetterCnt == ns) {
        	if (npQueCnt > 0) return false;	
            return s.equals(sValid);
        }
        
        //Here below:  npLetterCnt < ns
        if (sValidQue.length() > ns) return false;
        
        if (sValidQue.length() == ns) {
        	return isSubsequence_QuestionMark(s, sValidQue);
        }
        
        //sValidQue.length() < ns
        if (npStarCnt == 0) return false;
        if (np == ns) {
        	return isEqualbyPosition(s.toCharArray(), carrp);
        } else {
        	return isSubsequence_QuestionMark(s, sValidQue);
        }
    }

    private boolean isEqualbyPosition(char[] carrs, char[] carrp) {
    	for (int i=0; i<carrs.length; i++) {
    		if (carrp[i] == '?' || carrp[i] == '*') continue;
    		
    		if (carrp[i] != carrs[i]) return false;
    	}
    	
    	return true;
    }
    
    //Check whether p is a subsequence of s
    private boolean isSubsequence_QuestionMark(String s, String p) {
    	if (p == null || p.length() == 0) return true;
    	if ((s == null || s.length() == 0) && (p != null && p.length() != 0)) return false;
    	int ns = s.length();
    	int np = p.length();
    	if (np > ns) return false;
    	char[] carrs = s.toCharArray();
    	char[] carrp = p.toCharArray();
    	int i = 0, j = 0;
    	
    	while (i < ns && j < np) {
    		if (carrp[j] == '?') {
    			i++;
    			j++;
    		} else if (carrs[i] != carrp[j]) {
    			i++;
    		} else {
    			i++;
    			j++;
    		}
    	}
    	
    	if (j == np) return true;
        
    	return false;
    }
    
    
    //Check whether p is a subsequence of s
    private boolean isSubsequence(String s, String p) {
    	if (p == null || p.length() == 0) return true;
    	if ((s == null || s.length() == 0) && (p != null && p.length() != 0)) return false;
    	int ns = s.length();
    	int np = p.length();
    	if (np > ns) return false;
    	if (np == ns) return s.equals(p);
    	char[] carrs = s.toCharArray();
    	char[] carrp = p.toCharArray();
    	int i = 0, j = 0;
    	
    	while (i < ns && j < np) {
    		if (carrs[i] != carrp[j]) {
    			i++;
    		} else {
    			i++;
    			j++;
    		}
    	}
    	
    	if (j == np) return true;
        
    	return false;
    }
*/
}
