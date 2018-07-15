import java.io.*;
import java.util.*;

public class Key {
    private File keyFile;
    private String keyString;
    private Scanner keyScanner;

    public Key(File keyFile){
        this.keyFile = keyFile;
    }

    public void setKeyFile(File file){
        this.keyFile = file;
    }

    public String getKeyString() throws IOException{
        keyScanner = new Scanner(keyFile);
        keyString = keyScanner.next();
        return keyString;
    }
}
