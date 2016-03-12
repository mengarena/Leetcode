package com.leet;

//You are a product manager and currently leading a team to develop a new product. 
//Unfortunately, the latest version of your product fails the quality check. 
//Since each version is developed based on the previous version, all the versions after a bad version are also bad.
//
//Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, 
//which causes all the following ones to be bad.
//
//You are given an API bool isBadVersion(version) which will return whether version is bad. 
//Implement a function to find the first bad version. You should minimize the number of calls to the API.

public class FirstBadVersion {

	public FirstBadVersion() {
		// TODO Auto-generated constructor stub
	}

//	2126753390 versions
//	1702766719 is the first bad version.
	
	public void run() {
		int n = 129; //2126753390;
		
		System.out.println("Bad Version: " + firstBadVersion(n));
	}
	
	//[1, 2, ..., n]
    public int firstBadVersion(int n) {        
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int nStart = 1;
        int nEnd = n;
        int nMiddle, nPreMiddle;
        
        while (nStart < nEnd) {
        	nMiddle = nStart + (nEnd - nStart)/2;   //Don't use (nStart + nEnd)/2, which will lead to overflow
        	        	
        	if (nMiddle == nStart) {
        		if (isBadVersion(nMiddle)) {
        			return nMiddle;
        		} else {
        			return nEnd;
        		}
        		
        	} else {
        		if (isBadVersion(nMiddle)) {
        			nEnd = nMiddle;
        		} else {
        			nStart = nMiddle + 1;
        		}
        	}
        	
        }
        
        return nStart;
        
    }
		
    
    public boolean isBadVersion(int version) {
 //   	if (version >= 1702766719) return true;
       	if (version >= 17) return true;

    	return false;
    }


/*
    public int firstBadVersion(int n) {        
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int nStart = 1;
        int nEnd = n;
        int nMiddle, nPreMiddle;
        
        while (nStart < nEnd) {
        	nMiddle = (nStart + nEnd)/2;
        	        	
        	if (nMiddle == nStart) {
        		if (isBadVersion(nMiddle)) {
        			return nMiddle;
        		} else {
        			return nEnd;
        		}
        		
        	} else {
        		nPreMiddle = nMiddle - 1;

        		if (isBadVersion(nMiddle)) {
        			if (!isBadVersion(nPreMiddle)) {
        				return nMiddle;
        			} else {
        				nEnd = nPreMiddle;
        			}
        		} else if (!isBadVersion(nPreMiddle)) {
        			nStart = nMiddle + 1;
        		}
        	}
        	
        }
        
        return nStart;
        
    }
*/

}
