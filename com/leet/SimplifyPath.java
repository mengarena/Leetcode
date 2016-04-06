package com.leet;

import java.util.Stack;

//Given an absolute path for a file (Unix-style), simplify it.
//
//For example,
//path = "/home/", => "/home"
//path = "/a/./b/../../c/", => "/c"
//click to show corner cases.
//
//Corner Cases:
//Did you consider the case where path = "/../"?
//In this case, you should return "/".
//Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
//In this case, you should ignore redundant slashes and return "/home/foo".

//Microsoft
public class SimplifyPath {

	public SimplifyPath() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
//		String path = "/a/.//b/..//..//c/";
//		String path = "/../";
//		String path = "/home/";
//		String path = "/home//foo/";
//		String path = "/ab/../../.././cc/d";
//		String path = "/ab/././././cc/d";	
//		String path = "///";
//		String path = "/....";
//		String path = "/home/../../..";
//		String path = "/.../b//../a/./cc/";
//		String path = "/..hidden";
//		String path = "/.hidden";
//		String path = "/";
//		String path = "//";
//		String path = "/.";
		String path = "/..";
		System.out.println("Simplified Path = " + simplifyPath(path));
	}

	//Attention: in Unix, "...", "....", ".........." ... are all valid folders (.......1.txt is also a valid (hidden) file)
	//   "/..hidden",  "/.hidden"  are valid   
	
    public String simplifyPath(String path) {
        if (path == null || path.isEmpty()) return "";
        Stack<Character> stkPath = new Stack<Character>();
        int n = path.length();
        int i;
        int nDotCount = -1;
        char cPrevious = ' ';
        char cPath = ' ';
        
        for (i=0; i<n; i++) {
        	cPath = path.charAt(i);
        	
        	if (cPath == '.') {
        		if (nDotCount >= 0) nDotCount++;
        		
        		if (nDotCount < 0 && cPrevious == '/') {
        			nDotCount = 1;
        		} 
        		
        		stkPath.push('.');
        		cPrevious = cPath;
        	} else if (cPath == '/') {
        		if (nDotCount == 1) {
        			stkPath.pop();
        			cPrevious = stkPath.peek();
        		} else if (nDotCount == 2) {
        			stkPath.pop(); stkPath.pop();    //Pop ".."
        			
        			if (stkPath.size() > 1)  stkPath.pop();  //Pop the "/" before the above ".."
        			
        			while (stkPath.size() >= 1 && stkPath.peek() != '/') stkPath.pop();
        			cPrevious = stkPath.peek();
        			
        		} else {
        			if (cPrevious != '/') stkPath.push('/');
        			cPrevious = '/';
        		}
        		
        		nDotCount = -1;
        	} else {
        		stkPath.push(cPath);
        		nDotCount = -1;
        		cPrevious = cPath;
        	}
        	
        }
        
		if (nDotCount == 1) {
			stkPath.pop();
		} else if (nDotCount == 2) {
			stkPath.pop(); stkPath.pop();    //Pop ".."
			
			if (stkPath.size() > 1)  stkPath.pop();  //Pop the "/" before the above ".."
			
			while (stkPath.size() >= 1 && stkPath.peek() != '/') stkPath.pop();
		}
		
		if (stkPath.peek() == '/' && stkPath.size() > 1) stkPath.pop();
		
        //Pop stack to form the final path
        StringBuilder sNewPath = new StringBuilder();
        while (!stkPath.isEmpty())  sNewPath.insert(0, stkPath.pop());
        
        return sNewPath.toString();
    }
    
    
/*	
	//This solution is accepted, but not very efficient
    public String simplifyPath(String path) {
        if (path == null || path.isEmpty()) return "";
                
        //Remove current folder "."
        //Replace parent folder ".." with "##"
        //Leave other dot sequence (e.g. "...", "....", ".." in "/..hidden") there
        path = preprocessPath(path);        
        
        //remove continuous ///, replace it with "/"
        StringBuilder sNewPath = new StringBuilder();
        boolean bPrevSlash = false;
        sNewPath.append(path.charAt(0));
        if (path.charAt(0) == '/') bPrevSlash = true;
        
        for (int i=1; i<path.length(); i++) {
        	char cPath = path.charAt(i);
        	if (cPath == '/') {
        		if (bPrevSlash == true) continue;
        		sNewPath.append(cPath);
        		bPrevSlash = true;
        	} else {
        		sNewPath.append(cPath);
        		bPrevSlash = false;
        	}       	
         }
        
        int nIdx = -1;
        int nFirstCutPos = -1;
        int nLastCutPos = -1;
        String sPath = sNewPath.toString();
        
        nIdx = sPath.indexOf("##");
        while (nIdx != -1) {
        	nLastCutPos = nIdx+1;
        	if (nIdx+2 <= sPath.length()-1 && sPath.charAt(nIdx+2) == '/') {
        		nLastCutPos++;
        	}
        	
        	nFirstCutPos = getFirstCutPos(sPath, nIdx);
        	
        	if (nLastCutPos < sPath.length()-1) {
        		sPath = sPath.substring(0, nFirstCutPos) + sPath.substring(nLastCutPos+1);
        	} else {
        		sPath = sPath.substring(0, nFirstCutPos);
        	}
        	
        	nIdx = sPath.indexOf("##");
        }
        
        int n = sPath.length();
        
        if (sPath.charAt(n-1) == '/' && n > 1) sPath = sPath.substring(0, n-1);
        
        return sPath;
    }
	
    private int getFirstCutPos(String sPath, int nIdx) {
    	int nCutPos = nIdx;
    	boolean bFirstSlash = false;
    	
    	for (int i=nIdx; i>=0; i--) {
    		if (sPath.charAt(i) == '/') {
    			if (bFirstSlash == false) {
    				bFirstSlash = true;
    			} else {
    				nCutPos = i+1;
    				break;
    			}
    		}
    	}
    	
    	return nCutPos;
    }
    
    
    private String preprocessPath(String sPath) {
    	int n = sPath.length();
    	int i = 0;
    	int nContinuousDot = 0;
    	char cPath = ' ';
    	String sDot = "";
    	String sNewPath = "";
    	
    	while (i < n) {
    		cPath = sPath.charAt(i);
    		
    		if (cPath == '.') {
    			sDot = sDot + ".";
    			nContinuousDot++;
    		} else {
    			if (cPath != '/') {
    				sNewPath = sNewPath + sDot + cPath;
    			} else {
	    			if (nContinuousDot <= 1) {
	    				sNewPath = sNewPath + cPath;
	    			} else if (nContinuousDot == 2) {
	    			    sNewPath = sNewPath + "##" + cPath;
	    			} else {
	    				sNewPath = sNewPath + sDot + cPath;
	    			}
	    		}
    			
    			nContinuousDot = 0;
    			sDot = "";
    		}
    		
    		i++;
    	}
    	
    	if (nContinuousDot == 2) {
    		sNewPath = sNewPath + "##";
    	} else if (nContinuousDot > 2) {
    		sNewPath = sNewPath + sDot;
    	}
    	
    	return sNewPath;
    }
*/    
    
}
