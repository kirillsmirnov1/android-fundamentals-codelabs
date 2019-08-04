package com.trulden.recipies;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class FullRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_recipe);

        Intent intent = getIntent();

        int mPos = intent.getIntExtra(RecipeListAdapter.EXTRA_RECIPE, 0);

        // Title
        this.setTitle(getResources().getStringArray(R.array.recipe_titles)[mPos]);

        // Image
        int imgId = getResources().obtainTypedArray(R.array.recipe_images).getResourceId(mPos, -1);
        ((ImageView)findViewById(R.id.recipe_image)).setImageResource(imgId);
//        Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.orange_ice_cream);
//        ((ImageView)findViewById(R.id.recipe_image)).setImageBitmap(bImage);
        // Text
        ((TextView)findViewById(R.id.full_recipe_text))
                .setText(getResources().getStringArray(R.array.full_recipies)[mPos]);
    }
}
