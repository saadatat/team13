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