/*

829. Consecutive Numbers Sum

Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?

Example 1:
Input: 5
Output: 2
Explanation: 5 = 5 = 2 + 3

Example 2:
Input: 9
Output: 3
Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4

Example 3:
Input: 15
Output: 4
Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5
Note: 1 <= N <= 10 ^ 9.

Hard
*/


class Solution {
public:

    // 58%
    int consecutiveNumbersSum(int N) {
        int cnt = 0;
        
        // max length of consecutive intergers:  1, 2....y = (1+y)*y/2 = N
        for (int y = 1; (1+y)*y <= 2*N; ++y) {  // Number of consecutive integers
            int medium = N/y;
            // Base of the consecutive integers
            int startNum = medium - y/2 + (y % 2 == 1? 0:1);
            // To sum these consecutive integers:
            // startNum, startNum+1, .... startNum+y-1
            // startNum*y + (y-1)*y/2 = N  ==>
            if (2*startNum*y + y*y - y == 2*N) cnt++;
        }
        
        return cnt;
    }
};
