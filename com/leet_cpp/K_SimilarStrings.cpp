/*

854. K-Similar Strings

Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters 
in A exactly K times so that the resulting string equals B.

Given two anagrams A and B, return the smallest K for which A and B are K-similar.

Example 1:
Input: A = "ab", B = "ba"
Output: 1

Example 2:
Input: A = "abc", B = "bca"
Output: 2

Example 3:
Input: A = "abac", B = "baca"
Output: 2

Example 4:
Input: A = "aabc", B = "abca"
Output: 2

Note:
1 <= A.length == B.length <= 20
A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}

Hard
*/

class Solution {
public:

    // 94%
    // Basically, it is BFS
    int kSimilarity(string A, string B) {
        if (A == B) return 0;
        if (A.length() != B.length()) return -1;
        
        int cc[26] = {0};
        for (char c:A) cc[c-'a']++;
        for (char c:B) {
            if (cc[c-'a'] == 0) return -1;
            cc[c-'a']--;
        }
        
        int n = A.length();
        
        for (int i = 0; i< n-1; ++i) {
            if (A[i] == B[i]) continue;
            
            vector<int> candiPos;
            
            // When A[i] != B[i],
            // Need to find a j, where A[j] = B[i], then after swap A[i] and A[j], reduce one mismatch (i.e. make progress),
            // But be noted, at position j, we need to make sure A[j] != B[j], otherwise, if we swap A[i] and A[j], 
            // We reduce one mismatch, but also introduce one mismatch
            for (int j = i+1; j<n; ++j) {
                if (A[j] == B[i] && A[j] != B[j]) {
                    if (A[i] == B[j]) {   // In this case, one swap could reduce two mismatch, just do it to make progress
                        swap(A[i], A[j]);
                        return 1 + kSimilarity(A.substr(i+1), B.substr(i+1));
                    }
                    
                    candiPos.push_back(j);  // We need to check these j position to see which one makes best result
                                            // because each possible swap between i and j will affect future swaps
                }
            }
            
            int minSwaps = n-1;  // At most swap n-1 times
            
            // From all possible j positions, find the one which produce the least swaps
            for (auto j:candiPos) {
                swap(A[i], A[j]);
                minSwaps = min(minSwaps, 1 + kSimilarity(A.substr(i+1), B.substr(i+1)));
                if (minSwaps == 1) break;
                swap(A[i], A[j]);
            }
                               
            return minSwaps;
        }
        
        return -1;
    }
    
};
