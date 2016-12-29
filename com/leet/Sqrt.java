/*
Implement sqrt, accuracy goes to digit after point

*/

class Sqrt {

    //This WORK !
    //Here attention, to get n digits after point, we need to make the original x multiplied by 10^(2n)
    //For example, to get 3 digits, we need to increase x to x*1000000
    //Reason:  sqrt(a*b) = sqrt(a)*sqrt(b)
    //So sqrt(a*10^6) = sqrt(a)*sqrt(10^6) = sqrt(a)*10^3
    public static double mySqrt(int x, int digit) {
	    long xx = x*(long)Math.pow(10, digit*2);
	    
	    return mySqrt(xx)*1.0/(long)Math.pow(10, digit);
	}   

    public static long mySqrt(long x) {
	    if (x <= 0) return 0;
	    
	    long i = 1;
	    long j = x;
	    long mid;
	    
	    while (i<=j) {
		    mid = i + (j-i)/2;
		    
		    if (mid == 0) {
			    i = mid+1;
			} else if (mid > x/mid) {
			    j = mid-1;
			} else if (mid < x/mid) {
				i = mid+1;
			} else {
				return mid;
			}
		}
		
		return j;
    }
       
       
       
       
    //This method does NOT work
    //Reason: in the helper function, the digit tail after point is random (hard to control),
    //For example, sometime, expect mid = 2.8, but actually it might be 2.80000000000003, 
    //which might make the mid*mid < (or >) x gives result different from expected 
    public static double mySqrt(int x) {
	    if (x <= 0) return 0;
	    
	    int i = 1;
	    int j = x;
	    int mid;
	    double ret = 0.0;
	    int intRet = -1;
	    
	    while (i<=j) {
		    mid = i + (j-i)/2;
		    
		    if (mid == 0) {
			    i = mid+1;
			} else if (mid > x/mid) {
			    j = mid-1;
			} else if (mid < x/mid) {
				i = mid+1;
			} else {
			    intRet = mid;
			    break;
			}
		}
		
		if (intRet != -1) return intRet;
        
        double level = 0.1;
        double ans = j*1.0;
        
        for (i=0; i<5; i++) {
			
			ans = mySqrtHelper(x*1.0, ans, level);
			
		    level = level/10;
		}
		
		return ans;
	}   
        
    private static double mySqrtHelper(double x, double base, double level) {
	    double i = base;
	    double j = base + level*9;
	    double mid;
	    
	    while (i <= j) {
			//mid = i + (j-i)/2;
			
		    double diff = j-i;
		    int count = (int)Math.round(diff/level); //(int)(diff/level);
            count = count/2;
           	
           	mid = i + level*count;
           		    
		    if (mid == 0.0) {
			    i = mid+level;
			} else if (mid*mid > x) {
			    j = mid-level;
			} else if (mid*mid < x) {
			    i = mid+level;
			} else {
			    return mid;
			}
		}
		
		return j;
	}    

}

