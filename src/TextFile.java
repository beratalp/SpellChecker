import java.io.*;
import java.util.*;

/**
 * @author 404 Not Found
 * @version 24.07.2018
 */
public class TextFile {
    private File file;
    private Scanner fileScanner;
    private PrintWriter writer;

    /**
     *
     * @throws IOException
     */
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

    /**
     * This method is for saving the file
     * @param file file that user want to save
     * @param text text of the file
     * @throws IOException incase the file does not found
     */
    public void saveFile(File file, String text) throws IOException {
        writer = new PrintWriter(file);
        writer.print(text);
        writer.close();
    }

    /**
     * This method is for checking whether file is equal to another file or not
     * @param s text of the compared file
     * @return true if they are equal or false if they are different
     * @throws IOException in case there is no file
     */
    public boolean isEqual (String s) throws IOException{
        if ( getText().equals(s) ){
            return true;
        } else{
            return false;
        }
    }

    /**
     * This method is for opening the selected file
     * @param file selected file that wished to open
     */
    public void openFile(File file){
        this.file = file;
    }

    /**
     * This method is for getting the text that in the file
     * @return the text that had been in the file
     * @throws IOException in case there is no file
     */
    public String getText() throws IOException{
        fileScanner = new Scanner(file);
        String returnString = "";
        while(fileScanner.hasNext()){
            returnString += (fileScanner.nextLine() + "\n");
        }
        return returnString;
    }

    /**
     * This method is for getting the absolute path of the file
     * @return absolute path of the file
     */
    public String getPath(){
        return file.getAbsolutePath();
    }

    /**
     * This method is for getting short path of file
     * @return name of the file
     */
    public String getShortPath(){
        return file.getName();
    }
}
