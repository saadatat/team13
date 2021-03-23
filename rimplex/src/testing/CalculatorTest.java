package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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
    String validTest = "400";
    String longValidTest = "40000+30000";
    String emptyTest = "        ";
    String nullTest = null;

    assertEquals("(400)", Calculator.formatDisplayOperand(validTest));
    assertEquals("(40000+30000)", Calculator.formatDisplayOperand(longValidTest));
    assertThrows(IllegalArgumentException.class, () -> Calculator.formatDisplayOperand(emptyTest));
    assertThrows(IllegalArgumentException.class, () -> Calculator.formatDisplayOperand(nullTest));

  }

}
