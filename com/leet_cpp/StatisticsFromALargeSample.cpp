/*

1093. Statistics from a Large Sample

We sampled integers between 0 and 255, and stored the results in an array count:  
count[k] is the number of integers we sampled equal to k.

Return the minimum, maximum, mean, median, and mode of the sample respectively, as an array of floating point numbers.
The mode is guaranteed to be unique.

(Recall that the median of a sample is:
The middle element, if the elements of the sample were sorted and the number of elements is odd;
The average of the middle two elements, if the elements of the sample were sorted and the number of elements is even.)
 
Example 1:
Input: count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]

Output: [1.00000,3.00000,2.37500,2.50000,3.00000]

Example 2:
Input: count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]

Output: [1.00000,4.00000,2.18182,2.00000,1.00000]
 
Constraints:
count.length == 256
1 <= sum(count) <= 10^9
The mode of the sample that count represents is unique.
Answers within 10^-5 of the true value will be accepted as correct.

Medium
*/


class Solution {
public:

    // Mode means the most frequent element
    vector<double> sampleStats(vector<int>& count) {
        vector<double> ans;
        int n = count.size();
        if (n == 0) return ans;
        
        long cnt = 0;
        int minVal = -1;
        int maxVal = -1;
        int maxCnt = 0;
        int mode = 0;
        long sum = 0;
        
        for (int i = 0; i<n; ++i) {
            if (count[i] > 0) {
                sum += count[i]*i;
                if (minVal == -1) minVal = i;
                maxVal = i;
                cnt += count[i];
                if (maxCnt < count[i]) {
                    maxCnt = count[i];
                    mode = i;
                }
            }
        }
        
        if (cnt == 0) {
            ans.insert(ans.begin(), {0,0,0,0,0});
            return ans;
        }
        
        ans.push_back(minVal);
        ans.push_back(maxVal);
        ans.push_back(sum*1.0/cnt);

        bool single = true;
        if (cnt % 2 == 1) {
            cnt = cnt/2 + 1;
        } else {
            cnt = cnt/2;
            single = false;
        }
        
        long totalCnt = 0;
        
        int first = -1;
        int second = -1;
        
        for (int i=0; i<n; ++i) {
            if (count[i] > 0) {
                totalCnt = totalCnt + count[i];
                if (single) {
                    if (totalCnt >= cnt) {
                        first = i;
                        second = i;
                        break;
                    }
                } else {
                    if (totalCnt >= cnt) {
                        if (first == -1) first = i;
                    }
                    
                    if (totalCnt >= cnt+1) {
                        if (second == -1) second = i;
                        break;
                    }
                }
            }
        }
        
        ans.push_back((first+second)*1.0/2);
        ans.push_back(mode);
        return ans;
    }
};
