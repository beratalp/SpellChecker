import javax.swing.*;
import java.util.ArrayList;
/**
 * This is an abstract class defines of how a SpellChecker would work, and also includes helper classes to handle Exceptipons
 * @author 404 Not Found
 * @verison 0.2
 */
public abstract class SpellChecker {
    public final static String VERSION = "0.2";
    public final static String APP_NAME = "Spell Inspector";
    public static boolean isOnline = true;

    public enum Language{
        TURKISH,
        ENGLISH
    }

    /**
     * Run SpellChecker on the given String and with the given Language
     * @param str String to run on
     * @param lang Language to operate
     * @return ArrayList of Word Objects
     * @throws Exception in case there is something wrong
     */
    public abstract ArrayList<Word> spellCheck(String str, Language lang) throws Exception;

    /**
     * Run GrammarChecker on the given String and with the given Language
     * @param str String to run on
     * @param lang Language to operate
     * @return ArrayList of Word Objects
     * @throws Exception in case there is something wrong
     */
    public abstract ArrayList<Word> grammarCheck(String str, Language lang) throws Exception;

    /**
     * Run SynonymFinder on the given String and with the given Language
     * @param str String to run on
     * @param lang Language to operate
     * @return ArrayList of Word Objects
     * @throws Exception in case there is something wrong
     */
    public abstract ArrayList<Word> findSynonyms(String str, Language lang) throws Exception;

    /**
     * Pop-ups an Error window with the given Exception
     * @param e Exception to display
     */
    public static void Error(Exception e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, e.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    /**
     * Pop-ups an Error window with the given String
     * @param s String to display
     */
    public static void Error(String s){
        JOptionPane.showMessageDialog(null, s, "Error!", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    /**
     * Pop-ups a Warning window with given Exception and String
     * @param e Exception to display
     * @param s String to display
     */
    public static void Warning(Exception e, String s){
        JOptionPane.showMessageDialog(null, e.getStackTrace().toString() + "\n" + s, "Warning!", JOptionPane.INFORMATION_MESSAGE);
    }

}
