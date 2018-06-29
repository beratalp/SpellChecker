import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class WelcomeScreen {
    public static void main(String[] args) throws IOException {
        String fileName = "recents.txt";
        File recentsFile = new File(fileName);
        ArrayList<String> recentsArray = new ArrayList<String>();
        Scanner fileScan = new Scanner(recentsFile);
        SpellChecker spellCheck = new SpellChecker();
        while(fileScan.hasNext()){
            recentsArray.add(fileScan.nextLine());
        }
        JFrame frame = new JFrame();
        frame.setSize(400,300);
        frame.setTitle("Spell Investigator " + spellCheck.getVERSION());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] recents = new String[recentsArray.size()];
        for(int i = 0; i < recentsArray.size(); i++){
            recents[i] = recentsArray.get(i);
        }
        BufferedImage logo = ImageIO.read(new File("logo.jpeg"));
        JList<String> recentList = new JList<String>(recents);
        JPanel panel = new JPanel();
        JLabel recentLabel = new JLabel("Recent Files: ");
        JLabel logoLabel = new JLabel(new ImageIcon(logo));
        panel.setBackground(Color.decode("#455A64"));
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints recentListC = new GridBagConstraints();
        GridBagConstraints newButtonC = new GridBagConstraints();
        GridBagConstraints recentLabelC = new GridBagConstraints();
        GridBagConstraints logoC = new GridBagConstraints();
        recentListC.gridx = 0;
        recentListC.gridy = 1;
        recentListC.fill = GridBagConstraints.BOTH;
        recentLabelC.gridheight = GridBagConstraints.REMAINDER;
        recentLabelC.gridx = 0;
        recentLabelC.gridy = 0;
        logoC.gridx = 1;
        logoC.gridy = 0;
        panel.setLayout(layout);
        panel.add(recentLabel, recentLabelC);
        panel.add(recentList,recentListC);
        frame.add(panel);
        JButton newButton = new JButton("Create a new file");
        newButtonC.gridx = 1;
        newButtonC.gridy = 1;
        panel.add(newButton, newButtonC);
        panel.add(logoLabel, logoC);
        frame.setVisible(true);
    }
}
