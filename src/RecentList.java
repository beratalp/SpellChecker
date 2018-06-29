import javax.swing.*;
import java.io.*;
import java.util.*;

public class RecentList{
    public RecentList(String fileName) throws IOException{
        File recentsFile = new File(fileName);
        ArrayList<String> recentsArray = new ArrayList<String>();
        Scanner fileScan = new Scanner(recentsFile);
        while(fileScan.hasNext()){
            recentsArray.add(fileScan.nextLine());
        }
        JList<String> recentList = new JList<String>((String[])recentsArray.toArray());

    }
    public void recentWrite(File file) throws IOException{

    }
}
