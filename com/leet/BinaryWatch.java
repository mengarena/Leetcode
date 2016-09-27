package com.leet;

import java.util.ArrayList;
import java.util.List;

//A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
//
//Each LED represents a zero or one, with the least significant bit on the right.
//
//    x x
//8 4 2 1
//
//   x  x     x
//32 16 8 4 2 1
//
//For example, the above binary watch reads "3:25".
//
//Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
//
//Example:
//
//Input: n = 1
//Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
//		
//Note:
//The order of output does not matter.
//The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
//The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".


public class BinaryWatch {

	public BinaryWatch() {
		// TODO Auto-generated constructor stub
	}

	
	public void run() {
		int num = 4;
		
		List<String> lstRet = readBinaryWatch(num);
		
		System.out.println("----");
	}
	
	
	//ACC:  46%
    public List<String> readBinaryWatch(int num) {
        int[] hours = {8, 4, 2, 1};
        int[] mins = {32, 16, 8, 4, 2, 1};
        List<String> lstWatch = new ArrayList<String>();
        
        int nHourCnt = hours.length;
        int nMinCnt = mins.length;
        
        if (num <= 0) {
            lstWatch.add("0:00");
            return lstWatch;
        }
        
        int i, j;
        
        for (i=0; i<=Math.min(num, 4); i++) {
            j = num - i;
            
            if (j > mins.length) continue;
            
            List<String> lstHour = getHour(hours, i);
            List<String> lstMin = getMin(mins, j);
            
            if (lstHour.size() == 0) lstHour.add("0");
            if (lstMin.size() == 0) lstMin.add("00");
            
            for (String sHour:lstHour) {
                for (String sMin:lstMin) {
                    if (Integer.parseInt(sHour) < 12 && Integer.parseInt(sMin) < 60) {
                        lstWatch.add(sHour + ":" + sMin);
                    }
                }
            }
        }
        
        return lstWatch;
    }
    
    private List<List<Integer>> getHourMin(int[] hours, int cnt, int startIdx) {
        List<List<Integer>> lstlstHour = new ArrayList<List<Integer>>();
        int n = hours.length;
        int i;
        
        if (startIdx >= n || cnt == 0 || cnt > hours.length-startIdx) return lstlstHour;
        
        if (cnt == 1) {
            for (i=startIdx; i<n; i++) {
                List<Integer> lstHour = new ArrayList<Integer>();
                lstHour.add(hours[i]);
                lstlstHour.add(lstHour);
            }
            
            return lstlstHour;
        }
        
        
        for (i=startIdx; i<n-cnt+1; i++) {
             List<List<Integer>> lstlstHourTmp = getHourMin(hours, cnt-1, i+1);
             for (List<Integer> lstHourTmp:lstlstHourTmp) {
                 lstHourTmp.add(0, hours[i]);
                 lstlstHour.add(lstHourTmp);
             }
        }
        
        return lstlstHour;
    }
    
    private List<String> getHour(int[] hours, int cnt) {
        List<String> lstHour = new ArrayList<String>();
        
        if (cnt == 0 || cnt > hours.length) {
            return lstHour;
        }
        
        List<List<Integer>> lstlstHour = getHourMin(hours, cnt, 0);
        
        if (lstlstHour.size() == 0) return lstHour;
        
        for (List<Integer> lstHourTmp:lstlstHour) {
            int sum = 0;
            for (int hour:lstHourTmp) sum += hour;
            lstHour.add(sum + "");
        }
        
        return lstHour;
    }
    
    private List<String> getMin(int[] mins, int cnt) {
        List<String> lstMin = new ArrayList<String>();
        if (cnt == 0 || cnt > mins.length) {
            return lstMin;
        }
        
        List<List<Integer>> lstlstMin = getHourMin(mins, cnt, 0);
        
        if (lstlstMin.size() == 0) return lstMin;
        
        for (List<Integer> lstMinTmp:lstlstMin) {
            int sum = 0;
            for (int min:lstMinTmp) sum += min;
            if (sum < 10) {
                lstMin.add("0" + sum);
            } else {
                lstMin.add("" + sum);
            }
        }
        
        return lstMin;
    }    

}
