package app;

import gui.*;
import calculations.*;
import java.lang.reflect.*;
import javax.swing.*;

/**
 * The main class for the rimplex application.
 * Adding comment to test if I can.
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
    try
    {
      SwingUtilities.invokeAndWait(new Rimplex());
    }
    catch (InterruptedException | InvocationTargetException e)
    {
      System.out.println("Unable to start the GUI.");
    }
  }

  /**
   * The code to execute in the event dispatch thread.
   */
  public void run()
  {
    MainWindow window = new MainWindow();
    window.setVisible(true);
  }
}
