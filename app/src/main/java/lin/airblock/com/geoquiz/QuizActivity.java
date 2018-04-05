package lin.airblock.com.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mPrevButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, false)
    };
    private int mCurrentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setTitle(R.string.app_name);

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this
                        ,mQuestionBank[mCurrentIndex].ismAnswerTrue()?R.string.correct_toast:R.string.false_toast
                        ,Toast.LENGTH_SHORT).show();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(false);
                Toast toast = Toast.makeText(QuizActivity.this
                ,!mQuestionBank[mCurrentIndex].ismAnswerTrue()?R.string.correct_toast:R.string.false_toast
                ,Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,0,0);
                toast.show();

            }
        });

        mQuestionTextView = findViewById(R.id.question_text_view);
        mPrevButton = findViewById(R.id.previous_button);
        mNextButton = findViewById(R.id.next_button);

        mPrevButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (mCurrentIndex<0) {
                    mCurrentIndex = mQuestionBank.length-1;
                } else {
                    mCurrentIndex = (mCurrentIndex-1+mQuestionBank.length)%mQuestionBank.length;
                }
                Question question = mQuestionBank[mCurrentIndex];
                mQuestionTextView.setText(question.getmTextResId());

                mPrevButton.setText("question "+ prevIndex());
                mNextButton.setText("question "+ nextIndex());



            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (mCurrentIndex<0) {
                    mCurrentIndex = 0;
                } else {
                    mCurrentIndex = (mCurrentIndex+1+mQuestionBank.length)%mQuestionBank.length;
                }
                Question question = mQuestionBank[mCurrentIndex];
                mQuestionTextView.setText(question.getmTextResId());

                mPrevButton.setText("question "+ prevIndex());
                mNextButton.setText("question "+ nextIndex());


            }
        });



    }

    int prevIndex(){
        return (mCurrentIndex-1+mQuestionBank.length)%mQuestionBank.length+1;
    }
    int nextIndex(){
        return (mCurrentIndex+1+mQuestionBank.length)%mQuestionBank.length+1;
    }


}
