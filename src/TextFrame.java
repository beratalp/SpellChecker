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


    JPanel panel = new JPanel();
    JPanel panelCenter = new JPanel();
    JButton buttonSave = new JButton("Save");
    JButton buttonOpenFile = new JButton("Open");
    JButton buttonNewFile = new JButton("New");
    JButton buttonSpellCheck = new JButton("SpellChecker");
    JButton buttonAutoCorrect = new JButton("AutoCorrect");
    JButton buttonIncreaseSize = new JButton("Increase Size");
    JButton buttonDecreaseSize = new JButton("Decrease Size");


    public TextFrame(File file) throws FileNotFoundException{
        panelCenter.setLayout(new BorderLayout());
        panelCenter.add(textArea, BorderLayout.CENTER);
        initializeTextField(readFile(file));
        setJMenuBar(menuBar);
        addComponentsButtons();
        addComponentsMenu();
        addComponentsMenuItems();
        add(panel, BorderLayout.NORTH);
        add(panelCenter);
        setSize(950,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
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

    }

    public String readFile(File file) throws FileNotFoundException{
        String returnString = "";
        Scanner fileScanner = new Scanner(file);
        while(fileScanner.hasNext()){
            returnString += fileScanner.nextLine();
            returnString += "\n";
        }
        return returnString;
    }

    public void initializeTextField(String fileStr){
        textArea.setText(fileStr);
    }

    class buttonAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(buttonSpellCheck)){
                SpellCheckerOnline spellChecker = new SpellCheckerOnline();
                try {
                    spellChecker.spellCheck(textArea.getText(), SpellChecker.Language.ENGLISH);
                }
                catch (Exception exception){
                    spellChecker.Error(exception);
                }
            }
        }
    }

}
