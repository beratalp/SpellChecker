import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 404 Not Found
 * @version 24.07.2018
 */

public class RightClickMenu extends JPopupMenu {
    private JMenuItem copy;
    private JMenuItem cut;
    private JMenuItem paste;

    //Constructor

    /**
     *
     */
    public RightClickMenu(){
        setComponents();
        addComponents();
        addActionListeners();
    }

    /**
     * this method is for adding components to the right click menu
     */
    public void addComponents() {
        add(copy);
        add(cut);
        add(paste);
        add(new JSeparator());
    }

    /**
     * This method is for setting components name
     */
    public void setComponents(){
        copy = new JMenuItem("Copy");
        cut = new JMenuItem("Cut");
        paste = new JMenuItem("Paste");
    }

    /**
     * This method is for adding actionListeners to menuItems
     */
    public void addActionListeners(){
        copy.addActionListener(new CopyAction());
        paste.addActionListener(new PasteAction());
        cut.addActionListener(new CutAction());
    }

    public class CopyAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            String selected = TextFrame.getSelected();
            StringSelection selection = new StringSelection(selected);
            clipboard.setContents(selection, null);
        }
    }

    public class PasteAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            try{
                TextFrame.pasteText((String) clipboard.getData(DataFlavor.stringFlavor));
            }
            catch (Exception ex){
                SpellChecker.Warning(ex, "You shouldn't have done that.");
            }
        }
    }
    public class CutAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            String selected = TextFrame.getSelected();
            try{
                TextFrame.cutText(selected);
                StringSelection selection = new StringSelection(selected);
                clipboard.setContents(selection, null);
            }
            catch (Exception ex){
                SpellChecker.Warning(ex, "You shouldn't have done that.");
            }
        }
    }

    public class SuggestionAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            TextFrame.replaceText(TextFrame.toReplace, ((JMenuItem)e.getSource()).getText());
        }
    }

    /**
     * This method is for adding suggestion to the suggestion part of the right click menu.
     * @param suggestion recommended suggestion of the selected word.
     */
    public void addSuggestion(String suggestion){
        JMenuItem suggestionItem = new JMenuItem(suggestion);
        suggestionItem.addActionListener(new SuggestionAction());
        add(suggestionItem);
    }
}
