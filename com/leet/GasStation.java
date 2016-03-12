package com.leet;

//There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
//
//You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
//You begin the journey with an empty tank at one of the gas stations.
//
//Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
//
//Note:
//The solution is guaranteed to be unique.

public class GasStation {

	public GasStation() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] gas = {};
		int[] cost = {};
		
		int nStartingIdx = canCompleteCircuit(gas, cost);
		
		System.out.println("The starting index is: " + nStartingIdx);
	}
	
	
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || cost.length == 0 || gas.length != cost.length) return -1;
        int n = gas.length;
        int i;
        int nRemained = 0;
        int nStartingIdx = 0;
        int nTotal = 0;
        int nDiff;
        
        for (i=0; i<=n-1; i++) {
        	nDiff = gas[i]-cost[i];
        	nTotal = nTotal + nDiff;
        	
        	nRemained = nRemained + nDiff;
        	// Starting point must after i.  
        	//If at i, nRemained < 0; say this session, 
        	//the starting station is x, it means the total travel costs between x and i is more than gas could be fueled. 
        	//Say a random station y between x and i, then the total gas[x~y] >= cost[x~y], then the gas[y~i] < cost[y~i], therefore, y could not be the valid starting station;
        	//So any station [x~i] could not be valid starting station
        	if (nRemained < 0) {  
        		nStartingIdx = i+1; 
        		nRemained = 0;
        	}
        }
        
        if (nTotal < 0) return -1;
        
        return nStartingIdx;
    }
	
}
