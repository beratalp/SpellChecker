import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public abstract class SpellChecker {
    public final static String VERSION = "0.1";
    public final static String APP_NAME = "Spell Inspector";

    public enum Language{
        TURKISH,
        ENGLISH
    }

    public abstract ArrayList<Word> spellCheck(String str, Language lang);

    public abstract ArrayList<Word> grammarCheck(String str, Language lang);

    public abstract ArrayList<Word> findSynonyms(String str, Language lang);
}
