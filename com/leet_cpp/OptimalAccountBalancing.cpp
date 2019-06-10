/*

465. Optimal Account Balancing

A group of friends went on holiday and sometimes lent each other money. 
For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. 
We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. 
Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), 
the transactions can be represented as [[0, 1, 10], [2, 0, 5]].

Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.

Note:

A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.

Example 1:
Input:
[[0,1,10], [2,0,5]]

Output:
2

Explanation:
Person #0 gave person #1 $10.
Person #2 gave person #0 $5.

Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.

Example 2:
Input:
[[0,1,10], [1,0,1], [1,2,5], [2,0,5]]

Output:
1

Explanation:
Person #0 gave person #1 $10.
Person #1 gave person #0 $1.
Person #1 gave person #2 $5.
Person #2 gave person #0 $5.

Therefore, person #1 only need to give person #0 $4, and all debt is settled.

Hard

Google, Uber
*/


class Solution {
public:

    // 66%
    // if debts[i] > 0, this person needs to give money to others
    // if debts[i] < 0, this person needs to collect money from others
    int minTransfers(vector<vector<int>>& transactions) {
        unordered_map<int, long> balances;
        for (auto& t:transactions) {
            balances[t[0]] -= t[2];
            balances[t[1]] += t[2];
        }
        
        vector<int> debts;
        for (auto& m:balances) {
            if (m.second) debts.push_back(m.second);
        }
        
        return dfs(debts, 0);
    }
    
    int dfs(vector<int>& debts, int s) {
        // The person has debt = 0, don't need to process
        while (s < debts.size() && debts[s] == 0) s++;
        
        int ret = INT_MAX;
        
        // check every other to see whether can clear the debt for person s
        // And try all these "every other" to get the minimum number of transactions
        int prev = 0;
        for (long i=s+1; i<debts.size(); ++i) {
            if (debts[i] == prev || debts[i]*debts[s] > 0) continue;  // If this person has same debt as the one previously tried, don't need to try again (because the minimum number of transactions will be same). OR if this person has same kind of debt (both should give or should collect, then this person can not clear for person s)
            debts[i] += debts[s];  // Clear the debt for s
            ret = min(ret, 1 + dfs(debts, s+1));
            debts[i] -= debts[s];  // Reset for trying next
            prev = debts[i];
        }
        
        return ret < INT_MAX ? ret:0;
    }
};
