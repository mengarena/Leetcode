package com.leet;

import java.util.Vector;

//Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
//
//For example,
//S = "ADOBECODEBANC"
//T = "ABC"
//Minimum window is "BANC".
//
//Note:
//If there is no such window in S that covers all characters in T, return the empty string "".
//
//If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

//Facebook, Linkedin, Snapchat, Uber
//Hard
public class MinimumWindowSubstring {

	public MinimumWindowSubstring() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//String s = "ADOBECODEBANC";
		//String t = "ABC";
		String s = "a";
		String t = "a";

		System.out.println(minWindow(s, t));
	
	}
	
	
	//Accepted, Even better
	//O(n)
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0) return "";
        if (t == null || t.length() == 0) return s.substring(0, 1);
        int nt = t.length();
        int ns = s.length();
        int start = 0, end = 0;
        int counter = nt;
        int minStart = 0;
        int minEnd = 0;
        int minLen = Integer.MAX_VALUE;
        int[] carr = new int[128];
        
        for (int i=0; i<nt; i++) carr[t.charAt(i)]++;
        
        //For those characters not in String t, their corresponding carr[i] could at most be 0 in the following while loop (decrease at one place in outer loop and recover at the inner loop)
        while (end < ns) {
            if (carr[s.charAt(end)] > 0) {
                counter--;
            }
            
            carr[s.charAt(end)]--;
            
            end++;
            
            while (counter == 0) {
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                    minEnd = end;
                }
                
                carr[s.charAt(start)]++;
                
                if (carr[s.charAt(start)] > 0)
                    counter++;
                    
                start++;    
            }
        }
        
        if (minLen != Integer.MAX_VALUE) return s.substring(minStart, minEnd);
        
        return "";
    }
	
	
	
	//Accepted
    public String minWindowA(String s, String t) {
        if (s == null || s.length() == 0) return "";
        if (t == null || t.length() == 0) return s.substring(0, 1);
                
        int nLenS = s.length();
        int nLenT = t.length();
        
        if (nLenT > nLenS) return "";
        
        int nStart = 0;
        int nEnd = nLenT-1;
        int i;
        int tarr[] = new int[52];
        int sarr[] = new int[52];
        int nMinLen = Integer.MAX_VALUE;
        int nStartPos = -1, nEndPos = -1;

        for (i=0; i<nLenT; i++) {
        	if (t.charAt(i) <= 'Z') {
        		tarr[t.charAt(i)-'A']++;
        	} else {
        		tarr[t.charAt(i)-'a'+26]++;
        	}
        }
        
        for (i=nStart; i<=nEnd; i++) {
        	if (s.charAt(i) <= 'Z') {
        		sarr[s.charAt(i)-'A']++;
        	} else {
        		sarr[s.charAt(i)-'a'+26]++;
        	}
        }
        
        while (nEnd < nLenS && nStart + nLenT <= nLenS) {
            if (isSame(sarr, tarr)) {
                if (nEnd-nStart+1 >= nLenT) {
                	if (nMinLen > nEnd-nStart + 1) {
                		nMinLen = nEnd-nStart + 1;
                		nStartPos = nStart;
                		nEndPos = nEnd;
                	}
                    
                    nStart = nStart + 1;
                    if (s.charAt(nStart-1) <= 'Z') {
                    	sarr[s.charAt(nStart-1) - 'A']--;
                    } else {
                    	sarr[s.charAt(nStart-1) - 'a'+26]--;
                    }                   
                } else {
                    if (nEnd+1 <= nLenS-1) {
                        nEnd = nEnd+1;
                        if (s.charAt(nEnd) <= 'Z') {
                        	sarr[s.charAt(nEnd)-'A']++;
                        } else {
                        	sarr[s.charAt(nEnd)-'a' + 26]++;	
                        }
                        
                    } else {
                        break;
                    }
                }
            } else {
                if (nEnd+1 <= nLenS-1) {
                    nEnd = nEnd+1;
                    if (s.charAt(nEnd) < 'Z') {
                    	sarr[s.charAt(nEnd)-'A']++;	
                    } else {
                    	sarr[s.charAt(nEnd)-'a'+26]++;
                    }
                    
                } else {
                    break;
                }
            }
        }
        
        if (nStartPos == -1) return "";
        
        return s.substring(nStartPos, nEndPos+1);
    }
    
    private boolean isSame(int[] sarr, int[] tarr) {
        for (int i=0; i<tarr.length; i++) {
            if (tarr[i] != 0 && sarr[i] < tarr[i]) return false;
        }
        
        return true;
    }
}
