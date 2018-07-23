import java.io.*;

public class RecentList extends TextFile{
    public RecentList(){
        super("recents.txt");
    }

    public String[] getRecents() throws IOException{
        return super.getText().split("\n");
    }

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
        for(String s: recentArray){
            writeString += s;
        }
        File file = new File("recents.txt");
        super.saveFile(file, writeString);
    }

}
