package com.example.android.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.journalapp.database.AppDatabase;
import com.example.android.journalapp.database.DiaryEntry;

import java.util.Date;

/**
 * Created by tshepisomogapi on 2018/06/28.
 */

public class AddEntryActivity extends AppCompatActivity{

    public static final String EXTRA_ENTRY_ID = "extraTaskId";

    private static final int DEFAULT_ENTRY_ID = -1;

    EditText mEditText;
    Button mButton;

    private int mEntryId = DEFAULT_ENTRY_ID;

    AppDatabase appDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        initViews();

        appDatabase = AppDatabase.getInstance(getApplicationContext());


        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(EXTRA_ENTRY_ID)) {
            mButton.setText("Modify");
            if (mEntryId == DEFAULT_ENTRY_ID) {
                // populate the UI
            }
        }


    }

    private void initViews() {
        mEditText = findViewById(R.id.editTextEntryDescription);

        mButton = findViewById(R.id.saveButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });
    }

    public void onSaveButtonClicked() {


        String description = mEditText.getText().toString();

        Date date = new Date();

        DiaryEntry diaryEntry = new DiaryEntry(description,date);

        appDatabase.entryDao().insertEntry(diaryEntry);

        finish();
    }
}
