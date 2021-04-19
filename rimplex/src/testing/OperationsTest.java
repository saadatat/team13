package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.text.NumberFormat;
import org.junit.jupiter.api.Test;
import calculations.Operations;

class OperationsTest 
{
  NumberFormat fmat;
  
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
  void additionTestComprehensive()
  {
    for (double i = -2; i <= 2; i += 0.25)
    {
      for (double j = -2; j <= 2; j += 0.25)
      {
        for (double k = -2; k <= 2; k += 0.25)
        {
          for (double l = -2; l <= 2; l += 0.25)
          {
            // Instantiate NumberFormat, MUST BE IN FOR LOOP FOR THREADING!
            fmat = NumberFormat.getInstance();
            fmat.setMinimumFractionDigits(0);
            fmat.setMaximumFractionDigits(3);

            String firstOp = String.format("%.2f+%.2fi", i, j).replace("+-", "-");
            String secondOp = String.format("%.2f+%.2fi", k, l).replace("+-", "-");
            String one = fmat.format(i + k);
            String two = fmat.format(j + l);
            String expected = String.format("%s+%si", one, two).replace("+-", "-");
            assertEquals(expected, Operations.addition(firstOp, secondOp));
          }
        }
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

  @Test
  void subtractionTestComprehensive()
  {
    for (double i = -2; i <= 2; i += 0.25)
    {
      for (double j = -2; j <= 2; j += 0.25)
      {
        for (double k = -2; k <= 2; k += 0.25)
        {
          for (double l = -2; l <= 2; l += 0.25)
          {
            // Instantiate NumberFormat, MUST BE IN FOR LOOP FOR THREADING!
            fmat = NumberFormat.getInstance();
            fmat.setMinimumFractionDigits(0);
            fmat.setMaximumFractionDigits(3);

            String firstOp = String.format("%.2f+%.2fi", i, j).replace("+-", "-");
            String secondOp = String.format("%.2f+%.2fi", k, l).replace("+-", "-");
            String one = fmat.format(i - k);
            String two = fmat.format(j - l);
            String expected = String.format("%s+%si", one, two).replace("+-", "-");
            assertEquals(expected, Operations.subtraction(firstOp, secondOp));
          }
        }
      }
    }
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
    assertEquals("-1+0i", Operations.divide(termFirstNegOnly1, termFirstNegOnly2));
    assertEquals("-0.92-0.56i", Operations.divide(termPositive1, termNegative2));
    assertEquals("-0.28+1.04i", Operations.divide(termPositive1, termFirstNegOnly2));
    assertEquals("-0.92-0.56i", Operations.divide(termPositive1, termNegative2));
  }
  
  @Test
  void divisionTestComprehensive()
  {
    for (double i = -2; i <= 2; i += 0.25)
    {
      for (double j = -2; j <= 2; j += 0.25)
      {
        for (double k = -2; k <= 2; k += 0.25)
        {
          for (double l = -2; l <= 2; l += 0.25)
          {
            // Instantiate NumberFormat, MUST BE IN FOR LOOP FOR THREADING!
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
            quotient1 = Math.round((top1 / denominator) * scale ) / scale;
            quotient2 = Math.round((top2 / denominator) * scale ) / scale;
            
            String firstOp = String.format("%.2f+%.2fi", i, j).replace("+-", "-");
            String secondOp = String.format("%.2f+%.2fi", k, l).replace("+-", "-");
            String one = fmat.format(quotient1);
            String two = fmat.format(quotient2);
            String expected = String.format("%s+%si", one, two).replace("+-", "-");
            assertEquals(expected, Operations.divide(firstOp, secondOp));
          }
        }
      }
    }
  }

  /*
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
  
  /*
   * 
   */

}
