import javax.swing.*;
import java.util.ArrayList;
/**
 * @author 404 Not Found
 * @verison 24.07.2018
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

    public abstract ArrayList<Word> findSynonyms(String str, Language lang) throws Exception;

    public static void Error(Exception e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, e.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public static void Error(String s){
        JOptionPane.showMessageDialog(null, s, "Error!", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    public static void Warning(Exception e, String s){
        JOptionPane.showMessageDialog(null, e.getStackTrace().toString() + "\n" + s, "Warning!", JOptionPane.INFORMATION_MESSAGE);
    }

}
