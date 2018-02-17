package com.jrdev9.codelabroomword.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jrdev9.codelabroomword.R;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "REPLY";

    private EditText edtWord;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        setup();
    }

    private void setup() {
        initEditWord();
        initButtonSave();
    }

    private void initEditWord() {
        edtWord = findViewById(R.id.edit_word);
    }

    private void initButtonSave() {
        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(getOnClickListenr());
    }

    private View.OnClickListener getOnClickListenr() {
        return new View.OnClickListener() {
            public void onClick(View view) {
                onSaveClicked();
            }
        };
    }

    private void onSaveClicked() {
        Intent replyIntent = new Intent();
        if (TextUtils.isEmpty(edtWord.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String word = edtWord.getText().toString();
            replyIntent.putExtra(EXTRA_REPLY, word);
            setResult(RESULT_OK, replyIntent);
        }
        finish();
    }
}
