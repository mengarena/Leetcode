package com.leet;

import java.util.ArrayList;
import java.util.List;

//You are given a listing of directories and files in a file system.
//Each directory and file has a name, which is non-empty string consisting of alphanumerical characters.
//Additionally, the name of each file contains a single dot character; 
//the part of the name starting with the dot is called the extension.
//Directory names do not contain any dots.
//All the names are case-sensitive.
//
//Each entry is listed on a separate line.
//Every directory is followed by the listing of its contents indented by one space character.
//The contents of the root directory are not indented.
//
//Here is a sample listing:
//	dir1
//	  dir11
//	  dir12
//	    picture.jpeg
//	    dir121
//	    file1.txt
//	dir2
//	  file2.gif
//	  
//We have three files (picture.jpeg, file1.txt and file2.gif) and six directories(dir1, dir11, dir12, dir121, dir2 and the root directory).
//Directory dir12 contains two files (picture.jpeg and file1.txt) and an empty directory (dir121).
//The root directory contains two directories (dir1 and dir2).
//
//The absolute path of a file is a string containing the names of directories which have to be traversed (from the root directory) to reach the file, 
//separated by slash characters.
//For example, the absolute path to the file file1.txt is "/dir1/dir12/file1.txt".
//Note that there is no "drive letter", such as "C:", and each absoluve path starts with a slash.
//
//We are interested in image files only; that is, files with extensions .jpeg, .png or .gif (and only these extensions).
//We are looking for the total length of all the absolute paths leading to the image files.
//For example, in the file system described above there are two image files:
//"/dir1/dir12/picture.jpeg" and "/dir2/file2.gif".
//The total length of the absolute paths to these files is 24+15 = 39.
//
//Write a function:
//	class Solution {public int solution(String s); }
//
//that, given a string S consisting of N characters which contains the listing of a file system, 
//returns the total of lengths (in characters) modulo 1,000,000,007 of all the absolute paths to the image files.
//
//For example, given the sample listing shown above, the function should return 39, as explained above.
//If there are no image files, the function should return 0.
		

public class TotalImageFileLen {

	public TotalImageFileLen() {
		// TODO Auto-generated constructor stub
	}

    public void run() {
//    	String s = " dir1  dir11  dir12   picture.jpeg   dir121   file1.txt dir2  file2.gif  file3.txt  file4.png";
    	String s = " dir1  dir11  dir12   picture.txt   dir121   file1.txt dir2  file2.gif";
//    	String s = " dir1  dir11  dir12   picture.txt   dir121   file1.txt";   
    	
    	int imageFileLen = getTotalImageFileLen(s);
    	
    	System.out.println(imageFileLen);
    }
    
    
    //Strategy:  Similar to Question:  Longest Absolute File Path
    //
    public int getTotalImageFileLen(String s) {
    	if (s == null || s.isEmpty()) return 0;
    	
    	int n = s.length();
    	int level = 0;
    	List<Integer> lstLevelLen = new ArrayList<>();
    	int totalLen = 0;
    	int i;
    	int start = -1, end = -1;
    	
    	for (i=0; i<n; i++) {
    		if (s.charAt(i) == ' ') {
    			if (start != -1) {
    				if (lstLevelLen.size() >= level) {
    					lstLevelLen.set(level-1, end-start+1);
    				} else {
    					lstLevelLen.add(end-start+1);
    				}
    				
    				String sPart = s.substring(start, end+1);
    				if (isImageFile(sPart)) {
    					totalLen += getPathLen(lstLevelLen, level-1);
    				}
    				
        			start = -1;
        			end = -1;
        			
        			level = 0;
    			}
    			
    			level++;   //one indent (space) corresponds to one level
    			
    		} else {
    			if (start == -1) {
    				start = i;
    			}
    			
    			end = i;
    		}
    	}
    	
    	if (start != -1) {
			if (lstLevelLen.size() >= level) {
				lstLevelLen.set(level-1, end-start+1);
			} else {
				lstLevelLen.add(end-start+1);
			}
			
			String sPart = s.substring(start, end+1);
			if (isImageFile(sPart)) {
				totalLen += getPathLen(lstLevelLen, level-1);
			}    		
    	}
    	
    	return totalLen % 1000000007;
    }

    
    private boolean isImageFile(String sPart) {
    	String[] ext = new String[]{".jpeg", ".png", ".gif"};
    	
    	for (int i=0; i<ext.length; i++) {
    		if (sPart.indexOf(ext[i]) != -1) return true;
    	}
    	
    	return false;
    }
    
    private int getPathLen(List<Integer> lstLevelLen, int nIdx) {
    	int len = 0;
    	
    	for (int i=nIdx; i>=0; i--) {
    		len += lstLevelLen.get(i) + 1;   //1: for the "/" in path
    	}
    	
    	return len;
    }
}
