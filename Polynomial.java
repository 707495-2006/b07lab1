public class Polynomial{
	private double[] coefficients;
	
	public Polynomial(){
		coefficients = new double[]{0};
	}
	
	public Polynomial(double[] coefficients){
		this.coefficients = coefficients;
	}
	
	public Polynomial add(Polynomial otherPolynomial){
		int len_of_calling = this.coefficients.length;
		int len_of_other = otherPolynomial.coefficients.length;
		int max = 0;
		
		if(len_of_calling > len_of_other){
			max = len_of_calling;
		}
		else{
			max = len_of_other;
		}
		double[] newPoly = new double[max];
		
		for(int i = 0; i<max; i++){
			if(i<len_of_calling && i<len_of_other){
				newPoly[i] = this.coefficients[i] + otherPolynomial.coefficients[i];
			}
			else if(i>=len_of_calling){
				newPoly[i] = otherPolynomial.coefficients[i];
			}
			else{
				newPoly[i] = this.coefficients[i];
			}
		}
		Polynomial p = new Polynomial(newPoly);
		return p;
	}
	public double evaluate(double x){
		int length = this.coefficients.length;
		double result=0;
		for(int i=0; i<length; i++){
			result+= this.coefficients[i]*Math.pow(x,i);
		}
	    return result;
	}
	
	public boolean hasRoot(double x){
		if(evaluate(x) == 0.0){
			return true;
		}
		else{
			return false;
		}
	}
}