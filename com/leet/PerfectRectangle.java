package com.leet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

//Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.
//
//Each rectangle is represented as a bottom-left point and a top-right point. 
//For example, a unit square is represented as [1,1,2,2]. (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).
//
//
//Example 1:
//
//rectangles = [
//  [1,1,3,3],
//  [3,1,4,2],
//  [3,2,4,4],
//  [1,3,2,4],
//  [2,3,3,4]
//]
//
//Return true. All 5 rectangles together form an exact cover of a rectangular region.
//
//Example 2:
//
//rectangles = [
//  [1,1,2,3],
//  [1,3,2,4],
//  [3,1,4,2],
//  [3,2,4,4]
//]
//
//Return false. Because there is a gap between the two rectangular regions.
//
//Example 3:
//
//rectangles = [
//  [1,1,3,3],
//  [3,1,4,2],
//  [1,3,2,4],
//  [3,2,4,4]
//]
//
//Return false. Because there is a gap in the top center.
//
//Example 4:
//
//rectangles = [
//  [1,1,3,3],
//  [3,1,4,2],
//  [1,3,2,4],
//  [2,2,4,4]
//]
//
//Return false. Because two of the rectangles overlap with each other.


//Google
//Hard
public class PerfectRectangle {

	public PerfectRectangle() {
		// TODO Auto-generated constructor stub
	}

	
	//------------------------
//	https://discuss.leetcode.com/topic/55923/o-n-solution-by-counting-corners-with-detailed-explaination/2
//		
//	This is an expanded version of my earlier post under the contest discussion board.
//	The following code passes through not only the OJ but also various test cases others have pointed out.
//
//	Idea
//
//	0_1472399247817_perfect_rectangle.jpg
//
//	Consider how the corners of all rectangles appear in the large rectangle if there's a perfect rectangular cover.
//	Rule 1: The local shape of the corner has to follow one of the three following patterns
//
//	Corner of the large rectangle (blue): it occurs only once among all rectangles
//	T-junctions (green): it occurs twice among all rectangles
//	Cross (red): it occurs four times among all rectangles
//	Rule 2: A point can only be the top-left corner of at most one sub-rectangle. 
//	Similarly it can be the top-right/bottom-left/bottom-right corner of at most one sub-rectangle. Otherwise overlaps occur.
//
//	Proof of correctness
//
//	Obviously, any perfect cover satisfies the above rules. 
//	So the main question is whether there exists an input which satisfy the above rules, yet does not compose a rectangle.
//
//	First, any overlap is not allowed based on the above rules because
//
//	aligned overlap like [[0, 0, 1, 1], [0, 0, 2, 2]] are rejected by Rule 2.
//	unaligned overlap will generate a corner in the interior of another sub-rectangle, so it will be rejected by Rule 1.
//	Second, consider the shape of boundary for the combined shape. The cross pattern does not create boundary. 
//	The corner pattern generates a straight angle on the boundary, and the T-junction generates a straight border.
//	So the shape of the union of rectangles has to be rectangle(s).
//
//	Finally, if there are more than two non-overlapping rectangles, at least 8 corners will be found, and cannot be matched to the 4 bounding box corners 
//	(be reminded we have shown that there is no chance of overlapping).
//	So the cover has to be a single rectangle if all above rules are satisfied.
//
//	Algorithm
//
//	Step1: Based on the above idea we maintain a mapping from (x, y)->mask by scanning the sub-rectangles from beginning to end.
//
//	(x, y) corresponds to corners of sub-rectangles
//	mask is a 4-bit binary mask. Each bit indicates whether there have been a sub-rectangle with a top-left/top-right/bottom-left/bottom-right corner at (x, y). 
//	If we see a conflict while updating mask, it suffice to return a false during the scan.
//	In the meantime we obtain the bounding box of all rectangles (which potentially be the rectangle cover) by getting the upper/lower bound of x/y values.
//	Step 2: Once the scan is done, we can just browse through the unordered_map to check the whether the mask corresponds to a T junction / cross, or corner if it is indeed a bounding box corner.
//	(note: my earlier implementation uses counts of bits in mask to justify corners, and this would not work with certain cases as @StefanPochmann points out).
//
//	Complexity
//
//	The scan in step 1 is O(n) because in the outer loop it visits each rectangle and in the inner loop it looks up unordered_map.
//
//	Step2 visits 1 corner at a time with O(1) computations for at most 4n corners (actually much less because either corner overlap or early stopping occurs). So it's also O(n).	
//	
//	// pos encoding: 1 - TL 2- TR 4- BL 8-BR
//	// return false if a conflict in mask occurs (i.e. there used to be a rectangle with corner (x, y) at pos
//	
//	inline bool insert_corner(unordered_map<int, unordered_map<int, int>>& corner_count, int x, int y, int pos) {
//	    int& m = corner_count[x][y];
//	    if (m & pos) return false;
//	    m |= pos;
//	    return true;
//	}
//
//	bool isRectangleCover(vector<vector<int>>& rectangles) {
//	    // step 1: counting
//	    unordered_map<int, unordered_map<int, int>> corner_count;
//	    int minx = INT_MAX, maxx=INT_MIN, miny=INT_MAX, maxy=INT_MIN;
//	    for (auto& rect : rectangles) {
//	        minx = min(minx, rect[0]);
//	        maxx = max(maxx, rect[2]);
//	        miny = min(miny, rect[1]);
//	        maxy = max(maxy, rect[3]);
//	        if (!insert_corner(corner_count, rect[0], rect[1], 1)) return false;
//	        if (!insert_corner(corner_count, rect[2], rect[1], 2)) return false;
//	        if (!insert_corner(corner_count, rect[0], rect[3], 4)) return false;
//	        if (!insert_corner(corner_count, rect[2], rect[3], 8)) return false;
//	    }
//	    
//	    //step2: checking
//	    bool valid_corner[16] = {false};
//	    bool valid_interior[16] = {false};
//	    valid_corner[1] = valid_corner[2] = valid_corner[4] = valid_corner[8] = true;
//	    valid_interior[3] = valid_interior[5] = valid_interior[10] = valid_interior[12] = valid_interior[15] = true;
//	    
//	    for (auto itx = corner_count.begin(); itx != corner_count.end(); ++itx) {
//	        int x = itx->first;
//	        for (auto ity = itx->second.begin(); ity != itx->second.end(); ++ity) {
//	            int y = ity->first;
//	            int mask = ity->second;
//	            if ((x == minx || x == maxx) && (y == miny || y == maxy)) {
//	                if ( !valid_corner[mask] ) return false;
//	            }
//	            else {
//	                if ( !valid_interior[mask]) return false;
//	            }
//	        }
//	    }
//	    return true;
//	}
//	
//	
//	The above code may be refined by changing the 2D unordered_map to 1D. But such improvements has no effect on complexity.
//	
//	struct pairhash {//double hash function for pair key
//		public:
//		    template <typename T, typename U>
//		    size_t operator()(const pair<T, U> &rhs) const {
//		        size_t l = hash<T>()(rhs.first);
//		        size_t r = hash<U>()(rhs.second);
//		        return l + 0x9e3779b9 + (r << 6) + (r >> 2);
//		    }
//		};
//
//		bool isRectangleCover(vector<vector<int>>& rectangles) {
//		    // step 1: counting
//		    unordered_map<pair<int, int>, int, pairhash> corner_count;
//		    int minx = INT_MAX, maxx=INT_MIN, miny=INT_MAX, maxy=INT_MIN;
//		    for (auto& rect : rectangles) {
//		        minx = min(minx, rect[0]);
//		        maxx = max(maxx, rect[2]);
//		        miny = min(miny, rect[1]);
//		        maxy = max(maxy, rect[3]);
//		        
//		        int& m1 = corner_count[make_pair(rect[0], rect[1])]; 
//		        if (m1 & 1) return false; else m1 |= 1;
//		        int& m2 = corner_count[make_pair(rect[2], rect[1])];
//		        if (m2 & 2) return false; else m2 |= 2;
//		        int& m3 = corner_count[make_pair(rect[0], rect[3])]; 
//		        if (m3 & 4) return false; else m3 |= 4;
//		        int& m4 = corner_count[make_pair(rect[2], rect[3])]; 
//		        if (m4 & 8) return false; else m4 |= 8;
//		    }
//		    
//		    //step2: checking
//		    for (const auto& kv: corner_count) {
//		        pair<int, int> pos; int mask;
//		        tie(pos, mask) = kv;
//		        if ((pos.first == minx || pos.first == maxx) && (pos.second == miny || pos.second == maxy)) {
//		            if (mask != 1 && mask != 2 && mask != 4 && mask != 8) return false;
//		        }
//		        else {
//		            if (mask != 3 && mask != 5 && mask != 10 && mask != 12 && mask != 15) return false;
//		        }
//		    }
//		    return true;
//		}
	//------------------------
	
	
	
	
	
//	To make a perfect rectangle, two conditions need to be met:
//
//		The total area of all sub-rectangles == (rightmost-leftmost)*(uppermost-lowermost);
//		For every side of each sub-rectangle, there should be one or more sub-rectangles with corresponding counter-sides, except for the outermost sides, i.e., for each top side, there should be corresponding bottom sides of other sub-rectangle(s) with the same values, and the right sides should have corresponding left ones . So we use four maps to store all sides(<Interval>) of the sub-rectangles, and for each key in topmap, except for the uppermost side of the parent-rectangle, there should be a same key in bottommap, and their values should be the same after merging.
		 //ACC   
	
	class Interval {
	    int start,end;
	    Interval() { start = 0; end = 0; }
	    Interval(int s, int e) { start = s; end = e; }
	}	
	
	     public boolean isRectangleCover(int[][] rectangles) {
		        long area=0;
		        int up=Integer.MIN_VALUE, low = Integer.MAX_VALUE, rightest=Integer.MIN_VALUE, leftest=Integer.MAX_VALUE;
		        HashMap<Integer,ArrayList<Interval>> topmap = new HashMap<>();
		        HashMap<Integer,ArrayList<Interval>> bottommap = new HashMap<>();
		        HashMap<Integer,ArrayList<Interval>> leftmap = new HashMap<>();
		        HashMap<Integer,ArrayList<Interval>> rightmap = new HashMap<>();
		        for(int i=0;i<rectangles.length;i++){
		            int[] r1 = rectangles[i];
		            int top = r1[3], right = r1[2], left = r1[0], bottom = r1[1];
		            up = Math.max(top,up);
		            low = Math.min(low, bottom);
		            rightest = Math.max(right,rightest);
		            leftest = Math.min(left, leftest);
		            if (!topmap.containsKey(top))  topmap.put(top,new ArrayList<Interval>());
		            topmap.get(top).add(new Interval(left,right));
		            if (!bottommap.containsKey(bottom))  bottommap.put(bottom,new ArrayList<Interval>());
		            bottommap.get(bottom).add(new Interval(left,right));
		            if(!leftmap.containsKey(left)) leftmap.put(left,new ArrayList<Interval>());
		            leftmap.get(left).add(new Interval(bottom,top));
		            if(!rightmap.containsKey(right)) rightmap.put(right,new ArrayList<Interval>());
		            rightmap.get(right).add(new Interval(bottom,top));
		            area += (top-bottom)*(right-left);
		        }
		        if(area!=( rightest-leftest)*(up-low)) return false;
		        if(bottommap.size()!=topmap.size()||leftmap.size()!=rightmap.size()) return false;
		        return (compareMaps(bottommap,topmap,low) && compareMaps(rightmap,leftmap,rightest) ) ;
		    }
	     
		    public boolean compareMaps(HashMap<Integer,ArrayList<Interval>> map1, HashMap<Integer,ArrayList<Interval>> map2, int side){
		        for(int top: map1.keySet()  ){
		            if(top==side) continue;
		            List<Interval> mergedTopList = merge(map1.get(top));
		            if(!map2.containsKey(top)) return false;
		            List<Interval> mergedBottomeList = merge(map2.get(top));
		            if(mergedBottomeList.size()!=mergedTopList.size()) return false;
		            for(int i=0;i<mergedBottomeList.size();i++) {
		                if(mergedBottomeList.get(i).start!=mergedTopList.get(i).start ||mergedBottomeList.get(i).end!=mergedTopList.get(i).end) return false;
		            }
		        }
		        return true;
		    }
		    
		    public List<Interval> merge(List<Interval> intervals) {
		        List<Interval> res = new ArrayList<>();
		        if(intervals==null||intervals.size()<2) return intervals;
		        Collections.sort(intervals, new Comparator<Interval>(){public int compare(Interval i1, Interval i2) { return i1.start-i2.start; } } );
		        int start = intervals.get(0).start, end=intervals.get(0).end;
		        for(int i=1;i<intervals.size();i++){
		            Interval cur = intervals.get(i);
		            if(cur.start>end){
		                res.add(new Interval(start, end) );
		                start=cur.start;
		            }
		            if(cur.end>end) end=cur.end;
		        }
		        res.add(new Interval(start, end) ); 
		        return res;
		    }



	
	
	//Exceed Time Limit
	//Attention, in the rectangles, for the up-right points, the rectangle cell is on its left-bottom
    public boolean isRectangleCoverA(int[][] rectangles) {
        if (rectangles == null || rectangles.length == 0 || rectangles[0].length == 0) return false;
        int n = rectangles.length;
        int xMin = Integer.MAX_VALUE, yMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE, yMax = Integer.MIN_VALUE;
        int i,j,k;
        
        for (i=0; i<n; i++) {
            xMin = Math.min(xMin, rectangles[i][0]);
            yMin = Math.min(yMin, rectangles[i][1]);
            xMax = Math.max(xMax, rectangles[i][2]-1);
            yMax = Math.max(yMax, rectangles[i][3]-1);
        }
        
        int[][] grid = new int[xMax-xMin+1][yMax-yMin+1];
        
        for (i=0; i<n; i++) {
            for (j=rectangles[i][0]; j<=rectangles[i][2]-1; j++) {
                for (k=rectangles[i][1]; k<=rectangles[i][3]-1; k++) {
                    if (grid[j-xMin][k-yMin] == 1) return false;
                    grid[j-xMin][k-yMin] = 1;
                }
            }
        }
        
        for (i=0; i<xMax-xMin+1; i++) {
            for (j=0; j<yMax-yMin+1; j++) {
                if (grid[i][j] == 0) return false;
            }
        }
        
        return true;
    }

}
