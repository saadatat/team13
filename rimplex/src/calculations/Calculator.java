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
  private double[] resultDoubles; // Double representation of answer with real first and imaginary second.
  private String operator;
  private boolean fractionDisplay;
  private boolean divisionFormatResult;
  private String fractionResult;

  /**
   * Default constructor.
   */
  public Calculator()
  {
    leftOperand = null;
    operator = null;
    rightOperand = null;
    result = null;
    fractionResult = null;
    fractionDisplay = false;
    resultDoubles = new double[2];
    divisionFormatResult = false;
    
  }

  /**
   * Accepts an operand string and returns the formatted result to be used for the display.
   * Italisizes i and adds parantheses.
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
    formattedString = formattedString.replace("i", "\uD835\uDC56");

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

    if (divisionFormatResult == false) {
      
      if (resultDoubles == null) {
        return "0+0i";
      }
      
      double real = resultDoubles[0];
      double imag = resultDoubles[1];
      result = Operations.formatResult(real, imag, false);
     
      
      if (fractionDisplay) {
        return Operations.formatResult(real, imag, true);
      }
      }else {
        divisionFormatResult = false;
        return result;
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
    resultDoubles = Operations.addition(leftOperand, rightOperand);
    resultResetHelper();
  }

  /**
   * subtracts the operands and reset values besides result.
   */
  public void subtract()
  {
    resultDoubles = Operations.subtraction(leftOperand, rightOperand);
    resultResetHelper();
  }

  /**
   * multiply the operands and reset values besides result.
   */
  public void multiply()
  {
    resultDoubles = Operations.multiply(leftOperand, rightOperand);
    resultResetHelper();
  }

  /**
   * divide the operands and reset values besides result.
   */
  public void divide()
  {
    if (fractionDisplay) {
      result = Operations.fractionFormat(leftOperand, rightOperand);
      resultDoubles = Operations.divide(leftOperand, rightOperand);
      divisionFormatResult = true;
    }else {
    resultDoubles = Operations.divide(leftOperand, rightOperand);
    }
    resultResetHelper();
  }
  
  /**
   * Helper method for when operations are called.
   */
  private void resultResetHelper()
  {
    leftOperand = null;
    rightOperand = null;
    operator = null;
  }

  /**
   * calls the appropriate calculation method.
   */
  public boolean formResult()
  {
    if (operator.equals("+") && validOperands())
    {
      add();
    }
    else if (operator.equals("-") && validOperands())
    {
      subtract();
    }
    else if (operator.equals("×") && validOperands())
    {
      multiply();
    }
    else if (operator.equals("÷") && validOperands())
    {
      String[] test = rightOperand.split("\\+|-");
      
      if (test[0].equals("0")) {
        clear();
        return false;
      }
      divide();
    }
    return true;
  }

  /**
   * Checks if both operands are valid.
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
    resultDoubles = null;
  }
  
  public void setFractionDisplay(boolean incoming) {
    fractionDisplay = incoming;
  }
  
  public boolean getFractionDisplay() {
    return fractionDisplay;
  }
  
//**Methods to help increase coverage for testing**//
  public void setDivisionFormatResult(boolean incoming) {
    divisionFormatResult = incoming;
  }
  
  public String getLeftOperandString() {
    return leftOperand;
  }
  
  public String getRightOperandString() {
    return rightOperand;
  }
  
  public double[] getDoubles() {
    return resultDoubles;
  }
  
  public void setResultDoubles(double first, double second) {
    resultDoubles[0] = first;
    resultDoubles[1] = second;
  }
  

  
  
}
