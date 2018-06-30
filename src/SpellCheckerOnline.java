import java.net.*;
import java.io.*;

public class SpellCheckerOnline extends SpellChecker {
    public static boolean isConnectionWorks() throws InterruptedException, IOException {
        String command;
        if (System.getProperty("os.name").startsWith("Windows")){
            command = "ping -n 1 www.google.com";
        }
        else{
            command = "ping -c 1 www.google.com";
        }
        Process p1 = Runtime.getRuntime().exec(command);
        int returnVal = p1.waitFor();
        boolean reachable = (returnVal==0);
        return reachable;
    }
}
