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

public class Recommendations extends Fragment {


    private SharedPreferences dataForm;
    private String[] recommendations;
    private LinearLayout linearLayoutDecisions;
    private int numberOfLines=0;

    public Recommendations() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createForm();
        return inflater.inflate(R.layout.fragment_recommendations, container, false);
    }

    private void createForm(){
        dataForm = getActivity().getSharedPreferences("M15Recommendations", Context.MODE_PRIVATE);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDataForm();
        linearLayoutDecisions = view.findViewById(R.id.linearLayoutDecisions);
        if(recommendations!=null){
            for(int i=0; i<recommendations.length; i++){
                addRecommendation(recommendations[i]);
            }
        }else{
            addRecommendation();
        }
        Button buttonAddRecommendation = view.findViewById(R.id.add_recommendation);
        buttonAddRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecommendation();
            }
        });
        Button buttonRemoveRecommendation = view.findViewById(R.id.remove_recommendation);
        buttonRemoveRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOfLines==1){
                    Toast.makeText(getContext(), "No hay mas recomendaciones que remover", Toast.LENGTH_SHORT).show();
                }else{
                    removeObservation();
                }
            }
        });
    }

    private void getDataForm(){
        dataForm = getActivity().getSharedPreferences("M15Recommendations", Context.MODE_PRIVATE);
        boolean generateChange = dataForm.getBoolean("generateChange", false);
        if(generateChange){
            int numberChange = dataForm.getInt("numberChange", 1);
            recommendations = new String[numberChange];
            for (int i=0; i<numberChange; i++) {
                recommendations[i] = dataForm.getString("recommendationNumber"+i, "");
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void addRecommendation() {
        EditText editText = new EditText(getContext());
        editText.setId(numberOfLines);
        editText.setHint("recomendación " +(numberOfLines+1));
        linearLayoutDecisions.addView(editText);
        addTextChangedListener(editText);
        numberOfLines++;
    }

    private void addRecommendation(String observation){
        EditText editText = new EditText(getContext());
        editText.setId(numberOfLines);
        if(observation.equals("")){
            editText.setHint("recomendación " +(numberOfLines+1));
        }else{
            editText.setText(observation);
        }
        linearLayoutDecisions.addView(editText);
        addTextChangedListener(editText);
        numberOfLines++;
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

    private void setDataForm(String text, int id){
        SharedPreferences.Editor editor= dataForm.edit();
        editor.putBoolean("generateChange", true);
        editor.putInt("numberChange", numberOfLines);
        editor.putString("recommendationNumber"+id, text);
        editor.apply();
    }

    private void removeObservation(){
        numberOfLines--;
        linearLayoutDecisions.removeViewAt(numberOfLines);
        removeDataFrom(numberOfLines);
    }

    private void removeDataFrom(int id){
        SharedPreferences.Editor editor= dataForm.edit();
        editor.putInt("numberChange", numberOfLines);
        editor.remove("recommendationNumber"+id);
        editor.apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void resetForm(){
        if(numberOfLines>0){
            do{
                removeObservation();
            }while(numberOfLines!=0);
        }
        addRecommendation();
        SharedPreferences.Editor editor = dataForm.edit();
        editor.clear();
        editor.apply();
    }

}
