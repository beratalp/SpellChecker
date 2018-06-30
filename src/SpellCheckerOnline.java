import java.net.*;
import java.io.*;

public class SpellCheckerOnline extends SpellChecker {
    public static boolean isConnectionWorks() throws InterruptedException, IOException {
        Process p1 = Runtime.getRuntime().exec("ping -c 1 www.google.com");
        int returnVal = p1.waitFor();
        boolean reachable = (returnVal==0);
        return reachable;
    }
}
