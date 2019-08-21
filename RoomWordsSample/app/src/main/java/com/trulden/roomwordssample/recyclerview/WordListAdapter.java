package com.trulden.roomwordssample.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.trulden.roomwordssample.MainActivity;
import com.trulden.roomwordssample.NewWordActivity;
import com.trulden.roomwordssample.R;
import com.trulden.roomwordssample.database.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    public static final String UPDATE_WORD_POSITION_EXTRA = "word_position";
    public static final String UPDATE_WORD_TEXT_EXTRA = "word_text";

    private final LayoutInflater mInflater;
    private List<Word> mWords; // Cached copy of words
    private Context mContext;

    public WordListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWords != null) {
            Word current = mWords.get(position);
            holder.wordItemView.setText(current.getWord());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    public void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    public Word getWordAtPosition (int position) {
        return mWords.get(position);
    }

    class WordViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            wordItemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, NewWordActivity.class);
            intent.putExtra(UPDATE_WORD_POSITION_EXTRA, getAdapterPosition());
            intent.putExtra(UPDATE_WORD_TEXT_EXTRA, mWords.get(getAdapterPosition()).getWord());
            ((Activity) mContext).startActivityForResult(intent, MainActivity.UPDATE_WORD_ACTIVITY_REQUEST_CODE);
        }
    }
}
