package com.trulden.roomwordssample;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.WrapperListAdapter;

import com.trulden.roomwordssample.recyclerview.WordListAdapter;

public class NewWordActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY =
            "com.example.android.roomwordssample.REPLY";

    private EditText mEditWordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);

        final int wordPos = getIntent().getIntExtra(WordListAdapter.UPDATE_WORD_POSITION_EXTRA, -1);
        if(wordPos != -1){
            mEditWordView.setText(getIntent().getStringExtra(WordListAdapter.UPDATE_WORD_TEXT_EXTRA));
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mEditWordView.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    replyIntent.putExtra(WordListAdapter.UPDATE_WORD_POSITION_EXTRA, wordPos);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
