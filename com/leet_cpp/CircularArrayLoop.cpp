/*

457. Circular Array Loop

You are given a circular array nums of positive and negative integers. 
If a number k at an index is positive, then move forward k steps. 
Conversely, if it's negative (-k), move backward k steps. 
Since the array is circular, you may assume that the last element's next element is the first element, 
and the first element's previous element is the last element.

Determine if there is a loop (or a cycle) in nums. 
A cycle must start and end at the same index and the cycle's length > 1. 
Furthermore, movements in a cycle must all follow a single direction. 
In other words, a cycle must not consist of both forward and backward movements.

Example 1:
Input: [2,-1,1,2,2]
Output: true
Explanation: There is a cycle, from index 0 -> 2 -> 3 -> 0. The cycle's length is 3.

Example 2:
Input: [-1,2]
Output: false
Explanation: The movement from index 1 -> 1 -> 1 ... is not a cycle, because the cycle's length is 1. 
By definition the cycle's length must be greater than 1.

Example 3:
Input: [-2,1,-1,-2,-2]
Output: false
Explanation: The movement from index 1 -> 2 -> 1 -> ... is not a cycle, 
because movement from index 1 -> 2 is a forward movement, but movement from index 2 -> 1 is a backward movement. 
All movements in a cycle must follow a single direction.
 
 
Note:
-1000 ≤ nums[i] ≤ 1000
nums[i] ≠ 0
1 ≤ nums.length ≤ 5000
 
Follow up:
Could you solve it in O(n) time complexity and O(1) extra space complexity?

Medium

Google
*/


class Solution {
public:

    // 100%
    // Strategy:
    // 0) Since the number are between -1000~1000, if they are processed, set the value to  < -3000 or  > 3000
    //    (i.e. by adding 3000 or - 3000) 
    // 1) Start one unprocessed number, move forwards (or backwards), until it meets a processed (*)number < -3000 or > 3000
    // 2) This (*) processed number might be an entry point of a loop, 
    //    or a number in the chain leads to a loop previously processed 
    // 3) From this (*) this processed number, move forwards (or backwards) to find the real entry point of a loop,
    //    by setting the numbers to < -6000 or > 6000,  if it see a number is < -6000 or > 6000 again, 
    //    that will be the real entry point of the loop
    // 4) Reset the number processed in step 3) to range < -3000 or > 3000
    // 5) From the real entry point to go through the loop, to see
    //    a) whether the number in the loop has same sign
    //    b) len > 1
    bool circularArrayLoop(vector<int>& nums) {
        int n = nums.size();
        if (n <= 1) return false;
        int i = 0;
        
        while (i < n) {
            while (i < n && (nums[i] > 3000 || nums[i] < -3000)) i++;
            
            if (i == n) break;
            
            int j = i;
            
            while (nums[j] > -3000 && nums[j] < 3000) {
                int next;
                if (nums[j] == 0) break;
                
                if (nums[j] > 0) {
                    next = (j + nums[j]) % n;
                    nums[j] += 3000;
                } else {
                    next = (j + nums[j] + n) % n;
                    if (next < 0) next = next+n;
                    nums[j] -= 3000;
                }
                
                j = next;
            }
            
            if (nums[j] == 0) {
                i++;
                continue;
            }
            
            int startPos = j;
            
            int orgStart = startPos;
            
            // To find the real entry point of the loop
            while (nums[startPos] < 6000 && nums[startPos] > -6000) {
                if (nums[startPos] > 0) {
                    nums[startPos] += 3000;
                    startPos = (startPos + nums[startPos] - 6000) % n;
                } else {
                    nums[startPos] -= 3000;
                    startPos = (startPos + nums[startPos] + 6000 + n) % n;
                    if (startPos < 0) startPos += n;
                }
            }
            
            // set > 6000 and < -6000 to range > 3000 and < -3000
            while (nums[orgStart] > 6000 || nums[orgStart] < -6000) {
                if (nums[orgStart] > 0) {
                    nums[orgStart] -= 3000;
                    orgStart = (orgStart + nums[orgStart] - 3000) % n;
                } else {
                    nums[orgStart] += 3000;
                    orgStart = (orgStart + nums[orgStart] + 3000 + n) % n;
                    if (orgStart < 0) orgStart += n;
                }
            }
            
            bool candi = true;
            int step = 1;
            int next;
            if (nums[startPos] > 0) {
                next = (startPos + nums[startPos] - 3000) % n;
            } else {
                next = (startPos + nums[startPos] + 3000 + n) % n; 
                if (next < 0) next += n;
            }
            
            while (next != startPos) {
                step++;
                if (nums[next]*nums[startPos] < 0) {
                    candi = false;
                    break;
                }
                
                if (nums[next] > 0) {
                    next = (next + nums[next] - 3000) % n;
                } else {
                    next = (next + nums[next] + 3000 + n) % n; 
                    if (next < 0) next += n;
                }  
            }
            
            if (candi && step > 1) return true;
            i++;
        }
        
        return false;
    }
 
 
     // 21%
     bool circularArrayLoop(vector<int>& nums) {
        int n = nums.size();
        for(int i=0; i<n; i++){
            int slow = i;
            int fast = getNextIdx(nums, slow);
            bool direction = nums[slow] > 0;

            if(fast == slow) { // check if the index circle itelf
                nums[i] = 0;
                continue;
            }

            while (slow != fast && nums[fast] != 0) { 
                if(nums[fast] > 0 != direction) break;
                slow = getNextIdx(nums, slow);
                fast = getNextIdx(nums, fast);
                if(nums[fast] > 0 != direction) break;                
                fast = getNextIdx(nums, fast);
            }

            if (fast == slow && fast != getNextIdx(nums, fast))
                return true;

            nums[i] = 0;
        }
        
        return false;
    }
    
    int getNextIdx(vector<int>& nums, int i) {
        int n = nums.size();
        return ((i+nums[i])%n+n)%n;
    }
};

