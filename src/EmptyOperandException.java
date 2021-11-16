/**
 * Program Name:	EmptyOperandException.java
 * Purpose:			A customized subclass of the Exception class that will create an exception object
 *          		if a user tries to use functions or operators when the text field is empty
 * @author			Robert Ren, 0975676
 * Date:			Apr. 16, 2021
 */

public class EmptyOperandException extends Exception
{
	//constructor
	EmptyOperandException()
	{
		//pass the message string to be displayed up to the super class Exception's constructor
		super("EmptyOperandException, field is empty");
	}
}
//End of class