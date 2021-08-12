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
    private Integer avatar;
    private View rootView;
    private TextView nameTextView;
    private ImageView avatarImageView;

    public UserInformationFragment() {
        // Required empty public constructor
    }

    public static UserInformationFragment newInstance(String name, Integer avatar) {
        UserInformationFragment fragment = new UserInformationFragment();
        fragment.setName(name);
        fragment.setAvatar(avatar);
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
        nameTextView.setText(name);
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

}