package calculations;

public class Operations
{
  /**
   * Adds two complex numbers.
   * Will return in A+Bi or A-Bi form.
   * @param operandOne First complex number to add.
   * @param operandTwo Second complex number to add
   * @return Returns the answer.
   */
  public static String addition(String operandOne, String operandTwo)
  {
    int[][] opInts = parseToInt(operandOne.replace("+-", "-"), operandTwo.replace("+-", "-"));
    
    int realSum = (opInts[0][0] + opInts[1][0]);
    int imagSum = (opInts[0][1] + opInts[1][1]);

    char operator = '+';
    
    //Handles negative imaginary
    if (imagSum < 0)
    {
      operator = '-';
      imagSum = -1 * imagSum;
    }
  
    return String.format("%d%c%di", realSum, operator, imagSum);
  }
  
  /**
   * Subtracts two complex numbers.
   * Will return in A+Bi or A-Bi form.
   * @param operandOne First complex number, to subtract from.
   * @param operandTwo Second complex, the amount to subtract.
   * @return Returns the answer.
   */
  public static String subtraction(String operandOne, String operandTwo)
  {
    int[][] opInts = parseToInt(operandOne.replace("+-", "-"), operandTwo.replace("+-", "-"));
    
    int realDiff = (opInts[0][0] - opInts[1][0]);
    int imagDiff = (opInts[0][1] - opInts[1][1]);

    char operator = '+';
    
    //Handles negative imaginary
    if (imagDiff < 0)
    {
      operator = '-';
      imagDiff = -1 * imagDiff;
    }
  
    return String.format("%d%c%di", realDiff, operator, imagDiff);
  }
  
  /**
   * Accepts two Strings representing operands to multiply.
   * Variables named after  -> (a + bi) * (b + ci).
   * 
   * @param operandOne
   *          - the first operand
   * @param operandTwo
   *          - the second operand
   * @return String - the product
   */
  public static String multiply(String operandOne, String operandTwo)
  {

    //Split the strings into two ints, the one before '+' and one before 'i'
    String op1[] = operandOne.split("\\+|i");
    String op2[] = operandTwo.split("\\+|i");
    
    int ac = ( Integer.parseInt(op1[0])  * (Integer.parseInt(op2[0]) ) ) ;
    int bd = ( Integer.parseInt(op1[1])  * (Integer.parseInt(op2[1]) ) );
    int ad = ( Integer.parseInt(op1[0])  * (Integer.parseInt(op2[1]) ) );
    int bc = ( Integer.parseInt(op1[1])  * (Integer.parseInt(op2[0]) ) );
 
    int realProduct = ac - bd;
    int imagProduct = ad + bc;
    
    char operator = '+';
    
    //Handles negative imaginary
    if (imagProduct < 0)
    {
      operator = '-';
      imagProduct = -1 * imagProduct;
    }
  
    return realProduct + ""+ operator + "" + imagProduct + "i";
  }
  
  /**
   * Accepts two Strings representing operands to divide.
   * Variables named after -> ( (ac + bd) + (bc - ad)i ) / c^2 + d ^2.
   * 
   * @param operandOne
   *          - the first operand
   * @param operandTwo
   *          - the second operand
   * @return String - the quotient
   */
  public static String divide (String operandOne, String operandTwo)
  {
    
    String quotient1;
    String quotient2;   

    //Split the strings into two ints, the one before '+' and one before 'i'
    String op1[] = operandOne.split("\\+|i");
    String op2[] = operandTwo.split("\\+|i");
    
    int ac = ( Integer.parseInt(op1[0])  * (Integer.parseInt(op2[0]) ) ) ;
    int bd = ( Integer.parseInt(op1[1])  * (Integer.parseInt(op2[1]) ) );
    int ad = ( Integer.parseInt(op1[0])  * (Integer.parseInt(op2[1]) ) );
    int bc = ( Integer.parseInt(op1[1])  * (Integer.parseInt(op2[0]) ) );
 
    int top1 = ac + bd;
    int top2 = bc - ad;
    
    
    int cSquare = (int)Math.pow(Integer.parseInt(op2[0]) , 2 );
    int dSquare = (int)Math.pow(Integer.parseInt(op2[1]) , 2 );
    
    int denominator = cSquare+ dSquare; 
    
    quotient1 = "( " + top1 + " / " + denominator + " )";
    quotient2 = "( " + top2 + "i / " + denominator + " )";
    
    String operator = " + ";
    
  
    return quotient1 + operator + quotient2;
  }
  
  /**
   * Private helper method for helping parse values.
   * This method will return a 2D array with the values as ints ordered respectively.
   * @param operandOne The first complex number to parse, must not be in +- form.
   * @param operandTwo The second complex number to parse, must not be in +- form.
   * @return returns the parsed strings as indididual integers in a 2D array.
   */
  private static int[][] parseToInt(String operandOne, String operandTwo)
  {
    int negModifier1A = 1;
    int negModifier1B = 1;
    int negModifier2A = 1;
    int negModifier2B = 1;
    int[] op1Int;
    int[] op2Int;
    String[] op1;
    String[] op2;
    
    //Split the strings into two ints, the one before '+'/'-' and one before 'i'
    
    //Checks if first operand has negative values
    if (operandOne.charAt(0) == '-')
    {
      op1 = operandOne.substring(1).split("\\+|i|-");
      negModifier1A = -1;
    } else
      op1 = operandOne.split("\\+|i|-");    
    
    //Checks to see if there is a minus before second term
    if (operandOne.substring(1).contains("-"))
      negModifier1B = -1;
    
    
    //Checks if second operand has negative values
    if (operandTwo.charAt(0) == '-')
    {
      op2 = operandTwo.substring(1).split("\\+|i|-");
      negModifier2A = -1;
    } else
      op2 = operandTwo.split("\\+|i|-");

    //Checks second term for negative
    if (operandTwo.substring(1).contains("-"))
      negModifier2B = -1;
    
    //Converting parsed string to integers
    op1Int = new int[op1.length];
    op1Int[0] = Integer.parseInt(op1[0]) * negModifier1A;
    op1Int[1] = Integer.parseInt(op1[1]) * negModifier1B;
      
    op2Int = new int[op2.length];
    op2Int[0] = Integer.parseInt(op2[0]) * negModifier2A;
    op2Int[1] = Integer.parseInt(op2[1]) * negModifier2B;
    
    return new int[][]{op1Int, op2Int};
  }
}
