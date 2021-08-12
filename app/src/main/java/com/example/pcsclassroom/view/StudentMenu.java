package com.example.pcsclassroom.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.pcsclassroom.R;
import com.example.pcsclassroom.view.fragments.UserInformationFragment;

public class StudentMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);
        String userName = getIntent().getExtras().getString("userName") != null ? getIntent().getExtras().getString("userName") : "not provided";
        Integer userAvatar = getIntent().getExtras().getInt("userAvatar") != -1 ? getIntent().getExtras().getInt("userAvatar") : 0;
        String roll = getIntent().getExtras().getString("userRoll") != null ? getIntent().getExtras().getString("userRoll") : "not provided";
        getSupportFragmentManager().beginTransaction().replace(R.id.user_menu_user_information_fragment, UserInformationFragment.newInstance(userName, userAvatar, roll)).commit();
        setTitle(R.string.register_title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.student_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.configuration_button_student_menu:
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("configuration");
                builder.create().show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}