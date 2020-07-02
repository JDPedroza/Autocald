package com.example.autocald.ui.maintenanceData.computerData;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.autocald.R;


public class ComputerData extends Fragment {

    public ComputerData() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_computer_data, container, false);
    }
}
