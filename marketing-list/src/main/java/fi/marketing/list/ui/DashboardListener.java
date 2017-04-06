package fi.marketing.list.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * DashboardListener implements ActionListener and provides actions of the
 * buttons in the UI.
 */
public class DashboardListener implements ActionListener {

//    private JTextField tulosteKentta;
//    private JButton suorita;
    private JLabel label;
    private String text;

    public DashboardListener(JLabel la, String text) {
        this.label = la;
        this.text = text;
    }
//    public DashboardListener(JTextField tuloste, JButton suorita) {
//        this.tulosteKentta = tuloste;
//        this.suorita = suorita;
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(label)) {
            text = "455";
        }
//        if (e.getSource().equals(suorita)) {
//            tulosteKentta.setText("1");
//        }

    }
}
