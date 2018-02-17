package com.jrdev9.codelabroomword.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.jrdev9.codelabroomword.daos.WordDao;
import com.jrdev9.codelabroomword.models.Word;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = buildDatabase(context);
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    private static WordRoomDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database").build();
    }

    public abstract WordDao wordDao();
}
