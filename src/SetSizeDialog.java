import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Collections;

public class SetSizeDialog extends JDialog {


    private JSpinner spinner;
    private JButton applyButton;
    public SetSizeDialog(){
        setLayout(new FlowLayout(FlowLayout.CENTER ,100,10));
        applyButton = new JButton("Apply");
        setTitle("Set Size");
        Integer[] size = new Integer[71];
        int j = 0;
        for(Integer i = 8; i <= 78 || j == 72; i++){
            size[j] = i;
            j++;
        }
        SpinnerListModel sizeModel = new SpinnerListModel(size);
        spinner = new JSpinner(sizeModel);
        Dimension dimension = new Dimension(35,30);
        spinner.setPreferredSize(dimension);
        spinner.setValue(12);
        add(spinner);
        add(applyButton);
        setSize(300,150);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

