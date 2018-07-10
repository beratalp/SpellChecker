
import java.util.ArrayList;

public abstract class SpellChecker {
    public final static String VERSION = "0.1";
    public final static String APP_NAME = "Spell Inspector";

    public enum Language{
        TURKISH,
        ENGLISH
    }

    public abstract ArrayList<Word> spellCheck(String str, Language lang) throws Exception;

    public abstract ArrayList<Word> grammarCheck(String str, Language lang) throws Exception;

    public abstract ArrayList<Word> findSynonyms(String str, Language lang) throws Exception;

    public static void Error(){
        System.out.println("Error!");
        System.exit(0);
    }
}
