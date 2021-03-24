package calculations;

import java.util.List;

public class Calculator
{

  /**
   * Parses args for 2 complex numbers
   * 
   * @param stringToCovert - parameter to get convert
   * @return xxxxxx - the list of Strings complex nums
   */
  public static List<String> parse(String stringToConvert)
  {
    
      //**NEGATIVE IMAGINARY NUMBERS SHOULD BE IN FORM: a+-bi
    
    return null;

  }
  
  
  /**
   * Accepts an operand string and returns the complex version.
   * 
   * @param stringToCovert
   *          - the incoming operand string that needs to be converted to complex.
   * @return String - the incoming string converted to complex.
   */
  public static String convertToComplex(String stringToConvert)
  {
    String complexConversion = "";

    if (stringToConvert == null || stringToConvert.trim().equals(""))
    {
      throw new IllegalArgumentException();
    }

    complexConversion = stringToConvert;

    if (complexConversion.charAt(complexConversion.length() - 1) != 'i')
    {
      complexConversion += "+0i";
    }

    return complexConversion;
  }

  /**
   * Accepts an operand string and returns the formatted result to be used for the display.
   * 
   * @param stringToFormat
   *          - the incoming operand string to format.
   * @return String - the formatted operand string.
   */
  public static String formatDisplayOperand(String stringToFormat)
  {
    String formattedString = "";

    if (stringToFormat == null || stringToFormat.trim().equals(""))
    {
      throw new IllegalArgumentException();
    }

    formattedString = "(" + stringToFormat + ")";

    return formattedString;
  }



}
