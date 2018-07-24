import java.util.*;

/**
 * @author 404 Not Found
 * @version 24.07.2018
 */

public class SpellCheckerOffline extends SpellChecker {
    @Override
    public ArrayList<Word> grammarCheck(String str, Language lang) {
        return null;
    }

    @Override
    public ArrayList<Word> spellCheck(String str, Language lang) {
        return null;
    }

    @Override
    public ArrayList<Word> findSynonyms(String str, Language lang) {
        return null;
    }

    private Dictionary dictionary;
    final static String filePath = "E:/Project/dict.txt";
    final static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    SpellCheckerOffline()
    {
        dictionary = new Dictionary();
        dictionary.createDictionary(filePath);
    }


    public void run()
    {
        Scanner scan = new Scanner(System.in);
        String input;

        while (true)
        {
            System.out.print("\nEnter a word: ");
            input = scan.nextLine();
            if (input.equals(""))
            {
                break;
            }
            if (dictionary.contains(input))
            {
                System.out.println("\n" + input + "Word is spelled correctly!");
            }
            else
            {
                System.out.print("Word is not spelled correctly! ");
                System.out.println(printAlternatives(input));
            }
        }
    }

    public String printAlternatives(String input)
    {
        StringBuilder x = new StringBuilder();
        ArrayList<String> alternatives = findAlternatives(input);
        if (alternatives.size() == 0)
        {
            return "Cannot find any alternative words!\n";
        }
        x.append("Alternative Words:\n");
        for (String s : alternatives)
        {
            x.append("\n" + s);
        }
        return x.toString();
    }

    public ArrayList<String> findAlternatives(String input)
    {
        ArrayList<String> altList = new ArrayList<String>();
        altList.addAll(endCharMissing(input));
        altList.addAll(extraCharAdded(input));
        altList.addAll(charsSwapped(input));
        return altList;
    }

    public ArrayList<String> endCharMissing(String input)
    {
        ArrayList<String> altList = new ArrayList<String>();
        for (char c : alphabet)
        {
            String atFront = c + input;
            String atBack = input + c;
            if (dictionary.contains(atFront))
            {
                altList.add(atFront);
            }
            if (dictionary.contains(atBack))
            {
                altList.add(atBack);
            }
        }
        return altList;
    }

    public ArrayList<String> extraCharAdded(String input)
    {
        ArrayList<String> altList = new ArrayList<String>();

        int len = input.length() - 1;
        //removing character from the front
        if (dictionary.contains(input.substring(1)))
        {
            altList.add(input.substring(1));
        }

        for (int i = 1; i < len; i++)
        {
            //removing each character between the first and last characters
            String subWord = input.substring(0, i);
            subWord = subWord + (input.substring((i + 1), input.length()));
            if (dictionary.contains(subWord))
            {
                altList.add(subWord);
            }
        }

        //removing character from the end
        if (dictionary.contains(input.substring(0, len)))
        {
            altList.add(input.substring(0, len));
        }
        return altList;
    }


    public ArrayList<String> permutate(String input)
    {
        ArrayList<String> result = new ArrayList<String>();

        if(input.length() == 0)
        {
            result.add(input);
            return result;
        }
        else
        {
            for ( int i = 0; i < input.length(); i++ )
            {
                String shorter = input.substring(0, i) + input.substring(i + 1);

                ArrayList<String> shorterPermutations = permutate(shorter);

                for ( String s : shorterPermutations )
                {
                    String x = input.charAt(i) + s;
                    result.add(x);
                }
            }
            return result;
        }
    }

    public ArrayList<String> charsSwapped(String word)
    {
        ArrayList<String> permutations = permutate(word);
        ArrayList<String> resultList = new ArrayList<String>();

        for (int i = 0; i < permutations.size(); i++ )
        {
            String s = permutations.get(i);
            if ( dictionary.contains(s) )
            {
                resultList.add(s);
            }
        }
        return resultList;
    }

}

