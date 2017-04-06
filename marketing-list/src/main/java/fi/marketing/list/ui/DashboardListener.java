package fi.marketing.list.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * DashboardListener implements ActionListener and provides actions of the
 * buttons in the UI.
 */
public class DashboardListener implements ActionListener {

    private JTextField tulosteKentta;
    private JButton suorita;

    public DashboardListener(JTextField tuloste, JButton suorita) {
        this.tulosteKentta = tuloste;
        this.suorita = suorita;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(suorita)) {
            tulosteKentta.setText("1");
        }

    }
}
