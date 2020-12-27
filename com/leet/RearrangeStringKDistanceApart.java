package com.leet;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.
//
//All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
//
//Example 1:
//str = "aabbcc", k = 3
//Result: "abcabc"
//The same letters are at least distance 3 from each other.
//
//Example 2:
//str = "aaabc", k = 3 
//Answer: ""
//It is not possible to rearrange the string.
//
//Example 3:
//str = "aaadbbcc", k = 2
//Answer: "abacabcd"
//Another possible answer is: "abcabcda"
//
//The same letters are at least distance 2 from each other.

//Google
//Hard
public class RearrangeStringKDistanceApart {

	public RearrangeStringKDistanceApart() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String str = "aa";
		int k = 0;
		
		String sRet = rearrangeString(str, k);
		
		System.out.println(sRet);
		
	}
	
	
	
    //ACC:  71%
    //Strategy: use two arrays to remember: 1) remained count of each letter  2) Most left possible position for each letter
    public String rearrangeString(String str, int k) {
        if (str == null || str.length() <= 1 || k == 0) return str;
        int n = str.length();
        int i;
        StringBuilder sb = new StringBuilder();

        int[] narrCnt = new int[26];  //Count of each letter
        int[] narrValidLeftPos = new int[26];   //Most left possible position for each letter
        
        for (i=0; i<n; i++) narrCnt[str.charAt(i)-'a']++;
        
        for (i=0; i<n; i++) {  //Position of each letter
            int candidateLetterIdx = getCandidateLetterIdx(narrCnt, narrValidLeftPos, i);   
		             //Find out which letter is most suitable to be put at position i in the final result
                             //The most suitable letter is the one 1) has largest remained count  2) Its left position is valid
            if (candidateLetterIdx == -1) return "";
            narrCnt[candidateLetterIdx]--;
            narrValidLeftPos[candidateLetterIdx] = i+k;
            sb.append((char)('a' + candidateLetterIdx));
        }
 
        return sb.toString();
    }
    
    private int getCandidateLetterIdx(int[] narrCnt, int[] narrValidLeftPos, int curPos) {
        int candidateLetterIdx = -1;
        int maxCnt = 0;
        
        for (int i=0; i<narrCnt.length; i++) {
            if (narrCnt[i] > 0 && narrCnt[i] > maxCnt && narrValidLeftPos[i] <= curPos) {
                maxCnt = narrCnt[i];
                candidateLetterIdx = i;
            }
        }
        
        return candidateLetterIdx;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    class LetterCnt {
        char c;
        int cnt;
        public LetterCnt(char c, int cnt) {
            this.c = c;
            this.cnt = cnt;
        }
    }
    
    //ACC: 44%
    //Strategy:  Order the letters based on their count
    //To form each k-length segment, always use the letters which has more count
    //
    public String rearrangeStringA(String str, int k) {
        if (str == null || str.length() <= 1 || k == 0) return str;
        int n = str.length();
        int i;
        int maxCnt = 0;
        StringBuilder sb = new StringBuilder();
        int nUsed = 0;
        
        Queue<LetterCnt> pqA = new PriorityQueue<LetterCnt>(new Comparator<LetterCnt>() {
            public int compare(LetterCnt a, LetterCnt b) {
                if (a.cnt == b.cnt) {
                    return a.c - b.c;
                } else {
                    return b.cnt - a.cnt;
                }
            }
        });
        
        //Queue<LetterCnt> pqB = new LinkedList<LetterCnt>();   //Wrong if use it, it cannot guarantee the order of the letters which have same count
        
        Queue<LetterCnt> pqB = new PriorityQueue<LetterCnt>(new Comparator<LetterCnt>() {
            public int compare(LetterCnt a, LetterCnt b) {
                if (a.cnt == b.cnt) {
                    return a.c - b.c;
                } else {
                    return b.cnt - a.cnt;
                }
            }
        });
        
        
        int[] narrCnt = new int[26];
        
        for (i=0; i<n; i++) narrCnt[str.charAt(i)-'a']++;
        
        for (i=0; i<26; i++) {
            if (narrCnt[i] > 0) {
                LetterCnt lc = new LetterCnt((char)('a' + i), narrCnt[i]);
                pqA.offer(lc);
                maxCnt = Math.max(maxCnt, narrCnt[i]);
            }
        }
        
        if (pqA.size() < k && maxCnt > 1) return "";
        
        while (pqA.size() >= k) {
            
            for (i=0; i<k; i++) {
                LetterCnt lcTmp = pqA.poll();
                sb.append(lcTmp.c);
                lcTmp.cnt--;
                nUsed++;
                
                if (lcTmp.cnt > 0) pqB.offer(lcTmp);
            }
            
            while (!pqB.isEmpty()) pqA.offer(pqB.poll());
        }
        
        if (!pqA.isEmpty() && pqA.size() != n-nUsed) return "";
        
        while (!pqA.isEmpty()) sb.append(pqA.poll().c);
        
        return sb.toString();
    }

}
