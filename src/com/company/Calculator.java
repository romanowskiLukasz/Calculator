/**
 * @author Lukasz Romanowski
 * @version 1.0
 */
package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;



/**
 * klasa implementujaca kalkulator infiksowy/postfiksowy
 */
public class Calculator implements ActionListener
{
    JFrame frame;
    JTextField textField;
    JCheckBox checkBox;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton,subButton,mulButton,divButton,
            decButton,equButton,delButton,clrButton,
            spcButton;
    JPanel panel,ONPpanel;
    String expression[];
    Stack<Double> stack = new Stack<Double>();
    Font myFont= new Font("American Typewriter", Font.PLAIN,30);
    double num1=0,num2=0,result=0;
    char operator;

    /** konstruktor klasy Calculator inicjalizujacy
     * oraz odpowiednio ustawiajacy ramke wraz z elementami
     */
    Calculator()
    {
        frame=new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(336,525);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(70, 130, 180));
        frame.setResizable(false);

        textField=new JTextField();
        textField.setBounds(3,40,310,50);
        textField.setFont(new Font("American Typewriter", Font.PLAIN,40));
        textField.setForeground(Color.white);
        textField.setBorder(null);
        textField.setBackground(new Color(70, 130, 180));
        textField.setEditable(false);

        checkBox=new JCheckBox("ONP");
        checkBox.setFocusable(false);
        checkBox.setBounds(5,1,100,20);
        checkBox.setBackground(Color.white);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JCheckBox cb = (JCheckBox) event.getSource();
                if (cb.isSelected()) {
                    spcButton.setEnabled(true);
                    textField.setText("");
                    num1=0;num2=0;result=0;

                    spcButton.setEnabled(true);
                    stack.clear();
                    expression=new String[0];
                } else {
                    textField.setText("");
                    num1=0;num2=0;result=0;
                    spcButton.setEnabled(false);
                }
            }
        });

        panel=new JPanel();
        panel.setBounds(0,160,320,210);
        panel.setLayout(new GridLayout(3,4,0,0));
        ONPpanel=new JPanel();
        ONPpanel.setBounds(0,0,320,20);
        ONPpanel.setLayout(null);
        ONPpanel.setBackground(Color.WHITE);

        addButton=new JButton("+");
        subButton=new JButton("-");
        mulButton=new JButton("x");
        divButton=new JButton("/");
        decButton=new JButton(".");
        equButton=new JButton("=");
        delButton=new JButton("<-");
        clrButton=new JButton("AC");
        spcButton=new JButton("Space");

        functionButtons[0]=addButton;
        functionButtons[1]=subButton;
        functionButtons[2]=mulButton;
        functionButtons[3]=divButton;
        functionButtons[4]=decButton;
        functionButtons[5]=equButton;
        functionButtons[6]=delButton;
        functionButtons[7]=clrButton;
        functionButtons[8]=spcButton;

        for(JButton button: functionButtons)
        {
            button.addActionListener(this);
            button.setFont(myFont);
            button.setFocusable(false);
            button.setBackground(new Color(255,165,0));
        }
        for(int i=0;i<10;i++)
        {
             numberButtons[i]=new JButton(String.valueOf(i));
             numberButtons[i].addActionListener(this);
             numberButtons[i].setFont(myFont);
             numberButtons[i].setFocusable(false);
             numberButtons[i].setBackground(new Color(240,240,240));
        }

        numberButtons[0].setBounds(0,370,160,70);
        numberButtons[0].setBackground(new Color(240,240,240));
        delButton.setBounds(120,90,120,70);
        delButton.setBackground(new Color(224,224,224));
        clrButton.setBounds(0,90,120,70);
        clrButton.setBackground(new Color(224,224,224));
        equButton.setBounds(240,370,80,70);
        spcButton.setBounds(0,440,320,45);
        spcButton.setEnabled(false);
        divButton.setBounds(240,90,80,70);
        decButton.setBounds(160,370,80,70);
        decButton.setBackground(new Color(240,240,240));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);

        ONPpanel.add(checkBox);

        frame.add(numberButtons[0]);
        frame.add(divButton);
        frame.add(panel);
        frame.add(ONPpanel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(decButton);
        frame.add(equButton);
        frame.add(spcButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    /**
     * funkcja obslugujaca dzialanie klasycznego kalkulatora
     * (dzialania w notacji infiksowej)
     * @param e
     */
    void classicCalculator(ActionEvent e)
    {
        if(e.getSource()==addButton)
        {
            num1=Double.parseDouble(textField.getText());
            operator='+';
            textField.setText("");
        }
        if(e.getSource()==subButton)
        {
            num1=Double.parseDouble(textField.getText());
            operator='-';
            textField.setText("");
        }
        if(e.getSource()==mulButton)
        {
            num1=Double.parseDouble(textField.getText());
            operator='*';
            textField.setText("");
        }
        if(e.getSource()==divButton)
        {
            num1=Double.parseDouble(textField.getText());
            operator='/';
            textField.setText("");
        }
        if(e.getSource()==equButton)
        {
            try {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                }
                textField.setText(String.valueOf(result));
                num1 = result;
            }
            catch (Exception err)
            {
                num1=0;num2=0;result=0;
                JOptionPane.showMessageDialog(null,"probably wrong expression","Error",JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    /**
     * funkcja sprawdzajaca czy podany string jest liczba
     * @param str string do sprawdzenia
     * @return true jezeli string jest liczba, false jezeli nie jest
     */
    static boolean isNumeric(final String str)
    {
        if (str == null || str.length() == 0) return false;
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c) && c!='.') return false;
        }
        return true;
    }
    /**
     * funkcja sprawdzajaca czy podany string jest obslugiwanym operandem
     * @param str string do sprawdzenia
     * @return true jezeli string jest operandem, false jezeli nie jest
     */
    static boolean isOperand(final String str)
    {
        if (str == null || str.length() == 0) return false;
        if(str.equals("+") || str.equals("-") ||str.equals("*") ||str.equals("/") )
            return true;
        else return false;
    }

    /**
     *  kalkulator obslugujacy wyrazenia postfiksowe(ONP)
     *  nalezy wpisac cale dzialanie wraz ze spacjami a nastepnie =
     * @param e
     */
    void ONPCalculator(ActionEvent e)
    {
        if(e.getSource()==addButton)
        {
            textField.setText(textField.getText().concat("+"));
        }
        if(e.getSource()==subButton)
        {
            textField.setText(textField.getText().concat("-"));
        }
        if(e.getSource()==mulButton)
        {
            textField.setText(textField.getText().concat("*"));
        }
        if(e.getSource()==divButton)
        {
            textField.setText(textField.getText().concat("/"));
        }
        if(e.getSource()==spcButton)
        {
            textField.setText(textField.getText().concat(" "));
        }
        if(e.getSource()==equButton)
        {
            expression=textField.getText().split(" ");
            for (String elem : expression) {
                if (isNumeric(elem)) {
                    stack.push(Double.parseDouble(elem)); //liczby wkladamy do stosu
                } else if (isOperand(elem)) {
                    num1 = stack.pop();
                    num2 = stack.pop();
                    switch (elem) {
                        case "+":
                            stack.push(num1 + num2);
                            break; case "-": stack.push(num1 - num2);
                            break; case "/": stack.push(num1 / num2);
                            break; case "*": stack.push(num1 * num2);
                            break;
                    }
                }
            }
            if(!stack.empty())
            {
                textField.setText(Double.toString(stack.pop()));
            }
            else
            {
                textField.setText("");
                JOptionPane.showMessageDialog(null,"stack is empty, wrong expression(try with spaces)","Error",JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for(int i=0;i<10;i++)
        {
            if(e.getSource()==numberButtons[i])
            {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if(e.getSource()==decButton)
        {
            textField.setText(textField.getText().concat("."));
        }

        if(checkBox.isSelected()) ONPCalculator(e);
        else  classicCalculator(e);

        if(e.getSource()==clrButton)
        {
            textField.setText("");
            num1=0;num2=0;result=0;
            stack.clear();
            expression=new String[0];
        }
        if(e.getSource()==delButton)
        {
            String string=textField.getText();
            textField.setText("");
            for (int i=0;i<string.length()-1;i++)
            {
                textField.setText(textField.getText()+string.charAt(i));
            }
        }

    }
    public static void main(String[] args)
    {
        Calculator calculator=new Calculator();
    }
}
