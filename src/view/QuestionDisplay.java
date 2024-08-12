package view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.AbstractQuestion;

public class QuestionDisplay extends JPanel {
    //I need to create a panel that shows question to the north and answers to the south
    //In the case of short answer it needs to be a text box
    //in the case of multi it needs to be 4 radio buttons
    //in the case of true false it needs to be 2 radio buttons

    //Default state should just be a black screen. after going thro the door it should go back to a black screen



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
    private JLabel[] myMultiAnswers;

    /**
     * Default constructor.
     */
    public QuestionDisplay() {
        setLayout(new GridBagLayout());

        myQuestion = null;
        myQuestionText = new JLabel(" ");
        myShortAnswer = new JTextField(20);
    }

    /**
     * Mutator for myQuestion. When adding in a question, sets the question text and answer texts.
     *
     * @param theQuestion the Question we pull the text from.
     */
    public void setQuestion(AbstractQuestion theQuestion) {
        myQuestion = theQuestion;
        myQuestionText.setText(myQuestion.getQuestion());
    }

    /**
     * Creates the screen proper with question to the north and answers to the bottom.
     */
    private void CreateScreen() {
        add(myQuestionText, BorderLayout.NORTH);
        //need to find a way to add the answers to the bottom.
        add(CreateAnswerBlock(), BorderLayout.SOUTH);
    }

    private JPanel CreateAnswerBlock() {
        JPanel temp = new JPanel();

        if(myQuestion.getType() == 1) {
            //need to create buttons here.
            //I have an array of 4 answers that could be right or wrong
            //I want each button to just have the text of the answer, and to be centered.
        }
        else if(myQuestion.getType() == 2) {
            //similar to the top, I want just two though, true or false.


        }
        else if(myQuestion.getType() == 3) {
            temp.add(myShortAnswer);
        }

        return temp;
    }

    /**
     * Returns a flag if the question is answered correctly or not.
     *
     * @return True if the answer is correct, false if the answer is incorrect.
     */
    private boolean isCorrect() {
        boolean isCorrect = false;

        if(myQuestion.getType() == 3) {
            //compare the input text to correct answer and set isCorrect to that.
        }

        return isCorrect;
    }
}
