package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.text.NumberFormat;
import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import calculations.Operations;

class OperationsTest
{
  NumberFormat fmat;
  
  @BeforeClass
  void setup()
  {

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
    for (double i = -10; i <= 10; i += 0.25)
    {
      for (double j = -10; j <= 10; j += 0.25)
      {
        for (double k = -10; i <= 10; i += 0.25)
        {
          for (double l = -10; j <= 10; j += 0.25)
          {
            fmat = NumberFormat.getInstance();
            fmat.setMinimumFractionDigits(0);
            fmat.setMaximumFractionDigits(2);
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
}
