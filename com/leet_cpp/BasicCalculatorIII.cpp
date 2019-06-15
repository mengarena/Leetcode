/*

772. Basic Calculator III

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), 
the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , 
open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. 
All intermediate results will be in the range of [-2147483648, 2147483647].

Some examples:

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 
Note: Do not use the eval built-in library function.

Hard

Google, Uber, Amazon
*/


class Solution {
public:
    struct Node {
        int type;    // 0: number, 1: operator, 2: '(' or ')'
        long num;
        char symbol;   // '+', '-', '*', '/', '(', ')'
        Node(int t) { type = t; }
    };
    
    // In stack,expression "a-b" stores as two elements a and -b
    void backword(stack<Node>& s, long num) {
        if (!s.empty() && s.top().type == 1) {
            if (s.top().symbol == '+') {
                s.pop();
                Node tmp(0);
                tmp.num = num;
                s.push(tmp);
            } else if (s.top().symbol == '-') {
                s.pop();
                Node tmp(0);
                tmp.num = -1*num;
                s.push(tmp);
            } else if (s.top().symbol == '*') {
                s.pop();
                Node tmp = s.top();
                s.pop();
                tmp.num = tmp.num*num;
                s.push(tmp);
            } else if (s.top().symbol == '/') {
                s.pop();
                Node tmp = s.top();
                s.pop();
                tmp.num = tmp.num/num;
                s.push(tmp);
            }
        } else {
            Node tmp(0);
            tmp.num = num;
            s.push(tmp);
        }
    }
    
    // 98%
    int calculate(string s) {
        stack<Node> stk;
        string ss;
        for (auto c:s) ss.push_back(c);
        
        long num = 0;
        int prevType = -1;
        
        for (int i=0; i<ss.length(); ++i) {            
            if (isdigit(ss[i])) {
                num = num*10 + ss[i] - '0';
                prevType = 0;
                continue;
            }
            
            if (ss[i] == '*' || ss[i] == '/' || ss[i] == '+' || ss[i] == '-') {
                if (prevType == 0) {
                    backword(stk, num);
                }
                
                num = 0;
                Node tmp(1);
                tmp.symbol = ss[i];
                stk.push(tmp);
                prevType = 1;
                continue;
            }
            
            if (ss[i] == '(') {
                Node tmp(2);
                tmp.symbol = ss[i];
                stk.push(tmp);
                prevType = 2;
            } else if (ss[i] == ')') {
                if (prevType == 0) {
                    backword(stk, num);
                }
                
                num = 0;
                int tmpNum = 0;
                while (!stk.empty() && (stk.top().type != 2 || stk.top().symbol != '(')) {
                    Node tmp = stk.top();
                    stk.pop();
                    tmpNum += tmp.num;
                }
                
                if (!stk.empty() && stk.top().type == 2 && stk.top().symbol == '(') {
                    stk.pop();
                    backword(stk, tmpNum);
                }
                
                prevType = 2;
            }
        }
        
        if (prevType == 0) {
            backword(stk, num);
        }
        
        long finalNum = 0;
        while (!stk.empty()) {
            finalNum += stk.top().num;
            stk.pop();
        }
        
        return finalNum;
    }


};
