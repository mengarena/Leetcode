package com.leet;

//There are n bulbs that are initially off. 
//You first turn on all the bulbs. 
//Then, you turn off every second bulb. 
//On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).
//For the ith round, you toggle every i bulb. 
//For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds.
//
//Example:
//
//Given n = 3. 
//
//At first, the three bulbs are [off, off, off].
//After first round, the three bulbs are [on, on, on].
//After second round, the three bulbs are [on, off, on].
//After third round, the three bulbs are [on, off, off]. 
//
//So you should return 1, because there is only one bulb is on.

public class BulbSwitcher {

	public BulbSwitcher() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int n = 99999;
		System.out.println("On #Bulb = " + bulbSwitch(n));
	}

	
    //Strategy:  for any bulb position,
    //If we get its factor, like x = a*b, including x = x*1
    //So if a*b, a != b, the bulb is toggled twice and the effect is cancelled
    //so the bulb whose positions does not have x = a*a, it always have two-factor pairs, 
    //which will cancel the effect of toggling and the bulb will be in initial status (i.e. OFF)
    //
    //Unless, there is x = a*b,  a==b, then the total effect will equal to one toggle from initial, which results in "ON"
    //So only the x could be x = a*a, it will be ON at the end
    public int bulbSwitch(int n) {
        int i=1;
        int nCount = 0;
        
        while (i*i <= n) {
            i++;
            nCount = nCount + 1;
        }
        
        return nCount;
    }
	
}
