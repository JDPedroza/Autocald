package com.example.autocald.ui.conditionBoilerElements;

import android.content.Context;
import android.content.SharedPreferences;
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
    private int numberFragment;

    public MainConditionBoilerElements(int module) {
        if(module==4){
            this.title = new String[]{"Valvula de Combustible", "Ventilador", "Trasformador de Ignicion", "Electrodos", "Cable de Alta", "Boquilla de combustible", "Reguladora de Combustible", "Filtro de combustible", "Tuberia de combustible", "Manometros de Combustible", "Swich de Presion"};
            this.dataForm = new String[]{"M4FuelValve", "M4Fan", "M4IgnitionTransformer", "M4Electrodes", "M4HighCable", "M4FuelNozzle", "M4FuelRegulator", "M4FuelFilter", "M4FuelLine", "M4FuelGauges", "M4PressureSwich"};
        }else if(module==5){
            this.title = new String[]{"Control de Presion", "Termostato", "Warrick( Bajo nivel Aux)", "Modutrol", "Control de Modulacion", "Manometros", "Valvulas de seguridad", "Swich de Aire Ventilador"};
            this.dataForm = new String[]{"M5PressureControl", "M5Thermostat", "M5WarrickLowAuxLevel", "M5Modutrol", "M5ModulationControl", "M5PressureGauges", "M5SafetyValves", "M5SwichAirFan"};
        }else if(module==6){
            this.title = new String[]{"Grifos de Purga", "Vidrio de nivel", "Empaques", "Flotador", "Ampolletas o Micros", "Grifos de nivel"};
            this.dataForm = new String[]{"M6BleedTaps", "M6LevelGlass", "M6Packaging", "M6Float", "M6AmpoulesMicros", "M6LevelTaps"};
        }else if(module==7){
            this.title = new String[]{"Aspecto General", "Flotador", "Vidrio de nivel", "Filtro de Agua", "Tuberias", "Valvulas", "Termometro"};
            this.dataForm = new String[]{"M7GeneralAspect", "M7FloatModule7", "M7LevelGlass", "M7WaterFilter", "M7Pipelines", "M7Valves", "M7Thermometer"};
        }else if(module==8){
            this.title = new String[]{"Aspecto General", "Acople", "Presion", "Accesorios"};
            this.dataForm = new String[]{"M8GeneralAspectModule8", "M8Coupling", "M8Pressure", "M8Accessories"};
        }else if(module==9){
            this.title = new String[]{"Falla de Llama", "Bajo Nivel Macdonell", "Bajo Nivel Warrick", "Alta Presion", "Baja Presion", "Aire de Combustion", "Alta presion de Vapor", "Prueba Hidrostatica", "Valvulas de Seguridad", "Temperatura (Alta y Baja)", "Otros"};
            //this.dataForm = new String[]{"M9FlameFailure", "M9MacdonellLowLevel", "M9LowLevelWarrick", "M9HighPressure", "M9LowPressure", "M9CombustionAir", "M9HighSteamPressure", "M9HydrostaticTest", "M9SafetyValves", "M9TemperatureHighLow", "M9Others"};
            this.dataForm = new String[]{"M91", "M92", "M93", "M94", "M95", "M96", "M97", "M98", "M99", "M910", "M911"};
        }else if(module==10){
            this.title = new String[]{"Shell", "Camara Humeda", "Camara Seca", "Refractarios", "Tuberia interna", "Soldaduras", "Tapas Frontal y Trasera", "Manhole", "Handhole", "Empaques", "Pintura ", "Aislamiento"};
            this.dataForm = new String[]{"M10Shell", "M10WetChamber", "M10DryChamber", "M10Refractories", "M10InternalPiping", "M10Welds", "M10FrontBacKCovers", "M10Manhole", "M10Handhole", "M10Packaging", "M10Painting", "M10Isolation"};
        }else if(module==11){
            this.title = new String[]{"Programador", "Sensor de Llama", "Fotocelda", "Cableado General", "Corazas Electricas", "Breaker", "Fusibles", "Contactores", "Reles", "Terminales", "Organización ", "Placas de Señalizacion", "Bombillos"};
            this.dataForm = new String[]{"M11Programmer", "M11FlameSensor", "M11Photocell", "M11GeneralWiring", "M11ElectricShells", "M11Breaker", "M11Fuses", "M11Contactors", "M11Relays", "M11Terminals", "M11Organization", "M11SignalingPlates", "M11LightBulbs"};
        }else if(module==12){
            this.title = new String[]{"Ventilador", "Compresor", "Bomba de Agua", "Bomba de Combustible", "Rodamientos"};
            this.dataForm = new String[]{"M12FanModule12", "M12Compressor", "M12WaterPump", "M12FuelPump", "M12Bearings"};
        }else{
            this.title = new String[]{"Aspecto General", "Soporteria", "Aislamiento", "Valvulas", "Trampas", "Cheques", "Purgas", "Distribuidor"};
            this.dataForm = new String[]{"M13GeneralAspectModule13", "M13Support", "M13IsolationModule13", "M13ValvesModule13", "M13Traps", "M13Checks","M13Purges", "M13Distributor"};
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

    private void loadViewPager(){
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
        TextView[] dots = new TextView[title.length];
        dotLayout.removeAllViews();

        for(int i = 0; i< dots.length; i++){
            dots[i]=new TextView(requireActivity().getApplicationContext());
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

    private ViewPager.OnPageChangeListener pagerListener=new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int position) {
            numberFragment = position;
            addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    public void resetForm(){
        adapter.resetFragment((SliderFragment) adapter.getCurrentFragment());
    }

    public void resetModule(){
        Fragment fragment;
        if(numberFragment==0){
            fragment = adapter.getItem(numberFragment+1);
            adapter.resetFragment((SliderFragment) fragment);
        }else if(numberFragment == adapter.getCount()-1){
            fragment = adapter.getItem(numberFragment-1);
            adapter.resetFragment((SliderFragment) fragment);
        }else{
            fragment = adapter.getItem(numberFragment-1);
            adapter.resetFragment((SliderFragment) fragment);
            fragment = adapter.getItem(numberFragment+1);
            adapter.resetFragment((SliderFragment) fragment);
        }
        fragment = adapter.getItem(numberFragment);
        adapter.resetFragment((SliderFragment) fragment);
        for (String s : dataForm) {
            SharedPreferences eDataForm = requireContext().getSharedPreferences(s, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = eDataForm.edit();
            editor.clear();
            editor.apply();
        }
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
