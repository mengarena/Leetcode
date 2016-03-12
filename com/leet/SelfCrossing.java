package com.leet;

//You are given an array x of n positive numbers. 
//You start at point (0,0) and moves x[0] metres to the north, then x[1] metres to the west, 
//x[2] metres to the south, x[3] metres to the east and so on. 
//In other words, after each move your direction changes counter-clockwise.
//
//Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.
//
//Example 1:
//Given x = [2, 1, 1, 2]
//Return true (self crossing)
//Example 2:
//Given x = [1, 2, 3, 4]
//Return false (not self crossing)
//Example 3:
//Given x = [1, 1, 1, 1]
//Return true (self crossing)

public class SelfCrossing {

	public SelfCrossing() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//int[] x = {2,1,1,2};
		//int[] x = {1,2,3,4};
		//int[] x = {1,1,1,1};
		//int[] x = {1,1,2,1,1};
		int[] x = {3,3,3,2,1,1};
		//int[] x = {1,1,2,2,1,1};
		
		System.out.println(isSelfCrossing(x));
	}
	
	//Strategy:  
	//if current line is t,  it will only be possibly cross with line t-3, t-4, t-5;  it could not cross with other lines
	// When detect crossing, not only check two lines go cross each other perpendicularly, but also need to check whether they just touch or cross in same directions
    public boolean isSelfCrossing(int[] x) {
        if (x == null || x.length <= 3) return false;
        int n = x.length;
        int i;
    
        for (i=3; i<n; i++) {
        	//t-3 line
        	if ((x[i] >= x[i-2]) && (x[i-1] <= x[i-3])) return true;
        	
        	//t-4 line
        	if (i >= 4) {
        		if ((x[i-1] == x[i-3]) && (x[i-2] <= x[i-4] + x[i])) return true;
        		
        		//t-5 line
        		if (i >= 5) {
        			if ((x[i] + x[i-4] >= x[i-2]) && (x[i-1] + x[i-5] >= x[i-3]) && (x[i-4] <= x[i-2]) && (x[i-1] <= x[i-3])) return true;
        		}
        	}
        	
        }
        
        return false;
    }
	
	
/* The following solution is accepted:  2 ms	
	//Strategy:  1) Calculate the coordinates and hence know the coordinates of the two points of each line
	// 2) if current line is t,  it will only be possibly cross with line t-3, t-4, t-5;  it could not cross with other lines
	// When detect crossing, not only check two lines go cross each other perpendicularly, but also need to check whether they just touch or cross in same directions
    public boolean isSelfCrossing(int[] x) {
        if (x == null || x.length <= 3) return false;
        int n = x.length;
        int i;
        int nCurX = 0, nCurY = 0;
        
        for (i=0; i<n; i++) {        	
        	if ((i % 4) == 0) {    //South:  check nMaxY,  X doesn't change
        		nCurY = nCurY + x[i];
        		x[i] = nCurY;
        	} else if ((i % 4) == 1) {  //West:  check nMinX, Y doesn't change
        		nCurX = nCurX - x[i];
        		x[i] = nCurX;
        	} else if ((i % 4) == 2) {  //North:  check nMinY, X doesn't change
        		nCurY = nCurY - x[i];
        		x[i] = nCurY;
        	} else {  //East:   check nMaxX, Y doesn't change
        		nCurX = nCurX + x[i];
        		x[i] = nCurX;
        	}
        	
        	//Check whether cross
        	if ((i % 2) == 0) {   // Y change        		
        		if (i >= 3) {
            		//Coordinates of current line:
            		int nLineX = x[i-1];
            		int nLineMaxY = x[i] > x[i-2]? x[i]:x[i-2];
            		int nLineMinY = x[i] < x[i-2]? x[i]:x[i-2];
            		
            		int nLineY3 = x[i-4];
            		int nLineX3Max = 0;
            		int nLineX3Min = 0;
            		
            		if (i > 4) {
            			nLineX3Max = x[i-3] > x[i-5] ? x[i-3]:x[i-5];
            			nLineX3Min = x[i-3] < x[i-5] ? x[i-3]:x[i-5];
            		} else {
            			nLineX3Max = x[i-3] > 0 ? x[i-3]:0;
            			nLineX3Min = x[i-3] < 0 ? x[i-3]:0;            			
            		}
            		
            		if (nLineMinY <= nLineY3 && nLineY3 <= nLineMaxY && nLineX3Min <= nLineX && nLineX <= nLineX3Max) {
            			return true;
            		}
        			
            		if (i >= 4) {
            			int nLineX4 =(i >= 5? x[i-5]:0);
        				int nLineY4Min = 0;
        				int nLineY4Max = 0;
        				if (i > 5) {
        					nLineY4Max = x[i-4] > x[i-6] ? x[i-4]:x[i-6];
        					nLineY4Min = x[i-4] < x[i-6] ? x[i-4]:x[i-6];
        				} else {
        					nLineY4Max = x[i-4] > 0 ? x[i-4]:0;
        					nLineY4Min = x[i-4] < 0 ? x[i-4]:0;
        				}

        				if (((nLineMinY <= nLineY4Min && nLineY4Min <= nLineMaxY) || (nLineMinY <= nLineY4Max && nLineY4Max <= nLineMaxY)) && (nLineX4 == nLineX)) return true;
            			
            			
	        			if (i >= 5) {
	        				int nLineY5 = x[i-6];
	        				int nLineX5Max = 0;
	        				int nLineX5Min = 0;
	        				
	        				if (i > 6) {
	        					nLineX5Max = x[i-5] > x[i-7] ? x[i-5]:x[i-7];
	        					nLineX5Min = x[i-5] < x[i-7] ? x[i-5]:x[i-7];
	        				} else {
	        					nLineX5Max = x[i-5] > 0 ? x[i-5]:0;
	        					nLineX5Min = x[i-5] < 0 ? x[i-5]:0;        					
	        				}
	        				
	                		if (nLineMinY <= nLineY5 && nLineY5 <= nLineMaxY && nLineX5Min <= nLineX && nLineX <= nLineX5Max) {
	                			return true;
	                		}    
	                		
	        			}
            		}
        		}
        		
        		
        	} else {   // X change
        		
        		if (i >= 3) {
            		//Coordinates of current line:
            		int nLineY = x[i-1];
            		int nLineMaxX = x[i-2] > x[i]? x[i-2]:x[i];
            		int nLineMinX = x[i-2] < x[i]? x[i-2]:x[i];;
            		
            		int nLineX3 = (i >= 4? x[i-4]:0);
            		int nLineY3Max = 0;
            		int nLineY3Min = 0;
            		
            		if (i > 4) {
            			nLineY3Max = x[i-3] > x[i-5] ? x[i-3]:x[i-5];
            			nLineY3Min = x[i-3] < x[i-5] ? x[i-3]:x[i-5];		
            		} else {
            			nLineY3Max = x[i-3] > 0 ? x[i-3]:0;
            			nLineY3Min = x[i-3] < 0 ? x[i-3]:0;	
            		}
            		
            		if (nLineMinX <= nLineX3 && nLineX3 <= nLineMaxX && nLineY3Min <= nLineY && nLineY <= nLineY3Max) {
            			return true;
            		}

            		if (i >= 4) {
            			int nLineY4 = (i >= 5? x[i-5]:0);
            			int nLineX4Max = 0;
            			int nLineX4Min = 0;
            			
        				if (i > 5) {
        					nLineX4Max = x[i-4] > x[i-6] ? x[i-4]:x[i-6];
        					nLineX4Min = x[i-4] < x[i-6] ? x[i-4]:x[i-6];
        				} else {
        					nLineX4Max = x[i-4] > 0 ? x[i-4]:0;
        					nLineX4Min = x[i-4] < 0 ? x[i-4]:0;
        				}
        				
        				if (((nLineMinX <= nLineX4Min && nLineX4Min <= nLineMaxX) || (nLineMinX <= nLineX4Max && nLineX4Max <= nLineMaxX)) && (nLineY4 == nLineY)) return true;
            			
	        			if (i >= 5) {
	                		int nLineX5 = (i >= 6 ? x[i-6]:0);
	                		int nLineY5Max = 0;
	                		int nLineY5Min = 0;
	                		
	                		if (i > 6) {
	                			nLineY5Max = x[i-5] > x[i-7] ? x[i-5]:x[i-7];
	                			nLineY5Min = x[i-5] < x[i-7] ? x[i-5]:x[i-7];
	                		} else {
	                			nLineY5Max = x[i-5] > 0 ? x[i-5]:0;
	                			nLineY5Min = x[i-5] < 0 ? x[i-5]:0;                			
	                		}
	                		
	                		if (nLineMinX <= nLineX5 && nLineX5 <= nLineMaxX && nLineY5Min <= nLineY && nLineY <= nLineY5Max) {
	                			return true;
	                		}
	                			        				
	        			}
            		}
        		}
        	}
        	
        }
        
        return false;
    }
*/    
	
}
