/**
 * Program Name:	GUICalculator.java
 * Purpose:			generate a basic calculator GUI with some simple math calculations
 * @author			Robert Ren
 * Date:			Apr. 16, 2021
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GUICalculator extends JFrame
{
	//CLASS WIDE SCOPE AREA
	Calculator calc;	//calculator object
	String text = "";
	//several counters to check for which buttons pressed
	private int click = 0;
	private int plusCount = 0;
	private int minusCount = 0;
	private int multiCount = 0;
	private int divideCount = 0;
	
	private JMenuBar menuBar = new JMenuBar();  
	private JMenu fileMenu, convertMenu, helpMenu;	//menu objects
	private JMenuItem exitItem, useItem, aboutItem;	//menu items
	private JRadioButtonMenuItem hexItem, decItem, octItem, binItem; //radio button menu for conversions
	private JPanel txtPnl, btnPnl;	//panels for text field and buttons
	private JTextField txtfld;	
	private JButton button[] = new JButton[10];	//an array of ten buttons
	//function buttons
	private JButton btnC, btnBack, btnPerc, btnPlusMinus, btnSquared, btnRoot,
					btnDivide, btnMultiply, btnPlus, btnMinus, btnDecimal, btnEqual,
					btnNull1, btnNull2;
	private Container contentPane;
	//font for texts in the text field and buttons
	private Font font = new Font("SansSerif", Font.PLAIN,22); 
	//font for menus
	private Font font2 = new Font("SansSerif", Font.PLAIN, 12);
	
	//Constructor
	public GUICalculator()
	{
		super("Calculator");
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,365);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout(5,5));//use border layout for the frame
		
		//instance of Calculator
		calc = new Calculator();
		
		//format text field
		txtfld = new JTextField("0.0", 15);
		txtfld.setEditable(false);
		txtfld.setBackground(Color.white);
		txtfld.setHorizontalAlignment(SwingConstants.RIGHT);
		txtfld.setFont(font);
		txtfld.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//add the text field to the txtPnl and add the txtPnl to main panel
		txtPnl = new JPanel();
		txtPnl.setLayout(new FlowLayout());
		txtPnl.add(txtfld);
		this.add(txtPnl, BorderLayout.NORTH);
		
		//Register a listener for the buttons
		ButtonNanny bnanny = new ButtonNanny();
		//Register a listener for the menu items
		MenuNanny mnanny = new MenuNanny();
		
		//set up menu items for File option
		this.setJMenuBar(menuBar);
		fileMenu = new JMenu("File");
		fileMenu.setFont(font2);
		menuBar.add(fileMenu);
		exitItem = fileMenu.add("Exit");
		exitItem.setFont(font2);
		exitItem.addActionListener(mnanny);
		
		//set up menu items for Convert option
		convertMenu = new JMenu("Convert");
		convertMenu.setFont(font2);
		menuBar.add(convertMenu);
		convertMenu.add(hexItem = new JRadioButtonMenuItem("Hex", false));
		convertMenu.add(decItem = new JRadioButtonMenuItem("Dec", false));
		convertMenu.add(octItem = new JRadioButtonMenuItem("Oct", false));
		convertMenu.add(binItem = new JRadioButtonMenuItem("Bin", false));
		hexItem.setFont(font2);
		decItem.setFont(font2);
		octItem.setFont(font2);
		binItem.setFont(font2);
		
		//radio buttons are mutually exclusive
		ButtonGroup bGroup = new ButtonGroup();
		bGroup.add(hexItem);
		bGroup.add(decItem);
		bGroup.add(octItem);
		bGroup.add(binItem);
		
		binItem.addActionListener(mnanny);
		hexItem.addActionListener(mnanny);
		octItem.addActionListener(mnanny);
		decItem.addActionListener(mnanny);
		
		//set up menu items for Help option
		helpMenu = new JMenu("Help");
		helpMenu.setFont(font2);
		menuBar.add(helpMenu);
		useItem = helpMenu.add("How To Use");
		useItem.setFont(font2);
		useItem.addActionListener(mnanny);
		aboutItem = helpMenu.add("About");
		aboutItem.setFont(font2);
		aboutItem.addActionListener(mnanny);
		
		//set up panel to hold buttons
		btnPnl = new JPanel();
		btnPnl.setLayout(new GridLayout(6,4,5,5));
		this.add(btnPnl, BorderLayout.CENTER);
		
		//create buttons with number 0-9
		for(int i = 0; i < button.length; i++)
		{
			button[i] = new JButton(i + "");
			button[i].setFont(font);
			button[i].setBackground(Color.white);
			button[i].addActionListener(bnanny);
		}
		
		//create all other functionality buttons
		//the first row
		btnC = new JButton("C");
		btnC.setFont(font);
		btnC.setBackground(Color.white);
		btnPnl.add(btnC);
		btnC.addActionListener(bnanny);
		btnBack = new JButton();
		ImageIcon backIcon = new ImageIcon("Backspace.png");
		btnBack.setIcon(backIcon);
		btnBack.setBackground(Color.white);
		btnPnl.add(btnBack);
		btnBack.addActionListener(bnanny);
		btnPerc = new JButton();
		ImageIcon percIcon = new ImageIcon("Percentage.png");
		btnPerc.setIcon(percIcon);
		btnPerc.setBackground(Color.white);
		btnPnl.add(btnPerc);
		btnPerc.addActionListener(bnanny);
		btnPlusMinus = new JButton();
		ImageIcon pmIcon = new ImageIcon("PlusMinus.png");
		btnPlusMinus.setIcon(pmIcon);
		btnPlusMinus.setBackground(Color.white);
		btnPnl.add(btnPlusMinus);
		btnPlusMinus.addActionListener(bnanny);
		
		//the second row
		btnSquared = new JButton();
		ImageIcon squaredIcon = new ImageIcon("Xsquared.png");
		btnSquared.setIcon(squaredIcon);
		btnSquared.setBackground(Color.white);
		btnPnl.add(btnSquared);
		btnSquared.addActionListener(bnanny);
		btnRoot = new JButton();
		ImageIcon rootIcon = new ImageIcon("SquareRoot.png");
		btnRoot.setIcon(rootIcon);
		btnRoot.setBackground(Color.white);
		btnPnl.add(btnRoot);
		btnRoot.addActionListener(bnanny);
		btnNull1 = new JButton();
		btnNull1.setBackground(Color.white);
		btnPnl.add(btnNull1);
		btnDivide = new JButton("/");
		btnDivide.setFont(font);
		btnDivide.setBackground(Color.white);
		btnPnl.add(btnDivide);
		btnDivide.addActionListener(bnanny);
		
		//the third row
		btnPnl.add(button[7]);
		btnPnl.add(button[8]);
		btnPnl.add(button[9]);
		btnMultiply = new JButton("X");
		btnMultiply.setFont(font);
		btnMultiply.setBackground(Color.white);
		btnPnl.add(btnMultiply);
		btnMultiply.addActionListener(bnanny);
		
		//the fourth row
		btnPnl.add(button[4]);
		btnPnl.add(button[5]);
		btnPnl.add(button[6]);
		btnMinus = new JButton("-");
		btnMinus.setFont(font);
		btnMinus.setBackground(Color.white);
		btnPnl.add(btnMinus);
		btnMinus.addActionListener(bnanny);
		
		//the fifth row
		btnPnl.add(button[1]);
		btnPnl.add(button[2]);
		btnPnl.add(button[3]);
		btnPlus = new JButton("+");
		btnPlus.setFont(font);
		btnPlus.setBackground(Color.white);
		btnPnl.add(btnPlus);
		btnPlus.addActionListener(bnanny);
		
		//the last row
		btnNull2 = new JButton();
		btnNull2.setBackground(Color.white);
		btnPnl.add(btnNull2);
		btnPnl.add(button[0]);
		btnDecimal = new JButton(".");
		btnDecimal.setFont(font);
		btnDecimal.setBackground(Color.white);
		btnPnl.add(btnDecimal);
		btnDecimal.addActionListener(bnanny);
		btnEqual = new JButton("=");
		btnEqual.setFont(font);
		btnEqual.setBackground(Color.white);
		btnPnl.add(btnEqual);
		btnEqual.addActionListener(bnanny);

		//packs the components into the smallest possible area.
		this.pack();
		//LAST LINE
		this.setVisible(true);
	}
	
	//inner class for menu buttons
	private class MenuNanny implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			//check which button was pressed
			if(ev.getSource() == exitItem)
			{
				System.exit(0);	//exit application
			}
			else if(ev.getSource() == useItem)	//instructions on how to use application
			{
				String message = "Here are some instructions on how to use this Calculator App:\n"
						+ "1. You can input numbers by pressing any numbered buttons(0-9).\n"
						+ "2. You can press the decimal button to add decimal to the number.\n"
						+ "3. You can press +, -, x, / operators to add, substract, multiply, or divide.\n"
						+ "4. You can press C to clear the field.\n"
						+ "5. You can press backspace to delete the last inputed number or decimal.\n"
						+ "6. You can press percent to convert the number to a percentage number.\n"
						+ "7. You can press plus and minus button to toggle between nagative and positive.\n"
						+ "8. You can press x squared button to square a number.\n"
						+ "9. You can press square root button to find the square root of a number.\n"
						+ "10. You can convert the number into hexdecimal, octal, binary, and decimal form\n"
						+ "by clicking the options under the Convert tab.\n";
				
				JOptionPane.showMessageDialog(contentPane, message, "How to Use", JOptionPane.PLAIN_MESSAGE);
			}
			else if(ev.getSource() == aboutItem)	//about this application
			{
				String message = "(c) Copyright Robert Ren. App created as personal project\n"
						+ "purely for personal use.";
				
				JOptionPane.showMessageDialog(contentPane, message, "About this Application", JOptionPane.PLAIN_MESSAGE);
			}
			else if(ev.getSource() == hexItem)	//convert value to hex
			{
				//check for exception
				try
				{
					if(txtfld.getText().length() == 3 && txtfld.getText().charAt(0) == '0' && txtfld.getText().charAt(1) == '.'
				       && txtfld.getText().charAt(2) == '0')
					{
						txtfld.setText(calc.convertHex(txtfld.getText()));
					}
					else
						txtfld.setText(calc.convertHex(calc.getOperand()));
				}
				catch(EmptyOperandException ex)
				{
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(ev.getSource() == octItem)	//convert value to octal
			{
				//check for exception
				try
				{
					if(txtfld.getText().length() == 3 && txtfld.getText().charAt(0) == '0' && txtfld.getText().charAt(1) == '.'
				       && txtfld.getText().charAt(2) == '0')
					{
						txtfld.setText(calc.convertHex(txtfld.getText()));
					}
					else
						txtfld.setText(calc.convertOct(calc.getOperand()));
				}
				catch(EmptyOperandException ex)
				{
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(ev.getSource() == binItem)	//convert value to binary
			{
				//check for exception
				try
				{
					if(txtfld.getText().length() == 3 && txtfld.getText().charAt(0) == '0' && txtfld.getText().charAt(1) == '.'
				       && txtfld.getText().charAt(2) == '0')
					{
						txtfld.setText(calc.convertHex(txtfld.getText()));
					}
					else
						txtfld.setText(calc.convertBin(calc.getOperand()));
				}
				catch(EmptyOperandException ex)
				{
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(ev.getSource() == decItem)	//convert value to decimal
			{
				//error when the field is empty
				if(txtfld.getText().length() == 3 && txtfld.getText().charAt(0) == '0' && txtfld.getText().charAt(1) == '.'
				   && txtfld.getText().charAt(2) == '0')
					JOptionPane.showMessageDialog(contentPane, "The field is empty.", "Warning", JOptionPane.ERROR_MESSAGE);
				else
					txtfld.setText(calc.getOperand());
			}
		}
	}
	
	//inner class for all calculator buttons
	private class ButtonNanny implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ev)
		{	
			//applied getSource() to buttons
			JButton pb = (JButton)ev.getSource();
			
			//check for which number button was pressed 
			for(int i = 0; i < 10; i ++)
			{
				if(pb == button[i])
				{
					//if field has default value and no button pressed prior, clear the field with the number 
					if(txtfld.getText().length() == 3 && txtfld.getText().charAt(0) == '0' && txtfld.getText().charAt(1) == '.'
							   && txtfld.getText().charAt(2) == '0' && click == 0)
						txtfld.setText("");
					
					//make sure certain buttons only clicked once
					click++;
					plusCount = 0;
					minusCount = 0;
					multiCount = 0;
					divideCount = 0;
					
					//check for exception
					try
					{
						text = txtfld.getText();
						txtfld.setText(text + i);
						calc.buildOperand(txtfld.getText());
					}
					catch(LongOperandException ex)
					{
						JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
						txtfld.setText("0.0");
						calc.clear();
						click = 0;
					}
				}
			}
			
			//make sure only one decimal exist for the current operand
			if(pb == btnDecimal && calc.isDecimalPressed() == false && calc.getOperand().length() != 0)
			{
				//check for exception
				try
				{
					text = txtfld.getText();
					txtfld.setText(text + ".");
					calc.buildOperand(txtfld.getText());
					calc.setDecimalPressed(true);
				}
				catch(LongOperandException ex)
				{
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
					txtfld.setText("0.0");
					calc.clear();
				}
			}
			
			//make sure the operator can only be pressed once for binary expression
			if(pb == btnPlus)
			{
				//check exception
				try
				{
					if(plusCount == 0 && minusCount == 0 && multiCount == 0 && divideCount == 0)
					{
						calc.buildExpression("+");
						txtfld.setText("0.0");
						calc.setDecimalPressed(false);
						plusCount = 1;
						click = 0;
					}
				}
				catch(EmptyOperandException ex)
				{
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			//make sure the operator can only be pressed once for binary expression
			if(pb == btnMinus)
			{
				try
				{
					if(plusCount == 0 && minusCount == 0 && multiCount == 0 && divideCount == 0)
					{
						calc.buildExpression("-");
						txtfld.setText("0.0");
						calc.setDecimalPressed(false);
						minusCount = 1;
						click = 0;
					}
				}
				catch(EmptyOperandException ex)
				{
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			//make sure the operator can only be pressed once for binary expression
			if(pb == btnMultiply)
			{
				try
				{
					if(plusCount == 0 && minusCount == 0 && multiCount == 0 && divideCount == 0)
					{
						calc.buildExpression("x");
						txtfld.setText("0.0");
						calc.setDecimalPressed(false);
						multiCount = 1;
						click = 0;
					}
				}
				catch(EmptyOperandException ex)
				{
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			//make sure the operator can only be pressed once for binary expression
			if(pb == btnDivide)
			{
				try
				{
					if(plusCount == 0 && minusCount == 0 && multiCount == 0 && divideCount == 0)
					{
						calc.buildExpression("/");
						txtfld.setText("0.0");
						calc.setDecimalPressed(false);
						divideCount = 1;
						click = 0;
					}
				}
				catch(EmptyOperandException ex)
				{
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			//set the text field with the result
			if(pb == btnEqual)
			{
				//check for exceptions
				try
				{
					if(txtfld.getText().length() == 3 && txtfld.getText().charAt(0) == '0' && txtfld.getText().charAt(1) == '.'
							&& txtfld.getText().charAt(2) == '0')
					{
						calc.setOperand(txtfld.getText());
						calc.buildExpression("");
					}
						txtfld.setText(calc.calculate() + calc.toString());	
						plusCount = 0;
						minusCount = 0;
						multiCount = 0;
						divideCount = 0;
				}
				catch(EmptyOperandException ex)
				{
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
					calc.clear();
				}
				catch(ArithmeticException ex)
				{
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
					txtfld.setText("0.0");
					calc.clear();
					click = 0;
				}
			}
			
			//clear all fields
			if(pb == btnC)
			{
				txtfld.setText("0.0");
				calc.clear();
				click = 0;
				plusCount = 0;
				minusCount = 0;
				multiCount = 0;
				divideCount = 0;
			}
			
			//delete the last inputed number or decimal
			if(pb == btnBack)
			{
				try 
				{
					txtfld.setText(calc.backspace(txtfld.getText()));
					calc.setOperand(txtfld.getText());
					click = 0;
				}
				catch(EmptyOperandException ex)
				{
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
					
				}
			}
			
			//toggle between negative and positive
			if(pb == btnPlusMinus)
			{
				try
				{
					calc.setOperand(txtfld.getText());
					if(Double.parseDouble(txtfld.getText()) > 0)
					{
						txtfld.setText(calc.togglePlusMinus(true));
						calc.setOperand(txtfld.getText());
					}
					else
					{
						txtfld.setText(calc.togglePlusMinus(false));
						calc.setOperand(txtfld.getText());
					}
				}
				catch(EmptyOperandException ex)
				{
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			//value as percentage
			if(pb == btnPerc)
			{
				try
				{
					txtfld.setText(calc.findPercentage(txtfld.getText()));
					calc.setOperand(txtfld.getText());
				}
				catch(EmptyOperandException ex)
				{
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			//value as squared
			if(pb == btnSquared)
			{
				try
				{
					txtfld.setText(calc.findSquared(txtfld.getText()));
					calc.setOperand(txtfld.getText());
				}
				catch(EmptyOperandException ex)
				{
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			//value as square root
			if(pb == btnRoot)
			{
				try
				{
					txtfld.setText(calc.findSquareRoot(txtfld.getText()));
					calc.setOperand(txtfld.getText());
				}
				catch(EmptyOperandException ex)
				{
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), "Warning", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}

	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new GUICalculator();
	}//end main
}
//End of class
