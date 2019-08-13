package com.example.whowroteit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
    implements LoaderManager.LoaderCallbacks<String> {

    public static final String QUERY_STRING = "queryString";

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

        if(getSupportLoaderManager().getLoader(0) != null){
            getSupportLoaderManager().initLoader(0, null, this);
        }
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
            Bundle queryBundle = new Bundle();
            queryBundle.putString(QUERY_STRING, queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);

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

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";

        if(args != null){
            queryString = args.getString(QUERY_STRING);
        }

        return new BookLoader(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try{

            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            int i = 0;
            String title = null;
            String authors = null;

            while (i < itemsArray.length() && (authors == null && title == null)){
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e){
                    e.printStackTrace();
                }

                ++i;
            }

            if(title != null && authors != null){
                mTitleText.setText(title);
                mAuthorText.setText(authors);
            } else {
                mTitleText.setText(R.string.no_results);
                mAuthorText.setText("");
            }

        } catch (Exception e){
            e.printStackTrace();

            mTitleText.setText(R.string.no_results);
            mAuthorText.setText("");
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
