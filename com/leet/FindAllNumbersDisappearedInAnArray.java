/*
448. Find All Numbers Disappeared in an Array 

Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
*/

//Google
//Easy
public class Solution {
	
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> lstRet = new ArrayList<>();
        if (nums == null || nums.length == 0) return lstRet;
        int n = nums.length;
        int i;
        
        //Increase the value at the position whole corresponding values exist
        for (i=1; i<=n; i++) {
            int tmpVal = nums[i-1];
            int tmpPos = (tmpVal -1) % n;
            nums[tmpPos] = nums[tmpPos] + n;
        }
        
        //Find the values missed
        for (i=0; i<n; i++) {
            if (nums[i] <= n) {
                lstRet.add(i+1);
            }
        }
        
        return lstRet;
    }	
	
	
    public List<Integer> findDisappearedNumbersB(int[] nums) {
        List<Integer> lstRet = new ArrayList<>();
        if (nums == null || nums.length == 0) return lstRet;
        int n = nums.length;
        int i;
        
        //Negate the value at the corresponding position
        for (i=1; i<=n; i++) {
            int tmpPos = Math.abs(nums[i-1]) -1;
            if (nums[tmpPos] > 0) nums[tmpPos] = -nums[tmpPos];
        }
        
        //Find the values missed
        for (i=0; i<n; i++) {
            if (nums[i] > 0) {
                lstRet.add(i+1);
            }
        }
        
        return lstRet;
    }	
	
    public List<Integer> findDisappearedNumbersA(int[] nums) {
        List<Integer> lstRet = new ArrayList<>();
        if (nums == null || nums.length == 0) return lstRet;
        int n = nums.length;
        int i;
        
        //Put value into its position
        for (i=1; i<=n; i++) {
            int tmp = nums[i-1];
            
            while (tmp != nums[tmp-1]) {
                int tmptmp = nums[tmp-1];
                nums[tmp-1] = tmp;
                tmp = tmptmp;   
            }
        }
        
        //Find the values missed
        for (i=0; i<n; i++) {
            if (nums[i] != i+1) {
                lstRet.add(i+1);
            }
        }
        
        return lstRet;
    }
}
