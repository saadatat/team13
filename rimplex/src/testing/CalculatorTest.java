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
    
    //Normal test
    assertEquals("22+16i", Operations.multiply(validNum1, validNum2));
    //Switch normal
    assertEquals("22+16i",Operations.multiply(validNum2, validNum1));
    //zero times valid
    assertEquals("18+3i", Operations.multiply(validNum1, zeroImaginary));
    //Check when theres a minus
    assertEquals("33-13i", Operations.multiply(validNum1, minusImag));
    assertEquals("-23+27i", Operations.multiply(minusReal, validNum1));
    
    
    
    
  

  }

}
