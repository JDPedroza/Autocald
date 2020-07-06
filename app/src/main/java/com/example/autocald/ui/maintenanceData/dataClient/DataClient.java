package com.example.autocald.ui.maintenanceData.dataClient;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.autocald.R;

import java.util.Calendar;

public class DataClient extends Fragment {

    private TextView textViewDateEditable;
    private Spinner spinner;
    private SharedPreferences dataForm;
    private EditText serviceControl;
    private EditText editTextTypesServices;
    private EditText editTextClient;
    private EditText editTextCapacity;
    private EditText editTextModel;

    public DataClient() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createDataForm();
        return inflater.inflate(R.layout.fragment_data_client, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        serviceControl = view.findViewById(R.id.serviceControl);
        serviceControl.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDataForm(1);
            }
        });

        textViewDateEditable = view.findViewById(R.id.textView_date_editable);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int mount = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        textViewDateEditable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String date = day+"/"+(month+1)+"/"+year;
                        textViewDateEditable.setText(date);
                        setDataForm(2);
                    }
                }, year,mount,day);
                datePickerDialog.show();
            }
        });

        spinner = view.findViewById(R.id.spinnerTypesServices);
        //leemos el array
        String [] observaciones={"Mant General y Correctivo", "Reparación", "Otro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_item_sliders, observaciones);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                int id = arg2+3;
                if(arg2==0){
                    editTextTypesServices.setEnabled(false);
                    editTextTypesServices.setText("Mant General y Correctivo");
                    setDataForm(id);
                }else if(arg2==1){
                    editTextTypesServices.setEnabled(false);
                    editTextTypesServices.setText("Reparación");
                    setDataForm(id);
                }else{
                    editTextTypesServices.setEnabled(true);
                    if(editTextTypesServices.getText().toString().equals("Mant General y Correctivo")
                            || editTextTypesServices.getText().toString().equals("Reparación")
                    ){
                        editTextTypesServices.setText("");
                    }
                    setDataForm(id);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        editTextTypesServices = view.findViewById(R.id.editTextTypesServices);
        editTextTypesServices.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(editTextTypesServices.getText().length() >= 0
                        && spinner.getSelectedItem().toString().equals("Otra")
                        && !editTextTypesServices.getText().toString().equals("")) {
                    setDataForm(6);
                }
            }
        });

        editTextClient = view.findViewById(R.id.editTextClient);
        editTextClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDataForm(7);
            }
        });

        editTextCapacity = view.findViewById(R.id.editTextCapacity);
        editTextCapacity.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDataForm(8);
            }
        });

        editTextModel = view.findViewById(R.id.editTextModel);
        editTextModel.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDataForm(9);
            }
        });
        getDataFrom();
    }

    private void createDataForm(){
        dataForm = getActivity().getSharedPreferences("M1DataClient", Context.MODE_PRIVATE);
    }

    private void setDataForm(int i){
        SharedPreferences.Editor editor= dataForm.edit();
        if(i==1){
            editor.putString("serviceControl", serviceControl.getText().toString());
        }else if(i==2){
            editor.putString("serviceDate", textViewDateEditable.getText().toString());
        }else if(i==3){
            editor.putInt("serviceType", 0);
        }else if(i==4){
            editor.putInt("serviceType", 1);
        }else if(i==5){
            editor.putInt("serviceType", 2);
        }else if(i==6){
            editor.putString("serviceTypeText", editTextTypesServices.getText().toString());
        }else if(i==7){
            editor.putString("serviceClient", editTextClient.getText().toString());
        }else if(i==8){
            editor.putString("serviceCapacity", editTextCapacity.getText().toString());
        }else{
            editor.putString("serviceModel", editTextModel.getText().toString());
        }

        editor.apply();
    }

    private void getDataFrom(){
        dataForm = getActivity().getSharedPreferences("M1DataClient", Context.MODE_PRIVATE);
        serviceControl.setText(dataForm.getString("serviceControl", ""));
        textViewDateEditable.setText(dataForm.getString("serviceDate", "00/00/00"));
        spinner.setSelection(dataForm.getInt("serviceType", 0));
        editTextTypesServices.setText(dataForm.getString("serviceTypeText", "Mant General y Correctivo"));
        editTextClient.setText(dataForm.getString("serviceClient", ""));
        editTextCapacity.setText(dataForm.getString("serviceCapacity", ""));
        editTextModel.setText(dataForm.getString("serviceModel", ""));
    }

    @SuppressLint("SetTextI18n")
    public void resetForm(){
        serviceControl.setText("");
        textViewDateEditable.setText("00/00/00");
        spinner.setSelection(0);
        editTextTypesServices.setText("Mant General y Correctivo");
        editTextClient.setText("");
        editTextCapacity.setText("");
        editTextModel.setText("");
        SharedPreferences.Editor editor = dataForm.edit();
        editor.clear();
        editor.apply();
    }
}
