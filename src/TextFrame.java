import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class TextFrame extends JFrame {
    static JTextPane textArea = new JTextPane();
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
    JButton buttonNewFile = new JButton();
    JButton buttonSpellCheck = new JButton();
    JButton buttonAutoCorrect = new JButton();
    JButton buttonIncreaseSize = new JButton();
    JButton buttonDecreaseSize = new JButton();
    RightClickMenu rightClickMenu;

    private TextFile file;
    private ArrayList<Word> words;
    private ArrayList<Word> synonyms;

    private static float textSize = 12;
    private static Font textFont;


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
        sizeItem.addActionListener(new menuAction());
        typefaceItem.addActionListener(new menuAction());
        textArea.addMouseListener(new RightMenuAction());
        buttonOpenFile.addActionListener(new buttonAction());
        buttonNewFile.addActionListener(new buttonAction());
        buttonDecreaseSize.addActionListener(new buttonAction());
        buttonIncreaseSize.addActionListener(new buttonAction());
        buttonSave.addActionListener(new buttonAction());
        quit.addActionListener(new menuAction());
        about.addActionListener(new menuAction());
        openFile.addActionListener(new menuAction());
        saveAsFile.addActionListener(new menuAction());
        newFile.addActionListener(new menuAction());
    }

    public void addComponentsButtons() {
        panel.add(buttonNewFile);
        buttonNewFile.setToolTipText("New File");
        try{
            Image img = ImageIO.read(getClass().getResource("new.png"));
            buttonNewFile.setIcon(new ImageIcon(img));
        }
        catch (Exception ex){
            SpellChecker.Error(ex);
        }
        buttonOpenFile.setToolTipText("Open File");
        panel.add(buttonOpenFile);
        try{
            Image img = ImageIO.read(getClass().getResource("open.png"));
            buttonOpenFile.setIcon(new ImageIcon(img));
        }
        catch (Exception ex){
            SpellChecker.Error(ex);
        }
        buttonSave.setToolTipText("Save");
        panel.add(buttonSave);
        try{
            Image img = ImageIO.read(getClass().getResource("save.png"));
            buttonSave.setIcon(new ImageIcon(img));
        }
        catch (Exception ex){
            SpellChecker.Error(ex);
        }
        panel.add(buttonSpellCheck);
        buttonSpellCheck.setToolTipText("Spell Check");
        try{
            Image img = ImageIO.read(getClass().getResource("spellCheck.png"));
            buttonSpellCheck.setIcon(new ImageIcon(img));
        }
        catch (Exception ex){
            SpellChecker.Error(ex);
        }
        buttonSpellCheck.addActionListener(new buttonAction());
        panel.add(buttonAutoCorrect);
        buttonAutoCorrect.setToolTipText("AutoCorrect");
        try{
            Image img = ImageIO.read(getClass().getResource("autoCorrect.png"));
            buttonAutoCorrect.setIcon(new ImageIcon(img));
        }
        catch (Exception ex){
            SpellChecker.Error(ex);
        }
        panel.add(buttonIncreaseSize);
        buttonIncreaseSize.setToolTipText("Increase Size");
        try{
            Image img = ImageIO.read(getClass().getResource("increaseSize.png"));
            buttonIncreaseSize.setIcon(new ImageIcon(img));
        }
        catch (Exception ex){
            SpellChecker.Error(ex);
        }
        panel.add(buttonDecreaseSize);
        buttonDecreaseSize.setToolTipText("Decrease Size");
        try{
            Image img = ImageIO.read(getClass().getResource("decreaseSize.png"));
            buttonDecreaseSize.setIcon(new ImageIcon(img));
        }
        catch (Exception ex){
            SpellChecker.Error(ex);
        }
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
                    synonyms = spellChecker.findSynonyms(textArea.getText(), SpellChecker.Language.ENGLISH);
                    for(Word word: words){
                        System.out.println(word.getOrig());
                        underLineWord(word.getOrig(), word.getIndex());
                    }
                    for(Word word: synonyms){
                        words.add(word);
                    }
                } catch (Exception exception) {
                    spellChecker.Error(exception);
                }
            } else if ( e.getSource().equals(buttonOpenFile) ){
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(null);
                File browseFile = fc.getSelectedFile();
                if(returnVal == 0){
                    setVisible(false);
                    try{
                        TextFile fileOpen = new TextFile(browseFile.getPath());
                        TextFrame frameNew = new TextFrame(fileOpen);
                    }
                    catch(Exception ex){
                        SpellChecker.Error(ex);
                    }
                }
            } else if ( e.getSource().equals( buttonNewFile ) ){
                try{
                    TextFile file = new TextFile();
                    TextFrame frame = new TextFrame(file);
                    setVisible(false);
                    dispose();
                }
                catch(Exception ex){
                    SpellChecker.Error(ex);
                }
            } else if( e.getSource().equals( buttonIncreaseSize) ){
                if(textSize ++ <= 78) {
                    textSize++;
                    textArea.setFont(textArea.getFont().deriveFont(textSize));
                }
                else{
                    SpellChecker.Warning(new Exception(), "You can't change size any longer.");
                }
            } else if( e.getSource().equals( buttonDecreaseSize) ) {
                if(textSize -- >= 8) {
                    textSize--;
                    textArea.setFont(textArea.getFont().deriveFont(textSize));
                }
                else{
                    SpellChecker.Warning(new Exception(), "You can't change size any longer.");
                }
            } else if(e.getSource().equals(buttonSave)) {
            }
        }
    }

    public class menuAction implements ActionListener {
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
            else if( e.getSource() == sizeItem){
                SetSizeDialog dialog = new SetSizeDialog();
            }else if( e.getSource() == typefaceItem){
                SetFontDialog dialog = new SetFontDialog();
            }else if (e.getSource().equals(quit)){
                try {
                    if ( file.isEqual(textArea.getText())){
                        setVisible(false);
                        dispose();
                        WelcomeScreen screen = new WelcomeScreen();
                    } else{
                        Object[] options = {"Close",
                                "Cancel", "Save"};
                        int n = JOptionPane.showOptionDialog(
                                null,"Save Changes to document \"" + file.getShortPath()+ "\" before closing it ? " ,
                                "UnSaved Changes",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);
                        if(n == 0){
                            setVisible(false);
                            dispose();
                            try{
                                new WelcomeScreen();
                            }
                            catch (Exception ex){
                                SpellChecker.Error(ex);
                            }
                        }
                        else if(n == 2){
                            JFileChooser fileChooser = new JFileChooser();
                            int returnValue = fileChooser.showSaveDialog(saveFile);
                            if (returnValue == JFileChooser.APPROVE_OPTION) {
                                File file = fileChooser.getSelectedFile();
                                if (file == null) {
                                    return;
                                }
                                if (!file.getName().toLowerCase().endsWith(".txt")) {
                                    file = new File(file.getParentFile(), file.getName() + ".txt");
                                }
                                try {
                                    textArea.write(new OutputStreamWriter(new FileOutputStream(file),
                                            "utf-8"));
                                } catch (Exception ex) {
                                    SpellChecker.Error(ex);
                                }
                            }
                            setVisible(false);
                            dispose();
                            new WelcomeScreen();
                        }
                    }
                } catch ( Exception ex){}
            } else if ( e.getSource() == about ){
                About about = new About();
            } else if ( e.getSource() == openFile ) {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(null);
                File browseFile = fc.getSelectedFile();
                if(returnVal == 0){
                    setVisible(false);
                    try{
                        TextFile file = new TextFile(browseFile.getPath());
                        TextFrame frame = new TextFrame(file);
                    }
                    catch(Exception ex){
                        SpellChecker.Error(ex);
                    }
                }
            } else if ( e.getSource() == saveAsFile ){
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showSaveDialog(saveFile);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File newFile = fileChooser.getSelectedFile();
                    if (newFile == null) {
                        return;
                    }
                    try{
                        file.saveFile(newFile, textArea.getText());
                    }
                    catch (Exception ex){
                        SpellChecker.Error(ex);
                    }
                }
            } else if ( e.getSource() == newFile ){
                try{
                    TextFile file = new TextFile();
                    TextFrame frame = new TextFrame(file);
                    setVisible(false);
                    dispose();
                }
                catch(Exception ex){
                    SpellChecker.Error(ex);
                }
            }
        }
    }
    class RightMenuAction implements MouseListener{
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(SwingUtilities.isRightMouseButton(e)) {
                populateMenu();
                rightClickMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    public static String getSelected(){
        return textArea.getSelectedText();
    }


    public static void pasteText(String text){
        int location = textArea.getCaretPosition();
        Document doc = textArea.getDocument();
        try{
            doc.insertString(location, text, null);
        }
        catch (BadLocationException ex){
        }
    }

    public static void cutText(String selection){
        try {
            if(selection == null){
                return;
            }
            textArea.replaceSelection("");
        } catch( Exception ex ){
            }
    }

    public static void setTextFont(Font font){
        textArea.setFont(font.deriveFont(textSize));
    }

    public static void setTextSize(int size){
        textSize = size;
        textArea.setFont(textArea.getFont().deriveFont(textSize));
    }
    public static String getTextSize(){
        return ((int) textSize) + "";
    }

    public void underLineWord(String word, int offset){
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setUnderline(attributeSet, true);
        textArea.getStyledDocument().setCharacterAttributes(offset, word.length(),
                attributeSet, true);
    }

    public void populateMenu(){
        rightClickMenu = new RightClickMenu();
        String text = "";
        boolean textSet = false;
        int caretPosition = textArea.getCaretPosition();
        char c = ' ';
        try{
            c = textArea.getText(caretPosition, 1).charAt(0);
        }
        catch (Exception ex){
            SpellChecker.Error(ex);
        }
        while(c != ' '){
            try{
                c = textArea.getText(caretPosition, 1).charAt(0);
            }
            catch (Exception ex){
                String[] textArray = textArea.getText().split(" ");
                text = textArray[0];
                System.out.println(text);
                textSet = true;
                break;
            }
            System.out.println(caretPosition);
            caretPosition--;
        }
        caretPosition += 2;
        System.out.println(caretPosition);
        try{
            c = textArea.getText(caretPosition, 1).charAt(0);
        }
        catch (Exception ex){
            SpellChecker.Error(ex);
        }
        while(c != ' ') {
            try {
                c = textArea.getText(caretPosition, 1).charAt(0);
                if(!textSet)
                    text += c;
            } catch (Exception ex) {
                String[] textArray = textArea.getText().split(" ");
                text = textArea.getText().split(" ")[textArray.length - 1];
                break;
            }
            caretPosition++;
        }
        try{
            for(Word word: words){
                if(word.getOrig().equals(text.trim())){
                    for(String suggestion: word.getSuggestions()){
                        rightClickMenu.addSuggestion(suggestion);
                    }
                }
            }
        }
        catch (Exception ex){
        }
    }

}

