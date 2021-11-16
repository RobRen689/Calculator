/**
 * Program Name:	Calculator.java
 * Purpose:			A Calculator class that holds all appropriate calculation methods
 * @author			Robert Ren, 0975676
 * Date:			Apr. 16, 2021
 */

import java.util.ArrayList;
public class Calculator
{
	//data members
	private String operand;
	private String operator;
	private boolean decimalPressed;
	private ArrayList<String> list;
	
	//no arg Constructor
	Calculator(){
		operand = "";
		operator = "";
		decimalPressed = false;
		list = new ArrayList<String>();
	}

	/**
	 * Get the operand of this object
	 * @return the operand
	 */
	public String getOperand()
	{
		return operand;
	}

	/**
	 * Sets the operand of this object
	 * @param operand - the value to set
	 */
	public void setOperand(String operand)
	{
		this.operand = operand;
	}

	/**
	 * Get the operator of this object
	 * @return the operator
	 */
	public String getOperator()
	{
		return operator;
	}

	/**
	 * Sets the operator of this object
	 * @param operator - the value to set
	 */
	public void setOperator(String operator)
	{
		this.operator = operator;
	}

	/**
	 * Get the decimalPressed of this object
	 * @return the decimalPressed
	 */
	public boolean isDecimalPressed()
	{
		return decimalPressed;
	}

	/**
	 * Sets the decimalPressed of this object
	 * @param decimalPressed - the value to set
	 */
	public void setDecimalPressed(boolean decimalPressed)
	{
		this.decimalPressed = decimalPressed;
	}
	
	//clears all members to their default value
	public void clear()
	{
		this.operand = "";
		this.operator = "";
		this.decimalPressed = false;
		this.list.clear();
	}
	
	//returns the value with the last character removed
	public String backspace(String value) throws EmptyOperandException
	{
		String newValue = "";
		
		//MODIFY so that if user passes in an "empty" value, the exception will be thrown
		if(value.length() == 3 && value.charAt(0) == '0' && value.charAt(1) == '.'
				   && value.charAt(2) == '0')
		{
			//instantiate the exception
			throw new EmptyOperandException();
		}
		else
		{
			//if value is not empty, remove the last character
			if(value != null && value.length() > 0)
			{
				newValue = value.substring(0, value.length() - 1);
			}
			//reset value to 0.0 if the last character is removed
			if(value.length() == 1)
			{
				newValue = "0.0";
			}
		}
		
		return newValue;
	}
	
	//returns the value as a percentage representation
	public String findPercentage(String value) throws EmptyOperandException
	{
		double dblValue = Double.parseDouble(value);
		double valueAsPercent = 0.0;
		
		//MODIFY so that if user passes in an "empty" value, the exception will be thrown
		if(value.length() == 3 && value.charAt(0) == '0' && value.charAt(1) == '.'
				   && value.charAt(2) == '0')
		{
			//instantiate the exception
			throw new EmptyOperandException();
		}
		else	
		{
			//convert the value to a percentage
			valueAsPercent = dblValue / 100;
		}
		
		return (valueAsPercent + "");
	}
	
	//returns the value as a negative or positive number based on the flag value
	public String togglePlusMinus(boolean flag) throws EmptyOperandException
	{
		String newOperand = "";
		
		//MODIFY so that if user passes in an "empty" value, the exception will be thrown
		if(Double.parseDouble(getOperand()) == 0.0)
		{
			//instantiate the exception
			throw new EmptyOperandException();
		}
		else
		{
			//change the string value to negative if flag value is true
			//otherwise change the value to positive
			if(flag == true)
			{
				newOperand = "-" + getOperand();
			}
			if(flag == false)
			{
				if(getOperand().charAt(0) == '-')
				{
					newOperand = getOperand().substring(1);
				}
			}
		}
		
		return newOperand;
	}
	
	//returns the squared calculated value of the passing value
	public String findSquared(String value) throws EmptyOperandException
	{
		//convert the value from string to double
		double dblValue = Double.parseDouble(value);
		double result = 0.0;
		
		//MODIFY so that if user passes in an "empty" value, the exception will be thrown
		if(value.length() == 3 && value.charAt(0) == '0' && value.charAt(1) == '.'
				   && value.charAt(2) == '0')
		{
			//instantiate the exception
			throw new EmptyOperandException();
		}
		else
		{
			//multiply the value with itself to get the squared value
			result = dblValue * dblValue;
		}
		
		return (result + "");
	}
	
	//returns the square root value representation of the passing value
	public String findSquareRoot(String value) throws EmptyOperandException
	{
		//convert the value from string to double
		double dblValue = Double.parseDouble(value);
		double result = 0.0;
		
		//MODIFY so that if user passes in an "empty" value, the exception will be thrown
		if(value.length() == 3 && value.charAt(0) == '0' && value.charAt(1) == '.'
		   && value.charAt(2) == '0')
		{
			//instantiate the exception
			throw new EmptyOperandException();
		}
		else
		{
			//calculate the square root
			result = Math.sqrt(dblValue);
		}
		
		return (result + "");
	}
	
	//build the value into the operand variable for future calculation
	public void buildOperand(String value) throws LongOperandException
	{
		//MODIFY so that if the text field exceed the text length limit, the exception will be thrown
		if(value.length() > 21)
		{
			//instantiate the exception
			throw new LongOperandException();
		}
		else
		{
			setOperand(value);
		}
	}
	
	//build the operator into the operator variable
	public void buildExpression(String value) throws EmptyOperandException
	{
		//MODIFY so that if user passes in an "empty" value, the exception will be thrown
		if(getOperand().length() == 0)
		{
			//instantiate the exception
			throw new EmptyOperandException();
		}
		else
		{
			//add the previously entered operand and the current operator to the list
			setOperator(value);
			list.add(getOperand());
			list.add(getOperator());
		}
	}
	
	//returns the calculated result from the values in the list using proper BEDMAS
	public double calculate() throws EmptyOperandException, ArithmeticException
	{
		double result = 0.0;
		
		//MODIFY so that if user passes in an "empty" value, the exception will be thrown
		if(getOperand().length() == 3 && getOperand().charAt(0) == '0' && getOperand().charAt(1) == '.'
				&& getOperand().charAt(2) == '0')
		{
			//instantiate the exception
			throw new EmptyOperandException();
		}
		else
		{
			list.add(getOperand());
			//check for the multiply or divide operator first
			//if multiply or divide found, multiply or divide the two values surrounding the operator
			//then remove those two values and the operator, and add the result into the list as a new operand
			for(int i = 0; i < list.size(); i++)
			{
				if(list.get(i) == "x")
				{
					result = Double.parseDouble(list.get(i - 1)) * Double.parseDouble(list.get(i + 1));
					list.remove(i-1);
					list.remove(i-1);
					list.remove(i-1);
					list.add((i-1), (result+""));
					i = 0;
				}
				else if(list.get(i) == "/")
				{
					//MODIFY so that if the denominator is zero, the exception will be thrown
					if(Double.parseDouble(list.get(i + 1)) == 0)
					{
						//instantiate the exception
						throw new ArithmeticException("Error. Division by zero cannot be done.");
					}
					else
					{
						result = Double.parseDouble(list.get(i - 1)) / Double.parseDouble(list.get(i + 1));
						list.remove(i-1);
						list.remove(i-1);
						list.remove(i-1);
						list.add((i-1), (result+""));
						i = 0;
					}
				}
			}
			
			//check for the add or subtract operator
			//if found, add or subtract the two values surrounding the operator
			//then remove those two values and the operator, and add the result into the list as a new operand
			for(int i = 0; i < list.size(); i++)
			{
				if(list.get(i) == "-")
				{
					result = Double.parseDouble(list.get(i - 1)) - Double.parseDouble(list.get(i + 1));
					list.remove(i-1);
					list.remove(i-1);
					list.remove(i-1);
					list.add((i-1), (result+""));
					i = 0;
				}
				else if(list.get(i) == "+")
				{
					result = Double.parseDouble(list.get(i - 1)) + Double.parseDouble(list.get(i + 1));
					list.remove(i-1);
					list.remove(i-1);
					list.remove(i-1);
					list.add((i-1), (result+""));
					i = 0;
				}
			}
			
			//after all calculations are done, only one value exist in the list
			//that value is the overall calculation result
			//set the result as the new operand for further calculation
			result = Double.parseDouble(list.get(0));
			setOperand(result + "");
			list.clear(); //clear the list
		}
		
		return result;
	}
	
	//returns the value as hexdecimal representation 
	public String convertHex(String value) throws EmptyOperandException
	{
		//convert the string to double first
		//and rounding the number to whole number
		double dblValue = Double.parseDouble(value);
		int intValue = (int)(dblValue + 0.5);
		int remainder = 0;
		//all the numbers and characters that a hex can have
		char hex[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		String hexConversion = "";
		
		//MODIFY so that if user passes in an "empty" value, the exception will be thrown
		if(value.length() == 3 && value.charAt(0) == '0' && value.charAt(1) == '.'
		   && value.charAt(2) == '0')
		{
			//instantiate the exception
			throw new EmptyOperandException();
		}
		else
		{
			//conversion between 0 is still zero
			if(intValue == 0)
			{
				hexConversion = "0";
			}
			//calculate the value into a hex decimal
			while(intValue > 0)
			{
				remainder = intValue % 16; //find the remainder
				hexConversion = hex[remainder] + hexConversion; //correspond the remainder with the hex character
				intValue = intValue / 16;
			}
		}
		
		return hexConversion;
	}
	
	//returns the value as octal representation
	public String convertOct(String value) throws EmptyOperandException
	{
		//convert the string to double first
		//and rounding the number to whole number
		double dblValue = Double.parseDouble(value);
		int intValue = (int)(dblValue + 0.5);
		int remainder = 0;
		//all the numbers that a octal can have
		char oct[] = {'0', '1', '2', '3', '4', '5', '6', '7'};
		String octConversion = "";
		
		//MODIFY so that if user passes in an "empty" value, the exception will be thrown
		if(value.length() == 3 && value.charAt(0) == '0' && value.charAt(1) == '.'
		   && value.charAt(2) == '0')
		{
			//instantiate the exception
			throw new EmptyOperandException();
		}
		else
		{
			//conversion between 0 is still zero
			if(intValue == 0)
			{
				octConversion = "0";
			}
			//calculate the value into an octal
			while(intValue > 0)
			{
				remainder = intValue % 8; //find the remainder
				octConversion = oct[remainder] + octConversion; //correspond the remainder with the oct number
				intValue = intValue / 8;
			}
		}
		
		return octConversion;
	}
	
	//returns the value as binary representation
	//the result might differ from the example because of no leading zeros
	public String convertBin(String value) throws EmptyOperandException
	{
		//convert the string to double first
				//and rounding the number to whole number
		double dblValue = Double.parseDouble(value);
		int intValue = (int)(dblValue + 0.5);
		int i = 0;
		int [] bin = new int[100]; //an array that holds the binary numbers
		String binConversion = "";
		
		//MODIFY so that if user passes in an "empty" value, the exception will be thrown
		if(value.length() == 3 && value.charAt(0) == '0' && value.charAt(1) == '.'
		   && value.charAt(2) == '0')
		{
			//instantiate the exception
			throw new EmptyOperandException();
		}
		else
		{
			//conversion between 0 is still zero
			if(intValue == 0)
			{
				binConversion = "0";
			}
			//calculate the value into a binary
			while(intValue > 0)
			{ 
				bin[i] = intValue % 2; //find the remainder
				intValue = intValue / 2;
				i++;
			}
			
			//the previous calculation is in reverse order
			//reverse the conversion to return the result in the correct order
			for(int j = i - 1; j >= 0; j--)
			{
				binConversion = binConversion + bin[j];
			}
		}
		
		return binConversion;
	}
	
	//returns the string representation
	public String toString()
	{
		return "";
	}
	
}
//End of class