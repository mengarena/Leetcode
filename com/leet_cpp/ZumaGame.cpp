/*
Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). 
You also have several balls in your hand.

Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). 
Then, if there is a group of 3 or more balls in the same color touching, remove these balls. 
Keep doing this until no more balls can be removed.

Find the minimal balls you have to insert to remove all the balls on the table. 
If you cannot remove all the balls, output -1.

Examples:

Input: "WRRBBW", "RB"
Output: -1
Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW

Input: "WWRRBBWW", "WRBRW"
Output: 2
Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty

Input:"G", "GGGGG"
Output: 2
Explanation: G -> G[G] -> GG[G] -> empty 

Input: "RBYYBBRRB", "YRBGB"
Output: 3
Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty 

Note:
You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
The number of balls on the table won't exceed 20, and the string represents these balls is called "board" in the input.
The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.

*/

//Hard
//Baidu

class Solution {
public:

    //Function Correct, but Exceed Time Limit
    //Strategy:
    //1) do some pre-process, for example, in "hand", if the char does not exist in "board", could be removed;
    //   reduce the consecutive chars (# >= 3);
    //
    //Try to insert into every place (0 to after end), and then check whether could be reduce to empty
    //
    //Possible way to reduce time:  memorize the result of each board, hand combination
	
	
	//Check whether hand is empty
	bool isEmpty(unordered_map<char, int> handmap) {
		if (handmap.empty()) return true;
		int count = 0;
		for (auto it = handmap.begin(); it != handmap.end(); it++) {
			count += it->second;
		}
		
		return count == 0;
	}

    //Remove the consecutive chars (# >= 3) from the board
	string removeConsecutive(string org) {
		int count = 0;
		string ans = "";
		char prev = 0;
		string tmp = "";
		
		if (org.length() <= 2) return org;
		
		for (auto it=org.begin(); it != org.end(); it++) {
			if (*it == prev) {
				tmp = tmp + *it;
				count++;
			} else {
				if (count <= 2) {
					ans = ans + tmp;
				}
				
				count = 1;
				tmp = *it;
				prev = *it;
			}
		}
		
		if (count <= 2) ans = ans + tmp;
		
		count = 0;
		prev = 0;
		
		for (auto it=ans.begin(); it!=ans.end(); it++) {
			if (prev == *it) {
				count++;
				if (count >= 3) break;
			} else {
				count = 1;
				prev = *it;
			}
		}
		
		if (count >= 3) return removeConsecutive(ans);
		
		return ans;
	}

	int findMinStepHelper(string board, unordered_map<char, int> handmap) {
		board = removeConsecutive(board);
		int nLen = board.length();
		if (nLen == 0) return 0;
		
		if (isEmpty(handmap)) return -1;
		
		//for (auto it=board.begin(); it != board.end(); it++) {
		//    if (handmap[*it] == 0) return -1;
		//}
		
		if (nLen == 1) {
			if (handmap[board[0]] >= 2) {
				return 2;
			} else {
				return -1;
			}
		} else if (nLen == 2) {
			if (board[0] == board[1]) {
				if (handmap[board[0]] >= 1) {
					return 1;
				} else {
					return -1;
				}
			}
		}
		
		int ret = INT_MAX;
		int step;
		
		for (int i=0; i<=board.length(); i++) {
			if (i > 0 && i < board.length() && board[i] == board[i-1]) continue;
			
			for (auto it = handmap.begin(); it != handmap.end(); it++) {
				if (it->second == 0) continue;
				
				string myboard(board);
				unordered_map<char, int> myhandmap(handmap);
				
				myboard.insert(myboard.begin() + i, it->first);
				myhandmap[it->first]--;
				
				step = findMinStepHelper(myboard, myhandmap);
				if (step != -1) {
					ret = min(ret, step+1);   //+1 is the current insert operation
				}
			}
		}
		
		if (ret == INT_MAX) return -1;
		
		return ret;
	}

	int findMinStep(string board, string hand) {
		unordered_map<char, int> handmap;
		//Preprocess
		for (auto it=hand.begin(); it != hand.end(); it++) {
			if (board.find(*it) == string::npos) continue;
			handmap[*it]++;
		}
		
		return findMinStepHelper(board, handmap);
	}
};

