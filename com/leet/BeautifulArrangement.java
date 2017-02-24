/*

Suppose you have N integers from 1 to N. 
We define a beautiful arrangement as an array that is constructed by these N numbers successfully 
if one of the following is true for the ith position (1 ≤ i ≤ N) in this array:

The number at the ith position is divisible by i.
i is divisible by the number at the ith position.
Now given N, how many beautiful arrangements can you construct?

Example 1:
Input: 2
Output: 2

Explanation: 

The first beautiful arrangement is [1, 2]:

Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

The second beautiful arrangement is [2, 1]:

Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.

Note:
N is a positive integer and will not exceed 15.

*/

//Medium
//Google

public class Solution {
	
	
	//ACC:  81%
	//Use hashmap to remember the processed situation
	//One limitation of this method:  Strictly following the condition:  N <= 15 as stated in the question
	
	
    Map<Integer, Integer> hm = new HashMap<>();   //Used situation, count
    
    public int countArrangement(int N) {
        if (N == 0) return 0;
        List<Integer>[] valuesAtPos = new List[N+1];
        
        //Find out, at each position, what numbers could be put
        for (int i=1; i<=N; i++) {
            valuesAtPos[i] = new ArrayList<>();
            
            for (int j=1; j<=N; j++) {
                if (j % i == 0 || i % j == 0) valuesAtPos[i].add(j);
            }
        }
        
        return helper(valuesAtPos, 1, 0);
    }
    
    
    //Use bit in "used" to mark whether a number in valuesAtPos[pos] has been used
    private int helper(List<Integer>[] valuesAtPos, int pos, int used) {
        if (hm.containsKey(used)) return hm.get(used);
        
        if (pos == valuesAtPos.length) return 1;
        
        int count = 0;
        
        for (int num:valuesAtPos[pos]) {
            if (((used >> num) & 1) == 1) continue;  //This num is used
            count += helper(valuesAtPos, pos+1, used | (1 << num));   //Set it to used for next processing
        }
        
        hm.put(used, count);
        
        return count;
    }	
	
	
	
	//ACC: 69%
	//At every position, just try every possible number
	
    int count = 0;
    
    public int countArrangementA(int N) {
        if (N == 0) return 0;
        
        helper(N, 1, new boolean[N+1]);
        
        return count;
    }
    
    private void helper(int N, int pos, boolean[] used) {
        if (pos > N) {
            count++;
            return;
        }
        
        for (int i=1; i<=N; i++) {
            if (!used[i] && ((pos % i == 0) || (i % pos == 0)) ) {
                used[i] = true;
                helper(N, pos+1, used);
                used[i] = false;
            }
        }
    }
}
