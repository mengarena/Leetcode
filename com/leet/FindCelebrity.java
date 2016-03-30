package com.leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. 
//The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
//
//Now you want to find out who the celebrity is or verify that there is not one. 
//The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. 
//You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
//
//You are given a helper function bool knows(a, b) which tells you whether A knows B. 
//Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.
//
//Note: There will be exactly one celebrity if he/she is in the party. 
//Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.


//Linkedin
public class FindCelebrity {
	/* The knows API is defined in the parent class Relation.
    boolean knows(int a, int b); */
	
	public FindCelebrity() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		
	}
	

	//AC: 25%
	//Rule:  knows(A, B)
	//Yes ==> A is NOT celebrity; B might be; need to further confirm with knows(B,A), only when knows(B, A)==false, B will be candidate celebrity
	//No ==> A might be;  B is NOT celebrity
	//
	//Attention: for non-celebrity, they might know each other mutually
    public int findCelebrity(int n) {
        List<Integer> lstCandidate = new ArrayList<Integer>();
        int nCelId = -1;
        int i;
        boolean bAnswer = false;
        int nNonCandidate = -1;
        
        for (i=0; i<n/2; i++) {
        	bAnswer = knows(i, n-1-i);
        	if (bAnswer == true) {
        		if (!knows(n-1-i, i)) {        			
        		    lstCandidate.add(n-1-i);
        		    nNonCandidate = i;
        		}
        	} else {
        		lstCandidate.add(i);
        		nNonCandidate = n-1-i;
        	}
        }
        
        if (n % 2 == 1) {
            if (nNonCandidate != -1) {
        	    bAnswer = knows(nNonCandidate, n/2);
        	    if (bAnswer == true) {
        	        if (!knows(n/2, nNonCandidate)) lstCandidate.add(n/2);
        	    }
            } else {
                lstCandidate.add(n/2);      
            }
        }
        
        nCelId = findCelebrityHelper(lstCandidate);
        if (nCelId != -1) {
        	for (i = 0; i < n; i++) {
        		if (i != nCelId && (knows(i, nCelId) == false || knows(nCelId, i) == true)) return -1;
        	}
        }
        
        return nCelId;
    }	
    
    
    private int findCelebrityHelper(List<Integer> lstCandidate) {
    	 int n = lstCandidate.size();
    	 if (n == 0) return -1;
    	 if (n == 1) return lstCandidate.get(0);
    	 List<Integer> lstCandidateNew = new ArrayList<Integer>();
    	 boolean bAnswer = false;
    	 int i;
    	 int nNonCandidate = -1;
    	 
         for (i=0; i<n/2; i++) {
         	bAnswer = knows(lstCandidate.get(i), lstCandidate.get(n-1-i));
         	if (bAnswer == true) {
         		if (!knows(lstCandidate.get(n-1-i), lstCandidate.get(i))) {
         		    lstCandidateNew.add(lstCandidate.get(n-1-i));
         		    nNonCandidate = lstCandidate.get(i);
         		}
         	} else {
         		lstCandidateNew.add(lstCandidate.get(i));
         		nNonCandidate = lstCandidate.get(n-1-i);
         	}
         }
         
         if (n % 2 == 1) {
            if (nNonCandidate != -1) {
         	    bAnswer = knows(nNonCandidate, lstCandidate.get(n/2));
         	    if (bAnswer == true) {
         	        if (!knows(lstCandidate.get(n/2), nNonCandidate)) lstCandidateNew.add(lstCandidate.get(n/2));         		
         	    }
            } else {
                lstCandidateNew.add(lstCandidate.get(n/2)); 
            }
         }
    	 
         return findCelebrityHelper(lstCandidateNew);
    }
    
    
    private boolean knows(int a, int b) {
    	return true;
    }
}
