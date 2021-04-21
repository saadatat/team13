package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import calculations.Calculator;

/**
 * The main window for the Rimplex GUI.
 * 
 * @author Dylan Moreno, Kory Erdmann, Arman Saadat, Max Berger
 * @version Rimplex 1.0
 */
public class MainWindow extends JFrame implements ActionListener, KeyListener
{
  private static final long serialVersionUID = 2740437090361841747L;
  
  // Calculator Reference
  private Calculator calculator;
  
  // Calculator Buttons
  private JButton addButton;
  private JButton imaginaryButton;
  private JButton clearButton;
  private JButton divideButton;
  private JButton equalsButton;
  private JButton multiplyButton;
  private JButton resetButton;
  private JButton subtractButton;
  private JButton signButton;
  private JButton fractionDisplayButton;
  private JButton leftParenthesisButton;
  private JButton rightParenthesisButton;
  private JButton resultButton;
  private JButton backspace;
  private JButton decimal;
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
  
  // Calculator Display/Input Fields
  private JLabel displayLabel;
  private JTextField inputTextField;
  private JTextPane resultDisplayArea;
  
  // Panels and Panes
  private JButton hideResultButton;
  private JPanel mainPanel;
  private JPanel southPanel;
  private JPanel resultPanel;
  private JScrollPane scroll;
  private JPanel testPanel;
  
  // Menu Items
  private JMenuBar menuBar;
  private JMenu fileTab;
  private JMenu settings;
  private JMenu languages;
  private JMenu help;
  private JMenuItem about;
  private JMenuItem fileSetting;
  private JMenuItem helpPage;
  private JMenuItem print;
  private JMenuItem recordButton;
  private JMenuItem english;
  private JMenuItem spanish;
  private JMenuItem french; 
  private JMenuItem portugese;
  private JMenuItem japanese;
  private JMenuItem russian;
  
  // Dialogs and Prompts
  private AboutDialog aboutDialog;
  
  // Values
  private boolean recordingEnabled = false;
  private String resultHistory;
  private String recordHistory;

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
    this.setSize(350, 425);
    this.setTitle("rimpleX");
    this.setVisible(true);
    resultHistory = "";
    recordHistory = "";
    centerForm(); // center the window on the screen
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
    southPanel = new JPanel(new GridLayout(5, 0));
    resultPanel = new JPanel(new BorderLayout());

    resultPanel.setVisible(false);

    displayLabel = new JLabel(" ");

    resultDisplayArea = new JTextPane();

    scroll = new JScrollPane(resultDisplayArea);
    scroll.setPreferredSize(new Dimension(300, 200));

    inputTextField = new JTextField("");

    // Configurations
    inputTextField.setEditable(false);
    displayLabel.addKeyListener(this);

    // Instantiate the JButtons
    resetButton = new JButton("R");
    clearButton = new JButton("C");
    imaginaryButton = new JButton("𝑖");
    addButton = new JButton("+");
    subtractButton = new JButton("-");
    multiplyButton = new JButton("×");
    divideButton = new JButton("÷");
    equalsButton = new JButton("=");
    signButton = new JButton("±");
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
    backspace = new JButton("←");
    decimal = new JButton(".");
    fractionDisplayButton = new JButton("D");
    leftParenthesisButton = new JButton("(");
    rightParenthesisButton = new JButton(")");
    
    // Languages Menu Items
    languages = new JMenu("Languages");
    english = new JMenuItem("English");
    spanish = new JMenuItem("Spanish");
    french = new JMenuItem("French");
    portugese = new JMenuItem("Portugese");
    japanese = new JMenuItem("Japanese");
    russian = new JMenuItem("Russian");
    
    // Menu items
    print = new JMenuItem("Print");
    settings = new JMenu("Settings");
    help = new JMenu("Help");
    fileTab = new JMenu("File");
    about = new JMenuItem("About");
    fileSetting = new JMenuItem("Save Recorded Calculations");
    helpPage = new JMenuItem("Instructions");
    recordButton = new JMenuItem("Toggle Record");
    
    
    // Create menubar
    menuBar = new JMenuBar();
    
    
    
  }

  /**
   * Set-up the components used by this window.
   */
  private void setComponents()
  {
    // Configure colors
    Color lightBlue = new Color(210, 237, 255, 255);
    Color gray = new Color(204, 204, 204, 255);
    Color yellow = new Color(131, 139, 82, 255);
    Color green = new Color(99, 164, 157, 255);
    BufferedImage rimplexLogo = null;
    
    // Layout
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    // Add buttons
    mainPanel.add(displayLabel);
    mainPanel.add(inputTextField);
    southPanel.add(signButton);
    southPanel.add(clearButton);
    southPanel.add(backspace);
    southPanel.add(addButton);
    southPanel.add(resetButton);
    southPanel.add(one);
    southPanel.add(two);
    southPanel.add(three);
    southPanel.add(subtractButton);
    southPanel.add(fractionDisplayButton);
    southPanel.add(four);
    southPanel.add(five);
    southPanel.add(six);
    southPanel.add(multiplyButton);
    southPanel.add(leftParenthesisButton);
    southPanel.add(seven);
    southPanel.add(eight);
    southPanel.add(nine);
    southPanel.add(divideButton);
    southPanel.add(rightParenthesisButton);
    southPanel.add(zero);
    southPanel.add(imaginaryButton);
    southPanel.add(equalsButton);
    southPanel.add(decimal);
    southPanel.add(resultButton);

    resultButton.setBorderPainted(false);
    resultButton.setContentAreaFilled(false);
    hideResultButton.setBorderPainted(false);
    hideResultButton.setContentAreaFilled(false);
    resultPanel.add(hideResultButton, BorderLayout.LINE_END);

    resultDisplayArea.setEditable(false);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    resultPanel.add(scroll);

    // Set content area false
    resultButton.setContentAreaFilled(false);
    decimal.setContentAreaFilled(false);
    backspace.setContentAreaFilled(false);
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
    imaginaryButton.setContentAreaFilled(false);
    multiplyButton.setContentAreaFilled(false);
    subtractButton.setContentAreaFilled(false);
    leftParenthesisButton.setContentAreaFilled(false);
    rightParenthesisButton.setContentAreaFilled(false);
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
    imaginaryButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    subtractButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    fractionDisplayButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    signButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    backspace.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    decimal.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    leftParenthesisButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    rightParenthesisButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

    //Fonts
    Font timesFont = new Font("Times New Roman", Font.BOLD, 16);
    Font dialogFont = new Font("Dialog", Font.BOLD, 16);
    Font dialogFont2 = new Font("Dialog", 0, 16);
    zero.setFont(timesFont);
    one.setFont(timesFont);
    two.setFont(timesFont);
    three.setFont(timesFont);
    four.setFont(timesFont);
    five.setFont(timesFont);
    six.setFont(timesFont);
    seven.setFont(timesFont);
    eight.setFont(timesFont);
    nine.setFont(timesFont);
    equalsButton.setFont(dialogFont);
    decimal.setFont(dialogFont);
    addButton.setFont(dialogFont);
    multiplyButton.setFont(dialogFont);
    subtractButton.setFont(dialogFont);
    divideButton.setFont(dialogFont);
    leftParenthesisButton.setFont(dialogFont);
    rightParenthesisButton.setFont(dialogFont);
    fractionDisplayButton.setFont(timesFont);
    resetButton.setFont(timesFont);
    clearButton.setFont(timesFont);
    backspace.setFont(timesFont);
    imaginaryButton.setFont(dialogFont);
    signButton.setFont(dialogFont2);
    
    // Set backroung/foreground
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
    decimal.setForeground(green);
    backspace.setForeground(yellow);
    fractionDisplayButton.setForeground(green);
    leftParenthesisButton.setForeground(green);
    rightParenthesisButton.setForeground(green);
    divideButton.setForeground(green);
    decimal.setForeground(green);

    // Sizes
    resultButton.setPreferredSize(new Dimension(30, 30));
    zero.setPreferredSize(new Dimension(75, 30));
    one.setPreferredSize(new Dimension(30, 30));
    two.setPreferredSize(new Dimension(30, 30));
    three.setPreferredSize(new Dimension(30, 30));
    four.setPreferredSize(new Dimension(30, 30));
    five.setPreferredSize(new Dimension(30, 30));
    six.setPreferredSize(new Dimension(30, 30));
    seven.setPreferredSize(new Dimension(30, 30));
    eight.setPreferredSize(new Dimension(30, 30));
    nine.setPreferredSize(new Dimension(30, 30));
    equalsButton.setPreferredSize(new Dimension(30, 30));
    resetButton.setPreferredSize(new Dimension(30, 30));
    clearButton.setPreferredSize(new Dimension(30, 30));
    divideButton.setPreferredSize(new Dimension(30, 30));
    multiplyButton.setPreferredSize(new Dimension(30, 30));
    addButton.setPreferredSize(new Dimension(30, 30));
    imaginaryButton.setPreferredSize(new Dimension(30, 30));
    subtractButton.setPreferredSize(new Dimension(30, 30));
    fractionDisplayButton.setPreferredSize(new Dimension(30, 30));
    signButton.setPreferredSize(new Dimension(30, 30));

    JPanel test1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
    JPanel test2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
    JPanel test3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
    JPanel test4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
    JPanel test5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
    test1.add(signButton);
    test1.add(clearButton);
    test1.add(backspace);
    test1.add(addButton);
    test1.add(resetButton);
    southPanel.add(test1);
    test2.add(one);
    test2.add(two);
    test2.add(three);
    test2.add(subtractButton);
    test2.add(fractionDisplayButton);
    southPanel.add(test2);
    test3.add(four);
    test3.add(five);
    test3.add(six);
    test3.add(multiplyButton);
    test3.add(leftParenthesisButton);
    southPanel.add(test3);
    test4.add(seven);
    test4.add(eight);
    test4.add(nine);
    test4.add(divideButton);
    test4.add(rightParenthesisButton);
    southPanel.add(test4);
    test5.add(zero);
    test5.add(imaginaryButton);
    test5.add(equalsButton);
    test5.add(decimal);
    test1.setBackground(gray);
    test2.setBackground(gray);
    test3.setBackground(gray);
    test4.setBackground(gray);
    test5.setBackground(gray);
    southPanel.add(test5);

    backspace.setPreferredSize(new Dimension(30, 30));
    decimal.setPreferredSize(new Dimension(30, 30));

    leftParenthesisButton.setPreferredSize(new Dimension(30, 30));
    rightParenthesisButton.setPreferredSize(new Dimension(30, 30));
    leftParenthesisButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    rightParenthesisButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

    southPanel.setPreferredSize(new Dimension(300, 250));
    mainPanel.setPreferredSize(new Dimension(50, 50));
    
    // Set borders
    inputTextField.setBorder(null);
    resultPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    testPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    southPanel.setBorder(new EmptyBorder(10, 10, 10, 30));

    // set image
    try
    {
      rimplexLogo = ImageIO.read(new File("rimplex/images/logoRimplex.png"));
    }
    catch (IOException e)
    {
      try
      {
        rimplexLogo = ImageIO.read(new File("images/logoRimplex.png"));
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
    }

    JLabel rimplexHolder = new JLabel(new ImageIcon(rimplexLogo));
    rimplexHolder.setPreferredSize(new Dimension(50, 50));

    testPanel.add(rimplexHolder, BorderLayout.NORTH);
    testPanel.add(mainPanel, BorderLayout.CENTER);
    testPanel.add(southPanel, BorderLayout.SOUTH);
    testPanel.add(resultButton, BorderLayout.EAST);
    this.add(testPanel, BorderLayout.CENTER);
    this.add(resultPanel, BorderLayout.LINE_END);
    pack();
    setLocationRelativeTo(null);
  
    help.add(about);
    help.add(helpPage);
    
    languages.add(english);

    languages.add(spanish);
    languages.add(french);  
    languages.add(portugese);
    languages.add(japanese);
    languages.add(russian);
    
    fileTab.add(fileSetting);
    settings.add(languages);
    fileTab.add(recordButton);
    fileTab.add(print);
    menuBar.add(fileTab);
    menuBar.add(settings);
    menuBar.add(help);
    menuBar.setPreferredSize(new Dimension(100,25));
    this.add(menuBar, BorderLayout.NORTH);  
  }

  /**
   * Set the listeners for the necessary components.
   */
  private void setListeners()
  {
    // add listeners
    addButton.addActionListener(this);
    this.addKeyListener(this);
    addButton.addKeyListener(this);
    imaginaryButton.addActionListener(this);
    imaginaryButton.addKeyListener(this);
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
    backspace.addActionListener(this);
    decimal.addActionListener(this);
    resultButton.addActionListener(this);
    resultButton.addKeyListener(this);
    leftParenthesisButton.addActionListener(this);
    leftParenthesisButton.addKeyListener(this);
    rightParenthesisButton.addActionListener(this);
    rightParenthesisButton.addKeyListener(this);
    hideResultButton.addActionListener(this);
    hideResultButton.addKeyListener(this);
    fractionDisplayButton.addActionListener(this);
    fractionDisplayButton.addKeyListener(this);
    inputTextField.addKeyListener(this);
    zero.addActionListener(this);
    zero.addKeyListener(this);
    one.addActionListener(this);
    one.addKeyListener(this);
    two.addActionListener(this);
    two.addKeyListener(this);
    three.addActionListener(this);
    three.addKeyListener(this);
    four.addActionListener(this);
    four.addKeyListener(this);
    five.addActionListener(this);
    five.addKeyListener(this);
    six.addActionListener(this);
    six.addKeyListener(this);
    seven.addActionListener(this);
    seven.addKeyListener(this);
    eight.addActionListener(this);
    eight.addKeyListener(this);
    nine.addActionListener(this);
    nine.addKeyListener(this);
    
    // Language Option Listeners
    english.addActionListener(this);
    spanish.addActionListener(this);

    french.addActionListener(this);  
    portugese.addActionListener(this);
    japanese.addActionListener(this);
    russian.addActionListener(this);
    
    // Menu Item Listeners
    print.addActionListener(this);
    recordButton.addActionListener(this);
    about.addActionListener(this);
    fileSetting.addActionListener(this);
    helpPage.addActionListener(this);
  }

  /**
   * Handles any new button presses or other actions.
   */
  public void actionPerformed(ActionEvent e)
  {
    // Call helper method to notify application of a change in language if there is one.
    changeLanguage(e);
    
    // Toggle recording, toggles boolean value and highlighting is handled here.
    if (e.getSource() == recordButton)
    {
      recordingEnabled = !recordingEnabled;
      if (recordingEnabled)
      {
        recordButton.setForeground(new Color(128, 0, 0));
      }
      else
      {
        // Default color
        recordButton.setForeground(UIManager.getColor("TextField.foreground"));
      }
    }
    
    // Open systems print dialog if selected
    if (e.getSource() == print)
    {
      try
      {
        resultDisplayArea.print();
      }
      catch (PrinterException e1)
      {
        e1.printStackTrace();
      }
    }
    
    // Open about dialog if selected
    if (e.getSource() == about) {
      aboutDialog = AboutDialog.getInstance();
      aboutDialog.display();
    }
    
    // Open save dialog
    if (e.getSource() == fileSetting) {
       FileDialog.saveCalcs(recordHistory);
    }
    
    // Open help webpage in default browser
    if (e.getSource() == helpPage) {
      try
      {
        Desktop.getDesktop().open(new File("webpages/helpPage.html"));
      }
      catch (IOException e1)
      {
        System.out.println("Unable to open webpage. May be incorrect directory.");
        e1.printStackTrace();
      }
    }
    
    boolean par = false;
    String command = e.getActionCommand(); 
    String inputField = inputTextField.getText().trim();
    if (inputTextField.getText().contains(")") || command.equals("×") || command.equals("÷")
        || !inputTextField.getText().contains("("))
    {
      par = true;
      inputField = inputField.replace(")", "");
      inputField = inputField.replace("(", "");
    }
    
    WarningDialog warningDialog = WarningDialog.getInstance();
    inputField = inputField.trim();
    inputField = inputField.replace("𝑖", "i");
    String operators = "+-÷×";
    String result = calculator.getResult();

    if (!(inputField.matches("^[0-9i+-.]*$")) && inputField.charAt(0) != '(')
    {
      if (!inputField.trim().equals("") && (operators.contains(command) || command.equals("=")))
      {
        warningDialog.displayDialog();
      }
    }
    else if (operators.contains(command))
    {
      if (par)
      {
        if ((command.equals("×") || command.equals("÷"))
            && ((inputField.length() != 0) && (inputField.charAt(inputField.length() - 1) == '+'
                || inputField.charAt(inputField.length() - 1) == '-')))
        {
          warningDialog.displayDialog();
        }
        else if (inputTextField.getText().contains("(") && !inputTextField.getText().contains(")"))
        {
          warningDialog.displayDialog();
        }
        else
        {
          operationEvent(command);
        }
      }
      else
      {
        if (!inputField.contains("+") && !inputField.contains("-")
            && inputField.charAt(inputField.length() - 1) != '(')
        {
          inputTextField.setText(inputField += command);
        }
        else
        {
          warningDialog.displayDialog();
        }

      }
    }
    
    // Equals button
    else if (command.equals("="))
    {
      equalsEvent();
    }

    // Clear button
    if (command.equals("C"))
    {
      inputTextField.setText("");
    }

    // Reset button
    if (command.equals("R"))
    {
      this.clear();
      calculator.clear();
    }


    

    // Sign switch button
    if (command.equals("±") && !inputField.equals(""))

    {
      if (inputField.contains("("))
      {
        if (inputField.charAt(1) != '-')
        {
          inputTextField.setText("(-" + inputField.substring(1, inputField.length()));
        }
        else
        {
          StringBuffer newString = new StringBuffer(inputField);
          newString.deleteCharAt(1);
          inputTextField.setText(newString.toString());
        }

      }
      if (!inputField.contains("("))
        if (inputField.charAt(0) != '-')
        {
          inputTextField.setText("-" + inputField);
        }
        else
        {
          inputTextField.setText(inputField.substring(1, inputField.length()));
        }
    }
    // Expand history
    if (command.equals(">"))
    {
      resultButton.setVisible(false);
      resultPanel.setVisible(true);
      pack();
      setLocationRelativeTo(null);
    }

    // Collapse history
    if (command.equals("<"))
    {
      resultButton.setVisible(true);
      resultPanel.setVisible(false);
      pack();
      setLocationRelativeTo(null);
      resultButton.setText(">");
    }

    // When enabling fraction display.
    if (command.equals("D"))
    {
      fractionDisplayButton.setText("F");
      calculator.setFractionDisplay(true);
      fractionDisplayButton.setContentAreaFilled(true);
      fractionDisplayButton.setBackground(new Color(210, 237, 255, 255));
    }


    // When disabling fraction display.

    if (command.equals("F"))
    {
      fractionDisplayButton.setText("D");
      calculator.setFractionDisplay(false);
      fractionDisplayButton.setContentAreaFilled(false);
    }
    
    // If command is a number append it.
    if (command.matches("[0-9]"))
    {
      if (!hasImaginary())
      {
        inputTextField.setText(inputField += command);
      }
      else
      {
        warningDialog.displayDialog("Please enter a complex number in standard form.");
      }
    }

    // When the imaginary number button is pressed.
    if (command.equals("𝑖"))
    {
      if (inputField.length() != 0)
      {
        if (inputField.contains("(") && !(inputField.contains("-") || inputField.contains("+")))
        {
          warningDialog.displayDialog();
        }
        else if (inputField.charAt(inputField.length() - 1) != '.' && !hasImaginary()
            && !inputField.contains("i"))
        {
          inputTextField.setText(inputField += "𝑖");
        }
        else
        {
          warningDialog.displayDialog();
        }
      }
      else
      {
        inputTextField.setText(inputField += "𝑖");
      }
    }
    
    // When left paranthesis is pressed.
    if (command.equals("("))
    {
      if (inputField.trim().equals(""))
      {
        inputTextField.setText(inputField += "(");
      }
      else
      {
        warningDialog.displayDialog("Paranthesis must be at beginning of input for complex calculations.");
      }
    }
    
    // When right paranthesis is pressed.
    if (command.equals(")"))
    {
      if (!inputField.contains("(") || inputField.contains(")"))
      {
        warningDialog.displayDialog("Must exactly one of each paranthesis correctly.");
      }
      else if (inputField.charAt(inputField.length() - 1) == '-'
          || inputField.charAt(inputField.length() - 1) == '+'
          || inputField.charAt(inputField.length() - 1) != 'i'
          || !(inputField.contains("+") || inputField.contains("-")))
      {
        warningDialog.displayDialog("Please enter a complex number in standard form if using paranthesis.");
      }
      else
      {
        inputTextField.setText(inputField += ")");
      }
    }

    if (command.equals("."))
    {
      if (inputField.length() == 0)
      {
        String newText = inputField.concat(".");
        inputTextField.setText(newText);
      }
      else if (!hasDecimal() && !hasImaginary())
      {
        inputTextField.setText(inputField += ".");
      }
      else
      {
        warningDialog.displayDialog();
      }
    }
    
    if (command.equals("←") && inputField.length() > 0)
    {
      inputTextField.setText(inputField.substring(0, inputField.length() - 1));
    }
  }

  /**
   * Code to be run if the equals button is pressed.
   */
  public void equalsEvent()
  {
    String inputField = inputTextField.getText().trim();
    inputField = inputField.replace("𝑖", "i");
    inputField = inputField.replace("(", "");
    inputField = inputField.replace(")", "");
    WarningDialog warningDialog = WarningDialog.getInstance();
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
      boolean valid = calculator.formResult();
      if (valid)
      {
        displayLabel
            .setText(displayLabel.getText() + Calculator.formatItalic(calculator.getResult()));
        inputTextField.setText("");
        resultHistory += displayLabel.getText() + "\n";
        
        // If recording is enabled append calculations
        if (recordingEnabled)
        {
          recordHistory += displayLabel.getText() + "\n";
        }
        
        resultDisplayArea.setText(resultHistory);
      }
      else
      {
        warningDialog.displayDialog();
        clear();
      }
    }
  }

  public void operationEvent(String command)
  {
    String inputField = inputTextField.getText().trim();

    inputField = inputField.replace(")", "");
    inputField = inputField.replace("(", "");
    

    String result = calculator.getResult();

    inputField = inputField.replace("𝑖", "i");

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
        if (result.contains("/"))
        {
          WarningDialog warningDialog = WarningDialog.getInstance();
          warningDialog.displayDialog();
          clear();
        }
        else
        {
          calculator.setLeftOperand(result);
          calculator.setOperator(command);
          displayLabel
              .setText(calculator.formatDisplayOperand(calculator.getLeftOperand()) + command);
        }
      }
    }
    else if (calculator.getLeftOperand() != null && !calculator.getLeftOperand().trim().equals(""))
    {
      if (!inputField.equals("") && (calculator.getRightOperand() == null
          || calculator.getRightOperand().trim().equals("")))
      {
        String resultString = calculator.formatDisplayOperand(calculator.getLeftOperand())
            + calculator.getOperator();
        calculator.setRightOperand(inputField);
        System.out.println(calculator.getRightOperand());
        resultString += calculator.formatDisplayOperand(calculator.getRightOperand());
        boolean valid = calculator.formResult();
        if (valid)
        {
          resultString += "=" + calculator.getResult();
          calculator.setOperator(command);
          calculator.setLeftOperand(calculator.getResult());
          displayLabel.setText(calculator.formatDisplayOperand(calculator.getResult()) + command);
          resultHistory += resultString + "\n";
          resultDisplayArea.setText(resultHistory);
        }
        else
        {
          WarningDialog warningDialog = WarningDialog.getInstance();
          warningDialog.displayDialog();
          clear();
        }
      }
    }
    inputTextField.setText("");
  }
  
  /**
   * When called it reformats all text fields that display i to be italisized.
   * @throws BadLocationException 
   */
  public void formatImaginarySymbol() throws BadLocationException
  { 
    // Set textpane and set up default styles
    JTextPane textPane = new JTextPane();
    
    // Create attribute set for imaginary number font
    SimpleAttributeSet imagNumAttributes = new SimpleAttributeSet();
    StyleConstants.setItalic(imagNumAttributes, true);
    StyleConstants.setFontFamily(imagNumAttributes, "Times New Roman");
    StyleConstants.setFontSize(imagNumAttributes, textPane.getFont().getSize() + 4);
    
    StyledDocument doc = textPane.getStyledDocument();
    Style style = textPane.addStyle("", null);
    
    style.addAttributes(imagNumAttributes);
    doc.insertString(doc.getLength(), "i", style);
  }

  /**
   * keyTyped method inherited from JFrame.
   * This method essentially maps physical keys to the GUI buttons.
   */
  @Override
  public void keyTyped(KeyEvent e)
  {
    if (e.getKeyChar() == 'i')
    {
      imaginaryButton.doClick();
    }

    // For numbers
    if (e.getKeyChar() > 47 && e.getKeyChar() < 58)
    {
      switch(e.getKeyChar()) {
        case KeyEvent.VK_0:
          zero.doClick();
          break;
        case KeyEvent.VK_1:
          one.doClick();
          break;
        case KeyEvent.VK_2:
          two.doClick();
          break;
        case KeyEvent.VK_3:
          three.doClick();
          break;
        case KeyEvent.VK_4:
          four.doClick();
          break;
        case KeyEvent.VK_5:
          five.doClick();
          break;
        case KeyEvent.VK_6:
          six.doClick();
          break;
        case KeyEvent.VK_7:
          seven.doClick();
          break;
        case KeyEvent.VK_8:
          eight.doClick();
          break;
        case KeyEvent.VK_9:
          nine.doClick();
          break;
      }
    }

    if (e.getKeyChar() == '+')
    {
      addButton.doClick();
    }

    if (e.getKeyChar() == '-')
    {
      subtractButton.doClick();
    }

    if (e.getKeyChar() == '*')
    {
      multiplyButton.doClick();
    }

    if (e.getKeyChar() == '/')
    {
      divideButton.doClick();
    }

    if (e.getKeyChar() == '.')
    {
      decimal.doClick();
    }

    if (e.getKeyChar() == '=' || e.getKeyChar() == KeyEvent.VK_ENTER)
    {
      equalsButton.doClick();
    }

    if (e.getKeyChar() == '(')
    {
      leftParenthesisButton.doClick();
    }

    if (e.getKeyChar() == ')')
    {
      rightParenthesisButton.doClick();
    }
    
    if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE)
    {
      backspace.doClick();
    }
  }
  
  public boolean hasDecimal()
  {
    String[] test = inputTextField.getText().split("\\+|-");

    if (test.length == 1 && test[0].contains(".")
        && !(inputTextField.getText().contains("+") || inputTextField.getText().contains("-")))
    {
      return true;
    }
    else if (test.length == 2 && test[1].contains("."))
    {
      return true;
    }
    return false;
  }

  public boolean hasImaginary()
  {
    String[] test = inputTextField.getText().split("\\+|-");

    if (test.length == 1
        && (test[0].contains("𝑖") || test[0].contains("i") || test[0].contains("?")))
    {
      return true;
    }
    else if (test.length == 2
        && (test[1].contains("𝑖") || test[1].contains("i") || test[1].contains("?")))
    {
      return true;
    }
    return false;
  }

  @Override
  public void keyPressed(KeyEvent e)
  {
  }

  @Override
  public void keyReleased(KeyEvent e)
  {
  }
  
  /**
   * Clear text fields.
   */
  public void clear()
  {
    inputTextField.setText("");
    displayLabel.setText("");
  }
  
  /**
   * Helper method to change languages depending on user input.
   * @param e Action event passed from ActionPerformed.
   */
  private void changeLanguage(ActionEvent e)
  {
  /// Language Listeners
    if (e.getSource() == english)
    {
      settings.setText("Settings");
      print.setText("Print");
      languages.setText("Languages");
      english.setText("English");
      spanish.setText("Spanish");
      french.setText("French");
      portugese.setText("Portugese");
      japanese.setText("Japanese");
      russian.setText("Russian");
      help.setText("Help");
      about.setText("About");
      helpPage.setText("Instructions");
      fileSetting.setText("Save Recorded Calculations");
      recordButton.setText("Toggle Recording");
      fileTab.setText("File");
      
    }
   
    if (e.getSource() == spanish)
    {
      settings.setText("Ajustes");
      print.setText("Impresión");
      languages.setText("Idiomas");
      english.setText("Inglés");
      spanish.setText("Español");
     
      french.setText("Francés");  
      portugese.setText("Portugués");
      japanese.setText("Japonés");
      russian.setText("Ruso");
      help.setText("Ayudar");
      about.setText("Acerca de");
      helpPage.setText("Instrucciones");
      fileSetting.setText("Guardar cálculos registrados");
      recordButton.setText("Alternar grabación");
      fileTab.setText("Archivo");
    }
   
    if (e.getSource() == french)
    {
      settings.setText("Les paramètres");
      print.setText("Imprimer");
      languages.setText("Les langues");
      english.setText("Anglais");
      spanish.setText("Espanol");
     
      french.setText("Français"); 
      portugese.setText("Portugais");
      japanese.setText("Japonais");
      russian.setText("Russe");
      help.setText("Aider");
      about.setText("À propos");
      helpPage.setText("Instructions");
      fileSetting.setText("Enregistrer les calculs enregistrés");
      recordButton.setText("Basculer l'enregistrement");
      fileTab.setText("Déposer");
    }

    if (e.getSource() == portugese)
    {
      settings.setText("Definições");
      print.setText("Impressão");
      languages.setText("Línguas");
      english.setText("Inglês");
      spanish.setText("Espanhol");
      french.setText("Francês");
      portugese.setText("Português");
      japanese.setText("Japonês");
      russian.setText("Russo");
      help.setText("Ajuda");
      about.setText("Cerca de");
      helpPage.setText("Instruções");
      fileSetting.setText("Salvar cálculos registrados");
      recordButton.setText("Alternar gravação");
      fileTab.setText("Arquivo");
    }
    if (e.getSource() == japanese)
    {
      settings.setText("設定");
      print.setText("印刷");
      languages.setText("言語");
      english.setText("英語");
      spanish.setText("スペイン語");
      french.setText("フランス語");
      portugese.setText("ポルトガル語");
      japanese.setText("日本語");
      russian.setText("ロシア");
      help.setText("助けて");
      about.setText("約");
      helpPage.setText("指示");
      fileSetting.setText("記録された計算を保存する");
      recordButton.setText("録音の切り替え");
      fileTab.setText("ファイル");
    }
    if (e.getSource() == russian)
    {
      settings.setText("Настройки");
      print.setText("Распечатать");
      languages.setText("Языки");
      english.setText("английский");
      spanish.setText("испанский");
      french.setText("Французский");
      portugese.setText("Португальский");
      japanese.setText("Японский");
      russian.setText("русский");
      help.setText("Помощь");
      about.setText("О");
      helpPage.setText("инструкции");
      fileSetting.setText("Сохранить записанные расчеты");
      recordButton.setText("Переключить запись");
      fileTab.setText("Файл");
    }
  }
}
