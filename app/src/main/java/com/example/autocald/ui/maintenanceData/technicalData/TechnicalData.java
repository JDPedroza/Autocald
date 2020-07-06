package com.example.autocald.ui.maintenanceData.technicalData;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
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
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.autocald.R;

import java.util.Calendar;

public class TechnicalData extends Fragment {

    private TextView Time_start;
    private TextView Time_end;
    private EditText editText_name_technical;
    private SharedPreferences dataForm;

    public TechnicalData() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createDataForm();
        return inflater.inflate(R.layout.fragment_technical_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText_name_technical=view.findViewById(R.id.editText_name_technical);
        editText_name_technical.addTextChangedListener(new TextWatcher() {
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

        Time_start=view.findViewById(R.id.Time_start);
        Time_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c= Calendar.getInstance();
                int hora = c.get(Calendar.HOUR_OF_DAY);
                int minutos = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Time_start.setText(hourOfDay+":"+minute);
                        setDataForm(2);
                    }
                },hora,minutos,false);
                timePickerDialog.show();
            }
        });
        Time_end=view.findViewById(R.id.Time_end);
        Time_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c= Calendar.getInstance();
                int hora = c.get(Calendar.HOUR_OF_DAY);
                int minutos = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Time_end.setText(hourOfDay+":"+minute);
                        setDataForm(3);
                    }
                },hora,minutos,false);
                timePickerDialog.show();
            }
        });
        getDataForm();
    }

    private void createDataForm(){
        dataForm = requireActivity().getSharedPreferences("M3dataTechnical", Context.MODE_PRIVATE);
    }

    private void setDataForm(int id){
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor= dataForm.edit();
        if(id==1){
            editor.putString("nameTechnical", editText_name_technical.getText().toString());
        }else if(id==2){
            editor.putString("timeStart", Time_start.getText().toString());
        }else{
            editor.putString("timeEnd", Time_end.getText().toString());
        }
        editor.apply();
    }

    private void getDataForm(){
        dataForm = requireActivity().getSharedPreferences("M3dataTechnical", Context.MODE_PRIVATE);
        editText_name_technical.setText(dataForm.getString("nameTechnical", ""));
        Time_start.setText(dataForm.getString("timeStart", "0:00"));
        Time_end.setText(dataForm.getString("timeEnd", "0:00"));
    }
    @SuppressLint("SetTextI18n")
    public void resetForm(){
        editText_name_technical.setText("");
        Time_start.setText("0:00");
        Time_end.setText("0:00");
        SharedPreferences.Editor editor = dataForm.edit();
        editor.clear();
        editor.apply();
    }
    /*
    private void timeTotal(){
        Date fecha1 = ...;
        Date fecha2 = ...;
        long milisegundos = fecha1.getTime() - fecha2.getTime();
        Calendar calendario = Calendar.getInstance();
        calendario.setTimeInMillis(milisegundos);

        int horas = calendario.get(Calendar.HOUR);
        int minutos = calendario.get(Calendar.MINUTE);
        int segundos = calendario.get(Calendar.SECOND);
    }
     */
}
