package com.leet;

//Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. 
//You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F 
//		(T and F represent True and False respectively).
//
//Note:
//
//The length of the given string is ¡Ü 10000.
//Each number will contain only one digit.
//The conditional expressions group right-to-left (as usual in most languages).
//The condition will always be either T or F. That is, the condition will never be a digit.
//The result of the expression will always evaluate to either a digit 0-9, T or F.
//
//Example 1:
//
//Input: "T?2:3"
//
//Output: "2"
//
//Explanation: If true, then result is 2; otherwise result is 3.
//
//Example 2:
//
//Input: "F?1:T?4:5"
//
//Output: "4"
//
//Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
//
//             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
//          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
//          -> "4"                                    -> "4"
//
//Example 3:
//
//Input: "T?T?F:5:3"
//
//Output: "F"
//
//Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
//
//             "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
//          -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
//          -> "F"                                    -> "F"
//
//          Hide Company Tags

public class TernaryExpressionParser {

	public TernaryExpressionParser() {
		// TODO Auto-generated constructor stub
	}

	
	public void run() {
		String expression = "T?T?F:5:3";
		
		String sRet = parseTernary(expression);
		
		System.out.println(sRet);
	}
	
	
	//ACC: Snapchat
    public String parseTernary(String expression) {
        if (expression == null || expression.isEmpty()) return "";
        return parseHelper(expression);
    }
    
    private String parseHelper(String sExp) {
        int idxLastQue = sExp.lastIndexOf('?');
        
        if (idxLastQue == -1) return sExp;
        
        int idxCondition = getCondition(sExp, idxLastQue);
        
        int idxColon = sExp.indexOf(':', idxLastQue);
        
        String sNewExp = "";
        
        String sLeftCond = sExp.substring(idxLastQue+1, idxColon);
        
        int idxRightEnd = getRightCondition(sExp, idxColon);
        
        String sRightCond = sExp.substring(idxColon+1, idxRightEnd);
        
        String sRemained = sExp.substring(idxRightEnd);
        
        if (sExp.charAt(idxCondition) == 'T') {
           
            sNewExp = sExp.substring(0, idxCondition) + sLeftCond + sRemained;
        } else {
            
            sNewExp = sExp.substring(0, idxCondition) + sRightCond + sRemained;
        }
        
        return parseHelper(sNewExp);
    }
    
    private int getCondition(String sExp, int idxQue) {
        while (idxQue >= 0 && sExp.charAt(idxQue) != 'T' && sExp.charAt(idxQue) != 'F') idxQue--;
        
        return idxQue;
    }
    
    private int getRightCondition(String sExp, int idxColon) {
        int idxRightEnd = sExp.length();
        
        int idxEnd = idxColon+1;
        
        while (idxEnd < idxRightEnd) {
            if (sExp.charAt(idxEnd) != 'T' && sExp.charAt(idxEnd) != 'F' && !Character.isDigit(sExp.charAt(idxEnd))) break;
            idxEnd++;
        }
        
        return idxEnd;
    }

}
