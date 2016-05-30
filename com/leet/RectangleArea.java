package com.leet;

//Find the total area covered by two rectilinear rectangles in a 2D plane.
//
//Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
//
//Rectangle Area
//Assume that the total area is never beyond the maximum possible value of int.

public class RectangleArea {

	public RectangleArea() {
		// TODO Auto-generated constructor stub
	}

	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
	    if (C < E || G < A ) {
	        return (G-E)*(H-F) + (C-A)*(D-B);
	    }
	 
	    if (D < F || H < B) {
	        return (G-E)*(H-F) + (C-A)*(D-B);
	    }
	    
	    int right = Math.min(C,G);
	    int left = Math.max(A,E);
	    int top = Math.min(H,D);
	    int bottom = Math.max(F,B);
	 
	    return (G-E)*(H-F) + (C-A)*(D-B) - (right-left)*(top-bottom);
	}
	
	
	//ACC
    public int computeAreaA(int A, int B, int C, int D, int E, int F, int G, int H) {
        int len11 = C-A;
        int len12 = D-B;
        int len21 = G-E;
        int len22 = H-F;
        int inter11 = 0;
        int inter12 = 0;
        int inter21 = 0;
        int inter22 = 0;
        
        int area = len11*len12 + len21*len22;
        
        if (C <= E || G <= A) return area;
        if (B >= H || F >= D) return area;
        
        inter11 = Math.max(A, E);
        inter12 = Math.min(C, G);
        inter21 = Math.max(B, F);
        inter22 = Math.min(D, H);
        
        area = area - (inter12-inter11)*(inter22-inter21);
        
        return area;
    }
	
	
	
	
    public int computeArea_navie(int A, int B, int C, int D, int E, int F, int G, int H) {
        int nArea = 0;
                
        int nACLen = C - A;
        int nBDLen = D - B;
        int nEGLen = G - E;
        int nFHLen = H - F;
        
        if (A <= E && B <= F && G <= C && H <= D) {
        	nArea = nACLen*nBDLen;
        	return nArea;
        }
        
        if (E <= A && F <= B && C <= G && D <= H) {
        	nArea = nEGLen*nFHLen;
        	return nArea;
        }
        
        if (G <= A || C <= E || D <= F || H <= B) {
        	nArea = nACLen*nBDLen + nEGLen*nFHLen;
        	return nArea;
        }
        
        if (A <= E && G <= C && F <= B && D <= H) {
        	nArea = nACLen*nBDLen + nEGLen*nFHLen - (G-E)*(D-B);
        	return nArea;        	
        }
        
        if (E <= A && C <= G && B <= F && H <= D) {
        	nArea = nACLen*nBDLen + nEGLen*nFHLen - (C-A)*(H-F);
        	return nArea;        	
        }
               
        if (E <= A && A <= G && F <= B  && D <= H) {
        	nArea = nACLen*nBDLen + nEGLen*nFHLen - (G-A)*(D-B);
        	return nArea;
        }
        
        if (E <= C && C <= G && F <= B && D <= H) {
        	nArea = nACLen*nBDLen + nEGLen*nFHLen - (C-E)*(D-B);
        	return nArea;        	
        }
        
        if (F <= D && D <= H && E <= A && C <= G) {
        	nArea = nACLen*nBDLen + nEGLen*nFHLen - (C-A)*(D-F);
        	return nArea;        	
        }
        
        if (F <= B && B <= H && E <= A && C <= G) {
        	nArea = nACLen*nBDLen + nEGLen*nFHLen - (C-A)*(H-B);
        	return nArea;        	
        }
       
        if (E < A && A <= G && F < B && B <= H) {
        	nArea = nACLen*nBDLen + nEGLen*nFHLen - (G-A)*(H-B);
        	return nArea;
        } 
        
        if (E < A && A <= G && F < D && D <= H) {
        	nArea = nACLen*nBDLen + nEGLen*nFHLen - (G-A)*(D-F);
        	return nArea;        	
        }
        
        if (E < C && C <= G && F < D && D <= H) {
        	nArea = nACLen*nBDLen + nEGLen*nFHLen - (C-E)*(D-F);
        	return nArea;        	        	
        }
        
        if (E < C && C <= G && F < B && B <= H) {
        	nArea = nACLen*nBDLen + nEGLen*nFHLen - (C-E)*(H-B);
        	return nArea;        	        	        	
        }
        
        if (E < A && A <= G && B <= F && H <= D) {
        	nArea = nACLen*nBDLen + nEGLen*nFHLen - (G-A)*(H-F);
        	return nArea;        	        	        	        	
        }
        
        if (E < C && C <= G && B <= F && H <= D) {
        	nArea = nACLen*nBDLen + nEGLen*nFHLen - (C-E)*(H-F);
        	return nArea;        	
        }
        
        if (F < D  && D <= H && A <= E && G <= C) {
        	nArea = nACLen*nBDLen + nEGLen*nFHLen - (D-F)*(G-E);
        	return nArea;        	
        }
        
        if (F < B  && B <= H && A <= E  && G <= C) {
        	nArea = nACLen*nBDLen + nEGLen*nFHLen - (H-B)*(G-E);
        	return nArea;        	
        }
        
        
        return nArea;
    }
    
	public void run() {
		int A,B,C,D,E,F,G,H;
		
		A = -3; B = 0;
		C = 3; D = 4;
		
		E = 0; F = -1;
		G = 9; H = 2;
		
		int nArea = computeArea(A,B,C,D,E,F,G,H);
		
		System.out.println("Rectangle Area = " + nArea);
	}

}
