package com.leet;

import java.util.ArrayList;
import java.util.List;

//Compare two version numbers version1 and version2.
//If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
//
//You may assume that the version strings are non-empty and contain only digits and the . character.
//The . character does not represent a decimal point and is used to separate number sequences.
//For instance, 2.5 is not "two and a half" or "half way to version three", 
//it is the fifth second-level revision of the second first-level revision.
//
//Here is an example of version numbers ordering:
//
//0.1 < 1.1 < 1.2 < 13.37

public class CompareVersionNumbers {

	public CompareVersionNumbers() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String sVer1 = "1.0";
		String sVer2 = "1.10";
		
		//System.out.println(sVer1.compareTo(sVer2));
		
		//List<String> lstVersionRanges = getVersionParts("1.10");
		//for (String sVal:lstVersionRanges) System.out.println(sVal);
		System.out.println("Version order: " + compareVersion(sVer1, sVer2));
 	}
	
	//Accepted, better version
    public int compareVersion(String version1, String version2) {
        String[] sarrVer1 = version1.split("\\.");
        String[] sarrVer2 = version2.split("\\.");
        int nFieldCnt1 = sarrVer1.length;
        int nFieldCnt2 = sarrVer2.length;
        int nCommFieldCnt = Math.min(nFieldCnt1, nFieldCnt2);
        int i;
        int nRet = 0;
        
        for (i=0; i<nCommFieldCnt; i++) {
            int nVer1 = Integer.valueOf(sarrVer1[i]).intValue();
            int nVer2 = Integer.valueOf(sarrVer2[i]).intValue();
            
            if (nVer1 > nVer2) {
                return 1;
            } else if (nVer1 < nVer2) {
                return -1;
            }
        }
        
        //Pay attention,  1.0 = 1,   1.0.0.0 = 1
        if (nFieldCnt1 > nFieldCnt2) {
            for (i=nCommFieldCnt; i<nFieldCnt1; i++) {
                if (Integer.valueOf(sarrVer1[i]).intValue() > 0) return 1;
            }

        } else if (nFieldCnt1 < nFieldCnt2) {
            for (i=nCommFieldCnt; i<nFieldCnt2; i++) {
                if (Integer.valueOf(sarrVer2[i]).intValue() > 0) return -1;
            }
        }
        
        return nRet;
    }

	
	
/* Accepted	
	//version strings are non-empty
    public int compareVersion(String version1, String version2) {

    	List<String> lstVer1Ranges = getVersionParts(version1);
    	List<String> lstVer2Ranges = getVersionParts(version2);
     	
    	int nLen1 = lstVer1Ranges.size();
    	int nLen2 = lstVer2Ranges.size();
    	if (nLen1 < nLen2) {
    		for (int i=1; i<=nLen2-nLen1; i++) lstVer1Ranges.add("0");
    	} else if (nLen1 > nLen2) {
    		for (int i=1; i<=nLen1-nLen2; i++) lstVer2Ranges.add("0");
    	}
    	
    	String sVer1, sVer2;
    	
    	sVer1 = lstVer1Ranges.get(0);
    	sVer2 = lstVer2Ranges.get(0);
    	
    	if (sVer1.length() > sVer2.length()) return 1;
    	if (sVer1.length() < sVer2.length()) return -1;
 
    	int nMajorOrder =  sVer1.compareTo(sVer2);
    	if (nMajorOrder > 0) {
    		return 1;
    	} else if (nMajorOrder < 0) {
    		return -1;
    	}
    	
    	for (int i=1; i<lstVer1Ranges.size(); i++) {
        	sVer1 = lstVer1Ranges.get(i);
        	sVer2 = lstVer2Ranges.get(i);
    		
	    	if (sVer1.length() > sVer2.length()) return 1;
	    	if (sVer1.length() < sVer2.length()) return -1;
	    	int nMinorOrder = sVer1.compareTo(sVer2);
	    	if (nMinorOrder > 0) {
	    		return 1;
	    	} else if (nMinorOrder < 0) {
	    		return -1;
	    	}
    	}
    	    			
    	return 0;
    }

    
    public List<String> getVersionParts(String sVersion) {
    	List<String> lstVersionParts = new ArrayList<String>();
    	int n = sVersion.length();
    	int nPointIdx = -1;
    	int nStartPos = 0;
    	int i;
    	
    	if (n == 0) return lstVersionParts;
    	if (n == 1) {
    		if (sVersion.charAt(0) == '.') {
    			return lstVersionParts;
    		} else {
    			lstVersionParts.add(sVersion);
    			return lstVersionParts;
    		}
    	}
    	
    	nPointIdx = sVersion.indexOf('.');
    	while (nPointIdx != -1) {
    		lstVersionParts.add(sVersion.substring(nStartPos, nPointIdx));
    		nStartPos = nPointIdx+1;
    		nPointIdx = sVersion.indexOf('.', nStartPos);
    	}
    	
    	if (nPointIdx == -1) lstVersionParts.add(sVersion.substring(nStartPos));
    	
    	for (int j=0; j<lstVersionParts.size(); j++) {
    		
    		if (lstVersionParts.get(j).length() == 0) {
    			lstVersionParts.set(j, "0");
    		} else {
    		
    			String sVerMajor = lstVersionParts.get(j);
    			String sVerMajorNew = sVerMajor;
    	    	int nStartIdx = -1;
    	    	for (i=0; i<sVerMajor.length(); i++) {
    	    		if (sVerMajor.charAt(i) != '0') {
    	    			nStartIdx = i;
    	    			break;
    	    		} 
    	    	}
    	    		
    	    	if (nStartIdx == -1) {
    	    		sVerMajorNew = "0";
    	    	} else {
    	    		sVerMajorNew = sVerMajor.substring(nStartIdx);
    	    	}
   			
    	    	lstVersionParts.set(j, sVerMajorNew);
    		}
    	}	
    	
    	return lstVersionParts;
    }
*/    
    
}
