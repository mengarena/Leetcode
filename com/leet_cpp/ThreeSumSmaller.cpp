//259. 3Sum Smaller
//
//Given an array of n integers nums and a target, 
//find the number of index triplets i, j, k with 0 <= i < j < k < n that 
//satisfy the condition nums[i] + nums[j] + nums[k] < target.
//
//For example, given nums = [-2, 0, 1, 3], and target = 2.
//
//Return 2. Because there are two triplets which sums are less than 2:

//[-2, 0, 1]
//[-2, 0, 3]
//
//Follow up:
//Could you solve it in O(n2) runtime?
//
//Google
//Medium

class Solution {
public:
    int threeSumSmaller(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int i, j, k;
        int sum = 0;
        int count = 0;
        
        for (i=0; i<=n-3; i++) {
            j = i+1;
            k = n-1;
            
            while (j < k) {
                sum = nums[i] + nums[j] + nums[k];
                if (sum < target) {
                    count += k-j;
                    j++;
                } else {
                    k--;
                }
            }
        }
        
        return count;
    }
};
