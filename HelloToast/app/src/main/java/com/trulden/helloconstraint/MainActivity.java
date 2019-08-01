package com.trulden.helloconstraint;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;
    private Button  mZeroButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
        mZeroButton  = findViewById(R.id.button_zero);
    }

    public void showToast(View toastButton) {
        Toast toast = Toast.makeText(this, R.string.toast_message,
                                                        Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View countUpButton) {
        mCount++;

        updateCountText();

        if(mCount % 2 == 0)
            countUpButton.setBackgroundColor(Color.argb(255, 100, 50, 0));
        else
            countUpButton.setBackgroundColor(Color.argb(255, 50, 100, 0));

        mZeroButton.setBackgroundColor(Color.argb(255, 20, 200, 30));
    }

    private void updateCountText() {
        if(mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
    }

    public void setZero(View zeroButton) {
        mCount = 0;
        updateCountText();
        zeroButton.setBackgroundColor(getResources().getColor(R.color.darkGrey));
    }
}
