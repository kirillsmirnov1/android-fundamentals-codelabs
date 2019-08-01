package com.trulden.shoppinglist;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ItemsActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM = "EXTRA_ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
    }

    public void clickItem(View view) {
        String item = null;
        switch (view.getId()){
            case R.id.apples:
                item = getResources().getString(R.string.apples);
                break;
            case R.id.bananas:
                item = getResources().getString(R.string.bananas);
                break;
            case R.id.cheese:
                item = getResources().getString(R.string.cheese);
                break;
            case R.id.milk:
                item = getResources().getString(R.string.milk);
                break;
            case R.id.oats:
                item = getResources().getString(R.string.oats);
                break;
            case R.id.oranges:
                item = getResources().getString(R.string.oranges);
                break;
            case R.id.passionfruit:
                item = getResources().getString(R.string.passionfruit);
                break;
            case R.id.strawberry:
                item = getResources().getString(R.string.strawberry);
                break;
            case R.id.tomatoes:
                item = getResources().getString(R.string.tomatoes);
                break;
            case R.id.yogurt:
                item = getResources().getString(R.string.yogurt);
                break;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_ITEM, item);
        setResult(RESULT_OK, intent);
        finish();
    }
}
