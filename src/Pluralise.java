/**
 * This class has methods for pluralizing singular items
 * @author 404 Not Found
 * @version 0.2
 */
public class Pluralise
{
  
  /**
   * checks for words followed by an integer and pluralizes them
   * @param input - the string
   * @return original string with nouns pluralised where necessary
   */
  public static String pluralizeAll(String input)
  {
    String[] wordList = input.split(" ", -2); 
    String out = "";
    
    for (int i = 0; i < wordList.length; i++)
    {
      if ( isNumeric(wordList[i]) && wordList[i] != "1" && i != wordList.length - 1 )
      {
        wordList[i + 1] = pluralize(wordList[i + 1]);
      }
    }
    
    for( int i = 0; i < wordList.length; i++ )
    {
      out = out + " " + wordList[i];
    }
    return out; 
  }
  
  /**
   * checks if the string is a number
   * @param str - the string
   * @return whether the string is a num
   */ 
  public static boolean isNumeric(String str)
  {
    for (char c : str.toCharArray())
    {
      if (!Character.isDigit(c)) return false;
    }
    return true;
  }
  
  /**
   * conerts a noun into a plural
   * @param word 
   * @return the converted string
   */ 
  public static String pluralize(String word)
  {
    int lastPos;
    
    if (word.charAt(word.length() - 1) == '.')
    {
      lastPos = word.length() - 2;
    }
    else
    {
      lastPos = word.length() - 1;
    }
    
    char last = word.charAt(lastPos);
    char before = word.charAt(lastPos - 1);
    String plural;
    if (word.substring(0, lastPos + 1).endsWith("ch") || word.substring(0, lastPos + 1).endsWith("s")
          || word.substring(0, lastPos + 1).endsWith("sh") || word.substring(0, lastPos + 1).endsWith("x") 
          || word.substring(0, lastPos + 1).endsWith("z"))
    {
      if (word.charAt(word.length() - 1) == '.')
      {
        plural = word.substring(0, lastPos + 1) +"es.";
      }
      else
      {
        plural = word + "es";
      }
      
    }
    else if (!(before == 'a' || before == 'e' || before == 'i'
                 || before == 'o' || before == 'u') && last == 'y')
    {
      if (word.charAt(word.length() - 1) == '.')
      {
        plural = word.substring(0, lastPos) +"ies.";
      }
      else
      {
        plural = word.substring(0, lastPos) + "ies";
      }
    }
    else
    {
      if (word.charAt(word.length() - 1) == '.')
      {
        plural = word.substring(0, lastPos + 1) + "s.";
      }
      else
      {
        plural = word + "s";
      }
    }
    return plural;
  }
  
  
}

