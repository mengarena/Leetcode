//217. Contains Duplicate  
//
//Given an array of integers, find if the array contains any duplicates. 
//Your function should return true if any value appears at least twice in the array, 
//and it should return false if every element is distinct.
//
//Palantir, Airbnb, Yahoo

class Solution {
public:
    bool containsDuplicate(vector<int>& nums) {
        if (nums.size() <= 1) return false;
        
        unordered_set<int> setNum;
        
        for (vector<int>::iterator it = nums.begin(); it != nums.end(); ++it) {
            if (setNum.find(*it) != setNum.end()) return true;
            
            setNum.insert(*it);
        }
        
        return false;
    }
};
