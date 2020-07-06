package com.example.autocald.ui.ending;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.autocald.MainActivity;
import com.example.autocald.R;
import com.example.autocald.utilities.CaptureBitmapView;

public class DigitalSignature extends Fragment {

    private CaptureBitmapView mSig;
    private Button btnClear;
    private Button btnSave;

    public DigitalSignature() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_digital_signature, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnClear = view.findViewById(R.id.btnClear);
        btnSave = view.findViewById(R.id.btnSave);

        LinearLayout mContent = (LinearLayout) view.findViewById(R.id.signLayout);
        mSig = new CaptureBitmapView(getActivity().getApplicationContext(), null);
        mContent.addView(mSig, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap signature = mSig.getBitmap();
                ((MainActivity)getActivity()).removeFragment(signature);
                }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSig.ClearCanvas();
            }
        });
    }
}
