package com.leet;

import java.util.Stack;

//Given an encoded string, return it's decoded string.
//
//The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. 
//Note that k is guaranteed to be a positive integer.
//
//You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
//
//Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, 
//k. For example, there won't be input like 3a or 2[4].
//
//Examples:
//
//s = "3[a]2[bc]", return "aaabcbc".
//s = "3[a2[c]]", return "accaccacc".
//s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

		
//Google		
public class DecodeString {

	public DecodeString() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String s = "3[a]2[bc]";
		
		String sDecoded = decodeString(s);
		
		System.out.println(sDecoded);
	}
	
	
	//ACC:  5ms
	//Many case:
	//abcd
	//2[abc]xyc3[d]
	//sd2[f2[e]g]i
	//sd2[2[e]]i
	//sd2[2[e]g]i
	//3[a[2[c]]
	//3[a]2[bc]
    public String decodeString(String s) {
        if (s == null || s.isEmpty()) return s;
        Stack<Integer> stkCount = new Stack<Integer>();
        Stack<StringBuilder> stkStr = new Stack<StringBuilder>();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        
        for (char c:s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                count = count*10 + (int) (c-'0');
            } else if (c == '[') {
                stkCount.push(count);
                stkStr.push(sb);   //sb could be empty or already has some content
                count = 0;
                sb = new StringBuilder();
            } else if (c == ']') {
                count = stkCount.pop();
                StringBuilder repeatPart = sb;
                sb = stkStr.pop();
                for (; count>=1; count--) sb.append(repeatPart);
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
	
	
	
	
	//ACC:  5ms
	public String decodeStringA(String s) {
        if (s == null || s.isEmpty()) return s;
        int n = s.length();
        int i,j;
        String sPart = "";
        String sPrev = "";
        int count = 0;
        StringBuilder sb = new StringBuilder();
        int start = -1;
        int prevChar = -1;  //0: digit;  1: character;    2: '[';    3: ']'
        char c = 0;
        Stack<String> stk = new Stack<String>();
        
        for (i=0; i<n; i++) {
            c = s.charAt(i);
            
            if (c >= '0' && c <= '9') {
                if (prevChar == 1) {
                    sPart = s.substring(start, i);
                    if (!stk.isEmpty() && !isDigit(stk.peek())) {
                        sPart = stk.pop() + sPart;
                    }
                    
                    stk.push(sPart);
                    start = -1;
                }
                
                if (start == -1) start = i;
                
                prevChar = 0;
            } else if (c == '[') {
                if (start >= 0) {
                    stk.push(s.substring(start, i));
                }
                
                start = -1;
                prevChar = 2;
            } else if (c == ']') {
               if (start != -1) {
                   sPart = s.substring(start, i);
                   if (!stk.isEmpty() && !isDigit(stk.peek())) {
                       sPart = stk.pop() + sPart;
                   }
               } else {
                   sPart = stk.pop();
               }       
                   
               sPrev = stk.pop();
                   
               if (isDigit(sPrev)) {
                    count = Integer.parseInt(sPrev);
                    sb = new StringBuilder();
                       
                    for (j=1; j<=count; j++) sb.append(sPart);
                    
                    if (!stk.isEmpty()) {   
                        sPrev = stk.peek();
                        if (!isDigit(sPrev)) {
                            sPrev = stk.pop();
                            sb.insert(0, sPrev);
                        }
                    }
                       
                    stk.push(sb.toString());
               } 

               start = -1;
               
               prevChar = 3; 
            } else {
                if (start == -1) start = i;
                
                prevChar = 1; 
            }
        }
        
        sPart = "";
        
        if (!stk.isEmpty()) sPart = stk.pop();
        
        if (start != -1) {
            sPart = sPart + s.substring(start);
        }
        
        return sPart;
    }
    
    private boolean isDigit(String s) {
        if (s.charAt(0) >= '0' && s.charAt(0) <= '9') return true;
        return false;
    }


}
