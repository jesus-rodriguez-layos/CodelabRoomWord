package com.jrdev9.codelabroomword.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.jrdev9.codelabroomword.R;
import com.jrdev9.codelabroomword.adapters.WordListAdapter;
import com.jrdev9.codelabroomword.models.Word;
import com.jrdev9.codelabroomword.viewmodels.WordViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private WordViewModel wordViewModel;
    private WordListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
    }

    private void setup() {
        initToolbar();
        initRecyclerView();
        initFloatingAction();
        initWordViewModel();
    }

    private void initFloatingAction() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(onFloatingActionClickListener());
    }

    private View.OnClickListener onFloatingActionClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewWordActivity();
            }
        };
    }

    private void openNewWordActivity() {
        Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
        startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
    }


    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initWordViewModel() {
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        wordViewModel.getAllWords().observe(this, getWordsObserver());
    }

    private Observer<List<Word>> getWordsObserver() {
        return new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                adapter.setWords(words);
            }
        };
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (isRequestNewWordAndOk(requestCode, resultCode)) {
            insertNewWord(data);
        } else {
            showMsgWordNotSaved();
        }
    }

    private boolean isRequestNewWordAndOk(int requestCode, int resultCode) {
        return requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK;
    }

    private void showMsgWordNotSaved() {
        Toast.makeText(
                getApplicationContext(),
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show();
    }

    private void insertNewWord(Intent data) {
        Word word = new Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY));
        wordViewModel.insert(word);
    }
}
