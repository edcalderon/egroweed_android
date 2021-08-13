package com.example.pcsclassroom.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pcsclassroom.R;
import com.example.pcsclassroom.controller.ProfileActivityController;


import es.dmoral.toasty.Toasty;


public class Profile extends AppCompatActivity {

    private EditText emailEditText;
    private EditText rollEditText;
    private EditText nameEditText;
    private ImageView avatarImageView;
    private Button updateButton;
    private ProfileActivityController profileActivityController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        String userName = getIntent().getExtras().getString("userName") != null ? getIntent().getExtras().getString("userName") : "";
        String userEmail = getIntent().getExtras().getString("userEmail") != null ? getIntent().getExtras().getString("userEmail") : "";
        String userRoll = getIntent().getExtras().getString("userRoll") != null ? getIntent().getExtras().getString("userRoll") : "";
        Integer userAvatar = getIntent().getExtras().getInt("userAvatar") != -1 ? getIntent().getExtras().getInt("userAvatar") : 0;
        emailEditText = findViewById(R.id.editText_profile_email);
        emailEditText.setText(userEmail);
        emailEditText.setFocusable(false);
        nameEditText = findViewById(R.id.editText_profile_name);
        nameEditText.setText(userName);
        rollEditText = findViewById(R.id.editText_profile_roll);
        rollEditText.setText(userRoll);
        rollEditText.setFocusable(false);
        avatarImageView = findViewById(R.id.imageView_profile_imagen_profile);
        setAvatarImageView(userAvatar);
        updateButton = findViewById(R.id.button_profile_update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });

        if(userRoll.compareTo("E-grower")==0){
            setTitle(R.string.profile_egrower);
        } else {
            setTitle(R.string.profile_egrower_master);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    public void updateUser(){
        if(nameEditText.getText().toString().isEmpty()) {
            Toasty.warning(getApplicationContext(), "Change something", Toast.LENGTH_SHORT, true).show();
        } else{
            profileActivityController.updateUser(this, emailEditText.getText().toString(), nameEditText.getText().toString());
        }
    }
    public void updateUserSucceed(){
        Toasty.success(getApplicationContext(), "User updated", Toast.LENGTH_SHORT, true).show();
    }
    public void logout(){
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.configuration_button_student_menu_dashboard:
                finish();
                return true;
            case R.id.configuration_button_student_menu_logout:
                AlertDialog.Builder builder_logout = new AlertDialog.Builder(this)
                        .setTitle("Exit")
                        .setMessage("Do you want to exit?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                logout();
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder_logout.create().show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void setAvatarImageView(Integer avatarIndex){
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