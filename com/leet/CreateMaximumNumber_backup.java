package com.leet;

import java.util.ArrayList;
import java.util.List;

public class CreateMaximumNumber_backup {

	public CreateMaximumNumber_backup() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
//		int[] nums1 = {3,4,6,5};
//		int[] nums2 = {9,1,2,5,8,3};

//		int[] nums1 = {6, 7};
//		int[] nums2 = {6, 0, 4};

//		int[] nums1 = {3, 9};
//		int[] nums2 = {8, 9};
		
//		int[] nums1 = {3,4,8,9,3,0};
//		int[] nums2	= {6,1,9,1,1,2};
				
		
//		int[] nums1 = {2,2,0,6,8,9,6};
//		int[] nums2 = {5,2,4,5,3,6,2};
		
//		int[] nums1 = {5,9,3,7,5,6,2,3};
//		int[] nums2	= {3,8,1,2,8,6,0,8};
		
		int[] nums1 = {8,0,4,4,1,7,3,6,5,9,3,6,6,0,2,5,1,7,7,7,8,7,1,4,4,5,4,8,7,6,2,2,9,4,7,5,6,2,2,8,4,6,0,4,7,8,9,1,7,0};
		int[] nums2 = {6,9,8,1,1,5,7,3,1,3,3,4,9,2,8,0,6,9,3,3,7,8,3,4,2,4,7,4,5,7,7,2,5,6,3,6,7,0,3,5,3,2,8,1,6,6,1,0,8,4};
		
		int k = 50;
		
		int[] narrRet = maxNumber(nums1, nums2, k);
		
		System.out.println("----");
		for (int i=0; i<narrRet.length; i++) {
			System.out.print(narrRet[i] + ",");
		}
	}

	
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int i = Math.max(k - nums2.length, 0); i <= Math.min(nums1.length, k); i++) {
            int[] res1 = get_max_sub_array(nums1, i);
            int[] res2 = get_max_sub_array(nums2, k - i);
            int[] res = new int[k];
            int pos1 = 0, pos2 = 0, tpos = 0;
 
            while (pos1 < res1.length || pos2 < res2.length) {
                res[tpos++] = greater(res1, pos1, res2, pos2) ? res1[pos1++] : res2[pos2++];
            }
 
            if (!greater(ans, 0, res, 0))
                ans = res;
        }
 
        return ans;
    }
 
    public boolean greater(int[] nums1, int start1, int[] nums2, int start2) {
        for (; start1 < nums1.length && start2 < nums2.length; start1++, start2++) {
            if (nums1[start1] > nums2[start2]) return true;
            if (nums1[start1] < nums2[start2]) return false;
        }
        return start1 != nums1.length;
    }
 
    public int[] get_max_sub_array(int[] nums, int k) {
        int[] res = new int[k];
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            while (len > 0 && len + nums.length - i > k && res[len - 1] < nums[i]) {
                len--;
            }
            if (len < k)
                res[len++] = nums[i];
        }
        return res;
    }	
	
	
	
	
	
	
    
        
    //Compares the four numbers presented as the four strings
    public String myMaxString(String sNumStr1a, String sNumStr1b, String sNumStr2a, String sNumStr2b) {
    	String sMaxString = sNumStr1a;
    	
    	sMaxString = myMaxString(sMaxString, sNumStr1b);
    	sMaxString = myMaxString(sMaxString, sNumStr2a);
    	sMaxString = myMaxString(sMaxString, sNumStr2b);
    	
    	return sMaxString;
    }
    
  //Compare the two numbers presented as the two strings
    public String myMaxString(String strA, String strB) {
    	int nLenA = strA.length();
    	int nLenB = strB.length();
    	
    	if (nLenA > nLenB) {
    		return strA;
    	} else if (nLenA < nLenB) {
    		return strB;
    	} else {
    		if (strA.compareTo(strB) < 0) {
    			return strB;
    		} else {
    			return strA;
    		}
    	}
    	
    }
   
    
    //Works
//    public String myMaxNumber(int[] nums1, int nIdx1, int[] nums2, int nIdx2, int k) {
//    	String sRet = "";
//    	String sNumStr1a = "";
//    	String sNumStr1b = "";
//    	String sNumStr2a = "";
//    	String sNumStr2b = "";
//    	
//    	if (k == 0) return "";
//    	
//    	if (nums1 != null && nIdx1 < nums1.length) {  
//    		if (nIdx1+1 < nums1.length && nums1[nIdx1] < nums1[nIdx1+1] && nums1.length - (nIdx1+1) + nums2.length - nIdx2 >= k) {
//    			sNumStr1a = "";
//    		} else {
//    			sNumStr1a = nums1[nIdx1] + myMaxNumber(nums1, nIdx1+1, nums2, nIdx2, k-1);
//    		}
//    	
//    		sNumStr1b = myMaxNumber(nums1, nIdx1+1, nums2, nIdx2, k);
//    	}
//    	
//    	if (nums2 != null && nIdx2 < nums2.length) {    
//    		if (nIdx2+1 < nums2.length && nums2[nIdx2] < nums2[nIdx2+1] && nums2.length - (nIdx2+1) + nums1.length - nIdx1 >= k) {
//    			sNumStr2a = "";
//    		} else {
//    			sNumStr2a = nums2[nIdx2] + myMaxNumber(nums1, nIdx1, nums2, nIdx2+1, k-1);
//    		}
//    		
//    		sNumStr2b = myMaxNumber(nums1, nIdx1, nums2, nIdx2+1, k);    		
//    	}
//    	
//    	sRet = myMaxString(sNumStr1a, sNumStr1b, sNumStr2a, sNumStr2b);
//
//    	return sRet;
//    }    
    
    
     //Works    
//    public String myMaxNumber(int[] nums1, int nIdx1, int[] nums2, int nIdx2, int k) {
//    	String sRet = "";
//    	String sNumStr1a = "";
//    	String sNumStr1b = "";
//    	String sNumStr2a = "";
//    	String sNumStr2b = "";
//    	
//    	if (k == 0) return "";
//    	
//    	if (nums1 != null && nIdx1 < nums1.length) {    		
//    		sNumStr1a = nums1[nIdx1] + myMaxNumber(nums1, nIdx1+1, nums2, nIdx2, k-1);
//    		sNumStr1b = myMaxNumber(nums1, nIdx1+1, nums2, nIdx2, k);
//    	}
//    	
//    	if (nums2 != null && nIdx2 < nums2.length) {    		
//    		sNumStr2a = nums2[nIdx2] + myMaxNumber(nums1, nIdx1, nums2, nIdx2+1, k-1);
//    		sNumStr2b = myMaxNumber(nums1, nIdx1, nums2, nIdx2+1, k);    		
//    	}
//    	
//    	sRet = myMaxString(sNumStr1a, sNumStr1b, sNumStr2a, sNumStr2b);
//
//    	return sRet;
//    }        
    
//    public String backup_myMaxNumber(List<Integer> lstNums1, List<Integer> lstNums2, int k) {
//    	String sRet = "";
//    	int nInitNum;
//    	String sNumStr1a = "";
//    	String sNumStr1b = "";
//    	String sNumStr2a = "";
//    	String sNumStr2b = "";
//    	List<Integer> lstNums1Org = new ArrayList<Integer>();
//    	List<Integer> lstNums2Org = new ArrayList<Integer>();
//    	
//    	if (k == 0) return "";
//    	
//    	for (Integer num1:lstNums1) lstNums1Org.add(num1);
//    	for (Integer num2:lstNums2) lstNums2Org.add(num2);
//
//    	if (lstNums1 != null && lstNums1.size() > 0) {
//    		nInitNum = lstNums1.get(0);
//    		lstNums1.remove(0);
//    		List<Integer> lstNums1OrgA = new ArrayList<Integer>();
//    		List<Integer> lstNums2OrgA = new ArrayList<Integer>();
//    		for (Integer num1:lstNums1) lstNums1OrgA.add(num1);
//    		for (Integer num2:lstNums2) lstNums2OrgA.add(num2);
//    		
//    		sNumStr1a = nInitNum + myMaxNumber(lstNums1, lstNums2, k-1);
//    		sNumStr1b = myMaxNumber(lstNums1OrgA, lstNums2OrgA, k);
//    	}
//    	
//    	if (lstNums2Org != null && lstNums2Org.size() > 0) {
//    		nInitNum = lstNums2Org.get(0);
//    		lstNums2Org.remove(0);
//    		
//    		List<Integer> lstNums1OrgB = new ArrayList<Integer>();
//    		List<Integer> lstNums2OrgB = new ArrayList<Integer>();
//    		for (Integer num1:lstNums1Org) lstNums1OrgB.add(num1);
//    		for (Integer num2:lstNums2Org) lstNums2OrgB.add(num2);
//
//    		sNumStr2a = nInitNum + myMaxNumber(lstNums1Org, lstNums2Org, k-1);
//    		sNumStr2b = myMaxNumber(lstNums1OrgB, lstNums2OrgB, k);    		
//    	}
//    	
//    	sRet = myMaxString(sNumStr1a, sNumStr1b, sNumStr2a, sNumStr2b);
//
//    	return sRet;
//    }    
   
   
    public int[] getMaxString(int[] nums, int nIdx, int k) {
        int[] res = new int[k];
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            while (len > 0 && len + nums.length - i > k && res[len - 1] < nums[i]) {
                len--;
            }
            if (len < k)
                res[len++] = nums[i];
        }

		
    	return res;
    }
    
    public String myMaxNumber(int[] nums1, int nIdx1, int[] nums2, int nIdx2, int k) {
    	String sRet = "";
    	String sNumStr1a = "";
    	String sNumStr2a = "";
    	int i, j;
    	
    	if (k == 0) return "";

    	if (nums1.length - nIdx1 + nums2.length - nIdx2 == k && k > 0) {
    		i = nIdx1; j=nIdx2;
    		int n1, n2;
    		while (i<nums1.length || j<nums2.length) {
    			if (i < nums1.length) {
    				n1 = nums1[i];
    			} else {
    				n1 = -1;
    			}
    			
    			if (j < nums2.length) {
    				n2 = nums2[j];
    			} else {
    				n2 = -2;
    			}
    			
    			if (n1 >= n2 && n1 >= 0) {
    				sRet = sRet + n1;
    				i = i + 1;
    			} else if (n1 < n2) {
    				sRet = sRet + n2;
    				j = j + 1;
    			}
    			
    		}
    		
    		return sRet;
    	}
    	    	
    	
    	if (nums1 != null && nIdx1 < nums1.length) {  
    		
    		if (nIdx1 + 1 < nums1.length && nIdx2 < nums2.length) {
    			if (nums1[nIdx1] < nums1[nIdx1+1] || nums1[nIdx1] < nums2[nIdx2]) {
    				sNumStr1a = myMaxNumber(nums1, nIdx1+1, nums2, nIdx2, k);
    			} else {
    				sNumStr1a = nums1[nIdx1] + myMaxNumber(nums1, nIdx1+1, nums2, nIdx2, k-1);
    			}
    		} else if (nIdx1 + 1 == nums1.length && nIdx2 < nums2.length) {
    			if (nums1[nIdx1] < nums2[nIdx2]) {
    				sNumStr1a = myMaxNumber(nums1, nums1.length, nums2, nIdx2, k);
    			} else {
    				sNumStr1a = nums1[nIdx1] + myMaxNumber(nums1, nums1.length, nums2, nIdx2, k-1);
    			}
    		} else if (nIdx1 + 1 < nums1.length && nIdx2 == nums2.length) {
    			if (nums1[nIdx1] < nums1[nIdx1+1]) {
    				sNumStr1a = myMaxNumber(nums1, nIdx1+1, nums2, nums2.length, k);
    			} else {
    				sNumStr1a = nums1[nIdx1] + myMaxNumber(nums1, nIdx1+1, nums2, nums2.length, k-1);
    			}
    		} else {  //nIdx1 + 1 == nums1.length && nIdx2 == nums2.length
				sNumStr1a = nums1[nIdx1] + "";
    		}
    		    	
    	}
    	
    	
    	if (nums2 != null && nIdx2 < nums2.length) {    
    		if (nIdx2 + 1 < nums2.length && nIdx1 < nums1.length) {
    			if (nums2[nIdx2] < nums2[nIdx2+1] || nums2[nIdx2] < nums1[nIdx1]) {
    				sNumStr2a = myMaxNumber(nums1, nIdx1, nums2, nIdx2+1, k);
    			} else {
    				sNumStr2a = nums2[nIdx2] + myMaxNumber(nums1, nIdx1, nums2, nIdx2+1, k-1);
    			}
    		} else if (nIdx2 + 1 == nums2.length && nIdx1 < nums1.length) {
    			if (nums2[nIdx2] < nums1[nIdx1]) {
    				sNumStr2a = myMaxNumber(nums1, nIdx1, nums2, nums2.length, k);
    			} else {
    				sNumStr2a = nums2[nIdx2] + myMaxNumber(nums1, nIdx1, nums2, nums2.length, k-1);
    			}
    		} else if (nIdx2 + 1 < nums2.length && nIdx1 == nums1.length) {
    			if (nums2[nIdx2] < nums2[nIdx2+1]) {
    				sNumStr2a = myMaxNumber(nums1, nums1.length, nums2, nIdx2+1, k);
    			} else {
    				sNumStr2a = nums2[nIdx2] + myMaxNumber(nums1, nums1.length, nums2, nIdx2+1, k-1);
    			}
    		} else {
				sNumStr2a = nums2[nIdx2] + "";
    		}
    	}
    	
    	sRet = myMaxString(sNumStr1a, sNumStr2a);

    	return sRet;
    }    
    
}
