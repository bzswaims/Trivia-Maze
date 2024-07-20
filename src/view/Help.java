package view;

import javax.swing.*;
import java.awt.*;

public class Help {

    //jframe to put the thing sin
    private JFrame myHelp;

    public Help() {
        myHelp = new JFrame();
        myHelp.setLocationRelativeTo(null);
        myHelp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        start();
    }

    public void start() {
        ImageIcon image = new ImageIcon("files/help.png");
        JLabel label = new JLabel(image);
        myHelp.add(label);
        myHelp.pack();
        myHelp.setVisible(true);
    }
}
