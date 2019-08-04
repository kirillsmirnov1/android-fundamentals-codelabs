package com.trulden.scorekeeper;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        
        // Change the label of the menu based on the state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.night_mode){
            // Get the night mode state of the app.
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }
            // Recreate the activity for the theme change to take effect.
            recreate();
        }
        return true;
    }
}
