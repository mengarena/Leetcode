package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//
//Find all strobogrammatic numbers that are of length = n.
//
//For example,
//Given n = 2, return ["11","69","88","96"].
//
//Hint:
//
//Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.

public class StrobogrammaticNumberII {

	public StrobogrammaticNumberII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		List<String> lstNumbers = findStrobogrammatic(4);
		
		for (String stmp:lstNumbers) System.out.print(stmp + ",");
		System.out.println();
	
	}
	
	//AC:  Recursion:  67%
    public List<String> findStrobogrammatic(int n) {
        return findStrobogrammaticHelper(n, n);
    }
    
    private List<String> findStrobogrammaticHelper(int n, int nTotal) {
        if (n == 0) return new ArrayList<String>(Arrays.asList(""));
        if (n == 1) return new ArrayList<String>(Arrays.asList("0", "1", "8"));
        
        List<String> lstTmp = findStrobogrammaticHelper(n-2, nTotal);
        
        List<String> lstResult = new ArrayList<String>();   
        //Here use a new list. Another option is just use lstTmp, to remove nSize element at the beginning and in the for loop, add the new string at the end
        //But that way, the performance is much slower (4%)
        
        int nSize = lstTmp.size();

        for (int i=0; i<nSize; i++) {
            String stmp = lstTmp.get(i);
            
            if (n != nTotal) lstResult.add("0" + stmp + "0");
            
            lstResult.add("1" + stmp + "1");
            lstResult.add("8" + stmp + "8");
            lstResult.add("6" + stmp + "9");
            lstResult.add("9" + stmp + "6");
            
        }
        
        return lstResult;
    }
	
	
    
    
    
    
    
    //AC:  Faster 19%
	//Strategy:  get a valid n number first "1111111", then try to replace each digit with 0,8,6,9
    public List<String> findStrobogrammaticC(int n) {
        List<String> lstNumbers = new ArrayList<String>();
        if (n == 0) return lstNumbers;

        int nSize;
        int i;
        String stmp;
        StringBuilder sb = new StringBuilder();
        for (i=0; i<n; i++) sb.append("1");
        
        lstNumbers.add(sb.toString());
        
        for (i=0; i<n/2; i++) {
        	nSize = lstNumbers.size();
        	for (int j=0; j<nSize; j++) {
        		stmp = lstNumbers.get(j);
        		if (i > 0) lstNumbers.add(stmp.substring(0, i) + "0" + stmp.substring(i+1, n-1-i) + "0" + stmp.substring(n-i));
        		lstNumbers.add(stmp.substring(0, i) + "8" + stmp.substring(i+1, n-1-i) + "8" + stmp.substring(n-i));
        		lstNumbers.add(stmp.substring(0, i) + "6" + stmp.substring(i+1, n-1-i) + "9" + stmp.substring(n-i));
        		lstNumbers.add(stmp.substring(0, i) + "9" + stmp.substring(i+1, n-1-i) + "6" + stmp.substring(n-i));
        	}
        }
        
        if (n % 2 == 1) {
            nSize = lstNumbers.size();
            for (i=0; i<nSize; i++) {
            	lstNumbers.add(lstNumbers.get(i).substring(0, n/2) + "0" + lstNumbers.get(i).substring(n/2+1));
            	lstNumbers.add(lstNumbers.get(i).substring(0, n/2) + "8" + lstNumbers.get(i).substring(n/2+1));
            }
        }
        
        return lstNumbers;
    }
    
    
    
    
	//AC, but low efficiency   4%
    public List<String> findStrobogrammaticA(int n) {
        List<String> lstNumbers = new ArrayList<String>();
        if (n == 0) return lstNumbers;
        
        if (n % 2 == 1) {   //The middle digit
            lstNumbers.add("0");
            lstNumbers.add("1");
            lstNumbers.add("8");
        } else {
        	lstNumbers.add("");
        }
        
        if (n == 1) return lstNumbers;
        
        findStrobogrammaticHelperA(n/2, lstNumbers);
        
        return lstNumbers;
    }
    
    private void findStrobogrammaticHelperA(int n, List<String> lstNumbers) {
        int nSize = lstNumbers.size();
        int i;
        String stmp = "";
        
        for (i=0; i<nSize; i++) {
            stmp = lstNumbers.remove(0);
            if (n > 1) lstNumbers.add("0" + stmp + "0");  //First digit cannot be 0
            lstNumbers.add("1" + stmp + "1");
            lstNumbers.add("8" + stmp + "8");
            lstNumbers.add("6" + stmp + "9");
            lstNumbers.add("9" + stmp + "6");
        }   
        
        if (n > 1) findStrobogrammaticHelperA(n-1, lstNumbers);
        
        return;
    }
    
    
    
	//AC, but low efficiency, slow!   4%
    public List<String> findStrobogrammaticB(int n) {
        List<String> lstNumbers = new ArrayList<String>();
        List<StringBuilder> lstNumbersSB = new ArrayList<StringBuilder>();
        if (n == 0) return lstNumbers;
        
        if (n % 2 == 1) {   //The middle digit
        	lstNumbersSB.add(new StringBuilder("0"));
        	lstNumbersSB.add(new StringBuilder("1"));
        	lstNumbersSB.add(new StringBuilder("8"));
        } else {
        	lstNumbersSB.add(new StringBuilder(""));
        }
                
        if (n > 1) findStrobogrammaticHelperB(n/2, lstNumbersSB);
        
        for (StringBuilder sb:lstNumbersSB) lstNumbers.add(sb.toString());
        
        return lstNumbers;
    }
    
    private void findStrobogrammaticHelperB(int n, List<StringBuilder> lstNumbersSB) {
        int nSize = lstNumbersSB.size();
        int i;
        StringBuilder stmp = null;
        StringBuilder tmp = null;
        
        for (i=0; i<nSize; i++) {
            stmp = lstNumbersSB.remove(0);
            if (n > 1) {
            	tmp = new StringBuilder(stmp);
            	tmp.insert(0, "0");
            	tmp.append("0") ;
            	lstNumbersSB.add(tmp);  //First digit cannot be 0
            }
            
            tmp = new StringBuilder(stmp);
        	tmp.insert(0, "1");
        	tmp.append("1") ;
            lstNumbersSB.add(tmp);
            
            tmp = new StringBuilder(stmp);
        	tmp.insert(0, "8");
        	tmp.append("8") ;
            lstNumbersSB.add(tmp);

            tmp = new StringBuilder(stmp);
        	tmp.insert(0, "6");
        	tmp.append("9") ;
            lstNumbersSB.add(tmp);
            
            tmp = new StringBuilder(stmp);
        	tmp.insert(0, "9");
        	tmp.append("6") ;
            lstNumbersSB.add(tmp);
        }   
        
        if (n > 1) findStrobogrammaticHelperB(n-1, lstNumbersSB);
        
        return;
    }
   
}
