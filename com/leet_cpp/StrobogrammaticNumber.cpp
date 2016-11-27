/*
246. Strobogrammatic Number

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.

*/

//Google

//Easy

class Solution {
public:
    bool isStrobogrammatic(string num) {
        if (num.size() == 0) return true;
        if (num.size() == 1) {
            if (num[0] == '0' || num[0] == '1' || num[0] == '8') return true;
            return false;
        }
        
        if (num.size() > 1 && num[0] == '0') return false;
        
        int i=0, j=num.size() - 1;
        
        while (i < j) {
            if (num[i] == '0' || num[i] == '1' || num[i] == '8') {
                if (num[j] != num[i]) return false;
            } else if (num[i] == '6') {
                if (num[j] != '9') return false;
            } else if (num[i] == '9') {
                if (num[j] != '6') return false;
            } else {
                return false;
            }
            
            i++;
            j--;
        }
        
        if ((num.size() % 2) == 1) {
            if (num[i] != '0' && num[i] != '1' && num[i] != '8') return false;
        }
        
        return true;
    }
};

