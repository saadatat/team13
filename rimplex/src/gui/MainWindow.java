package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calculations.Calculator;

/**
 * The main window for the Rimplex GUI.
 * 
 * @author Dylan Moreno
 * @version Rimplex 1.0
 */
public class MainWindow extends JFrame
{
  private static final long serialVersionUID = 2740437090361841747L;
  private Calculator calculator;
  private JButton addButton;
  private JButton clearButton;
  private JButton divideButton;
  private JButton equalsButton;
  private JButton multiplyButton;
  private JButton resetButton;
  private JButton subtractButton;

  private JLabel displayLabel;

  private JPanel mainPanel;
  private JPanel southPanel;

  private JTextField inputTextField;

  /**
   * Default Constructor.
   */
  public MainWindow()
  {
    super();
    calculator = new Calculator();
    createComponents(); // create needed objects
    setComponents(); // modify/add/format the components
    setListeners(); // set listeners for components

    this.setSize(600, 450);
    this.setTitle("Rimplex");
    this.setVisible(true);

    centerForm();
  }

  /**
   * Centers the window on the screen.
   */
  private void centerForm()
  {
    Dimension dimScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension dimFrameSize = getSize();

    if (dimFrameSize.height > dimScreenSize.height)
    {
      dimFrameSize.height = dimScreenSize.height;
    }
    if (dimFrameSize.width > dimScreenSize.width)
    {
      dimFrameSize.width = dimScreenSize.width;
    }

    setLocation((dimScreenSize.width - dimFrameSize.width) / 2,
        (dimScreenSize.height - dimFrameSize.height) / 2);
  }

  /**
   * Create the components used by this window.
   */
  private void createComponents()
  {
    mainPanel = new JPanel();
    southPanel = new JPanel();

    displayLabel = new JLabel("test");

    inputTextField = new JTextField();

    resetButton = new JButton("R");
    clearButton = new JButton("C");
    addButton = new JButton("+");
    subtractButton = new JButton("-");
    multiplyButton = new JButton("X");
    divideButton = new JButton("/");
    equalsButton = new JButton("=");
  }

  /**
   * Set-up the components used by this window.
   */
  private void setComponents()
  {
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(displayLabel);
    mainPanel.add(inputTextField);

    southPanel.add(resetButton);
    southPanel.add(clearButton);
    southPanel.add(addButton);
    southPanel.add(subtractButton);
    southPanel.add(multiplyButton);
    southPanel.add(divideButton);
    southPanel.add(equalsButton);

    this.add(mainPanel, BorderLayout.CENTER);
    this.add(southPanel, BorderLayout.SOUTH);
  }

  /**
   * Set the listeners for the necessary components.
   */
  private void setListeners()
  {
    Listener listener = Listener.getInstance();
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
