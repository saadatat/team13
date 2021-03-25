package calculations;

public class Operations
{
  
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
}
