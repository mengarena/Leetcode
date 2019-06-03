/*

970. Powerful Integers

Given two positive integers x and y, an integer is powerful if it is equal to x^i + y^j for some integers i >= 0 and j >= 0.

Return a list of all powerful integers that have value less than or equal to bound.

You may return the answer in any order.  In your answer, each value should occur at most once.

Example 1:
Input: x = 2, y = 3, bound = 10
Output: [2,3,4,5,7,9,10]
Explanation: 
2 = 2^0 + 3^0
3 = 2^1 + 3^0
4 = 2^0 + 3^1
5 = 2^1 + 3^1
7 = 2^2 + 3^1
9 = 2^3 + 3^0
10 = 2^0 + 3^2


Example 2:
Input: x = 3, y = 5, bound = 15
Output: [2,4,6,8,10,14]
 
Note:
1 <= x <= 100
1 <= y <= 100
0 <= bound <= 10^6

Easy
*/

class Solution {
public:

    // 100%
    vector<int> powerfulIntegers(int x, int y, int bound) {
        if (bound <= 1) return vector<int>();
        if (x == 1 && y == 1) return vector<int>(1, 2);
        
        unordered_set<int> ans;
        
        if (x == 1 || y == 1) {
            int z = x == 1? y:x;
            
            for (int zz = 1; zz + 1 <= bound; zz *= z) {
                ans.insert(zz+1);
            }
            
            return vector<int>(begin(ans), end(ans));
        }
        
        for (int xx=1; xx+1 <= bound; xx *= x) {
            for (int yy=1; xx+yy <= bound; yy *= y) {
                ans.insert(xx+yy);
            }
        }
        
        return vector<int>(begin(ans), end(ans));
    }
};
