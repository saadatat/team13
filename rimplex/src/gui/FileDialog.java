package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

/**
 * Utility class to bring up to save a file based on a string of text.
 * 
 * @author Max Berger
 *
 */
public abstract class FileDialog
{
  
  
  
  /**
   * Utility method, saves file.
   * 
   * @param saveText
   *          The text to be written out.
   */
  public static void saveCalcs(String saveText)
  {
     ResourceBundle bundle = ResourceBundle.getBundle("Strings");
    JFileChooser fileChooser = new JFileChooser();
    
     fileChooser.setDialogTitle(bundle.getString("FileDialog"));
    
    int userSelection = fileChooser.showSaveDialog(null);

    if (userSelection == JFileChooser.APPROVE_OPTION)
    {
      File fileToSave = fileChooser.getSelectedFile();
      System.out.println("Save as file: " + fileToSave.getAbsolutePath());

      try
      {
        PrintWriter outFile = new PrintWriter(fileToSave);
        outFile.print(saveText);
        outFile.close();
      }
      catch (FileNotFoundException e1)
      {
        e1.printStackTrace();
      }
    }
  }


}
