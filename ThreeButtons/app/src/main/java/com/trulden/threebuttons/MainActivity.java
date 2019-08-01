package com.trulden.threebuttons;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_QUOTE = "EXTRA_QUOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked(View view) {
        Intent intent = new Intent(this, QuoteActivity.class);
        String message = "null";
        switch(view.getId()){
            case R.id.button_dickens:
                message = getResources().getString(R.string.dickens_quote);
                break;
            case R.id.button_poe:
                message = getResources().getString(R.string.poe_quote);
                break;
            case R.id.button_rand:
                message = getResources().getString(R.string.rand_quote);
                break;
        }
        intent.putExtra(EXTRA_QUOTE, message);
        startActivity(intent);
    }
}
