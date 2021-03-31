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

  /**
   * Default Constructor. Display default message.
   */
  public WarningDialog()
  {
    showMessageDialog(null, "Invalid input. Please try again.", "Warning", JOptionPane.WARNING_MESSAGE);
  }
  
  /**
   * Explicit-Value Constructor. Display custom message.
   */
  public WarningDialog(String message)
  {
    showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
  }
  
  /**
   * Explicit-Value Constructor. Display custom message and title.
   */
  public WarningDialog(String message, String title)
  {
    showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
  }
}
