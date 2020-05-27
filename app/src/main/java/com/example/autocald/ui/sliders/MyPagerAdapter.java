package com.example.autocald.ui.sliders;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    public List<Fragment> fragments;
    public FragmentManager fm;

    public MyPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        this.fm = fm;
        this.fragments = new ArrayList<Fragment>();
    }


    public void addFragmentAtPosition(int position, Fragment f) {
        if(position == fragments.size())
            fragments.add(f);
        else
            fragments.add(position, f);
        notifyDataSetChanged();
    }

    public void addFragment(Fragment fragment){
        fragments.add(fragment);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }
}
