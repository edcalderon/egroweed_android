package com.example.pcsclassroom.controller;

import com.example.pcsclassroom.model.LocalStorage;
import com.example.pcsclassroom.model.dao.UserRoomDao;
import com.example.pcsclassroom.model.pojo.User;
import com.example.pcsclassroom.view.Profile;


public class ProfileActivityController {
    private UserRoomDao userRoomDao;

    public void updateUser(Profile profile, String email, String name){
        this.userRoomDao = LocalStorage.getLocalStorage(profile.getApplicationContext()).userRoomDao();
        User user = this.userRoomDao.getUserByEmail(email);
        user.setName(name);
        this.userRoomDao.updateOne(user);
        profile.updateUserSucceed();
    }
}
