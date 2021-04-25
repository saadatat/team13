package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

/**
 * Utility class to bring up to save a file based on a string of text.
 * 
 * @author Max Berger
 *
 */
public abstract class FileDialog
{
  private static String language = "English";

  /**
   * Utility method, saves file.
   * 
   * @param saveText
   *          The text to be written out.
   */
  public static void saveCalcs(String saveText)
  {
    JFileChooser fileChooser = new JFileChooser();
    if (language.equals("English"))
    {
      fileChooser.setDialogTitle("Specify a location to save calculations");
    }

    if (language.equals("Spanish"))
    {
      fileChooser.setDialogTitle("Especifique una ubicación para guardar los cálculos");
    }

    if (language.equals("French"))
    {
      fileChooser.setDialogTitle("Spécifiez un emplacement pour enregistrer les calculs");
    }
    if (language.equals("Portugese"))
    {
      fileChooser.setDialogTitle("Especifique um local para salvar os cálculos");
    }
    if (language.equals("Japanese"))
    {
      fileChooser.setDialogTitle("計算を保存する場所を指定します");
    }
    if (language.equals("Russian"))
    {
      fileChooser.setDialogTitle("Укажите место для сохранения расчетов");
    }

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

  public static void setLanguage(String incoming)
  {
    language = incoming;
  }

}
