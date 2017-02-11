/*
Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

Example 1:
Input: [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
    After flipping, the maximum number of consecutive 1s is 4.

Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000

Follow up:
What if the input numbers come in one by one as an infinite stream? 
In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. 
Could you solve it efficiently?
*/

//Medium
//Google

class Solution {
public:

    //ACC
    int findMaxConsecutiveOnes(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return 1;
        
        int prevOnes = 0;
        int curOnes = 0;
        bool oneZero = false;   //To remember the case 1 1 0 1 1, whether such a "0" exists
        int maxOnes = 0;
        bool prevIsZero = false;   //Remember whether the previous number is 0
        
        for (const int& num:nums) {
            if (num == 1) {
                curOnes++;
                prevIsZero = false;
            } else {
                if (oneZero) {
                    if (prevIsZero) {
                        oneZero = false;
                        prevOnes = 0;
                        curOnes = 0;
                    } else {
                        maxOnes = max(maxOnes, prevOnes+curOnes+1);
                        prevOnes = curOnes;
                        curOnes = 0;
                    }
                } else {
                    maxOnes = max(maxOnes, curOnes+1);   //+1 means turn the 0 into 1
                    oneZero = true;
                    prevOnes = curOnes;
                    curOnes = 0;
                }
                
                prevIsZero = true;
            }
        }
        
        if (curOnes > 0) {
            if (oneZero) {
                maxOnes = max(maxOnes, prevOnes + curOnes + 1);
            } else {
                maxOnes = max(maxOnes, curOnes);
            }
        }
        
        return maxOnes;
    }
};

