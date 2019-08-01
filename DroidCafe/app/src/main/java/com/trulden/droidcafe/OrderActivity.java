package com.trulden.droidcafe;

import android.os.PersistableBundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class OrderActivity extends AppCompatActivity {

    private TextView mOrderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        mOrderText = findViewById(R.id.order_textView);

        if(savedInstanceState != null){
            mOrderText.setText(savedInstanceState.getString("order"));
        }

        mOrderText.append(getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE) + "\n");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("order", mOrderText.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("DESTROY", "DESTROY");
    }
}
