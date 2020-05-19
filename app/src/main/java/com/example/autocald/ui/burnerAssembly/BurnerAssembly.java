package com.example.autocald.ui.burnerAssembly;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.autocald.R;
import com.example.autocald.ui.sliders.MyViewPagerAdapter;

public class BurnerAssembly extends Fragment {

    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;
    private LinearLayout dotLayout;
    private Button btnBack;
    private Button btnNext;

    public BurnerAssembly() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_burner_assembly, container, false);

    }
}
