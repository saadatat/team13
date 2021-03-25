package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calculations.Calculator;
import calculations.Operations;

class CalculatorTest
{

  /**
   * convertToComplexTest - test convertToComplex.
   */
  @Test
  void convertToComplexTest()
  {
    String complexTest = "3+0i";
    String nonComplexTest = "5";
    String emptyTest = "   ";
    String nullTest = null;

    assertEquals("3+0i", Calculator.convertToComplex(complexTest));
    assertEquals("5+0i", Calculator.convertToComplex(nonComplexTest));
    assertThrows(IllegalArgumentException.class, () -> Calculator.convertToComplex(emptyTest));
    assertThrows(IllegalArgumentException.class, () -> Calculator.convertToComplex(nullTest));

  }

  /**
   * formatDisplayOperandTest - test formatDisplayOperand.
   */
  @Test
  void formatDisplayOperandTest()
  {
    String validTest = "400";
    String longValidTest = "40000+30000";
    String emptyTest = "        ";
    String nullTest = null;

    assertEquals("(400)", Calculator.formatDisplayOperand(validTest));
    assertEquals("(40000+30000)", Calculator.formatDisplayOperand(longValidTest));
    assertThrows(IllegalArgumentException.class, () -> Calculator.formatDisplayOperand(emptyTest));
    assertThrows(IllegalArgumentException.class, () -> Calculator.formatDisplayOperand(nullTest));

  }

  /**
   * multiplyTest - test multiply method.
   */
  @Test
  void multiplyTest()
  {

    String validNum1 = "6+1i";
    String validNum2 = "4+2i";
    String zeroImaginary = "3+0i";
    String minusImag = "5+-3i";
    String minusReal = "-3+5i";

    // Normal test
    assertEquals("22.00+16.00i", Operations.multiply(validNum1, validNum2));
    // Switch normal
    assertEquals("22.00+16.00i", Operations.multiply(validNum2, validNum1));
    // zero times valid
    assertEquals("18.00+3.00i", Operations.multiply(validNum1, zeroImaginary));
    // Check when theres a minus
    assertEquals("33.00-13.00i", Operations.multiply(validNum1, minusImag));
    assertEquals("-23.00+27.00i", Operations.multiply(minusReal, validNum1));

  }

  /**
   * divideTest - test divide method.
   */
  @Test
  void divideTest()
  {

    String termPositive1 = "2+5i";
    String termPositive2 = "4+3i";
    String termNegative1 = "-2-5i";
    String termNegative2 = "-4-3i";
    String termFirstNegOnly1 = "-4+3i";
    String termFirstNegOnly2 = "4-3i";

    assertEquals("0.92+0.56i", Operations.divide(termPositive1, termPositive2));
    assertEquals("0.92+0.56i", Operations.divide(termNegative1, termNegative2));
    assertEquals("-1.00+0.00i", Operations.divide(termFirstNegOnly1, termFirstNegOnly2));
    assertEquals("-0.92-0.56i", Operations.divide(termPositive1, termNegative2));
    assertEquals("-0.28+1.04i", Operations.divide(termPositive1, termFirstNegOnly2));

  }

  @Test
  void additionTest()
  {
    String termPositive1 = "2+5i";
    String termPositive2 = "4+3i";
    String termNegative1 = "2+5i";
    String termNegative2 = "-4-3i";
    String termFirstNegOnly1 = "-4+3i";
    String termFirstNegOnly2 = "-4+3i";

    assertEquals("6.00+8.00i", Operations.addition(termPositive1, termPositive2));
    assertEquals("-2.00+2.00i", Operations.addition(termNegative1, termNegative2));
    assertEquals("-8.00+6.00i", Operations.addition(termFirstNegOnly1, termFirstNegOnly2));
    assertEquals("0.00+0.00i", Operations.addition("0+0i", "0+0i"));

    for (double i = -10; i <= 10; i++)
    {
      for (double j = -50; j <= 50; j++)
      {
        String firstOp = String.format("%.2f+%.2fi", i, j).replace("+-", "-");
        String secondOp = String.format("%.2f+%.2fi", j, i).replace("+-", "-");
        String expected = String.format("%.2f+%.2fi", i + j, i + j).replace("+-", "-");
        assertEquals(expected, Operations.addition(firstOp, secondOp));
      }
    }
  }

  @Test
  void subtractionTest()
  {
    String termPositive1 = "2+5i";
    String termPositive2 = "4+3i";
    assertEquals("-2.00+2.00i", Operations.subtraction(termPositive1, termPositive2));

    for (double i = -10; i <= 10; i++)
    {
      for (double j = -50; j <= 50; j++)
      {
        String firstOp = String.format("%.2f+%.2fi", i, j).replace("+-", "-");
        String secondOp = String.format("%.2f+%.2fi", j, i).replace("+-", "-");
        String expected = String.format("%.2f+%.2fi", i - j, j - i).replace("+-", "-");
        assertEquals(expected, Operations.subtraction(firstOp, secondOp));
      }
    }
  }

  @Test
  void setLeftOperandTest()
  {
    Calculator calculator = new Calculator();
    assertEquals(calculator.getLeftOperand(), null);
    calculator.setLeftOperand("test");
    assertEquals(calculator.getLeftOperand(), "test");
  }

  @Test
  void setRightOperandTest()
  {
    Calculator calculator = new Calculator();
    assertEquals(calculator.getRightOperand(), null);
    calculator.setRightOperand("test");
    assertEquals(calculator.getRightOperand(), "test");
  }

  @Test
  void setResultTest()
  {
    Calculator calculator = new Calculator();
    assertEquals(calculator.getResult(), null);
    calculator.setResult("test");
    assertEquals(calculator.getResult(), "test");
  }

}
