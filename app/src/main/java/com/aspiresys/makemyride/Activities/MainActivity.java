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
    /**
     * Performing navigation action in navigation bar
     */

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.home:
                    setTitle("HomeFragment");
                    HomeFragment homeFragment = new HomeFragment();
//                    replaceFragment(homeFragment, true);
//                    replaceFragment(homeFragment);
                    return true;
                case R.id.ride_booking:
                    setTitle("BookingFragment");

                    fragment = new BookingFragment();
                    replaceFragment(fragment, false);
//                    replaceFragment(fragment);
                    return true;
                case R.id.rewards:
                    setTitle("OffersFragment");
                    fragment = new OffersFragment();
                    replaceFragment(fragment, false);
//                    replaceFragment(fragment);
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
        mHomeFragment = new HomeFragment();
        addFragment(mHomeFragment);
        setTitle("HomeFragment");
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * @param fragment
     */

    //replacing fragments
    public void replaceFragment(Fragment fragment, boolean isHomeFragments) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            if(isHomeFragments)
            {
                fragmentTransaction.addToBackStack(null);
            }
            fragmentTransaction.commit();

    }

    /**
     * @param fragment
     * @returns void
     */
    public void addFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame, fragment);
        ft.addToBackStack(TAG);
        ft.commit();
    }

    /**
     * On back press it moves to mainActivity( default fragment)
     */

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


     //Popping the fragments before it is replaced or added by other fragment in UI
    public boolean popNonDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getFragments().size();
        if ( count > 0 ) {
            for ( int i = 0; i < count; i++ ) {
                Fragment fragment = fm.getFragments().get( i );
                if ( ! ( fragment instanceof  HomeFragment ) )
                {
                    fm.popBackStackImmediate();
                }
                if ( i == count ) {
                    return true;
                }
            }
        }

       return false;
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

