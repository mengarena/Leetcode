package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
//
//You should pack your words in a greedy approach; 
//that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
//
//Extra spaces between words should be distributed as evenly as possible. 
//If the number of spaces on a line do not divide evenly between words,
//the empty slots on the left will be assigned more spaces than the slots on the right.
//
//For the last line of text, it should be left justified and no extra space is inserted between words.
//
//For example,
//words: ["This", "is", "an", "example", "of", "text", "justification."]
//L: 16.
//
//Return the formatted lines as:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
//		
//Note: Each word is guaranteed not to exceed L in length.
//
//click to show corner cases.
//
//Corner Cases:
//A line other than the last line might contain only one word. What should you do in this case?
//In this case, that line should be left-justified.


//Airbnb, Linkedin, Facebook
//Hard
public class TextJustification {

	public TextJustification() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		//String[] words = {"This", "is", "an", "example", "of", "text", "justification.", "bb"};
		//String[] words = {"a", "b", "c", "d", "e", "f"};
		String[] words = {"What","must","be","shall","be."};
		int maxWidth = 12;
		
		List<String> lstFull = fullJustify(words, maxWidth);
		
		for (String sfull:lstFull) {
			System.out.println("[" + sfull + "]");
		}
	}
	
	
	//ACC: 34%
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lstFull = new ArrayList<String>();
        if (words == null || words.length == 0) return lstFull;
        int n = words.length;
        int i;
        int wordIdx = 0;
        int startIdx = 0;
        int endIdx = 0;
        
        if (n == 1) {
        	StringBuilder sb = new StringBuilder();
        	sb.append(words[0]);
        	for (i=words[0].length(); i<maxWidth; i++) {
        		sb.append(" ");
        	}
        	lstFull.add(sb.toString());
        	return lstFull;
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (wordIdx < n) {
        	if (sb.toString().length() + words[wordIdx].length() == maxWidth) {
        		sb.append(words[wordIdx]);
        		lstFull.add(sb.toString());
        		
        		wordIdx++;
        		startIdx = wordIdx;
        		endIdx = startIdx;
        		sb = new StringBuilder();
        		
        	} else if (sb.toString().length() + words[wordIdx].length() > maxWidth) {
        		String sLine = getJustifiedLine(words, startIdx, endIdx-1, maxWidth);
        		
        		lstFull.add(sLine);
        		startIdx = wordIdx;
        		endIdx = startIdx;
        		sb = new StringBuilder();
        		
        	} else {  //sb.toString().length() + words[wordIdx].length() < maxWidth
        		sb.append(words[wordIdx]);
        		sb.append(" ");
        		wordIdx++;
        		endIdx++;
        	}
        }
        
        if (sb.length() > 0) {
        	//For the last line of text, it should be left justified and no extra space is inserted between words.
        	for (i=sb.length(); i<maxWidth; i++) {
        		sb.append(" ");
        	}
    		
    		lstFull.add(sb.toString());
        }
       
        return lstFull;
    }

    
    private String getJustifiedLine(String[] words, int startIdx, int endIdx, int maxWidth) {
    	StringBuilder sb = new StringBuilder();
    	int i,j;
    	
    	if (endIdx == startIdx) {
    		sb.append(words[startIdx]);
    		for (i=words[startIdx].length(); i<maxWidth; i++) {
    			sb.append(" ");
    		}
    		
    		return sb.toString();
    	}
    	
    	int nLen = 0;
    	
    	for (i=startIdx; i<=endIdx; i++) nLen = nLen + words[i].length();
    	
    	int spaceCnt = maxWidth - nLen;
    	int avgSpaceCnt = spaceCnt/(endIdx-startIdx);
    	int nExtraCnt = spaceCnt - avgSpaceCnt*(endIdx-startIdx);   //To assign the nExtraCnt space, each word get at most one (otherwise, the extra part could be distributed to everyone at least 1)
    	
    	for (i=0; i<nExtraCnt; i++) {
    		sb.append(words[startIdx++]);
    		sb.append(" ");
    		for (j=0; j<avgSpaceCnt; j++) {
    			sb.append(" ");
    		}
    	}
    	
    	for (i=startIdx; i<=endIdx; i++) {
    		sb.append(words[i]);
    		
    		if (i != endIdx) {
    			for (j=0; j<avgSpaceCnt; j++) sb.append(" ");
    		}
    	}
    	
    	return sb.toString();
    }
}
