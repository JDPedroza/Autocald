package com.example.autocald.ui.maintenanceData.computerData;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.autocald.R;


public class ComputerData extends Fragment {

    private EditText editText_Vapor_Pressure_Value;
    private EditText editText_Vapor_Pressure_Observation;
    private EditText editText_Air_Atomization_Value;
    private EditText editText_Air_Atomization_Observation;
    private EditText editText_Steam_Atomization_Value;
    private EditText editText_Steam_Atomization_Observation;
    private EditText editText_Fuel_Temperature_Value;
    private EditText editText_Fuel_Temperature_Observation;
    private EditText editText_Fuel_Pressure_Value;
    private EditText editText_Fuel_Pressure_Observation;
    private EditText editText_Line_Gas_Pressure_Value;
    private EditText editText_Line_Gas_Pressure_Observation;
    private EditText editText_Gas_Train_Pressure_Value;
    private EditText editText_Gas_Train_Pressure_Observation;
    private EditText editText_Gas_Temperature_Value;
    private EditText editText_Gas_Temperature_Observation;
    private EditText editText_Water_Temperature_Value;
    private EditText editText_Water_Temperature_Observation;
    private EditText editText_Boiler_Level_Value;
    private EditText editText_Boiler_Level_Observation;
    private EditText editText_Condensate_Tank_Level_Value;
    private EditText editText_Condensate_Tank_Level_Observation;
    private EditText editText_Fuel_Tank_Level_Value;
    private EditText editText_Fuel_Tank_Level_Observation;
    private SharedPreferences dataForm;

    public ComputerData() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createDataForm();
        return inflater.inflate(R.layout.fragment_computer_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText_Vapor_Pressure_Value = view.findViewById(R.id.editText_Vapor_Pressure_Value);
        addTextChangedListener(editText_Vapor_Pressure_Value);
        editText_Vapor_Pressure_Observation = view.findViewById(R.id.editText_Vapor_Pressure_Observation);
        addTextChangedListener(editText_Vapor_Pressure_Observation);
        editText_Air_Atomization_Value = view.findViewById(R.id.editText_Air_Atomization_Value);
        addTextChangedListener(editText_Air_Atomization_Value);
        editText_Air_Atomization_Observation = view.findViewById(R.id.editText_Air_Atomization_Observation);
        addTextChangedListener(editText_Air_Atomization_Observation);
        editText_Steam_Atomization_Value = view.findViewById(R.id.editText_Steam_Atomization_Value);
        addTextChangedListener(editText_Steam_Atomization_Value);
        editText_Steam_Atomization_Observation = view.findViewById(R.id.editText_Steam_Atomization_Observation);
        addTextChangedListener(editText_Steam_Atomization_Observation);
        editText_Fuel_Temperature_Value = view.findViewById(R.id.editText_Fuel_Temperature_Value);
        addTextChangedListener(editText_Fuel_Temperature_Value);
        editText_Fuel_Temperature_Observation = view.findViewById(R.id.editText_Fuel_Temperature_Observation);
        addTextChangedListener(editText_Fuel_Temperature_Observation);
        editText_Fuel_Pressure_Value = view.findViewById(R.id.editText_Fuel_Pressure_Value);
        addTextChangedListener(editText_Fuel_Pressure_Value);
        editText_Fuel_Pressure_Observation = view.findViewById(R.id.editText_Fuel_Pressure_Observation);
        addTextChangedListener(editText_Fuel_Pressure_Observation);
        editText_Line_Gas_Pressure_Value = view.findViewById(R.id.editText_Line_Gas_Pressure_Value);
        addTextChangedListener(editText_Line_Gas_Pressure_Value);
        editText_Line_Gas_Pressure_Observation = view.findViewById(R.id.editText_Line_Gas_Pressure_Observation);
        addTextChangedListener(editText_Line_Gas_Pressure_Observation);
        editText_Gas_Train_Pressure_Value = view.findViewById(R.id.editText_Gas_Train_Pressure_Value);
        addTextChangedListener(editText_Gas_Train_Pressure_Value);
        editText_Gas_Train_Pressure_Observation = view.findViewById(R.id.editText_Gas_Train_Pressure_Observation);
        addTextChangedListener(editText_Gas_Train_Pressure_Observation);
        editText_Gas_Temperature_Value = view.findViewById(R.id.editText_Gas_Temperature_Value);
        addTextChangedListener(editText_Gas_Temperature_Value);
        editText_Gas_Temperature_Observation = view.findViewById(R.id.editText_Gas_Temperature_Observation);
        addTextChangedListener(editText_Gas_Temperature_Observation);
        editText_Water_Temperature_Value = view.findViewById(R.id.editText_Water_Temperature_Value);
        addTextChangedListener(editText_Water_Temperature_Value);
        editText_Water_Temperature_Observation = view.findViewById(R.id.editText_Water_Temperature_Observation);
        addTextChangedListener(editText_Water_Temperature_Observation);
        editText_Boiler_Level_Value = view.findViewById(R.id.editText_Boiler_Level_Value);
        addTextChangedListener(editText_Boiler_Level_Value);
        editText_Boiler_Level_Observation = view.findViewById(R.id.editText_Boiler_Level_Observation);
        addTextChangedListener(editText_Boiler_Level_Observation);
        editText_Condensate_Tank_Level_Value = view.findViewById(R.id.editText_Condensate_Tank_Level_Value);
        addTextChangedListener(editText_Condensate_Tank_Level_Value);
        editText_Condensate_Tank_Level_Observation = view.findViewById(R.id.editText_Condensate_Tank_Level_Observation);
        addTextChangedListener(editText_Condensate_Tank_Level_Observation);
        editText_Fuel_Tank_Level_Value = view.findViewById(R.id.editText_Fuel_Tank_Level_Value);
        addTextChangedListener(editText_Fuel_Tank_Level_Value);
        editText_Fuel_Tank_Level_Observation = view.findViewById(R.id.editText_Fuel_Tank_Level_Observation);
        addTextChangedListener(editText_Fuel_Tank_Level_Observation);
        getDataFrom();
    }

    private void addTextChangedListener(final EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(editText.getText().length() >= 0) {
                    setDataForm(editText.getText().toString(), editText.getId());
                }
            }
        });
    }

    private void setDataForm(String text, int id) {
        SharedPreferences.Editor editor= dataForm.edit();
        if(id==editText_Vapor_Pressure_Value.getId()){
            editor.putString("editText_Vapor_Pressure_Value", text);
        }else if(id==editText_Vapor_Pressure_Observation.getId()){
            editor.putString("editText_Vapor_Pressure_Observation", text);
        }else if(id==editText_Air_Atomization_Value.getId()){
            editor.putString("editText_Air_Atomization_Value", text);
        }else if(id==editText_Air_Atomization_Observation.getId()){
            editor.putString("editText_Air_Atomization_Observation", text);
        }else if(id==editText_Steam_Atomization_Value.getId()){
            editor.putString("editText_Steam_Atomization_Value", text);
        }else if(id==editText_Steam_Atomization_Observation.getId()){
            editor.putString("editText_Steam_Atomization_Observation", text);
        }else if(id==editText_Fuel_Temperature_Value.getId()){
            editor.putString("editText_Fuel_Temperature_Value", text);
        }else if(id==editText_Fuel_Temperature_Observation.getId()){
            editor.putString("editText_Fuel_Temperature_Observation", text);
        }else if(id==editText_Fuel_Pressure_Value.getId()){
            editor.putString("editText_Fuel_Pressure_Value", text);
        }else if(id==editText_Fuel_Pressure_Observation.getId()){
            editor.putString("editText_Fuel_Pressure_Observation", text);
        }else if(id==editText_Line_Gas_Pressure_Value.getId()){
            editor.putString("editText_Line_Gas_Pressure_Value", text);
        }else if(id==editText_Line_Gas_Pressure_Observation.getId()){
            editor.putString("editText_Line_Gas_Pressure_Observation", text);
        }else if(id==editText_Gas_Train_Pressure_Value.getId()){
            editor.putString("editText_Gas_Train_Pressure_Value", text);
        }else if(id==editText_Gas_Train_Pressure_Observation.getId()){
            editor.putString("editText_Gas_Train_Pressure_Observation", text);
        }else if(id==editText_Gas_Temperature_Value.getId()){
            editor.putString("editText_Gas_Temperature_Value", text);
        }else if(id==editText_Gas_Temperature_Observation.getId()){
            editor.putString("editText_Gas_Temperature_Observation", text);
        }if(id==editText_Water_Temperature_Value.getId()){
            editor.putString("editText_Water_Temperature_Value", text);
        }else if(id==editText_Water_Temperature_Observation.getId()){
            editor.putString("editText_Water_Temperature_Observation", text);
        }else if(id==editText_Boiler_Level_Value.getId()){
            editor.putString("editText_Boiler_Level_Value", text);
        }else if(id==editText_Boiler_Level_Observation.getId()){
            editor.putString("editText_Boiler_Level_Observation", text);
        }else if(id==editText_Condensate_Tank_Level_Value.getId()){
            editor.putString("editText_Condensate_Tank_Level_Value", text);
        }else if(id==editText_Condensate_Tank_Level_Observation.getId()){
            editor.putString("editText_Condensate_Tank_Level_Observation", text);
        }else if(id==editText_Fuel_Tank_Level_Value.getId()){
            editor.putString("editText_Fuel_Tank_Level_Value", text);
        }else{
            editor.putString("editText_Fuel_Tank_Level_Observation", text);
        }
        editor.apply();
    }

    private void createDataForm(){
        dataForm = requireActivity().getSharedPreferences("M2DataComputer", Context.MODE_PRIVATE);
    }

    private void getDataFrom(){
        editText_Vapor_Pressure_Value.setText(dataForm.getString("editText_Vapor_Pressure_Value", ""));
        editText_Vapor_Pressure_Observation.setText(dataForm.getString("editText_Vapor_Pressure_Observation", ""));
        editText_Air_Atomization_Value.setText(dataForm.getString("editText_Air_Atomization_Value", ""));
        editText_Air_Atomization_Observation.setText(dataForm.getString("editText_Air_Atomization_Observation", ""));
        editText_Steam_Atomization_Value.setText(dataForm.getString("editText_Steam_Atomization_Value", ""));
        editText_Steam_Atomization_Observation.setText(dataForm.getString("editText_Steam_Atomization_Observation", ""));
        editText_Fuel_Temperature_Value.setText(dataForm.getString("editText_Fuel_Temperature_Value", ""));
        editText_Fuel_Temperature_Observation.setText(dataForm.getString("editText_Fuel_Temperature_Observation", ""));
        editText_Fuel_Pressure_Value.setText(dataForm.getString("editText_Fuel_Pressure_Value", ""));
        editText_Fuel_Pressure_Observation.setText(dataForm.getString("editText_Fuel_Pressure_Observation", ""));
        editText_Line_Gas_Pressure_Value.setText(dataForm.getString("editText_Line_Gas_Pressure_Value", ""));
        editText_Line_Gas_Pressure_Observation.setText(dataForm.getString("editText_Line_Gas_Pressure_Observation", ""));
        editText_Gas_Train_Pressure_Value.setText(dataForm.getString("editText_Gas_Train_Pressure_Value", ""));
        editText_Gas_Train_Pressure_Observation.setText(dataForm.getString("editText_Gas_Train_Pressure_Observation", ""));
        editText_Gas_Temperature_Value.setText(dataForm.getString("editText_Gas_Temperature_Value", ""));
        editText_Gas_Temperature_Observation.setText(dataForm.getString("editText_Gas_Temperature_Observation", ""));
        editText_Water_Temperature_Value.setText(dataForm.getString("editText_Water_Temperature_Value", ""));
        editText_Water_Temperature_Observation.setText(dataForm.getString("editText_Water_Temperature_Observation", ""));
        editText_Boiler_Level_Value.setText(dataForm.getString("editText_Boiler_Level_Value", ""));
        editText_Boiler_Level_Observation.setText(dataForm.getString("editText_Boiler_Level_Observation", ""));
        editText_Condensate_Tank_Level_Value.setText(dataForm.getString("editText_Condensate_Tank_Level_Value", ""));
        editText_Condensate_Tank_Level_Observation.setText(dataForm.getString("editText_Condensate_Tank_Level_Observation", ""));
        editText_Fuel_Tank_Level_Value.setText(dataForm.getString("editText_Fuel_Tank_Level_Value", ""));
        editText_Fuel_Tank_Level_Observation.setText(dataForm.getString("editText_Fuel_Tank_Level_Observation", ""));
    }

    public void resetForm(){
        editText_Vapor_Pressure_Value.setText("");
        editText_Vapor_Pressure_Observation.setText("");
        editText_Air_Atomization_Value.setText("");
        editText_Air_Atomization_Observation.setText("");
        editText_Steam_Atomization_Value.setText("");
        editText_Steam_Atomization_Observation.setText("");
        editText_Fuel_Temperature_Value.setText("");
        editText_Fuel_Temperature_Observation.setText("");
        editText_Fuel_Pressure_Value.setText("");
        editText_Fuel_Pressure_Observation.setText("");
        editText_Line_Gas_Pressure_Value.setText("");
        editText_Line_Gas_Pressure_Observation.setText("");
        editText_Gas_Train_Pressure_Value.setText("");
        editText_Gas_Train_Pressure_Observation.setText("");
        editText_Gas_Temperature_Value.setText("");
        editText_Gas_Temperature_Observation.setText("");
        editText_Water_Temperature_Value.setText("");
        editText_Water_Temperature_Observation.setText("");
        editText_Boiler_Level_Value.setText("");
        editText_Boiler_Level_Observation.setText("");
        editText_Condensate_Tank_Level_Value.setText("");
        editText_Condensate_Tank_Level_Observation.setText("");
        editText_Fuel_Tank_Level_Value.setText("");
        editText_Fuel_Tank_Level_Observation.setText("");
        SharedPreferences.Editor editor = dataForm.edit();
        editor.clear();
        editor.apply();
    }
}
