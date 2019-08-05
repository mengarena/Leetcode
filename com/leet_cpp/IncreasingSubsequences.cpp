/*
Given an integer array, your task is to find all the different possible increasing subsequences of the given array, 
and the length of an increasing subsequence should be at least 2 .

Example:

Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]

Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.

*/

//Medium
//Yahoo

class Solution {
public:

    //ACC
    vector<vector<int>> findSubsequences(vector<int>& nums) {
        vector<vector<int>> ans;
        vector<int> seq;
        
        dfs(ans, seq, nums, 0);
        return ans;
    }

    void dfs(vector<vector<int>>& ans, vector<int>& seq, vector<int>& nums, int pos) {
	    if (seq.size() >= 2) ans.push_back(seq);   //If contains >= 2 elements, add into final result
	    
	    unordered_set<int> hashset;   //To remember the nums[i] which has been added on current seq, 
	                                  //which could make sure duplicate elements only be added once
	    
	    //Try to add elements from pos to the end
	    for (int i=pos; i<nums.size(); i++) {
	        if ((seq.empty() || nums[i] >= seq.back()) && hashset.find(nums[i]) == hashset.end()) {
		        seq.push_back(nums[i]);
		        dfs(ans, seq, nums, i+1);
		        seq.pop_back();
		    
		        hashset.insert(nums[i]);
	        }
	    }
	}
    


    //Function correct
    //But Time Exceed Limit
    vector<vector<int>> findSubsequencesA(vector<int>& nums) {
        vector<vector<int>> ans;
        
        vector<int> tmp(1);
        tmp[0] = nums[0];
        ans.push_back(tmp);
        
        for (int i=1; i<nums.size(); i++) {
            int curSize = ans.size();
            
            vector<int> newElem(1);
            newElem[0] = nums[i];
            ans.push_back(newElem);
            
            for (int j=0; j<curSize; j++) {
                vector<int> newArr(ans[j].begin(), ans[j].end());
                
                if (nums[i] >= newArr[newArr.size()-1]) {
                    newArr.push_back(nums[i]);
                    
                    if (find(ans.begin(), ans.end(), newArr) == ans.end()) ans.push_back(newArr);
                }
            }
        }
        
        auto it = ans.end();
        
        while (it > ans.begin()) {
            --it;
            if ((*it).size() <= 1) ans.erase(it);
        }
        
        return ans;
    }
};

