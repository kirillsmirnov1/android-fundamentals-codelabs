package com.trulden.recipies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class RecipeListAdapter
        extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private final LinkedList<String> mTitlesList;
    private final LinkedList<String> mAnnotationsList;
    private LayoutInflater mInflater;

    public RecipeListAdapter(Context context, LinkedList<String> titles,
                             LinkedList<String> annotations) {
        mInflater = LayoutInflater.from(context);
        mTitlesList = titles;
        mAnnotationsList = annotations;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.titleItemView.setText(mTitlesList.get(position));
        holder.annotationItemView.setText(mAnnotationsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTitlesList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleItemView;
        public final TextView annotationItemView;
        final RecipeListAdapter mAdapter;

        public RecipeViewHolder(View view, RecipeListAdapter recipeListAdapter) {
            super(view);
            mAdapter = recipeListAdapter;
            titleItemView = view.findViewById(R.id.recipe_title);
            annotationItemView = view.findViewById(R.id.recepie_annotation);
        }
    }
}
