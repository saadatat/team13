package calculations;

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
    double[][] opdoubles = parseTodouble(operandOne.replace("+-", "-"), operandTwo.replace("+-", "-"));

    double realSum = (opdoubles[0][0] + opdoubles[1][0]);
    double imagSum = (opdoubles[0][1] + opdoubles[1][1]);

    char operator = '+';

    // Handles negative imaginary
    if (imagSum < 0)
    {
      operator = '-';
      imagSum = -1 * imagSum;
    }

    return String.format("%.2f%c%.2fi", realSum, operator, imagSum);
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
    double[][] opdoubles = parseTodouble(operandOne.replace("+-", "-"), operandTwo.replace("+-", "-"));

    double realDiff = (opdoubles[0][0] - opdoubles[1][0]);
    double imagDiff = (opdoubles[0][1] - opdoubles[1][1]);

    char operator = '+';

    // Handles negative imaginary
    if (imagDiff < 0)
    {
      operator = '-';
      imagDiff = -1 * imagDiff;
    }

    return String.format("%.2f%c%.2fi", realDiff, operator, imagDiff);
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

    // Split the strings double two doubles, the one before '+' and one before 'i'
    
    double[][] opdoubles = parseTodouble(operandOne.replace("+-", "-"), operandTwo.replace("+-", "-"));

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

    return String.format("%.2f%c%.2fi", realProduct, operator, imagProduct);
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

    double quotient1;
    double quotient2;
    char operator = '+';

    // Split the strings double two doubles, the one before '+' and one before 'i'
    String op1[] = operandOne.split("\\+|i");
    String op2[] = operandTwo.split("\\+|i");

    double[][] opdoubles = parseTodouble(operandOne.replace("+-", "-"), operandTwo.replace("+-", "-"));
    
    double ac = opdoubles[0][0] * opdoubles[1][0];
    double bd = opdoubles[0][1] * opdoubles[1][1];
    double ad = opdoubles[0][0] * opdoubles[1][1];
    double bc = opdoubles[0][1] * opdoubles[1][0];

    double top1 = ac + bd;
    double top2 = bc - ad;

    double cSquare = (double) Math.pow(opdoubles[1][0], 2);
    double dSquare = (double) Math.pow(opdoubles[1][1], 2);

    double denominator = cSquare + dSquare;

    quotient1 = (top1 / denominator);
    quotient2 = (top2 / denominator);
   
    if (quotient2 < 0)
    {
      operator = '-';
      quotient2 = -1 * quotient2 ;
    }

    return String.format("%.2f%c%.2fi", quotient1, operator, quotient2);
  }

  /**
   * Private helper method for helping parse values. This method will return a 2D array with the
   * values as doubles ordered respectively.
   * 
   * @param operandOne
   *          The first complex number to parse, must not be in +- form.
   * @param operandTwo
   *          The second complex number to parse, must not be in +- form.
   * @return returns the parsed strings as indididual doubleegers in a 2D array.
   */
  private static double[][] parseTodouble(String operandOne, String operandTwo)
  {
    double negModifier1A = 1;
    double negModifier1B = 1;
    double negModifier2A = 1;
    double negModifier2B = 1;
    double[] op1double;
    double[] op2double;
    String[] op1;
    String[] op2;

    // Split the strings doubleo two doubles, the one before '+'/'-' and one before 'i'

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
    op1double = new double[op1.length];
    op1double[0] = Double.parseDouble(op1[0]) * negModifier1A;
    op1double[1] = Double.parseDouble(op1[1]) * negModifier1B;

    op2double = new double[op2.length];
    op2double[0] = Double.parseDouble(op2[0]) * negModifier2A;
    op2double[1] = Double.parseDouble(op2[1]) * negModifier2B;

    return new double[][] {op1double, op2double};
  }
}
