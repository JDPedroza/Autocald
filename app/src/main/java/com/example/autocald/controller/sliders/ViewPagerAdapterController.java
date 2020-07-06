package com.example.autocald.controller.sliders;

import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.example.autocald.ui.conditionBoilerElements.SliderFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapterController extends FragmentPagerAdapter {

    private Fragment mCurrentFragment;

    //generamos un array para que sera recursivos los fragments
    private FragmentManager fm;
    FragmentTransaction tr;
    List<Fragment> list=new ArrayList<>();

    public ViewPagerAdapterController(@NonNull FragmentManager fm) {
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

    public void resetFragment(SliderFragment fragment){
        fragment.resetFrom();
    }

    public void addPhoto(SliderFragment fragment, String path){
        fragment.addPhoto(path);
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (getCurrentFragment() != object) {
            mCurrentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }
}
