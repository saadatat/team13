package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

public abstract class FileDialog
{ 
  public static void saveCalcs()
  {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Specify a location to save calculations");   

    int userSelection = fileChooser.showSaveDialog(null);
    
    String fileName = String.format(null, null);
    File theFile = new File("calculations");
    PrintWriter outFile;
    try
    {
      outFile = new PrintWriter(theFile);
      outFile.print("asdf");
      outFile.close();
    }
    catch (FileNotFoundException e1)
    {
      e1.printStackTrace();
    }
    
    fileChooser.setSelectedFile(theFile);
    
    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();
        System.out.println("Save as file: " + fileToSave.getAbsolutePath());
    }
  }
}
