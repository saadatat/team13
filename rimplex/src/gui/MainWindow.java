package gui;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import calculations.Calculator;
import calculations.Operations;

public class MainWindow extends JFrame
{
  private static final long serialVersionUID = 2740437090361841747L;
  private Calculator calculator;

  /**
   * Default Constructor.
   */
  public MainWindow()
  {
    super();
    calculator = new Calculator();
    this.setVisible(true);
  }

  public void actionPerformed(ActionEvent e)
  {

    String command = e.getActionCommand();
    String operators = "+-/*";
    String leftOperand = calculator.getLeftOperand();
    String result = calculator.getResult();

    if (operators.contains(command))
    {

      if (leftOperand == null || leftOperand.trim().equals(""))
      {
        if (result == null || result.trim().equals(""))
        {
          calculator.setLeftOperand("0");
        }
        else
        {
          calculator.setLeftOperand(result);
        }
      }
      else if (calculator.validOperands())
      {
        calculator.formResult();
      }

      calculator.setOperator(command);
    }

    if (command.equals("="))
    {
      calculator.formResult();
    }

  }
}
