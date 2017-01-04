/*
456. 132 Pattern   

Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. 
Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.

Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].

Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
*/

//Medium

public class Solution {
	//ACC
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        Stack<Integer> stk = new Stack<>();
        int min = Integer.MAX_VALUE;
        
        for (int num:nums) {
            if (num <= min) {
                min = num;
            } else {
				//Everytime, if pop, pop() two; everytime, push two
				//So in the stack, actually there are 2-element pair (which are as ai, ak)
                while (!stk.isEmpty()) {
                    if (stk.peek() >= num) break;
                    stk.pop();  //at this step, means num > min (ai); here pop the min
                    if (stk.pop() > num) return true;  //num > ak   
                }
                
                //Due to the "if" condition, the index of num is always larger than the index of min
                stk.push(num);
                stk.push(min);
            }
        }
        
        return false;
    }

}

