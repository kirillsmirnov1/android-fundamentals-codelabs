package com.trulden.scorekeeper;

import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private int mScore1;
    private int mScore2;

    private TextView mScoreText1;
    private TextView mScoreText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScoreText1 = findViewById(R.id.score_1);
        mScoreText2 = findViewById(R.id.score_2);

        mScore1 = Integer.parseInt(mScoreText1.getText().toString());
        mScore2 = Integer.parseInt(mScoreText2.getText().toString());
    }

    public void decreaseScore(View view) {
        switch(view.getId()){
            case R.id.decreaseTeam1:
                mScore1--;
                mScoreText1.setText("" + mScore1);
                break;
            case R.id.decreaseTeam2:
                mScore2--;
                mScoreText2.setText("" + mScore2);
                break;
        }
    }

    public void increaseScore(View view) {
        switch(view.getId()){
            case R.id.increaseTeam1:
                mScore1++;
                mScoreText1.setText("" + mScore1);
                break;
            case R.id.increaseTeam2:
                mScore2++;
                mScoreText2.setText("" + mScore2);
                break;
        }
    }
}
