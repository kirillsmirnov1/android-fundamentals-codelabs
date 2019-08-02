package com.trulden.tabexperiment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private FragmentManager fm;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        mNumOfTabs = fm.getFragments().size();
        this.fm = fm;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new TabFragment1();
            case 1: return new TabFragment2();
            case 2: return new TabFragment3();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return fm.getFragments().size();//mNumOfTabs;
    }
}
