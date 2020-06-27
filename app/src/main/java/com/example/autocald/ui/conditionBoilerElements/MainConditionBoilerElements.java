package com.example.autocald.ui.conditionBoilerElements;

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
import com.example.autocald.controller.sliders.ViewPagerAdapterController;

public class MainConditionBoilerElements extends Fragment{

    private ViewPager viewPager;
    private ViewPagerAdapterController adapter;
    private LinearLayout dotLayout;

    private String[]title;
    private String[]dataForm;

    private TextView[] dots;

    public MainConditionBoilerElements(int module) {
        if(module==4){
            this.title = new String[]{"Valvula de Combustible", "Ventilador", "Trasformador de Ignicion", "Electrodos", "Cable de Alta", "Boquilla de combustible", "Reguladora de Combustible", "Filtro de combustible", "Tuberia de combustible", "Manometros de Combustible", "Swich de Presion"};
            this.dataForm = new String[]{"FuelValve", "Fan", "IgnitionTransformer", "Electrodes", "HighCable", "FuelNozzle", "FuelRegulator", "FuelFilter", "FuelLine", "FuelGauges", "PressureSwich"};
        }else if(module==5){
            this.title = new String[]{"Control de Presion", "Termostato", "Warrick( Bajo nivel Aux)", "Modutrol", "Control de Modulacion", "Manometros", "Valvulas de seguridad", "Swich de Aire Ventilador"};
            this.dataForm = new String[]{"PressureControl", "Thermostat", "WarrickLowAuxLevel", "Modutrol", "ModulationControl", "PressureGauges", "SafetyValves", "SwichAirFan"};
        }else if(module==6){
            this.title = new String[]{"Grifos de Purga", "Vidrio de nivel", "Empaques", "Flotador", "Ampolletas o Micros", "Grifos de nivel"};
            this.dataForm = new String[]{"BleedTaps", "LevelGlass", "Packaging", "Float", "AmpoulesMicros", "LevelTaps"};
        }else if(module==7){
            this.title = new String[]{"Aspecto General", "Flotador", "Vidrio de nivel", "Filtro de Agua", "Tuberias", "Valvulas", "Termometro"};
            this.dataForm = new String[]{"GeneralAspect", "FloatModule7", "LevelGlass", "WaterFilter", "Pipelines", "Valves", "Thermometer"};
        }else if(module==8){
            this.title = new String[]{"Aspecto General", "Acople", "Presion", "Accesorios"};
            this.dataForm = new String[]{"GeneralAspectModule8", "Coupling", "Pressure", "Accessories"};
        }else if(module==9){
            this.title = new String[]{"Falla de Llama", "Bajo Nivel Macdonell", "Bajo Nivel Warrick", "Alta Presion", "Baja Presion", "Aire de Combustion", "Alta presion de Vapor", "Prueba Hidrostatica", "Valvulas de Seguridad", "Temperatura (Alta y Baja)", "Otros"};
            this.dataForm = new String[]{"FlameFailure", "MacdonellLowLevel", "LowLevelWarrick", "High pressure", "LowPressure", "CombustionAir", "HighSteamPressure", "HydrostaticTest", "SafetyValves", "TemperatureHighLow", "Others"};
        }else if(module==10){
            this.title = new String[]{"Shell", "Camara Humeda", "Camara Seca", "Refractarios", "Tuberia interna", "Soldaduras", "Tapas Frontal y Trasera", "Manhole", "Handhole", "Empaques", "Pintura ", "Aislamiento"};
            this.dataForm = new String[]{"Shell", "WetChamber", "DryChamber", "Refractories", "InternalPiping", "Welds", "FrontBacKCovers", "Manhole", "Handhole", "Packaging", "Painting", "Isolation"};
        }else if(module==11){
            this.title = new String[]{"Programador", "Sensor de Llama", "Fotocelda", "Cableado General", "Corazas Electricas", "Breaker", "Fusibles", "Contactores", "Reles", "Terminales", "Organización ", "Placas de Señalizacion", "Bombillos"};
            this.dataForm = new String[]{"Programmer", "FlameSensor", "Photocell", "GeneralWiring", "ElectricShells", "Breaker", "Fuses", "Contactors", "Relays", "Terminals", "Organization", "SignalingPlates", "LightBulbs"};
        }else if(module==12){
            this.title = new String[]{"Ventilador", "Compresor", "Bomba de Agua", "Bomba de Combustible", "Rodamientos"};
            this.dataForm = new String[]{"FanModule12", "Compressor", "WaterPump", "FuelPump", "Bearings"};
        }else{
            this.title = new String[]{"Aspecto General", "Soporteria", "Aislamiento", "Valvulas", "Trampas", "Cheques", "Purgas", "Distribuidor"};
            this.dataForm = new String[]{"GeneralAspectModule13", "Support", "IsolationModule13", "ValvesModule13", "Traps", "Checks","Purges", "Distributor"};
        }
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
        adapter.notifyDataSetChanged();
    }

    //metodo para cargar las pantallas

    public void loadViewPager(){
        adapter = new ViewPagerAdapterController(getChildFragmentManager());
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

    public void resetForm(){
        adapter.resetFragment((SliderFragment) adapter.getCurrentFragment());
    }

    public void addPhoto(String path){
        adapter.addPhoto((SliderFragment) adapter.getCurrentFragment(), path);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter.notifyDataSetChanged();
    }
}
