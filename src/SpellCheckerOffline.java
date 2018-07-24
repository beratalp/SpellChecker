import java.util.*;

/**
 * This class extends SpellChecker class and includes methods for offline spell and grammar checking
 * @author 404 Not Found
 * @version 0.2
 */

public class SpellCheckerOffline extends SpellChecker {

    private Dictionary dictionary;
    final static String filePath = "dict.txt";
    final static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    SpellCheckerOffline()
    {
        super();
        dictionary = new Dictionary();
        dictionary.createDictionary(filePath);
    }

    /**
     * This method runs grammar checking methods on the given String
     * @param str String to run on
     * @param lang Language to operate (Only in English for now)
     * @return ArrayList of words
     */
    @Override
    public ArrayList<Word> grammarCheck(String str, Language lang) {
        ArrayList<Word> words = new ArrayList<>();
        TextFrame.textArea.setText(repetitionRemover(TextFrame.textArea.getText()));
        TextFrame.textArea.setText(Pluralise.pluralizeAll(TextFrame.textArea.getText()));
        TextFrame.textArea.setText(capitalStop(TextFrame.textArea.getText()));
        return words;
    }

    /**
     * This method runs spell checking methods on the given String
     * @param str String to run on
     * @param lang Language to operate (Only in English for now)
     * @return ArrayList of words
     */
    @Override
    public ArrayList<Word> spellCheck(String str, Language lang) {
        ArrayList<Word> words = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(str.split(" ")));
        for(String s: strings){
            Word word = new Word();
            ArrayList<String> suggestions = findAlternatives(s);
            word.setOrig(s);
            word.setIndex(str.indexOf(s));
            if(!dictionary.contains(s)){
                System.out.println(s);
                word.setWrong(true);
            }
            for(String sugg: suggestions){
                word.addSuggestion(sugg);
            }
            words.add(word);
        }
        return words;
    }

    /**
     * Not implemented yet
     * @param str String to run on
     * @param lang Language to operate
     * @return null
     */
    @Override
    public ArrayList<Word> findSynonyms(String str, Language lang) {
        return null;
    }


    /**
     * Finds alternative words to a given String
     * @param input the word
     * @return ArrayList of alternative Strings
     */
    public ArrayList<String> findAlternatives(String input)
    {
        ArrayList<String> altList = new ArrayList<String>();
        altList.addAll(endCharMissing(input));
        altList.addAll(extraCharAdded(input));
        altList.addAll(charsSwapped(input));
        return altList;
    }

    /**
     * inserts every letter at the start or at the end to find the possible correct word
     * @param input, the word to manipulte
     * @return the alternate word if it is present in the dictionary
     */
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
    /**
     * removes a character in the word one by one to find a match
     * @param input, the word to be manipulated
     * @return the alternate word if it is present in the dictionary
     */
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

    /**
     * outputs the permutations of the given word
     * @param input, the word to be permutated
     * @return the list containing all the possible permutations
     */
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
    /**
     * swaps every character turn by turn to find a match
     * @param word, the word to manipulte
     * @return the list of wors which are present in the dictionary
     */
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
    /**
     * this method removes consecutive repetition in a String
     * @param arg, the string to be corrected
     * @return String nonDuplicate, the corrected sentence
     */
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
    /**
     * this method makes the first word start with a capital Char and a full-stop at the end
     * @param arg, the string to be corrected
     * @return String correctSentence, the corrected sentence
     */
    public static String capitalStop (String arg)
    {
        char temp = arg.charAt(0);
        String correctSentence = "";
        //Make it upper case
        temp = Character.toUpperCase(temp);

        correctSentence  = Character.toString(temp);

        correctSentence = correctSentence + arg.substring(1);

        temp = correctSentence.charAt(arg.length()-1);

        if(temp!= '.' && temp !='?' && temp != '!')
        {
            correctSentence = correctSentence + ".";
        }
        return correctSentence;



    }
}

