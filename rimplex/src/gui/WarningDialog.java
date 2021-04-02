package gui;

import javax.swing.JOptionPane;

/**
 * The Listener for the GUI components.
 * 
 * @author Dylan Moreno
 * @version Rimplex 1.0
 */
public class WarningDialog extends JOptionPane
{
  private static final long serialVersionUID = 6150280268225452704L;
  
  private static WarningDialog instance;

  /**
   * Empty Constructor. Necessary for Singleton.
   */
  private WarningDialog()
  {
    
  }
  
  /**
   * Display default message.
   */
  public void displayDialog()
  {
    showMessageDialog(null, "Invalid input. Please try again.", "Warning", JOptionPane.WARNING_MESSAGE);
  }
  
  /**
   * Display custom message.
   */
  public void displayDialog(String message)
  {
    showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
  }
  
  /**
   * Display custom message and title.
   */
  public void displayDialog(String message, String title)
  {
    showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
  }
  
  /**
   * Returns this singleton instance.
   * @return
   */
  public static WarningDialog getInstance()
  {
    if (instance == null)
    {
      instance = new WarningDialog();
    }
    
    return instance;
  }
}
