package fi.marketing.list.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class UserInterface implements Runnable {

    private JFrame frame;

    public UserInterface() {
    }

    @Override
    public void run() {
        frame = new JFrame("Dashboard");
        frame.setPreferredSize(new Dimension(800, 400));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        GridLayout layout = new GridLayout(1, 3);
        //Edellä mainitut eivät näy ilman setLayout-komentoa, siksi oikea muoto:
        container.setLayout(new BorderLayout());
        
        JButton nappi = new JButton("Pohjoinen (North)");
        container.add(nappi, BorderLayout.NORTH);
        container.add(new JButton("Itä (East)"), BorderLayout.EAST);
        container.add(new JButton("Etelä (South)"), BorderLayout.SOUTH);
        JLabel kentta = new JLabel("Länsi (West)");
        container.add(kentta, BorderLayout.WEST);
        container.add(new JButton("Keski (Center)"), BorderLayout.CENTER);

        container.add(new JButton("Oletuspaikka (Center)"));
        
        nappi.addActionListener(new DashboardListener());
//        //Koska edelliset eivät vielä tee mitään, tarvitaan tapahtumakuuntelija:
//        JButton nappi = new JButton("Viestitä!");
//        nappi.addActionListener(new ViestiKuuntelija());
//
//        container.add(nappi);
    }

    public JFrame getFrame() {
        return frame;
    }
}
