/*

735. Asteroid Collision

We are given an array asteroids of integers representing asteroids in a row.

For each asteroid, the absolute value represents its size, 
and the sign represents its direction (positive meaning right, negative meaning left). 
Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. 
If two asteroids meet, the smaller one will explode. 
If both are the same size, both will explode. 
Two asteroids moving in the same direction will never meet.

Example 1:
Input: 
asteroids = [5, 10, -5]
Output: [5, 10]
Explanation: 
The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.

Example 2:
Input: 
asteroids = [8, -8]
Output: []
Explanation: 
The 8 and -8 collide exploding each other.

Example 3:
Input: 
asteroids = [10, 2, -5]
Output: [10]
Explanation: 
The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.

Example 4:
Input: 
asteroids = [-2, -1, 1, 2]
Output: [-2, -1, 1, 2]
Explanation: 
The -2 and -1 are moving left, while the 1 and 2 are moving right.
Asteroids moving the same direction never meet, so no asteroids will meet each other.

Note:
The length of asteroids will be at most 10000.
Each asteroid will be a non-zero integer in the range [-1000, 1000]..

Medium

Lyft, Visa
*/

class Solution {
public:

    // 92%
    // The negative numbers at the beginning, will not collide (they are moving left)
    // The positive numbers at the tail will not collide (they are moving right)
    // So only need to process the part in the middle which starts with an positive number and ending at a negative number
    // For the middle part, maintain a deque (or stack), to compare with the coming number
    // Only when current back() at the deque > 0 and current coming number < 0 (i.e. collide will happen),
    // need to process case by case
    vector<int> asteroidCollision(vector<int>& asteroids) {
        int n = asteroids.size();
        vector<int> ans;
        deque<int> seg;
        
        int start = -1;
        int end = n;
        
        // To find the position of the starting negative numbers and ending positive numbers
        while (start+1 <= n-1 && asteroids[start+1] < 0) start++;
        if (start == n-1) return asteroids;
        while (end-1 >= 0 && asteroids[end-1] > 0) end--;
        if (end == 0) return asteroids;
        
        for (int i=start+1; i<end; ++i) {
            if (asteroids[i] > 0) {
                seg.push_back(asteroids[i]);
            } else {
                bool to_push = true;
                while (!seg.empty() && seg.back() > 0) {
                    if (abs(seg.back()) < abs(asteroids[i])) {
                        seg.pop_back();  // The positive is destroyed by the negative
                    } else if (abs(seg.back()) == abs(asteroids[i])) {  // both will disappear
                        seg.pop_back();
                        to_push = false;
                        break;
                    } else {  // astroids[i] is destroyed by the positive 
                        to_push = false;
                        break;
                    }
                }
                
                if (to_push) seg.push_back(asteroids[i]);
            }
        }
        
        for (int i=0; i<=start; ++i) {
            ans.push_back(asteroids[i]);
        }
        
        while (!seg.empty()) {
            ans.push_back(seg.front());
            seg.pop_front();
        }
        
        for (int i=end; i<n; ++i) {
            ans.push_back(asteroids[i]);
        }
        return ans;
    }
};
