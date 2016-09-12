package com.leet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). 
//Given some queries, return the answers. If the answer does not exist, return -1.0.
//
//Example:
//Given a / b = 2.0, b / c = 3.0. 
//queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
//return [6.0, 0.5, -1.0, 1.0, -1.0 ].
//
//The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , 
//where equations.size() == values.size(), and the values are positive. 
//This represents the equations. Return vector<double>.
//
//According to the example above:
//
//equations = [ ["a", "b"], ["b", "c"] ],
//values = [2.0, 3.0],
//queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
//
//The input is always valid. 
//You may assume that evaluating the queries will result in no division by zero and there is no contradiction.

//Google
public class EvaluateDivision {

	public EvaluateDivision() {
		// TODO Auto-generated constructor stub
	}


	//ACC: 6ms
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        if (equations == null || equations.length == 0 || equations[0].length != 2
        || values == null || values.length == 0 || equations.length != values.length || queries == null 
        || queries.length == 0 || queries[0].length != 2) {
            double[] ret = new double[1];
            ret[0] = -1.0;
            return ret;
        }
        
        int n = equations.length;
        int m = queries.length;
        double[] retarr = new double[m];
        int i;
        
        Arrays.fill(retarr, -1.0);
        
        Set<String> setVar = new HashSet<String>();
        
        for (i=0; i<n; i++) {
            setVar.add(equations[i][0]);
            setVar.add(equations[i][1]);
        }
        
        for (i=0; i<m; i++) {
            if (!setVar.contains(queries[i][0]) || !setVar.contains(queries[i][0])) continue;
            retarr[i] = helper(equations, values, n, queries[i][0], queries[i][1], new HashSet<Integer>());
        }
        
        return retarr;
    }
    
    //setVar remember which equations has been used in the chain
    private double helper(String[][] equations, double[] values, int n, String var1, String var2, Set<Integer> setVar) {
        double ret = -1;
        int i;
        
        for (i=0; i<n; i++) {
            if (var1.equals(equations[i][0]) && var2.equals(equations[i][1])) return values[i];
            if (var2.equals(equations[i][0]) && var1.equals(equations[i][1])) return 1.0/values[i];
        }
        
        for (i=0; i<n; i++) {
            if (!setVar.contains(i) && var1.equals(equations[i][0])) {
                setVar.add(i);
                double tmp = values[i]*helper(equations, values, n, equations[i][1], var2, setVar);
                if (tmp > 0) return tmp;
                setVar.remove(i);
            }
            
            if (!setVar.contains(i) && var1.equals(equations[i][1])) {
                setVar.add(i);
                double tmp = 1.0/values[i]*helper(equations, values, n, equations[i][0], var2, setVar);
                if (tmp > 0) return tmp;
                setVar.remove(i);
            }
        }
        
        return ret;
    }
	
}

