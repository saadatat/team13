package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.text.NumberFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import calculations.Operations;

/**
 * Tests the operations.java class
 * @author Arman Saadat and Max Berger
 */
class OperationsTest
{
  NumberFormat fmat;
  static final double PERM_STP = 0.5; // Step for the permutations of numbers.
  static final double PERM_STR = -1; // Number to start permutation.
  static final double PERM_END = 2; // Number to end permutation.

  /**
   * Constructor coverage
   */
  @Test
  void testConstructor()
  {
    new Operations();
  }

  /**
   * Tests additions
   */
  @Test
  void additionTest()
  {
    String termPositive1 = "2+5i";
    String termPositive2 = "4+3i";
    String termNegative1 = "2+5i";
    String termNegative2 = "-4-3i";
    String termFirstNegOnly1 = "-4+3i";
    String termFirstNegOnly2 = "-4+3i";

    assertEquals("6+8i", Operations.formatResult(Operations.addition(termPositive1, termPositive2), false));
    assertEquals("-2+2i", Operations.formatResult(Operations.addition(termNegative1, termNegative2), false));
    assertEquals("-8+6i", Operations.formatResult(Operations.addition(termFirstNegOnly1, termFirstNegOnly2), false));
    assertEquals("0+0i", Operations.formatResult(Operations.addition("0+0i", "0+0i"), false));
  }
  
  /**
   * Tests subtraction
   */
  @Test
  void subtractionTest()
  {
    String termPositive1 = "2+5i";
    String termPositive2 = "4+3i";
    assertEquals("-2+2i", Operations.formatResult(Operations.subtraction(termPositive1, termPositive2), false));
  }
  
  /**
   * divideTest - test divide method.
   */
  @Test
  void divisionTest()
  {
    String termPositive1 = "2+5i";
    String termPositive2 = "4+3i";
    String termNegative1 = "-2-5i";
    String termNegative2 = "-4-3i";
    String termFirstNegOnly1 = "-4+3i";
    String termFirstNegOnly2 = "4-3i";

    assertEquals("0.92+0.56i", Operations.formatResult(Operations.divide(termPositive1, termPositive2), false));
    assertEquals("0.92+0.56i", Operations.formatResult(Operations.divide(termNegative1, termNegative2), false));
    assertEquals("-1+0i", Operations.formatResult(Operations.divide(termFirstNegOnly1, termFirstNegOnly2), false));
    assertEquals("-0.92-0.56i", Operations.formatResult(Operations.divide(termPositive1, termNegative2), false));
    assertEquals("-0.28+1.04i", Operations.formatResult(Operations.divide(termPositive1, termFirstNegOnly2), false));
    assertEquals("-0.92-0.56i", Operations.formatResult(Operations.divide(termPositive1, termNegative2), false));
  }
  
  /**
   * multiplyTest - test multiply method.
   */
  @Test
  void multiplicationTest()
  {
    String validNum1 = "6+1i";
    String validNum2 = "4+2i";
    String zeroImaginary = "3+0i";
    String minusImag = "5+-3i";
    String minusReal = "-3+5i";

    // Normal test
    assertEquals("22+16i", Operations.formatResult(Operations.multiply(validNum1, validNum2), false));
    // Switch normal
    assertEquals("22+16i", Operations.formatResult(Operations.multiply(validNum2, validNum1), false));
    // zero times valid
    assertEquals("18+3i", Operations.formatResult(Operations.multiply(validNum1, zeroImaginary), false));
    // Check when theres a minus
    assertEquals("33-13i", Operations.formatResult(Operations.multiply(validNum1, minusImag), false));
    assertEquals("-23+27i", Operations.formatResult(Operations.multiply(minusReal, validNum1), false));
  }
  
  /**
   * Mainly used to test fraction display, as decimal display
   * is already tested in the comprehensive methods.
   */
  @Test
  void formatResultTest()
  {
    String actual;
    String expected;
    
    actual = Operations.formatResult(0, 0, true);
    expected = "0+0i";
    assertEquals(expected, actual);
    
    actual = Operations.formatResult(0, 0.5, true);
    expected = "0+1/2i";
    assertEquals(expected, actual);
    
    actual = Operations.formatResult(0, 1, true);
    expected = "0+i";
    assertEquals(expected, actual);
  }
  
  /**
   * Tests parsingSingleNumber method.
   */
  @Test
  void parsingSingleNumberTests()
  {
    String actual;
    String expected;
    
    actual = Operations.formatResult(Operations.addition("1", "1"), false);
    expected = "2+0i";
    assertEquals(expected, actual);
    
    actual = Operations.formatResult(Operations.addition("i", "i"), false);
    expected = "0+2i";
    assertEquals(expected, actual);
    
    actual = Operations.formatResult(Operations.addition("30", "i"), false);
    expected = "30+i";
    assertEquals(expected, actual);
    
    actual = Operations.formatResult(Operations.subtraction("-30", "-i"), false);
    expected = "-30+i";
    assertEquals(expected, actual);
    
    actual = Operations.formatResult(Operations.subtraction("-i", "30"), false);
    expected = "-30-i";
    assertEquals(expected, actual);
    
  }
  
  /**
   * Tests fractionFormat method.
   */
  @Test
  void testFractionFormat()
  {
    String actual;
    String expected;
    
    actual = Operations.fractionFormat("10", "24");
    expected = "5/12+0i";
    assertEquals(expected, actual);
    
    actual = Operations.fractionFormat("6", "9");
    expected = "2/3+0i";
    assertEquals(expected, actual);
    
    actual = Operations.fractionFormat("0", "0");
    expected = "0+0i";
    assertEquals(expected, actual);
    
    actual = Operations.fractionFormat("0", "1i");
    expected = "0+0i";
    assertEquals(expected, actual);
    
    actual = Operations.fractionFormat("5", "1");
    expected = "5+0i";
    assertEquals(expected, actual);
    
    actual = Operations.fractionFormat("5+i", "2-2i");
    expected = "1+3/2i";
    assertEquals(expected, actual);
    
    actual = Operations.fractionFormat("1i", "1");
    expected = "0+1i";
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("valueGenerator")
  /**
   * Assists additionTest
   * @param permutation the permutation
   */
  void additionTestComprehensive(double[] permutation)
  {
    double i = permutation[0];
    double j = permutation[1];
    double k = permutation[2];
    double l = permutation[3];

    fmat = NumberFormat.getInstance();
    fmat.setMinimumFractionDigits(0);
    fmat.setMaximumFractionDigits(3);
    
    // Calculates formatted string for double
    String one = fmat.format(i + k);
    String two = fmat.format(j + l);
    
    // For inputs generated in standard form. (0+1i)
    String firstOp = String.format("%.2f+%.2fi", i, j).replace("+-", "-");
    String secondOp = String.format("%.2f+%.2fi", k, l).replace("+-", "-");   
    String expected = String.format("%s+%si", one, two).replace("+-", "-");
    expected = expected.replace("-1i", "-i");
    expected = expected.replace("+1i", "+i");
    assertEquals(expected, Operations.formatResult(Operations.addition(firstOp, secondOp), false));
    
    // For inputs with single nums that have single numbers and don't include zeros
    firstOp = String.format("%.2f+%.2fi", i, j).replace("+-", "-");
    secondOp = String.format("%.2f+%.2fi", k, l).replace("+-", "-"); 
  }

  @ParameterizedTest
  @MethodSource("valueGenerator")
  /**
   * Assists subtractionTest
   * @param permutation the permutation
   */
  void subtractionTestComprehensive(double[] permutation)
  {
    double i = permutation[0];
    double j = permutation[1];
    double k = permutation[2];
    double l = permutation[3];

    fmat = NumberFormat.getInstance();
    fmat.setMinimumFractionDigits(0);
    fmat.setMaximumFractionDigits(3);

    String firstOp = String.format("%.2f+%.2fi", i, j).replace("+-", "-");
    String secondOp = String.format("%.2f+%.2fi", k, l).replace("+-", "-");
    String one = fmat.format(i - k);
    String two = fmat.format(j - l);
    String expected = String.format("%s+%si", one, two).replace("+-", "-");
    expected = expected.replace("-1i", "-i");
    expected = expected.replace("+1i", "+i");
    assertEquals(expected, Operations.formatResult(Operations.subtraction(firstOp, secondOp), false));

  }

  @ParameterizedTest
  @MethodSource("valueGenerator")
  /**
   * Assists divisionTest
   * @param permutation the permutation
   */
  void divisionTestComprehensive(double[] permutation)
  {
    double i = permutation[0];
    double j = permutation[1];
    double k = permutation[2];
    double l = permutation[3];

    fmat = NumberFormat.getInstance();
    fmat.setMinimumFractionDigits(0);
    fmat.setMaximumFractionDigits(3);

    double scale = Math.pow(10, 3);
    double quotient1;
    double quotient2;
    double ac = i * k;
    double bd = j * l;
    double ad = i * l;
    double bc = j * k;
    double top1 = ac + bd;
    double top2 = bc - ad;
    double cSquare = (double) Math.pow(k, 2);
    double dSquare = (double) Math.pow(l, 2);
    double denominator = cSquare + dSquare;
    quotient1 = Math.round((top1 / denominator) * scale) / scale;
    quotient2 = Math.round((top2 / denominator) * scale) / scale;

    String firstOp = String.format("%.2f+%.2fi", i, j).replace("+-", "-");
    String secondOp = String.format("%.2f+%.2fi", k, l).replace("+-", "-");
    String one = fmat.format(quotient1);
    String two = fmat.format(quotient2);
    String expected = String.format("%s+%si", one, two).replace("+-", "-");
    expected = expected.replace("-1i", "-i");
    expected = expected.replace("+1i", "+i");
    assertEquals(expected, Operations.formatResult(Operations.divide(firstOp, secondOp), false));
  }
  
  @ParameterizedTest
  @MethodSource("valueGenerator")
  /**
   * Assists multiplicationTest
   * @param permutation the permutation
   */
  void multiplicationTestComprehensive(double[] permutation)
  {
    double i = permutation[0];
    double j = permutation[1];
    double k = permutation[2];
    double l = permutation[3];

    fmat = NumberFormat.getInstance();
    fmat.setMinimumFractionDigits(0);
    fmat.setMaximumFractionDigits(3);
    
    double ac = i * k;
    double bd = j * l;
    double ad = i * l;
    double bc = j * k;

    double realProduct = ac - bd;
    double imagProduct = ad + bc;
    
    String firstOp = String.format("%.2f+%.2fi", i, j).replace("+-", "-");
    String secondOp = String.format("%.2f+%.2fi", k, l).replace("+-", "-");
    String one = fmat.format(realProduct);
    String two = fmat.format(imagProduct);
    String expected = String.format("%s+%si", one, two).replace("+-", "-");
    expected = expected.replace("-1i", "-i");
    expected = expected.replace("+1i", "+i");
    assertEquals(expected, Operations.formatResult(Operations.multiply(firstOp, secondOp), false));
  }
  
  /**
   * Generates a permutation of 4 numbers.
   * @return A double array.
   */
  private static ArrayList<double[]> valueGenerator()
  {
    ArrayList<double[]> values = new ArrayList<double[]>();
    for (double i = PERM_STR; i <= PERM_END; i += PERM_STP)
    {
      for (double j = PERM_STR; j <= PERM_END; j += PERM_STP)
      {
        for (double k = PERM_STR; k <= PERM_END; k += PERM_STP)
        {
          for (double l = PERM_STR; l <= PERM_END; l += PERM_STP)
          {
            double[] gen = {i, j, k, l};
            values.add(gen);
          }
        }
      }
    }
    return values;
  }
}
