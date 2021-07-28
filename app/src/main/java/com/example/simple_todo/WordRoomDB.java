package com.example.simple_todo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.simple_todo.modal.Word;

@Database(entities = {Word.class}, version = 2)
public abstract class WordRoomDB extends RoomDatabase {
    public abstract DaoWord daoWord();
    private static WordRoomDB INSTANCE;
    static WordRoomDB getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized (WordRoomDB.class){
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WordRoomDB.class,"worddb")
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}
