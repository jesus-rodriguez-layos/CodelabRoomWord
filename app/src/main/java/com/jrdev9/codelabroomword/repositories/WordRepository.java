package com.jrdev9.codelabroomword.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.jrdev9.codelabroomword.daos.WordDao;
import com.jrdev9.codelabroomword.db.WordRoomDatabase;
import com.jrdev9.codelabroomword.models.Word;

import java.util.List;

public class WordRepository {

    private final WordDao wordDao;
    private final LiveData<List<Word>> liveDataAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        liveDataAllWords = wordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return wordDao.getAllWords();
    }

    public void insert(Word word) {
        new InsertAsyncTask(wordDao).execute(word);
    }

    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao asyncTaskDao;

        private InsertAsyncTask(WordDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
