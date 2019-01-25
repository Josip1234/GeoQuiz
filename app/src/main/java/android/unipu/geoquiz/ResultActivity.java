package android.unipu.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView correct;
    private static final String EXTRA_KEY="extra_ključ_za_dohvat";
    private Integer numCorrectAnswer;
    private Button mReturnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        numCorrectAnswer=getIntent().getIntExtra(EXTRA_KEY,0);
        correct=(TextView) findViewById(R.id.numOfCorrect);
        correct.setText(String.valueOf(numCorrectAnswer));
        mReturnButton = (Button) findViewById(R.id.return_button);
        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(ResultActivity.this,MainActivity.class);
                b.putExtra(EXTRA_KEY,numCorrectAnswer);
                startActivity(b);
            }
        });

    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(EXTRA_KEY,numCorrectAnswer);
    }
}
