package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask <Void, Void, String>{

    private WeakReference<TextView> mTextView;

    SimpleAsyncTask(TextView tv){
        mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {

        Random r = new Random();
        int n = 1 + r.nextInt(10);
        n*=200;

        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return "Awake at last after sleeping " + n + " milliseconds";
    }

    @Override
    protected void onPostExecute(String s) {
        mTextView.get().setText(s);
    }
}
