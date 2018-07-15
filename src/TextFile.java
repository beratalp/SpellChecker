import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.*;

public class TextFile {
    private File file;
    private Scanner fileScanner;
    private PrintWriter writer;

    public TextFile() throws IOException{
        this.file = new File("untitled.txt");
        try{
            file.createNewFile();
        }
        catch (IOException e){
            file.delete();
            file.createNewFile();
        }
    }

    public TextFile(String path){
        this.file = new File(path);
    }


    public void saveFile(File file, String text) throws IOException {
        writer = new PrintWriter(file);
        writer.print(text);
        writer.close();
    }

    public void openFile(File file){
        this.file = file;
    }

    public String getText() throws IOException{
        fileScanner = new Scanner(file);
        String returnString = "";
        while(fileScanner.hasNext()){
            returnString += fileScanner.nextLine();
        }
        return returnString;
    }

    public String getPath(){
        return file.getAbsolutePath();
    }
}
