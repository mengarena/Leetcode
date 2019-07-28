/*

819. Most Common Word

Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.
It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.
Words in the paragraph are not case sensitive.  The answer is in lowercase.

Example:

Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.
 
Note:

1 <= paragraph.length <= 1000.
0 <= banned.length <= 100.
1 <= banned[i].length <= 10.
The answer is unique, and written in lowercase 
(even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
There are no hyphens or hyphenated words.
Words only consist of letters, never apostrophes or other punctuation symbols.


Easy
*/

class Solution {
public:
    // 96%
    string mostCommonWord(string paragraph, vector<string>& banned) {
        unordered_map<string, int> wordFreq;
        string word;
        string res;
        int maxFreq = 0;
        int n = paragraph.length();
        
        int i = 0;
        while (i < n && !isalpha(paragraph[i])) i++;
        int j = i+1;
        
        while (j < n) {
            if (isalpha(paragraph[j])) {
                j++;
                continue;
            }
            
            word = paragraph.substr(i, j-i);
            transform(word.begin(), word.end(), word.begin(), ::tolower);   // ***
            
            if (find(banned.begin(), banned.end(), word) == banned.end()) {
                if (wordFreq.count(word)) {
                    wordFreq[word]++;
                } else {
                    wordFreq[word] = 1;
                }
                
                if (maxFreq < wordFreq[word]) {
                    maxFreq = wordFreq[word];
                    res = word;
                }
            }

            i = j;
            while (i < n && !isalpha(paragraph[i])) i++;
            j = i+1;
            
        }

        if (i == n) return res;

        word = paragraph.substr(i, j-i);
        transform(word.begin(), word.end(), word.begin(), ::tolower);
            
        if (find(banned.begin(), banned.end(), word) == banned.end()) {
            wordFreq[word]++;
                
            if (maxFreq < wordFreq[word]) {
                maxFreq = wordFreq[word];
                res = word;
            }
        }

        return res;
    }
};
