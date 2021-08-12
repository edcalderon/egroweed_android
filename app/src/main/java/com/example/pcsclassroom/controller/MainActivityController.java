package com.example.pcsclassroom.controller;

import com.example.pcsclassroom.model.LocalStorage;
import com.example.pcsclassroom.model.dao.UserRoomDao;
import com.example.pcsclassroom.model.pojo.User;
import com.example.pcsclassroom.view.MainActivity;

public class MainActivityController {
    //DAO  -> Data Access Object
    private UserRoomDao userRoomDao;

    public void checkUser(MainActivity mainActivity){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivity.getApplicationContext()).userRoomDao();
        String name;
        Integer avatar;
        User user;
        user = this.userRoomDao.getUser();
        if (user != null){
            name = user.getName();
            avatar = user.getAvatar();
            mainActivity.registerSucceed(name,avatar);
        }
    }


    public void register(MainActivity mainActivity, String name, Integer avatar){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivity.getApplicationContext()).userRoomDao();
        if(name == null || name.compareTo("")==0){
            mainActivity.nameIsMandatory();
            return;
        }

        User user = new User();
        user.setName(name);
        user.setAvatar(avatar);
        this.userRoomDao.insertOne(user);

        mainActivity.registerSucceed(name,avatar);
    }
}
