package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a string containing only digits, restore it by returning all possible valid IP address combinations.
//
//For example:
//Given "25525511135",
//
//return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

public class RestoreIPAddresses {

	public RestoreIPAddresses() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		//String s = "25525511135";
		String s = "010010";
		
		List<String> lstValidIP = restoreIpAddresses(s);
		for (String sIP:lstValidIP) System.out.print(sIP + ",  ");
	}

	//Valid IP:  each part: 0~255
	//For IP address, each part if not one digit, should not start with 0
    public List<String> restoreIpAddresses(String s) {
        List<List<String>> lstlstValidIP = new ArrayList<List<String>>();
        List<String> lstValidIP = new ArrayList<String>();
        if (s == null || s.isEmpty() || s.length() > 12 || s.length() < 4) return lstValidIP;
        
        lstlstValidIP = getIpAddress(s, 4); 
        
        for (List<String> lstValidIpTmp:lstlstValidIP) {
        	String sValidIP = "";
        	for (String sValidIpPart:lstValidIpTmp) {
        		if (sValidIP.isEmpty())  {
        			sValidIP = sValidIpPart;
        		} else {
        			sValidIP = sValidIP + "." + sValidIpPart;
        		}
        	}
        	
        	lstValidIP.add(sValidIP);
        }
        
        return lstValidIP;
    }
	
    
	private List<List<String>> getIpAddress(String s, int nRemainedPartCnt) {
		List<List<String>> lstlstValidIP = new ArrayList<List<String>>();
		int n = s.length();
		
		if (nRemainedPartCnt == 1) {
			if (n == 1) {
				List<String> lstValidIp = new ArrayList<String>();
				lstValidIp.add(s);
				lstlstValidIP.add(lstValidIp);				
			} else if (n > 1 && n <= 3) {
				if (s.charAt(0) != '0' && Integer.valueOf(s) <= 255) {
					List<String> lstValidIp = new ArrayList<String>();
					lstValidIp.add(s);
					lstlstValidIP.add(lstValidIp);					
				}
			}
			
			return lstlstValidIP;
		}
	
		int nMaxLen = Math.min(3,  n);
		
		for (int i=1; i<=nMaxLen; i++) {
			if (n-i < nRemainedPartCnt-1 || n-i > 3*(nRemainedPartCnt-1) ) continue;
			
			String sPart = s.substring(0, i);
			if (Integer.valueOf(sPart) > 255 || (sPart.length() > 1 && s.charAt(0) == '0')) continue;
			
			List<List<String>> lstlstValidIPTmp = getIpAddress(s.substring(i), nRemainedPartCnt-1);
			
			for (List<String> lstValidIPTmp:lstlstValidIPTmp) {
				lstValidIPTmp.add(0, sPart);
				lstlstValidIP.add(lstValidIPTmp);
			}
		}
	
		return lstlstValidIP;
	}
}
