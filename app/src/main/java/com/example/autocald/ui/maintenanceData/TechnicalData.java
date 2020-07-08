package com.example.autocald.ui.maintenanceData;

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
import java.util.Date;

public class TechnicalData extends Fragment {

    private int hour_start;
    private int minutes_start;
    private int hour_end;
    private int minutes_end;
    private TextView Time_start;
    private TextView Time_end;
    private TextView Time_total;
    private EditText editText_name_technical;
    private SharedPreferences dataForm;
    private boolean time_start;
    private boolean time_end;

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
                        hour_start=hourOfDay;
                        minutes_start=minute;
                        Time_start.setText(hourOfDay+":"+minute);
                        setDataForm(2);
                        time_start=true;
                        timeTotal();
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
                        hour_end=hourOfDay;
                        minutes_end=minute;
                        Time_end.setText(hourOfDay+":"+minute);
                        setDataForm(3);
                        time_end=true;
                        timeTotal();
                    }
                },hora,minutos,false);
                timePickerDialog.show();
            }
        });
        Time_total = view.findViewById(R.id.textView_time_total_result);
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
        }else if(id==3){
            editor.putString("timeEnd", Time_end.getText().toString());
        }else{
            editor.putString("timeTotal", Time_total.getText().toString());
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
    @SuppressLint("SetTextI18n")
    private void timeTotal(){
        if(time_start&&time_end){
            Date fecha1 = new Date();
            Date fecha2 = new Date();
            fecha1.setHours(hour_start);
            fecha1.setMinutes(minutes_start);
            fecha2.setHours(hour_end);
            fecha2.setMinutes(minutes_end);

            long diff = fecha2.getTime() - fecha1.getTime();

            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000);

            Time_total.setText(diffHours+":"+diffMinutes);
        }
        setDataForm(4);
    }
}
