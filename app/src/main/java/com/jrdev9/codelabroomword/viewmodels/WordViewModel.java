package com.jrdev9.codelabroomword.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.jrdev9.codelabroomword.models.Word;
import com.jrdev9.codelabroomword.repositories.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;
    private LiveData<List<Word>> liveDataAllWords;

    public WordViewModel(Application application) {
        super(application);
        wordRepository = new WordRepository(application);
        liveDataAllWords = wordRepository.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return liveDataAllWords;
    }

    public void insert(Word word) {
        wordRepository.insert(word);
    }
}
