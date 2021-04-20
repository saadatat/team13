package app;

import gui.*;
import java.lang.reflect.*;
import javax.swing.*;

/**
 * The main class for the rimplex application. Adding comment to test if I can.
 * 
 * @author Max Berger
 * @version 1.0
 */
public class Rimplex implements Runnable
{
  /**
   * The entry point of the application.
   * 
   * @param args
   *          The command line arguments (which are ignored)
   */
  public static void main(final String[] args)
  {
    System.setProperty("apple.laf.useScreenMenuBar", "true");
    System.setProperty("apple.awt.application.name", "rimpleX");
    try
    {
      SwingUtilities.invokeAndWait(new Rimplex());
    }
    catch (InterruptedException | InvocationTargetException e)
    {
      System.out.println("Unable to start the GUI.");
      System.out.println(e);
    }
  }

  /**
   * The code to execute in the event dispatch thread.
   */
  public void run()
  {
    MainWindow window = new MainWindow();
    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    window.setVisible(true);
  }
}
