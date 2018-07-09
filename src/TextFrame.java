import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class TextFrame extends JFrame {

    JTextArea textArea = new JTextArea();
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");
    JMenu settingsMenu = new JMenu("Settings");
    JMenu helpMenu = new JMenu("Help");
    JPanel panel = new JPanel();
    GroupLayout layout = new GroupLayout(panel);
    JPanel panelCenter = new JPanel();
    JButton buttonSave = new JButton("Save");
    JButton buttonOpenFile = new JButton("Open");
    JButton buttonNewFile = new JButton("New");
    JButton buttonSpellCheck = new JButton("SpellChecker");
    JButton buttonAutoCorrect = new JButton("AutoCorrect");
    JButton buttonIncreaseSize = new JButton("Increase Size");
    JButton buttonDecreaseSize = new JButton("Decrease Size");


    public TextFrame(File file){
        panelCenter.setLayout(new BorderLayout());
        panelCenter.add(textArea, BorderLayout.CENTER);
        setJMenuBar(menuBar);
        addComponentsButtons();
        addComponentsMenu();
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
        panel.add(buttonAutoCorrect);
        panel.add(buttonDecreaseSize);
        panel.add(buttonDecreaseSize);
    }

    public void addComponentsMenu(){
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);
    }
    public void addComponentsMenuItems(){

    }






}
