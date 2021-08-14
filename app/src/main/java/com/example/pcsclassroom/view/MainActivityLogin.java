package com.example.pcsclassroom.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pcsclassroom.R;
import com.example.pcsclassroom.controller.MainActivityControllerLogin;
import com.example.pcsclassroom.model.pojo.User;

import es.dmoral.toasty.Toasty;

public class MainActivityLogin extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private MainActivityControllerLogin mainActivityControllerLogin;
    private Button loginButton;
    public static final String SESSION = "MyPrefs" ;
    public static final String Name = "nameKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        setContentView(R.layout.activity_main_login);
        emailEditText = findViewById(R.id.editTextEmail_login);
        passwordEditText = findViewById(R.id.editTextTextPassword_login);
        loginButton = findViewById(R.id.button_login_loginView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean emptyPassword = passwordEditText.getText().toString().isEmpty();
                if(emptyPassword) {
                    Toasty.warning(getApplicationContext(), "Enter a password", Toast.LENGTH_SHORT, true).show();
                }
                if(emailEditText.getText().toString().isEmpty()) {
                    Toasty.warning(getApplicationContext(), "Enter an email address", Toast.LENGTH_SHORT, true).show();
                }else {
                    if (emailEditText.getText().toString().trim().matches(emailPattern) && !emptyPassword) {
                        login();
                    } else {
                        Toasty.error(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT, true).show();
                    }
                }
            }
        });
        mainActivityControllerLogin = new MainActivityControllerLogin();

    }

    public void login(){
        mainActivityControllerLogin.login(this,
                emailEditText.getText().toString(),
                passwordEditText.getText().toString()
        );
    }

    public void loginSucceed(User user){
        SharedPreferences sharedpreferences = getSharedPreferences(SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, user.getEmail());
        editor.commit();
        if(user.getRoll().compareTo("E-grower")==0){
            Intent newActivity = new Intent(this, StudentMenu.class);
            newActivity.putExtra("userEmail", user.getEmail());
            // newActivity.putExtra("userName", user.getName());
            newActivity.putExtra("userAvatar", user.getAvatar());
            newActivity.putExtra("userRoll", user.getRoll());
            startActivity(newActivity);
        }
        if(user.getRoll().compareTo("E-grower Master")==0){
            Intent newActivity = new Intent(this, EgrowerMasterDashboard.class);
            newActivity.putExtra("userEmail", user.getEmail());
            // newActivity.putExtra("userName", user.getName());
            newActivity.putExtra("userAvatar", user.getAvatar());
            newActivity.putExtra("userRoll", user.getRoll());
            startActivity(newActivity);
        }

    }

    public void loginFail(User user){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please verify your email and password.")
                .setTitle("Something went wrong!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}