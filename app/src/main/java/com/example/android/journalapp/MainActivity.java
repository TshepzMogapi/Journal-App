package com.example.android.journalapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.android.journalapp.database.AppDatabase;
import com.example.android.journalapp.database.DiaryEntry;

import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;


public class MainActivity extends AppCompatActivity implements EntryAdapter.ListItemClickListener{

    private EntryAdapter mAdapter;


    private AppDatabase mAppDatabase;

    private RecyclerView mEntryList;


    private static final String TAG = AddEntryActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mEntryList = (RecyclerView) findViewById(R.id.rv_diary_entries);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mEntryList.setLayoutManager(layoutManager);

        mEntryList.setHasFixedSize(true);

        mAdapter = new EntryAdapter(this, this);

        mEntryList.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        mEntryList.addItemDecoration(decoration);



        FloatingActionButton fabButton = findViewById(R.id.fab);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addEntryIntent = new Intent(MainActivity.this, AddEntryActivity.class);
                startActivity(addEntryIntent);
            }
        });

        mAppDatabase = AppDatabase.getInstance(getApplicationContext());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {

            case R.id.action_refresh:
                mAdapter = new EntryAdapter(this, this);
                mEntryList.setAdapter(mAdapter);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();

        retrieveEntries();

    }

    private void retrieveEntries() {

        Log.d(TAG, "Calle d Called");

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                final List<DiaryEntry> entries = mAppDatabase.entryDao().loadAllEntries();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setEntries(entries);
                    }
                });
            }
        });
    }

    @Override
    public void onListItemClick(int clickedItemId) {

        Intent intent = new Intent(MainActivity.this, AddEntryActivity.class);
        intent.putExtra(AddEntryActivity.EXTRA_ENTRY_ID, clickedItemId);
        startActivity(intent);
    }

}
