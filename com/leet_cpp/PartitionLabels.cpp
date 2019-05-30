/*

763. Partition Labels

A string S of lowercase letters is given. 
We want to partition this string into as many parts as possible so that each letter appears in at most one part, 
and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.

Note:
S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.

Medium
*/

class Solution {
public:

    // 34%
    vector<int> partitionLabels(string S) {
        vector<int> ans;
        if (S.empty()) return ans;
        int start = 0;
        
        while (start < S.length()) {
            int end = S.find_last_of(S[start]);

            int i = start;
            while (i != end) {
                i++;
                int tmp = S.find_last_of(S[i]);
                if (tmp > end) end = tmp;
            }
            
            ans.push_back(end-start+1);
            start = end + 1;
        }
        
        return ans;
        
    }
};
