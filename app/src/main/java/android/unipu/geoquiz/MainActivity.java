package android.unipu.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Josip Bošnjak
 *
 * Ova aplikacija dohvaća preko gumba referencu koja
 * kaže dali je odgovor na postavljano pitanje točno ili netočno
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private TextView mQuestionTextView;
    private TextView mCorrect;
    private TextView mIncorrect;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_0, false),
            new Question(R.string.question_1, true),
            new Question(R.string.question_2, false),
            new Question(R.string.question_3, true),
            new Question(R.string.question_4, false),
            new Question(R.string.question_5, true)
    };
    private int mCurrentIndex = 0;
    public Integer numCorrectAnswer = 0;
    public Integer numIncorrectAnswer = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mCorrect=(TextView) findViewById(R.id.no_of_correct);
        mIncorrect=(TextView) findViewById(R.id.no_of_incorrect);

        mTrueButton = (Button) findViewById(R.id.true_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(true);
                numCorrectAnswer=numCorrectAnswer+1;
                mCorrect.setText("Number of correct questions:"+numCorrectAnswer);
            }


        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(false);
                numIncorrectAnswer=numIncorrectAnswer+1;
                mIncorrect.setText("Number of incorrect questions:"+numIncorrectAnswer);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();

            }
        });

        updateQuestion();

        mPreviousButton=(Button) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousQuestion();
            }
        });
    }

    private void previousQuestion() {
        mCurrentIndex=checkIndex(mCurrentIndex);
        System.out.println(mCurrentIndex);
        int question = mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);

           }

     private int checkIndex(int index){
        if(index<=0){
            index=mQuestionBank.length-1;
        }else {
            index=mCurrentIndex-1;
        }
        return index;
     }


    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);
    }

    ;

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].ismAnswerTrue();

        int messageResId = 0;
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    ;


}





