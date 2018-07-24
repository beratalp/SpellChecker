import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 * This class is for
 * @author 404 Not Found
 * @version 24.07.2018
 */

public class TextFrame extends JFrame {
    static JTextPane textArea = new JTextPane();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu fileMenu = new JMenu("File");
    private JMenu editMenu = new JMenu("Edit");
    private JMenu settingsMenu = new JMenu("Settings");
    private JMenu helpMenu = new JMenu("Help   ");
    private JMenu textMenu = new JMenu("Text");
    private JMenu backgroundMenu = new JMenu("Background");
    private JMenu backgroundColorMenu = new JMenu("Color");
    private JMenu textColorMenu = new JMenu("Color");
    private JMenu languageMenu = new JMenu("Language");
    private JMenuItem sizeItem = new JMenuItem("Size");
    private JMenuItem typefaceItem = new JMenuItem("Typeface");
    private JMenuItem blackBackground = new JMenuItem("Black");
    private JMenuItem redBackground = new JMenuItem("Red");
    private JMenuItem greyBackground = new JMenuItem("Grey");
    private JMenuItem blueBackground = new JMenuItem("Blue");
    private JMenuItem darkBlueBackground = new JMenuItem("Dark Blue");
    private JMenuItem whiteBackground = new JMenuItem("White");
    private JMenuItem orangeBackground = new JMenuItem("Orange");
    private JMenuItem greenBackground = new JMenuItem("Green");
    private JMenuItem purpleBackground = new JMenuItem("Purple");
    private JMenuItem pinkBackground = new JMenuItem("Pink");
    private JMenuItem blackText = new JMenuItem("Black");
    private JMenuItem redText = new JMenuItem("Red");
    private JMenuItem greyText = new JMenuItem("Grey");
    private JMenuItem blueText = new JMenuItem("Blue");
    private JMenuItem darkBlueText = new JMenuItem("Dark Blue");
    private JMenuItem whiteText = new JMenuItem("White");
    private JMenuItem orangeText = new JMenuItem("Orange");
    private JMenuItem greenText = new JMenuItem("Green");
    private JMenuItem purpleText = new JMenuItem("Purple");
    private JMenuItem pinkText = new JMenuItem("Pink");
    private JMenuItem newFile = new JMenuItem("New");
    private JMenuItem openFile = new JMenuItem("Open");
    private JMenuItem saveFile = new JMenuItem("Save");
    private JMenuItem saveAsFile = new JMenuItem("Save As");
    private JMenuItem quit = new JMenuItem("Quit");
    private JMenuItem about = new JMenuItem("About");
    private JMenuItem forum = new JMenuItem("Forum");
    private JCheckBoxMenuItem onlineMode = new JCheckBoxMenuItem("Online Mode");
    private JCheckBoxMenuItem englishMode = new JCheckBoxMenuItem("English");
    private JCheckBoxMenuItem turkishMode = new JCheckBoxMenuItem( "Turkish" );
    SpellChecker spellChecker;
    private SpellChecker.Language lang = SpellChecker.Language.ENGLISH;

    private JPanel panel = new JPanel();
    private JPanel panelCenter = new JPanel();
    private JPanel panelLow = new JPanel();
    private JButton buttonSave = new JButton();
    private JButton buttonOpenFile = new JButton();
    private JButton buttonNewFile = new JButton();
    private JButton buttonSpellCheck = new JButton();
    private JButton buttonAutoCorrect = new JButton();
    private JButton buttonIncreaseSize = new JButton();
    private JButton buttonDecreaseSize = new JButton();
    private JLabel label = new JLabel("Ready.");
    RightClickMenu rightClickMenu;

    private TextFile file;
    private static ArrayList<Word> words;
    private static ArrayList<Word> synonyms = new ArrayList<>();

    private static float textSize = 12;
    private static Font textFont;
    public static String toReplace;



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
        englishMode.setSelected(true);
        add(panel, BorderLayout.NORTH);
        add(panelCenter,BorderLayout.CENTER);
        add(panelLow , BorderLayout.SOUTH);
        addStatusBar();
        panel.setBackground(Color.decode("#455A64"));
        panelCenter.setBackground(Color.decode("#455A64"));
        setTitle(file.getShortPath());
        setSize(950, 800);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new ExitListener());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This method is for adding actionListeners to all the components
     */
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
        englishMode.addActionListener(new menuAction());
        turkishMode.addActionListener(new menuAction());
        buttonSave.addActionListener(new buttonAction());
        onlineMode.addActionListener(new menuAction());
        saveFile.addActionListener(new menuAction());
    }

    /**
     * This method is for adding the status bar
     */
    public void addStatusBar(){
        panelLow.setLayout(new BoxLayout(panelLow, BoxLayout.X_AXIS));
        panelLow.setBorder(new BevelBorder(BevelBorder.LOWERED));
        label.setHorizontalAlignment(SwingConstants.LEFT);
        panelLow.add(label);
    }

    /**
     * This method is for adding the components on the frame.
     */

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

    /**
     * This method is for adding the all components on the menu bar
     */
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

    /**
     * This method is for adding the menu items on the menus
     */
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
        languageMenu.add(englishMode);
        languageMenu.add(turkishMode);
        helpMenu.add(forum);
        helpMenu.add(new JSeparator());
        helpMenu.add(about);
    }

    /**
     * This method is for initialize the text field
     * @param file the recent file that the user want to work on
     * @throws IOException in case the file does not exist
     */
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
                    words = spellChecker.spellCheck(textArea.getText(), lang);
                    label.setText("Finding synonyms...");
                    synonyms = spellChecker.findSynonyms(textArea.getText(), lang);
                    for(Word word: words){
                        if(word.isWrong())
                            underLineWord(word.getOrig(), word.getIndex());
                    }
                    try{
                        for(Word word: synonyms){
                            words.add(word);
                        }
                    }
                    catch (Exception ex){
                    }
                } catch (Exception exception) {
                    spellChecker.Error(exception);
                }
                try{
                    label.setText("Word Count: " + synonyms.size() + "\t" + "Misspelled Words: " + (words.size() - synonyms.size()));
                }
                catch(Exception ex){
                    label.setText("Word Count: " + words.size());
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
                if (getTitle().equals("untitled.txt") ){
                    String fileString = saveAsAction();
                    setTitle(fileString);
                }
                else{
                    try{
                        file.saveFile(new File(file.getPath()), textArea.getText());
                    }
                    catch (Exception ex){
                        SpellChecker.Error(ex);
                    }
                }
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
                closeAction(true);
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
                String fileString = saveAsAction();
                setTitle(fileString);
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
            } else if ( e.getSource() == englishMode ){
                    turkishMode.setState(false);
                    lang = SpellChecker.Language.ENGLISH;
            }
            else if ( e.getSource() == turkishMode){
                    englishMode.setState(false);
                    lang = SpellChecker.Language.TURKISH;
            } else if (e.getSource() == onlineMode){
                if ( onlineMode.isSelected() == false )
                SpellChecker.isOnline = false;
            } else if ( e.getSource() == saveFile ){
                if (getTitle().equals("untitled.txt") ){
                    String fileString = saveAsAction();
                    setTitle(fileString);
                }
                else{
                    try{
                        file.saveFile(new File(file.getPath()), textArea.getText());
                    }
                    catch (Exception ex){
                        SpellChecker.Error(ex);
                    }
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

    /**
     * This method is for pasting the text.
     * @param text the selected text that wanted to paste in JPaneEditor.
     */
    public static void pasteText(String text){
        int location = textArea.getCaretPosition();
        Document doc = textArea.getDocument();
        try{
            doc.insertString(location, text, null);
        }
        catch (BadLocationException ex){
        }
    }

    /**
     * This method is for cutting the text that have been selected.
     * @param selection the String tht wanted to cut.
     */
    public static void cutText(String selection){
        try {
            if(selection == null){
                return;
            }
            textArea.replaceSelection("");
        } catch( Exception ex ){
            }
    }

    /**
     * This method is for setting the text font according to giving the font type.
     * @param font Font that wanted to set.
     */
    public static void setTextFont(Font font){
        textArea.setFont(font.deriveFont(textSize));
    }

    /**
     * This method is for setting the text size according to giving size number.
     * @param size size number that wanted to set the size.
     */
    public static void setTextSize(int size){
        textSize = size;
        textArea.setFont(textArea.getFont().deriveFont(textSize));
    }

    /**
     * This method is for etting the current text size.
     * @return String representation of the current text size.
     */
    public static String getTextSize(){
        return ((int) textSize) + "";
    }

    /**
     * This method is for underlining misspelled word that had been entered the textArea after spellchecking.
     * @param word word that misspelled
     * @param offset ??
     */
    public static void underLineWord(String word, int offset){
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setUnderline(attributeSet, true);
        String[] strings = textArea.getText().split(" ");
        int length = word.length();
        if(word.equals(strings[strings.length - 1])){
            length = length - 1;
        }
        textArea.getStyledDocument().setCharacterAttributes(offset, length,
                attributeSet, true);
    }

    /**
     * This method is for removing the underline function after the correction.
     */
    public static void removeUnderLine(){
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        textArea.getStyledDocument().setCharacterAttributes(0, textArea.getText().length(), attributeSet, true);
    }

    /**
     * this method is for creating the right click menu
     */
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
                textSet = true;
                break;
            }
            caretPosition--;
        }
        caretPosition += 2;
        System.out.println(caretPosition);
        try{
            c = textArea.getText(caretPosition, 1).charAt(0);
        }
        catch (Exception ex){
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
        char[] chars = text.toCharArray();
        Character punctuation = null;
        String punctutations = ".,:;";
        for(char ch: chars){
            for(char c2: punctutations.toCharArray()){
                if(c2 == ch){
                    punctuation = c2;
                }
            }
        }
        toReplace = text;
        try{
            for(Word word: words){
                if(word.getOrig().equals(text.trim().replace(".", ""))){
                    for(String suggestion: word.getSuggestions()){
                        if(punctuation != null)
                            rightClickMenu.addSuggestion(suggestion + punctuation);
                        else
                            rightClickMenu.addSuggestion(suggestion);
                    }
                }
            }
        }
        catch (Exception ex){
        }
    }
    public String saveAsAction(){
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(saveFile);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File newFile = fileChooser.getSelectedFile();
            if (newFile == null) {
                return null;
            }
            try{
                file.saveFile(newFile, textArea.getText());
            }
            catch (Exception ex){
                SpellChecker.Error(ex);
            }
        }

        try{
            return fileChooser.getSelectedFile().getName();
        }
        catch (Exception ex){
            SpellChecker.Error(ex);
            return null;
        }
    }

    /**
     * This method is for replacing the misspelled word according to user selection
     * or replacing the correct spelled word with its synonyms.
     * @param orig original version of the selected word
     * @param news if the orig is misspelled word, news will be the correct version of it
     * or if the orig is correct spelled word, the news will be one of the synonym of it.
     */
    public static void replaceText(String orig, String news){
        news = news + " ";
        try{
            for(Word word: synonyms){
                if(word.getOrig().equals(orig)){
                    news.trim();
                }
            }
        }
        catch (Exception e){

        }
        textArea.setText(textArea.getText().replaceFirst(orig, news));
        removeUnderLine();
        for(int i = 0; i < words.size(); i ++){
            if(words.get(i).getOrig().equals(orig) && words.get(i).isWrong()){
                words.get(i).setWrong(false);
            }
        }
        for(Word word: words){
            if(word.isWrong()){
                System.out.println(word.getOrig());
                int index = textArea.getText().indexOf(word.getOrig());
                if(index != -1)
                    underLineWord(word.getOrig(), index);
            }
        }
    }

    public class ExitListener extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e) {
            if(textArea.getText().length() > 0){
                closeAction(false);
            }
            else{
                System.exit(0);
            }
        }
    }

    public void closeAction(boolean openWelcome){
        try {
            if ( file.isEqual(textArea.getText())){
                setVisible(false);
                dispose();
                if(openWelcome)
                    new WelcomeScreen();
                else
                    System.exit(0);
            } else{
                Object[] options = {"Close",
                        "Cancel", "Save"};
                int n = JOptionPane.showOptionDialog(
                        null,"Save Changes to document \"" + file.getShortPath()+ "\" before closing it ? " ,
                        "Unsaved Changes",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if(n == 0){
                    setVisible(false);
                    dispose();
                    try{
                        if(openWelcome)
                            new WelcomeScreen();
                        else
                            System.exit(0);
                    }
                    catch (Exception ex){
                        SpellChecker.Error(ex);
                    }
                }
                else if(n == 2){
                    saveAsAction();
                    setVisible(false);
                    dispose();
                    if(openWelcome)
                        new WelcomeScreen();
                    else
                        System.exit(0);
                }
            }
        } catch ( Exception ex){}
    }
}

