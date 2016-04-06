package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a sorted integer array without duplicates, return the summary of its ranges.
//
//For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
	
//Google
public class SummaryRanges {

	public SummaryRanges() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {1, 3};
		
		List<String> lstRanges = summaryRanges(nums);
		
		if (lstRanges == null) return;
		
		for (String sRange:lstRanges) System.out.println(sRange);
	}
	
    public List<String> summaryRanges(int[] nums) {
        List<String> lstRanges = new ArrayList<String>();
        
        if (nums == null) return null;
        if (nums.length == 0) return lstRanges;
        if (nums.length == 1) {
        	lstRanges.add(nums[0] + "");
        	return lstRanges;
        }
        
        int nStart, nStartIdx, nEnd;
        int i;
        String sRange = "";
        
        nStart = nums[0];
        nStartIdx = 0;
        sRange = nums[0] + "";
        
        for (i=1; i<nums.length; i++) {
        	nEnd = nums[i];
        	
        	if (nEnd - nStart == i - nStartIdx) {
        		continue;
        	} else {
        		if (nums[i-1] - nStart > 0) {
        			sRange = sRange + "->" + nums[i-1];
        		}
        		lstRanges.add(sRange);
        		sRange = nEnd + "";
        		nStart = nEnd;
        		nStartIdx = i;
        	}
        }
        
        if (nums[nums.length-1] - nStart > 0) {
        	sRange = sRange + "->" + nums[nums.length-1];
        }
        
		lstRanges.add(sRange);
     
        return lstRanges;
    }
	
}
