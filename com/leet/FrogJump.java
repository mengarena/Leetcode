package com.leet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. 
//The frog can jump on a stone, but it must not jump into the water.
//
//Given a list of stones' positions (in units) in sorted ascending order, 
//determine if the frog is able to cross the river by landing on the last stone. 
//Initially, the frog is on the first stone and assume the first jump must be 1 unit.
//
//If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. 
//Note that the frog can only jump in the forward direction.
//
//Note:
//
//The number of stones is ¡Ý 2 and is < 1,100.
//Each stone's position will be a non-negative integer < 231.
//The first stone's position is always 0.
//
//Example 1:
//
//[0,1,3,5,6,8,12,17]
//
//There are a total of 8 stones.
//The first stone at the 0th unit, second stone at the 1st unit,
//third stone at the 3rd unit, and so on...
//The last stone at the 17th unit.
//
//Return true. The frog can jump to the last stone by jumping 
//1 unit to the 2nd stone, then 2 units to the 3rd stone, then 
//2 units to the 4th stone, then 3 units to the 6th stone, 
//4 units to the 7th stone, and 5 units to the 8th stone.
//
//Example 2:
//
//[0,1,2,3,4,8,9,11]
//
//Return false. There is no way to jump to the last stone as 
//the gap between the 5th and 6th stone is too large.

//Snapchat
//Hard
public class FrogJump {

	public FrogJump() {
		// TODO Auto-generated constructor stub
	}
	

	public void run() {
	    //int[] stones = {0,1,2,3,4,8,9,11};
	    int[] stones = {0,1,3,6,10,15,19,21,24,26,30,33};
	    
	    boolean bRet = canCross(stones);
	    
	    System.out.println(bRet);
	}
	
	
	//ACC:  50%
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length <= 1) return true;
        int n = stones.length;
        if (stones[0] != 0 || stones[1] != 1) return false;
        
        if (n <= 2) return true;
        int last = stones[n-1];
        int i;
        int stone;
        int nextStone;
        
        Map<Integer, Set<Integer>> hm = new HashMap<Integer, Set<Integer>>();  //Stone position (not index), 
	                                                                       //possible steps from this stone
        
        for (i=0; i<n; i++) {
            hm.put(stones[i], new HashSet<Integer>());
        }

        hm.get(0).add(1);
        
        for (i=0; i<n; i++) {
            stone = stones[i];
            
            for (int step:hm.get(stone)) {
                nextStone = stone + step;
                if (nextStone == last) return true;
                
                Set<Integer> stepSet = hm.get(nextStone);
                if (stepSet != null) {
                    if (step > 1) {
                        stepSet.add(step-1);
                    }
                    
                    stepSet.add(step);
                    stepSet.add(step+1);
                }
            }
        }
        
        return false;
    }
	
	
	
    
    
    
	//Function correct. Exceed Time Limit
    public boolean canCrossA(int[] stones) {
        if (stones == null || stones.length <= 1) return true;
        int n = stones.length;
        if (stones[0] != 0 || stones[1] != 1) return false;
        
        if (n <= 2) return true;
        
        Queue<Integer> quStep = new LinkedList<Integer>();
        Queue<Integer> quPos = new LinkedList<Integer>();
        Set<Integer> setPos = new HashSet<Integer>();
        int size = 0;
        int i;
        int pos, step;
        int startIdx = 2;
        
        quPos.offer(1);
        quStep.offer(1);
        
        while (!quPos.isEmpty()) {
            size = quPos.size();
            setPos.clear();
            
            for (i=0; i<size; i++) {
                pos = quPos.poll();
                step = quStep.poll();
                
                if (step > 1) {
	                if (pos+step-1 == stones[n-1]) {
	                    return true;  
	                } else if (pos+step-1 > stones[n-1]) {
	                    continue;
	                //} else if (!setPos.contains(pos+step-1) && isStoneExist(stones, n, startIdx, pos+step-1)) {
	                } else if (isStoneExist(stones, n, startIdx, pos+step-1)) {
	                    quPos.offer(pos+step-1);
	                    quStep.offer(step-1);
	                    setPos.add(pos+step-1);
	                }
                }
                
                if (pos+step == stones[n-1]) {
                    return true;
                } else if (pos+step > stones[n-1]) {
                    continue;
                //} else if (!setPos.contains(pos+step) && isStoneExist(stones, n, startIdx, pos+step)) {
                } else if (isStoneExist(stones, n, startIdx, pos+step)) {
                    quPos.offer(pos+step);
                    quStep.offer(step);
                    setPos.add(pos+step);
                }
                
                if (pos+step+1 == stones[n-1]) {
                    return true;
                } else if (pos+step+1 > stones[n-1]) {
                    continue;
                //} else if (!setPos.contains(pos+step+1) && isStoneExist(stones, n, startIdx, pos+step+1)) {
                } else if (isStoneExist(stones, n, startIdx, pos+step+1)) {	
                    quPos.offer(pos+step+1);
                    quStep.offer(step+1);
                    setPos.add(pos+step+1);
                }
            }
            
        }
        
        return false;
    }
    
    private boolean isStoneExist(int[] stones, int n, int startIdx, int pos) {
        int i = startIdx;
        int j = n-1;
        int mid;
        
        if (i > j) return false;
        
        while (i <= j) {
            mid = i + (j-i)/2;
            
            if (stones[mid] == pos) return true;
            
            if (stones[mid] > pos) {
                j = mid-1;
            } else {
                i = mid+1;
            }
        }
        
        return false;
    }

}
