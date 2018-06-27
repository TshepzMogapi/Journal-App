package com.example.android.journalapp.database;

import java.util.Date;

/**
 * Created by tshepisomogapi on 2018/06/28.
 */

public class DiaryEntry {

    private int id;
    private String description;
    private Date updatedAt;


    public DiaryEntry(String description,  Date updatedAt) {
        this.description = description;
        this.updatedAt = updatedAt;
    }

    public DiaryEntry(int id, String description, Date updatedAt) {
        this.id = id;
        this.description = description;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
