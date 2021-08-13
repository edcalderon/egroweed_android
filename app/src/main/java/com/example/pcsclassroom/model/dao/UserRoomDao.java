package com.example.pcsclassroom.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pcsclassroom.model.pojo.User;

import java.util.List;

@Dao
public interface UserRoomDao {
    @Query("SELECT * FROM users")
    List<User>  getAll();

    @Query("SELECT * FROM users LIMIT 1")
    User getUser();

    @Query("SELECT * FROM users WHERE avatar = :avatarQuery")
    List<User> getUserByAvatar(Integer avatarQuery);

    @Query("SELECT * FROM users WHERE name = :nameQuery")
    User getUserByName(String nameQuery);

    @Insert
    void insertAl(User ... users );
    @Insert
    void insertOne(User user);

    @Update
    void updateList(List<User> users);
    @Update
    void updateOne(User user);

    @Delete
    void deleteAll(User ... user);

    @Delete
    void deleteOne(User user);

}
