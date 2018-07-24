import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 404 Not Found
 * @version 24.07.2018
 */
public class SetFontDialog extends JDialog{
    private JButton applyButton;
    private JComboBox<Font> fontBox;

    //Constructor
    public SetFontDialog() {
        setTitle("Set Font");
        applyButton =  new JButton("Apply");
        setLayout (new FlowLayout());
        GraphicsEnvironment graphEnviron = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] allFonts = graphEnviron.getAllFonts();

        fontBox = new JComboBox<>(allFonts);
        fontBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                                                          Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value != null) {
                    Font font = (Font) value;
                    value = font.getName();
                }
                return super.getListCellRendererComponent(list, value, index,
                        isSelected, cellHasFocus);
            }
        });
        JScrollPane scroll = new JScrollPane(fontBox) ;
        Font defaultFont = TextFrame.textArea.getFont();
        fontBox.setSelectedItem(defaultFont);
        add(scroll);
        add(applyButton);
        applyButton.addActionListener(new ButtonListener());
        setSize(300,150);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            TextFrame.setTextFont((Font) fontBox.getSelectedItem());
            setVisible(false);
            dispose();
        }
    }
}
