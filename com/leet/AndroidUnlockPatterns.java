package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//Given an Android 3x3 key lock screen and two integers m and n, where 1 ¡Ü m ¡Ü n ¡Ü 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.
//
//Rules for a valid pattern:
//Each pattern must connect at least m keys and at most n keys.
//All the keys must be distinct.
//If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
//The order of keys used matters.
//
//Explanation:
//| 1 | 2 | 3 |
//| 4 | 5 | 6 |
//| 7 | 8 | 9 |
//
//Invalid move: 4 - 1 - 3 - 6 
//Line 1 - 3 passes through key 2 which had not been selected in the pattern.
//
//Invalid move: 4 - 1 - 9 - 2
//Line 1 - 9 passes through key 5 which had not been selected in the pattern.
//
//Valid move: 2 - 4 - 1 - 3 - 6
//Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern
//
//Valid move: 6 - 5 - 4 - 1 - 9 - 2
//Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.
//
//Example:
//Given m = 1, n = 1, return 9.

		
//Google, Medium		
public class AndroidUnlockPatterns {

	public AndroidUnlockPatterns() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void run() {
		int m = 1;
		int n = 9;
		
		System.out.println(numberOfPatterns(m, n));
	}

	//ACC:
	private static int nTotalCnt = 0;
	private static int nSingleTotalCnt = 0;
	//Function Correct, Exceeded time limit
    private static int[][] adjSteps = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}, {2, 1}, {2, -1}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {-1, -2}, {1, -2}};  //One step move
    private static int[][] adjAdjSteps = {{0, 2}, {0, -2}, {-2, 0}, {2, 0}, {-2, -2}, {2, 2}, {-2, 2}, {2, -2}}; //Two steps move  
    private static int[][] adjMidSteps = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};  //Middle pos of the two steps move
	
    public int numberOfPatterns(int m, int n) {
        if (m <= 0 || n <= 0 || n < m) return 0;
        if (n <= 1) return 9;
        if (m > 9) m = 9;
        if (n > 9) n = 9;
        boolean[][] visited = new boolean[3][3];
        
        nTotalCnt = 0;

        //Position of 1,3,7,9 are similar, could only process one of them and x4
        //Position of 2,4,6,9 are similar, could only process one of them and x4
        //Position of 5 is unique        
        for (int i=m; i<=n; i++) {
	        nSingleTotalCnt = 0;
	        visited[0][0] = true;
	        helper(visited, i-1, 0, 0);
	        visited[0][0] = false;
	        
	        nTotalCnt += nSingleTotalCnt*4;
	
	        nSingleTotalCnt = 0;
	        visited[0][1] = true;
	        helper(visited, i-1, 0, 1);
	        visited[0][1] = false;
	        
	        nTotalCnt += nSingleTotalCnt*4;
	        
	        nSingleTotalCnt = 0;
	        visited[1][1] = true;
	        helper(visited, i-1, 1, 1);
	        visited[1][1] = false;
	        
	        nTotalCnt += nSingleTotalCnt;
        }
               
        return nTotalCnt;  
    }

	
    private void helper(boolean[][] visited, int remained, int x, int y) {
    	if (remained == 0) {
    		nSingleTotalCnt++;
    		return;
    	}
 
    	int j, nextX, nextY, midX, midY;
    	
        //Try one step move from last position
        for (j=0; j<adjSteps.length; j++) {
        	nextX = x + adjSteps[j][0];
        	nextY = y + adjSteps[j][1];
        	
        	if (nextX >= 0 && nextX <= 2 && nextY >= 0 && nextY <= 2 && !visited[nextX][nextY]) {
        		visited[nextX][nextY] = true;
        		helper(visited, remained-1, nextX, nextY);
        		visited[nextX][nextY] = false;
        	}
        }
        
        if (x == 1 && y == 1) return;
        
        //Try two steps move from last position
        for (j=0; j<adjAdjSteps.length; j++) {
        	nextX = x + adjAdjSteps[j][0];
        	nextY = y + adjAdjSteps[j][1];

        	if (nextX >= 0 && nextX <= 2 && nextY >= 0 && nextY <= 2 && !visited[nextX][nextY]) {
            	midX = x + adjMidSteps[j][0];
            	midY = y + adjMidSteps[j][1];
            	
            	if (visited[midX][midY]) {
            		visited[nextX][nextY] = true;
            		helper(visited, remained-1, nextX, nextY);
            		visited[nextX][nextY] = false;           		
            	}
        	}
        }
        
    }
	
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private static int[][] cell = {{1,2,3}, {4,5,6}, {7,8,9}};
    
    public int numberOfPatternsA(int m, int n) {
        if (m <= 0 || n <= 0 || n < m) return 0;
        if (n <= 1) return 9;
        if (m > 9) m = 9;
        if (n > 9) n = 9;
        int nTotalCnt = 0;
        
        if (m == 1) nTotalCnt = 9;

        //Position of 1,3,7,9 are similar, could only process one of them and x4
        //Position of 2,4,6,9 are similar, could only process one of them and x4
        //Position of 5 is unique        
        nTotalCnt += getNumberOfPattern(m, n, 0, 0)*4 + getNumberOfPattern(m, n, 0, 1)*4 + getNumberOfPattern(m, n, 1, 1);
        
        return nTotalCnt;  
    }

    //Function Correct
    //Exceed time limit
    private int getNumberOfPattern(int m, int n, int startX, int startY) {    	
    	Queue<Set<Integer>> quSetVisited = new LinkedList<Set<Integer>>();
    	Queue<Integer> quLastPosX = new LinkedList<Integer>();
    	Queue<Integer> quLastPosY = new LinkedList<Integer>();
    	
    	int nTotalCnt = 0;
    	
    	quLastPosX.offer(startX);
    	quLastPosY.offer(startY);
    	Set<Integer> setVisited1 = new HashSet<Integer>();
    	setVisited1.add(cell[startX][startY]);
    	quSetVisited.offer(setVisited1);
    	
    	int i,j;
    	int nextX, nextY;
    	int x, y;
    	int nextVal;
    	int midX, midY;
    	int midVal;
    	    	    	    	
    	for (int k=2; k<=n; k++) {  
        	int nSize = quLastPosX.size();
        	
            for (i=0; i<nSize; i++) {
                x = quLastPosX.poll();
                y = quLastPosY.poll();
                Set<Integer> setVisited = quSetVisited.poll();
                        
                //Try one step move from last position
                for (j=0; j<adjSteps.length; j++) {
                	nextX = x + adjSteps[j][0];
                	nextY = y + adjSteps[j][1];
                	
                	if (nextX >= 0 && nextX <= 2 && nextY >= 0 && nextY <= 2) {
                		nextVal = cell[nextX][nextY];
                        
                		if (!setVisited.contains(nextVal)) {
                			Set<Integer> setVisitedNew = new HashSet<Integer>(setVisited);
                			setVisitedNew.add(nextVal);
                			quSetVisited.offer(setVisitedNew); 
                			
                			quLastPosX.offer(nextX);
                			quLastPosY.offer(nextY);             			
                		}
                	}
                }
                
                if (x == 1 && y == 1) {
                	continue;
                }
                
                //Try two steps move from last position
                for (j=0; j<adjAdjSteps.length; j++) {
                	nextX = x + adjAdjSteps[j][0];
                	nextY = y + adjAdjSteps[j][1];

                	if (nextX >= 0 && nextX <= 2 && nextY >= 0 && nextY <= 2) {
                		nextVal = cell[nextX][nextY];
                		
                    	midX = x + adjMidSteps[j][0];
                    	midY = y + adjMidSteps[j][1];
                		midVal = cell[midX][midY];
                        
                		if (!setVisited.contains(nextVal) && setVisited.contains(midVal)) {
                			Set<Integer> setVisitedNew = new HashSet<Integer>(setVisited);
                			setVisitedNew.add(nextVal);
                			quSetVisited.offer(setVisitedNew);
                			
                			quLastPosX.offer(nextX);
                			quLastPosY.offer(nextY);       			
                		}
                	}
                }
            }  //for (i=0; i<nSize; i++) {
        	            
    		if (k >= m)  nTotalCnt +=  quSetVisited.size();   //Calculate total #pattern for m~n
    	}
    	
    	return nTotalCnt;
    }
    
  
    
    
    
    
    
    
    
    private int getNumberOfPatternB(int m, int n, int startX, int startY) {
    	List<Set<Integer>> lstSetVisited = new ArrayList<Set<Integer>>();    	
    	List<Integer> lstLastPosX = new ArrayList<Integer>();
    	List<Integer> lstLastPosY = new ArrayList<Integer>();
    	int nTotalCnt = 0;
    	
    	lstLastPosX.add(startX);
    	lstLastPosY.add(startY);
    	Set<Integer> setVisited1 = new HashSet<Integer>();
    	setVisited1.add(cell[startX][startY]);
    	lstSetVisited.add(setVisited1);
    	
    	int i,j;
    	int nextX, nextY;
    	int x, y;
    	int nextVal;
    	int midX, midY;
    	int midVal;
    	
    	    	    	    	
    	for (int k=2; k<=n; k++) {
        	List<Integer> lstLastPosXNew = new ArrayList<Integer>();
        	List<Integer> lstLastPosYNew = new ArrayList<Integer>();
        	List<Set<Integer>> lstSetVisitedNew = new ArrayList<Set<Integer>>();
  
        	int nSize = lstLastPosX.size();
        	
            for (i=0; i<nSize; i++) {
                x = lstLastPosX.get(i);
                y = lstLastPosY.get(i);
                Set<Integer> setVisited = lstSetVisited.get(i);
                            
                //Try one step move from last position
                for (j=0; j<adjSteps.length; j++) {
                	nextX = x + adjSteps[j][0];
                	nextY = y + adjSteps[j][1];
                	
                	if (nextX >= 0 && nextX <= 2 && nextY >= 0 && nextY <= 2) {
                		nextVal = cell[nextX][nextY];
                        
                		if (!setVisited.contains(nextVal)) {
                			Set<Integer> setVisitedNew = new HashSet<Integer>(setVisited);
                			setVisitedNew.add(nextVal);
                			lstSetVisitedNew.add(setVisitedNew); 
                			
                			lstLastPosXNew.add(nextX);
                			lstLastPosYNew.add(nextY);
                			           			
                		}
                	}
                }
                
                if (x == 1 && y == 1) continue;
                
                //Try two steps move from last position
                for (j=0; j<adjAdjSteps.length; j++) {
                	nextX = x + adjAdjSteps[j][0];
                	nextY = y + adjAdjSteps[j][1];
                	
                	midX = x + adjMidSteps[j][0];
                	midY = y + adjMidSteps[j][1];
                	
                	if (nextX >= 0 && nextX <= 2 && nextY >= 0 && nextY <= 2) {
                		nextVal = cell[nextX][nextY];
                		midVal = cell[midX][midY];
                        
                		if (!setVisited.contains(nextVal) && setVisited.contains(midVal)) {
                			Set<Integer> setVisitedNew = new HashSet<Integer>(setVisited);
                			setVisitedNew.add(nextVal);
                			lstSetVisitedNew.add(setVisitedNew);
                			
                			lstLastPosXNew.add(nextX);
                			lstLastPosYNew.add(nextY);       			
                		}
                	}
                }
            }
        	
//            lstLastPosX = new ArrayList<>(lstLastPosXNew);
//            lstLastPosY = new ArrayList<>(lstLastPosYNew);
//            lstSetVisited = new ArrayList<>(lstSetVisitedNew);

            lstLastPosX = lstLastPosXNew;
            lstLastPosY = lstLastPosYNew;
            lstSetVisited = lstSetVisitedNew;
            
    		if (k >= m)  nTotalCnt +=  lstSetVisitedNew.size();;   //Calculate total #pattern for m~n
    	}
    	
    	return nTotalCnt;
    }
     
    
    
    
    
    
    
    
    
    
    //Wrong
    //Try all possible one-step move
    private int getPatternA(List<Integer> lstLastPosX, List<Integer> lstLastPosY, List<Set<Integer>> lstSetVisited) {
    	int i,j;
    	int nextX, nextY;
    	int x, y;
    	int nextVal;
    	int midX, midY;
    	int midVal;
    	int nSize = lstLastPosX.size();
    	    	
    	List<Integer> lstLastPosXNew = new ArrayList<Integer>();
    	List<Integer> lstLastPosYNew = new ArrayList<Integer>();
    	List<Set<Integer>> lstSetVisitedNew = new ArrayList<Set<Integer>>();
    	
        for (i=0; i<nSize; i++) {
            x = lstLastPosX.get(i);
            y = lstLastPosY.get(i);
            Set<Integer> setVisited = lstSetVisited.get(i);
                        
            //Try one step move from last position
            for (j=0; j<adjSteps.length; j++) {
            	nextX = x + adjSteps[j][0];
            	nextY = y + adjSteps[j][1];
            	
            	if (nextX >= 0 && nextX <= 2 && nextY >= 0 && nextY <= 2) {
            		nextVal = cell[nextX][nextY];
                    
            		if (!setVisited.contains(nextVal)) {
            			Set<Integer> setVisitedNew = new HashSet<Integer>(setVisited);
            			setVisitedNew.add(nextVal);
            			lstSetVisitedNew.add(setVisitedNew); 
            			
            			lstLastPosXNew.add(nextX);
            			lstLastPosYNew.add(nextY);
            			           			
            		}
            	}
            }
            
            //Try two steps move from last position
            for (j=0; j<adjAdjSteps.length; j++) {
            	nextX = x + adjAdjSteps[j][0];
            	nextY = y + adjAdjSteps[j][1];
            	
            	midX = x + adjMidSteps[j][0];
            	midY = y + adjMidSteps[j][1];
            	
            	if (nextX >= 0 && nextX <= 2 && nextY >= 0 && nextY <= 2) {
            		nextVal = cell[nextX][nextY];
            		midVal = cell[midX][midY];
                    
            		if (!setVisited.contains(nextVal) && setVisited.contains(midVal)) {
            			Set<Integer> setVisitedNew = new HashSet<Integer>(setVisited);
            			setVisitedNew.add(nextVal);
            			lstSetVisitedNew.add(setVisitedNew);
            			
            			lstLastPosXNew.add(nextX);
            			lstLastPosYNew.add(nextY);       			
            		}
            	}
            }
        }
    	
        lstLastPosX = new ArrayList<>(lstLastPosXNew);
        lstLastPosY = new ArrayList<>(lstLastPosYNew);
        lstSetVisited = new ArrayList<>(lstSetVisitedNew);
        
    	return lstSetVisitedNew.size();
    }
       
    
    
//    private int getNumberOfPattern(int m, int n, int startX, int startY) {
//    	List<Set<Integer>> lstSetVisited = new ArrayList<Set<Integer>>();    	
//    	List<Integer> lstLastPosX = new ArrayList<Integer>();
//    	List<Integer> lstLastPosY = new ArrayList<Integer>();
//    	int nTotalCnt = 0;
//    	
//    	lstLastPosX.add(startX);
//    	lstLastPosY.add(startY);
//    	Set<Integer> setVisited = new HashSet<Integer>();
//    	setVisited.add(cell[startX][startY]);
//    	lstSetVisited.add(setVisited);
//    	    	
//    	for (int i=2; i<=n; i++) {
//    		int nPatternCnt = getPatternA(lstLastPosX, lstLastPosY, lstSetVisited);
//    		
//    		if (i >= m)  nTotalCnt += nPatternCnt;   //Calculate total #pattern for m~n
//    	}
//    	
//    	return nTotalCnt;
//    }
    
     //Try all possible one-step move
    private int getPattern(List<Integer> lstLastPosX, List<Integer> lstLastPosY, List<Set<Integer>> lstSetVisited) {
    	int i,j;
    	int nextX, nextY;
    	int x, y;
    	int nextVal;
    	int midX, midY;
    	int midVal;
    	int nSize = lstLastPosX.size();
    	boolean bSet = false;
    	    	
    	
        for (i=nSize-1; i>=0; i--) {
            x = lstLastPosX.get(i);
            y = lstLastPosY.get(i);
            Set<Integer> setVisited = lstSetVisited.get(i);
            
            bSet = false;
            
            //Try one step move from last position
            for (j=0; j<adjSteps.length; j++) {
            	nextX = x + adjSteps[j][0];
            	nextY = y + adjSteps[j][1];
            	
            	if (nextX >= 0 && nextX <= 2 && nextY >= 0 && nextY <= 2) {
            		nextVal = cell[nextX][nextY];
                    
            		if (!setVisited.contains(nextVal)) {
            			Set<Integer> setVisitedNew = new HashSet<Integer>(setVisited);
            			setVisitedNew.add(nextVal);
            			
            			if (bSet == false) {
            				lstSetVisited.set(i, setVisitedNew);
            				lstLastPosX.set(i, nextX);
            				lstLastPosY.set(i, nextY);
            				bSet = true;
            			} else {
            				lstSetVisited.add(i, setVisitedNew);
            				lstLastPosX.add(i, nextX);
            				lstLastPosY.add(i, nextY);            				
            			}
            		}
            	}
            }
            
            //Try two steps move from last position
            for (j=0; j<adjAdjSteps.length; j++) {
            	nextX = x + adjAdjSteps[j][0];
            	nextY = y + adjAdjSteps[j][1];
            	
            	midX = x + adjMidSteps[j][0];
            	midY = y + adjMidSteps[j][1];
            	
            	if (nextX >= 0 && nextX <= 2 && nextY >= 0 && nextY <= 2) {
            		nextVal = cell[nextX][nextY];
            		midVal = cell[midX][midY];
                    
            		if (!setVisited.contains(nextVal) && setVisited.contains(midVal)) {
            			Set<Integer> setVisitedNew = new HashSet<Integer>(setVisited);
            			setVisitedNew.add(nextVal);
            			
            			if (bSet == false) {
            				lstSetVisited.set(i, setVisitedNew);
            				lstLastPosX.set(i, nextX);
            				lstLastPosY.set(i, nextY);
            			} else {
            				lstSetVisited.add(i, setVisitedNew);
            				lstLastPosX.add(i, nextX);
            				lstLastPosY.add(i, nextY); 
            			}
            		}
            	}
            }
        }
    	
    	return lstSetVisited.size();
    }
    
    
}


/*
    private int getNumberOfPattern(int m, int n, int startX, int startY) {    	
    	Queue<Set<Integer>> quSetVisited = new LinkedList<Set<Integer>>();
    	Queue<Integer> quLastPosX = new LinkedList<Integer>();
    	Queue<Integer> quLastPosY = new LinkedList<Integer>();
    	
    	int nTotalCnt = 0;
    	
    	quLastPosX.offer(startX);
    	quLastPosY.offer(startY);
    	Set<Integer> setVisited1 = new HashSet<Integer>();
    	setVisited1.add(cell[startX][startY]);
    	quSetVisited.offer(setVisited1);
    	
    	int i,j;
    	int nextX, nextY;
    	int x, y;
    	int nextVal;
    	int midX, midY;
    	int midVal;
    	    	    	    	
    	for (int k=2; k<=n; k++) {  
        	int nSize = quLastPosX.size();
        	
            for (i=0; i<nSize; i++) {
                x = quLastPosX.poll();
                y = quLastPosY.poll();
                Set<Integer> setVisited = quSetVisited.poll();
                        
                //Try one step move from last position
                for (j=0; j<adjSteps.length; j++) {
                	nextX = x + adjSteps[j][0];
                	nextY = y + adjSteps[j][1];
                	
                	if (nextX >= 0 && nextX <= 2 && nextY >= 0 && nextY <= 2) {
                		nextVal = cell[nextX][nextY];
                        
                		if (!setVisited.contains(nextVal)) {
                			Set<Integer> setVisitedNew = new HashSet<Integer>(setVisited);
                			setVisitedNew.add(nextVal);
                			quSetVisited.offer(setVisitedNew); 
                			
                			quLastPosX.offer(nextX);
                			quLastPosY.offer(nextY);             			
                		}
                	}
                }
                
                if (x == 1 && y == 1) {
                	continue;
                }
                
                //Try two steps move from last position
                for (j=0; j<adjAdjSteps.length; j++) {
                	nextX = x + adjAdjSteps[j][0];
                	nextY = y + adjAdjSteps[j][1];

                	if (nextX >= 0 && nextX <= 2 && nextY >= 0 && nextY <= 2) {
                		nextVal = cell[nextX][nextY];
                		
                    	midX = x + adjMidSteps[j][0];
                    	midY = y + adjMidSteps[j][1];
                		midVal = cell[midX][midY];
                        
                		if (!setVisited.contains(nextVal) && setVisited.contains(midVal)) {
                			Set<Integer> setVisitedNew = new HashSet<Integer>(setVisited);
                			setVisitedNew.add(nextVal);
                			quSetVisited.offer(setVisitedNew);
                			
                			quLastPosX.offer(nextX);
                			quLastPosY.offer(nextY);       			
                		}
                	}
                }
            }  //for (i=0; i<nSize; i++) {
        	            
    		if (k >= m)  nTotalCnt +=  quSetVisited.size();   //Calculate total #pattern for m~n
    	}
    	
    	return nTotalCnt;
    }

 * */


