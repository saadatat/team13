package calculations;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles math operations and parsing.
 * @author Max Berger, Arman Saadat, Kory Erdman
 *
 */
public class Operations
{
  /**
   * Adds two complex numbers. Will return in A+Bi or A-Bi form.
   * 
   * @param operandOne
   *          First complex number to add.
   * @param operandTwo
   *          Second complex number to add
   * @return Returns the answer.
   */
  public static double[] addition(String operandOne, String operandTwo)
  {
    double[][] opdoubles = parseTodouble(operandOne, operandTwo);
    double[] returnDoubles;

    double realSum = (opdoubles[0][0] + opdoubles[1][0]);
    double imagSum = (opdoubles[0][1] + opdoubles[1][1]);

    returnDoubles = new double[2];
    returnDoubles[0] = realSum;
    returnDoubles[1] = imagSum;  
    return returnDoubles;
  }

  /**
   * Subtracts two complex numbers. Will return in A+Bi or A-Bi form.
   * 
   * @param operandOne
   *          First complex number, to subtract from.
   * @param operandTwo
   *          Second complex, the amount to subtract.
   * @return Returns the answer.
   */
  public static double[] subtraction(String operandOne, String operandTwo)
  {
    double[][] opdoubles = parseTodouble(operandOne, operandTwo);

    double realDiff = (opdoubles[0][0] - opdoubles[1][0]);
    double imagDiff = (opdoubles[0][1] - opdoubles[1][1]);

    double[] returnDoubles = new double[2];
    returnDoubles[0] = realDiff;
    returnDoubles[1] = imagDiff;  
    return returnDoubles;
  }

  /**
   * Accepts two Strings representing operands to multiply. Variables named after: (a + bi) * 
   * (b + ci).
   * 
   * @param operandOne
   *          - the first operand
   * @param operandTwo
   *          - the second operand
   * @return String - the product
   */
  public static double[] multiply(String operandOne, String operandTwo)
  {
    double[][] opdoubles = parseTodouble(operandOne, operandTwo);

    double ac = opdoubles[0][0] * opdoubles[1][0];
    double bd = opdoubles[0][1] * opdoubles[1][1];
    double ad = opdoubles[0][0] * opdoubles[1][1];
    double bc = opdoubles[0][1] * opdoubles[1][0];

    double realProduct = ac - bd;
    double imagProduct = ad + bc;

    double[] returnDoubles = new double[2];
    returnDoubles[0] = realProduct;
    returnDoubles[1] = imagProduct;  
    return returnDoubles;
  }

  /**
   * Accepts two Strings representing operands to divide. Variables named after:( (ac + bd) + (bc
   * - ad)i ) / c^2 + d ^2.
   * 
   * @param operandOne
   *          - the first operand
   * @param operandTwo
   *          - the second operand
   * @return String - the quotient
   */
  public static double[] divide(String operandOne, String operandTwo)
  {
    double scale = Math.pow(10, 3);
    double quotient1;
    double quotient2;

    double[][] opdoubles = parseTodouble(operandOne, operandTwo);

    double ac = opdoubles[0][0] * opdoubles[1][0];
    double bd = opdoubles[0][1] * opdoubles[1][1];
    double ad = opdoubles[0][0] * opdoubles[1][1];
    double bc = opdoubles[0][1] * opdoubles[1][0];

    double top1 = ac + bd;
    double top2 = bc - ad;

    double cSquare = (double) Math.pow(opdoubles[1][0], 2);
    double dSquare = (double) Math.pow(opdoubles[1][1], 2);

    double denominator = cSquare + dSquare;

    quotient1 = Math.round((top1 / denominator) *scale ) / scale;
    quotient2 = Math.round((top2 / denominator) *scale ) / scale;

    double[] returnDoubles = new double[2];
    returnDoubles[0] = quotient1;
    returnDoubles[1] = quotient2;  
    return returnDoubles;
  }

  /**
   * Private helper method for helping parse values. This method will return a 2D array with the
   * values as doubles ordered respectively.
   * 
   * @param operandOne
   *          The first complex number to parse, must not be in +- form.
   * @param operandTwo
   *          The second complex number to parse, must not be in +- form.
   * @return returns the parsed strings as individual doubleegers in a 2D array.
   */
  private static double[][] parseTodouble(final String operandOneIn, final String operandTwoIn)
  { 
    String operandOne = operandOneIn;
    String operandTwo = operandTwoIn;
    double negModifier1A = 1;
    double negModifier1B = 1;
    double negModifier2A = 1;
    double negModifier2B = 1;
    double[] op1double;
    double[] op2double;
    String[] op1;
    String[] op2;
    
    // Pattern for matching an operand that is a single imaginary.
    Pattern oneImagPattern = Pattern.compile("^[-]*[0-9]*[.]*[0-9]+[i]$");
    Matcher m;
    
    // If this method is passed a single real number without an imaginary
    // counterpart then convert it to standard form.
    if (!operandOne.contains("i"))
    {
      operandOne += "+0i";
    }
    if (!operandTwo.contains("i"))
    {
      operandTwo += "+0i";
    }
    

    //Singular negative i fix op1
    if(operandOne.equals("-i"))
    {
      operandOne = "-1i";
     
    }
    
    //Singular i fix op1
    if(operandOne.equals("i"))
    {
     
     operandOne = "1i";
    }
    
    
    //Singular i fix op2
    if(operandTwo.equals("i"))
    {
      operandTwo = "1i";
     
    }
    
    //Singular negative i fix op2
    if(operandTwo.equals("-i"))
    {
      operandTwo = "-1i";
     
    }
    // Convert to standard form if there is a single imaginary number.
    m = oneImagPattern.matcher(operandOne);
    if (m.matches()) {
      operandOne = "0+" + operandOne;
    }
    m = oneImagPattern.matcher(operandTwo);
    if (m.matches()) {
      operandTwo = "0+" + operandTwo;
    }
    
    // Formatting for adjacent symbols.
    operandOne = operandOne.replace("+-", "-");
    operandTwo = operandTwo.replace("+-", "-");
    
    // Replace any lone imaginary symbols with 1 so they work with code below.
    operandOne = operandOne.replace("+i", "+1i");
    operandTwo = operandTwo.replace("+i", "+1i");
    operandOne = operandOne.replace("-i", "-1i");
    operandTwo = operandTwo.replace("-i", "-1i");
    
    
    // Split the strings double two doubles, the one before '+'/'-' and one before 'i'
    
    // Checks if first operand has negative values
    if (operandOne.charAt(0) == '-')
    {
      op1 = operandOne.substring(1).split("\\+|i|-");
      negModifier1A = -1;
    }
    else
      op1 = operandOne.split("\\+|i|-");

    // Checks to see if there is a minus before second term
    if (operandOne.substring(1).contains("-"))
      negModifier1B = -1;

    // Checks if second operand has negative values
    if (operandTwo.charAt(0) == '-')
    {
      op2 = operandTwo.substring(1).split("\\+|i|-");
      negModifier2A = -1;
    }
    else
      op2 = operandTwo.split("\\+|i|-");

    // Checks second term for negative
    if (operandTwo.substring(1).contains("-"))
      negModifier2B = -1;
    
    // Converting parsed string to doubleegers
    op1double = new double[2]; // Always make an array the size of two.

    op1double[0] = Double.parseDouble(op1[0]) * negModifier1A;
    op1double[1] = Double.parseDouble(op1[1]) * negModifier1B;
    

    op2double = new double[2];

    op2double[0] = Double.parseDouble(op2[0]) * negModifier2A;
    op2double[1] = Double.parseDouble(op2[1]) * negModifier2B;

    return new double[][] {op1double, op2double};
  }
  
  /**
   * Overloaded formatResult function for when given resultDoubles
   * @param resultDoubles - the two doubles
   * @param asFraction - boolean whether to turn into fraction 
   * @return formatResult(resultDoubles[0], resultDoubles[1], asFraction)
   *         the formatted result
   */
  public static String formatResult(double[] resultDoubles, boolean asFraction)
  {
    return formatResult(resultDoubles[0], resultDoubles[1], asFraction);
  }

  /**
   * Formats the result. Removes unnecessary 0's.
   * 
   * @param real
   *          - the real number.
   * @param asFraction
   *          - whether or not to turn into a fraction.
   * @param imaginary
   *          - the imaginary.
   * @return String - the formatted result.
   */
  public static String formatResult(double real, double imaginary, boolean asFraction)
  {
    NumberFormat fmat = NumberFormat.getInstance();
    String returnString = "";
    String realString;
    String imagString;
    fmat.setMinimumFractionDigits(0);
    fmat.setMaximumFractionDigits(3);
    
    if (asFraction)
    {
      realString = toFraction(real);
      imagString = toFraction(imaginary);
    } else {
      realString = fmat.format(real);
      imagString = fmat.format(imaginary);
    }
    
    // All results must be a complex number. Refer to the "FormOfResults" story. 
    returnString = realString + "+" + imagString + "i";
    
    returnString = returnString.replace(",", "");
    returnString = returnString.replace("+-", "-");
    returnString = returnString.replace("+1i", "+i");
    returnString = returnString.replace("-1i", "-i");
    
    return returnString;
  }
  
  /**
   * Takes in a double decimal and converts it to a mixed fraction as a string.
   * Returns 0 if it evaluates to 0.
   * @param numberToConvert
   * @return Returns the formatted mixed number fraction. "1 1/2"
   */
  private static String toFraction (double numberToConvert)
  {
    // Rounds whole number, extracts decimal, and then multiplies decimal based on number of
    // decimal places there are. Then it can be converted into a fraction.
    double onlyDecimal = numberToConvert;
    long numerator = (long) (onlyDecimal * 10000);
    long denominator = 10000;
    
    // Simplify the fraction part
    long gcd = Operations.getGCD(numerator, denominator);
    numerator /= gcd;
    denominator /= gcd; 
    
    // Code to append string, only appends if evaluated to other than zero.
    String rval = "";
    
    // If numerator isn't zero add it to the string.
    if (numerator == 0)
    {
      return "0";
    } else if (denominator == 1)
    {
      return String.format("%d", numerator);
    } else
    {
      rval += String.format("%d/%d", numerator, denominator);
    }
    
    // Return trimmed value just in case.
    return rval.trim();
  }
  

  /**
   * Allows the division result to be properly simplified for inputs such as "6/9". Do not delete if you don't have another solution yet... 
   * @param operandOne the first part of the expression
   * @param operandTwo the second part of the expression
   * @return the formatted fraction
   */
  public static String fractionFormat(String operandOne, String operandTwo) {

    String quotient1;
    String quotient2;
    char operator = '+';
    double[][] opdoubles = parseTodouble(operandOne, operandTwo);

    double ac = opdoubles[0][0] * opdoubles[1][0];
    double bd = opdoubles[0][1] * opdoubles[1][1];
    double ad = opdoubles[0][0] * opdoubles[1][1];
    double bc = opdoubles[0][1] * opdoubles[1][0];

    double top1 = ac + bd;
    double top2 = bc - ad;

    float cSquare = (float) Math.pow(opdoubles[1][0], 2);
    float dSquare = (float) Math.pow(opdoubles[1][1], 2);

    double denominator = cSquare + dSquare;

    if (top1 == 0) {
      quotient1 = "0";
    }else {
      long gcd1 = getGCD((int)top1, (int)denominator);
      long returnTop = (long)top1/gcd1;
      long returnDen = (long)denominator/gcd1;
      if (returnDen == 1) {
        quotient1 = returnTop + "";
      }else {
        quotient1 = returnTop + "/" + returnDen;
      }
    }

    if(top2 == 0) {
      quotient2 = "0";
    }else {
      long gcd1 = getGCD((int)top2, (int)denominator);
      long returnTop = (long)top2/gcd1;
      long returnDen = (long)denominator/gcd1;
      if (returnDen == 1) {
        quotient2 = "" + returnTop;
      }else {
        quotient2 = returnTop + "/" + returnDen;
      }
    }


  return quotient1 + operator + quotient2 + "i";
  }
  
  /**
   * Helper method to get the GCD.
   * @param number1 First number
   * @param number2 Second number
   * @return The GCD.
   */
  private static long getGCD(long number1, long number2) {
    if (number2 == 0) {
      return number1;
    }
    return getGCD(number2, number1 % number2);
  }
}
