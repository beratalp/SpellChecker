import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightClickMenu extends JPopupMenu {
    private JMenuItem copy;
    private JMenuItem cut;
    private JMenuItem paste;

    public RightClickMenu(){
        setComponents();
        addComponents();
        addActionListeners();
    }

    public void addComponents() {
        add(copy);
        add(cut);
        add(paste);
        add(new JSeparator());
    }

    public void setComponents(){
        copy = new JMenuItem("Copy");
        cut = new JMenuItem("Cut");
        paste = new JMenuItem("Paste");
    }

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

    public void addSuggestion(String suggestion){
        JMenuItem suggestionItem = new JMenuItem(suggestion);
        add(suggestionItem);
    }
}
