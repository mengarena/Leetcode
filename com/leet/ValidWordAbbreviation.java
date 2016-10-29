package com.leet;

//Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
//
//A string such as "word" contains only the following valid abbreviations:
//
//["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
//
//Notice that only the above abbreviations are valid abbreviations of the string "word". 
//Any other string is not a valid abbreviation of "word".
//
//Note:
//Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
//
//Example 1:
//Given s = "internationalization", abbr = "i12iz4n":
//
//Return true.
//
//Example 2:
//Given s = "apple", abbr = "a2e":
//
//Return false.

//Google
//Easy
public class ValidWordAbbreviation {

	public ValidWordAbbreviation() {
		// TODO Auto-generated constructor stub
	}

	//ACC: 31ms
    public boolean validWordAbbreviation(String word, String abbr) {
        if ((word == null || word.length() == 0) && (abbr == null || abbr.length() == 0)) return true;
        if ((word == null || word.length() == 0) ^ (abbr == null || abbr.length() == 0)) return false;
        if (word.length() < abbr.length()) return false;
        
        int n = word.length();
        int m = abbr.length();
        int i, j;
        int startPos, endPos;
        char cW = 0;
        char cA = 0;
 
        i = 0;
        j = 0;
        
        while (i < n && j < m) {
            cW = word.charAt(i);
            cA = abbr.charAt(j);
            
            if (cA >= '1' && cA <= '9') {
                startPos = j;
                endPos = j+1;
                
                while (endPos < m && abbr.charAt(endPos) >= '0' && abbr.charAt(endPos) <= '9') endPos++;
            
                i += Integer.parseInt(abbr.substring(startPos, endPos));
                j = endPos;
            } else {
                if (cA == cW) {
                    i++;
                    j++;
                } else {
                    return false;
                }
            }
        }
        
        return (i == n && j == m);
        
    }

}
