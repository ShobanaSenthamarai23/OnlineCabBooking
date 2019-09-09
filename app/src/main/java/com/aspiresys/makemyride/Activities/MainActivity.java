package com.aspiresys.makemyride.Activities;

import android.os.Bundle;

import com.aspiresys.makemyride.Fragments.BookingFragment;
import com.aspiresys.makemyride.Fragments.HomeFragment;
import com.aspiresys.makemyride.Fragments.OffersFragment;
import com.aspiresys.makemyride.Fragments.ProfileFragment;
import com.aspiresys.makemyride.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "HomeFragment";
    HomeFragment mHomeFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.home:
                    setTitle("HomeFragment");
                    HomeFragment homeFragment = new HomeFragment();
                    replaceFragment(homeFragment);
                    return true;
                case R.id.ride_booking:
                    setTitle("BookingFragment");
                    fragment = new BookingFragment();
                    replaceFragment(fragment);
                    return true;
                case R.id.rewards:
                    setTitle("OffersFragment");
                    fragment = new OffersFragment();
                    replaceFragment(fragment);
                    return true;
                case R.id.profile:
                    setTitle("ProfileFragment");
                    fragment = new ProfileFragment();
                    addFragment(fragment);
                    return true;
                default:
                    new HomeFragment();
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        BottomNavigationView navView = findViewById(R.id.nav_view);
//        mTextMessage = findViewById(R.id.message);
        mHomeFragment = new HomeFragment();
        addFragment(mHomeFragment);
        setTitle("HomeFragment");
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    //replace fragment
    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment frag=popNonDefaultFragment();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commit();
    }


    public void addFragment(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame, f);
        ft.addToBackStack(TAG);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public Fragment popNonDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        int count=getSupportFragmentManager().getBackStackEntryCount();
        Fragment frag = fm.getFragments().get(count>0?count-1:count);
       return frag;
        }
    }

    //    @Override
//    public void onBackPressed() {
//
//        FragmentManager fm = getSupportFragmentManager();
//        for (Fragment frag : fm.getFragments()) {
//            if (frag == null) {
//                super.onBackPressed();
//                finish();
//                return;
//            }
//            if (frag.isVisible()) {
//                FragmentManager childFm = frag.getChildFragmentManager();
//                if (childFm.getFragments() == null) {
//                    super.onBackPressed();
//                    finish();
//                    return;
//                }
//                if (childFm.getBackStackEntryCount() > 1) {
//                    childFm.popBackStack();
//                    return;
//                }
//                else {
//
//                    fm.popBackStack();
//                    if (fm.getFragments().size() <= 1) {
//                        finish();
//                    }
//                    return;
//                }
//
//            }
//        }
//    }

