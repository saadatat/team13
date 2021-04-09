package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import calculations.Operations;
import calculations.Calculator;


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
    Calculator calculator = new Calculator();
    String validTest = "400";
    String longValidTest = "40000+30000";
    String emptyTest = "        ";
    String nullTest = null;

    assertEquals("(400)", calculator.formatDisplayOperand(validTest));
    assertEquals("(40000+30000)", calculator.formatDisplayOperand(longValidTest));
    assertThrows(IllegalArgumentException.class, () -> calculator.formatDisplayOperand(emptyTest));
    assertThrows(IllegalArgumentException.class, () -> calculator.formatDisplayOperand(nullTest));

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
    assertEquals("22+16i", Operations.multiply(validNum1, validNum2));
    // Switch normal
    assertEquals("22+16i", Operations.multiply(validNum2, validNum1));
    // zero times valid
    assertEquals("18+3i", Operations.multiply(validNum1, zeroImaginary));
    // Check when theres a minus
    assertEquals("33-13i", Operations.multiply(validNum1, minusImag));
    assertEquals("-23+27i", Operations.multiply(minusReal, validNum1));

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

    assertEquals("0.920+0.560i", Operations.divide(termPositive1, termPositive2));
    assertEquals("0.920+0.560i", Operations.divide(termNegative1, termNegative2));
    assertEquals("-1+0i", Operations.divide(termFirstNegOnly1, termFirstNegOnly2));
    assertEquals("-0.920-0.560i", Operations.divide(termPositive1, termNegative2));
    assertEquals("-0.280+1.040i", Operations.divide(termPositive1, termFirstNegOnly2));

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