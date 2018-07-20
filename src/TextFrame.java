import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;
import javax.imageio.ImageIO;

public class TextFrame extends JFrame {

    JTextPane textArea = new JTextPane();
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
    JMenuItem greenText = new JMenuItem("Green");
    JMenuItem purpleText = new JMenuItem("Purple");
    JMenuItem pinkText = new JMenuItem("Pink");
    JMenuItem english = new JMenuItem("English");
    JMenuItem turkish = new JMenuItem("Turkish");
    JMenuItem newFile = new JMenuItem("New");
    JMenuItem openFile = new JMenuItem("Open");
    JMenuItem saveFile = new JMenuItem("Save");
    JMenuItem saveAsFile = new JMenuItem("Save As");
    JMenuItem quit = new JMenuItem("Quit");
    JMenuItem about = new JMenuItem("About");
    JMenuItem forum = new JMenuItem("Forum");
    JCheckBoxMenuItem onlineMode = new JCheckBoxMenuItem("Online Mode");
    SpellChecker spellChecker;


    JPanel panel = new JPanel();
    JPanel panelCenter = new JPanel();
    JButton buttonSave = new JButton();
    JButton buttonOpenFile = new JButton();
    JButton buttonNewFile = new JButton("New");
    JButton buttonSpellCheck = new JButton("SpellChecker");
    JButton buttonAutoCorrect = new JButton("AutoCorrect");
    JButton buttonIncreaseSize = new JButton("Increase Size");
    JButton buttonDecreaseSize = new JButton("Decrease Size");

    private TextFile file;
    private ArrayList<Word> words;


    public TextFrame(TextFile file) throws Exception {
        this.file = file;
        panelCenter.add(textArea);
        initializeTextField(file);
        setJMenuBar(menuBar);
        if(SpellChecker.isOnline){
            onlineMode.setState(true);
        }
        if(!SpellCheckerOnline.isConnectionWorks()){
            onlineMode.setEnabled(false);
        }
        addComponentsButtons();
        addComponentsMenu();
        addComponentsMenuItems();
        addActionListeners();
        add(panel, BorderLayout.NORTH);
        add(panelCenter,BorderLayout.CENTER);
        panel.setBackground(Color.decode("#455A64"));
        panelCenter.setBackground(Color.decode("#455A64"));
        setSize(950, 800);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void addActionListeners() {
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
        redText.addActionListener(new menuAction());
        blueText.addActionListener(new menuAction());
        blackText.addActionListener(new menuAction());
        greyText.addActionListener(new menuAction());
        whiteText.addActionListener(new menuAction());
        orangeText.addActionListener(new menuAction());
        greenText.addActionListener(new menuAction());
        pinkText.addActionListener(new menuAction());
        darkBlueText.addActionListener(new menuAction());
        blueText.addActionListener(new menuAction());
        purpleText.addActionListener(new menuAction());
    }

    public void addComponentsButtons() {
        panel.add(buttonNewFile);
        try{
            Image img = ImageIO.read(getClass().getResource("open.png"));
            buttonOpenFile.setIcon(new ImageIcon(img));
        }
        catch (Exception ex){
            SpellChecker.Error(ex);
        }
        panel.add(buttonOpenFile);
        try{
            Image img = ImageIO.read(getClass().getResource("save.png"));
            buttonSave.setIcon(new ImageIcon(img));
        }
        catch (Exception ex){
            SpellChecker.Error(ex);
        }
        panel.add(buttonSave);
        panel.add(buttonSpellCheck);
        buttonSpellCheck.addActionListener(new buttonAction());
        panel.add(buttonAutoCorrect);
        panel.add(buttonIncreaseSize);
        panel.add(buttonDecreaseSize);
    }

    public void addComponentsMenu() {
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);
        editMenu.add(textMenu);
        editMenu.add(backgroundMenu);
        textMenu.add(textColorMenu);
        backgroundMenu.add(backgroundColorMenu);
    }

    public void addComponentsMenuItems() {
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


    public void initializeTextField(TextFile file) throws IOException {
        textArea.setText(file.getText());
    }

    class buttonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(buttonSpellCheck)) {
                if (SpellChecker.isOnline)
                    spellChecker = new SpellCheckerOnline();
                else
                    spellChecker = new SpellCheckerOffline();
                try {
                    words = spellChecker.spellCheck(textArea.getText(), SpellChecker.Language.ENGLISH);
                    char[] textChars = textArea.getText().toCharArray();
                    int wordIndex = 0;
                    for(Word word: words){
                        for(int i = word.getIndex(); i < word.getSuggestions().get(0).length(); i ++){
                            textChars[i] = word.getSuggestions().get(0).charAt(wordIndex);
                            wordIndex ++;
                        }
                    }
                    textArea.setText(String.valueOf(textChars));
                } catch (Exception exception) {
                    spellChecker.Error(exception);
                }
            }
        }
    }

    class menuAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == blackBackground) {
                textArea.setBackground(Color.decode("#424242"));
            } else if (e.getSource() == redBackground) {
                textArea.setBackground(Color.decode("#E53935"));
            } else if (e.getSource() == greyBackground) {
                textArea.setBackground(Color.decode("#9E9E9E"));
            } else if (e.getSource() == blueBackground) {
                textArea.setBackground(Color.decode("#42A5F5"));
            } else if (e.getSource() == darkBlueBackground) {
                textArea.setBackground(Color.decode("#283593"));
            } else if (e.getSource() == whiteBackground) {
                textArea.setBackground(Color.white);
            } else if (e.getSource() == purpleBackground) {
                textArea.setBackground(Color.decode("#8E24AA"));
            } else if (e.getSource() == pinkBackground) {
                textArea.setBackground(Color.decode("#F06292"));
            } else if (e.getSource() == greenBackground) {
                textArea.setBackground(Color.decode("#1B5E20"));
            } else if (e.getSource() == orangeBackground) {
                textArea.setBackground(Color.decode("#E64A19"));
            } else if ( e.getSource() == redText ){
                textArea.setForeground(Color.decode("#E53935"));
            }else if ( e.getSource() == blackText ){
                textArea.setForeground(Color.black);
            }else if ( e.getSource() == greyText ){
                textArea.setForeground(Color.decode("#9E9E9E"));
            }else if ( e.getSource() == blueText ){
                textArea.setForeground(Color.decode("#42A5F5"));
            }else if ( e.getSource() == darkBlueText ){
                textArea.setForeground(Color.decode("#283593"));
            }else if ( e.getSource() == whiteText ){
                textArea.setForeground(Color.white);
            }else if ( e.getSource() == purpleText ){
                textArea.setForeground(Color.decode("#8E24AA"));
            }else if ( e.getSource() == pinkText ){
                textArea.setForeground(Color.decode("#F06292"));
            }else if ( e.getSource() == greenText ){
                textArea.setForeground(Color.decode("#1B5E20"));
            }else if ( e.getSource() == orangeText ){
                textArea.setForeground(Color.decode("#E64A19"));
            }
        }
    }
}

