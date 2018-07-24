import java.io.*;
/**
 * This class is used for handling the Recents file, which is used in WelcomeScreen's JList, this class extends TextFile class
 * @author 404 Not Found
 * @version 0.2
 */
public class RecentList extends TextFile{
    /**
     * Creates a new RecentList object, initializes recents.txt
     */
    public RecentList(){
        super("recents.txt");
    }

    /**
     * Returns recent file names as an Array
     * @return recent file names
     * @throws IOException in case where the recents.txt is inaccessible
     */
    public String[] getRecents() throws IOException{
        return super.getText().split("\n");
    }

    /**
     * Adds a recent file name to the file
     * @param fileString file name
     * @throws IOException in case where the recents.txt is inaccessible
     */
    public void addRecent(String fileString) throws IOException{
        String[] recentArray = super.getText().split("\n");
        for(String s: recentArray){
            if(s.equals(fileString)){
                return;
            }
        }
        if(recentArray.length == 10){
            recentArray[10] = null;
        }
        for(int i = 0; i < recentArray.length - 1; i++){
            recentArray[i + 1] = recentArray[i];
        }
        recentArray[0] = fileString;
        String writeString = "";
        for(String s: getRecents()) {
            writeString += s + "\n";
        }
        for(String s: recentArray){
            writeString += s + "\n";
        }
        File file = new File("recents.txt");
        super.saveFile(file, writeString);
    }

}
