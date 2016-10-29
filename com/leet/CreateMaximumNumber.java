package com.leet;

//Given two arrays of length m and n with digits 0-9 representing two numbers. 
//Create the maximum number of length k <= m + n from digits of the two. 
//The relative order of the digits from the same array must be preserved. 
//Return an array of the k digits. You should try to optimize your time and space complexity.
//
//Example 1:
//nums1 = [3, 4, 6, 5]
//nums2 = [9, 1, 2, 5, 8, 3]
//k = 5
//return [9, 8, 6, 5, 3]
//
//Example 2:
//nums1 = [6, 7]
//nums2 = [6, 0, 4]
//k = 5
//return [6, 7, 6, 0, 4]
//
//Example 3:
//nums1 = [3, 9]
//nums2 = [8, 9]
//k = 3
//return [9, 8, 9]

//Google
//Hard
public class CreateMaximumNumber {

	public CreateMaximumNumber() {
		// TODO Auto-generated constructor stub
	}
	

    public void run() {
//		int[] nums1 = {3,4,6,5};
//		int[] nums2 = {9,1,2,5,8,3};
//    	int k = 5;
    	
    	int[] nums1 = {2,5,6,4,4,0};
    	int[] nums2 = {7,3,8,0,6,5,7,6,2};
    	int k = 15;
    	
		int[] narrRet = maxNumber(nums1, nums2, k);
		
		
		System.out.println("----");
		for (int i=0; i<narrRet.length; i++) {
			System.out.print(narrRet[i] + ",");
		}
    	
    }
    

    
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
    	if (k == 0) return null;
    	if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0)) return null;
    	if ((nums1 == null || nums1.length == 0) && (nums2.length < k)) return null;
    	if ((nums1.length < k) && (nums2 == null || nums2.length == 0)) return null;
    	int n1 = nums1.length;
    	int n2 = nums2.length;
    	int[] narrNumRet = new int[k];
    	
    	int nMinLen1 = Math.max(0, k-n2);  //From nums1, at least use #nMinLen1 elements
    	int nMaxLen1 = Math.min(k, n1);    //From nums1, at most use #nMaxLen1 elements
    	
    	for (int i=nMinLen1; i<=nMaxLen1; i++) {
    		int[] subNums1 = getMaxSubArray(nums1, i);
    		int[] subNums2 = getMaxSubArray(nums2, k-i);
    		
    		//Merge subNums1, subNums2
    		int t1 = 0;
    		int t2 = 0;
    		int t = 0;
    		int[] narrNum = new int[k];
    		
    		while (t1 < subNums1.length || t2 < subNums2.length) {
    			if (IsGreater(subNums1, t1, subNums2, t2)) {
    				narrNum[t++] = subNums1[t1++];
    			} else {
    				narrNum[t++] = subNums2[t2++];
    			}
    		}
    		
    		if (IsGreater(narrNum, 0, narrNumRet, 0)) narrNumRet = narrNum;
    	
    	}
    	
    	return narrNumRet;
    }
    
    
    //If the prefix is same for the nums1, nums2, the longer array will be "greater" 
    public boolean IsGreater(int[] nums1, int start1, int[] nums2, int start2) {
        for (; start1 < nums1.length && start2 < nums2.length; start1++, start2++) {
            if (nums1[start1] > nums2[start2]) return true;
            if (nums1[start1] < nums2[start2]) return false;
        }
        return start1 != nums1.length;
    }
    
    
    private int[] getMaxSubArray(int[] narrNums, int k) {
    	int[] narrRet = new int[k];
    	int n = narrNums.length;
    	int i;
    	int nPrevIdx = -1;
    	
    	for (i=0; i<k; i++) {
    		int nMax = 0;
    		int nTmpPrevIdx = nPrevIdx+1;
    		
    		//Possible searching range of each element
    		for (int j=n-k+i; j>=nPrevIdx+1; j--) {
    			if (nMax <= narrNums[j]) {
    				nMax = narrNums[j];
    				nTmpPrevIdx = j;
    			}
    		}
    		
    		nPrevIdx = nTmpPrevIdx;
    		narrRet[i] = nMax;
    	}
    	
    	return narrRet;
    }
    
    
//    private boolean IsGreater(int[] narr1, int[] narr2) {
//    	if (narr1.length != narr2.length) return true;
//    	
//    	for (int i=0; i<narr1.length; i++) {
//    		if (narr1[i] < narr2[i]) return false;
//    		if (narr1[i] > narr2[i]) return true;
//    	}
//    	
//    	return false;
//    }
}


//if (t1 == subNums1.length) {
//	narrNum[t++] = subNums2[t2++];
//} else if (t2 == subNums2.length) {
//	narrNum[t++] = subNums1[t1++];
//} else {
//	if (subNums1[t1] > subNums2[t2]) {
//		narrNum[t++] = subNums1[t1++];
//	} else if (subNums1[t1] < subNums2[t2]) {
//		narrNum[t++] = subNums2[t2++];
//	} else {
//		if (subNums1.length-t1 > subNums2.length-t2) {
//			narrNum[t++] = subNums1[t1++];
//		} else {
//			narrNum[t++] = subNums2[t2++];
//		}
//	}
//}
