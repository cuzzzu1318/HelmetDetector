package com.example.helmet;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Privacy.class}, version = 1)
public abstract class Appdatabase extends RoomDatabase {
    public abstract PrivacyDao privacyDao();
}
