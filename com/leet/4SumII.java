/*
454. 4Sum II 

Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. 
All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
*/

//Medium

public class Solution {

    //ACC:  O(n^2)
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A == null || A.length == 0) return 0;
        int count = 0;
        
        Map<Integer, Integer> hmAB = new HashMap<>();
        
        for (int numA:A) {
            for (int numB:B) {
                hmAB.put(numA+numB, hmAB.getOrDefault(numA+numB, 0) + 1);
            }
        }
        
        for (int numC:C) {
            for (int numD:D) {
                count += hmAB.getOrDefault(-numC-numD, 0);
            }
        }
        
        return count;
    }	
	
	
/* Exceed Time Limit	
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A == null || A.length == 0) return 0;
        int n = A.length;
        int i = 0;
        int count = 0;
        
        Map<Integer, Integer> hmA = new HashMap<>();
         for (int num:A) {
            hmA.put(num, hmA.getOrDefault(num, 0) + 1);
        }       
        
        Map<Integer, Integer> hmB = new HashMap<>();
        for (int num:B) {
            hmB.put(num, hmB.getOrDefault(num, 0) + 1);
        }
        
        Map<Integer, Integer> hmC = new HashMap<>();
         for (int num:C) {
            hmC.put(num, hmC.getOrDefault(num, 0) + 1);
        }       
        
        Map<Integer, Integer> hmD = new HashMap<>();
        for (int num:D) {
            hmD.put(num, hmD.getOrDefault(num, 0) + 1);
        }
        
        Set<Integer> keysA = hmA.keySet();
        
        for (int num:keysA) {
            int countA = hmA.get(num);
            
            int tmpCount = getThreeSumCount(hmB, hmC, hmD, -num);
            
            count += countA*tmpCount;
        }
        
        return count;
    }
    
    public int getThreeSumCount(Map<Integer, Integer> hmB, Map<Integer, Integer> hmC, Map<Integer, Integer> hmD, int target) {
        int count = 0;
        
        Set<Integer> keysB = hmB.keySet();
        
        for (int num:keysB) {
            int countB = hmB.get(num);
            int tmpCount = getTwoSumCount(hmC, hmD, target - num);
            
            count += countB*tmpCount;
        }
        
        return count;
    }
    
    public int getTwoSumCount(Map<Integer, Integer> hmC, Map<Integer, Integer> hmD, int target) {
        int i = 0;
        int count = 0;
        
        Set<Integer> keysC = hmC.keySet();
        
        for (int num:keysC) {
            
            if (hmD.containsKey(target - num)) {
                count += hmC.get(num) * hmD.get(target - num);
            }
        }
        
        return count;
    }
 */   
    
    
 /*  Exceed Time Limit
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A == null || A.length == 0) return 0;
        int n = A.length;
        int i = 0;
        int count = 0;
        
        Map<Integer, Integer> hmA = new HashMap<>();
         for (int num:A) {
            hmA.put(num, hmA.getOrDefault(num, 0) + 1);
        }       
        
        Map<Integer, Integer> hmB = new HashMap<>();
        for (int num:B) {
            hmB.put(num, hmB.getOrDefault(num, 0) + 1);
        }
        
        Map<Integer, Integer> hmC = new HashMap<>();
         for (int num:C) {
            hmC.put(num, hmC.getOrDefault(num, 0) + 1);
        }       
        
        Map<Integer, Integer> hmD = new HashMap<>();
        for (int num:D) {
            hmD.put(num, hmD.getOrDefault(num, 0) + 1);
        }
        
        Set<Integer> keysA = hmA.keySet();
        Set<Integer> keysB = hmB.keySet();
        
        for (int numA:keysA) {
            int countA = hmA.get(numA);
            
            for (int numB:keysB) {
                int countB = hmB.get(numB);
                
                count += countA*countB*getTwoSumCount(hmC, hmD, -numA-numB);
            }
        }
        
        return count;
    }
    
    public int getTwoSumCount(Map<Integer, Integer> hmC, Map<Integer, Integer> hmD, int target) {
        int i = 0;
        int count = 0;
        
        Set<Integer> keysC = hmC.keySet();
        
        for (int num:keysC) {
            
            if (hmD.containsKey(target - num)) {
                count += hmC.get(num) * hmD.get(target - num);
            }
        }
        
        return count;
    }  
  
  */   
}
