package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class QAPanel extends JPanel {
    private static final Dimension DIMENSION = new Dimension(200, 400);
    private static final JButton[] TF_BUTTONS = {new JButton("True"),
                                                new JButton("False")};
    private final JTextPane myQuestionText;
    private final JPanel myAnswerPanel;
    private final JButton[] myMCButtons;
    private final JTextField myTextField;
    private final JLabel myTextInfo;
    private final PropertyChangeSupport myPCS;

    public QAPanel() {
        myQuestionText = new JTextPane();
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
        this.add(myQuestionText);
        this.add(myAnswerPanel);
        myQuestionText.setEditable(false);
        this.setVisible(true);
    }

    /**
     * Sets up text field.
     */
    private void setUpTextField() {
        // Use Key Bindings to bind the 'A' key press to an action
        InputMap inputMap = myTextField.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = myTextField.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter_key_pressed");
        actionMap.put("enter_key_pressed", new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent theE) {
                setValue(myTextField.getText());
                myTextField.setText("");
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
        for (int i = 0; i < myMCButtons.length; i++) {
            myMCButtons[i] = new JButton();
            myMCButtons[i].setFont(Font.getFont("Tomorrow Regular"));
            myMCButtons[i].addActionListener(buttonListener);
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
        myQuestionText.setBackground(theDark);
        myQuestionText.setForeground(thePurple);
        myAnswerPanel.setBackground(theDark);
        myAnswerPanel.setForeground(thePurple);
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

    public void setQuestion(final int theType, final String theQuestion,
                             final String[] theAnswers) {
        final String html = "<html><body style='width: %1spx'>%1s";
        myQuestionText.setText(String.format(theQuestion, 200, html));
        if (theType == 1) {
            myAnswerPanel.setLayout(new GridLayout(2, 2));
            for (int i = 0; i < myMCButtons.length; i++) {
                myAnswerPanel.add(myMCButtons[i]);
                myMCButtons[i].setEnabled(true);
                myMCButtons[i].setText(theAnswers[i]);
            }
        } else if (theType == 2) {
            myAnswerPanel.setLayout(new GridLayout(1,2));
            for (JButton button : TF_BUTTONS) {
                myAnswerPanel.add(button);
                button.setEnabled(true);
            }
        } else if (theType == 3) {
            myAnswerPanel.setLayout(new GridLayout(2,1));
            myAnswerPanel.add(myTextField);
            myAnswerPanel.add(myTextInfo);
        }
    }

    public void clearQuestion() {
        myQuestionText.setText("");
        myAnswerPanel.removeAll();
        myAnswerPanel.repaint();
        myAnswerPanel.revalidate();
    }
}
