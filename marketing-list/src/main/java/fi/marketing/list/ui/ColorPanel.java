package fi.marketing.list.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * ColorPanel divides the UI into different elements by color.
 */
public class ColorPanel extends JPanel {

    private static final float FONT_POINTS = 24f;
    private int prefW;
    private int prefH;

    public ColorPanel(Color color, int prefW, int prefH) {
        setBackground(color);

        createComponents(color);

        this.prefW = prefW;
        this.prefH = prefH;

        // GBL can be useful for simply centering components
        setLayout(new GridBagLayout());
        String text = String.format("%d x %d", prefW, prefH);
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(FONT_POINTS));
        label.setForeground(Color.gray);
        add(label);

    }

    private void createComponents(Color color) {

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(prefW, prefH);
    }
}
