/*

937. Reorder Log Files

You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.  
It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.  
The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  
The digit-logs should be put in their original order.

Return the final order of the logs.

Example 1:
Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 
Note:
0 <= logs.length <= 100
3 <= logs[i].length <= 100
logs[i] is guaranteed to have an identifier, and a word after the identifier.

Easy
*/

class Solution {
public:
    
    // Sort the string based on log content first, if ties, sort with identifier+log content (i.e. sort with whole thing)
    // result is in (ascending) lexicographical order
    static bool fcomp(const string& str1, const string& str2) {
        size_t pos1 = str1.find_first_of(' ');
        size_t pos2 = str2.find_first_of(' ');
        int cmp = str1.substr(pos1+1).compare(str2.substr(pos2+1));
        if (cmp) {
            return cmp < 0;
        } else {
            return str1.compare(str2) < 0;
        }
    }
    
    vector<string> reorderLogFiles(vector<string>& logs) {
        vector<string> ans;
        vector<string> digits;
        for (auto log:logs) {
            size_t pos = log.find_first_of(' ');
            if (log[pos+1] >= 'a' && log[pos+1] <= 'z') {
                ans.push_back(log);
            } else {
                digits.push_back(log);
            }
        }
        
        sort(ans.begin(), ans.end(), fcomp);
        ans.insert(ans.end(), digits.begin(), digits.end());
        return ans;
    }
};
