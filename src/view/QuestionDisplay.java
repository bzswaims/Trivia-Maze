//TODO: need to make short answer work

package view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import model.AbstractQuestion;
import model.MultiQuestion;
import model.ShortQuestion;
import model.TrueFalseQuestion;
import java.util.Random;

public class QuestionDisplay extends JPanel {
    //Default state should just be a black screen. after going thro the door it should go back to a black screen

    /**
     * Size of panel in pixels.
     */
    public static final int SIZE_OF_PANEL = 200;

    /**
     * The question to display.
     */
    private AbstractQuestion myQuestion;

    /**
     * The text for the question.
     */
    private final JLabel myQuestionText;

    /**
     * Single JTextField to take in the short answer.
     */
    private JTextField myShortAnswer;

    /**
     * List for answers for multi questions.
     */
    private List<String> myMultiAnswers;

    /**
     * List of listeners to use for the buttons.
     */
    private ActionListener[] myListeners;

    /**
     * random number generator.
     */
    private final Random myRand;

    /**
     * list to store the buttons with the answers on them.
     */
    private final List<JButton> myAnswerButtons;

    /**
     * Value to return if correct answer is selected.
     */
    private boolean myIsCorrect;

    /**
     * String array to store the answers for the multi questions.
     */
    private String[] myAnswers;

    /**
     * JPanel to store the answers in.
     */
    private final JPanel myAnswerBlock;

    /**
     * Default constructor.
     */
    public QuestionDisplay() {
        setLayout(new GridBagLayout());

        myQuestion = null;
        myQuestionText = new JLabel(" ");
        myShortAnswer = new JTextField(20);
        myMultiAnswers = new ArrayList<>();
        myListeners = null;
        myRand = new Random();
        myAnswerButtons = new ArrayList<>();
        myIsCorrect = false;
        myAnswers = null;
        myAnswerBlock = new JPanel();



        //TODO: need to make the screen blank or something by default.
    }

    /**
     * Mutator for myQuestion. When adding in a question, sets the question text and answer texts.
     *
     * @param theQuestion the Question we pull the text from.
     */
    public void setQuestion(AbstractQuestion theQuestion) {
        myQuestion = theQuestion;
        myQuestionText.setText(myQuestion.getQuestion());

        if(myQuestion instanceof MultiQuestion) {
            myMultiAnswers = ((MultiQuestion) myQuestion).getIncorrectAnswers();
            myMultiAnswers.add(myQuestion.getCorrectAnswer());

            int size = myMultiAnswers.size();
            int option1 = myRand.nextInt(size);

            int option2 = myRand.nextInt(size);
            while(option2 == option1){
                if(option2 < 3) {
                    option2++;
                }
                else{
                    option2 = 0;
                }
            }

            int option3 = myRand.nextInt(size);
            while(option3 == option1 || option3 == option2){
                if(option3 < 3) {
                    option3++;
                }
                else{
                    option3 = 0;
                }
            }

            int option4 = myRand.nextInt(size);
            while(option4 == option1 || option4 == option2 || option4 == option3){
                if(option4 < 3) {
                    option4++;
                }
                else{
                    option4 = 0;
                }
            }

            myAnswers = new String[myMultiAnswers.size()];
            myAnswers[option1] = myMultiAnswers.getFirst();
            myAnswers[option2] = myMultiAnswers.get(1);
            myAnswers[option3] = myMultiAnswers.get(2);
            myAnswers[option4] = myMultiAnswers.get(3);

            myListeners = createMultiListeners();
            createButtons(myAnswers);

        } else if (myQuestion instanceof TrueFalseQuestion) {
            myListeners = createTrueFalseListeners();

            String[] temp = {"True", "False"};
            createButtons(temp);
        } else if (myQuestion instanceof ShortQuestion) {
            //TODO need to work on shortquestion part.
        }

        setUpAnswerBlock();
        createScreen();
    }

    /**
     * Creates the screen proper with question to the north and answers to the bottom.
     */
    private void createScreen() {
        add(myQuestionText, BorderLayout.NORTH);
        add(myAnswerBlock, BorderLayout.SOUTH);

        //TODO: need to update question screen to display.
    }

    /**
     * Populates the menu bar.
     */
    private void setUpAnswerBlock() {
        for (int i = 0; i < myAnswerButtons.size(); i++) {
            myAnswerBlock.add(myAnswerButtons.get(i));
        }
    }

    /**
     * returns if the correct answer was selected.
     *
     * @return True if the answer is correct, false if the answer is incorrect.
     */
    public boolean isCorrect() {
        return myIsCorrect;
        //TODO: need to update screen to go back to blank
    }

    /**
     * Creates the buttons proper.
     *
     * @param theText The text attached to the button.
     *
     * @return The button proper.
     */
    public JButton buttonMaker(String theText) {
        final JButton button = new JButton(theText);
        button.setEnabled(true);

        return button;
    }

    /**
     * This creates an array of action listeners.
     *
     * @return an array of action listeners for my buttons
     */
    private ActionListener[] createMultiListeners() {
        return new ActionListener[] {theEvent -> {
            //Option 1
            myIsCorrect = myAnswers[0].equals(myQuestion.getCorrectAnswer());
            //compare the array at point 1 to correct answer.
        }, theEvent -> {
            //Option 2
            myIsCorrect = myAnswers[1].equals(myQuestion.getCorrectAnswer());
        }, theEvent -> {
            //Option 3
            myIsCorrect = myAnswers[2].equals(myQuestion.getCorrectAnswer());
        }, theEvent -> {
            //Option 4
            myIsCorrect = myAnswers[3].equals(myQuestion.getCorrectAnswer());
        }, theEvent -> {
            //Cheat, always correct.
            //This will always be correct, and will be deleted in the final cut.
            myIsCorrect = true;
        }};
    }

    private ActionListener[] createTrueFalseListeners() {
        return new ActionListener[] {theEvent -> {
            //Option True
            myIsCorrect = myQuestion.getCorrectAnswer().equals("True");
        }, theEvent -> {
            //Option False
            myIsCorrect = myQuestion.getCorrectAnswer().equals("False");
        }, theEvent -> {
            //Cheat, always correct.
            myIsCorrect = true;
        }};
    }

    /**
     * Methods that compiles the buttons into the panel.
     */
    private void createButtons(final String[] theNames) {
        for (int i = 0; i < theNames.length; i++) {
            JButton button = buttonMaker(theNames[i]); //need to add in correct answer string.
            button.addActionListener(myListeners[i]);
            myAnswerButtons.add(button);
        }
    }
}