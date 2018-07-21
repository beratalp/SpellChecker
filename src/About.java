import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;

public class About extends JFrame {
    JPanel panelTop = new JPanel();
    JLabel label = new JLabel(SpellChecker.APP_NAME + " " + SpellChecker.VERSION , SwingConstants.CENTER);
    JTabbedPane tabPane = new JTabbedPane();
    JPanel panel2 = new JPanel();
    JPanel panel1 = new JPanel();
    JLabel mailAlp = new JLabel("Berat Alp Erbil - beratalp@gmail.com");
    JLabel mailBeril = new JLabel("Beril Bayram - berilbayram@gmail.com");
    JLabel mailSalman = new JLabel("Salman Soomro - salmanakhtar@gmail.com ");
    JLabel mailHassam = new JLabel("Hassam Abdullah - hassamabdullah1@gmail.com");
    JLabel mailUmer = new JLabel("Umer Shamaan - shamaan0086@gmail.com");
    public About(){
        setLocationRelativeTo(null);
        panel1.setLayout( new FlowLayout( FlowLayout.LEFT,0,10) ) ;
        panel1.add(mailAlp);
        panel1.add(mailBeril );
        panel1.add(mailSalman);
        panel1.add(mailHassam);
        panel1.add(mailUmer);
        addMouseListeners();
        label.setForeground(Color.white);
        panelTop.setBackground(Color.decode("#455A64"));
        panelTop.setLayout(new BorderLayout() );
        setBackground(Color.decode("#455A64"));
        setForeground(Color.decode("#455A64"));
        try {
            BufferedImage logo = ImageIO.read(new File("logo.png"));
            JLabel logoLabel = new JLabel(new ImageIcon(logo));
            panelTop.add( logoLabel , BorderLayout.CENTER);
        } catch (Exception ex){
            SpellChecker.Error(ex);
        }
        panelTop.add( label, BorderLayout.SOUTH );
        setLayout(new GridLayout(2,1));
        tabPane.add("Developers", panel1);
        tabPane.add("Open Source Licenses", panel2);
        add(panelTop);
        add(tabPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
        setVisible(true);
    }
    public void addMouseListeners(){
        mailAlp.addMouseListener( new mouseEvent());
        mailBeril.addMouseListener( new mouseEvent());
        mailSalman.addMouseListener( new mouseEvent());
        mailUmer.addMouseListener( new mouseEvent());
        mailHassam.addMouseListener( new mouseEvent());
    }

    public class mouseEvent implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            if( e.getSource() == mailAlp ){
                if (Desktop.isDesktopSupported()) {
                    try{
                        Desktop.getDesktop().browse(new URI("mailto:beratalp@gmail.com"));
                    } catch ( Exception ex ){
                        SpellChecker.Error(ex);
                    }
                }
            } else if ( e.getSource() == mailBeril ){
                if (Desktop.isDesktopSupported()) {
                    try{
                        Desktop.getDesktop().browse(new URI("mailto:berilbayram@gmail.com"));
                    } catch ( Exception ex ){
                        SpellChecker.Error(ex);
                    }
                }
            } else if ( e.getSource() == mailSalman ){
                if (Desktop.isDesktopSupported()) {
                    try{
                        Desktop.getDesktop().browse(new URI("mailto:salmanakhtar@gmail.com"));
                    } catch ( Exception ex ){
                        SpellChecker.Error(ex);
                    }
                }
            } else if ( e.getSource() == mailHassam ){
                if (Desktop.isDesktopSupported()) {
                    try{
                        Desktop.getDesktop().browse(new URI("mailto:hassamabdullah1@gmail.com"));
                    } catch ( Exception ex ){
                        SpellChecker.Error(ex);
                    }
                }
            } else if ( e.getSource() == mailUmer ){
                if (Desktop.isDesktopSupported()) {
                    try{
                        Desktop.getDesktop().browse(new URI("mailto:shamaan0086@gmail.com"));
                    } catch ( Exception ex ){
                        SpellChecker.Error(ex);
                    }
                }
            }
        }
        public void mousePressed(MouseEvent e) {

        }
        public void mouseReleased(MouseEvent e) {

        }
        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {

        }
    }
}
