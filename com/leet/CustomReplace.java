/*
Implement the customized replace() method of String

*/

class CustomReplace {

    public String customReplace(String str, String from, String to) {
	    String[] sarr = str.split(from);
	    boolean bLast = true;
	    
	    for (int i=str.length()-from.length(), j=0; i <= str.length()-1; i++, j++) {
	        if (str.charAt(i) != from.charAt(j)) {
				bLast = false;
				break;
		    }
	    }	
	    
	    StringBuilder sb = new StringBuilder();
	    
	    for (int i=0; i<sarr.length-1; i++) {
		    sb.append(sarr[i]).append(to);
		}
		
		sb.append(sarr[sarr.length-1]);
		
		if (bLast) sb.append(to);
		
		return sb.toString();
	}   

}

