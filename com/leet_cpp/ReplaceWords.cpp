/*

648. Replace Words

In English, we have a concept called root, which can be followed by some other words to form another longer word - 
let's call this word successor. For example, the root an, followed by other, which can form another word another.

Now, given a dictionary consisting of many roots and a sentence. 
You need to replace all the successor in the sentence with the root forming it. 
If a successor has many roots can form it, replace it with the root with the shortest length.

You need to output the sentence after the replacement.

Example 1:
Input: dict = ["cat", "bat", "rat"]
sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"
 
Note:
The input will only have lower-case letters.
1 <= dict words number <= 1000
1 <= sentence words number <= 1000
1 <= root length <= 100
1 <= sentence words length <= 1000

Medium
*/

class Solution {
public:
    
    static bool fncomp(string& A, string& B) {
        return A.length() < B.length();
    }
        
    // 28%
    string replaceWords(vector<string>& dict, string sentence) {
        istringstream ss(sentence);
        string s = "";
        string word;
        int minLen = INT_MAX;
        int maxLen = 0;
        
        for (auto w:dict) {
            minLen = min(static_cast<int>(w.length()), minLen);
            maxLen = max(static_cast<int>(w.length()), maxLen);
        }
        
        if (maxLen == 0) return "";
        
        sort(dict.begin(), dict.end(), fncomp);
        
        while (ss >> word) {
            if (word.length() < minLen) {
                s += " " + word;
                continue;
            }
            
            bool found = false;
            for (auto dd:dict) {
                if (word.rfind(dd, 0) == 0) {
                    s += " " + dd;
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                s += " " + word;
            }
        }
        
        return s.substr(1);
    }
};
