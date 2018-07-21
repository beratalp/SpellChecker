import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class WelcomeScreen {

    public WelcomeScreen() throws Exception{
        main(null);
    }
    private static JCheckBox onlineBox;
    private static TextFile file;
    public static void main(String[] args) throws IOException, InterruptedException{
        String fileName = "recents.txt";
        File recentsFile = new File(fileName);
        ArrayList<String> recentsArray = new ArrayList<String>();
        Scanner fileScan = new Scanner(recentsFile);
        while (fileScan.hasNext()) {
            recentsArray.add(fileScan.next());
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
        recentList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    String selectedFile = (String) recentList.getSelectedValue();
                    System.out.println(selectedFile);
                    try{
                        frame.setVisible(false);
                        TextFrame textFrame = new TextFrame(new TextFile(selectedFile));
                    }
                    catch (Exception ex){
                        frame.setVisible(true);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JPanel panel = new JPanel();
        JButton newButton = new JButton("Create a New File");
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    file = new TextFile();
                }
                catch (Exception ex){
                    SpellChecker.Error(ex);
                }
                frame.setVisible(false);
                if(onlineBox.isSelected()){
                    SpellChecker.isOnline = true;
                }
                else{
                    SpellChecker.isOnline = false;
                }
                try{
                    TextFrame frame = new TextFrame(file);
                }
                catch (Exception ex){
                    SpellChecker.Error(ex);
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
                    if(onlineBox.isSelected()){
                        SpellChecker.isOnline = true;
                    }
                    else{
                        SpellChecker.isOnline = false;
                    }
                    frame.setVisible(false);
                    try{
                        TextFile file = new TextFile(browseFile.getPath());
                        TextFrame frame = new TextFrame(file);
                    }
                    catch(Exception ex){
                        SpellChecker.Error(ex);
                    }
                }
            }
        });
        JLabel recentLabel = new JLabel("Recent Files: ",JLabel.CENTER);
        recentLabel.setForeground(Color.white);
        JLabel logoLabel = new JLabel(new ImageIcon(logo));
        onlineBox = new JCheckBox("Online Mode");
        if(!SpellCheckerOnline.isConnectionWorks()){
            SpellChecker.isOnline = false;
            onlineBox.setEnabled(false);
        }
        else{
            SpellChecker.isOnline = true;
            onlineBox.setSelected(true);
        }
        onlineBox.setBackground(Color.decode("#455A64"));
        onlineBox.setForeground(Color.white);
        JButton helpButton = new JButton("About & Help");
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new About();
            }
        });
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
