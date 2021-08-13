package com.example.pcsclassroom.controller;

import com.example.pcsclassroom.model.LocalStorage;
import com.example.pcsclassroom.model.dao.UserRoomDao;
import com.example.pcsclassroom.model.pojo.User;
import com.example.pcsclassroom.view.MainActivityLogin;


public class MainActivityControllerLogin {
    //DAO  -> Data Access Object
    private UserRoomDao userRoomDao;

    public void login(MainActivityLogin mainActivityLogin, String email, String password){
        this.userRoomDao = LocalStorage.getLocalStorage(mainActivityLogin.getApplicationContext()).userRoomDao();
        User user = this.userRoomDao.getUserByEmail(email);
        if(user.getPassword().compareTo(password)==0){
            mainActivityLogin.loginSucceed(user);
        } else {
            mainActivityLogin.loginFail(user);
        }
    }
}
