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
	
	
	//ACC:  67%:   DP + DFS
    public List<List<String>> partition(String s) {
        List<List<String>> lstlstParts = new ArrayList<>();
        int n = s.length();
        boolean bPart[][] = new boolean[n][n];   //Where substring[i,j] is palindrome
        
        for (int i=0; i<n; i++) {
            for (int left=0; left<=i; left++) {
                if (s.charAt(left) == s.charAt(i) && (i-left <= 1 || bPart[left+1][i-1])) {
                    bPart[left][i] = true;
                }
            }
        }
        
        getPartition(s, 0, new ArrayList<String>(), lstlstParts, bPart);
        
        return lstlstParts;
    }
    
    private void getPartition(String s, int start, List<String> lstPart, List<List<String>> lstlstParts, boolean[][] bPart) {
        if (start == s.length()) {
            lstlstParts.add(new ArrayList<String>(lstPart));
            return;
        }
        
        for (int i=start; i<s.length(); i++) {
            if (bPart[start][i]) {
                lstPart.add(s.substring(start, i+1));
                getPartition(s, i+1, lstPart, lstlstParts, bPart);
                lstPart.remove(lstPart.size()-1);
            }
        }
    }
	
	
	//ACC: 32%    DFS
	public List<List<String>> partitionA(String s) {
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
    
    
	
	//ACC: 32%  DP
    public List<List<String>> partitionB(String s) {
        int n = s.length();
        List<List<String>> lstlstParts[] = new List[n+1];
        boolean bPart[][] = new boolean[n][n];
        lstlstParts[0] = new ArrayList<List<String>>();
        lstlstParts[0].add(new ArrayList<String>());
        int i;
        int left;
        
        for (i=0; i<n; i++) {
            lstlstParts[i+1] = new ArrayList<List<String>>();
            for (left=0; left<=i; left++) {
                if (s.charAt(left) == s.charAt(i) && (i-left <= 1 || bPart[left+1][i-1])) {
                    bPart[left][i] = true;
                    String ss = s.substring(left, i+1);
                    
                    for (List<String> lstPart:lstlstParts[left]) {
                        List<String> lstPartTmp = new ArrayList<String>(lstPart);
                        lstPartTmp.add(ss);
                        lstlstParts[i+1].add(lstPartTmp);
                    }
                }
            }
        }
        
        
        return lstlstParts[n];
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
