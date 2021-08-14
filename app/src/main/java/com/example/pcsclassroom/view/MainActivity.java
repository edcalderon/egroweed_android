package com.example.pcsclassroom.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pcsclassroom.R;
import com.example.pcsclassroom.controller.MainActivityController;
import com.example.pcsclassroom.model.pojo.User;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button leftAvatarButton, rightAvatarButton, registerButton, loginButton;
    private ImageView avatarImageView;
    private Spinner spinnerRoles;
    private int avatarIndex;
    private MainActivityController mainActivityController;
    public static final String SESSION = "MyPrefs" ;
    public static final String Name = "nameKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        setContentView(R.layout.activity_main);
        // nameEditText = findViewById(R.id.input_user_name_register_user);
        emailEditText = findViewById(R.id.editTextEmail_login);
        passwordEditText = findViewById(R.id.editTextTextPassword_input_register);
        leftAvatarButton = findViewById(R.id.left_button_avatar_register_user);
        rightAvatarButton = findViewById(R.id.right_button_register_user);
        avatarImageView = findViewById(R.id.avatar_image_view_register_user);
        registerButton = findViewById(R.id.button_login_loginView);
        loginButton = findViewById(R.id.button_login_register);
        spinnerRoles = findViewById(R.id.spinner_app_login_roles);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRoles.setAdapter(adapter);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean emptyPassword = passwordEditText.getText().toString().isEmpty();
                if(emptyPassword) {
                    Toasty.warning(getApplicationContext(), "Enter an password", Toast.LENGTH_SHORT, true).show();
                }
                if(emailEditText.getText().toString().isEmpty()) {
                    Toasty.warning(getApplicationContext(), "Enter an email address", Toast.LENGTH_SHORT, true).show();
                }else {
                    if (emailEditText.getText().toString().trim().matches(emailPattern) && !emptyPassword) {
                        registerUser();
                    } else {
                        Toasty.error(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT, true).show();
                    }
                }
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(MainActivity.this, MainActivityLogin.class);
                startActivity(newActivity);
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
        User actualUser = mainActivityController.checkActualUser(this);
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        String sessionName = sharedpreferences.getString("nameKey", "name");
        if(actualUser != null && actualUser.getEmail().compareTo(sessionName)==0){
            registerSucceed(actualUser);
        }
    }

    public void updateAlreadyRegisteredUser(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You can only update your avatar.")
                .setTitle("That is your actual name")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateRegisteredUser();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void userAlreadyTaken(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("try again.")
                .setTitle("That user has already been taken.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void alertNewUserToRegister(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure to register with this new user?")
                .setTitle("Warning!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainActivityController.register(MainActivity.this,
                                emailEditText.getText().toString(),
                                passwordEditText.getText().toString(),
                                avatarIndex,
                                spinnerRoles.getSelectedItem().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void registerSucceed(User user){
        SharedPreferences sharedpreferences = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, user.getEmail());
        editor.commit();
        if(user.getRoll().compareTo("E-grower")==0){
            Intent newActivity = new Intent(this, StudentMenu.class);
            newActivity.putExtra("userEmail", user.getEmail());
            newActivity.putExtra("userAvatar", user.getAvatar());
            newActivity.putExtra("userRoll", user.getRoll());
            startActivity(newActivity);
        }
        if(user.getRoll().compareTo("E-grower Master")==0){
            Intent newActivity = new Intent(this, EgrowerMasterDashboard.class);
            newActivity.putExtra("userEmail", user.getEmail());
            newActivity.putExtra("userAvatar", user.getAvatar());
            newActivity.putExtra("userRoll", user.getRoll());
            startActivity(newActivity);
        }

    }

    public void registerUser(){
        User checkActualUser = mainActivityController.checkActualUser(this);
        String checkUserEmail = mainActivityController.checkUserEmail(this, emailEditText.getText().toString());
        if(checkActualUser == null && checkUserEmail == null){
            mainActivityController.register(this,
                    emailEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    avatarIndex,
                    spinnerRoles.getSelectedItem().toString());
        }
        if(checkActualUser.getEmail().compareTo(emailEditText.getText().toString())==0){
            updateAlreadyRegisteredUser();
        }
        if(checkUserEmail != null){
            userAlreadyTaken();
        }
        if(checkActualUser != null && checkUserEmail == null){
            alertNewUserToRegister();
        }
    }
    public void updateRegisteredUser(){
        mainActivityController.updateRegisteredUser(this,
                emailEditText.getText().toString(),
                passwordEditText.getText().toString(),
                avatarIndex,
                spinnerRoles.getSelectedItem().toString());
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