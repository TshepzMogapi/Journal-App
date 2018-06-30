package com.example.android.journalapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by tshepisomogapi on 2018/06/29.
 */

@Dao
public interface EntryDao {


    @Query("SELECT * FROM entry WHERE id = :id")
    DiaryEntry loadEntryById(int id);

    @Query("SELECT * FROM entry ORDER BY updated_at")
    List<DiaryEntry> loadAllEntries();

    @Insert
    void insertEntry(DiaryEntry diaryEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateEntry(DiaryEntry diaryEntry);

}
