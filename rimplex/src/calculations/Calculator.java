package calculations;

import java.io.Serializable;
import java.io.StringReader;
import java.util.List;

public class Calculator
{

  private String leftOperand;
  private String rightOperand;
  private String result;

  /**
   * Default constructor.
   */
  public Calculator()
  {
    leftOperand = null;
    rightOperand = null;
    result = null;
  }
  
  /**
   * The calculate method, takes in a whole string and runs neccessary calculations.
   */
  public String calculate(String inputString)
  {
    StringReader inputReader = new StringReader(inputString);
    String firstTerm = 
        inputString.substring(inputString.indexOf("(") + 1, inputString.indexOf(")"));
    String secondTerm = 
        inputString.substring(inputString.lastIndexOf("(") + 1, inputString.lastIndexOf(")"));
    return "";
  }

  /**
   * Accepts an operand string and returns the complex version.
   * 
   * @param stringToCovert
   *          - the incoming operand string that needs to be converted to complex.
   * @return String - the incoming string converted to complex.
   */
  public static String convertToComplex(String stringToConvert)
  {
    String complexConversion = "";

    if (stringToConvert == null || stringToConvert.trim().equals(""))
    {
      throw new IllegalArgumentException();
    }

    complexConversion = stringToConvert;

    if (complexConversion.charAt(complexConversion.length() - 1) != 'i')
    {
      complexConversion += "+0i";
    }

    return complexConversion;
  }

  /**
   * Accepts an operand string and returns the formatted result to be used for the display.
   * 
   * @param stringToFormat
   *          - the incoming operand string to format.
   * @return String - the formatted operand string.
   */
  public static String formatDisplayOperand(String stringToFormat)
  {
    String formattedString = "";

    if (stringToFormat == null || stringToFormat.trim().equals(""))
    {
      throw new IllegalArgumentException();
    }

    formattedString = "(" + stringToFormat + ")";

    return formattedString;
  }

  /**
   * getLeftOperand - return the left operand.
   * 
   * @return String the left operand.
   */
  public String getLeftOperand()
  {
    return leftOperand;
  }

  /**
   * getRightOperand - return the right operand.
   * 
   * @return String the right operand.
   */
  public String getRightOperand()
  {
    return rightOperand;
  }

  /**
   * getResult - return the result.
   * 
   * @return String the result.
   */
  public String getResult()
  {
    return result;
  }

  /**
   * setLeftOperand - sets the left operand of the calculator.
   * 
   * @param incomingLeftOperand
   *          - the left operand.
   */
  public void setLeftOperand(String incomingLeftOperand)
  {
    leftOperand = incomingLeftOperand;
  }

  /**
   * setRightOperand - sets the right operand of the calculator.
   * 
   * @param incomingRightOperand
   *          - the right operand.
   */
  public void setRightOperand(String incomingRightOperand)
  {
    rightOperand = incomingRightOperand;
  }

  /**
   * setResult - sets the result of the calculator.
   * 
   * @param incomingResult
   *          - the result.
   */
  public void setResult(String incomingResult)
  {
    result = incomingResult;
  }

}
