/*

992. Subarrays with K Different Integers

Given an array A of positive integers, call a (contiguous, not necessarily distinct) 
subarray of A good if the number of different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.

Example 1:
Input: A = [1,2,1,2,3], K = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].

Example 2:
Input: A = [1,2,1,3,4], K = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 
Note:
1 <= A.length <= 20000
1 <= A[i] <= A.length
1 <= K <= A.length

Hard

Amazon
*/



class Solution {
     
public:
    int subarraysWithKDistinct(vector<int>& A, int K) {
        Window w1, w2;
        int left1 = 0, left2 = 0;
        int ans = 0;
        
        for (int right=0; right<A.size(); ++right) {
            w1.addElement(A[right]);
            w2.addElement(A[right]);
            
            // After following whiles,
            // left1 points to the 1st element the whole super subarray which could form K-diff array by ending at "right"
            // left2 points to the one next to the last element which could form K-diff array by ending at "right"
            // so by ending at "right", we are left2-left1 K-diff subarrays
            while (w1.getUniqueCnt() > K) w1.removeElement(A[left1++]);
            while (w2.getUniqueCnt() >= K) w2.removeElement(A[left2++]);
            
            ans += left2-left1;   // Each time, calculate how many K-diff arrays by ending at position 'right'
        }
        
        return ans;
    }
    
private:
    struct Window {
        unordered_map<int, int> counts;
        int uniqueCnt;
        
        Window() { uniqueCnt = 0; }
        
        void addElement(int num) {
            counts[num]++;
            if (counts[num] == 1) uniqueCnt++;
        }
        
        void removeElement(int num) {
            counts[num]--;
            if (counts[num] == 0) uniqueCnt--;
        }
        
        int getUniqueCnt() {
            return uniqueCnt;
        }
    };
};
