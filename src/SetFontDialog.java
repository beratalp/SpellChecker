import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 * Auto Generated Java Class.
 */
public class SetFontDialog extends JDialog{

    private JButton applyButton;

    public SetFontDialog() {
        setTitle("Set Font");
        applyButton =  new JButton("Apply");
        setLayout (new FlowLayout());
        GraphicsEnvironment graphEnviron = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] allFonts = graphEnviron.getAllFonts();

        JComboBox<Font> fontBox = new JComboBox<>(allFonts);
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
        Font defaultFont = new Font("Arial",Font.PLAIN, 1);
        fontBox.setSelectedItem(defaultFont);
        add(scroll);
        add(applyButton);
        setSize(300,150);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
