package com.example.autocald.ui.sliders;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.autocald.R;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    //generamos un array para que sera recursivos los fragments
    FragmentManager fm;
    FragmentTransaction tr;
    List<Fragment> list=new ArrayList<>();

    public MyViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        this.fm = fm;
        this.tr = fm.beginTransaction();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void addFragment(Fragment fragment, int position){
        if(position == list.size())
            list.add(fragment);
        else
            list.add(position, fragment);
            notifyDataSetChanged();
     }

    public void resetFragment(Fragment fragment, ViewPager viewPager){
        list.remove(9);
        tr.replace(viewPager.getId(), fragment);
        tr.commit();
    }
}
