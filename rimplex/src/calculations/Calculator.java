package calculations;

import java.awt.Font;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator
{

  private String leftOperand;
  private String rightOperand;
  private String result;
  private String operator;

  /**
   * Default constructor.
   */
  public Calculator()
  {
    leftOperand = null;
    operator = null;
    rightOperand = null;
    result = null;
  }

  /**
   * The calculate method, takes in a whole string and runs neccessary calculations.
   */
  public String calculate(String inputString)
  {
    String firstTerm = inputString.substring(inputString.indexOf("(") + 1,
        inputString.indexOf(")"));
    String secondTerm = inputString.substring(inputString.lastIndexOf("(") + 1,
        inputString.lastIndexOf(")"));

    Pattern pattern = Pattern.compile("\\)(.*?)\\(", Pattern.DOTALL);
    Matcher matcher = pattern.matcher(inputString);

    String operator = "";
    if (matcher.find())
      operator = matcher.group(1);
    return operator;
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
  public String formatDisplayOperand(String stringToFormat)
  {
    String formattedString = "";

    if (stringToFormat == null || stringToFormat.trim().equals(""))
    {
      throw new IllegalArgumentException();
    }

    formattedString = "(" + stringToFormat + ")";
    formattedString = formatItalic(formattedString);

    return formattedString;
  }

  /**
   * Converts string i to italics.
   * 
   * @param stringToFormat
   * @return
   */
  public static String formatItalic(String stringToFormat)
  {
    String formattedString = "";
    if (stringToFormat == null || stringToFormat.trim().equals(""))
    {
      throw new IllegalArgumentException();
    }
    formattedString = stringToFormat;
    formattedString = formattedString.replace("i", "𝑖");

    return formattedString;
  }

  /**
   * getLeftOperand - return the left operand.
   * 
   * @return String the left operand.
   */
  public String getLeftOperand()
  {
    if (leftOperand == null)
    {
      return null;
    }
    if (leftOperand.contains("+1i"))
    {
      return leftOperand.replace("+1i", "+i");
    }
    else if (leftOperand.contains("-1i"))
    {
      return leftOperand.replace("-1i", "-i");
    }
    return leftOperand;
  }

  /**
   * getOperator - return the operator.
   * 
   * @return String the operator.
   */
  public String getOperator()
  {
    return operator;
  }

  /**
   * getRightOperand - return the right operand.
   * 
   * @return String the right operand.
   */
  public String getRightOperand()
  {
    if (rightOperand == null)
    {
      return null;
    }
    if (rightOperand.contains("+1i"))
    {
      return rightOperand.replace("+1i", "+i");
    }
    else if (rightOperand.contains("-1i"))
    {
      return rightOperand.replace("-1i", "-i");
    }
    return rightOperand;
  }

  /**
   * getResult - return the result.
   * 
   * @return String the result.
   */
  public String getResult()
  {
    if (result == null)
    {
      return null;
    }
    if (result.contains("+1i"))
    {
      return result.replace("+1i", "+i");
    }
    else if (result.contains("-1i"))
    {
      return result.replace("-1i", "-i");
    }
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
    if (incomingLeftOperand.contains("+i"))
    {
      leftOperand = incomingLeftOperand.replace("+i", "+1i");
    }
    else if (incomingLeftOperand.contains("-i"))
    {
      leftOperand = incomingLeftOperand.replace("-i", "-1i");
    }
    else
    {
      leftOperand = incomingLeftOperand;
    }

  }

  /**
   * setOperator - sets the operator.
   * 
   * @param incomingOperator
   *          - the operator.
   */
  public void setOperator(String incomingOperator)
  {
    operator = incomingOperator;
  }

  /**
   * setRightOperand - sets the right operand of the calculator.
   * 
   * @param incomingRightOperand
   *          - the right operand.
   */
  public void setRightOperand(String incomingRightOperand)
  {

    if (incomingRightOperand.contains("+i"))
    {
      rightOperand = incomingRightOperand.replace("+i", "+1i");
    }
    else if (incomingRightOperand.contains("-i"))
    {
      rightOperand = incomingRightOperand.replace("-i", "-1i");
    }
    else
    {
      rightOperand = incomingRightOperand;
    }

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

  /**
   * adds the operands and reset values besides result.
   */
  public void add()
  {

    result = Operations.addition(leftOperand, rightOperand);

    leftOperand = null;
    rightOperand = null;
    operator = null;
  }

  /**
   * subtracts the operands and reset values besides result.
   */
  public void subtract()
  {

    result = Operations.subtraction(leftOperand, rightOperand);

    leftOperand = null;
    rightOperand = null;
    operator = null;
  }

  /**
   * multiply the operands and reset values besides result.
   */
  public void multiply()
  {

    result = Operations.multiply(leftOperand, rightOperand);

    leftOperand = null;
    rightOperand = null;
    operator = null;
  }

  /**
   * divide the operands and reset values besides result.
   */
  public void divide()
  {

    result = Operations.divide(leftOperand, rightOperand);

    leftOperand = null;
    rightOperand = null;
    operator = null;
  }

  /**
   * calls the appropriate calculation method.
   */
  public void formResult()
  {
    if (operator.equals("+") && validOperands())
    {
      add();
    }
    else if (operator.equals("-") && validOperands())
    {
      subtract();
    }
    else if (operator.equals("x") && validOperands())
    {
      multiply();
    }
    else if (operator.equals("/") && validOperands())
    {
      divide();
    }
  }

  /**
   * checks if both operands are valid.
   * 
   * @return boolean whether the operands are valid.
   */
  public boolean validOperands()
  {
    boolean returnValue = true;

    if (leftOperand == null || leftOperand.trim().equals("") || rightOperand == null
        || rightOperand.trim().equals(""))
    {
      returnValue = false;
    }

    return returnValue;
  }

  /**
   * resets all calculator values.
   */
  public void clear()
  {
    result = null;
    leftOperand = null;
    rightOperand = null;
    operator = null;
  }
}
