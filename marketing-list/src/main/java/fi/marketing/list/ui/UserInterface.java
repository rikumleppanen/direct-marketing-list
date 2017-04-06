package fi.marketing.list.ui;

import fi.marketing.list.logic.Consent;
import fi.marketing.list.logic.Type;
import fi.marketing.list.logic.lists.CustomerList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class UserInterface implements Runnable {

    private JFrame frame;
    private static final Color GREEN = new Color(200, 255, 200);
    private static final Color BLUE = new Color(200, 200, 255);
    private JButton nappi;
    private JTextField tulostusKentta;
    private JLabel label1;
    private CustomerList customers;

    /**
     * UserInterface is the main building block of the UI.
     */
    public UserInterface(CustomerList customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        frame = new JFrame("Dashboard");
        frame.setPreferredSize(new Dimension(800, 400));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());
//        frame.getContentPane().add(new ColorPanel(Color.pink, 800, 80), BorderLayout.NORTH);
//        frame.getContentPane().add(new ColorPanel(GREEN, 300, 420), BorderLayout.WEST);
//        frame.getContentPane().add(new ColorPanel(BLUE, 500, 420), BorderLayout.CENTER);

        //luoKomponentit(frame.getContentPane());
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        int count = customers.numberOfCustomers();
        int emails = customers.getNumberOfEmails();
        int numbers = customers.getNumberOfPhoneNumbers();
        //JLabel label2 = new JLabel("Recent downloads of contact lists: ", JLabel.SOUTH);
        //Set the position of the text, relative to the icon:

        JTextArea textArea = new JTextArea("Total number of customers: " + count + "\n" + "Total number of phone numbers: " + numbers + "\n" + "Total number of emails: " + emails);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setBorder(BorderFactory.createEmptyBorder());
        container.add(textArea, BorderLayout.CENTER);
        textArea.setFont(UIManager.getFont("Label.font"));
        //JLabel label2 = new JLabel("Text-Only Label", JLabel.EAST);

        //DashboardListener listen = new DashboardListener(label1, text);
        //container.add(label2);
//        ColorPanel pink = new ColorPanel(Color.pink, 800, 80);
//        container.add(pink, BorderLayout.NORTH);
//        container.add(new ColorPanel(GREEN, 300, 420), BorderLayout.WEST);
//        container.add(new ColorPanel(BLUE, 500, 420), BorderLayout.CENTER);
//        //BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
//        nappi = new JButton("Suorita");
//        pink.add(nappi);
//        pink.add(new JButton("Testaa"));
//        pink.add(new JButton("Lähetä"));
//        tulostusKentta = new JTextField("0");
//        pink.add(tulostusKentta);
//        DashboardListener listen = new DashboardListener(tulostusKentta, nappi);
//        nappi.addActionListener(listen);
//        tulostusKentta.addActionListener(listen);
//        //container.add(tulostusKentta);
//        container.add(pink.getComponent(0));
        //container.add(pink.getComponent(0));
        //GridLayout overallLayout = new GridLayout(1,3);
        //container.setLayout(new BorderLayout());
//        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
//        container.setLayout(overrallLayout);
//
//        container.add(new JLabel("Valitse ruokavalio:"));
//        JRadioButton liha = new JRadioButton("Liha");
//        JRadioButton kala = new JRadioButton("Kala");
//        container.add(new JLabel("Eka!"));
//        container.add(new JLabel("Toka!"));
//        container.add(new JLabel("Kolmas!"));
        //GridLayout layout = new GridLayout(1, 3);
        //Edellä mainitut eivät näy ilman setLayout-komentoa, siksi oikea muoto:
//        container.add(luoValikko(), BorderLayout.NORTH);
//        container.add(statisticsDesktop(), BorderLayout.WEST);
        //container.setLayout(new BorderLayout());
//        JButton nappi = new JButton("Pohjoinen (North)");
//        container.add(nappi, BorderLayout.NORTH);
//        container.add(new JButton("Itä (East)"), BorderLayout.EAST);
//        //container.add(new JButton("Etelä (South)"), BorderLayout.SOUTH);
//        JLabel kentta = new JLabel("Länsi (West)");
//        container.add(kentta, BorderLayout.WEST);
//        container.add(new JButton("Keski (Center)"), BorderLayout.CENTER);
//
//        container.add(new JButton("Oletuspaikka (Center)"));
//
//        nappi.addActionListener(new DashboardListener());
//        //Koska edelliset eivät vielä tee mitään, tarvitaan tapahtumakuuntelija:
//        JButton nappi = new JButton("Viestitä!");
//        nappi.addActionListener(new ViestiKuuntelija());
//
//        container.add(nappi);
    }

//    private JPanel statisticsDesktop() {
//        JPanel panel = new JPanel(new GridLayout(1, 3));
//        return panel;
//    }
//
//    private JPanel luoValikko() {
//        JPanel panel = new JPanel(new GridLayout(1, 3));
//        panel.add(new JButton("Suorita"));
//        panel.add(new JButton("Testaa"));
//        panel.add(new JButton("Lähetä"));
//        return panel;
//
//        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
//        container.setLayout(layout);
//
//        container.add(new JLabel("Eka!"));
//        container.add(new JLabel("Toka!"));
//        container.add(new JLabel("Kolmas!"));
//    }
    public JFrame getFrame() {
        return frame;
    }
}
