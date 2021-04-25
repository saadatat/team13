package gui;
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
  private String language = "English";

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
    if (language.equals("English")) {
    showMessageDialog(null, "rimpleX is a desktop calculator designed to work with these numbers:\n\n• Real\n• Imaginary\n• Complex\n\nDeveloped by SagaciousMedia.", "About rimpleX", JOptionPane.PLAIN_MESSAGE);
    }
    if (language.equals("Spanish")) {
    showMessageDialog(null,"rimpleX es una calculadora de escritorio diseñada para trabajar con estos números: \n \n • Real \n • Imaginario \n • Complejo \n \nDesarrollado por SagaciousMedia.", "Acerca de rimpleX", JOptionPane.PLAIN_MESSAGE);
    }
    if (language.equals("French")) {
    showMessageDialog(null,"rimpleX est une calculatrice de bureau conçue pour fonctionner avec ces nombres: \n \n • Réel \n • Imaginaire \n • Complexe \n \n Développé par SagaciousMedia.", "À propos de rimpleX", JOptionPane.PLAIN_MESSAGE);
    }
    if (language.equals("Portugese")) {
    showMessageDialog(null,"rimpleX é uma calculadora de desktop projetada para trabalhar com estes números: \n \n • Real \n • Imaginário \n • Complexo \n \n Desenvolvido por SagaciousMedia.", "Sobre rimpleX", JOptionPane.PLAIN_MESSAGE);
    }
    if (language.equals("Japanese")) {
    showMessageDialog(null,"rimpleXは次の数値で動作するように設計されたデスクトップ計算機です：\n \n•実数\n•虚数\n•複素数\n \nSagaciousMediaによって開発されました。", "rimpleXについて", JOptionPane.PLAIN_MESSAGE);
    }
    if (language.equals("Russian")) {
    showMessageDialog(null,"rimpleX - настольный калькулятор, предназначенный для работы со следующими числами: \n \n • Реальные \n • Мнимые \n • Сложные \n \n Разработано SagaciousMedia.", "О rimpleX", JOptionPane.PLAIN_MESSAGE);
    }
  }
  
  public void setLanguage(String incoming) {
    language = incoming;
  }
}
