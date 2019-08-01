package com.trulden.sendkey;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText);
        if (editText != null)
            editText.setOnEditorActionListener
                    (new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                            boolean handled = false;

                            if (actionId == EditorInfo.IME_ACTION_SEND){
                                dialNumber();
                                handled = true;
                            }

                            return handled;
                        }
                        // If view is found, set the listener for editText.
                    });
    }

    private void dialNumber() {
        EditText editText = findViewById(R.id.editText);
        String phoneNum = null;

        if(editText != null) phoneNum = "tel:" + editText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_DIAL);

        intent.setData(Uri.parse(phoneNum));

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Log.d("SendKey", "Can't handle this: " + phoneNum);
        }
    }


}
