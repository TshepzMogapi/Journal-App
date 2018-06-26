package com.example.android.journalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    //todo Remove below line
    private static final int NUM_LIST_ITEMS = 100;

    private EntryAdapter mAdapter;

    private RecyclerView mEntryList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mEntryList = (RecyclerView) findViewById(R.id.rv_diary_entries);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mEntryList.setLayoutManager(layoutManager);

        mEntryList.setHasFixedSize(true);

        mAdapter = new EntryAdapter(NUM_LIST_ITEMS);

        mEntryList.setAdapter(mAdapter);
    }
}
