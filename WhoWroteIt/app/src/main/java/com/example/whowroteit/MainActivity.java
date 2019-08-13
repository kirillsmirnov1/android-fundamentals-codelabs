package com.example.whowroteit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = (EditText)findViewById(R.id.bookInput);
        mTitleText = (TextView)findViewById(R.id.titleText);
        mAuthorText = (TextView)findViewById(R.id.authorText);

    }

    public void searchBooks(View view) {
        String queryString = mBookInput.getText().toString();

        // Hide the keyboard
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if(inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        // Check connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
            getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if(connMgr != null){
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if(networkInfo != null && networkInfo.isConnected()
            && queryString.length() != 0) {

            // Start query
            new FetchBook(mTitleText, mAuthorText).execute(queryString);

            // Set loading text, while query is working
            mAuthorText.setText("");
            mTitleText.setText(R.string.loading);
        } else {
            if(queryString.length() == 0) {
                mTitleText.setText(getResources().getString(R.string.no_search_term));
            } else {
                mTitleText.setText(getResources().getString(R.string.no_network));
            }
            mAuthorText.setText("");
        }
    }
}
