package calculations;

import java.text.NumberFormat;
import java.util.Arrays;

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
  public static String addition(String operandOne, String operandTwo)
  {
    double[][] opdoubles = parseTodouble(operandOne, operandTwo);

    double realSum = (opdoubles[0][0] + opdoubles[1][0]);
    double imagSum = (opdoubles[0][1] + opdoubles[1][1]);

    char operator = '+';

    // Handles negative imaginary
    if (imagSum < 0)
    {
      operator = '-';
      imagSum = -1 * imagSum;
    }

    return formatResult(realSum, operator, imagSum);
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
  public static String subtraction(String operandOne, String operandTwo)
  {
    double[][] opdoubles = parseTodouble(operandOne, operandTwo);

    double realDiff = (opdoubles[0][0] - opdoubles[1][0]);
    double imagDiff = (opdoubles[0][1] - opdoubles[1][1]);

    char operator = '+';

    // Handles negative imaginary
    if (imagDiff < 0)
    {
      operator = '-';
      imagDiff = -1 * imagDiff;
    }

    return formatResult(realDiff, operator, imagDiff);
  }

  /**
   * Accepts two Strings representing operands to multiply. Variables named after -> (a + bi) * (b +
   * ci).
   * 
   * @param operandOne
   *          - the first operand
   * @param operandTwo
   *          - the second operand
   * @return String - the product
   */
  public static String multiply(String operandOne, String operandTwo)
  {
    double[][] opdoubles = parseTodouble(operandOne, operandTwo);

    double ac = opdoubles[0][0] * opdoubles[1][0];
    double bd = opdoubles[0][1] * opdoubles[1][1];
    double ad = opdoubles[0][0] * opdoubles[1][1];
    double bc = opdoubles[0][1] * opdoubles[1][0];

    double realProduct = ac - bd;
    double imagProduct = ad + bc;

    char operator = '+';

    // Handles negative imaginary
    if (imagProduct < 0)
    {
      operator = '-';
      imagProduct = -1 * imagProduct;
    }

    return formatResult(realProduct, operator, imagProduct);
  }

  /**
   * Accepts two Strings representing operands to divide. Variables named after -> ( (ac + bd) + (bc
   * - ad)i ) / c^2 + d ^2.
   * 
   * @param operandOne
   *          - the first operand
   * @param operandTwo
   *          - the second operand
   * @return String - the quotient
   */
  public static String divide(String operandOne, String operandTwo)
  {
    double scale = Math.pow(10, 3);
    double quotient1;
    double quotient2;
    char operator = '+';

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

    if (quotient2 < 0)
    {
      
      operator = '-';
      quotient2 = -1 * quotient2;
    }

    return formatResult(quotient1, operator, quotient2);
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
  private static double[][] parseTodouble(String operandOneIn, String operandTwoIn)
  {
   
    if(operandOneIn.equals("i"))
    {
  
      operandOneIn = "1i";
    }
    
    if(operandTwoIn.equals("i"))
    {
      operandTwoIn = "1i";
    }
    
    String operandOne = operandOneIn.replace("+-", "-");
    String operandTwo = operandTwoIn.replace("+-", "-");
    boolean neg1 = false;
    boolean neg2 = false;
    double negModifier1A = 1;
    double negModifier1B = 1;
    double negModifier2A = 1;
    double negModifier2B = 1;
    double[] op1double;
    double[] op2double;
    String[] op1;
    String[] op2;
    String[] temp;
    
    if (!operandOne.contains("i"))
    {
      operandOne += "+0i";
    }
    if (!operandTwo.contains("i"))
    {
      operandTwo += "+0i";
    }
    
    
    
    
    // Split the strings double two doubles, the one before '+'/'-' and one before 'i'

    // Checks if first operand has negative values
    if (operandOne.charAt(0) == '-')
    {
      op1 = operandOne.substring(1).split("\\+|i|-");
      negModifier1A = -1;
      neg1 = true;
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
      neg2 = true;
    }
    else
      op2 = operandTwo.split("\\+|i|-");

    // Checks second term for negative
    if (operandTwo.substring(1).contains("-"))
      negModifier2B = -1;

  
    if(op1.length == 1)
    {
      op1 = Arrays.copyOf(op1, 2);
      if (!neg1) 
      {
        op1[1] = op1[0];
      } else {
        op1[1] = "-" + op1[0];
        System.out.println(op1[1]);
      }
      
      op1[0] = "0";
    }
    if(op2.length == 1)
    {
      op2 = Arrays.copyOf(op2, 2);
      if (!neg2) 
      {
        op2[1] = op2[0];
      } else {
        op2[1] = "-" + op2[0];
      }
      op2[0] = "0";
    }
    neg1 = false;
    neg2 = false;
    
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
   * Formats the result. Removes unnecessary 0's.
   * 
   * @param real
   *          - the real number.
   * @param operator
   *          - the operator.
   * @param imaginary
   *          - the imaginary.
   * @return String - the formatted result.
   */
  public static String formatResult(double real, char operator, double imaginary)
  {
    NumberFormat fmat = NumberFormat.getInstance();
    fmat.setMinimumFractionDigits(0);
    fmat.setMaximumFractionDigits(3);
    String returnString = String.format("%s%c%si", fmat.format(real), operator, fmat.format(imaginary));
    returnString = returnString.replace(",", "");
    return returnString;
  }
  
  public static String formatFractionDisplay(String incoming) {
    
   String splits[] = incoming.split("\\+|-|i");
    String splits2[];
    String splits3[];
    int digits1;
    int digits2;
    int denominator1;
    int denominator2;
    int numerator1;
    int numerator2;
    int gcd1;
    int gcd2;
    double result1;
    double result2;
    String returnString = "";
    String operator = "";
    
    if (incoming.contains("+")) {
      operator = "+";
    }else {
      operator = "-";
    }
    
    if (splits[0].contains(".")) {
      splits2 = splits[0].split("\\.");
      digits1 = splits2[1].length();
      denominator1 = (int) Math.pow(10, digits1);
      result1 = Double.parseDouble(splits[0]);
      numerator1 = (int)(result1*denominator1);
      gcd1 = getGCD(numerator1, denominator1);
      returnString = numerator1 / gcd1 + "/" + denominator1 / gcd1;
    }else {
      returnString += splits[0];
    }
    if (splits[1].contains(".")) {
      splits3 = splits[1].split("\\.");
      digits2 = splits3[1].length();
      denominator2 = (int) Math.pow(10, digits2);
      result2 = Double.parseDouble(splits[0]);
      numerator2 = (int)(result2*denominator2);
      gcd2 = getGCD(numerator2, denominator2);
      returnString += operator + numerator2 / gcd2 + "/" + denominator2 / gcd2;
    }else {
      returnString += operator + splits[1]; 
    }
     
   return returnString + "i";
    
  }
  
  public static int getGCD(int number1, int number2) {
    if (number2 == 0) {
      return number1;
    }
    return getGCD(number2, number1 % number2);
  }
  


  public static String test(String operandOne, String operandTwo) {
    
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

  double cSquare = (double) Math.pow(opdoubles[1][0], 2);
  double dSquare = (double) Math.pow(opdoubles[1][1], 2);

  double denominator = cSquare + dSquare;
  
  if (top1 == 0) {
    quotient1 = "0";
  }else {
  int gcd1 = getGCD((int)top1, (int)denominator);
  int returnTop = (int)top1/gcd1;
  int returnDen = (int)denominator/gcd1;
  quotient1 = returnTop + "/" + returnDen;
  }
  if(top2 == 0) {
    quotient2 = "0";
  }else {
    int gcd1 = getGCD((int)top1, (int)denominator);
    int returnTop = (int)top2/gcd1;
    int returnDen = (int)denominator/gcd1;
    quotient2 = returnTop + "/" + returnDen;
  }


  return quotient1 + operator + quotient2;
  }
  
  
}
