package calculatorLogic;

import java.util.Stack;

public class BusinessLogic implements CalculatorInterface {

	public double add(double a,	double b) {
		return a + b;
	}
	
	//	method to subtract two numbers
    public double sub(double a,	double b) {
    	return a - b;
    }
    
    //    method to multiply two numbers
    public double mul(double a,	double b) {
    	return a * b;
    }
    
    //    method to divide two numbers
    public double div(double a,	double b) {
    	return a / b;
    }

	public double solve(String input) {
		char[] tokens = input.toCharArray();
		
		// Stack for numbers
        Stack<Double> values = new Stack<Double>();

        // Stack for Operators
        Stack<Character> ops = new Stack<Character>();

	    for (int i = 0; i < tokens.length; i++) {
	    	
	    	// Current token is a number, push it to stack for numbers
	    	if (tokens[i] >= '0' && tokens[i] <= '9') {
	    		StringBuffer sbuf = new StringBuffer();
	    		
	    		// There may be more than one digits in number
	    		while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
	    			sbuf.append(tokens[i++]);
	    		
	    		values.push(Double.parseDouble(sbuf.toString()));
	    	} else if (tokens[i] == '(') {
	    		ops.push(tokens[i]);
	    	} else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                	values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                
                ops.pop();
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                	values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                 
                ops.push(tokens[i]);
            }
	    }

	    // Entire expression has been parsed at this point, apply remaining ops to remaining values
	    while (!ops.empty())
	    	values.push(applyOp(ops.pop(), values.pop(), values.pop()));
	
	    // Top of 'values' contains result, return it
	    return values.pop();
	}
	  
	public static boolean hasPrecedence(char op1, char op2) {
		if (op2 == '(' || op2 == ')')
			return false;
	    if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
	        return false;
	    else
	        return true;
	}
	  
	public Double applyOp(char op,  Double b, Double a) {
	    switch (op) {
	      	case '+':
	      		return add(a,b);  // calling the business class 
	      	case '-':
	      		return sub(a, b);
	      	case '*':
	      		return mul(a, b);
	      	case '/':
	      		if (b == 0)
	      			throw new UnsupportedOperationException("Cannot divide by zero");
	      		return div(a, b);
	    }
	    return 0.00;
	}
}
