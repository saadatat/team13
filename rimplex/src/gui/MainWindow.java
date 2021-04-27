package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.swing.ScrollPaneConstants.*;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.Timer;
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
public class MainWindow extends JFrame implements ActionListener, KeyListener, ComponentListener
{
  Timer timer;
  int size = 0;
  int size2 = 0;
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
  private JTextArea resultDisplayArea; // History

  // Panels and Panes
  private JButton hideResultButton;
  private JPanel displayPanel;
  private JPanel southPanel;
  private JPanel resultPanel; // History
  private JPanel resultDisplayPanel;
  private JPanel testPanel; //

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

  private Window frame;
  private JPanel newFramePanel;
  private JScrollPane scroll;
  // Values
  private boolean recordingEnabled = false;
  private String resultHistory;
  private String recordHistory;
  private String language;
  private ResourceBundle bundle = ResourceBundle.getBundle("languages/Strings");
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
    this.setSize(320, 440);
    this.setMinimumSize(new Dimension(320, 440));
    this.setMaximumSize(new Dimension(320, 440));
    this.setTitle("rimpleX");
    this.setVisible(true);
    resultHistory = "";
    recordHistory = "";
    centerForm(); // center the window on the screen
    calculator.setFractionDisplay(false);
    this.setFocusable(true);
    language = "English";
    
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
    displayPanel = new JPanel();
    southPanel = new JPanel(new GridLayout(5, 0));
    resultPanel = new JPanel(new BorderLayout());

    resultPanel.setVisible(false);

    displayLabel = new JLabel(" ");

    resultDisplayArea = new JTextArea();
    scroll = new JScrollPane(resultDisplayArea);
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
    languages = new JMenu(bundle.getString("Languages"));
    english = new JMenuItem(bundle.getString("English"));
    spanish = new JMenuItem(bundle.getString("Spanish"));
    french = new JMenuItem(bundle.getString("French"));
    portugese = new JMenuItem(bundle.getString("Portuguese"));
    japanese = new JMenuItem(bundle.getString("Japanese"));
    russian = new JMenuItem(bundle.getString("Russian"));

    // Menu items
    print = new JMenuItem(bundle.getString("Print"));
    settings = new JMenu(bundle.getString("Settings"));
    help = new JMenu(bundle.getString("Help"));
    fileTab = new JMenu(bundle.getString("File"));
    about = new JMenuItem(bundle.getString("About"));
    fileSetting = new JMenuItem(bundle.getString("Save"));
    helpPage = new JMenuItem(bundle.getString("Instructions"));
    recordButton = new JMenuItem(bundle.getString("Toggle"));

    // Create menubar
    menuBar = new JMenuBar();

    scroll.setVisible(false);

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
    displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

    // Add buttons
    displayPanel.add(displayLabel);
    displayPanel.add(inputTextField);
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

    resultButton.setBorder(null);
    hideResultButton.setBorderPainted(false);
    hideResultButton.setContentAreaFilled(false);
    resultPanel.setBackground(lightBlue);
    resultDisplayPanel = new JPanel(new BorderLayout());
    resultDisplayPanel.setBackground(lightBlue);
    resultDisplayPanel.add(hideResultButton, BorderLayout.LINE_END);
    resultDisplayPanel.add(scroll);
    resultDisplayPanel.setPreferredSize(new Dimension(1, 1));
    scroll.setBorder(null);
    scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
    scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));

    resultDisplayPanel.setMaximumSize(new Dimension(250, 250));
    hideResultButton.setBorder(new EmptyBorder(0, 0, 40, 5));
    resultPanel.add(resultDisplayPanel, BorderLayout.SOUTH);

    scroll.getVerticalScrollBar().setVisible(false);
    resultDisplayArea.setEditable(false);
    resultDisplayArea.setVisible(false);
    resultDisplayArea.setLineWrap(true);
    resultDisplayArea.setWrapStyleWord(false);

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
    hideResultButton.setBorderPainted(false);
    hideResultButton.setContentAreaFilled(false);

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

    // Fonts
    resultButton.setFont(new Font("Dialog", Font.BOLD, 16));
    hideResultButton.setFont(new Font("Dialog", Font.BOLD, 16));
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
    displayPanel.setBackground(lightBlue);
    resultDisplayPanel.setBackground(lightBlue);
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

    JPanel test1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 17, 10));
    JPanel test2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 17, 10));
    JPanel test3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 17, 10));
    JPanel test4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 17, 10));
    JPanel test5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 17, 10));
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

    // Main Panel
    JPanel mainPanel = new JPanel(new BorderLayout());

    backspace.setPreferredSize(new Dimension(30, 30));
    decimal.setPreferredSize(new Dimension(30, 30));
    mainPanel.add(displayPanel, BorderLayout.CENTER);
    mainPanel.add(southPanel, BorderLayout.SOUTH);
    mainPanel.setBackground(gray);
    leftParenthesisButton.setPreferredSize(new Dimension(30, 30));
    rightParenthesisButton.setPreferredSize(new Dimension(30, 30));
    leftParenthesisButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    rightParenthesisButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

    southPanel.setPreferredSize(new Dimension(275, 250));
    displayPanel.setPreferredSize(new Dimension(200, 50));

    // Set borders
    inputTextField.setBorder(null);
    resultPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
    testPanel.setBorder(new EmptyBorder(10, 10, 10, 0));
    southPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

    ImageIcon rimplexIcon = null;
    rimplexIcon = new ImageIcon(MainWindow.class.getResource("/images/logoRimplex.png"));

    JLabel rimplexHolder = new JLabel(rimplexIcon);
    rimplexHolder.setPreferredSize(new Dimension(50, 50));

    mainPanel.add(rimplexHolder, BorderLayout.NORTH);

    testPanel.add(mainPanel, BorderLayout.CENTER);
    testPanel.add(resultButton, BorderLayout.EAST);
    resultButton.setBorder(new EmptyBorder(50, 0, 0, 5));
    this.add(testPanel, BorderLayout.CENTER);

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
    menuBar.setPreferredSize(new Dimension(100, 25));
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

    this.addComponentListener(this);
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
    if (e.getSource() == about)
    {
      aboutDialog = AboutDialog.getInstance();
      aboutDialog.display();
    }

    // Open save dialog
    if (e.getSource() == fileSetting)
    {
      FileDialog.saveCalcs(recordHistory);
    }

    // Open help webpage in default browser
    if (e.getSource() == helpPage)
    {
      openHelpPage();
    }

    boolean par = false;
    String command = e.getActionCommand(); // String representation of command
    String inputField = inputTextField.getText().trim(); // String representation of what is input
    
    //Check against regex as user is typing in.
    System.out.println(isValidInput(inputField, false));
    
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

    // Display warning if an operator/equals is selected when no valid characters are being used.
    if (!(inputField.matches("^[0-9i+-.]*$")) && inputField.charAt(0) != '(')
    {
      if (!inputField.trim().equals("") && (operators.contains(command) || command.equals("=")))
      {
        warningDialog.displayDialog();
      }
    }
    // If not above then continue
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

      Color lightBlue = new Color(210, 237, 255, 255);
      int x = this.getLocation().x;
      int y = this.getLocation().y + this.getHeight();
      newFramePanel = new JPanel(new BorderLayout());
      frame = new Window(this);
      frame.setBackground(lightBlue);
      newFramePanel.setBackground(lightBlue);
      newFramePanel.add(resultPanel, BorderLayout.NORTH);
      newFramePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
      resultDisplayArea.setBorder(new EmptyBorder(10, 10, 10, 10));

      frame.add(newFramePanel);
      frame.setLocation(x + 275, y - 260);
      frame.pack();
      frame.setVisible(true);
      size = 0;
      resultPanel.setVisible(true);

      resultButton.setEnabled(false);

      hideResultButton.setVisible(false);

      timer = new Timer(1, new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {

          size += 16;
          resultDisplayPanel.setPreferredSize(new Dimension(size, 240));
          resultPanel.setPreferredSize(new Dimension(size, 240));
          newFramePanel.setPreferredSize(new Dimension(size, 240));
          frame.setSize(new Dimension(size, 240));
          revalidate();
          repaint();
          pack();

          if (size >= 240)
          {
            timer.stop();
            resultDisplayArea.setVisible(true);
            hideResultButton.setVisible(true);
            scroll.setVisible(true);

          }
        }
      });
      timer.start();
    }

    // Collapse history
    if (command.equals("<"))
    {
      scroll.setVisible(false);
      resultDisplayArea.setVisible(false);
      hideResultButton.setVisible(false);
      size = 240;
      timer = new Timer(1, new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          size -= 16;
          resultDisplayPanel.setPreferredSize(new Dimension(size, 240));
          resultPanel.setPreferredSize(new Dimension(size, 240));
          newFramePanel.setPreferredSize(new Dimension(size, 240));
          frame.setSize(new Dimension(size, 240));
          revalidate();
          repaint();
          pack();

          if (size == 0)
          {
            timer.stop();
            resultPanel.setVisible(false);
            frame.setVisible(false);
            newFramePanel.setVisible(false);
            testPanel.add(resultButton, BorderLayout.EAST);
            resultButton.setEnabled(true);

          }
        }
      });

      timer.start();
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
      // Only appends a number if an imaginary isn't included.
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
        warningDialog
            .displayDialog("Parenthesis must be at beginning of input for complex calculations.");
      }
    }

    // When right paranthesis is pressed.
    if (command.equals(")"))
    {
      if (!inputField.contains("(") || inputField.contains(")"))
      {
        warningDialog.displayDialog(
            "Complex numbers must have only one of each parenthesis in correct form.");
      }
      else if (inputField.charAt(inputField.length() - 1) == '-'
          || inputField.charAt(inputField.length() - 1) == '+'
          || inputField.charAt(inputField.length() - 1) != 'i'
          || !(inputField.contains("+") || inputField.contains("-")))
      {
        warningDialog
            .displayDialog("Please enter a complex number in standard form if using parenthesis.");
      }
      else
      {
        inputTextField.setText(inputTextField.getText() + ")");
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
    
    // Execute as long as there is a valid left operand
    if (calculator.getLeftOperand() != null && !calculator.getLeftOperand().equals(""))
    {
      // There isn't a right operand then set the right operand to 0+0i.
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
        resultHistory += displayLabel.getText() + "\n\n";

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
    // Create inputField string and format it for reading.
    String inputField = inputTextField.getText().trim();
    inputField = inputField.replace(")", "");
    inputField = inputField.replace("(", "");
    inputField = inputField.replace("𝑖", "i");
    
    // Get string of result from calculator, used for running totals.
    String result = calculator.getResult();
    
    // If the calculator currently doesn't have a left operand
    if (calculator.getLeftOperand() == null || calculator.getLeftOperand().trim().equals(""))
    {
      // If the user input isn't blank then assign the command input to the left operand of the calculator.
      // Also display the input on the GUI.
      if (!inputField.equals(""))
      {
        calculator.setLeftOperand(inputField);
        calculator.setOperator(command);
        displayLabel.setText(calculator.formatDisplayOperand(calculator.getLeftOperand())
            + calculator.getOperator());
      }
      // Since the calculator does not have a left operand, if there isn't a previous result for running totals
      // then set the left operand to zero values. This also will display on the GUI.
      else if (result == null || result.trim().equals(""))
      {
        calculator.setLeftOperand("0+0i");
        calculator.setOperator(command);
        displayLabel.setText(calculator.formatDisplayOperand(calculator.getLeftOperand())
            + calculator.getOperator());
      }
      // Retrieve the previous result, but only if not a fraction.
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
          resultHistory += resultString + "\n\n";
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
   * Another method for isValidInput set's to default regex.
   * @param input
   * @param inputIsComplete
   * @return
   */
  public boolean isValidInput(String input)
  {
    return isValidInput(input, true);
  }
  
  /**
   * Checks valid inputs against regex patterns, does NOT account for parantheses.
   * If you experience bugs please let me know.
   * @param input The input to check
   * @param isInputComplete True if the input is being checked right before hitting the equals button
   *        or any sort of similar action that would require the program parse the value.
   *        Set this to false if you just want to check if what the user is typing at the moment is valid.
   * @return Returns True if the input matches the regex pattern.
   */
  public boolean isValidInput(String input, boolean inputIsComplete)
  {
    input = input.replace("𝑖", "i");
    Matcher matcher;
    Pattern patternTyping =
        Pattern.compile("^[-]?[0-9]*[\\.]?[0-9]*((?<=[0-9])[+-])?[0-9]*((?<=[+-][0-9]+)[\\.])?[0-9]*[i]?$");
    Pattern patternParsing =
        Pattern.compile("^(\\((?=.+\\)))?(-?([0-9]+(\\.[0-9]+)?))?(([-]|(?<=[0-9])[+])(?=.*i))?((?<=[+-])([0-9]+(\\.[0-9]+)?))?i?((?<=\\(.+)\\))?$");
    
    if (inputIsComplete)
    {
      matcher = patternTyping.matcher(input);
    } else
    {
      matcher = patternParsing.matcher(input);
    }
    
    return matcher.matches();
  }
  
  /**
   * keyTyped method inherited from JFrame. This method essentially maps physical keys to the GUI
   * buttons.
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
      switch (e.getKeyChar())
      {
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
   * 
   * @param e
   *          Action event passed from ActionPerformed.
   */
  private void changeLanguage(ActionEvent e)
  {
    /// Language Listeners
    if (e.getSource() == english)
    {
      Locale.setDefault(new Locale("en", "US"));
      bundle = ResourceBundle.getBundle("languages/Strings");
      changeButtonLanguage();
    }

    if (e.getSource() == spanish)
    {
      Locale.setDefault(new Locale("es", "ES"));
      bundle = ResourceBundle.getBundle("languages/Strings");
      changeButtonLanguage();
    }

    if (e.getSource() == french)
    {
      Locale.setDefault(new Locale("fr", "FR"));
      bundle = ResourceBundle.getBundle("languages/Strings");
      changeButtonLanguage();
    }

    if (e.getSource() == portugese)
    {
      Locale.setDefault(new Locale("pt", "BR"));
      bundle = ResourceBundle.getBundle("languages/Strings");
      changeButtonLanguage();
    }
    if (e.getSource() == japanese)
    {
      Locale.setDefault(new Locale("ja", "JP"));
      bundle = ResourceBundle.getBundle("languages/Strings");
      changeButtonLanguage();
    }
    if (e.getSource() == russian)
    {
      Locale.setDefault(new Locale("ru", "RU"));
      bundle = ResourceBundle.getBundle("languages/Strings");
      changeButtonLanguage();
    }
  }

  public void componentMoved(ComponentEvent e)
  {
    if (frame != null)
    {
      int x = this.getLocation().x;
      int y = this.getLocation().y + this.getHeight();
      newFramePanel = new JPanel(new BorderLayout());
      frame.setLocation(x + 275, y - 260);
    }
  }

  @Override
  public void componentResized(ComponentEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void componentShown(ComponentEvent e)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void componentHidden(ComponentEvent e)
  {
    // TODO Auto-generated method stub

  }

  public void changeButtonLanguage() {
   
    languages.setText((bundle.getString("Languages")));
    english.setText(bundle.getString("English"));
    spanish.setText(bundle.getString("Spanish"));
    french.setText(bundle.getString("French"));
    portugese.setText(bundle.getString("Portuguese"));
    japanese.setText(bundle.getString("Japanese"));
    russian.setText(bundle.getString("Russian"));

    
    print.setText(bundle.getString("Print"));
    settings.setText(bundle.getString("Settings"));
    help.setText(bundle.getString("Help"));
    fileTab.setText(bundle.getString("File"));
    about.setText(bundle.getString("About"));
    fileSetting.setText(bundle.getString("Save"));
    helpPage.setText(bundle.getString("Instructions"));
    recordButton.setText(bundle.getString("Toggle"));
  }
  
  private void openHelpPage()
  {
    try
    {
      // Get the location of the helpPage within the program
      URL url = MainWindow.class.getResource("/webpages/helpPage.html");
      Path path = Paths.get(url.toURI());
      URL img1 = MainWindow.class.getResource("/images/logoRimplex.png");
      Path imgPath1 = Paths.get(url.toURI());
      URL img2 = MainWindow.class.getResource("/images/logoSagaciousMedia.gif");
      Path imgPath2 = Paths.get(url.toURI());
      
      // Create new temporary directory for webpage and images
      Path tempDir = Files.createTempDirectory("rimplextemp");
      
      // Create temporary file in the above directory
      File temp = File.createTempFile("rimplex", ".html", tempDir.toFile());
      temp.deleteOnExit();
      
      // Add temporary images to directory
      File tempImage1 = File.createTempFile("rimplex", ".html");
      tempImage1.deleteOnExit();
      File tempImage2 = File.createTempFile("rimplex", ".html");
      tempImage2.deleteOnExit();
      
      // Copy local file to temp file
      OutputStream os = new FileOutputStream(temp);
      Files.copy(path, os);
      os = new FileOutputStream(tempImage1);
      Files.copy(imgPath1, os);
      os = new FileOutputStream(tempImage2);
      Files.copy(imgPath2, os);
      
      // Opens in default application
      Desktop.getDesktop().open(temp);
    }
    catch (IOException | URISyntaxException e)
    {
      System.out.println("Unable to open webpage. May be incorrect directory.");
      e.printStackTrace();
    }
  }
}
