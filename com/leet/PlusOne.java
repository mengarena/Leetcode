package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a non-negative number represented as an array of digits, plus one to the number.
//
//The digits are stored such that the most significant digit is at the head of the list.

public class PlusOne {

	public PlusOne() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] narrNum = {9, 9, 5};
		
	    int[] nkk = new int[narrNum.length+1];  
	    nkk[0]=2;
		
		String sNum2 = "";
		for (int i = 0; i<nkk.length; i++) {
			sNum2 = sNum2 + nkk[i];
		}
	    
		System.out.println("======Plused One Result: " + sNum2);

		int[] narrNew = plusOne2(narrNum);


		String sNum = "";
		for (int i = 0; i<narrNew.length; i++) {
			sNum = sNum + narrNew[i];
		}
		
		System.out.println("Plused One Result: " + sNum);
	}
	
    public int[] plusOne_old(int[] digits) {
        int i;
        int nTmp = 0;
        List<Integer> lstNewDigits = new ArrayList<Integer>();
        
        //Plus one on last digit
        nTmp = digits[digits.length - 1] + 1;
        
        for (i=digits.length-2; i>=0; i--) {
        	if (nTmp > 9) {
        		digits[i+1] = 0;
        		nTmp = digits[i] + 1;
        	} else {
        		digits[i+1] = nTmp;
        		nTmp = digits[i];
        	}
        	
    		lstNewDigits.add(0, digits[i+1]);
        }
        
        //Process first digit
        if (nTmp > 9) {
        	lstNewDigits.add(0, 0);
        	lstNewDigits.add(0, 1);
        } else {
        	lstNewDigits.add(0, nTmp);
        }
        
        int nSize = lstNewDigits.size();
        
        //Convert to array
        int[] narrNew = new int[nSize];
        for (i=0; i<nSize; i++) {
        	narrNew[i] = lstNewDigits.get(i);
        }
        
        return narrNew;
    }	
	
    
    public int[] plusOne2(int[] digits) {  
        if(digits == null || digits.length==0)  
            return digits;  
        
        int carry = 1;  
        for(int i=digits.length-1;i>=0;i--)  
        {  
            int digit = (digits[i]+carry)%10;   
            carry = (digits[i]+carry)/10;  
            digits[i] = digit; 
            
            if(carry==0)  
                return digits;      
        }
        
        int [] res = new int[digits.length+1];      
        res[0] = 1;  
        return res;  
    }  
    
    public int[] plusOneGood(int[] digits) {
        int i;
        int nTmp = 0;
        int nLen = digits.length;
        
        //Plus one on last digit
        nTmp = digits[nLen - 1] + 1;
        
        for (i=nLen-2; i>=0; i--) {
        	if (nTmp > 9) {
        		digits[i+1] = 0;
        		nTmp = digits[i] + 1;
        	} else {
        		digits[i+1] = nTmp;
        		nTmp = digits[i];
        	}
        	
        }
        
        //Process first digit
        if (nTmp > 9) {   //If the first digit becomes 10, all other digits followed are 0
        	int[] narrNew = new int[nLen + 1];
        	narrNew[0] = 1;    //10000000
        	
        	return narrNew;
        } else {
        	digits[0] = nTmp;
        	return digits;	
        }
        
    }	    
    
    

    
//    public int[] plusOne(int[] digits) {
//        int nOrgNum = 0;
//        int i;
//                
//        for (i = digits.length-1; i>=0; i--) {
//        	nOrgNum = nOrgNum + (int) (digits[i]*Math.pow(10, i));
//        }
//        
//        nOrgNum = nOrgNum + 1;
//        
//    	int nRemainder = 0;
//    	int nQuotient = 0;
//    	
//    	nRemainder = nOrgNum % 10;
//    	nQuotient = nOrgNum / 10;
//    	
//    	lstDigit.add(0, nRemainder);
//    	
//    	while (nQuotient >= 10) {
//    		
//    	}
//    	
//    }	    
//    
}
