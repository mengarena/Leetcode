package com.leet;

import java.util.PriorityQueue;

//Given a sorted array of integers nums and integer values a, b and c. 
//Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.
//
//The returned array must be in sorted order.
//
//Expected time complexity: O(n)
//
//Example:
//	
//nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
//
//Result: [3, 9, 15, 33]
//
//nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
//
//Result: [-23, -5, 1, 7]


//Google
public class SortTransformedArray {

	public SortTransformedArray() {
		// TODO Auto-generated constructor stub
	}

	
	//ax^2 + bx + c is a parabola; when a > 0, the parabola towards up;  when a < 0, the parabola towards down
	//
	//Compare the elements from two ends of the array, the largest/least element must be at the end
	//
	//ACC:  2ms
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if (nums == null || nums.length == 0) return new int[0];
        int n = nums.length;
        int i = 0, j = n-1;
        int[] narr = new int[n];
        int idx = 0;
        int tmp1, tmp2;
        if (a >= 0) idx = n - 1;
        
        while (i <= j) {
            tmp1 = quad(nums[i], a, b, c);
            tmp2 = quad(nums[j], a, b, c);
            
            if (a >= 0) {
                if (tmp1 >= tmp2) {
                    narr[idx--] = tmp1;
                    i++;
                } else {
                    narr[idx--] = tmp2;
                    j--;
                }
                
            } else {
                if (tmp1 >= tmp2) {
                    narr[idx++] = tmp2;
                    j--;
                } else {
                    narr[idx++] = tmp1;
                    i++;
                }
            }
        }
        
        return narr;
    }
    
    private int quad(int x, int a, int b, int c) {
        return a*x*x + b*x + c;
    }
	
    
    
	
	//ACC: 9%
    public int[] sortTransformedArrayA(int[] nums, int a, int b, int c) {
        if (nums == null || nums.length == 0) return new int[0];
        int n = nums.length;
        int i;
        int[] narr = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        
        for (i=0; i<n; i++) {
            pq.offer(a*nums[i]*nums[i] + b*nums[i]);
        }
        
        for (i=0; i<n; i++) narr[i] = pq.poll() + c;
        
        return narr;
    }

}
