/**
 * Program Name:	LongOperandException.java
 * Purpose:			A customized subclass of the Exception class that will create an exception object
 *          		if a user enters too much values that exceed the text field limit
 * @author			Robert Ren, 0975676
 * Date:			Apr. 16, 2021
 */

public class LongOperandException extends Exception
{
	//constructor
	LongOperandException()
	{
		//pass the message string to be displayed up to the super class Exception's constructor
		super("LongOperandException, operand exceeds the field limit.");
	}
}
//End of class