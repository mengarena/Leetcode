package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a list of non negative integers, arrange them such that they form the largest number.
//
//For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
//
//Note: The result may be very large, so you need to return a string instead of an integer.

public class LargestNumber {

	public LargestNumber() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {10, 2};
		
		System.out.println("Result = " + largestNumber(nums));
	}

	
	
	//ACC:  35%
    public String largestNumber(int[] nums) {
        StringBuilder sbResult = new StringBuilder();
        int i,j;
        int nInsertPos = -1;
        
        List<Integer> lstNum = new ArrayList<Integer>();
        
        //Order the nums in lstlstDigits, larger in front
        for (i=0; i<nums.length; i++) {
        	if (lstNum.size() == 0) {
        		lstNum.add(nums[i]);
        	} else {
        		nInsertPos = -1;
        		for (j=0; j<lstNum.size(); j++) {
        			if (compareDigitList(nums[i], lstNum.get(j)) > 0) {
        				nInsertPos = j;
        				break;
        			}
        		}
        		
        		if (nInsertPos >= 0) {
        			lstNum.add(nInsertPos, nums[i]);
        		} else {
        			lstNum.add(nums[i]);
        		}
        	}
        }
        
        //Form the resultant number
        for (Integer num:lstNum) {
        	if (sbResult.length() == 0 && num == 0) continue;  //If all zero, not form a string here
        	sbResult.append(num);
        }
                
        if (sbResult.length() == 0 && lstNum.size() > 0) return "0";
        
        return sbResult.toString();
    }
	
    
    //Works, but slower than compareDigitListKK
    public int compareDigitList(int numA, int numB) {
    	String sA = String.valueOf(numA);
    	String sB = String.valueOf(numB);
    	
    	String sAB = sA + sB;
    	String sBA = sB + sA;
    	
    	if (sAB.compareTo(sBA) < 0) {
    		return -1;
    	} else {
    		return 1;
    	}
    	
    }
    
	
    //Compare two lists of digits
    //Rule:  1) On a digit position, if A > B,  A should be put in front of B
    //       2) All comparable digits are the same, but A is shorter than B, compare A's last digit with all the rest digits of B, 12 > 121; 12 < 1221;  12 < 128; 12 < 122 
    //       
    //Two digit list:  xxxxxaaaaccc
    //                 cccxxxxxaaaa
    //A: xxxxxaaaaa, B: ccc
    public int compareDigitListKK(int numA, int numB) {
    	int nRelationship = 1;
    	int i;
    	int nA, nB;
    	int nTmp1, nTmp2;
    	char[] carrA = String.valueOf(numA).toCharArray();
    	char[] carrB = String.valueOf(numB).toCharArray();
    	int nSizeA = carrA.length;
    	int nSizeB = carrB.length;    	
    	int nMinLen = (int) Math.min(nSizeA, nSizeB);
    	
    	//Compare first part (xxx - ccc)
    	for (i=0; i<nMinLen; i++) {
    		nA = carrA[i];
    		nB = carrB[i];
    		
    		if (nA < nB) {
    			nRelationship = -1;
    			return nRelationship;
    		} else if (nA > nB) {
    			nRelationship = 1;
    			return nRelationship;
    		}
    	}
    	
    	
    	if (nSizeA > nSizeB) {
    		//Compare 2nd part: xxaaaa -- xxxxxa
    		for (i=0; i<nSizeA-nSizeB; i++) {
    			nTmp1 = carrA[nMinLen+i];
    			nTmp2 = carrA[i];
    			
    			if (nTmp1 < nTmp2) {
    				nRelationship = -1;
    				return nRelationship;
    			} else if (nTmp1 > nTmp2) {
    				nRelationship = 1;
    				return nRelationship;
    			}
    		}
    		
    		//Compare last part: ccc -- aaa
    		for (i=0; i<nMinLen; i++) {
        		nB = carrB[i];
        		nA = carrA[i+nSizeA-nMinLen];
        		
        		if (nB < nA) {
    				nRelationship = -1;
    				return nRelationship;    			
        		} else if (nB > nA) {
    				nRelationship = 1;
    				return nRelationship;        			
        		}
    		}
    		
    	} else if (nSizeA < nSizeB) {
    		for (i=0; i<nSizeB-nSizeA; i++) {
    			nTmp1 = carrB[i];
    			nTmp2 = carrB[nMinLen+i];
    			
    			if (nTmp1 < nTmp2) {
    				nRelationship = -1;
    				return nRelationship;
    			} else if (nTmp1 > nTmp2) {
    				nRelationship = 1;
    				return nRelationship;    				
    			}
    			
    		}
    		
    		for (i=0; i<nMinLen; i++) {
        		nB = carrB[i+nSizeB-nMinLen];
        		nA = carrA[i];
        		
        		if (nB < nA) {
    				nRelationship = -1;
    				return nRelationship;	
        		} else if (nB > nA) {
    				nRelationship = 1;
    				return nRelationship;	        			
        		}
    		}
    		
    	}

    	return nRelationship;
    	    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	//ACC:  32%
    public String largestNumberA(int[] nums) {
        String sResult = "";
        int i,j;
        int nInsertPos = -1;
        
        List<List<Integer>> lstlstDigits = new ArrayList<List<Integer>>();
        
        //Order the nums in lstlstDigits
        for (i=0; i<nums.length; i++) {
        	List<Integer> lstDigit = convertDigits(nums[i]);   //Convert a number to a digit list
        	if (lstlstDigits.size() == 0) {
        		lstlstDigits.add(lstDigit);
        	} else {
        		nInsertPos = -1;
        		for (j=0; j<lstlstDigits.size(); j++) {
        			List<Integer> lstTmpDigit = lstlstDigits.get(j);
        			if (compareDigitListA(lstDigit, lstTmpDigit) > 0) {
        				nInsertPos = j;
        				break;
        			}
        		}
        		
        		if (nInsertPos >= 0) {
        			lstlstDigits.add(nInsertPos, lstDigit);
        		} else {
        			lstlstDigits.add(lstDigit);
        		}
        	}
        }
        
        //Form the resultant number
        for (List<Integer> lstTmpDigits : lstlstDigits) {
        	String sTmp = "";

        	for (Integer nDigit:lstTmpDigits) {
        		if (nDigit == 0 && sTmp.length() == 0 && sResult.length() == 0) continue;  //If all zero, not form a string here
        		
        		sTmp = sTmp + nDigit;
        	}
        	
        	sResult = sResult + sTmp;
        }
        
        if (sResult.length() == 0 && lstlstDigits.size() > 0) sResult = "0";
        
        return sResult;
    }
  
    
    
    //Convert a number to a digit list
    public List<Integer> convertDigits(int num) {
    	List<Integer> lstDigit = new ArrayList<Integer>();
  	  
    	if (num < 10) {
    	    lstDigit.add(num);
    	    return lstDigit;
    	} 	
    	
    	while (num > 0) {
    		lstDigit.add(0, num % 10);
    		num = num/10;
    	}
    
    	return lstDigit;
    }
    
    
    
    //Compare two lists of digits
    //Rule:  1) On a digit position, if A > B,  A should be put in front of B
    //       2) All comparable digits are the same, but A is shorter than B, compare A's last digit with all the rest digits of B, 12 > 1221;  12 < 128; 12 >= 122 
    //       
    //Two digit list:  xxxxxaaaaccc
    //                 cccxxxxxaaaa
    //A: xxxxxaaaaa, B: ccc
    public int compareDigitListA(List<Integer> lstDigitA, List<Integer> lstDigitB) {
    	int nRelationship = 1;
    	int i;
    	int nA, nB;
    	int nTmp1, nTmp2;
    	int nSizeA = lstDigitA.size();
    	int nSizeB = lstDigitB.size();    	
    	int nMinLen = (int) Math.min(nSizeA, nSizeB);
    	
    	//Compare first part (xxx - ccc)
    	for (i=0; i<nMinLen; i++) {
    		nA = lstDigitA.get(i);
    		nB = lstDigitB.get(i);
    		
    		if (nA < nB) {
    			nRelationship = -1;
    			return nRelationship;
    		} else if (nA > nB) {
    			nRelationship = 1;
    			return nRelationship;
    		}
    	}
    	
    	
    	if (nSizeA > nSizeB) {
    		//Compare 2nd part: xxaaaa -- xxxxxa
    		for (i=0; i<nSizeA-nSizeB; i++) {
    			nTmp1 = lstDigitA.get(nMinLen+i);
    			nTmp2 = lstDigitA.get(i);
    			
    			if (nTmp1 < nTmp2) {
    				nRelationship = -1;
    				return nRelationship;
    			} else if (nTmp1 > nTmp2) {
    				nRelationship = 1;
    				return nRelationship;
    			}
    		}
    		
    		//Compare last part: ccc -- aaa
    		for (i=0; i<nMinLen; i++) {
        		nB = lstDigitB.get(i);
        		nA = lstDigitA.get(i+nSizeA-nMinLen);
        		
        		if (nB < nA) {
    				nRelationship = -1;
    				return nRelationship;    			
        		} else if (nB > nA) {
    				nRelationship = 1;
    				return nRelationship;        			
        		}
    		}
    		
    	} else if (nSizeA < nSizeB) {
    		for (i=0; i<nSizeB-nSizeA; i++) {
    			nTmp1 = lstDigitB.get(i);
    			nTmp2 = lstDigitB.get(nMinLen+i);
    			
    			if (nTmp1 < nTmp2) {
    				nRelationship = -1;
    				return nRelationship;
    			} else if (nTmp1 > nTmp2) {
    				nRelationship = 1;
    				return nRelationship;    				
    			}
    			
    		}
    		
    		for (i=0; i<nMinLen; i++) {
        		nB = lstDigitB.get(i+nSizeB-nMinLen);
        		nA = lstDigitA.get(i);
        		
        		if (nB < nA) {
    				nRelationship = -1;
    				return nRelationship;	
        		} else if (nB > nA) {
    				nRelationship = 1;
    				return nRelationship;	        			
        		}
    		}
    		
    	}

    	return nRelationship;
    	    	
    }
    
    
    
    
    
    public int compareDigitListAA(List<Integer> lstDigitA, List<Integer> lstDigitB) {
    	int nRelationship = 0;
    	int nSizeA = lstDigitA.size();
    	int nSizeB = lstDigitB.size();    	
    	int nMinLen = (int) Math.min(nSizeA, nSizeB);
    	int i, j;
    	int nA, nB;
    	
    	for (i=0; i<nMinLen; i++) {
    		nA = lstDigitA.get(i);
    		nB = lstDigitB.get(i);
    		
    		if (nA > nB) {
    			nRelationship = 1;
    			break;
    		} else if (nA < nB) {
    			nRelationship = -1;
    			break;
    		}
    	}
    	
    	if (nRelationship != 0) {
    		return nRelationship;
    	} else {
			nRelationship = 1;

    		if (nSizeA < nSizeB) {
    			int nLastA = lstDigitA.get(nSizeA -1);
    			
    			for (j=0; j<nSizeB; j++) {
    				if (lstDigitB.get(j) > nLastA) {
    					nRelationship = -1;
    					break;
    				} else if (lstDigitB.get(j) < nLastA) {
    					break;
    				}
    			}
    			
    		} else if (nSizeA > nSizeB) {
    			int nLastB = lstDigitB.get(nSizeB -1);
    			
    			for (j=0; j<nSizeA; j++) {
    				if (lstDigitA.get(j) < nLastB) {
    					nRelationship = -1;
    					break;
    				} else if (lstDigitA.get(j) > nLastB) {
    					break;
    				}
    			}
    			
    		}
    		
    		return nRelationship;
    	}
    	
    }
}
