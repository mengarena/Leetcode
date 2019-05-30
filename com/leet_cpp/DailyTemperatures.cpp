/*

739. Daily Temperatures

Given a list of daily temperatures T, return a list such that, 
for each day in the input, tells you how many days you would have to wait until a warmer temperature. 
If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], 
your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. 
Each temperature will be an integer in the range [30, 100].

Medium
*/

class Solution {
public:

    // 86%
    vector<int> dailyTemperatures(vector<int>& T) {
        int n = T.size();
        vector<int> ans(n, 0);
        if (n == 0) return ans;
        
        stack<pair<int, int>> stk;  // stack of <value, index>
       
        stk.push(make_pair(T[n-1], n-1));
        
        for (int i=n-2; i>=0; --i) {
            while (!stk.empty() && T[i] >= stk.top().first) {
                stk.pop();
            }
            
            if (!stk.empty()) {
                ans[i] = stk.top().second - i;
            }
            
            stk.push(make_pair(T[i], i));
        }
        
        return ans;
    }
};
