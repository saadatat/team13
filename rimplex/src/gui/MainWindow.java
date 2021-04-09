package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

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
  private JButton signButton;
  private JButton resultButton;
  private JButton fractionDisplayButton;
  private JLabel displayLabel;
  private JTextArea resultDisplayArea;
  private JPanel mainPanel;
  private JPanel southPanel;
  private JPanel resultPanel;
  private JTextField inputTextField;
  private String resultHistory;
  private JScrollPane scroll;
  private JButton hideResultButton;
 
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
    this.setSize(650, 300);
    this.setTitle("Rimplex");
    this.setVisible(true);
    resultHistory = "";
    centerForm();
    calculator.setFractionDisplay(false);
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
    resultPanel = new JPanel(new BorderLayout());
    
    resultPanel.setVisible(false);
    
    mainPanel.setPreferredSize(new Dimension(650,250));
   
    displayLabel = new JLabel(" ");
    displayLabel.setPreferredSize(new Dimension(500, 250));
    resultDisplayArea = new JTextArea(" ");
    
    scroll = new JScrollPane(resultDisplayArea);
    scroll.setPreferredSize(new Dimension(400,300));
    inputTextField = new JTextField("");
    inputTextField.setPreferredSize(new Dimension(100, 30));
    
    
    
    resetButton = new JButton("R");
    clearButton = new JButton("C");
    addButton = new JButton("+");
    subtractButton = new JButton("-");
    multiplyButton = new JButton("x");
    divideButton = new JButton("/");
    equalsButton = new JButton("=");
    signButton = new JButton("+/-");
    resultButton = new JButton(">");
    hideResultButton = new JButton("<");
    fractionDisplayButton = new JButton("Format ( / )");
    addButton.addActionListener(this);
    subtractButton.addActionListener(this);
    divideButton.addActionListener(this);
    multiplyButton.addActionListener(this);
    equalsButton.addActionListener(this);
    resetButton.addActionListener(this);
    clearButton.addActionListener(this);
    inputTextField.addKeyListener(this);
    signButton.addActionListener(this);
    resultButton.addActionListener(this);
    hideResultButton.addActionListener(this);
    fractionDisplayButton.addActionListener(this);
  
 
    
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
    southPanel.add(signButton);
    southPanel.add(fractionDisplayButton);
    southPanel.add(resultButton);
    
    resultButton.setBorderPainted(false);
    resultButton.setContentAreaFilled( false );
    hideResultButton.setBorderPainted(false);
    hideResultButton.setContentAreaFilled( false );
resultPanel.add(hideResultButton, BorderLayout.LINE_END);
    

    resultDisplayArea.setEditable(false);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    resultPanel.add(scroll);
    
    //resultDisplayArea.setBackground(Color.lightGray);
    this.add(mainPanel, BorderLayout.CENTER);
    this.add(southPanel, BorderLayout.SOUTH);
    this.add(resultPanel, BorderLayout.LINE_END);
    pack();
    setLocationRelativeTo(null);
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
    WarningDialog warningDialog = WarningDialog.getInstance();
    String inputField = inputTextField.getText().trim();
    inputField = inputField.replace("𝑖", "i");
    String command = e.getActionCommand();
    String operators = "+-/x";
    String result = calculator.getResult();
    if (!(inputField.matches("^[0-9i+-.]*$")))
    {
      if (!inputField.trim().equals("") && (operators.contains(command) || command.equals("=")))
      {
        warningDialog.displayDialog();
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
    else if (command.equals("="))
    {

      equalsEvent();
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

    if (command.equals("+/-") && !inputField.equals(""))
    {
      if (inputField.charAt(0) != '-')
      {
        inputTextField.setText("-" + inputField);
      }
      else
      {
        inputTextField.setText(inputField.substring(1, inputField.length()));
      }
    }
    
    
    if (command.equals(">")) {
        
        resultButton.setVisible(false);
        resultPanel.setPreferredSize(new Dimension(500,100));
        resultPanel.setVisible(true);
        pack();
        setLocationRelativeTo(null);
      }
    
    if (command.equals("<")) {
      
      resultButton.setVisible(true);
      resultPanel.setVisible(false);  
      pack();
      setLocationRelativeTo(null);
      resultButton.setText(">");
    }
    
    if (command.equals("Format ( / )")) {
      fractionDisplayButton.setText("Format ( . )");
      calculator.setFractionDisplay(true);
    }
    
    if (command.equals("Format ( . )")) {
      fractionDisplayButton.setText("Format ( / )");
      calculator.setFractionDisplay(false);
    }

    /**
     * if (command.equals("0")) { inputTextField.setText("0"); }
     * 
     * if (command.equals("1")) { inputTextField.setText("1"); }
     * 
     * if (command.equals("2")) { inputTextField.setText("2"); }
     * 
     * if (command.equals("3")) { inputTextField.setText("3"); }
     * 
     * if (command.equals("4")) { inputTextField.setText("4"); }
     * 
     * if (command.equals("5")) { inputTextField.setText("5"); }
     * 
     * if (command.equals("6")) { inputTextField.setText("6"); }
     * 
     * if (command.equals("7")) { inputTextField.setText("7"); }
     * 
     * if (command.equals("8")) { inputTextField.setText("8"); }
     * 
     * if (command.equals("9")) { inputTextField.setText("9"); } if (command.equals("(")) {
     * inputTextField.setText("("); } if (command.equals(")")) { inputTextField.setText(")"); }
     **/

  }

  public void equalsEvent()
  {
    String inputField = inputTextField.getText().trim();
    inputField = inputField.replace("𝑖", "i");

    if (calculator.getLeftOperand() != null && !calculator.getLeftOperand().equals(""))
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
              + calculator.formatDisplayOperand(calculator.getRightOperand()) + "=");
      calculator.formResult();
      displayLabel
          .setText(displayLabel.getText() + Calculator.formatItalic(calculator.getResult()));
      inputTextField.setText("");
      resultHistory += displayLabel.getText() + "\n";
      resultDisplayArea.setText(resultHistory);
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

    if (e.getKeyCode() == KeyEvent.VK_ENTER)
    {
      equalsEvent();
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
