package com.leet;

//Additive number is a string whose digits can form additive sequence.
//
//A valid additive sequence should contain at least three numbers. 
//Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
//
//For example:
//"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
//
//1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
//"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
//1 + 99 = 100, 99 + 100 = 199
//
//Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
//
//Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
//
//Follow up:
//How would you handle overflow for very large input integers?


//Epic Systems
public class AdditiveNumber {

	public AdditiveNumber() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
//		String sNum = "112358";
//		String sNum = "199100199";
//		String sNum = "1203";

//		String sNum = "1123581321345589144";   //Additive
//		String sNum = "101";
		String sNum = "11235813213455890144";   //Not additive
		
		
		boolean bRet = isAdditiveNumber(sNum);
		
		if (bRet == false) {
			System.out.println(sNum + " is NOT Additive Number !");
		} else {
			System.out.println(sNum + " is Additive Number !");			
		}
	}
	
	//In this way,  could avoid overflow
	public boolean isEqual(String sFirstVal, String sSecondVal, String sThirdVal) {
		int nFirstLen = sFirstVal.length();
		int nSecondLen = sSecondVal.length();
		int nThirdLen = sThirdVal.length();
		int nMaxLen = Math.max(nFirstLen, nSecondLen);
		int nCount = 0;
		
		int nFirstSecondOverflow = 0;
		int nThirdOverflow = 0;
		
		int nFirstElement = 0;
		int nSecondElement = 0;
		int nThirdElement = 0;
		
		int nFirstSecondSum = 0;
		
		
		if (nThirdLen < nMaxLen || nThirdLen > nMaxLen + 1) return false;
		
		for (int i=nThirdLen; i>0; i--) {
			nCount = nCount + 1;
			
			if (nCount <= nFirstLen) {
				nFirstElement = Integer.valueOf(sFirstVal.substring(nFirstLen-nCount, nFirstLen-nCount+1));
			} else {
				nFirstElement = 0;
			}
			
			if (nCount <= nSecondLen) {
				nSecondElement = Integer.valueOf(sSecondVal.substring(nSecondLen-nCount, nSecondLen-nCount+1));				
			} else {
				nSecondElement = 0;
			}
			
			nThirdElement = Integer.valueOf(sThirdVal.substring(nThirdLen-nCount, nThirdLen-nCount+1));	
			
			nFirstSecondSum = nFirstElement + nSecondElement + nFirstSecondOverflow;
			
			nThirdElement = nThirdElement + nThirdOverflow;
			
			if (nThirdElement % 10 == nFirstSecondSum % 10) {
				if (nThirdElement >= 10) {
					nThirdOverflow = 1;
				} else {
					nThirdOverflow = 0;
				}
				
				if (nFirstSecondSum >= 10) {
					nFirstSecondOverflow = 1;
				} else {
					nFirstSecondOverflow = 0;
				}
			} else {
				return false;
			}
		}
		
		if (nThirdOverflow >= nFirstSecondOverflow) return true;
		
		return false;
	}
	
	
    public boolean isAdditiveNumber(String num) {
        boolean bRet = false;
        int n = num.length();
        
        //Decide possible length of first number
        int nFirstMaxLen = n/2;
        if (n % 2 == 0) nFirstMaxLen = nFirstMaxLen - 1;
        
        int nSecondMaxLen = 1;
        int nThirdStartLen = 1;
        int nThirdMaxLen = 1;
        int nRemainedLen = 0;
        
        String sFirstVal = "";
        String sSecondVal = "";
        String sThirdVal = "";
        
        
        for (int nFirstLen = 1; nFirstLen <= nFirstMaxLen; nFirstLen++) {
        	
        	sFirstVal = num.substring(0, nFirstLen);
        	
        	if (nFirstLen > 1 && sFirstVal.charAt(0) == '0') break;
        	        	
        	//Decide possible length of second number
        	nSecondMaxLen = (n - nFirstLen)/2;
        	
        	for (int nSecondLen = 1; nSecondLen <= nSecondMaxLen; nSecondLen++) {
        		
        		sSecondVal = num.substring(nFirstLen, nFirstLen+nSecondLen);
        		if (nSecondLen > 1 && sSecondVal.charAt(0) == '0') break;
        		
        		//Decide possible length of third number
        		nRemainedLen = n - nFirstLen - nSecondLen;
        		
        		nThirdStartLen = Math.max(nFirstLen, nSecondLen);
        		
        		if (nRemainedLen < nThirdStartLen) break;
        		
        		nThirdMaxLen = Math.min(nThirdStartLen+1, nRemainedLen);
        		
        		bRet = false;
        		
        		for (int nThirdLen = nThirdStartLen; nThirdLen <= nThirdMaxLen; nThirdLen++) {
        			sThirdVal = num.substring(nFirstLen + nSecondLen, nFirstLen + nSecondLen + nThirdLen);
        			if (nThirdLen > 1 && sThirdVal.charAt(0) == '0') break;
        			        			
        			boolean bEqual = isEqual(sFirstVal, sSecondVal, sThirdVal);
        			
            		if (bEqual == true) {

        				if (nFirstLen + nSecondLen + nThirdLen == n) {
        					bRet = true;
        					break;
        				} else {
        					//Check whether the remained part is Additive Num
	        				boolean bRemainedRet = isAdditiveNumber(num.substring(nFirstLen));
	        				
	        				if (bRemainedRet == true) {
	        					bRet = true;
	        					break;
	        				} else {
	        					continue;
	        				}   
        				}
        			}
        		}
        		
        		if (bRet == true) {
        			break;
        		}
        		
        	}
        	
        	if (bRet == true) {
        		break;
        	}
        }
        
        return bRet;
    }
	
}
