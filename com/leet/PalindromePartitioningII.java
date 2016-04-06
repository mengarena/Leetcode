package com.leet;

//Given a string s, partition s such that every substring of the partition is a palindrome.
//
//Return the minimum cuts needed for a palindrome partitioning of s.
//
//For example, given s = "aab",
//Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

public class PalindromePartitioningII {

	public PalindromePartitioningII() {
		// TODO Auto-generated constructor stub
	}

    public void run() {
    	//String s = "baabc";
    	String s = "fifgbeajcacehiicccfecbfhhgfiiecdcjjffbghdidbhbdbfbfjccgbbdcjheccfbhafehieabbdfeigbiaggchaeghaijfbjhi";
    	
    	System.out.println(minCut(s));
    }
	
    
    //AC:  34%
    //DP
    public int minCut(String s) {
        if (s == null || s.length() <= 1) return 0;
        int n=s.length();
        int i,j;
        int[] minCut = new int[n];   //Min cut by position i
        boolean[][] pal = new boolean[n][n];   //Is the substring [j, i] palindrome or not
        char[] carr = s.toCharArray();
        
        for (i=0; i<n; i++) {
            int nMinCut = i;  //Min cut by position i  (at position 1 (i=1, i.e. two letters in substring), there is at most 1 cut) 
            
            for (j=0; j<=i; j++) {
                if (carr[i] == carr[j] && (i-1 < j+1 || pal[j+1][i-1])) {   //for [j, i] To be palindrome, the two edge letters should be the same, and the remained part (if exist) should also be palindrome) 
                    pal[j][i] = true;   //substring [j, i] is palindrome, so one cut is between position j-1, j
                    nMinCut = j == 0? 0:Math.min(nMinCut, minCut[j-1]+1);
                }
            }
            minCut[i] = nMinCut;
        }
        
        return minCut[n-1];

    }    
    
    
    //Works, but exceeded time limit
    public int minCutA(String s) {
        if (s == null || s.length() <= 1) return 0;
        int n=s.length();
        int i;
        if (isPalindrome(s)) return 0;
        
        for (i=1; i<=n-2; i++){
        	boolean bret = cutString(s, i);
        	if (bret == true) return i; 
        }
        
        return n-1;
    }
    
    private boolean cutString(String s, int cutCnt) {
    	int n = s.length();
    	if (n <= cutCnt) return false;
    	if (n == cutCnt+1) return true;
    	if (cutCnt == 0) {
    		if (isPalindrome(s)) return true;
    		return false;
    	}
    	int i;
    	
    	for (i=1; i<=n-cutCnt; i++) {
    		String sub = s.substring(0, i);
    		if (isPalindrome(sub)) {
    			if (cutString(s.substring(i), cutCnt-1)) return true;
    		}
    	}
    	
    	return false;
    }
    
    private boolean isPalindrome(String s) {
    	int n=s.length();
    	int i=0, j=n-1;
    	if (n <= 1) return true;
    	
    	while (i < j) {
    		if (s.charAt(i) != s.charAt(j)) return false;
    		i++;
    		j--;
    	}
    	
    	return true;    	
    }

}
