package android.unipu.geoquiz;

import android.content.Intent;
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
    private Button mResultButton;
    private TextView mQuestionTextView;

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

    private static final String TAG = "Aktivnost MainActivity";
    private static final String KEY_INDEX="index_pitanja_kviza";
    private static final String EXTRA_KEY="extra_ključ_za_dohvat";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate (Bundle) pozvan");
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);


        mIncorrect=(TextView) findViewById(R.id.no_of_incorrect);

        mTrueButton = (Button) findViewById(R.id.true_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(true);

            }


        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(false);

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
        mResultButton=(Button) findViewById(R.id.result_button);
        mResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ResultActivity.class);
                i.putExtra(EXTRA_KEY,numCorrectAnswer);
                startActivity(i);
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
            numCorrectAnswer=numCorrectAnswer+1;

        } else {
            messageResId = R.string.incorrect_toast;
            numIncorrectAnswer=numIncorrectAnswer+1;
            mIncorrect.setText("Number of incorrect questions:"+numIncorrectAnswer);
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSavedInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }

    @Override
    protected void onResume(){
        super.onResume();
        numCorrectAnswer=getIntent().getIntExtra(EXTRA_KEY,0);
        Log.i("Correct","Correct answers:"+numCorrectAnswer);
    }


}





