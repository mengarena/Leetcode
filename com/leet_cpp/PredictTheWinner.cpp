/*
Given an array of scores that are non-negative integers. 
Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. 
Each time a player picks a number, that number will not be available for the next player. 
This continues until all the scores have been chosen. The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

Example 1:
Input: [1, 5, 2]
Output: False
Explanation: Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. 
If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.

Example 2:
Input: [1, 5, 233, 7]
Output: True
Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. 
No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.

Note:
1 <= length of the array <= 20.
Any scores in the given array are non-negative integers and will not exceed 10,000,000.
If the scores of both players are equal, then player 1 is still the winner.
Hide Company Tags
*/

//Medium
//Google

class Solution {
public:
    
    //ACC
    bool PredictTheWinner(vector<int>& nums) {
        int n = nums.size();
        
        return helper(nums, 0, n-1) >= 0;
    }
    
    int helper(vector<int>& nums, int s, int e) {
        if (s == e) return nums[s];
        
        return max(nums[s] - helper(nums, s+1, e), nums[e] - helper(nums, s, e-1));
    }



    //ACC
    //Complexity:  O(n^2)
    //https://www.youtube.com/watch?v=Tw1k46ywN6E&feature=youtu.be&list=PLUl4u3cNGP6317WaSNfmCvGym2ucw3oGp&t=3622
    //
    //When faceing nums <i, j>, I could choose nums[i], or nums[j]
    //So, finally, I should get max(nums[i] + something<i+1, j>,  nums[j] + something<i, j-1>)
    //Here something<i+1,j> or something<i, j-1> means the situation faced by the opponent
    //Because the opponent is also try to get his maximal, 
    //so my next turn will get the min from something<i+1, j>, which is min(nums<i+1, j-1>, nums<i+2, j>)  
    //(i.e. corresponds to the two situations of the opponent's selection;
    // one situation: opponent select nums[j]; the other situation: opponent select nums[i+1]
    //
    //So the DP is:
    //nums<i,j> = max(nums[i]+min(nums<i+1, j-1>, nums<i+2, j>), nums[j]+min(nums<i+1, j-1>, nums<i, j-2>)); 
    bool PredictTheWinner(vector<int>& nums) {
        int n = nums.size();

        int sumTmp = helper(nums, 0, n-1);
        
        return sumTmp >= accumulate(nums.begin(), nums.end(), 0) - sumTmp;
    }
    
    int helper(vector<int>& nums, int s, int e) {
        if (s == e) return nums[s];
        if (s+1 == e) return max(nums[s], nums[e]);
        
        return max(nums[s] + min(helper(nums, s+1, e-1), helper(nums, s+2, e)), nums[e] + min(helper(nums, s+1, e-1), helper(nums, s, e-2)));
    }

};

