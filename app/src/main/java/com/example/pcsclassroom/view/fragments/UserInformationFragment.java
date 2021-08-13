package com.example.pcsclassroom.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pcsclassroom.R;

public class UserInformationFragment extends Fragment {
    private String name;
    private String email;
    private Integer avatar;
    private String roll;
    private View rootView;
    private TextView nameTextView;
    private ImageView avatarImageView;
    private TextView rollTextView;

    public UserInformationFragment() {
        // Required empty public constructor
    }

    public static UserInformationFragment newInstance(String email, String name, Integer avatar, String roll) {
        UserInformationFragment fragment = new UserInformationFragment();
        fragment.setName(name);
        fragment.setEmail(email);
        fragment.setAvatar(avatar);
        fragment.setRoll(roll);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_user_information, container, false);
        nameTextView = rootView.findViewById(R.id.user_name_user_information_fragment);
        avatarImageView = rootView.findViewById(R.id.avatar_information_fragment);
        // rollTextView = rootView.findViewById(R.id.menu_roll_textView);
        nameTextView.setText(name);
        // rollTextView.setText(roll);
        switch (avatar){
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
        return  rootView;
    }

    public String getName() {
        return name;
    }

    public Integer getAvatar() {
        return avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
    }

    private void setRoll(String roll) { this.roll = roll; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}