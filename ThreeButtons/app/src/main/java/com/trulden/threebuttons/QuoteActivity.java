package com.trulden.threebuttons;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class QuoteActivity extends AppCompatActivity {

    TextView mQuoteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        mQuoteText = findViewById(R.id.textView_quote);

        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_QUOTE);

        mQuoteText.setText(text);
    }
}
