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

    public static final String INSTANCE_ENTRY_ID = "instanceEntryId";


    private static final int DEFAULT_ENTRY_ID = -1;

    EditText mEditText;
    Button mButton;

    private int mEntryId = DEFAULT_ENTRY_ID;

    AppDatabase mAppDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        initViews();

        mAppDatabase = AppDatabase.getInstance(getApplicationContext());


        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_ENTRY_ID)) {
            mEntryId = savedInstanceState.getInt(INSTANCE_ENTRY_ID, DEFAULT_ENTRY_ID);
        }

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(EXTRA_ENTRY_ID)) {
            mButton.setText("Modify");

            if (mEntryId == DEFAULT_ENTRY_ID) {

                mEntryId = intent.getIntExtra(EXTRA_ENTRY_ID, DEFAULT_ENTRY_ID);

                AppExecutors.getInstance().diskIO().execute(new  Runnable() {
                    @Override
                    public void run() {

                        final DiaryEntry diaryEntry = mAppDatabase.entryDao().loadEntryById(mEntryId);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                populateUI(diaryEntry);
                            }
                        });
                    }
                });
            }
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_ENTRY_ID, mEntryId);
        super.onSaveInstanceState(outState);
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

    private void populateUI(DiaryEntry diaryEntry) {

        if (diaryEntry == null) {
            return;
        }

        mEditText.setText(diaryEntry.getDescription());

    }

    public void onSaveButtonClicked() {


        String description = mEditText.getText().toString();

        Date date = new Date();

        final DiaryEntry diaryEntry = new DiaryEntry(description, date);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                if (mEntryId == DEFAULT_ENTRY_ID) {
                    // insert new task
                    mAppDatabase.entryDao().insertEntry(diaryEntry);
                } else {
                    //update task
                    diaryEntry.setId(mEntryId);
                    mAppDatabase.entryDao().updateEntry(diaryEntry);
                }

                finish();
            }
        });
    }
}
