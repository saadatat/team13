package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import calculations.Operations;
import calculations.Calculator;


class CalculatorTest
{
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
  void getLeftOperandTest()
  {
    Calculator calculator = new Calculator();
    assertEquals(calculator.getLeftOperand(), null);
    calculator.setLeftOperand("test");
    assertEquals(calculator.getLeftOperand(), "test");
    calculator.setLeftOperand("2+1i");
    assertEquals(calculator.getLeftOperand(), "2+i");
    calculator.setLeftOperand("2-1i");
    assertEquals(calculator.getLeftOperand(), "2-i");
    
  }

  @Test
  void getRightOperandTest()
  {
    Calculator calculator = new Calculator();
    assertEquals(calculator.getRightOperand(), null);
    calculator.setRightOperand("test");
    assertEquals(calculator.getRightOperand(), "test");
    calculator.setRightOperand("2+1i");
    assertEquals(calculator.getRightOperand(), "2+i");
    calculator.setRightOperand("2-1i");
    assertEquals(calculator.getRightOperand(), "2-i");
  }

  @Test
  void getResultTest()
  {
    Calculator calculator = new Calculator();
    calculator.setResult(null);
    assertEquals(null, calculator.getResult());
    calculator.setResult("test");
    assertEquals( "0+0i", calculator.getResult());
    
    calculator.setFractionDisplay(true);
    calculator.setResult("2.2");
    assertEquals( "0+0i", calculator.getResult());
    
    calculator.setDivisionFormatResult(true);
    calculator.setResult("2.2");
    assertEquals( "2.2", calculator.getResult());
  }
  
  /**
   * formatItalicTest - test formatItalic.
   */
  @Test
  void formatItalicTest()
  {
    String stringToFormatNULL = null;
    String stringToFormatEmpty = "";
    assertThrows(IllegalArgumentException.class, () -> Calculator.formatItalic(stringToFormatNULL));
    assertThrows(IllegalArgumentException.class, () -> Calculator.formatItalic(stringToFormatEmpty));

  }
  
  @Test
  void setOperatorTest()
  {
    Calculator calculator = new Calculator();
    
    calculator.setOperator("+");
    assertEquals("+", calculator.getOperator());
    
    calculator.setOperator("-");
    assertEquals("-", calculator.getOperator());
    
    calculator.setOperator("*");
    assertEquals("*", calculator.getOperator());
    
    calculator.setOperator("/");
    assertEquals("/", calculator.getOperator());
  }
  
  @Test
  void setLeftOperandTest()
  {
    Calculator calculator = new Calculator();
    
    calculator.setLeftOperand("2+i");
    assertEquals("2+1i", calculator.setLeftOperandString());
    
    calculator.setLeftOperand("2-i");
    assertEquals("2-1i", calculator.setLeftOperandString());
   
  }
  
  @Test
  void setRightOperandTest()
  {
    Calculator calculator = new Calculator();
    
    calculator.setRightOperand("2+i");
    assertEquals("2+1i", calculator.setRightOperandString());
    
    calculator.setRightOperand("2-i");
    assertEquals("2-1i", calculator.setRightOperandString());
   
  }
  
  

}