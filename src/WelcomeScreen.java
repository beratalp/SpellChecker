import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class WelcomeScreen {
    public static void main(String[] args) throws IOException, InterruptedException{
        String fileName = "recents.txt";
        File recentsFile = new File(fileName);
        ArrayList<String> recentsArray = new ArrayList<String>();
        Scanner fileScan = new Scanner(recentsFile);
        while (fileScan.hasNext()) {
            recentsArray.add(fileScan.nextLine());
        }if(recentsArray.size() == 0){
            recentsArray.add("No recent files");
        }
        JFrame frame = new JFrame();
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setTitle(SpellChecker.APP_NAME + " " + SpellChecker.VERSION);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] recents = new String[recentsArray.size()];
        for (int i = 0; i < recentsArray.size(); i++) {
            recents[i] = recentsArray.get(i);
        }
        BufferedImage logo = ImageIO.read(new File("logo.png"));
        JList<String> recentList = new JList<String>(recents);
        JPanel panel = new JPanel();
        JButton newButton = new JButton("Create a New File");
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                File emptyFile = new File("recents.txt");
                frame.setVisible(false);
                try{
                    TextFrame frame = new TextFrame(emptyFile);
                }
                catch (IOException io){
                }

            }
        });
        JButton browseButton = new JButton("Browse...");
        JFileChooser fc = new JFileChooser();
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(frame);
                File browseFile = fc.getSelectedFile();
                if(returnVal == 0){
                    frame.setVisible(false);
                }
            }
        });
        JLabel recentLabel = new JLabel("Recent Files: ",JLabel.CENTER);
        recentLabel.setForeground(Color.white);
        JLabel logoLabel = new JLabel(new ImageIcon(logo));
        JCheckBox onlineBox = new JCheckBox("Online Mode");
        if(!SpellCheckerOnline.isConnectionWorks()){
            onlineBox.setEnabled(false);
        }
        onlineBox.setBackground(Color.decode("#455A64"));
        onlineBox.setForeground(Color.white);
        JButton helpButton = new JButton("About & Help");
        panel.setBackground(Color.decode("#455A64"));
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(recentList, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(recentLabel, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(logoLabel,GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                        .addComponent(newButton, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                        .addComponent(browseButton, GroupLayout.DEFAULT_SIZE,368, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(onlineBox, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                .addComponent(helpButton, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        )
                )
        );
        layout.linkSize(SwingConstants.HORIZONTAL, newButton, browseButton);
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(recentLabel)
                .addGroup(layout.createParallelGroup()
                        .addComponent(recentList, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addComponent(newButton)
                                .addComponent(browseButton)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(helpButton)
                                        .addComponent(onlineBox)
                                )
                        )
                )
        );
        frame.add(panel);
        frame.setVisible(true);
    }
}
