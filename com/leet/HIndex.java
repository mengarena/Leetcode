package com.leet;


import java.util.Arrays;

//Formally, if f is the function that corresponds to the number of citations for each publication, 
//we compute the h index as follows. First we order the values of f from the largest to the lowest value. 
//Then, we look for the last position in which f is greater than or equal to the position (we call h this position).
//For example, if we have a researcher with 5 publications A, B, C, D, and E with 10, 8, 5, 4, and 3 citations, respectively, 
//the h index is equal to 4 because the 4th publication has 4 citations and the 5th has only 3. 
//In contrast, if the same publications have 25, 8, 5, 3, and 3, then the index is 3 because the fourth paper has only 3 citations.

//[**] The statement about H-index in the question is kinda misleading

//Google, Facebook, Bloomberg
public class HIndex {

	public HIndex() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		int[] citations = {3,0,6,1,5};
		int[] citations = {100};
		
		System.out.println("H Index = " + hIndex(citations));
	}


//	Formally, if f is the function that corresponds to the number of citations for each publication, we compute the h index as follows. 
//	First we order the values of f from the largest to the lowest value. Then, we look for the last position in which f is greater than or equal to the position (we call h this position).
//	For example, if we have a researcher with 5 publications A, B, C, D, and E with 10, 8, 5, 4, and 3 citations, 
//	respectively, the h index is equal to 4 because the 4th publication has 4 citations and the 5th has only 3. 
//	In contrast, if the same publications have 25, 8, 5, 3, and 3, then the index is 3 because the fourth paper has only 3 citations.
//	f(A)=10, f(B)=8, f(C)=5, f(D)=4, f(E)=3¡¡¡ú h-index=4
//	f(A)=25, f(B)=8, f(C)=5, f(D)=3, f(E)=3¡¡¡ú h-index=3	
	
	public int hIndex(int[] citations) {
		Arrays.sort(citations);  //Ascending order after sorting
		
		int h = 0;
		for(int i = citations.length-1; i >=0 ; i--) {
			if (citations[i] >= citations.length - i) h = citations.length - i;
		}
		
		return h;
	}
	
/*
	public int hIndex(int[] citations) {
		Arrays.sort(citations);  //Ascending order
		
		int h = 0;
		for(int i = 0; i < citations.length; i++){
			int currH = Math.min(citations[i], citations.length - i);
			if(currH > h){
				h = currH;
			}
		}
		
		return h;
	}
*/	
	
/*   
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        int nHIndex = 0;
        int n = citations.length;
        int i, nInsertPos;
                
        List<Integer> lstSortedCitation = new ArrayList<Integer>();
        
        lstSortedCitation.add(citations[0]);
        
        for (i=1; i<n; i++) {
        	nInsertPos = getInsertPos(lstSortedCitation, citations[i]);
        	lstSortedCitation.add(nInsertPos, citations[i]);
        }
                
        int nMax = lstSortedCitation.get(n-1);
        int narrHIndex[] = new int[nMax+1];  //0~nMax
        for (i=0; i<=nMax; i++) narrHIndex[i] = 0;
        
        for (i=0; i<n; i++) {
        	narrHIndex[lstSortedCitation.get(i)] = 1;
        }
                
        for (i=nMax; i>=0; i--) {
        	nHIndex = nHIndex + narrHIndex[i];
        	
        	if (nHIndex == i) return nHIndex;
        }
        
        return 0;
    }
    
    
    public int getInsertPos(List<Integer> lstSortedCitation, int nCitCnt) {
    	int nPos = 0;
    	int n = lstSortedCitation.size();
    	int i=0, j=n-1;
    	
    	if (nCitCnt <= lstSortedCitation.get(0)) return 0;
    	if (nCitCnt >= lstSortedCitation.get(n-1)) return n;
    	
    	while (i<=j) {
    		nPos = (i+j)/2;
    		
    		if (lstSortedCitation.get(nPos) == nCitCnt) {
    			return nPos;
    		} else if (lstSortedCitation.get(nPos) < nCitCnt) {
    			i = nPos+1;
    		} else {
    			j = nPos-1;
    		}
    	}
    	
    	return i;
    }
*/	

}
