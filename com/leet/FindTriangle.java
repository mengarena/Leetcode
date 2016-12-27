package com.leet;

import java.util.ArrayList;
import java.util.List;


/*
Given an int array, find
1) Whether there are three elements which could form a triangle
*/


//LinkedIn
public class FindTriangle {

	public FindTriangle() {
		// TODO Auto-generated constructor stub
	}


    //Strategy:  for every 3 elements a, b, c, must:  a+b > c;  a+c > b; b+c > a
    //If a, b, c are sorted:  a <= b <= c;
    //Then naturally, b+c > a;  a+c > b; we only need to find a+b > c
    //If an array is sorted, the two a,b which are closest to c have the biggest chance to meet a + b > c,
    //So, we only need to every three continuous elements in a sorted array
	public boolean ExistTriangle(int[] narr) {
	    if (narr == null || narr.length < 3) return false;
	    
	    Arrays.sort(narr);   //Complixity: nlogn
	    
	    for (int i=2; i<narr.length; i++) {
		    if (narr[i-2] + narr[i-1] > narr[i]) return true;
		}
	    
	    return false;
	}
	

    //Time Complixity O(n^2)
    //Analysis:  Time complixity of for loop (a) --- O(n)
    // Time complixity of for loop (b) --- O(n)
    // startIdx is creasing monotonically, so the total time on findLastK is constant (could be treated as n)
    //Therefor the total time is O(n^2)
    public int CountTriangle(int[] narr) {
	    if (narr == null || narr.length < 3) return 0;
	    Arrays.sort(narr);
	    int n = narr.length;
	    int i,j;
	    int count = 0;
	    
	    for (i=0; i<n-2; i++) {  //(a)
		
		    int startIdx = i+2;
		    
		    for (j=i+1; j<n-1; j++) {  //(b)
			    int lastIdx = findLastK(narr, startIdx, narr[i] + narr[j]); //(c) Find the last k meet narr[i] + narr[j] > narr[k
			    
			    if (lastIdx != -1) {
				    count += lastIdx-j;
				    
				    startIdx = lastIdx;  //Next time, for next combination [i, j], to search for the last k, we could start from lastIdx
				}
			}
		}
		
		return count;
	}
	
	
	//Another implementation
	//Time complexity O(n^2)
    public int CountTriangleK(int[] narr) {
	    if (narr == null || narr.length < 3) return 0;
	    Arrays.sort(narr);
	    int n = narr.length;
	    int i,j,k;
	    int count = 0;
	    
	    for (i=0; i<n-2; i++) {  //(a)
		
		    int k = i+2;
		    
		    for (j=i+1; j<n-1; j++) {  //(b)
				while (k < n && narr[i] + narr[j] > narr[k]) k++;
				
				count += k-1-j;
			}
		}
		
		return count;
	}
	
	
	
	
	
	//Find the last one which is < target
	private int findLastK(int[] narr, int start, int target) {
	    int end = narr.length-1;
	    int lastIdx = -1;
	    
	    if (start > end) retrun lastIdx;
	    
	    int mid;
	    
	    while (start <= end) {
		    mid = start + (end-start)/2;
		    
		    if (narr[mid] >= target) {
			    end = mid-1;
			} else {
				lastIdx = mid;
			    start = mid+1;
			}
		}
	
		return lastIdx;
	}

}
