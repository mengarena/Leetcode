package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MainProgram {

	public MainProgram() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SparseMatrixMultiplication myInstance = new SparseMatrixMultiplication();

		long lnTm1 = System.currentTimeMillis();

		myInstance.run();

		long lnTm2 = System.currentTimeMillis();
		
		System.out.println("Time: " + (lnTm2 - lnTm1) + " ms");
	
		
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
		
		return;
	
	}

}
