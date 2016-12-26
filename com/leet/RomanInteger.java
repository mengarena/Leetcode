package com.leet;

//Given a roman numeral, convert it to an integer.
//
//Input is guaranteed to be within the range from 1 to 3999.

//VII--7
//IX--9
//XV - 15
//XVII - 17
//XX - 20
//XXV - 25
//XXVI - 26
//LX - 60
//XC - 90
//CI - 101
//CCC - 300
//CD - 400
//CM - 900
//MDCC - 1700
//MCM - 1900

//Uber, Bloomberg, Microsoft, Facebook, Yahoo
//Easy
public class RomanInteger {

	public RomanInteger() {
		// TODO Auto-generated constructor stub
	}

	//Rule for Roman:
	/*
	 * I -- 1;    X -- 10;  C -- 100;  M -- 1000
	 * V -- 5;    L -- 50;  D -- 500
	 * 
	 * if ab (a < b), value = b-a
	 */
	public void run() {
		String s = "MDC";
		
		System.out.println("Roman Numeral: " + s + "   = " + romanToInt(s));
	}
	
	
	/*
	 * Special case:
	 * 
	 * 1) I, X, C could continuous up to 3 times; M could be continuous more than 3 times
	 * 2) V, L, D could only continuous occur 1 once
	 * 3) The next letter after V,L,D could not be larger than V, L, D  (e.g. VX is wrong)
	 * 4) Could not put more than one roman letter in front of a larger letter
	 * 
	 */
	//Check whether thr roman string is valid
	private boolean checkRomanString(String s) {
	    Map<Character, Integer> hm = new HashMap<Character, Integer>();
	    hm.put('I', 1);
	    hm.put('V', 5);
	    hm.put('X', 10);
	    hm.put('L', 50);
	    hm.put('C', 100);
	    hm.put('D', 500);
	    hm.put('M', 1000);
	    
	    char[] romArr = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
	    
	    String[] invalidArr = {"IIII", "XXXX", "CCCC", "MMMMM", "VV", "LL", "DD"};
	    int idx = 0;
	    int i;
	    
	    // 1), 2)
	    for (i=0; i<invalidArr.length; i++) {
		    if (s.indexOf(invalidArr[i]) != -1) return false;
		}
		
		//3)
		for (i=1; i<s.length(); i++) {
		    if ((s.charAt(i-1) == 'V' || s.charAt(i-1) == 'L' || s.charAt(i-1) == 'D') && (hm.get(s.charAt(i-1)) < hm.get(s.charAt(i)))) return false;
		}
		
		//4) 
        Map<Character, Integer> hmm = new HashMap<Character, Integer>();
        
        for (i=0; i<s.length(); i++) {
		    hmm.put(s.charAt(i), hmm.getOrDefault(s.charAt(i), 0) + 1);
		    
		    if (i >= 2) {
			    int nRomIdx = Arrays.asList(romArr).indexOf(s.charAt(i));
			    int count = 0;
			    
			    for (int j=0; j<nRomIdx; j++) {
				    count += hmm.getOrDefault(romArr[j] ,0);
				    if (count > 1) return false;
				}
			}
			
		}
        
        
	    return true;
	}
	
	
	//ACC
	public int romanToIntAA(String s) {
		if (s.isEmpty()) return 0;
		
	    Map<Character, Integer> hm = new HashMap<Character, Integer>();
	    hm.put('I', 1);
	    hm.put('V', 5);
	    hm.put('X', 10);
	    hm.put('L', 50);
	    hm.put('C', 100);
	    hm.put('D', 500);
	    hm.put('M', 1000);

        char[] carr = s.toCharArray();
        int result = 0;
        int tmpVal = 0;
        
        for (int i = 0; i < carr.length; i++) {
			tmpVal = hm.get(carr[i]);
			if (i != carr.length-1) {
			    if (tmpVal < hm.get(carr[i+1])) tmpVal = -tmpVal;
			}
			
			result += tmpVal;
	    }
	    
	    return result;	
	}
	
	public int romanToInt(String s) {
		if (s.isEmpty()) return 0;
		
	    Map<Character, Integer> hm = new HashMap<Character, Integer>();
	    hm.put('I', 1);
	    hm.put('V', 5);
	    hm.put('X', 10);
	    hm.put('L', 50);
	    hm.put('C', 100);
	    hm.put('D', 500);
	    hm.put('M', 1000);

        char[] carr = s.toCharArray();
        int result = 0;
        int tmpVal = 0;
        int prev = 0;
        
        for (int i = carr.length-1; i >= 0; i--) {
			tmpVal = hm.get(carr[i]);
            if (tmpVal < prev) {
			    result -= tmpVal;
			} else  {
				result += tmpVal;
			}
			
			prev = tmpVal;
	    }
	    
	    return result;	
	}
	
	
    public int romanToIntB(String s) {
        int nNumVal = 0;

        String sBase ="IVXLCDM";
        int[]  narrBase = {1, 5, 10, 50, 100, 500, 1000};
        int nLen = s.length();
        char cCur;
        int nCurIdx;
        int nPrevIdx;
        int nIdx = 1;
        
        if (nLen == 0) return 0;
        
        nPrevIdx = sBase.indexOf(s.charAt(0));
        
        while (nIdx < nLen) {
        	cCur = s.charAt(nIdx);
        	nCurIdx = sBase.indexOf(cCur);
        	if (nPrevIdx < nCurIdx) {
        		nNumVal = nNumVal - narrBase[nPrevIdx];
        	} else {
        		nNumVal = nNumVal + narrBase[nPrevIdx];
        	}
        	
        	nPrevIdx = nCurIdx;
        	nIdx = nIdx + 1;
        }
        
        nNumVal = nNumVal + narrBase[nPrevIdx];
        
    	return nNumVal;
    }
	
    
    
    public int romanToIntA(String s) {
        char[] tens = {'I', 'X', 'C', 'M'};   //1, 10, 100, 1000
        char[] fives = {'V', 'L', 'D'};   //5, 50, 500
        int nTenIdx = -1;
        int nFiveIdx = -1;
       
        int nPrev = 0;
        int ret = 0;
        int i, j;
        
        if (s == null || s.length() == 0) return 0;
        
        for (i=0; i<s.length(); i++) {
            nTenIdx = -1;
            nFiveIdx = -1;
            
            for (j=0; j<tens.length; j++) {
                if (tens[j] == s.charAt(i)) {
                    nTenIdx = j;
                    break;
                }
            }
            
            if (nTenIdx == -1) {
                for (j=0; j<fives.length; j++) {
                    if (fives[j] == s.charAt(i)) {
                        nFiveIdx = j;
                        break;
                    }
                }
            }
            
            if (nTenIdx != -1) {
                if ((int)Math.pow(10, nTenIdx) > nPrev && nPrev > 0) {
                    ret = ret - nPrev + (int) Math.pow(10, nTenIdx) - nPrev;
                } else {
                    ret += (int) Math.pow(10, nTenIdx);
                }
            } else if (nFiveIdx != -1) {
                if ((int)Math.pow(10, nFiveIdx)*5 > nPrev && nPrev > 0) {
                    ret = ret - nPrev + (int) Math.pow(10, nFiveIdx)*5 - nPrev;
                } else {
                    ret += (int) Math.pow(10, nFiveIdx)*5;
                }
            }

            if (nTenIdx != -1) {
                nPrev = (int) Math.pow(10, nTenIdx);
            } else {
                nPrev = 0;
            }
        }
        
        return ret;
    }    
    
}
