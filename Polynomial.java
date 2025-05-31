import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;

public class Polynomial{
	private double[] coefficients;
	private int[] exponents;
	
	public Polynomial(){
		coefficients = new double[0];
		exponents = new int[0];
	}
	
	public Polynomial(double[] coefficients, int[] exponents){
		this.coefficients = coefficients;
		this.exponents = exponents;
	}
	
	public Polynomial add(Polynomial otherPolynomial){
		double[] otherCoeffscopy = otherPolynomial.coefficients.clone();
		int[] otherExponentscopy = otherPolynomial.exponents.clone();
		
		int len_of_calling = this.coefficients.length;
		int len_of_other = otherPolynomial.coefficients.length;
		int max = len_of_calling + len_of_other;
		
		double[] sumCoeffs = new double[max];
		int[] sumExponents = new int[max];
		int found = -1;
		int position = 0;

		for(int i=0; i<len_of_calling; i++){
			for(int j=0; j<len_of_other; j++){
				if(this.exponents[i] == otherExponentscopy[j]){
					double sum = this.coefficients[i] + otherPolynomial.coefficients[j];
					sumCoeffs[position] = sum;
					sumExponents[position] = this.exponents[i];
					otherCoeffscopy[j] = 0;
					otherExponentscopy[j] = -1;
					found = 0; 
				    position++;
					break;
					
				}
			}
			if(found==-1){
				sumCoeffs[position] = this.coefficients[i];
				sumExponents[position] = this.exponents[i];
				position++;
			}
			found = -1;
		}

		for(int k=0; k<len_of_other; k++){
			if(otherCoeffscopy[k]!=0){
				sumCoeffs[position]= otherCoeffscopy[k];
				sumExponents[position] = otherExponentscopy[k];
				position++;
			}
		}
		if(position==0){
			return new Polynomial();
		}
		double[] finalCoeff = new double[position];
		int[] finalExponent = new int[position];
		int y=0;
		for(int i=0; i<position; i++){
			if(sumCoeffs[i] != 0){
				finalCoeff[y] = sumCoeffs[i];
				finalExponent[y]=sumExponents[i];
				y++;
			}
		}
		Polynomial newPoly = new Polynomial(finalCoeff, finalExponent);
		return newPoly;
	}
	
	public double evaluate(double x){
		int length = this.coefficients.length;
		double result = 0;
		for(int i=0; i<length; i++){
			result+= this.coefficients[i]*Math.pow(x, this.exponents[i]);
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
	
	public Polynomial multiply(Polynomial other){
		int len = this.coefficients.length;
		int len_other = other.coefficients.length;
		int size = len*len_other;
		
		double[] c = new double[size];
		int[] e = new int[size];
		int position=0;
		
		for(int i=0; i<len; i++){
			for(int j=0; j<len_other; j++){
				c[position]= this.coefficients[i]*other.coefficients[j];
				e[position] = this.exponents[i]+other.exponents[j];
				position++;
			}
		}
		
		double[] finalC = new double[position]; 
		int[] finalE = new int[position];
		int count =0;
		
		for(int i=0; i<position; i++){
			int exponent = e[i];
			double coeff = c[i];
		    for(int j=i+1; j<position; j++){
				if(e[j]==exponent){
					coeff+=c[j];
					c[j]=0;
				}
			}
			if(coeff!=0){
				finalC[count]=coeff;
				finalE[count] = exponent;
				count++;
			}
		}
		
		double[] trimmedC = new double[count]; 
		int[] trimmedE = new int[count];
		
		for(int i=0; i<count; i++){
			trimmedC[i]= finalC[i];
			trimmedE[i]= finalE[i];
		}
		
		Polynomial multiplied = new Polynomial(trimmedC, trimmedE);
		return multiplied;
	}
	
	public Polynomial(File file) throws Exception{
		Scanner s = new Scanner(file);
		String line = s.nextLine();
		s.close();
		
		String[] terms = line.split("(?=[+-])");
		int len = terms.length;
		double[] c = new double[len];
		int[] e = new int[len];
		
		for(int i=0; i<len; i++){
			String term = terms[i];
			if(term.contains("x")){
				String[] sections = term.split("x");
				c[i]=Double.parseDouble(sections[0]);
				e[i]=Integer.parseInt(sections[1]);
			}
			else{
				c[i]=Double.parseDouble(term);
				e[i]=0;
			}
		}
		
		this.coefficients = c;
        this.exponents = e;
	}
	
	
	public void saveToFile(String filename) throws IOException{
		FileWriter myWriter = new FileWriter(filename);
		int len = this.coefficients.length;
		
		for(int i=0; i<len; i++){
			double coeff = this.coefficients[i];
			int exponent = this.exponents[i];
			if(coeff<0 || i==0){
				myWriter.write("" + coeff);
			}
			else{
				myWriter.write("+" + coeff);
			}
			if(exponent!=0){
				myWriter.write("x" + exponent);
			}
		}
		myWriter.close();
	}
		
	}


