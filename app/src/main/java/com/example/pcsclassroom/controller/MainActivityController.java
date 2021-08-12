package com.example.pcsclassroom.controller;

import com.example.pcsclassroom.model.LocalStorage;
import com.example.pcsclassroom.model.dao.UserRoomDao;
import com.example.pcsclassroom.model.pojo.User;
import com.example.pcsclassroom.view.MainActivity;

public class MainActivityController {
    //DAO  -> Data Access Object
    private UserRoomDao userRoomDao;



    public User checkActualUser(MainActivity mainActivity){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivity.getApplicationContext()).userRoomDao();
        User user;
        user = this.userRoomDao.getUser();
        if (user != null){
            return user;
        }
        return null;
    }

    public String checkUserName(MainActivity mainActivity, String name){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivity.getApplicationContext()).userRoomDao();
        User user;
        user = this.userRoomDao.getUserByName(name);
        if (user != null){
            return user.getName();
        }
        return null;
    }

    public void register(MainActivity mainActivity, String name, Integer avatar, String roll){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivity.getApplicationContext()).userRoomDao();
        if(name == null || name.compareTo("")==0){
            mainActivity.nameIsMandatory();
            return;
        }
        User user = new User();
        user.setName(name);
        user.setAvatar(avatar);
        user.setRoll(roll);
        this.userRoomDao.insertOne(user);
        mainActivity.registerSucceed(user);
    }

    public void updateRegisteredUser(MainActivity mainActivity, String name, Integer avatar, String roll){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivity.getApplicationContext()).userRoomDao();
        if(name == null || name.compareTo("")==0){
            mainActivity.nameIsMandatory();
            return;
        }
        User user = new User();
        user.setName(name);
        user.setAvatar(avatar);
        user.setRoll(roll);
        this.userRoomDao.updateOne(user);
        mainActivity.registerSucceed(user);
    }
}
