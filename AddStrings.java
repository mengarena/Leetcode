package com.leet;

//Given two non-negative numbers num1 and num2 represented as string, return the sum of num1 and num2.
//
//Note:
//
//The length of both num1 and num2 is < 5100.
//Both num1 and num2 contains only digits 0-9.
//Both num1 and num2 does not contain any leading zero.
//You must not use any built-in BigInteger library or convert the inputs to integer directly.

public class AddStrings {

	public AddStrings() {
		// TODO Auto-generated constructor stub
	}

    
	//ACC: 40ms
    public String addStrings(String num1, String num2) {
        if (num1 == null || num1.length() == 0) return num2;
        if (num2 == null || num2.length() == 0) return num1;
        
        int digit1, digit2;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        int n1 = num1.length()-1;
        int n2 = num2.length()-1;
        
        while (n1 >= 0 && n2 >= 0) {
            digit1 = num1.charAt(n1) - '0';
            digit2 = num2.charAt(n2) - '0';
            
            sb.insert(0, (digit1+digit2+carry) % 10);
            
            carry = (digit1 + digit2 + carry) / 10;
            
            n1--;
            n2--;
        }
        
        if (n1 >= 0) {
            while (n1 >= 0) {
                digit1 = num1.charAt(n1) - '0';
                sb.insert(0, (digit1+carry) % 10);
                carry = (digit1 + carry) / 10;
                n1--;
            }
        } else if (n2 >= 0) {
            while (n2 >= 0) {
                digit2 = num2.charAt(n2) - '0';
                sb.insert(0, (digit2+carry) % 10);
                carry = (digit2 + carry) / 10;
                n2--;
            }            
        }
        
        if (carry > 0) sb.insert(0, carry);
        
        return sb.toString();
    }
}
