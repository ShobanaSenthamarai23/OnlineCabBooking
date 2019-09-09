package com.aspiresys.makemyride.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.aspiresys.makemyride.Fragments.LoginFragment;
import com.aspiresys.makemyride.R;

public class UserAuthenticationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_authentication_activity);
        LoginFragment loginFragment = new LoginFragment();
        addFragment(loginFragment);
    }

    public void addFragment(Fragment f) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container1, f);
        ft.commit();
    }

    public void replaceFragment(Fragment f) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container1, f);
        ft.addToBackStack(null);
        ft.commit();
    }



}

