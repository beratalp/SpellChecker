import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util. List;
import static java.util.Arrays.*;

public class Grammar 
{
   //user enters a sentence 
   
   //look at the first character 
   //if the first letter is Lower Case. Make the first letter upper Case.
   //Look at the last character
   //If the last character is NOT(.?!)
   
   //Enter another sentence or press Q to quit.
   
   public static void main(String args[])
   {
      Scanner scan = new Scanner(System.in);
      String originalSentence  = "";// the original sentence
      String correctSentence = "";// the corrected sentence
      
      char temp = ' ';
      
      System.out.println("Enter a sentence");
      originalSentence = scan.nextLine();
      
      do
      {
         temp = originalSentence.charAt(0);
         
         //Make it upper case
         temp = Character.toUpperCase(temp);
         
         correctSentence  = Character.toString(temp);
         
         correctSentence = correctSentence +originalSentence.substring(1);
         
         temp = correctSentence.charAt(originalSentence.length()-1);
         
         if(temp!= '.' && temp !='?' && temp != '!')
         {
            correctSentence = correctSentence + ".";
         }
         
         
         
         System.out.println("the word count is: " + wordCount(correctSentence));
         System.out.println("Corrected Sentence: " + correctSentence);
         System.out.println("repetitions have been removed: " + repetitionRemover(correctSentence));
         
         
         
         System.out.println("Enter another sentence or press Q to quit");
         originalSentence = scan.nextLine();
         
         
         
      } while(!originalSentence.equals("Q"));
   }
   
   public static int wordCount(String arg)
   {
      int counter = 0;
      
      for(int i = 0; i < arg.length(); i++)
      {
         if (arg.charAt(i) != ' ' && arg.charAt(i) != '!' && arg.charAt(i) != '?' && arg.charAt(i) != '.' && arg.charAt(i) != '"')
         {
            counter++;
            
         }
      }
      return counter;
      
      
   }
   
   public static String repetitionRemover(String arg)
   {
      
     String[] words = arg.split("\\W+");
StringBuilder stringBuilder = new StringBuilder();
Set<String> wordsHashSet = new HashSet<>();

for (String word : words) {
    // Check for duplicates
    if (wordsHashSet.contains(word.toLowerCase())) continue;

    wordsHashSet.add(word.toLowerCase());
    stringBuilder.append(word).append(" ");
}
String nonDuplicate = stringBuilder.toString().trim();
       return nonDuplicate;
   }
  
}

