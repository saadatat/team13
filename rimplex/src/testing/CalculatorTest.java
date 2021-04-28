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
    assertEquals("0+0i", calculator.getResult());
    calculator.setResult("test");
    assertEquals( "0+0i", calculator.getResult());
    
    calculator.setFractionDisplay(true);
    calculator.setResult("2.2");
    assertEquals( "0+0i", calculator.getResult());
    
    calculator.setDivisionFormatResult(true);
    calculator.setResult("2.2");
    assertEquals( "2.2", calculator.getResult());
    
    calculator.setDivisionFormatResult(false);
    calculator.setFractionDisplay(true);
    calculator.setResultDoubles(2.2, 0);
    calculator.setResult("2.2+0i");
    assertEquals( "11/5+0i", calculator.getResult());
    
    
    calculator.clear();
    
    calculator.setDivisionFormatResult(false);
    calculator.setResult("");
    assertEquals( "0+0i", calculator.getResult());
      
    
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
    assertEquals("2+1i", calculator.getLeftOperandString());
    
    calculator.setLeftOperand("2-i");
    assertEquals("2-1i", calculator.getLeftOperandString());
   
  }
  
  @Test
  void setRightOperandTest()
  {
    Calculator calculator = new Calculator();
    
    calculator.setRightOperand("2+i");
    assertEquals("2+1i", calculator.getRightOperandString());
    
    calculator.setRightOperand("2-i");
    assertEquals("2-1i", calculator.getRightOperandString());
   
  }
  
  @Test
  void opsTest()
  {
    Calculator calculator = new Calculator();
    
    double[] sum = {3.0,0};
    double[] quo = {0.5,0};
    double[] dif = {-1.0,0};
    double[] pro = {2.0,0};
   
    for(int i = 0; i < 1; i++)
    {
      calculator.setRightOperand("2");
      calculator.setLeftOperand("1");
      calculator.add();
      assertTrue(sum[i] == calculator.getDoubles()[i]);
      
      calculator.setRightOperand("2");
      calculator.setLeftOperand("1");
      calculator.subtract();
      assertTrue(dif[i] == calculator.getDoubles()[i]);
      
      calculator.setRightOperand("2");
      calculator.setLeftOperand("1");
      calculator.multiply();
      assertTrue(pro[i] == calculator.getDoubles()[i]);
      
      calculator.setRightOperand("2");
      calculator.setLeftOperand("1");
      calculator.divide();
      assertTrue(quo[i] == calculator.getDoubles()[i]);
      
      calculator.setFractionDisplay(true);
      calculator.setRightOperand("2");
      calculator.setLeftOperand("1");
      calculator.divide();
      assertTrue(quo[i] == calculator.getDoubles()[i]);
     
    }
   
  }
  
  @Test
  void formResultTest()
  {
    Calculator calculator = new Calculator();
    
    calculator.setOperator("+");
    assertTrue(calculator.formResult());
    calculator.setOperator("-");
    assertTrue(calculator.formResult());
    calculator.setOperator("×");
    assertTrue(calculator.formResult());
    calculator.setOperator("÷");
    assertTrue(calculator.formResult());
    
    calculator.setRightOperand("2+i");    
    calculator.setLeftOperand("2-i");
    calculator.setOperator("+");
    assertTrue(calculator.formResult());
    
    calculator.setRightOperand("2+i");    
    calculator.setLeftOperand("2-i");
    calculator.setOperator("-");
    assertTrue(calculator.formResult());
    
    calculator.setRightOperand("2+i");    
    calculator.setLeftOperand("2-i");
    calculator.setOperator("×");
    assertTrue(calculator.formResult());
    
    calculator.setRightOperand("2+i");    
    calculator.setLeftOperand("2-i");
    calculator.setOperator("÷");
    assertTrue(calculator.formResult());
    
    calculator.setRightOperand("0");    
    calculator.setLeftOperand("2-i");
    calculator.setOperator("÷");
    assertFalse(calculator.formResult());
   
  }
  
  @Test
  void validOperandsTest()
  {
    Calculator calculator = new Calculator();
    
    assertFalse(calculator.validOperands());
    
    calculator.setLeftOperand(" ");
    assertFalse(calculator.validOperands());
    
    calculator.setLeftOperand("2+1i");
    assertFalse(calculator.validOperands());
    
    calculator.setLeftOperand("2+1i");
    calculator.setRightOperand(" ");
    assertFalse(calculator.validOperands());
   
  }
  
  @Test
  void getFractionDisplayTest()
  {
    Calculator calculator = new Calculator();
    
    calculator.setFractionDisplay(false);
    assertFalse(calculator.getFractionDisplay());
    
    calculator.setFractionDisplay(true);
    assertTrue(calculator.getFractionDisplay());
   
  }
  

  
  

}