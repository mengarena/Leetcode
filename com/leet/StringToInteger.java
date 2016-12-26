package com.leet;

//Implement atoi to convert a string to an integer.
//
//Hint: Carefully consider all possible input cases. 
//If you want a challenge, please do not see below and ask yourself what are the possible input cases.
//
//Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
//You are responsible to gather all the input requirements up front.
//
//Update (2015-02-10):
//The signature of the C++ function had been updated. 
//If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.
//
//spoilers alert... click to show requirements for atoi.
//
//Requirements for atoi:
//The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. 
//Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, 
//and interprets them as a numerical value.
//
//The string can contain additional characters after those that form the integral number, 
//which are ignored and have no effect on the behavior of this function.
//
//If the first sequence of non-whitespace characters in str is not a valid integral number, 
//or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
//
//If no valid conversion could be performed, a zero value is returned. 
//If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

//Uber, Microsoft, Amazon, Bloomberg
//Easy
public class StringToInteger {

	public StringToInteger() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String sNum = "   	- 321";
//		String sNum = "0124";
		
		System.out.println("===" + myAtoi(sNum));
	}
	
	//ACC
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        char[] carr = str.toCharArray();
        boolean bSign = false;
        boolean bNeg = false;
        int n = carr.length;
        int i = 0;
        int ret = 0;
        int digit = 0;
        
        while ((i < n) && (carr[i] == ' ' || carr[i] == '\t')) i++;
        
        if (i == n) return 0;
        
        if (carr[i] == '-') {
            bSign = true;
            bNeg = true;
            i++;
        } else if (carr[i] == '+') {
            bSign = true;
            i++;
        }
        
        while (i < n && Character.isDigit(carr[i])) {
            if (ret > Integer.MAX_VALUE/10 || (ret == Integer.MAX_VALUE/10 && carr[i]-'0' > 7)) {
                if (bNeg) {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
            
            ret = ret * 10 + carr[i] - '0';
            
            i++;
        }
        
        if (bNeg) ret = ret*(-1);
        
        return ret;
    }
	
	
	
    public int myAtoiK(String str) {
        if (str == null || str.length() == 0) return 0;
        char[] carr = str.toCharArray();
        boolean bSign = false;
        boolean bNeg = false;
        boolean bDigit = false;
        int n = carr.length;
        int i = 0;
        int ret = 0;
        int digit = 0;
        
        while ((i < n) && (carr[i] == ' ' || carr[i] == '\t')) i++;
        
        if (i == n) return 0;
        
        if (carr[i] == '-') {
            bSign = true;
            bNeg = true;
        } else if (carr[i] == '+') {
            bSign = true;
        } else if (Character.isDigit(carr[i])) {
            bDigit = true;
            ret = carr[i] - '0';
        } else {
            return 0;
        }
        
        i++;
        
        while (i < n) {
            if (Character.isDigit(carr[i])) {
                bDigit = true;
                
                if (bNeg) {
                    if (ret > (Integer.MIN_VALUE + (carr[i]-'0'))/(-10)) {
                        return Integer.MIN_VALUE;
                    }
                } else {
                    if (ret > (Integer.MAX_VALUE - (carr[i]-'0'))/10) {
                        return Integer.MAX_VALUE;
                    }
                }
                
                ret = ret * 10 + (carr[i] - '0');
            } else {
                break;
            }
            
            i++;
        }
        
        if (bDigit == false) return 0;
        
        if (bNeg) ret = ret*(-1);
        
        return ret;
    }	
	
	
	//ACC
    public int myAtoiB(String str) {
        if (str == null || str.length() == 0) return 0;
        char carr[] = str.toCharArray();
        boolean bSign = false;
        boolean bNeg = false;
        boolean bDigit = false;
        int n = str.length();
        int i = 0;
        int ret = 0;
        int digit;
        
        while (carr[i] == ' ' || carr[i] == '\t') i++;
        
        if (carr[i] == '+') {
            bSign = true;
        } else if (carr[i] == '-') {
            bSign = true;
            bNeg = true;
        } else if (carr[i] < '0' || carr[i] > '9') {
            return 0;
        } else {
            ret = carr[i] - '0';
            bDigit = true;
        }
        
        i++;
        
        while (i < n) {
            if (carr[i] < '0' || carr[i] > '9') {
                if (bDigit) break;
                return 0;
            }
            
            bDigit = true;
            digit = carr[i] - '0';
            
            if (bNeg) {
                if ((Integer.MIN_VALUE+digit) / (-10) < ret) {
                    return Integer.MIN_VALUE;
                }
            } else {
                if ((Integer.MAX_VALUE - digit)/10 < ret) {
                    return Integer.MAX_VALUE;
                }
            }
            
            ret = ret*10 + digit;
            
            i++;
        }
        
        if (bNeg) ret = ret * (-1);
        
        return ret;
    }
	
	
	//ACC
    public int myAtoiA(String str) {
    	int nLen = str.length();
    	int nSymbol = 1;   //Neg or Pos
    	boolean bSymbolEncounted = false;
    	boolean bDigitEncounted = false;
    	int nResult = 0;
    	char cSingleElement;
    	int nSingleElement;
    	
    	
    	for (int i=0; i<nLen; i++) {
    		cSingleElement = str.charAt(i);
    		
    		if (bDigitEncounted == false) {
    			if (cSingleElement == ' ' || cSingleElement == '	') {
    				if (bSymbolEncounted == true) return 0;
    				continue;
    			} else if (cSingleElement == '+') {
    				if (bSymbolEncounted == false) {
    					nSymbol = 1;
    					bSymbolEncounted = true;
    				} else {
    					return 0;
    				}
    			} else if (cSingleElement == '-') {
    				if (bSymbolEncounted == false) {
    					nSymbol = -1;
    					bSymbolEncounted = true;
    				} else {
    					return 0;
    				}
    			} else {
    	    		nSingleElement = cSingleElement - '0';

    				if (nSingleElement >=0 && nSingleElement <= 9) {
    					nResult = nResult*10 + nSingleElement;
    					bDigitEncounted = true;
    				} else {
    					return 0;
    				}
    			}
    		} else {
	    		nSingleElement = cSingleElement - '0';

				if (nSingleElement >=0 && nSingleElement <= 9) {
					if (nSymbol == 1) {
						if ((Integer.MAX_VALUE - nSingleElement) / 10.0 < nResult) {
							return Integer.MAX_VALUE;
						}
					} else {
						if ((Integer.MIN_VALUE + nSingleElement) / 10.0 > (-1)*nResult) {
							return Integer.MIN_VALUE;
						}
					}
					
					nResult = nResult*10 + nSingleElement;
				} else {
					break;
				}
    		}
    	}
    	
    	return nResult*nSymbol;
    }
	
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public int org_myAtoi(String str) {
        int nResult = 0;
        int nLen = str.length();
        int nElement = 0;
        char cSingleElement;
        int nSymbol = 1;
        boolean bDigitEncounted = false;
        
        for (int i=nLen-1; i>=0; i--) {
        	cSingleElement = str.charAt(i);
        	if (cSingleElement == ' ' || cSingleElement == '	') {
        		
        		break;
        	}
        	
        	if (cSingleElement == '-') {
        		if (bDigitEncounted == true) {
        			nSymbol = -1;
        		}
        	} else if (cSingleElement == '+') {
        		
        	} else {
        		nElement = str.charAt(i) - '0';
        		if (nElement >= 0 && nElement <= 9) {
        			nResult = nResult + nElement*(int)Math.pow(10, nLen-1-i);
        		} else {
        			
        		}
        	}
        }
        
        return nResult*nSymbol;
    }   
    
}
