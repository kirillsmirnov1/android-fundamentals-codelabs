package com.trulden.checkboxes;

import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToastButtonClick(View view) {
        String result = "";
        int counter = 0;

        if(((CheckBox)findViewById(R.id.syrup)).isChecked()){
            result += getString(R.string.chocolate_syrup) + " ";
            counter ++;
        }

        if(((CheckBox)findViewById(R.id.sprinkles)).isChecked()){
            result += getString(R.string.sprinkles) + " ";
            counter ++;
        }

        if(((CheckBox)findViewById(R.id.nuts)).isChecked()){
            result += getString(R.string.crushed_nuts) + " ";
            counter ++;
        }

        if(((CheckBox)findViewById(R.id.cherries)).isChecked()){
            result += getString(R.string.cherries) + " ";
            counter ++;
        }

        if(((CheckBox)findViewById(R.id.crumbles)).isChecked()){
            result += getString(R.string.orio_cookie_crumbles) + " ";
            counter ++;
        }

        if(counter == 0)
            showToast("Nothing chosen");

        if(counter == 1)
            showToast("Topping: " + result);

        if(counter > 1)
            showToast("Toppings: " + result);
    }

    private void showToast(String text){
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

    }
}
