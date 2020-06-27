package com.example.autocald.ui.documentGeneration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.autocald.BuildConfig;
import com.example.autocald.MainActivity;
import com.example.autocald.R;
import com.example.autocald.utilities.TemplatePDF;

import java.io.IOException;

public class DocumentGeneration extends Fragment {

    private TemplatePDF templatePDF;
    private Button btnGenerate;
    private Button btnSend;
    private Bitmap signature;

    public DocumentGeneration() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_document_generation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnGenerate = view.findViewById(R.id.btnGenerate);
        btnSend = view.findViewById(R.id.btnSend);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                try {
                    templatePDF = new TemplatePDF(getActivity().getApplicationContext(), signature);
                    //getActivity().onBackPressed();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appSendPDF();
            }
        });
    }

    public void appSendPDF(){
        String[] mailto = {""};
        Uri uri = FileProvider.getUriForFile(getActivity().getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", templatePDF.getFile());
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Calc PDF Report");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,"Hi PDF is attached in this mail. ");
        emailIntent.setType("application/pdf");
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(emailIntent, "Send email using:"));
    }

    public void setSignature(Bitmap signature){
        this.signature = signature;
    }

}
