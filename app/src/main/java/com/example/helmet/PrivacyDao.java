package com.example.helmet;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import kotlinx.coroutines.flow.Flow;

@Dao
public interface PrivacyDao {
    @Query("SELECT * FROM Privacy")
    List<Privacy> getAll();

    @Insert
    void insert(Privacy... privacies);

    @Update
    void update(Privacy privacy);

    @Delete
    void delete(List<Privacy> privacy);

}
