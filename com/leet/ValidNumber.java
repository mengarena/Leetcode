package com.leet;

//Validate if a given string is numeric.
//
//Some examples:
//"0" => true
//" 0.1 " => true
//"abc" => false
//"1 a" => false
//"2e10" => true
//Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
//
//Update (2015-02-10):
//The signature of the C++ function had been updated. 
//If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.

//Linkedin
public class ValidNumber {

	public ValidNumber() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
//		String s = "5";   //t
//		String s = "  5";  //t
//		String s = "";   //false
//		String s = "  "; //false;
//		String s = "5   ";  //true;
//		String s = "  .5"; //true
//		String s = "5.   "; //true
//		String s = "a 1";  //false
//		String s = "k";   //false
//		String s = "1 k";  //false
//		String s = "1 a";  //false
//		String s = "k1";  //false
//		String s = "3b"; //false;
//		String s = "ab";  //false
//		String s = "0xab"; //true
//		String s = "3e0xff"; //false;
//		String s = "0xAAL"; //false;
//		String s = "B";  //false
//		String s = "5.";  //t
//		String s = "+.5";  //t
//		String s = "-.5";  //t
//		String s = "1+.5"; //false
//		String s = "2+0.5"; //false;
//		String s = ".5";   //t
//		String s = "1.2e10";  //t
//		String s = "1.2e1.2";  //False
//		String s = "e";        //False
//		String s = "2E";       //False
// 		String s = "1.2e1.";  //false
//		String s = "1.5f";   //true     //Leat code treat it as error for the tailing "f"
//		String s = "1F";   //true       //Leat code treat it as error for the tailing "F"
//		String s = "3l";   //true       //Leat code treat it as error for the tailing "l"
//		String s = "3L";   //true       //Leat code treat it as error for the tailing "L"
//		String s = "3.L";   //false     
//		String s = "3.0L";  //false
//		String s = "l";  //false;
// 		String s = "10.2e+3"; //true  *
//		String s = "10.2e-3";  //true  *
//		String s = "1.3f3e";  //false
//		String s = "0xFFbB";  //true
//		String s = "1e3L"; //false
//		String s = "1e3f";  //true      //Leat code treat it as error for the tailing "f"
//		String s = "1e3D"; // true      //Leat code treat it as error for the tailing "D"
//		String s = "10e"; //false;
//		String s = "07L";  //false      //Leat code treat it as error for the tailing "L"
//		String s = "07";  //true
//		String s = "0x00ff"; //true
//		String s = "++3"; //false
//		String s = "+-5"; //false;
//		String s = "+3e-3"; //true
//		String s = "3e++5"; //false;
//		String s = "3e+-5"; //false;
//		String s = "0e0" ; //true;
//		String s = "00e0"; //false;
//		String s = "0e00"; //false;
//		String s = "1ee1"; //false;
//		String s = "1e1e"; //false;
//		String s = " -."; //false;
		String s = "-0xEF";  //False
		
		System.out.println(isNumber(s));
	}

	
	//ACC:  73%
	//
	//Attention:  The test case in LeetCode does not consider the tailing type symbol (d, D, f, F, l, L), it treats these as error
	//To treat these type symbol, uncomment the related code
    public boolean isNumber(String s) {
       if (s == null || s.length() == 0) return false;
       int n = s.length();
       int i = 0;
       char carr[] = s.toCharArray();
       boolean signed = false;
       boolean dotted = false;
       boolean epowered = false;
       boolean digited = false;
       boolean prevE = false;
       boolean zeroStarted = false;
       boolean hexFormated = false;
       boolean numberReady = false;
       boolean typeSymbolled = false;
       
       if (n == 1) {
    	   if (carr[0] >= '0' && carr[0] <= '9') return true;
    	   return false;
       }
       
       while (i < n && isSpace(carr[i])) i++;
       
       if (i > n-1) return false;
       
       //First position
       if (isSign(carr[i])) {
    	   signed = true;
    	   numberReady = false;
       } else if (carr[i] == '.') {
    	   dotted = true;
    	   numberReady = false;
       } else if (isDigit(carr[i])) {
    	   digited = true;
    	   numberReady = true;
    	   if (carr[i] == '0') zeroStarted = true;
       } else {
    	   return false;
       }
       
       
       i++;
       
       if (i > n-1) return numberReady;
       
       //Second Position
       if (isSpace(carr[i])) {
    	   for (; i<n; i++) {
    		   if (!isSpace(carr[i])) return false; 
    	   }
    	   return numberReady;
       } else if (isSign(carr[i])) {
    	   return false;
       } else if (carr[i] == '.') {
    	   if (dotted == true) {
    		   return false;
    	   } else {
    		   dotted = true;
    		   if (digited == true) {
    		       numberReady = true;
    		   } else {
    			   numberReady = false;
    		   }
    	   }
       } else if (carr[i] == 'x') {
    	   if (zeroStarted == false) return false;
    	   hexFormated = true;
    	   digited = false;
    	   numberReady = false;
       } else if (isE(carr[i])) {
    	   if (digited == false) return false;
    	   epowered = true;
    	   prevE = true;
    	   numberReady = false;
//       } else if (isTypeSymbol(carr[i])) {
//    	   if (digited == false) return false;
//    	   typeSymbolled = true;
//    	   numberReady = true;
       } else {
    	   if (!isDigit(carr[i])) {
    		   return false;
    	   } else {    		   
    		   digited = true;
    		   numberReady = true;

    		   if (signed == true && carr[i] == '0') {
    			   zeroStarted = true;
    		   }
    	   }
       }
    
       if (i > n-1) return numberReady;
       i++;	   
       
       while (i < n) {
    	   if (typeSymbolled) break;
    	   
    	   if (isSign(carr[i])) { 
    		   if (signed == true) {
    			   if (prevE == true) {
    				   prevE = false;
    				   numberReady = false;
    			   } else {
    				   return false;
    			   }
    		   } else {
    			   if (prevE == true) {
    				   prevE = false;
    				   numberReady = false;
    			   } else {
    				   return false;
    			   }                	   
    		   }
    		   
    		   i++;
    		   continue;
    	   }
    	   
    	   if (carr[i] == '.') {    		   
    		   if (epowered == false) {
    			   if (dotted == true || hexFormated == true) return false;   //1..     0xF.    0x.
    			   dotted = true;
    			   numberReady = true;
    		   } else {
    			   return false;   //1.2e1. (wrong)
    		   }
    		   
    		   i++;
    		   continue;
    	   }
    	   
    	   if (isDigitHex(carr[i])) {    		   
    		   if (hexFormated == true) {
    			   if (dotted == true || epowered == true) return false; 
    			   numberReady = true;
    		   } else {
    			   if (isE(carr[i]))  {
    				   if (numberReady == false) return false;
    				   if (epowered == true) return false;
    				   epowered = true;
    				   prevE = true;
    				   numberReady = false;
//    			   } else if (isTypeSymbol(carr[i])) {  //d, f
//    				   if (numberReady == false) return false;
//    				   typeSymbolled = true;
//    				   i++;
//    				   break;
    			   } else {
    				   return false;
    			   }
    		   }
    		   
    		   i++;
    		   continue;
    	   }
    	   
//    	   if (isTypeSymbol(carr[i])) {
//    		   if (numberReady == false) return false;
//    		   if ((carr[i] == 'L' || carr[i] == 'l') && (hexFormated == true || dotted == true || epowered == true)) return false;
//    		   typeSymbolled = true;
//    		   i++;
//    		   break;
//    	   }
    	   
    	   
    	   if (carr[i] == 'x') {
    		   if (i > 2) return false;
    		   if (zeroStarted == true) {
    	    	   hexFormated = true;
    	    	   digited = false;
    	    	   numberReady = false;
    	    	   
    	    	   i++;
    	    	   continue;
    		   } else {
    			   return false;
    		   }
    	   }
    	       	   
    	   if (isSpace(carr[i])) {
    		   i++;
    		   break;    		   
    	   }
    	   
    	   if (isDigit(carr[i])) {    		   
    		   numberReady = true;
    		   
    		   i++;
    		   continue;    		   
    	   }
    	   
    	   return false;
       }
       
       for (;i<n; i++) {
    	   if (!isSpace(carr[i])) return false;
       }
       
       return numberReady;
       
    }

    
    
    private boolean isDigit(char c) {
    	if (c >= '0' && c <= '9') return true;
    	return false;
    }
    
    private boolean isDigitHex(char c) {
    	return ((c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'));
    }
    
    private boolean isSpace(char c) {
    	return c == ' ' || c == '\t';
    }
    
    private boolean isSign(char c) {
    	return c == '+' || c == '-';
    }
    
    private boolean isE(char c) {
    	return c == 'e' || c == 'E';
    }
    
    private boolean isTypeSymbol(char c) {
    	return c == 'D' || c == 'd' || c == 'f' || c == 'F' || c == 'l' || c == 'L';   //Double/Float/Long 
    }
    
    
    
    
    
/*
    public boolean isNumber(String s) {
       if (s == null || s.length() == 0) return false;
       int n = s.length();
       int i = 0;
       char carr[] = s.toCharArray();
       boolean signed = false;
       boolean signedAfterE = false;
       boolean dotted = false;
       boolean epowered = false;
       boolean digited = false;
       boolean prevE = false;
       boolean zeroStarted = false;
       boolean hexFormated = false;
       boolean numberReady = false;
       boolean typeSymbolled = false;
       boolean leadingZero = false;
       boolean leadingZeroAfterE = false;
       
       if (n == 1) {
    	   if (carr[0] >= '0' && carr[0] <= '9') return true;
    	   return false;
       }
       
       while (i < n && isSpace(carr[i])) i++;
       
       if (i > n-1) return false;
       
       //First postion
       if (isSign(carr[i])) {
    	   signed = true;
    	   numberReady = false;
       } else if (carr[i] == '.') {
    	   dotted = true;
    	   numberReady = false;
       } else if (isDigit(carr[i])) {
    	   digited = true;
    	   numberReady = true;
    	   if (carr[i] == '0') zeroStarted = true;
       } else {
    	   return false;
       }
       
       
       i++;
       
       if (i > n-1) return numberReady;
       
       //Second Position
       if (isSpace(carr[i])) {
    	   for (; i<n; i++) {
    		   if (!isSpace(carr[i])) return false; 
    	   }
    	   return numberReady;
       } else if (isSign(carr[i])) {
    	   return false;
       } else if (carr[i] == '.') {
    	   if (dotted == true) {
    		   return false;
    	   } else {
    		   dotted = true;
    		   numberReady = true;
    	   }
       } else if (carr[i] == 'x') {
    	   if (zeroStarted == false) return false;
    	   hexFormated = true;
    	   digited = false;
    	   numberReady = false;
       } else if (isE(carr[i])) {
    	   if (digited == false) return false;
    	   epowered = true;
    	   prevE = true;
    	   numberReady = false;
       } else if (isTypeSymbol(carr[i])) {
    	   if (digited == false) return false;
    	   typeSymbolled = true;
    	   numberReady = true;
       } else {
    	   if (!isDigit(carr[i])) {
    		   return false;
    	   } else {
    		   if (zeroStarted == true) return false;
    		   
    		   digited = true;
    		   numberReady = true;

    		   if (signed == true && carr[i] == '0') {
    			   zeroStarted = true;
    			   leadingZero = true;
    		   }
    	   }
       }
    
       if (i > n-1) return numberReady;
       i++;	   
       
       while (i < n) {
    	   if (typeSymbolled) break;
    	   
    	   if (isSign(carr[i])) { 
    		   if (signed == true) {
    			   if (prevE == true) {
    				   signedAfterE = true;
    				   prevE = false;
    				   numberReady = false;
    			   } else {
    				   return false;
    			   }
    		   } else {
    			   if (prevE == true) {
    				   signedAfterE = true;
    				   prevE = false;
    				   numberReady = false;
    			   } else {
    				   return false;
    			   }                	   
    		   }
    		   
    		   i++;
    		   continue;
    	   }
    	   
    	   if (carr[i] == '.') {    		   
    		   if (epowered == false) {
    			   if (dotted == true || hexFormated == true) return false;   //1..     0xF.    0x.
    			   dotted = true;
    			   numberReady = true;
    		   } else {
    			   return false;   //1.2e1. (wrong)
    		   }
    		   
    		   i++;
    		   continue;
    	   }
    	   
    	   if (isDigitHex(carr[i])) {    		   
    		   if (hexFormated == true) {
    			   if (dotted == true || epowered == true) return false; 
    			   numberReady = true;
    		   } else {
    			   if (isE(carr[i]))  {
    				   if (numberReady == false) return false;
    				   if (epowered == true) return false;
    				   epowered = true;
    				   prevE = true;
    				   numberReady = false;
    			   } else if (isTypeSymbol(carr[i])) {  //d, f
    				   if (numberReady == false) return false;
    				   typeSymbolled = true;
    				   i++;
    				   break;
    			   } else {
    				   return false;
    			   }
    		   }
    		   
    		   i++;
    		   continue;
    	   }
    	   
    	   if (isTypeSymbol(carr[i])) {
    		   if (numberReady == false) return false;
    		   if ((carr[i] == 'L' || carr[i] == 'l') && (hexFormated == true || dotted == true || epowered == true)) return false;
    		   typeSymbolled = true;
    		   i++;
    		   break;
    	   }
    	   
    	   
    	   if (carr[i] == 'x') {
    		   if (i > 2) return false;
    		   if (zeroStarted == true) {
    	    	   hexFormated = true;
    	    	   digited = false;
    	    	   numberReady = false;
    		   } else {
    			   return false;
    		   }
    	   }
    	       	   
    	   if (isSpace(carr[i])) {
    		   i++;
    		   break;    		   
    	   }
    	   
    	   if (isDigit(carr[i])) {
    		   
    		   if (epowered == false) {
    			   if (leadingZero == true) return false;
    		   } else {
    			   if (leadingZeroAfterE == true) return false;
    			   
    			   if (carr[i] == '0') {
    				   if (numberReady == false) {
    					   leadingZeroAfterE = true;
    				   }
    			   } else {
    				   leadingZeroAfterE = false;
    			   }
    		   }
    		   
    		   numberReady = true;
    		   
    		   i++;
    		   continue;    		   
    	   }
    	   
    	   return false;
       }
       
       for (;i<n; i++) {
    	   if (!isSpace(carr[i])) return false;
       }
       
       return numberReady;
       
    }

    
    
    private boolean isDigit(char c) {
    	if (c >= '0' && c <= '9') return true;
    	return false;
    }
    
    private boolean isDigitHex(char c) {
    	return ((c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'));
    }
    
    private boolean isSpace(char c) {
    	return c == ' ' || c == '\t';
    }
    
    private boolean isSign(char c) {
    	return c == '+' || c == '-';
    }
    
    private boolean isE(char c) {
    	return c == 'e' || c == 'E';
    }
    
    private boolean isTypeSymbol(char c) {
    	return c == 'D' || c == 'd' || c == 'f' || c == 'F' || c == 'l' || c == 'L';   //Double/Float/Long 
    } 
 
 * */    
    
    
    
    
}
