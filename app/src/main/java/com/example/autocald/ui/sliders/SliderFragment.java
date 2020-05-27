package com.example.autocald.ui.sliders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    TextView title, number_photos;
    Button btnB, btnR, btnM;
    Spinner spinnerObservations;
    EditText observation;
    SharedPreferences dataForm;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //creamos la vista
        view = inflater.inflate(R.layout.fragment_slider, container, false);

        //llamamos los elementos creados en el layaout para ENVIAR PARAMETROS.
        title = view.findViewById(R.id.txtTitle);
        spinnerObservations = view.findViewById(R.id.spinner);

        //leemos el array
        String [] observaciones={"NA", "Ok", "Otra", "Se cambio", "Se reviso"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item_sliders, observaciones);

        createForm(getArguments().getString("dataForm"));

        if(getArguments()!=null){
            title.setText(getArguments().getString("title"));
            spinnerObservations.setAdapter(adapter);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ////llamamos los elementos creados en el layaout para EDITAR FUNCIONES
        //State
        btnB = view.findViewById(R.id.btnB);
        btnR = view.findViewById(R.id.btnR);
        btnM = view.findViewById(R.id.btnM);

        //Observations
        spinnerObservations = view.findViewById(R.id.spinner);
        observation = view.findViewById(R.id.editText);
        observation.setEnabled(false);

        //Photo
        number_photos = view.findViewById(R.id.numberPhotos);

        btnB.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btnB.setBackgroundResource(R.drawable.style_btn_b);
                btnB.setTextColor(getResources().getColor(R.color.btnGoodDark));
                btnR.setBackgroundResource(R.drawable.style_btn_unselected);
                btnR.setTextColor(getResources().getColor(R.color.btnUnselected));
                btnM.setBackgroundResource(R.drawable.style_btn_unselected);
                btnM.setTextColor(getResources().getColor(R.color.btnUnselected));
                setForm(1);
            }
        });

        btnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnB.setBackgroundResource(R.drawable.style_btn_unselected);
                btnB.setTextColor(getResources().getColor(R.color.btnUnselected));
                btnR.setBackgroundResource(R.drawable.style_btn_r);
                btnR.setTextColor(getResources().getColor(R.color.btnRegularDark));
                btnM.setBackgroundResource(R.drawable.style_btn_unselected);
                btnM.setTextColor(getResources().getColor(R.color.btnUnselected));
                setForm(2);
            }
        });

        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnB.setBackgroundResource(R.drawable.style_btn_unselected);
                btnB.setTextColor(getResources().getColor(R.color.btnUnselected));
                btnR.setBackgroundResource(R.drawable.style_btn_unselected);
                btnR.setTextColor(getResources().getColor(R.color.btnUnselected));
                btnM.setBackgroundResource(R.drawable.style_btn_m);
                btnM.setTextColor(getResources().getColor(R.color.btnBadDark));
                setForm(3);
            }
        });

        spinnerObservations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int id = arg2+4;
                if(arg2==0){
                    observation.setEnabled(false);
                    observation.setText("NA");
                    setForm(id);
                }else if(arg2==1){
                    observation.setEnabled(false);
                    observation.setText("Ok");
                    setForm(id);
                }else if(arg2==2){
                    observation.setEnabled(true);

                    if(observation.getText().toString().equals("NA")
                            || observation.getText().toString().equals("Ok")
                            || observation.getText().toString().equals("Se cambio")
                            || observation.getText().toString().equals("Se reviso")
                    ){
                        observation.setText("");
                    }
                    setForm(id);
                }else if(arg2==3){
                    observation.setEnabled(false);
                    observation.setText("Se cambio");
                    setForm(id);
                }else{
                    observation.setEnabled(false);
                    observation.setText("Se reviso");
                    setForm(id);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        observation.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(observation.getText().length() >= 0
                        && spinnerObservations.getSelectedItem().toString().equals("Otra")
                        && !observation.getText().toString().equals("")) {
                    setForm(9);
                }
            }
        });

        getForm(getArguments().getString("dataForm"));


    }

    private void createForm(String name){
        dataForm = getActivity().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    private void setForm(int id){

        SharedPreferences.Editor editor= dataForm.edit();

        //setForm Botornes
        if(id==1){
            editor.putBoolean("dataBtnB", true);
            editor.putBoolean("dataBtnR", false);
            editor.putBoolean("dataBtnM", false);
        }else if(id==2){
            editor.putBoolean("dataBtnB", false);
            editor.putBoolean("dataBtnR", true);
            editor.putBoolean("dataBtnM", false);
        }else if(id==3){
            editor.putBoolean("dataBtnB", false);
            editor.putBoolean("dataBtnR", false);
            editor.putBoolean("dataBtnM", true);
        }

        //setForm Spinner
        if(id==4){
            editor.putInt("dataSpinnerItem", 0);
        }else if(id==5){
            editor.putInt("dataSpinnerItem", 1);
        }else if(id==6){
            editor.putInt("dataSpinnerItem", 2);
        }else if(id==7){
            editor.putInt("dataSpinnerItem", 3);
        }else if(id==8){
            editor.putInt("dataSpinnerItem", 4);
        }

        //setForm Observation
        if(id==9){
            String dataObservation = observation.getText().toString();
            editor.putString("dataObservationText", dataObservation);
        }

        editor.apply();
    }

    private void getForm(String name){

        dataForm = getActivity().getSharedPreferences(name, Context.MODE_PRIVATE);

        boolean btnBPress = dataForm.getBoolean("dataBtnB", false);
        boolean btnRPress = dataForm.getBoolean("dataBtnR", false);
        boolean btnMPress = dataForm.getBoolean("dataBtnM", false);

        //validación botones
        if(btnBPress){
            btnB.setBackgroundResource(R.drawable.style_btn_b);
            btnB.setTextColor(getResources().getColor(R.color.btnGoodDark));
            btnR.setBackgroundResource(R.drawable.style_btn_unselected);
            btnR.setTextColor(getResources().getColor(R.color.btnUnselected));
            btnM.setBackgroundResource(R.drawable.style_btn_unselected);
            btnM.setTextColor(getResources().getColor(R.color.btnUnselected));
        }else if(btnRPress){
            btnB.setBackgroundResource(R.drawable.style_btn_unselected);
            btnB.setTextColor(getResources().getColor(R.color.btnUnselected));
            btnR.setBackgroundResource(R.drawable.style_btn_r);
            btnR.setTextColor(getResources().getColor(R.color.btnRegularDark));
            btnM.setBackgroundResource(R.drawable.style_btn_unselected);
            btnM.setTextColor(getResources().getColor(R.color.btnUnselected));
        }else if(btnMPress){
            btnB.setBackgroundResource(R.drawable.style_btn_unselected);
            btnB.setTextColor(getResources().getColor(R.color.btnUnselected));
            btnR.setBackgroundResource(R.drawable.style_btn_unselected);
            btnR.setTextColor(getResources().getColor(R.color.btnUnselected));
            btnM.setBackgroundResource(R.drawable.style_btn_m);
            btnM.setTextColor(getResources().getColor(R.color.btnBadDark));
        }else{
            btnB.setBackgroundResource(R.drawable.style_btn_unselected);
            btnB.setTextColor(getResources().getColor(R.color.btnUnselected));
            btnR.setBackgroundResource(R.drawable.style_btn_unselected);
            btnR.setTextColor(getResources().getColor(R.color.btnUnselected));
            btnM.setBackgroundResource(R.drawable.style_btn_unselected);
            btnM.setTextColor(getResources().getColor(R.color.btnUnselected));
        }

        //validacion spinner
        int spinnerItem = dataForm.getInt("dataSpinnerItem", 0);
        spinnerObservations.setSelection(spinnerItem);

        //validacion observacion
        String dataObservationText = dataForm.getString("dataObservationText", "NA");
        observation.setText(dataObservationText);

    }

    public void resetFrom(){
        btnB.setBackgroundResource(R.drawable.style_btn_unselected);
        btnB.setTextColor(getResources().getColor(R.color.btnUnselected));
        btnR.setBackgroundResource(R.drawable.style_btn_unselected);
        btnR.setTextColor(getResources().getColor(R.color.btnUnselected));
        btnM.setBackgroundResource(R.drawable.style_btn_unselected);
        btnM.setTextColor(getResources().getColor(R.color.btnUnselected));

        spinnerObservations.setSelection(0);

        observation.setText("NA");
    }

    /*
    Debugeador :v
    public  void mostrar(String i){
        Toast.makeText(getActivity().getApplicationContext(),"Funcionalidad Activa " +i,Toast.LENGTH_LONG).show();
    }

     */
}
