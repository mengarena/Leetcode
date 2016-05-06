package com.leet;

//Implement regular expression matching with support for '.' and '*'.
//
//'.' Matches any single character.
//'*' Matches zero or more of the preceding element.
//
//The matching should cover the entire input string (not partial).
//
//The function prototype should be:
//bool isMatch(const char *s, const char *p)
//
//Some examples:
//isMatch("aa","a") ¡ú false
//isMatch("aa","aa") ¡ú true
//isMatch("aaa","aa") ¡ú false
//isMatch("aa", "a*") ¡ú true
//isMatch("aa", ".*") ¡ú true
//isMatch("ab", ".*") ¡ú true
//isMatch("aab", "c*a*b") ¡ú true


//Google, Facebook
public class RegularExpressionMatching {

	public RegularExpressionMatching() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void run() {
		System.out.println(isMatch("","."));    //false
		System.out.println(isMatch("","*"));    //false
		System.out.println("-------1------------");
		
		System.out.println(isMatch("aa","a"));    //false
		System.out.println(isMatch("aa","aa"));   //true
		System.out.println(isMatch("aaa","aa"));  //false
		System.out.println("-------2------------");
		
		System.out.println(isMatch("a", "."));      //true
		System.out.println(isMatch("ab", "."));     //false
		System.out.println(isMatch("aa", "a*"));  //true
		System.out.println(isMatch("ab", "a*"));  //false
		System.out.println("-------3------------");
		
		System.out.println(isMatch("aa", ".*"));  //true
		System.out.println(isMatch("ab", ".*"));  //true
		System.out.println(isMatch("ab", "."));   //false
		System.out.println(isMatch("a", "*"));   //false
		System.out.println(isMatch("ab", "**"));  //false
		System.out.println(isMatch("ab", "*a*b"));  //false
		System.out.println(isMatch("ab", "*b*"));  //false
		System.out.println("-------4------------");
		
		System.out.println(isMatch("aab", "c*a*b"));  //true
		System.out.println(isMatch("aabcd", "a*aabcd"));   //true
		System.out.println(isMatch("aabcd", "aa*aabcd"));  //false
		System.out.println(isMatch("aabcd", "a*abcd"));    //true
		System.out.println(isMatch("aaabcd", "a*aabcd"));  //true
		System.out.println(isMatch("aaabcd", "aa*aabcd"));  //true
		System.out.println("-------5------------");
	
		System.out.println(isMatch("aaabcd", "a*aaabcd"));   //true
		System.out.println(isMatch("aaabcd", "a*a*abcd"));  //true*
		System.out.println(isMatch("aaabcd", "a*aa*abcd"));  //true
		System.out.println(isMatch("aaabcd", "a*aa**abcd"));  //true
		System.out.println(isMatch("aaabcd", "a*a*ab*bcd"));  //true*
		System.out.println("-------6------------");
		
		
		System.out.println(isMatch("bbbba", ".*a*a"));  //true
		
		System.out.println(isMatch("ab", "a."));  //true
	}


	//ACC:  71%   (DP)
    public boolean isMatch(String s, String p) {
    	if ((s == null || s.length() == 0) && (p == null || p.length() == 0)) return true;
    	if (s != null && s.length() > 0 && (p == null || p.length() == 0)) return false;
        int lenS = s.length();
        int lenP = p.length();
        
        boolean dp[][] = new boolean[lenS+1][lenP+1];   //Default false, Whether s[0~i] p[0~j] matches,    i-1 in s corresponds i in dp
        int i, j;
       
        dp[0][0] = true;
        
        for (i=1; i<=lenS; i++) dp[i][0] = false;
        
        for (j=2; j<=lenP; j++) {
        	if (p.charAt(j-1) == '*' && dp[0][j-2] == true) dp[0][j] = true;   //dp[0][j-2] is by p.charAt(j-3); when p.charAt(j-1) = '*', it can cancel p.charAt(j-2)
        }
    	
        for (i=1; i<=lenS; i++) {
        	for (j=1; j<=lenP; j++) {
        		
        		if (p.charAt(j-1) == '.' || p.charAt(j-1) == s.charAt(i-1)) dp[i][j] = dp[i-1][j-1];
        		
        		else if (j >= 2 && p.charAt(j-1) == '*') {    //Sample: P:  mbcda* 
        			if (p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') {
        				dp[i][j] = dp[i-1][j] || dp[i][j-2];    //dp[i][j-2]: not using "a*" part,   
        				                                        //dp[i-1][j]:  "a*" part has been used/matched until s[i-2] (there is one index gap between dp and s/p)
        				                                        //             "a*" part always could give a 'a" to match s[i-1], so if s[i-2] and p[j-1] matches, 
        				                                        //              s[i-1] and p[j-1] also matches
        			} else {
        				dp[i][j] = dp[i][j-2];   //in P:   mbcda* ,  "a*" part not used (i.e. '*' is consumed with 'a', because "a" does not match with s[i-1]
        			}
        		} 
        	}
        }
        
        return dp[lenS][lenP];
    }
	
	
	
	
	
	
	
	//ACC: 18%
    public boolean isMatchA(String s, String p) {
        if ((s == null || s.length() == 0) && (p == null || p.length() == 0)) return true;
        if (s != null && s.length() > 0 && (p == null || p.length() == 0)) return false;
        int lenS = s.length();
        int lenP = p.length();
        
        if (lenP == 1) {        	
        	if (lenS == 1 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) return true;
        	if (lenS == 0 && p.charAt(0) == '*') return true;
        	
       		return false;
        }
        
        //lenP > 1
        if (p.charAt(1) != '*') {
        	if (lenS == 0 || (s.charAt(0) != p.charAt(0) && p.charAt(0) != '.')) return false;
        	return isMatch(s.substring(1), p.substring(1));  //First character matched, check remaining
        	
        } else {
        	//For case: S:  aaabc     P:  a*aaabc
        	if (isMatch(s, p.substring(2))) return true;    //Try not using the substring before "*", i.e. "a*" --> ""
        	
        	int i=0;
        	//Try different number of character from S to match with "a*" part in P
        	while (lenS > 0 && i < lenS && (s.charAt(i) == p.charAt(0) || p.charAt(0) == '.')) {
        		if (isMatch(s.substring(i+1), p.substring(2))) return true;
        		i++;
        	}
        }
        
        return false;
    }

    
    
    
    
/*	
    public boolean isMatch(String s, String p) {
        if ((s == null && p == null) || (s.length() == 0 && p.length() == 0)) return true;
        if (s != null && s.length() > 0 && (p == null || p.length() == 0)) return false;
        int lenS = s.length();
        int lenP = p.length();
        char carrS[] = s.toCharArray();
        char carrP[] = p.toCharArray();
        int i=0, j=0;
        int unmatchCnt = 0;
        char prev = 0;
        int equalStartPosS = -1;
        int starPos = -1;
        boolean prevStar = false;
        
        while (i < lenS) {
        	if (j < lenP && carrP[j] == '.') {
        		if (unmatchCnt > 0) return false;
        		prevStar = false;
        		prev = carrP[j];
        		
        		if (equalStartPosS == -1) {
        			equalStartPosS = i;
        		} else {
        			if (starPos == -1) {
        				equalStartPosS++;
        			}
        		}
        	
        		i++;
        		j++;
        	} else if (j < lenP && carrP[j] == '*') {
        		if (prevStar == true) {
        			j++;
        			continue;
        		}
        		
        		prevStar = true;
        		
        		if (unmatchCnt > 0) {  //Must cosume this "*" to get ride of the unmatch
        			unmatchCnt = 0;
        			prev = 0;
        			j++;
        			continue;
        		}
        		
        		if (prev == 0) {   //"*" occurs at the head, should be ignored
        			j++;
        			continue;
        		}
        		
        	    if (carrS[i] != prev && prev != '.') {
        	    	if (equalStartPosS != -1) {
        	    		starPos = j;
            			j++;
            			i = equalStartPosS;
            			equalStartPosS++;
        	    	} else {
	        	    	prev = 0;
	        	    	equalStartPosS = -1;
	        	    	starPos = -1;
	        	    	
	        	    	j++;
        	    	}

        	    } else {
        	    	starPos = j;
        	    	i++;
        	    	j++;
        	    }
        		
        	} else if (j < lenP && carrP[j] == carrS[i]) {
        		if (unmatchCnt > 0) return false;
        		
        		prevStar = false;
        		
        		prev = carrP[j];
        		
        		if (equalStartPosS == -1) {
        			equalStartPosS = i;
        		} else {
        			if (starPos == -1) {
        				equalStartPosS++;
        			}
        		}
        		
        		i++;
        		j++;
        	} else if (j < lenP && carrP[j] != carrS[i]) {
        		if (unmatchCnt > 0) return false;
        		
        		if (prevStar == true && prev == '.') {
        			while (j < lenP && carrS[i] != carrP[j]) j++;
        			starPos = -1;
        			unmatchCnt = 0;
        			prevStar = false;
        		} else {
        		
	        		prevStar = false;
	        		
	        		if (starPos != -1) {
	        			j = starPos+1;
	        			i = equalStartPosS;
	        			equalStartPosS++;
	        		} else {
	        			unmatchCnt++;
	        			j++;
	        		}
        		}
        	} else {
        		break;
        	}
        	
        }
        
        if (j<lenP) {
        	for (;j<lenP; j++) {
        		if (carrP[j] != '*') return false;
        	}
        }
        
        if (i < lenS) return false;
        
        return true;
    }
*/
    
}
