/*

914. X of a Kind in a Deck of Cards

In a deck of cards, each card has an integer written on it.

Return true if and only if you can choose X >= 2 such that 
it is possible to split the entire deck into 1 or more groups of cards, where:

Each group has exactly X cards.
All the cards in each group have the same integer.
 
Example 1:
Input: [1,2,3,4,4,3,2,1]
Output: true
Explanation: Possible partition [1,1],[2,2],[3,3],[4,4]

Example 2:
Input: [1,1,1,2,2,2,3,3]
Output: false
Explanation: No possible partition.

Example 3:
Input: [1]
Output: false
Explanation: No possible partition.

Example 4:
Input: [1,1]
Output: true
Explanation: Possible partition [1,1]

Example 5:
Input: [1,1,2,2,2,2]
Output: true
Explanation: Possible partition [1,1],[2,2],[2,2]

Note:

1 <= deck.length <= 10000
0 <= deck[i] < 10000

Easy
*/


class Solution {
public:

    // 91%
    // Strategy:  Find the min count of the same numbers
    // say the min count is 6, then check whether we can divide the number into
    // group sizes of 2, 3 or 6
    bool hasGroupsSizeX(vector<int>& deck) {
        if (deck.size() < 2) return false;
        unordered_map<int, int> m;
        
        for (auto num:deck) {
            m[num]++;
        }
        
        int minSize = INT_MAX;
        for (auto it=m.begin(); it != m.end(); ++it) {
            minSize = min(minSize, it->second);
        }
        
        if (minSize < 2) return false;
        
        vector<int> gcds;
        for (int i=2; i<=minSize; ++i) {
            if (minSize % i == 0) gcds.push_back(i);
        }
        
        int totalCnt = m.size();
        
        for (auto gcd:gcds) {
            int tmpTotalCnt = 0;
            
            for (auto it=m.begin(); it != m.end(); ++it) {
                if (it->second % gcd == 0) tmpTotalCnt++;
            }
            
            if (tmpTotalCnt == totalCnt) return true;
        }
        
        return false;
    }
};
