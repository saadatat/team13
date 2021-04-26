package app;

import gui.*;

import java.io.File;
import java.io.IOException;
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
  public static final String CURRENT_PATH = System.getProperty("user.dir");
  /**
   * The entry point of the application.
   * 
   * @param args
   *          The command line arguments (which are ignored)
   * @throws InterruptedException 
   * @throws InvocationTargetException 
   */
  public static void main(final String[] args) throws InvocationTargetException, InterruptedException
  {
    System.setProperty("apple.laf.useScreenMenuBar", "true");
    System.setProperty("apple.awt.application.name", "rimpleX");
   
      SwingUtilities.invokeAndWait(new Rimplex());
    
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
