/*

666. Path Sum IV

If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

For each integer in this list:
The hundreds digit represents the depth D of this node, 1 <= D <= 4.
The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. 
The position is the same as that in a full binary tree.
The units digit represents the value V of this node, 0 <= V <= 9.
Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. 
You need to return the sum of all paths from the root towards the leaves.

Example 1:
Input: [113, 215, 221]
Output: 12
Explanation: 
The tree that the list represents is:
    3
   / \
  5   1

The path sum is (3 + 5) + (3 + 1) = 12.

Example 2:
Input: [113, 221]
Output: 4
Explanation: 
The tree that the list represents is: 
    3
     \
      1

The path sum is (3 + 1) = 4.

Medium
*/


class Solution {
public:
    
    // 89%
    // Strategy: For each node, find its global index; and also find the global index of its parent
    //           Then count how many times of each node should be counted within the tree for total path sum
    int getOverallIdx(int num) {
        int level = num/100;
        int level_idx = (num/10)%10;
        
        return static_cast<int>(pow(2, level-1) + level_idx - 1 - 1);
    }
    
    int getParentIdx(int num) {
        int globalIdx = getOverallIdx(num);
        return (globalIdx-1)/2;
    }
    
    int pathSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int count[15] = {0};
        
        int n = nums.size();
        
        for (int i=n-1; i>=0; --i) {
            int overallIdx = getOverallIdx(nums[i]);
            if (count[overallIdx] == 0) count[overallIdx]++;
            if (i == 0) break;
            int parentIdx = getParentIdx(nums[i]);
            count[parentIdx] += count[overallIdx];
        }
        
        int sum = 0;
        
        for (int i=0; i<n; i++) {
            sum += (nums[i] % 10) * count[getOverallIdx(nums[i])];
        }
        
        return sum;
    }
};
