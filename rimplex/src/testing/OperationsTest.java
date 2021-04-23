package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.text.NumberFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import calculations.Operations;

class OperationsTest
{
  NumberFormat fmat;
  static final double PERM_STP = 0.5; // Step for the permutations of numbers.
  static final double PERM_STR = -1; // Number to start permutation.
  static final double PERM_END = 2; // Number to end permutation.

  @Test
  void testConstructor()
  {
    new Operations();
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

    assertEquals("6+8i", Operations.addition(termPositive1, termPositive2));
    assertEquals("-2+2i", Operations.addition(termNegative1, termNegative2));
    assertEquals("-8+6i", Operations.addition(termFirstNegOnly1, termFirstNegOnly2));
    assertEquals("0+0i", Operations.addition("0+0i", "0+0i"));

    for (int i = -10; i <= 10; i++)
    {
      for (int j = -50; j <= 50; j++)
      {

        String firstOp = String.format("%d+%di", i, j).replace("+-", "-");
        String secondOp = String.format("%d+%di", j, i).replace("+-", "-");
        String expected = String.format("%d+%di", i + j, i + j).replace("+-", "-");
        assertEquals(expected, Operations.addition(firstOp, secondOp));
      }
    }
  }
  
  @Test
  void subtractionTest()
  {
    String termPositive1 = "2+5i";
    String termPositive2 = "4+3i";
    assertEquals("-2+2i", Operations.subtraction(termPositive1, termPositive2));

    for (int i = -10; i <= 10; i++)
    {
      for (int j = -50; j <= 50; j++)
      {
        String firstOp = String.format("%d+%di", i, j).replace("+-", "-");
        String secondOp = String.format("%d+%di", j, i).replace("+-", "-");
        String expected = String.format("%d+%di", i - j, j - i).replace("+-", "-");
        assertEquals(expected, Operations.subtraction(firstOp, secondOp));
      }
    }
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

    assertEquals("0.92+0.56i", Operations.divide(termPositive1, termPositive2));
    assertEquals("0.92+0.56i", Operations.divide(termNegative1, termNegative2));
    assertEquals("-1+0i", Operations.divide(termFirstNegOnly1, termFirstNegOnly2));
    assertEquals("-0.92-0.56i", Operations.divide(termPositive1, termNegative2));
    assertEquals("-0.28+1.04i", Operations.divide(termPositive1, termFirstNegOnly2));
    assertEquals("-0.92-0.56i", Operations.divide(termPositive1, termNegative2));
  }
  
  /*
   * multiplyTest - test multiply method.
   */
  @Test
  void multiplicationTest(String firstOp, String secondOp)
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

  @ParameterizedTest
  @MethodSource("valueGenerator")
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
  }

  @ParameterizedTest
  @MethodSource("valueGenerator")
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
