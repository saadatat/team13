package gui;
import java.util.ResourceBundle;

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
    ResourceBundle bundle = ResourceBundle.getBundle("Strings");
    showMessageDialog(null, bundle.getString("AboutDialog"), bundle.getString("AboutDialogHeader"), JOptionPane.PLAIN_MESSAGE);
    
  }
  
}
