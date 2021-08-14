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

import com.example.pcsclassroom.R;
import com.example.pcsclassroom.view.fragments.UserInformationFragment;

public class StudentMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        String sessionEmail = sharedpreferences.getString("emailKey", "");
        String sessionName = sharedpreferences.getString("nameKey", "");
        String userEmail = sessionName != null ? sessionName : sessionEmail;
        Integer userAvatar = getIntent().getExtras().getInt("userAvatar") != -1 ? getIntent().getExtras().getInt("userAvatar") : 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.egrower_master_menu_user_information_fragment, UserInformationFragment.newInstance(userEmail, userAvatar)).commit();
        setTitle(R.string.dashboard_egrower);
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.SESSION, Context.MODE_PRIVATE);
        String sessionEmail = sharedpreferences.getString("emailKey", "");
        String sessionName = sharedpreferences.getString("nameKey", "");
        String userEmail = sessionName != null ? sessionName : sessionEmail;
        Integer userAvatar = getIntent().getExtras().getInt("userAvatar") != -1 ? getIntent().getExtras().getInt("userAvatar") : 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.egrower_master_menu_user_information_fragment, UserInformationFragment.newInstance(userEmail, userAvatar)).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.student_menu, menu);
        return true;
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
                Intent newActivity = new Intent(this, Profile.class);
                newActivity.putExtra("userName", getIntent().getExtras().getString("userName") != null ? getIntent().getExtras().getString("userName") : "");
                newActivity.putExtra("userEmail", getIntent().getExtras().getString("userEmail") != null ? getIntent().getExtras().getString("userEmail") : "");
                newActivity.putExtra("userAvatar", getIntent().getExtras().getInt("userAvatar") != -1 ? getIntent().getExtras().getInt("userAvatar") : 0);
                newActivity.putExtra("userRoll", getIntent().getExtras().getString("userRoll") != null ? getIntent().getExtras().getString("userRoll") : "");
                startActivity(newActivity);;
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
}