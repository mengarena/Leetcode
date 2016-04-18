package com.leet;

import java.util.ArrayList;
import java.util.List;

//Design an algorithm to encode a list of strings to a string. 
//The encoded string is then sent over the network and is decoded back to the original list of strings.
//
//Machine 1 (sender) has the function:
//
//string encode(vector<string> strs) {
//  // ... your code
//  return encoded_string;
//}
//Machine 2 (receiver) has the function:
//vector<string> decode(string s) {
//  //... your code
//  return strs;
//}
//So Machine 1 does:
//
//string encoded_string = encode(strs);
//and Machine 2 does:
//
//vector<string> strs2 = decode(encoded_string);
//strs2 in Machine 2 should be the same as strs in Machine 1.
//
//Implement the encode and decode methods.
//
//Note:
//The string may contain any possible characters out of 256 valid ascii characters. 
//Your algorithm should be generalized enough to work on any possible characters.
//Do not use class member/global/static variables to store states. 
//Your encode and decode algorithms should be stateless.
//Do not rely on any library method such as eval or serialize methods. 
//You should implement your own encode/decode algorithm.


//Google
public class EncodeDecodeStrings {

	public EncodeDecodeStrings() {
		// TODO Auto-generated constructor stub
	}

	
	public void run() {
		List<String> lstRs = new ArrayList<String>();
		lstRs.add("meng");
		lstRs.add("ru");
		lstRs.add("feng");
		lstRs.add("happy");
		lstRs.add("man!");
		
		String sEncode = encode(lstRs);
		
		System.out.println(sEncode);
		
		List<String> lstRR = decode(sEncode);
		
		for (String s:lstRR) System.out.println(s);
		
		System.out.println();
	}
	
	
	//AC:  53%
	
    // Encodes a list of strings to a single string.
	//Using length followed by "#" to delimit strings in encoded version
    public String encode(List<String> strs) {
        if (strs == null || strs.size() == 0) return null;
        StringBuilder sb = new StringBuilder();
        int nLen = 0;
        
        for (String s:strs) {
        	nLen = s.length();
        	sb.append(Integer.toString(nLen));
        	sb.append("#");
        	sb.append(s);
        }
        
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> lstRet = new ArrayList<String>();
        
        if (s == null) return lstRet;
        int n = s.length();
        int i;
        int nLen = 0;
        int nStartPos = 0;
        
        i=0; 
        nStartPos = 0;
        while (i < n) {
        	if (s.charAt(i) != '#') {
        		i++;
        		continue;
        	}
        	
        	nLen = Integer.valueOf(s.substring(nStartPos, i)).intValue();
        	
        	lstRet.add(s.substring(i+1, i+1+nLen));
        	
        	i = i + 1 + nLen;
        	nStartPos = i;
        	
        }

        return lstRet;
    }

}

//Your Codec object will be instantiated and called as such:
//Codec codec = new Codec();
//codec.decode(codec.encode(strs));

