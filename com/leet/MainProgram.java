package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainProgram {

	static int DD;
	int ling = 11;
	
	public MainProgram() {
		// TODO Auto-generated constructor stub
	}

    private static int findMedian(int[] nums) {
    	if (nums == null || nums.length == 0) return 0;
    	if (nums.length == 1) return nums[0];
    	int nMedth = (nums.length-1)/2+1;  //Median position
    	int nLeft = Integer.MIN_VALUE; 
    	int nRight = Integer.MAX_VALUE;
    	int nMedian = 0;
		int nSmallerCnt = 0, nLargerCnt = 0;
		int nCntDiff = 0;
    	
    	while (nLeft <= nRight) {
    		nMedian = (nLeft < 0 && nRight > 0) ? (nLeft + nRight) / 2 : nLeft + (nRight - nLeft) / 2;
    		nSmallerCnt = 0;
    		nLargerCnt = 0;
    		nCntDiff = 0;
    		
    		for (int i=0; i<nums.length; i++) {
    			if (nums[i] > nMedian) {
    				nLargerCnt++;
    				if (nLargerCnt > nMedth) {
    					nCntDiff = 1;
    					break;
    				}
    			} else if (nums[i] < nMedian) {
    				nSmallerCnt++;
    				if (nSmallerCnt > nMedth) {
    					nCntDiff = -1;
    					break;
    				}
    			}
    		}
    		    		
    		if (nCntDiff > 0) {
    			nLeft = nMedian + 1;
    		} else if (nCntDiff < 0) {
    			nRight = nMedian - 1;
    		} else {
    			break;
    		}

    	}
    	
    	return nMedian;
    }
    
	private static int findPos(int[] nums, int target) {
		if (nums == null || nums.length == 0) return 0;
		int n = nums.length;
		int i = 0, j = n - 1;
		int mid = 0;
		
		while (i <= j) {
			mid = (i+j)/2;
			
			if (nums[mid] == target) {
				System.out.println("#Number.Length = " + n + ", Mid = " + mid);

				return mid;
			} else if (nums[mid] > target) {
				j = mid - 1;
			} else {
				i = mid + 1;
			}
		}
		
		System.out.println("#Number.Length = " + n + ", Pos = " + i + "    (j = " + j + ")");
		
		return i;
	}
 	
	
	public static void change(StringBuilder x) {
		x.delete(0, 2).append("cd");
	}	
	
	public static void change(String y) {
		y = "cd";
	}	
	
	
	private static void Swaps(int a, int b) {
		System.out.println("Before = " + a + ", " + b);
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		
		System.out.println("After = " + a + ", " + b);
	}
	
	
	private static void testkk() {
		Map<Integer, List<Integer>> hm = new HashMap<>();
		
		
		List<Integer> kk = new ArrayList<>();
		kk.add(3); kk.add(6); kk.add(9);
		
		hm.put(5, kk);
		
		List<Integer> mm = hm.get(5);
		
		mm.remove(1);
		
		List<Integer> nn = hm.get(5);
		
		for (int k : nn) {
			System.out.println(k);
		}
	}
	
	
	private int test11() {
		try {
			return 1;
		} finally {
			System.out.println("Finally");
		}
	}
	
	
	public static class StartIdx implements Comparable<StartIdx> {
		int start;
		int idx;
		
		public StartIdx(int start, int idx) {
			this.start = start;
			this.idx = idx;
		}
		
		public int compareTo(StartIdx other) {
			//return this.start - other.start;   //Order:  Small ---> Large
			
			return other.start - this.start;   //Order: Large ---> Small
			
			//if (this.start < other.start) {
			//	return -1;
			//} else {
			//	return 1;
			//}
		}
	}
	
	
	public static void TestRun() {
		List<StartIdx> lstRet = new ArrayList<>();
		StartIdx a1 = new StartIdx(3, 1);
		StartIdx a2 = new StartIdx(2, 2);
		StartIdx a3 = new StartIdx(4, 3);
		StartIdx a4 = new StartIdx(1, 4);
		
		lstRet.add(a1);
		lstRet.add(a2);
		lstRet.add(a3);
		lstRet.add(a4);
		
		Collections.sort(lstRet);
		
		int n = lstRet.size();
		
		System.out.println("Start Order: ");
		for (int i=0; i<n; i++) {
			System.out.println(lstRet.get(i).start);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		final int mm;
//		mm = 99;
//
//		DD = 88;
//		
//		System.out.println(mm);
//		System.out.println(DD);
//		
//		DD= 0;
//		System.out.println(DD);
//		
		
//		int[][] aa = {{3,2},{5,8},{2,4}};
//		
//		Arrays.sort(aa, new Comparator<int[]>() {
//			public int compare(int[] a, int[] b) {
//				return b[0]-a[0];
//			}
//		});
//
//		
//		System.out.println();
		
		//System.out.println("Begin: " + System.currentTimeMillis());
		
		ArithmeticSlicesIISubsequence instance = new ArithmeticSlicesIISubsequence();
		instance.run();
		
		//TestRun();
		
		
		
		//System.out.println("End: " + System.currentTimeMillis());
		
		//dd.test11();
		
		//testkk();
		
		//float[] accMagOrientation = new float[3];
		
		//if (accMagOrientation == null) {
		//	System.out.println("adfad");
		//} else {
		//	System.out.println("3333");
		//}
		
		//int[] nums = {4, 5, 8, 10, 14};
		//int[] nums = {4, 5, 8, 10, 14, 17};
		
//		StringBuilder x = new StringBuilder("ab");
//		change(x);
//		System.out.println(x);
//		
//		
//		String y = "mm";
//		change(y);
//		System.out.println(y);
		
//		int[] nums = {4,5,6,7,8};
//		
//		System.out.println("Median = " + findMedian(nums));
//		//int target = 2;
//		
//		//int pos = findPos(nums, target);
//		
//		//System.out.println("Insert/Find Pos = " + pos);
//		
//		String ss = "mengrufeng";
//		
//		char[] carr = ss.toCharArray();
//		String sss = new String(carr, 0, 4);
//		
//		System.out.println(sss);
		
//		return;
		
		//Swaps(15, 73);
		
		//TreeDfsBfs myInstance = new TreeDfsBfs();
		//myInstance.run();
		
//		myInstance.push(512);
//		myInstance.push(-1024);
//		myInstance.push(-1024);
//		myInstance.push(512);
//		
//		myInstance.pop();
//		int a = myInstance.getMin();
//		myInstance.pop();
//		a = myInstance.getMin();
//		myInstance.pop();
//		a = myInstance.getMin();
		
		
//		StringBuilder sb = new StringBuilder();
//		
//		sb.append("abd");
//		System.out.println(sb.delete(sb.length()-1, sb.length()));
//		System.out.println(sb + sb.reverse().toString());
//		
//		System.out.println(sb.delete(sb.length()-1, sb.length()));
		
//		List<Boolean> lstVisited = new ArrayList<Boolean>();  
//		lstVisited.set(0, true);
//		lstVisited.set(1, true);
//		lstVisited.set(2, true);
//		lstVisited.set(3, true);
		
//		List<Integer> lstKK = new ArrayList<>();
//		
//		List<Integer> lstMM = new ArrayList<>();
//		lstMM.add(3); lstMM.add(4);
//		lstMM.addAll(lstKK);
//		
//		long nResult = 2147483648L;
//		int ret = (int) (0x0100000000L - nResult);
		
		//System.out.println(ret);
		
		
//		long lnTm1 = System.currentTimeMillis();

		//myInstance.run();
		
//		long lnTm2 = System.currentTimeMillis();
		
//		System.out.println("Time: " + (lnTm2 - lnTm1) + " ms");
	

		/////////////////Important//////////////////////
//		String ss = "12.3.4.5";
//		String kk[] = ss.split("\\.");
		
		
//		String mmm= "22\\5\\66";    //Actually it is "22\5\66 if printed
//	    String kkk[] = mmm.split("\\\\");
	    
	    //myString.split("\\s+");    //Split by space and tab
	    
//	    \w - Matches any word character.
//
//	    \W - Matches any nonword character.
//
//	    \s - Matches any white-space character.
//
//	    \S - Matches anything but white-space characters.
//
//	    \d - Matches any digit.
//
//	    \D - Matches anything except digits.
	    /////////////////Important/////////////////////
	
		
	    
	    
		//System.out.println(myInstance.isEqual("74", "47", "21"));
		//System.out.println(myInstance.isAdditiveNumber("7447121"));
		
		//System.out.println(myInstance.addStrings("110", "0"));
//		System.out.println("AZZ =" + myInstance.getIntAirport("AZZ"));
//		System.out.println("BAA =" + myInstance.getIntAirport("BAA"));
		
		
		//String s = "3*(6/3 - 1 + 2) - 1 + (4/2*(3+5))";
		//int i = 2;
		//String s = "3/(2*6/3-*6";
		//String[] saa = s.   split("[\\(,\\), \\*,/,\\-,+]");
		//String[] saa = s.split("[0-9]*");
		
		
//		HashMap<Integer, List<Integer>> hm = new HashMap<Integer, List<Integer>>();
//		List<Integer> lstTmp = new ArrayList<Integer>();
//		lstTmp.add(10);
//		lstTmp.add(11);
//		hm.put(1, lstTmp);
//		lstTmp = new ArrayList<Integer>();
//		lstTmp.add(20);
//		lstTmp.add(21);
//		hm.put(2, lstTmp);
//		
//		HashMap<Integer,  List<Integer>> hmm  = new HashMap<Integer,  List<Integer>>(hm);

//		List<Integer> lstKK = new ArrayList<Integer>();
//		lstKK = hmm.get(2);
//		lstKK.add(22);
//		hmm.replace(2,  lstKK);
		
//		List<Integer> lstKK = new ArrayList<Integer>(hmm.get(2));
//		lstKK.add(22);
//		hmm.replace(2,  lstKK);
		
		//i++;
		
		//System.out.println("==" + saa.length);
		
		//int[] aa = {3,2,1};
		//Arrays.sort(aa, 1,3);
		
		
		
//		String s1 = "abc";
		//String s2 = "acb";
		
		//System.out.println(s1.compareTo(s2));
		
		//System.out.println(Math.sqrt(-0.01));
		//System.out.println(Math.pow(0, -3));
		//System.out.println("----==" + 1/0.0);  
		
		// 1/0 ==> run time error
		// 1/0.0,   1.0/0, 1.0/0.0 ==> all are OK, generate Infinity
		
		System.out.println("--------Done--------");
		return;
	
	}

}
