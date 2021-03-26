package gui;

public class Listener
{
  private static Listener listener;
  
  /**
   * Default Constructor. Necessary for singleton.
   */
  private Listener()
  {
    // do nothing
  }
  
  /**
   * Create Singleton for Listener.
   * 
   * @return the one and only Listener
   */
  public static Listener getInstance()
  {
    if (listener == null)
    {
      listener = new Listener();
    }
    
    return listener;
  }
}
