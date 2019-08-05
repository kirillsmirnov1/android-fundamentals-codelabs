package com.trulden.battery;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ImageView mBatteryView;
    private int mBatteryLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBatteryView = findViewById(R.id.battery_view);
        mBatteryView.setImageLevel(mBatteryLevel);
    }

    public void plusPressed(View view) {
        if(mBatteryLevel < 6) {
            mBatteryLevel++;
            mBatteryView.setImageLevel(mBatteryLevel);
        } else {
            Toast.makeText(getApplicationContext(), "Maximum", Toast.LENGTH_SHORT).show();
        }
    }

    public void minusPressed(View view) {
        if(mBatteryLevel > 0) {
            mBatteryLevel--;
            mBatteryView.setImageLevel(mBatteryLevel);
        } else {
            Toast.makeText(getApplicationContext(), "Minimum", Toast.LENGTH_SHORT).show();
        }
    }
}
