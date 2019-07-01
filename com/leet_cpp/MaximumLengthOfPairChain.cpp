/*

646. Maximum Length of Pair Chain

You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. 
Chain of pairs can be formed in this fashion.

Given a set of pairs, find the length longest chain which can be formed. 
You needn't use up all the given pairs. You can select pairs in any order.

Example 1:
Input: [[1,2], [2,3], [3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4]
Note:
The number of given pairs will be in the range [1, 1000].

Medium:
Uber
*/


class Solution {
public:
    
    // Sort:  first in ascending order by end; if end is same, sort in descending order for start
    // The idea is similar to the Russia Doll problem
    //
    // Attention: This compare function must be "static"
    /*
       Think of how you call compareFunc outside of the class. You always would have something like
       a.compareFunc(b, c)
       ^             ^  ^
       which is 3 parameters, not 2.
       sort's code is outside your class and would have to use the syntax above.
       Making the member static allows this call:
       Solution::compareFunc(a, b)
    */
    static bool fncomp(const vector<int>& lhs, const vector<int>& rhs) {
        if (lhs[1] != rhs[1]) {
            return lhs[1] < rhs[1];
        } else {
            return lhs[0] > rhs[0];
        }
    }
    
    // 90%
    int findLongestChain(vector<vector<int>>& pairs) {
        sort(pairs.begin(), pairs.end(), fncomp);
        
        int len = 1;
        int curend = pairs[0][1];
        for (int i=1; i<pairs.size(); i++) {
            if (curend < pairs[i][0]) {
                len++;
                curend = pairs[i][1];
            }
        }
        
        return len;
    }
};
