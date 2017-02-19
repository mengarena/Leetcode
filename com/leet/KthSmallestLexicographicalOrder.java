package com.leet;

//Given integers n and k, find the lexicographically k-th smallest integer in the range from 1 to n.
//
//Note: 1 ¡Ü k ¡Ü n ¡Ü 109.
//
//Example:
//
//Input:
//n: 13   k: 2
//
//Output:
//10
//
//Explanation:
//The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.

//Hulu
//Hard
public class KthSmallestLexicographicalOrder {

	public KthSmallestLexicographicalOrder() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC
	//The numbers could be formed as Trie
	//       1                   2
	//     / | \               / | \
	//   0  ...  9            0 ... 9
	// /  \     / \         /  \   / \
	//0 .. 9   0 .. 9      0 .. 9 0 ..9
	//
	//If the tree is complete, it has 1+10+100 + .. node
	//The right most (largest) node of the subtree is x999
    int index = 0;
    int ans = 0;
    
    public int findKthNumber(int n, int k) {
        for(int i=1;i<=9;i++) {
            int c = count(n, i, "");
            if(k>c+index) {
                index+=c;
                continue;
            }
            if(helper(n, k, ""+i)) break;
        }
        return ans;
    }
    
    //To locate the result node with index
    public boolean helper(int n, int k, String cur) {
        index++;
        if(index==k) {
            ans = Integer.valueOf(cur);
            return true;
        }
        for(int i=0; i<=9; i++) {
            int c = count(n, i, cur);  //Count node under the subtree
            if(k>c+index) {
                index+=c;
                continue;
            }
            
            if(Integer.valueOf(cur+i)<=n) if(helper(n, k, cur+i)) return true;
        }
        return false;
    }
    
    //Get the number of node under this root node (including the root node)
    public int count(int n, int i, String prefix) {
        long cur = Long.valueOf(prefix+i);
        int ans = 0;
        int number = 1;
        while(cur<=n) {
            ans += number;
            cur *= 10;
            number *= 10;
        }
        if(n<(cur/10+number/10-1)) ans -= cur/10+number/10-1-n;
        return ans;
    }


    //////////////
    //Based on Lexicographical Numbers.java
    //Works, But exceed time limit for very large k, because, here has to visit every number
    public int findKthNumberA(int n, int k) {
        int base = 1;
        int count = 1;
        
        while (count < k) {
            if (base <= n/10) {
                base = base*10;
                count++;
                continue;
            }
            
            if (base <= n-1 && (base % 10) != 9) {
                base++;
            } else {
                base = base/10;
                base++;
                while (base % 10 == 0) base = base/10;
            }
            
            count++;
        }
        
        return base;
    }    
    


}
