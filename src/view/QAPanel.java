package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class QAPanel extends JPanel {
    private static final Dimension DIMENSION = new Dimension(200, 400);
    private static final JButton[] TF_BUTTONS = {new JButton("True"),
                                                new JButton("False")};
    private final JLabel myQuestionLabel;
    private final JPanel myAnswerPanel;
    private final JButton[] myMCButtons;
    private final JTextField myTextField;
    private final JLabel myTextInfo;
    private final PropertyChangeSupport myPCS;

    public QAPanel() {
        myQuestionLabel = new JLabel();
        myAnswerPanel = new JPanel();
        myMCButtons = new JButton[4];
        myTextField = new JTextField();
        myTextInfo = new JLabel("Press Enter to submit answer.");
        myPCS = new PropertyChangeSupport(this);
        setUp();
    }

    private void setUp() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(DIMENSION);
        setUpButtons();
        setUpTextField();
        this.add(myQuestionLabel);
        this.add(myAnswerPanel);
    }

    /**
     * Sets up text field.
     */
    private void setUpTextField() {
        myTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theE) {
                if (theE.getSource() instanceof JTextField) {
                    JTextField field = (JTextField) theE.getSource();
                    System.out.println("Answered: " + field.getText());
                    setValue(field.getText());
                }
            }
        });
    }

    /**
     * Sets up buttons.
     */
    private void setUpButtons() {
        final ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theE) {
                if (theE.getSource() instanceof JButton) {
                    JButton button = (JButton) theE.getSource();
                    System.out.println("Answered: " + button.getText());
                    setValue(button.getText());
                }
            }
        };
        for (JButton button : TF_BUTTONS) {
            button.setFont(Font.getFont("Tomorrow Regular"));
            button.addActionListener(buttonListener);
        }
        for (JButton button : myMCButtons) {
            button.setFont(Font.getFont("Tomorrow Regular"));
            button.addActionListener(buttonListener);
        }
    }

    /**
     * Sets colors.
     * @param thePurple Text color.
     * @param theDark Background color.
     */
    public void setColors(final Color thePurple, final Color theDark) {
        this.setBackground(theDark);
        for (JButton button : TF_BUTTONS) {
            button.setForeground(thePurple);
            button.setBackground(theDark);
        }
        for (JButton button : myMCButtons) {
            button.setForeground(thePurple);
            button.setBackground(theDark);
        }
        myTextInfo.setBackground(theDark);
        myTextInfo.setForeground(thePurple);
        myQuestionLabel.setBackground(theDark);
        myQuestionLabel.setForeground(thePurple);
    }

    /**
     * Adds PropertyChangeListener to the PropertyChangeSupport.
     * @param theL  the PropertyChangeListener to be added.
     *
     */
    public void addPropertyChangeListener(final PropertyChangeListener theL) {
        myPCS.addPropertyChangeListener(theL);
    }

    /**
     * Sends answer to listener.
     * @param theValue String.
     */
    private void setValue(final String theValue) {
        myPCS.firePropertyChange("Answer", "No answer", theValue);
    }

    private void setQuestion(final int theType, final String theQuestion,
                             final String[] theAnswers) {
        final String html = "<html><body style='width: %1spx'>%1s";
        myQuestionLabel.setText(String.format(theQuestion, 200, html));
        if (theType == 1) {

        } else if (theType == 2) {

        } else if (theType == 3) {

        }
    }
}
