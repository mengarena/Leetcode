package com.leet;

//Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
//
//Hint:  Expected runtime complexity is in O(log n) and the input is sorted.

//Facebook
public class HIndexII {

	public HIndexII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		int[] citations = {0, 1, 3,5,6};
		int[] citations = {0};
		
		System.out.println("H-Index = " + hIndex(citations));
	}
	
	
//	Formally, if f is the function that corresponds to the number of citations for each publication, we compute the h index as follows. 
//	First we order the values of f from the largest to the lowest value. Then, we look for the last position in which f is greater than or equal to the position (we call h this position).
//	For example, if we have a researcher with 5 publications A, B, C, D, and E with 10, 8, 5, 4, and 3 citations, respectively, 
//	the h index is equal to 4 because the 4th publication has 4 citations and the 5th has only 3. 
//	In contrast, if the same publications have 25, 8, 5, 3, and 3, then the index is 3 because the fourth paper has only 3 citations.
//	f(A)=10, f(B)=8, f(C)=5, f(D)=4, f(E)=3¡¡¡ú h-index=4
//	f(A)=25, f(B)=8, f(C)=5, f(D)=3, f(E)=3¡¡¡ú h-index=3		
	
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        int nHIndex = 0;
        int n = citations.length;
        int i = 0, j = n-1;
        int nMid;
        
        while (i <= j) {
        	nMid = (i+j)/2;
        	
        	if (citations[nMid] < n-nMid) {
        		i = nMid + 1;
        	} else if (citations[nMid] >= n-nMid) {
        		j = nMid - 1; 
        		nHIndex = n-nMid;
        	}
        }
        
        return nHIndex;
    }
	
}
