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
	
	
	
    //ACC
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lstTxt = new ArrayList<>();
        if (words == null || words.length == 0) return lstTxt;
        int n = words.length;
        int start = 0;
        int end = -1;
        int i = 0;
        int tmpLen = 0;
        int maxLen = 0;
        String sRet;
        
        for (i=0; i<n; i++) maxLen = Math.max(maxLen, words[i].length());
        if (maxLen > maxWidth) return lstTxt;
        
        tmpLen = words[0].length();
        start = 0;
        end = 0;
        
        i = 1;
        
        while (i < n) {
            if (tmpLen + 1 + words[i].length() <= maxWidth) {
                tmpLen = tmpLen + 1 + words[i].length();
                end = i;
            } else {
                sRet = getJustifiedTxt(words, start, end, maxWidth);
                lstTxt.add(sRet);

                start = i;
                end = i;
                tmpLen = words[start].length();
            }
            
            i++;
        }
        
        //Process last line
        sRet = "";
        for (i=start; i<end; i++) sRet += words[i] + " ";
        sRet += words[end];
        
        tmpLen = sRet.length();
        for (i=0; i<maxWidth-tmpLen; i++) sRet = sRet + " ";
        
        lstTxt.add(sRet);
        
        return lstTxt;
    }
    
    private String getJustifiedTxt(String[] words, int start, int end, int maxWidth) {
        int count = end - start;  //Number of spaces between these words
        int totalLen = 0;
        int i;
        
        if (start == end) {
            String sRet = words[start];
            for (i=words[start].length(); i<maxWidth; i++) sRet = sRet + " ";
            return sRet;
        }
        
        for(i=start; i<=end; i++) {
            totalLen += words[i].length();
        }
        
        int remainedSpace = maxWidth - totalLen;
        
        int spaceCnt = remainedSpace/count;  //Standard #space
        int headPart = remainedSpace % count;   //Extra 1 space for these "spaces"
        
        StringBuilder sb = new StringBuilder();
        for (i=0; i<headPart; i++) {
            sb.append(words[start+i]);
            for (int j=0; j<spaceCnt+1; j++) sb.append(" ");
        }
        
        for (i=headPart; i<count; i++) {
            sb.append(words[start+i]);
            for (int j=0; j<spaceCnt; j++) sb.append(" ");
        }
        
        sb.append(words[end]);
        
        return sb.toString();
    }


	
	
	
	
	//ACC: 34%
    public List<String> fullJustifyA(String[] words, int maxWidth) {
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
