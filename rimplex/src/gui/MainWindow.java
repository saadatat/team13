package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

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
  private JButton zero;
  private JButton one;
  private JButton two;
  private JButton three;
  private JButton four;
  private JButton five;
  private JButton six;
  private JButton seven;
  private JButton eight;
  private JButton nine;
  private JPanel testPanel;
 
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
    this.setSize(400, 300);
    this.setTitle("Rimplex");
    this.setVisible(true);
    resultHistory = "";
    centerForm();
    calculator.setFractionDisplay(false);
    this.setFocusable(true);
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
    testPanel = new JPanel(new BorderLayout());
    mainPanel = new JPanel();
    southPanel = new JPanel(new GridLayout(5, 5, 10, 5));
    resultPanel = new JPanel(new BorderLayout());
    
    resultPanel.setVisible(false);
    
  
   
    displayLabel = new JLabel(" ");
   
    resultDisplayArea = new JTextArea(" ");
    
    scroll = new JScrollPane(resultDisplayArea);
    scroll.setPreferredSize(new Dimension(300,200));
    
    
    inputTextField = new JTextField("");
    
    inputTextField.setEditable(false);
    inputTextField.addKeyListener(this);
    displayLabel.addKeyListener(this);
    
    
    
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
    zero = new JButton("0");
    one = new JButton("1");
    two = new JButton("2");
    three = new JButton("3");
    four = new JButton("4");
    five = new JButton("5");
    six = new JButton("6");
    seven = new JButton("7");
    eight = new JButton("8");
    nine = new JButton("9");
    fractionDisplayButton = new JButton("Format ( / )");
    addButton.addActionListener(this);
    this.addKeyListener(this);
    addButton.addKeyListener(this);
    subtractButton.addActionListener(this);
    subtractButton.addKeyListener(this);
    divideButton.addActionListener(this);
    divideButton.addKeyListener(this);
    multiplyButton.addActionListener(this);
    multiplyButton.addKeyListener(this);
    equalsButton.addActionListener(this);
    equalsButton.addKeyListener(this);
    resetButton.addActionListener(this);
    resetButton.addKeyListener(this);
    clearButton.addActionListener(this);
    clearButton.addKeyListener(this);
    signButton.addActionListener(this);
    signButton.addKeyListener(this);
    resultButton.addActionListener(this);
    resultButton.addKeyListener(this);
    hideResultButton.addActionListener(this);
    hideResultButton.addKeyListener(this);
    fractionDisplayButton.addActionListener(this);
    fractionDisplayButton.addKeyListener(this);
    inputTextField.addKeyListener(this);
    zero.addActionListener(this);
    one.addActionListener(this);
    two.addActionListener(this);
    three.addActionListener(this);
    four.addActionListener(this);
    five.addActionListener(this);
    six.addActionListener(this);
    seven.addActionListener(this);
    eight.addActionListener(this);
    nine.addActionListener(this);
  

    
  }

  /**
   * Set-up the components used by this window.
   */
  private void setComponents()
  {
    Color lightBlue = new Color(210, 237, 255, 255);
    Color gray = new Color(204,204,204,255);
    Color yellow = new Color(131,139,82,255);
    Color green = new Color(99,164,157,255);
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
    southPanel.add(zero);
    southPanel.add(one);
    southPanel.add(two);
    southPanel.add(three);
    southPanel.add(four);
    southPanel.add(five);
    southPanel.add(six);
    southPanel.add(seven);
    southPanel.add(eight);
    southPanel.add(nine);
    
    southPanel.add(resultButton);
    
    resultButton.setBorderPainted(false);
    resultButton.setContentAreaFilled( false );
    hideResultButton.setBorderPainted(false);
    hideResultButton.setContentAreaFilled( false );
resultPanel.add(hideResultButton, BorderLayout.LINE_END);
    

    resultDisplayArea.setEditable(false);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    resultPanel.add(scroll);
    
    
    resultButton.setContentAreaFilled(false);
    zero.setContentAreaFilled(false);
    one.setContentAreaFilled(false);
    two.setContentAreaFilled(false);
    three.setContentAreaFilled(false);
    four.setContentAreaFilled(false);
    five.setContentAreaFilled(false);
    six.setContentAreaFilled(false);
    seven.setContentAreaFilled(false);
    eight.setContentAreaFilled(false);
    nine.setContentAreaFilled(false);
    equalsButton.setContentAreaFilled(false);
    resetButton.setContentAreaFilled(false);
    clearButton.setContentAreaFilled(false);
    multiplyButton.setContentAreaFilled(false);
    divideButton.setContentAreaFilled(false);
    addButton.setContentAreaFilled(false);
    multiplyButton.setContentAreaFilled(false);
    subtractButton.setContentAreaFilled(false);
    fractionDisplayButton.setContentAreaFilled(false);
    signButton.setContentAreaFilled(false);
    resultButton.setContentAreaFilled(false);
    hideResultButton.setBorderPainted(false);
    hideResultButton.setContentAreaFilled(false);
    
    resultButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    zero.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    one.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    two.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    three.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    four.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    five.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    six.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    seven.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    eight.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    nine.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    equalsButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    resetButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    clearButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    divideButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    multiplyButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    addButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    subtractButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    fractionDisplayButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    signButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    
    resultDisplayArea.setBackground(lightBlue);
    mainPanel.setBackground(lightBlue);
    inputTextField.setBackground(lightBlue);
    testPanel.setBackground(gray);
    southPanel.setBackground(gray);
    resultPanel.setBackground(gray);
    clearButton.setForeground(yellow);
    signButton.setForeground(yellow);
    addButton.setForeground(green);
   subtractButton.setForeground(green);
    multiplyButton.setForeground(green);
    subtractButton.setForeground(green);
    equalsButton.setForeground(green);
    resetButton.setForeground(green);
    
    
    
    
    inputTextField.setBorder(null);
    
    resultPanel.setBorder(new EmptyBorder( 10, 10, 10, 10));
    
    testPanel.setBorder(new EmptyBorder( 10, 10, 10, 10));
    southPanel.setBorder(new EmptyBorder( 10, 10, 10, 30));
    
    JLabel rimplexHolder = new JLabel("Rimplex Logo Goes here");
    rimplexHolder.setPreferredSize(new Dimension(100, 50));
    mainPanel.setPreferredSize(new Dimension(100, 50));
    testPanel.add(rimplexHolder, BorderLayout.NORTH);
    testPanel.add(mainPanel, BorderLayout.CENTER);
    testPanel.add(southPanel, BorderLayout.SOUTH);
    testPanel.add(resultButton, BorderLayout.EAST);
    this.add(testPanel, BorderLayout.CENTER);
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

    if (command.equals("0"))
    {
      inputTextField.setText("0");
    }

    if (command.equals("1"))
    {
      inputTextField.setText(inputField += "1");
    }

    if (command.equals("2"))
    {
      inputTextField.setText(inputField += "2");
    }

    if (command.equals("3"))
    {
      inputTextField.setText(inputField += "3");
    }

    if (command.equals("4"))
    {
      inputTextField.setText(inputField += "4");
    }

    if (command.equals("5"))
    {
      inputTextField.setText(inputField += "5");
    }

    if (command.equals("6"))
    {
      inputTextField.setText(inputField += "6");
    }

    if (command.equals("7"))
    {
      inputTextField.setText(inputField += "7");
    }

    if (command.equals("8"))
    {
      inputTextField.setText(inputField += "8");
    }

    if (command.equals("9"))
    {
      inputTextField.setText(inputField += "9");
    }

   

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
      String newText = inputTextField.getText().concat("𝑖");
      inputTextField.setText(newText);
    }
    
    for(int i = 48; i < 58; i++)
    {
      if (e.getKeyChar() == i)
      {
        String newText = inputTextField.getText().concat((char)i + "");
        inputTextField.setText(newText);
        
      }
      
    }
    
    if(e.getKeyChar() == '+')
    {
      String newText = inputTextField.getText().concat("+");
      inputTextField.setText(newText);
      
    }
    
    if(e.getKeyChar() == '-')
    {
      String newText = inputTextField.getText().concat("-");
      inputTextField.setText(newText);
      
    }
    

  }
  

  
  
  @Override
  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_ENTER)
    {
      equalsEvent();
    }
   
  }

  @Override
  public void keyReleased(KeyEvent e)
  {
  
  }
  
  
}
