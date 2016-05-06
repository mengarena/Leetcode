package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. 
//All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
//
//Note:
//If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. 
//		For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
//All airports are represented by three capital letters (IATA code).
//You may assume all tickets form at least one valid itinerary.
//
//Example 1:
//tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
//Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
//
//Example 2:
//tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
//Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.

//Google
public class ReconstructItinerary {

	public ReconstructItinerary() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//String[][] tickets = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
		//String[][] tickets = {{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL", "JFK"}, {"ATL","SFO"}};
		//String[][] tickets = {{"JFK", "KUL"}, {"JFK", "NRT"}, {"NRT", "JFK"}};  //Expected output:  ["JFK","NRT","JFK","KUL"]  
		//String[][] tickets = {{"JFK", "ATL"}, {"ATL", "JFK"}};
		String[][] tickets = { {"TIA","AUA" }, {"BNE","CNS" }, {"CBR","ADL" }, {"ADL","TIA" }, {"JFK","CNS" }, {"TIA","AUA" }, {"AXA","ADL" }, {"AUA","ADL" }, {"EZE","TIA" }, {"ANU","CNS" }, {"AUA","BNE" }, {"AXA","CNS" }, {"ADL","CBR" }, {"BNE","JFK" }, {"AUA","CNS" }, {"AUA","AXA" }, {"CNS","AUA" }, {"BNE","ANU" }, {"ADL","AXA" }, {"AUA","JFK" }, {"TIA","CBR" }, {"CBR","AXA" }, {"AXA","CNS" }, {"ADL","BNE" }, {"BNE","AXA" }, {"CNS","TIA" }, {"JFK","CBR" }, {"CNS","EZE" }, {"JFK","AUA" }, {"CBR","ADL" }, {"ADL","AUA" }, {"AUA","AXA" }, {"BNE","CBR" }, {"AXA","CBR" }, {"CBR","BNE" }, {"TIA","CNS" }, {"AXA","ANU" }, {"CBR","SYD" }, {"TIA","BNE" }, {"ADL","CNS" }, {"CNS","TIA" }, {"AUA","CBR" }, {"ANU","BNE" }, {"AUA","AXA" }, {"CBR","BNE" }, {"CNS","ADL" }, {"AXA","TIA" }, {"AXA","JFK" }, {"AXA","CNS" }, {"JFK","BNE" }, {"EZE","JFK" }, {"BNE","EZE" }, {"CBR","AXA" }, {"TIA","ADL" }, {"CBR","JFK" }, {"AUA","CBR" }, {"CBR","EZE" }, {"BNE","AXA" }, {"AXA","CBR" }, {"AUA","AXA" }, {"CBR","TIA" }, {"CBR","BNE" }, {"CNS","ANU" }, {"BNE","AXA" }, {"BNE","AXA" }, {"TIA","BNE" }, {"JFK","CBR" }, {"CNS","CBR" }, {"CNS","AUA" }, {"CNS","TIA" }, {"AXA","CNS" }, {"CNS","JFK" }, {"EZE","AUA" }, {"CNS","ADL" }, {"AXA","CBR" }, {"JFK","BNE" }, {"AXA","BNE" }, {"JFK","TIA" }, {"AUA","AXA" }, {"CBR","AUA" }, {"BNE","CNS" }, {"BNE","CNS" }, {"ANU","AXA" }, {"AUA","CBR" }, {"CBR","AUA" }, {"AXA","CBR" }, {"TIA","AUA" }, {"TIA","JFK" }, {"EZE","AXA" }, {"JFK","AUA" }, {"ADL","TIA" }, {"CNS","AUA" }, {"CBR","JFK" }, {"JFK","EZE" }, {"AXA","CBR" } };		
		List<String> lstItinerary = findItinerary(tickets);
		for (String sAirPort:lstItinerary) System.out.print(sAirPort + ",");
	}
	
	
	//Strategy:  Convert String Airport code into integer
	//1. Convert string to integer for each airport (saved in hmAirport)
	//2. Organize the tickets (each airport maps to its destinations in hmTickets)
	//3. Find the itinerary. From the start airport, try each of destination (in order) as next airport to see whether it could complete the itinerary; if yes, this will be the result
    public List<String> findItinerary(String[][] tickets) {
        List<String> lstItinerary = new ArrayList<String>();
        if (tickets == null || tickets.length == 0 || tickets[0].length == 0) return lstItinerary;
        
        HashMap<Integer, List<Integer>> hmTickets = new HashMap<Integer, List<Integer>>();   //Each airport to its destination, destionation is ASC ordered.
        HashMap<Integer, String> hmAirport = new HashMap<Integer, String>();  //String airport is converted into integer
        
        int nItLen = tickets.length + 1;
        
        int nAirportJFK = getIntAirport("JFK");
        int n = tickets.length;
        int i;
        
        //Convert the ticket information into integers
        for (i=0; i<n; i++) {
    		String sAirportStart = tickets[i][0];
    		String sAirportEnd = tickets[i][1];
    		
    		int nAirportStart = getIntAirport(sAirportStart);
    		int nAirportEnd = getIntAirport(sAirportEnd);
    		
    		if (!hmAirport.containsKey(nAirportStart)) hmAirport.put(nAirportStart, sAirportStart);
    		if (!hmAirport.containsKey(nAirportEnd)) hmAirport.put(nAirportEnd, sAirportEnd);
    		
    		if (!hmTickets.containsKey(nAirportStart)) {
    			List<Integer> lstAirport = new ArrayList<Integer>();
    			lstAirport.add(nAirportEnd);
    			hmTickets.put(nAirportStart, lstAirport);
    		} else {
    			List<Integer> lstAirport = hmTickets.get(nAirportStart);
    			int nInsertPos = getInsertPos(lstAirport, nAirportEnd);
    			lstAirport.add(nInsertPos, nAirportEnd);
    			hmTickets.replace(nAirportStart, lstAirport);
    		}
    		
        }
        
        //Organize the tickets
        List<Integer> lstIt = getItinerary(hmTickets, nAirportJFK, nItLen);
        
        for (Integer nAirport:lstIt) {
        	lstItinerary.add(hmAirport.get(nAirport));
        }
        
        return lstItinerary;
    }
	
    
    private List<Integer> getItinerary(HashMap<Integer, List<Integer>> hmTickets, int nStartAirport, int nItLen) {
    	List<Integer> lstIt = new ArrayList<Integer>();  	    	
    	
    	if (nItLen == 1) {
    		lstIt.add(nStartAirport);
    		return lstIt;
    	}

    	List<Integer> lstNextAirportBack = hmTickets.get(nStartAirport);   //Possible Destinations from nStartAirport
    	
    	if (lstNextAirportBack == null || lstNextAirportBack.isEmpty()) {
    		lstIt.add(nStartAirport);
    		return lstIt;
    	}

    	int nCnt = lstNextAirportBack.size();
    	
    	//Try each of its possible next airport, to see whether from there, it could complete the itinerary
    	for (int i=1; i<=nCnt; i++) {
    		HashMap<Integer, List<Integer>> hmTicketsBack = new HashMap<Integer, List<Integer>>(hmTickets);  //Deep copy of the hashmap
    		List<Integer> lstNextAirport = new ArrayList<Integer>(hmTickets.get(nStartAirport));  //Deep copy of the list, otherwise, 
    		                                                                                      //next remove operation will affect the hmTicketsBack
    		
    		int nNextAirport = lstNextAirport.remove(i-1);
    		
    		hmTickets.replace(nStartAirport, lstNextAirport);
    		List<Integer> lstNextNextAirport = getItinerary(hmTickets, nNextAirport, nItLen-1);
    		
    		if (lstNextNextAirport.size() == nItLen-1) {   //this one could complete this itinerary
    			lstIt = new ArrayList<Integer>(lstNextNextAirport);
    			lstIt.add(0, nStartAirport);
    			break;
    		}
    		
    		//Current next airport could not complete the itinerary, need to try another next airport of nStartAirport
    		hmTickets = new HashMap<Integer, List<Integer>>(hmTicketsBack);  //Resort the hashmap for next round
    	}
    	    	
    	return lstIt;   //If none of the next airports of nStartAirport could complete the Itinerary, this will be empty.
    }
    
    
    private int getInsertPos(List<Integer> lstNumber, int nVal) {
    	int i = 0;
    	int j = lstNumber.size()-1;
    	int nMid;
    	
    	while (i <= j) {
    		nMid = (i+j)/2;
    		
    		if (lstNumber.get(nMid) >= nVal) {
    			j = nMid - 1;
    		} else {
    			i = nMid + 1;
    		}
    	}
    	
    	return i;
    }
    
    //Convert String Airport code into integer
    public int getIntAirport(String sAirPort) {
    	int nRet = 0;
    	
    	for (int i=0; i < sAirPort.length(); i++) {
    		nRet = nRet*26 + sAirPort.charAt(i) - 'A';     //Pay attention here:  multiply by 26
    	}
    	
    	return nRet;
    }
    
}
