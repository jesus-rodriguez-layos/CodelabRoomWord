package com.jrdev9.codelabroomword.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jrdev9.codelabroomword.R;
import com.jrdev9.codelabroomword.models.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater inflater;
    private List<Word> liveDataAllWords; // Cached copy of words

    public WordListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Word current = liveDataAllWords.get(position);
        holder.wordItemView.setText(current.getWord());
    }

    void setWords(List<Word> words) {
        liveDataAllWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // liveDataAllWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        return liveDataAllWords != null ? liveDataAllWords.size() : 0;
    }
}
