package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    //I need a load game button
    //I need a help button
    //I need an exit game button

    //For the attaching objects to the buttons
    private final List<ImageIcon> myMenu;

    //for storing the buttons proper
    private final List<JButton> myMenuButtons;

    //the bar proper
    private final JPanel myMenuBar;

    public MainMenu() {
        myMenu = new ArrayList<>();
        myMenuButtons = new ArrayList<>();
        myMenuBar = new JPanel(new GridLayout(0, 1));

        createButtons();
    }

    public JPanel getMenu() {
        return myMenuBar;
    }

    //I will have to make each button differently sadly i think
    private void createButtons() {
        myMenu.add(new ImageIcon("files/new.png"));
        myMenu.add(new ImageIcon("files/load.png"));
        myMenu.add(new ImageIcon("files/help.png"));


        for(final ImageIcon i : myMenu) {
            myMenuButtons.add(buttonMaker(i));
        }

        for (final JButton b : myMenuButtons) {
            myMenuBar.add(b);
        }
        myMenuBar.add(createExitButton(new ImageIcon("files/exit.png")));
    }

    private JButton createExitButton(ImageIcon theIcon) {
        final JButton button = new JButton(theIcon);
        button.setEnabled(true);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                System.exit(1);
            }
        });

        return button;
    }

    private JButton createHelpButton(ImageIcon theIcon) {
        final JButton button = new JButton(theIcon);
        button.setEnabled(true);

        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                //need to clear the jframe, and attach onto it the image for the help screen
                //
                new Help();
            }
        });

        return button;
    }

    public JButton buttonMaker(ImageIcon theIcon) {
        final JButton button = new JButton(theIcon);
        button.setEnabled(true);

        return button;
    }
}
