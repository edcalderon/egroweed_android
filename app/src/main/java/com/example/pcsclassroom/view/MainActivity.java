package com.example.pcsclassroom.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pcsclassroom.R;
import com.example.pcsclassroom.controller.MainActivityController;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText;
    private Button leftAvatarButton, rightAvatarButton, registerButton;
    private ImageView avatarImageView;
    private int avatarIndex;
    private MainActivityController mainActivityController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.input_user_name_register_user);
        leftAvatarButton = findViewById(R.id.left_button_avatar_register_user);
        rightAvatarButton = findViewById(R.id.right_button_register_user);
        avatarImageView = findViewById(R.id.avatar_image_view_register_user);
        registerButton = findViewById(R.id.button_register_user);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        leftAvatarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatarToLeft();
            }
        });
        rightAvatarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatarToRight(avatarImageView);
            }
        });
        avatarIndex = 0;
        getSupportActionBar().hide();
        mainActivityController = new MainActivityController();
        mainActivityController.checkUser(this);
    }
    public void nameIsMandatory(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("* Name is mandatory.")
                .setTitle("Something went wrong")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void registerSucceed(String name, Integer avatar){
        Intent newActivity = new Intent(this, StudentMenu.class);
        String userName = nameEditText.getText().toString();
        newActivity.putExtra("userName", userName);
        newActivity.putExtra("userAvatar", avatar);
        startActivity(newActivity);
    }

    public void registerUser(){
        mainActivityController.register(this,
                nameEditText.getText().toString(),
                avatarIndex);

    }
    public void avatarToLeft(){
        setAvatarIndex(avatarIndex - 1);
    }
    public void avatarToRight(View view){
        setAvatarIndex(avatarIndex + 1);
    }
    public void setAvatarIndex(int newAvatarIndex){
        if(newAvatarIndex < 0){
            avatarIndex = 4;
        } else {
            avatarIndex = newAvatarIndex % 5;
        }
        setAvatarImageView();
    }
    public void setAvatarImageView(){
        switch (avatarIndex){
            case 0:
                avatarImageView.setImageResource(R.drawable.ic_avatar_1);
                break;
            case 1:
                avatarImageView.setImageResource(R.drawable.ic_avatar_2);
                break;
            case 2:
                avatarImageView.setImageResource(R.drawable.ic_avatar_3);
                break;
            case 3:
                avatarImageView.setImageResource(R.drawable.ic_avatar_4);
                break;
            case 4:
                avatarImageView.setImageResource(R.drawable.ic_avatar_5);
                break;
        }
    }
}