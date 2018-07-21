import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class SetSizeDialog extends JDialog {


    private JSpinner spinner;
    private JButton applyButton;
    public SetSizeDialog(){
        setLayout(new FlowLayout(FlowLayout.CENTER ,100,10));
        applyButton = new JButton("Apply");
        setTitle("Set Size");
        String[] size = new String[71];
        int j = 0;
        for(Integer i = 8; i <= 78 || j == 72; i++){
            size[j] = i + "";
            j++;
        }
        SpinnerListModel sizeModel = new SpinnerListModel(size);
        spinner = new JSpinner(sizeModel);
        Dimension dimension;
        if (System.getProperty("os.name").startsWith("Windows")){
            dimension = new Dimension(35,30);
        }
        else{
            dimension = new Dimension(45,30);
        }
        spinner.setPreferredSize(dimension);
        spinner.setValue(TextFrame.getTextSize());
        add(spinner);
        add(applyButton);
        applyButton.addActionListener(new ButtonListener());
        setSize(300,150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            TextFrame.setTextSize(Integer.parseInt((String) spinner.getValue()));
            setVisible(false);
            dispose();
        }
    }
}

