import java.net.*;
import java.io.*;

public class SpellCheckerOnline extends SpellChecker {
    public static boolean isConnectionWorks() throws UnknownHostException, IOException {
        InetAddress address = InetAddress.getByName("www.google.com");
        if(address.isReachable(1)){
            return true;
        }
        else{
            return false;
        }
    }
}
