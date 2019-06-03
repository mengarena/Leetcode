/*

754. Reach a Number

You are standing at position 0 on an infinite number line. There is a goal at position target.

On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.

Return the minimum number of steps required to reach the destination.

Example 1:
Input: target = 3
Output: 2
Explanation:
On the first move we step from 0 to 1.
On the second step we step from 1 to 3.

Example 2:
Input: target = 2
Output: 3
Explanation:
On the first move we step from 0 to 1.
On the second move we step  from 1 to -1.
On the third move we step from -1 to 2.

Note:
target will be a non-zero integer in the range [-10^9, 10^9].

Easy
*/

class Solution {
public:
    
    // 88%
    // Strategy: Accumulate from 1 to x (i.e. sum up, all positives) for sum > target
    // Check the difference between sum and target
    // if odd, keep adding;
    // if even, we can always flip one of the steps. say the sum - target = 2k, 
    // we can flip step k to remove 2k from sum to reach target 
    int reachNumber(int target) {
        target = abs(target);
        int sum = 0;
        int n = 1;
        
        while (sum < target) {
            sum += n;
            n++;
        }
        
        if (sum == target) return n-1;
        
        while ((sum - target) % 2 == 1) {
            sum += n;
            n++;
        }
        
        return n-1;
    }
};
