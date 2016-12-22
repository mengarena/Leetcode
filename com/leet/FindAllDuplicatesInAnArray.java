/*
442. Find All Duplicates in an Array

Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]

*/

//Pocket Gems
//Medium

public class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> lstRet = new ArrayList<>();
        if (nums == null || nums.length <= 1) return lstRet;
        Map<Integer, Integer> hm = new HashMap<>();
        
        for (int num:nums) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
            
            if (hm.get(num) == 2) lstRet.add(num);
        }
        
        return lstRet;
    }
}
