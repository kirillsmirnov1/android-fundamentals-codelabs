package com.trulden.recipies;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> mTitlesList = new LinkedList<>();

    private final LinkedList<String> mAnnotationsList = new LinkedList<>();

    private RecyclerView mRecyclerView;
    private RecipeListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitlesList.addAll(Arrays.asList(getResources().getStringArray(R.array.recipe_titles)));
        mAnnotationsList.addAll(Arrays.asList(getResources().getStringArray(R.array.recipe_annotations)));

        mRecyclerView = findViewById(R.id.recepie_recyclerview);
        mAdapter = new RecipeListAdapter(this, mTitlesList, mAnnotationsList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
