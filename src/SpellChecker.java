
import javax.swing.*;
import java.util.ArrayList;

public abstract class SpellChecker {
    public final static String VERSION = "0.2";
    public final static String APP_NAME = "Spell Inspector";
    public static boolean isOnline = true;

    public enum Language{
        TURKISH,
        ENGLISH
    }

    public abstract ArrayList<Word> spellCheck(String str, Language lang) throws Exception;

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
