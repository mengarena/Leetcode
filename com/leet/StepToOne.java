package com.leet;

import java.util.HashMap;
import java.util.Map;


//If n is even, get n/2;  if n is odd, get 3*n+1
//For a n, how many steps will it get to 1
//For example, n = 3, ==> 10, 5, 16, 8, 4, 2, 1,   so 7 steps

public class StepToOne {

	public StepToOne() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int n = 3;
		
		System.out.println(getStep(n));
	}

	
	//Simple
	public int getStepA(int n) {
		if (n <= 1) return 0;
		
		if (n % 2 == 0) {
			return 1 + getStep(n/2);
		} else {
			return 1 + getStep(3*n+1);
		}
	}
	
	
	
	//Follow-up, optimize
	Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
	
	public int getStep(int n) {
		if (hm.containsKey(n)) return hm.get(n);
		
		int nStepCnt = 0;
		if (n > 1) {		
			if (n % 2 == 0) {
				nStepCnt = 1 + getStep(n/2);
			} else {
				nStepCnt = 1 + getStep(3*n+1);
			}
		}
		
		hm.put(n, nStepCnt);
		
		return nStepCnt;
	}

}
