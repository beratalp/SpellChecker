import java.net.*;
import java.io.*;

public class SpellCheckerOnline extends SpellChecker {
    public static boolean isConnectionWorks() throws UnknownHostException, IOException {
        InetAddress address = InetAddress.getByName("78.167.34.5");
        if(address.isReachable(1)){
            return true;
        }
        else{
            return false;
        }
    }
}
