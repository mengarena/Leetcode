/*

780. Reaching Points

A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).

Given a starting point (sx, sy) and a target point (tx, ty), 
return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty). 
Otherwise, return False.

Examples:
Input: sx = 1, sy = 1, tx = 3, ty = 5
Output: True
Explanation:
One series of moves that transforms the starting point to the target is:
(1, 1) -> (1, 2)
(1, 2) -> (3, 2)
(3, 2) -> (3, 5)

Input: sx = 1, sy = 1, tx = 2, ty = 2
Output: False

Input: sx = 1, sy = 1, tx = 1, ty = 1
Output: True

Note:
sx, sy, tx, ty will all be integers in the range [1, 10^9].

Hard

Uber, Saleforce
*/


class Solution {
public:

    // 89%
    // Strategy:  Do backwards, start from <tx, ty> towards <sx, sy>
    // Normally (if forwards), there is a step <a, b> --> <tx, ty>, to from <tx, ty> to get <a, b>,
    // i.e. do <tx-ty, ty> or <tx, ty-tx>
    // Only the larger could substract the smaller
    // Do try these steps to see whether can reach <sx, sy>
    bool reachingPoints(int sx, int sy, int tx, int ty) {
        if (tx == ty) return sx == sy && sx == tx;
        
        while (tx != sx || ty != sy) {
            if (tx < sx || ty < sy) return false;
            
            if (tx > ty) {
                int possibleCnt = max(1, (tx-sx)/ty);  // Optimization, without this step i.e. only do (tx -= ty), TLE
                tx -= ty*possibleCnt;
            } else {
                int possibleCnt = max(1, (ty-sy)/tx);
                ty -= tx*possibleCnt;
            }
        }
        
        return true;
    }
};
