/*
Given a List of words, return the words that can be typed using letters of alphabet on only one row's of 
American keyboard like the image below.

American keyboard

Example 1:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]

Note:
You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.
*/


//Easy
//Mathworks
class Solution {
public:
    vector<string> findWords(vector<string>& words) {
        vector<string> rows;
        vector<string> ans;
        
        rows.push_back("qwertyuiop");
        rows.push_back("asdfghjkl");
        rows.push_back("zxcvbnm");
        
        for (int i=0; i<words.size(); i++) {
            if (checkWord(rows, words[i])) ans.push_back(words[i]);
        }
        
        return ans;
    }
    
    
    //Check whether characters in word is in one row
    bool checkWord(vector<string>& rows, string word) {
        int curRow = -1;
        int prevRow = -1;
        
        for (int i=0; i<word.size(); i++) {
            for (int j=0; j<rows.size(); j++) {
                if (rows[j].find(word[i]) != string::npos) {
                    curRow = j;
                    if (prevRow != -1 && prevRow != curRow) return false;
                    prevRow = curRow;
                }
            }
        }
        
        return true;
    }
    
};

