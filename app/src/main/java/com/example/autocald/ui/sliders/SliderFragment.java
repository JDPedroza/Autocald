package com.example.autocald.ui.sliders;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.autocald.R;

public class SliderFragment extends Fragment {

    //creamos elementos de tipo vista imagen y texto
    View view;
    TextView title;
    Spinner spinner;
    Button btnB, btnR, btnM;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //creamos la vista
        view = inflater.inflate(R.layout.fragment_slider, container, false);

        //llamamos los elementos creados en el layaout
        title = view.findViewById(R.id.txtTitle);
        spinner = view.findViewById(R.id.spinner);
        RelativeLayout background = view.findViewById(R.id.background);

        String [] observaciones={"NA", "Ok", "Otra", "Se cambio", "Se reviso"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item_sliders, observaciones);

        if(getArguments()!=null){
            title.setText(getArguments().getString("title"));
            spinner.setAdapter(adapter);
            background.setBackgroundColor(getResources().getColor(R.color.background_1));
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mostrar();

        btnB= view.findViewById(R.id.btnB);
        btnR= view.findViewById(R.id.btnR);
        btnM= view.findViewById(R.id.btnM);

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    btnB.setBackgroundColor(getResources().getColor(R.color.btnGood));
                    btnR.setBackgroundColor(getResources().getColor(R.color.btnUnselected));
                    btnM.setBackgroundColor(getResources().getColor(R.color.btnUnselected));
                    //Toast.makeText(getActivity().getApplicationContext(),"Funcionalidad Activa",Toast.LENGTH_LONG).show();
            }
        });

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnB.setBackgroundColor(getResources().getColor(R.color.btnUnselected));
                btnR.setBackgroundColor(getResources().getColor(R.color.btnRegular));
                btnM.setBackgroundColor(getResources().getColor(R.color.btnUnselected));
                //Toast.makeText(getActivity().getApplicationContext(),"Funcionalidad Activa",Toast.LENGTH_LONG).show();
            }
        });

        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnB.setBackgroundColor(getResources().getColor(R.color.btnUnselected));
                btnR.setBackgroundColor(getResources().getColor(R.color.btnUnselected));
                btnM.setBackgroundColor(getResources().getColor(R.color.btnBad));
                //Toast.makeText(getActivity().getApplicationContext(),"Funcionalidad Activa",Toast.LENGTH_LONG).show();
            }
        });

    }

    public  void mostrar(){
        Toast.makeText(getActivity().getApplicationContext(),"Funcionalidad Activa",Toast.LENGTH_LONG).show();
    }
}
