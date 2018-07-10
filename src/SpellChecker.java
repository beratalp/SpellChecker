import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class SpellChecker {
    public final static String VERSION = "0.1";
    public final static String APPNAME = "Spell Inspector";

    public enum Language{
        TURKISH,
        ENGLISH
    }

    public abstract int[] spellCheck(String str, Language lang);

    public abstract int[] grammarCheck(String str, Language lang);
}
