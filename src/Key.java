import java.io.*;
import java.util.*;

/**
 * This class is used for handling Bing Spell Checking Key
 * @author 404 Not Found
 * @version 0.2
 */
public class Key {
    private File keyFile;
    private String keyString;
    private Scanner keyScanner;

    /**
     * Constructs a Key object with a given keyFile
     * @param keyFile keyFile to open
     */
    public Key(File keyFile){
        this.keyFile = keyFile;
    }

    /**
     * Sets the keyFile
     * @param file keyFile to set
     */
    public void setKeyFile(File file){
        this.keyFile = file;
    }

    /**
     * Returns the key String
     * @return the key String
     * @throws IOException in case the keyFile is not accessible
     */
    public String getKeyString() throws IOException{
        keyScanner = new Scanner(keyFile);
        while(keyScanner.hasNext()){
            keyString = keyScanner.next();
        }
        return keyString;
    }
}
