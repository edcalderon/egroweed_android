package com.example.pcsclassroom.model.pojo;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    @NonNull
    private String email;
    private String name;
    private String password;
    private Integer avatar;
    private String roll;

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


