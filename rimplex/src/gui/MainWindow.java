package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
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
 * @author Dylan Moreno, Kory Erdmann
 * @version Rimplex 1.0
 */
public class MainWindow extends JFrame implements ActionListener, KeyListener
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
    setResizable(false);
    this.setSize(575, 180);
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

    displayLabel = new JLabel(" ");
    displayLabel.setPreferredSize(new Dimension(200, 75));

    inputTextField = new JTextField("");
    inputTextField.setPreferredSize(new Dimension(15, 10));

    resetButton = new JButton("R");
    clearButton = new JButton("C");
    addButton = new JButton("+");
    subtractButton = new JButton("-");
    multiplyButton = new JButton("x");
    divideButton = new JButton("/");
    equalsButton = new JButton("=");
    addButton.addActionListener(this);
    subtractButton.addActionListener(this);
    divideButton.addActionListener(this);
    multiplyButton.addActionListener(this);
    equalsButton.addActionListener(this);
    resetButton.addActionListener(this);
    clearButton.addActionListener(this);
    inputTextField.addKeyListener(this);
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
    WarningDialog error;
    String inputField = inputTextField.getText().trim();
    inputField = inputField.replace("𝑖", "i");
    String command = e.getActionCommand();
    String operators = "+-/x";

    String result = calculator.getResult();
    if (!(inputField.matches("^[0-9i+-.]*$")))
    {
      if (!inputField.trim().equals("") && operators.contains(command))
      {
        error = new WarningDialog();
      }
    }
    else if (operators.contains(command))
    {
      if (calculator.getLeftOperand() == null || calculator.getLeftOperand().trim().equals(""))
      {
        if (!inputField.equals(""))
        {
          calculator.setLeftOperand(inputField);
          calculator.setOperator(command);
          displayLabel.setText(calculator.formatDisplayOperand(calculator.getLeftOperand())
              + calculator.getOperator());
        }
        else if (result == null || result.trim().equals(""))
        {
          calculator.setLeftOperand("0+0i");
          calculator.setOperator(command);
          displayLabel.setText(calculator.formatDisplayOperand(calculator.getLeftOperand())
              + calculator.getOperator());
        }
        else
        {

          calculator.setLeftOperand(result);
          calculator.setOperator(command);
          displayLabel
              .setText(calculator.formatDisplayOperand(calculator.getLeftOperand()) + command);
        }
      }
      else if (calculator.getLeftOperand() != null
          && !calculator.getLeftOperand().trim().equals(""))
      {
        if (!(inputField.equals("") && (calculator.getRightOperand() == null
            || calculator.getRightOperand().trim().equals(""))))
        {
          calculator.setRightOperand(inputField);
          displayLabel.setText(displayLabel.getText()
              + calculator.formatDisplayOperand(calculator.getRightOperand()));

        }
        else
        {
          calculator.setOperator(command);
          displayLabel.setText(calculator.formatDisplayOperand(calculator.getLeftOperand())
              + calculator.getOperator());
        }
      }
      inputTextField.setText("");
    }
    else if (command.equals("=")
        && (calculator.getLeftOperand() != null && !calculator.getLeftOperand().equals("")))
    {

      if (calculator.getRightOperand() == null || calculator.getRightOperand().trim().equals(""))
      {
        if (inputField.trim().equals(""))
        {
          calculator.setRightOperand("0+0i");
        }
        else
        {
          calculator.setRightOperand(inputField);
        }
      }
      displayLabel.setText(
          calculator.formatDisplayOperand(calculator.getLeftOperand()) + calculator.getOperator()
              + calculator.formatDisplayOperand(calculator.getRightOperand()) + command);
      calculator.formResult();
      displayLabel
          .setText(displayLabel.getText() + Calculator.formatItalic(calculator.getResult()));
      inputTextField.setText("");
    }

    if (command.equals("C"))
    {
      inputTextField.setText("");
    }

    if (command.equals("R"))
    {
      inputTextField.setText("");
      displayLabel.setText(" ");
      calculator.clear();
    }

  }

  @Override
  public void keyTyped(KeyEvent e)
  {
    if (e.getKeyChar() == 'i')
    {
      String newText = inputTextField.getText().replace("i", "𝑖");
      inputTextField.setText(newText);
    }

  }

  @Override
  public void keyPressed(KeyEvent e)
  {

    if (e.getKeyChar() == 'i')
    {
      String newText = inputTextField.getText().replace("i", "𝑖");
      inputTextField.setText(newText);
    }
  }

  @Override
  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyChar() == 'i')
    {
      String newText = inputTextField.getText().replace("i", "𝑖");
      inputTextField.setText(newText);
    }

  }
}
