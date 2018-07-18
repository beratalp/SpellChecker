import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;

public class TextFrame extends JFrame {

    JTextArea textArea = new JTextArea();
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");
    JMenu settingsMenu = new JMenu("Settings");
    JMenu helpMenu = new JMenu("Help");
    JMenu textMenu = new JMenu("Text");
    JMenu backgroundMenu = new JMenu("Background");
    JMenu backgroundColorMenu = new JMenu("Color");
    JMenu textColorMenu = new JMenu("Color");
    JMenu languageMenu = new JMenu("Language");
    JMenuItem sizeItem = new JMenuItem("Size");
    JMenuItem typefaceItem = new JMenuItem("Typeface");
    JMenuItem blackBackground = new JMenuItem("Black");
    JMenuItem redBackground = new JMenuItem("Red");
    JMenuItem greyBackground = new JMenuItem("Grey");
    JMenuItem blueBackground = new JMenuItem("Blue");
    JMenuItem darkBlueBackground = new JMenuItem("Dark Blue");
    JMenuItem whiteBackground = new JMenuItem("White");
    JMenuItem orangeBackground = new JMenuItem("Orange");
    JMenuItem greenBackground = new JMenuItem("Green");
    JMenuItem purpleBackground = new JMenuItem("Purple");
    JMenuItem pinkBackground = new JMenuItem("Pink");
    JMenuItem blackText = new JMenuItem("Black");
    JMenuItem redText = new JMenuItem("Red");
    JMenuItem greyText = new JMenuItem("Grey");
    JMenuItem blueText = new JMenuItem("Blue");
    JMenuItem darkBlueText = new JMenuItem("Dark Blue");
    JMenuItem whiteText = new JMenuItem("White");
    JMenuItem orangeText = new JMenuItem("Orange");
    JMenuItem greenText= new JMenuItem("Green");
    JMenuItem purpleText = new JMenuItem("Purple");
    JMenuItem pinkText = new JMenuItem("Pink");
    JMenuItem english = new JMenuItem("English");
    JMenuItem turkish = new JMenuItem("Turkish");
    JMenuItem newFile = new JMenuItem("New");
    JMenuItem openFile = new JMenuItem("Open");
    JMenuItem saveFile = new JMenuItem("Save");
    JMenuItem saveAsFile = new JMenuItem("Save As");
    JMenuItem quit = new JMenuItem("Quit");
    JMenuItem about = new JMenuItem( "About" );
    JMenuItem forum = new JMenuItem("Forum");
    JCheckBoxMenuItem onlineMode = new JCheckBoxMenuItem("Online Mode");
    SpellChecker spellChecker;



    JPanel panel = new JPanel();
    JPanel panelCenter = new JPanel();
    JButton buttonSave = new JButton("Save");
    JButton buttonOpenFile = new JButton("Open");
    JButton buttonNewFile = new JButton("New");
    JButton buttonSpellCheck = new JButton("SpellChecker");
    JButton buttonAutoCorrect = new JButton("AutoCorrect");
    JButton buttonIncreaseSize = new JButton("Increase Size");
    JButton buttonDecreaseSize = new JButton("Decrease Size");

    private TextFile file;


    public TextFrame(TextFile file) throws IOException{
        this.file = file;
        panelCenter.setLayout(new BorderLayout());
        panelCenter.add(textArea, BorderLayout.CENTER);
        initializeTextField(file);
        setJMenuBar(menuBar);
        addComponentsButtons();
        addComponentsMenu();
        addComponentsMenuItems();
        addAclionListeners();
        add(panel, BorderLayout.NORTH);
        add(panelCenter);
        setSize(950,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addAclionListeners(){
        blackBackground.addActionListener(new menuAction());
        redBackground.addActionListener(new menuAction());
        greyBackground.addActionListener(new menuAction());
        greenBackground.addActionListener(new menuAction());
        pinkBackground.addActionListener(new menuAction());
        purpleBackground.addActionListener(new menuAction());
        whiteBackground.addActionListener(new menuAction());
        orangeBackground.addActionListener(new menuAction());
        blueBackground.addActionListener(new menuAction());
        darkBlueBackground.addActionListener(new menuAction());
    }

    public void addComponentsButtons(){
        panel.add(buttonNewFile);
        panel.add(buttonOpenFile);
        panel.add(buttonSave);
        panel.add(buttonSpellCheck);
        buttonSpellCheck.addActionListener(new buttonAction());
        panel.add(buttonAutoCorrect);
        panel.add(buttonIncreaseSize);
        panel.add(buttonDecreaseSize);
    }

    public void addComponentsMenu(){
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);
        editMenu.add(textMenu);
        editMenu.add(backgroundMenu);
        textMenu.add(textColorMenu);
        backgroundMenu.add(backgroundColorMenu);
    }
    public void addComponentsMenuItems(){
        textMenu.add(sizeItem);
        textMenu.add(typefaceItem);
        backgroundColorMenu.add(blackBackground);
        backgroundColorMenu.add(redBackground);
        backgroundColorMenu.add(greyBackground);
        backgroundColorMenu.add(blueBackground);
        backgroundColorMenu.add(darkBlueBackground);
        backgroundColorMenu.add(whiteBackground);
        backgroundColorMenu.add(orangeBackground);
        backgroundColorMenu.add(greenBackground);
        backgroundColorMenu.add(purpleBackground);
        backgroundColorMenu.add(pinkBackground);
        textColorMenu.add(blackText);
        textColorMenu.add(redText);
        textColorMenu.add(greyText);
        textColorMenu.add(blueText);
        textColorMenu.add(darkBlueText);
        textColorMenu.add(whiteText);
        textColorMenu.add(orangeText);
        textColorMenu.add(greenText);
        textColorMenu.add(purpleText);
        textColorMenu.add(pinkText);
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(new JSeparator());
        fileMenu.add(saveFile);
        fileMenu.add(saveAsFile);
        fileMenu.add(new JSeparator());
        fileMenu.add(quit);
        settingsMenu.add(onlineMode);
        settingsMenu.add(languageMenu);
        languageMenu.add(english);
        languageMenu.add(turkish);
        helpMenu.add(forum);
        helpMenu.add(new JSeparator());
        helpMenu.add(about);
    }


    public void initializeTextField(TextFile file) throws IOException{
        textArea.setText(file.getText());
    }

    class buttonAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(buttonSpellCheck)){
                if(SpellChecker.isOnline)
                    spellChecker = new SpellCheckerOnline();
                else
                    spellChecker = new SpellCheckerOffline();
                try {
                    spellChecker.spellCheck(textArea.getText(), SpellChecker.Language.ENGLISH);
                }
                catch (Exception exception){
                    spellChecker.Error(exception);
                }
            }
        }
    }

    class menuAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == blackBackground) {
                textArea.setBackground(Color.BLACK);
            } else if (e.getSource() == redBackground) {
                textArea.setBackground(Color.red);
            } else if (e.getSource() == greyBackground) {
                textArea.setBackground(Color.darkGray);
            } else if (e.getSource() == blueBackground) {
                textArea.setBackground(Color.blue);
            } else if (e.getSource() == darkBlueBackground) {
                textArea.setBackground(Color.decode("#00008"));
            } else if (e.getSource() == whiteBackground) {
                textArea.setBackground(Color.white);
            } else if (e.getSource() == purpleBackground) {
                textArea.setBackground(Color.decode("#800080"));
            } else if (e.getSource() == pinkBackground) {
                textArea.setBackground(Color.pink);
            }else if (e.getSource() == greenBackground ){
                textArea.setBackground(Color.green);
            }else if (e.getSource() == orangeBackground ){
                textArea.setBackground(Color.orange);
            }

        }
    }

}
