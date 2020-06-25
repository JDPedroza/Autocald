package com.example.autocald.ui.burnerAssembly;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.autocald.R;
import com.example.autocald.controller.sliders.MyViewPagerAdapter;
import com.example.autocald.controller.sliders.SliderFragment;

public class BurnerAssembly extends Fragment implements SliderFragment.Actualizar{

    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;
    private LinearLayout dotLayout;

    private String[]title={"Valvula de Combustible", "Ventilador", "Trasformador de Ignicion", "Electrodos", "Cable de Alta", "Boquilla de combustible", "Reguladora de Combustible", "Filtro de combustible", "Tuberia de combustible", "Manometros de Combustible", "Swich de Presion"};
    private String[]dataForm={"FuelValve", "Fan", "IgnitionTransformer", "Electrodes", "HighCable", "FuelNozzle", "FuelRegulator", "FuelFilter", "FuelLine", "FuelGauges", "PressureSwich"};

    private TextView[] dots;

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

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewPager);
        dotLayout = view.findViewById(R.id.layoutDots);

        addDots(0);
        loadViewPager();
    }

    //metodo para cargar las pantallas

    public void loadViewPager(){
        adapter = new MyViewPagerAdapter(getActivity().getSupportFragmentManager());
        for(int i=0; i<title.length; i++){
            adapter.addFragment(newInstance(title[i], dataForm[i]), i);
        }
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(pagerListener);
    }

    //metodo que genera los sliders
    private SliderFragment newInstance(String title, String dataForm){
        Bundle bundle=new Bundle();
        bundle.putString("title", title);
        bundle.putString("dataForm", dataForm);

        SliderFragment fragment=new SliderFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    private void addDots(int currentPage){
        dots=new TextView[title.length];
        dotLayout.removeAllViews();

        for(int i=0; i<dots.length; i++){
            dots[i]=new TextView(getActivity().getApplicationContext());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            if(i==currentPage){
                dots[i].setTextColor(getResources().getColor(R.color.colorAccent));
            }else{
                dots[i].setTextColor(Color.LTGRAY);
            }
            dotLayout.addView(dots[i]);
        }
    }

    ViewPager.OnPageChangeListener pagerListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    private int getItem(int i){
        return viewPager.getCurrentItem()+i;
    }

    public void resetForm(){
        adapter.resetFragment((SliderFragment) adapter.getCurrentFragment());
    }

    @Override
    public void actualizarItem() {

    }
}
