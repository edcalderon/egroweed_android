package com.example.pcsclassroom.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pcsclassroom.model.dao.UserRoomDao;
import com.example.pcsclassroom.model.pojo.User;

@Database(entities = {User.class}, version = 2)
public abstract class LocalStorage extends RoomDatabase {
    public abstract UserRoomDao userRoomDao();
    private static LocalStorage LocalStorage;
    public static LocalStorage getLocalStorage(final Context context){
        if(LocalStorage == null){
            LocalStorage = Room.databaseBuilder(context,
                    LocalStorage.class,
                    "DCP2021-01-db").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return LocalStorage;
    }
}
