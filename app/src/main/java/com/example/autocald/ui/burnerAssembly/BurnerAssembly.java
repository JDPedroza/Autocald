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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autocald.R;
import com.example.autocald.ui.sliders.MyViewPagerAdapter;
import com.example.autocald.ui.sliders.SliderFragment;

public class BurnerAssembly extends Fragment{

    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;
    private LinearLayout dotLayout;
    private Button btnBack;
    private Button btnNext;

    private String[]title={"1", "2", "3", "4"};
    private String[]content={"hola1", "hola2", "hola3", "hola4"};
    private int[]image={R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    private int[]colorBackground, colorDots;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewPager);
        dotLayout = view.findViewById(R.id.layoutDots);
        btnBack = view.findViewById(R.id.btnBack);
        btnNext = view.findViewById(R.id.btnNext);

        colorBackground=getResources().getIntArray(R.array.array_background);
        colorDots=getResources().getIntArray(R.array.array_dots);

        addDots(0);
        loadViewPager();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem()==title.length-1){
                    getActivity().finish();
                }else{
                    int back=getItem(-1);
                    viewPager.setCurrentItem(back);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int next=getItem(+1);
                if(next<title.length) {
                    viewPager.setCurrentItem(next);
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Obtener Promocion",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //metodo para cargar las pantallas

    public void loadViewPager(){
        adapter = new MyViewPagerAdapter(getActivity().getSupportFragmentManager());
        for(int i=0; i<title.length; i++){
            adapter.addFragment(newInstance(title[i], content[i], image[i], colorBackground[i]));
        }
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(pagerListener);
    }

    //metodo que genera los sliders
    private SliderFragment newInstance(String title, String content, int image, int color){
        Bundle bundle=new Bundle();
        bundle.putString("title", title);
        bundle.putString("content", content);
        bundle.putInt("image", image);
        bundle.putInt("color", color);

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
                dots[i].setTextColor(colorDots[i]);
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

            if(position==title.length-1){
                btnNext.setText("Obtener");
                btnBack.setText("Salir");
            }else{
                btnNext.setText("Siguiente");
                btnBack.setText("Atras");
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    private int getItem(int i){
        return viewPager.getCurrentItem()+i;
    }

}
