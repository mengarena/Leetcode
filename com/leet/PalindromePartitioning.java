package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a string s, partition s such that every substring of the partition is a palindrome.
//
//Return all possible palindrome partitioning of s.
//
//For example, given s = "aab",
//Return
//
//  [
//    ["aa","b"],
//    ["a","a","b"]
//  ]

//Bloomberg
public class PalindromePartitioning {

	public PalindromePartitioning() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//String s = "aabaacac";
		String s = "fifgbeajcacehiicccfecbfhhgfiiecdcjjffbghdidbhbdbfbfjccgbbdcjheccfbhafehieabbdfeigbiaggchaeghaijfbjhi";
		//String s = "ltsqjodzeriqdtyewsrpfscozbyrpidadvsmlylqrviuqiynbscgmhulkvdzdicgdwvquigoepiwxjlydogpxdahyfhdnljshgjeprsvgctgnfgqtnfsqizonirdtcvblehcwbzedsmrxtjsipkyxk";
		List<List<String>> lstlstPartition = partition(s);
		
//		for (List<String> lstPartition:lstlstPartition) {
//			System.out.print("[");
//			for (String sPartition:lstPartition) {
//				System.out.print(sPartition + ",");
//			}
//			System.out.println("]");
//		}
	}
	
	
	
	public List<List<String>> partition(String s) {
		List<List<String>> lstlstParition = new ArrayList<List<String>>();
		
		if (s == null || s.length() == 0) return lstlstParition;
		
		List<String> lstPartition = new ArrayList<String>();
		
		partitionHelper(s, 0, lstPartition, lstlstParition);
		
		return lstlstParition;
	}
	
	
	public void partitionHelper(String s, int nStartPos, List<String> lstPartition, List<List<String>> lstlstParition) {
		if (nStartPos == s.length()) {
			List<String> lstPartitionTmp = new ArrayList<String>(lstPartition);
			lstlstParition.add(lstPartitionTmp);
			return;
		}
		
		for (int i=nStartPos+1; i<=s.length(); i++) {
			String sFirst = s.substring(nStartPos, i);
			if (IsPalindrome(sFirst)) {
				lstPartition.add(sFirst);
				partitionHelper(s, i, lstPartition, lstlstParition);
				lstPartition.remove(lstPartition.size()-1);
			}
		}
	}
	
	
	
/* Works, but time out	
    public List<List<String>> partition(String s) {
        List<List<String>> lstlstParition = new ArrayList<List<String>>();
        int i;
        if (s == null) return lstlstParition;
        int n = s.length();
        if (n == 0) return lstlstParition;
        if (n == 1) {
        	List<String> lstPartition = new ArrayList<String>();
        	lstPartition.add(s);
        	lstlstParition.add(lstPartition);
        	return lstlstParition;
        }
        
        for (i=1; i<=n; i++) {
        	List<List<String>> lstlstPartitionTmp = partition(s, i);
        	
        	for (List<String> lstPartitionTmp:lstlstPartitionTmp) lstlstParition.add(lstPartitionTmp);
        }
        
        return lstlstParition;
    }
	
    public List<List<String>> partition(String s, int nPartitionCnt) {
    	List<List<String>> lstlstPartition = new ArrayList<List<String>>();
    	int i;
    	
    	if ((s == null || s.length() == 0) && nPartitionCnt >= 1) return lstlstPartition;
    	if (s != null && s.length() > 0 && nPartitionCnt == 1) {
    		if (IsPalindrome(s) == false) return lstlstPartition;
    		List<String> lstPartition = new ArrayList<String>();
    		lstPartition.add(s);
    		lstlstPartition.add(lstPartition);
    		return lstlstPartition;
    	}
    	
    	if (s != null && s.length() == nPartitionCnt) {
    		List<String> lstPartition = new ArrayList<String>();
    		for (i=0; i<s.length(); i++) lstPartition.add(s.charAt(i) + "");
    		lstlstPartition.add(lstPartition);
    		return lstlstPartition;
    	}
    	
    	int n = s.length();
    	if (n < nPartitionCnt) return lstlstPartition;
    	
    	for (i=1; i<=n-nPartitionCnt+1; i++) { //First partition length
    		String sFirst = s.substring(0,i);
    		if (IsPalindrome(sFirst) == false) continue;
    		List<List<String>> lstlstPartitionTmp = partition(s.substring(i), nPartitionCnt-1);
    		for (List<String> lstPartitionTmp:lstlstPartitionTmp) {
    			lstPartitionTmp.add(0, sFirst);
    			lstlstPartition.add(lstPartitionTmp);
    		}
    	}
    	
    	return lstlstPartition;
    }
*/    
   
    public boolean IsPalindrome(String s) {
    	int n = s.length();
    	if (n == 1) return true;
    	int i=0, j=n-1;
    	
    	while (i<=j) {
    		if (s.charAt(i) != s.charAt(j)) return false;
    		i++;
    		j--;
    	}
    	    	
    	return true;
    }    
    
/*    
    public boolean IsPalindrome(String s) {
    	int n = s.length();
    	if (n == 1) return true;
    	int nLeft, nRight;
    	
    	if (n % 2 == 1) {
    		nLeft = n/2-1;
    		nRight = n/2+1;
    	} else {
    		nLeft = n/2-1;
    		nRight = n/2;
    	}
    	
    	while (nLeft >= 0) {
    		if (s.charAt(nLeft) != s.charAt(nRight)) return false;
    		nLeft--;
    		nRight++;
    	}
    	
    	return true;
    }
*/    
}
