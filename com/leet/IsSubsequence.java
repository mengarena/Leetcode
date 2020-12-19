package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a string s and a string t, check if s is subsequence of t.
//
//You may assume that there is only lower case English letters in both s and t. 
//t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).
//
//A subsequence of a string is a new string which is formed from the original string by 
//deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. 
//(ie, "ace" is a subsequence of "abcde" while "aec" is not).
//
//Example 1:
//s = "abc", t = "ahbgdc"
//Return true.
//
//Example 2:
//s = "axc", t = "ahbgdc"
//Return false.
//
//Follow up:
//If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. 
//In this scenario, how would you change your code?

//Pinterest
public class IsSubsequence {

	public IsSubsequence() {
		// TODO Auto-generated constructor stub
	}
	
    //ACC:  2ms
    //use indexOf to search the position of char in s from String t
//	I checked the origin code of func "indexOf" and "charAt". 
//	These two solution both traversed the char of String one by one to search the first occurrence specific char.
//	The difference is that indexOf only call once function then traversed in "String.value[]" arr, 
//	but we used multiple calling function "charAt" to get the value in "String.value[]" arr.
//	The time expense of calling function made the difference.
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.isEmpty()) return true;
        if (t == null || t.isEmpty() || s.length() > t.length()) return false;
        
        int ns = s.length();
        int prev = 0;
        
        for (int i=0; i<ns; i++) {
            prev = t.indexOf(s.charAt(i), prev);
            if (prev == -1) return false;
            prev++;
        }
        
        return true;
    }
    
	
    //ACC:  23 ms
    //Normal two pointers
    public boolean isSubsequenceA(String s, String t) {
        if (s == null || s.isEmpty()) return true;
        if (t == null || t.isEmpty() || s.length() > t.length()) return false;
        
        int nt = t.length();
        int ns = s.length();
        int idx = 0;
        
        char cs = s.charAt(idx);
        
        for (int i=0; i<t.length(); i++) {
            if (t.charAt(i) == cs) {
                idx++;
                if (idx == ns) return true;
                cs = s.charAt(idx);
            }
        }
        
        return false;
    }
    
    
    //For the follow-up question:
    //Strategy:  use a list list to remember the positions of each character in t
    //Later, for each character in s, do binary search to see whether it is possible to find it based on certain conditions 
    //(i.e. should larger than certain position)
    
    //ACC: 70ms
    public boolean isSubsequenceK(String s, String t) {
        if (s == null || s.isEmpty()) return true;
        if (t == null || t.isEmpty() || s.length() > t.length()) return false;
        
        int nt = t.length();
        int ns = s.length();
        List<List<Integer>> lstlstCharPos = new ArrayList<List<Integer>>();  //26 list for 26 letters
        
        //Attention: in Java, code like List<Integer>[] lstarr = new ArrayList<Integer>[26]; is NOT allowed
        //but, List<Integer>[] lstarr = new ArrayList[26];
        
        int loc = 0;
        int i;
        int idx = 0;
        int globalPos = 0;   //Global position in string t
        int[] posarr = new int[26];   //The current search starting position of each character in lstlstCharPos
        
        for (i=0; i<26; i++) {
            lstlstCharPos.add(null);
        }
        
        //Find the position of each character in t
        for (i=0; i<nt; i++) {
            idx = (int) t.charAt(i)-'a';
            if (lstlstCharPos.get(idx) == null) {
                List<Integer> lstCharPos = new ArrayList<Integer>();
                lstlstCharPos.set(idx, lstCharPos);
            }
            lstlstCharPos.get(idx).add(i);    
        }
        
        //The above part should only be done once, if many S come in
        
        //For each character in s, check whether it is possible to find it from certain position
        for (i=0; i<ns; i++) {
            idx = (int) s.charAt(i)-'a';
            if (lstlstCharPos.get(idx) == null) return false;
            loc = searchPos(lstlstCharPos.get(idx), posarr[idx], globalPos);
            if (loc == -1) return false;
            posarr[idx] = loc+1;   //Next time, in lstlstCharPos.get(idx), should search from this position
            globalPos = lstlstCharPos.get(idx).get(loc) + 1;   //Next should search from this position in t
        }
        
        return true;
    }
    
    //Binary search
    private int searchPos(List<Integer> lstCharPos, int start, int allowedPos) {
        int i = start;
        int j = lstCharPos.size()-1;
        int mid = 0;
        
        while (i < j) {
            mid = i + (j-i)/2;
            
            if (lstCharPos.get(mid) < allowedPos) {    
                i = mid + 1;
            } else {
                j = mid;
            }
            
        }
        
        if (i >= lstCharPos.size()) return -1;
        
        if (lstCharPos.get(i) >= allowedPos) return i;
        
        return -1;
    }
    
    
}
