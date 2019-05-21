/*

692. Top K Frequent Words

Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. 
If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.

Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.

Follow up:
Try to solve it in O(n log k) time and O(n) extra space.

Medium
*/


class Solution {
public:
    
    static bool fncomp(const pair<string, int>& A, const pair<string,int>& B) {
        if (A.second != B.second) {
            return A.second > B.second;
        } else {
            return A.first.compare(B.first) <= 0;
        }
    }
    
    // 99%
    // Complexity: O(nlogn)
    vector<string> topKFrequent(vector<string>& words, int k) {
        unordered_map<string, int> m;
        vector<pair<string, int>> sorted;
        vector<string> ans;
        
        for (auto w:words) {
            if (m.count(w)) {
                m[w]++;
            } else {
                m[w] = 1;
            }
        }
        
        for (auto it=m.begin(); it!=m.end(); ++it) {
            sorted.push_back(make_pair(it->first, it->second));
        }
        
        sort(sorted.begin(), sorted.end(), fncomp);
        int i=0;
        
        while (ans.size() < k && i<sorted.size()) {
            pair<string, int>  p = sorted[i++];
            ans.push_back(p.first);
        }
        return ans;
    }
};
