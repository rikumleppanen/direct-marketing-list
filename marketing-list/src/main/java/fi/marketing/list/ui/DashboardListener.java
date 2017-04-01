package fi.marketing.list.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardListener implements ActionListener {

    public DashboardListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Viesti vastaanotettu!");
    }

}
