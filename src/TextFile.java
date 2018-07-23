

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

    public boolean isEqual (String s) throws IOException{
        if ( getText().equals(s) ){
            return true;
        } else{
            return false;
        }
    }
    public void openFile(File file){
        this.file = file;
    }

    public String getText() throws IOException{
        fileScanner = new Scanner(file);
        String returnString = "";
        while(fileScanner.hasNext()){
            returnString += (fileScanner.nextLine() + "\n");
        }
        return returnString;
    }

    public String getPath(){
        return file.getAbsolutePath();
    }

    public String getShortPath(){
        return file.getName();
    }
}
