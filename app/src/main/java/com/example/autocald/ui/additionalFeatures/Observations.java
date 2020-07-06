package com.example.autocald.ui.additionalFeatures;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.autocald.R;

import java.util.Objects;

public class Observations extends Fragment {

    private int numberOfLines=0;
    private LinearLayout linearLayoutDecisions;
    private SharedPreferences dataForm;
    private String[] observations;

    public Observations() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createForm();
        return inflater.inflate(R.layout.fragment_observations, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDataForm();
        linearLayoutDecisions = view.findViewById(R.id.linearLayoutDecisions);
        if(observations!=null){
            for(int i=0; i<observations.length; i++){
                addObservation(observations[i]);
            }
        }else{
           addObservation();
        }

        Button buttonAddObservation = view.findViewById(R.id.add_observation);
        buttonAddObservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addObservation();
            }
        });

        Button buttonRemoveObservation = view.findViewById(R.id.remove_observation);
        buttonRemoveObservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOfLines==1){
                    Toast.makeText(getContext(), "No hay mas observaciones que remover", Toast.LENGTH_SHORT).show();
                }else{
                    removeObservation();
                }
            }
        });

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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addObservation() {
        EditText editText = new EditText(getContext());
        editText.setId(numberOfLines);
        editText.setHint("observación " +(numberOfLines+1));
        linearLayoutDecisions.addView(editText);
        addTextChangedListener(editText);
        numberOfLines++;
    }

    private void addObservation(String observation){
        EditText editText = new EditText(getContext());
        editText.setId(numberOfLines);
        if(observation.equals("")){
            editText.setHint("observación " +(numberOfLines+1));
        }else{
            editText.setText(observation);
        }
        linearLayoutDecisions.addView(editText);
        addTextChangedListener(editText);
        numberOfLines++;
    }

    private void removeObservation(){
        numberOfLines--;
        linearLayoutDecisions.removeViewAt(numberOfLines);
        removeDataFrom(numberOfLines);
    }

    private void createForm(){
        dataForm = requireActivity().getSharedPreferences("M14observations", Context.MODE_PRIVATE);
    }

    private void setDataForm(String text, int id){
        SharedPreferences.Editor editor= dataForm.edit();
        editor.putBoolean("generateChange", true);
        editor.putInt("numberChange", numberOfLines);
        editor.putString("observationNumber"+id, text);
        editor.apply();
    }

    private void removeDataFrom(int id){
        SharedPreferences.Editor editor= dataForm.edit();
        editor.putInt("numberChange", numberOfLines);
        editor.remove("observationNumber"+id);
        editor.apply();
    }

    private void getDataForm(){
        dataForm = requireActivity().getSharedPreferences("M14observations", Context.MODE_PRIVATE);
        boolean generateChange = dataForm.getBoolean("generateChange", false);
        if(generateChange){
            int numberChange = dataForm.getInt("numberChange", 1);
            observations = new String[numberChange];
            for (int i=0; i<numberChange; i++) {
                observations[i] = dataForm.getString("observationNumber"+i, "");
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void resetForm(){
        if(numberOfLines>0){
            do{
                removeObservation();
            }while(numberOfLines!=0);
        }
        addObservation();
        SharedPreferences.Editor editor = dataForm.edit();
        editor.clear();
        editor.apply();
    }

}
