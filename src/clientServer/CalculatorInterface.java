package clientServer;

public interface CalculatorInterface {
	//finds sum
	public double add(double a,	double b);
	
    //finds difference
	public double sub(double a,	double b);
    
	//finds product
	public double mul(double a,	double b);
    
	//finds quotient
	public double div(double a,	double b);
	
	//accepts and solves the infix expression
	public double solve(String input);
	
}