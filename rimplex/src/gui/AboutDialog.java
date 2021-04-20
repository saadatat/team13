package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * About Dialog.
 * 
 * @author Max Berger
 */
public class AboutDialog extends JOptionPane
{
  private static final long serialVersionUID = 6150280268225452704L;
  
  private static AboutDialog instance = new AboutDialog();

  /**
   * Empty Constructor. Necessary for Singleton.
   */
  private AboutDialog()
  {
  }
  
  /**
   * Get instance.
   * @return Returns instance current or to be created.
   */
  public static AboutDialog getInstance()
  {
    return instance;
  }
  
  /**
   * Display about message.
   */
  public void display()
  {
    showMessageDialog(null, "rimpleX is a desktop calculator designed to work with\n\n       Real\n       IMaginary and\ncomPLEX numbers\n\nDeveloped by SagaciousMedia", "About rimpleX", JOptionPane.PLAIN_MESSAGE);
  }
}
