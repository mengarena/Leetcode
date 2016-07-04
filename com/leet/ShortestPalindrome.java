package com.leet;

//Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. 
//Find and return the shortest palindrome you can find by performing this transformation.
//
//For example:
//
//Given "aacecaaa", return "aaacecaaa".
//
//Given "abcd", return "dcbabcd".

//Google, Pocket Gems
public class ShortestPalindrome {

	public ShortestPalindrome() {
		// TODO Auto-generated constructor stub
	}

    public void run() {
        String s = "aacecaaa";
       // String s = null;
        //String s = "ab";
        //String s = "adbacecaaa";
        System.out.println(shortestPalindrome(s));
    }
    
    
    
    
    //Algorithm: based on KMP algorithm (Complexity: O(n+n))
    public String shortestPalindromeA(String s) {
        if (s == null || s.isEmpty()) return "";
        int n = s.length();
        if (n == 1) return s;
        StringBuilder sb = new StringBuilder();

        int[] next = getNext(s);
        int nLongstPalindrome = next[next.length-1];  
        //maximal length equals to the last one in next[] (the last one's position is one beyond(right) of the last one of string),
        //The question asks to operate in front of the string, so by adding "#" + s.reverse(), 
        //the last next decides the length palindrome starting from beginning
        //which equals to the maxlen between prefix substring and postfix substring in s + "#" + s.reverse()
        //Which corresponds to the longest palindrome in original string
        
        sb.append(s.substring(nLongstPalindrome));
        sb.reverse();
        sb.append(s);

        return sb.toString();
    }   

    //Refer to http://blog.csdn.net/tukangzheng/article/details/38438481
    //The relationship between next[] and the maximal-length of common prefix / postfix substring at each positions is (i.e. maxlen array []:
    //All elements in next[] shifts left by 1 will be the maxlen array)
    //So here need to calculate next[] one element beyond the last one, when shift left by 1, it will be the maxlen of common prefix/postfix substring  at the end position 
    //next[i] means by position i (not including) in string, next[i] elements has been matched between prefix and postfix
    //Prefix substring:  starting from index 0
    //Postfix substring:  ending at current position
    private int[] getNext(String s) {
    	StringBuilder sb = new StringBuilder(s);
    	sb.reverse();
    	sb.insert(0, s + "#");
    	int n = sb.length();
    	int[] next = new int[n+1];
    	next[0] = -1;
    	int k = -1;   //Pattern index
    	int j = 0;   //String Index
    	char[] carr = sb.toString().toCharArray();
    	
    	while (j < n) {
    		if (k == -1 || carr[k] == carr[j]) {
    			k++;
    			j++;
    			next[j] = k;
    		} else {
    			k = next[k];
    		}
    	}
    	
    	return next;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Manacher Algorithm  (Complexity:  O(n))
    //http://blog.csdn.net/hopeztm/article/details/7932245
    public String shortestPalindrome(String s) {
        if (s == null || s.isEmpty()) return "";
        int n = s.length();
        if (n == 1) return s;
        StringBuilder sb = new StringBuilder();

        int nLongestPldFrom0 = getLongestPalindrome(s);
                
        sb.append(s.substring(nLongestPldFrom0));
        sb.reverse();
        sb.append(s);
        
        return sb.toString();
    }   

    
    
    
    //Get the max length of palindrome starting from position 0 of s
    private int getLongestPalindrome(String s) {
    	if (s == null || s.isEmpty()) return 0;
    	int n = s.length();
    	if (n == 1) return 1;
    	StringBuilder sb = new StringBuilder();
    	int i, j;
    	int nMid = 0, nRight = 0;  //center and right border of a palindrome
    	
    	sb.append("^");
    	for (i=0; i<n; i++) sb.append("#" + s.charAt(i));
    	sb.append("#$");
    	
    	char[] carr = sb.toString().toCharArray();
    	int narrPalinLen[] = new int[carr.length];  
    	//narrPalinLen[]: Length of the half length of the palindrome centered at position i (including i), 
    	//correspondingly, the actual length of the palindrome centered at position i will be narrPalinLen[i]-1;
    	//Attention: the new string contains "#"
    	//e.g. #a#b#a#,   at position "b", palindrome[i] will be 4, so the actual length of palindrome centered at b (i.e. aba) will be palindrome[i]-1
    	
    	for (i=1; carr[i] != '$'; i++) {  //Possible center point on the right side of the center (i.e. nMid) of current palindrome
    		j = nMid - (i-nMid);  //nMid is the centered point of the longer palindrome, 
    		                      //here j will be the mirrored position of i around nMid  (j is on left of nMid;  i is on right of nMid)
    		   		
    		narrPalinLen[i] = (nRight > i) ? Math.min(narrPalinLen[j], nRight-i):1;
    		
    		//Expand narrPalinLen[i]
    		//while (i+narrPalinLen[i] < carr.length && i-narrPalinLen[i] >= 0 && carr[i+narrPalinLen[i]] == carr[i-narrPalinLen[i]]) narrPalinLen[i]++;
    		while (carr[i+narrPalinLen[i]] == carr[i-narrPalinLen[i]]) narrPalinLen[i]++;
    		
    		if (i+narrPalinLen[i] > nRight) {
    			nRight = i + narrPalinLen[i];
    			nMid = i;
    		}
    	}
    	
    	int nMaxLen = 0;
    	
    	//Find max-length palindrome starting from head
    	for (i=0; i<narrPalinLen.length; i++) {
    		if (nMaxLen < narrPalinLen[i] && i-(narrPalinLen[i]-1)<=2) nMaxLen = narrPalinLen[i];   
    		//i-(narrPalinLen[i]-1)<=2 guarantees the string should be starting from head of raw string (^#a#b....)
    	}
    	
    	return nMaxLen-1;  //reduce 1 to get the actual length of Palindrome, because of the definition of narrPalinLen     	
    }
    
    
    
    
    
    
    
    //raw version of getLongestPalindrome
    private int getLongestPalindrome_raw(String s) {
    	if (s == null || s.isEmpty()) return 0;
    	int n = s.length();
    	if (n == 1) return 1;
    	StringBuilder sb = new StringBuilder();
    	int i, j;
    	int nMid = 0, nRight = 0;  //center and right border of a palindrome
    	
    	sb.append("^");
    	for (i=0; i<n; i++) sb.append("#" + s.charAt(i));
    	sb.append("#$");
    	
    	char[] carr = sb.toString().toCharArray();
    	int narrPalinLen[] = new int[carr.length];  
    	//Length of the half length of the palindrome centered at position i (including i), 
    	//correspondingly, the actual length of the palindrome centered at position i will be narrPalinLen[i]-1;
    	//Attention: the new string contains "#"
    	//e.g. #a#b#a#,   at position "b", palindrome[i] will be 4, so the actual length of palindrome centered at b (i.e. aba) will be palindrome[i]-1
    	
    	for (i=1; carr[i] != '$'; i++) {
    		j = nMid - (i-nMid);  //nMid is the centered point of the longer palindrome, here j will be the mirrored position of i around nMid
    		   		
    		narrPalinLen[i] = (nRight > i) ? Math.min(narrPalinLen[j], nRight-i):1;
    		
    		while (i+narrPalinLen[i] < carr.length && i-narrPalinLen[i] >= 0 && carr[i+narrPalinLen[i]] == carr[i-narrPalinLen[i]]) narrPalinLen[i]++;
    		
    		if (i+narrPalinLen[i] > nRight) {
    			nRight = i + narrPalinLen[i];
    			nMid = i;
    		}
    	}
    	
    	
    	int nMaxLen = 0;
    	
    	//Find max-length palindrome
    	for (i=0; i<narrPalinLen.length; i++) {
    		if (nMaxLen < narrPalinLen[i]) nMaxLen = narrPalinLen[i];
    	}
    	
    	return nMaxLen-1;  //reduce 1 to get the actual length of Palindrome, because of the definition of narrPalinLen 
    	
    }
    
    
    
    
    
    
    
    
    
    //Correct, but exceed time limit
    public String shortestPalindromeB(String s) {
        if (s == null || s.isEmpty()) return "";
        int n = s.length();
        if (n == 1) return s;
        int i;
        char[] carr = s.toCharArray();
        StringBuilder sb = new StringBuilder(s);
        int nEndIdx = n-1;
        
        for (i=n-1; i>=0; i--) {
        	if (isPalindrome(s.substring(0, i+1))) {
        		nEndIdx = i;
        		break;
        	}
        }
        
        for (i=nEndIdx+1; i<n; i++) {
        	sb.insert(0, carr[i]);
        }
        
        return sb.toString();
    }   
    
    private boolean isPalindrome(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) return true;
        char[] carr=s.toCharArray();
        int i=0, j=s.length()-1;
        
        while (i<j) {
            if (carr[i] != carr[j]) return false;
            i++;
            j--;
        }
        
        return true;
    }
}
