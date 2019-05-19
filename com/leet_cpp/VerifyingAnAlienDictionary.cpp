/*

953. Verifying an Alien Dictionary

In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. 
The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, 
return true if and only if the given words are sorted lexicographicaly in this alien language.


Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) 
According to lexicographical rules "apple" > "app", 
because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 

Note:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are english lowercase letters.

Easy
*/

class Solution {
public:
    // 94%
    bool isAlienSorted(vector<string>& words, string order) {
        int n = words.size();
        if (n <= 1) return true;
        for (int i=1; i<n; ++i) {
            if (!isInorder(words[i-1], words[i], order)) return false;
        }
        return true;
    }
    
    bool isInorder(const string& A, const string& B, const string& order) {
        int nLen = min(A.length(), B.length());
        for (int i=0; i<nLen; ++i) {
            if (order.find(A[i]) < order.find(B[i])) return true;
            if (order.find(A[i]) > order.find(B[i])) return false;
        }
        if (A.length() > B.length()) return false;
        return true;
    }
};
