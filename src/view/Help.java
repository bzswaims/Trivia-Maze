//TODO make the help button redraw the screen.

package view;

import javax.swing.*;

/**
 * Displays the help screen.
 *
 * @author Zane Swaims (bzswaims@uw.edu), Abbygaile Yrojo
 * @version 0.2
 */
public class Help extends JPanel {

        public Help() {
            start();
        }

        public void start() {
            ImageIcon image = new ImageIcon("files/helpmenu.png");
            JLabel label = new JLabel(image);
            this.add(label);
            this.setVisible(true);
        }
    }

