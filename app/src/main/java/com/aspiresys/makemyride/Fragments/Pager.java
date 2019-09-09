package com.aspiresys.makemyride.Fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

class Pager extends FragmentStatePagerAdapter {
  int mTabCount;
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        this.mTabCount=tabCount;
    }



    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ConfirmationPageFragment confirmationPage = new ConfirmationPageFragment();
                return confirmationPage;
            case 1:
                CancellationPageFragment cancellationPageFragment=new CancellationPageFragment();
                return cancellationPageFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }
}
