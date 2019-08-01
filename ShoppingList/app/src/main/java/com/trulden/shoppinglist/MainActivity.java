package com.trulden.shoppinglist;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout mMainLayout;
    private final static int ITEM_RESULT = 1;
    static ArrayList<String> items = new ArrayList<>();

    private static final String LOG_TAG = "ShoppingList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainLayout = findViewById(R.id.main_layout);
    }

    public void addItem(View view) {
        Intent intent = new Intent(this, ItemsActivity.class);
        startActivityForResult(intent, ITEM_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == ITEM_RESULT){

                String item = data.getStringExtra(ItemsActivity.EXTRA_ITEM);
                items.add(item);

                addTextView(item);
            }
        }
    }

    private void addTextView(String item) {
        TextView textView = new TextView(this);
        textView.setText(item);
        mMainLayout.addView(textView);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null){
            for(String item : items){
                addTextView(item);
            }
        }
    }

    public void findShop(View view) {
        String address = ((EditText)findViewById(R.id.editText_shop)).getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + address);

        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if(intent.resolveActivity(getPackageManager()) != null ){
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "Can't handle location!");
        }
    }
}
