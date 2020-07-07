package com.example.autocald.ui.ending;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autocald.BuildConfig;
import com.example.autocald.MainActivity;
import com.example.autocald.R;
import com.example.autocald.utilities.DynamicSizes;
import com.example.autocald.utilities.TemplatePDF;
import com.github.barteksc.pdfviewer.PDFView;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

public class DocumentGeneration extends Fragment {

    private TemplatePDF templatePDF;
    private Bitmap signature;
    private PDFView pdfView;
    private ProgressBar progressBar;
    private TextView progressTextInformation;
    private PdfPTable table;
    private Bitmap[] bmFinals;
    private boolean[] photosSelected;

    public DocumentGeneration() {
    }

    public DocumentGeneration(Bitmap[] bmFinals, boolean[] photosSelected) {
        this.bmFinals = bmFinals;
        this.photosSelected = photosSelected;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_document_generation, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnGenerate = view.findViewById(R.id.btnGenerate);
        Button btnSing = view.findViewById(R.id.btnSing);
        Button btnSend = view.findViewById(R.id.btnSend);
        pdfView = view.findViewById(R.id.pdfView);
        progressBar = view.findViewById(R.id.indeterminateBar);
        progressBar.setVisibility(View.GONE);
        progressTextInformation = view.findViewById(R.id.progress_text_information);
        LinearLayout layoutPdfView = view.findViewById(R.id.layoutPdfView);
        ViewGroup.LayoutParams params = layoutPdfView.getLayoutParams();
        params.height = maxHeight();
        layoutPdfView.setLayoutParams(params);
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if(bmFinals!=null){
                    templatePDF = new TemplatePDF(getContext());
                    new createDocument().execute();
                }else{
                    imageWarning();
                }
            }
        });
        btnSing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) requireActivity()).addFragment();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appSendPDF();
            }
        });
    }

    private void viewPDF(){
        pdfView.fromFile(templatePDF.getFile())
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .enableAntialiasing(true)
                .load();
    }

    private void appSendPDF(){
        String[] mailto = {""};
        Uri uri = FileProvider.getUriForFile(requireActivity().getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", templatePDF.getFile());
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mailto);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, templatePDF.generateExtraSubject());
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,"Autocald\n" +
                "Automatizacion y Calderas.\n" +
                "email: autocald@hotmail.com");
        emailIntent.setType("application/pdf");
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(emailIntent, "Send email using:"));
    }

    public void setSignature(Bitmap signature){
        this.signature = signature;
        new addSignature().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class createDocument extends AsyncTask<Void, Integer, Boolean> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Boolean doInBackground(Void... params) {
            templatePDF.loadData();
            publishProgress(1);
            templatePDF.loadDataClient();
            publishProgress(2);
            templatePDF.loadDataComputer();
            publishProgress(3);
            templatePDF.loadDataTechnical();
            publishProgress(5);
            templatePDF.loadDataBurnerAssembly();
            publishProgress(10);
            templatePDF.loadDataControlSecurity();
            publishProgress(15);
            templatePDF.loadDataWaterLevelControl();
            publishProgress(20);
            templatePDF.loadDataCondensateTank();
            publishProgress(25);
            templatePDF.loadDataWaterPump();
            publishProgress(30);
            templatePDF.loadDataSecurityTest();
            publishProgress(35);
            templatePDF.loadDataBody();
            publishProgress(40);
            templatePDF.loadDataElectricalPanelControl();
            publishProgress(45);
            templatePDF.loadDataElectricMotors();
            publishProgress(50);
            templatePDF.loadDataPipesAccessories();
            publishProgress(51);
            templatePDF.loadDataObservations();
            publishProgress(52);
            templatePDF.loadDataRecommendations();
            publishProgress(53);
            if(bmFinals!=null){
                templatePDF.loadImages(bmFinals, photosSelected);
            }
            publishProgress(55);
            templatePDF.createDocument();
            publishProgress(60);
            templatePDF.addMetaData("Dicumento", "Documento", "Autocald APP");
            publishProgress(65);
            try {
                table = templatePDF.customizeTabla();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            publishProgress(70);
            table = templatePDF.dataClient(table);
            publishProgress(75);
            table.addCell(templatePDF.customizeCell("ESTADO DE LOS ELEMENTOS DE LA CALDERA", 0, true, true, 0, 10));
            publishProgress(80);
            table = templatePDF.dataModules(table);
            publishProgress(85);
            table = templatePDF.dataObservationsRecommendations(table);
            publishProgress(90);
            table = templatePDF.dataTechnical(table);
            publishProgress(95);
            try {
                templatePDF.addTable(table);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            templatePDF.closeDocument();
            return true;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            if(progress==1){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_customer_data));
            }else if(progress==2){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_equipment_data));
            }else if(progress==3){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_technician_data));
            }else if(progress==5){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_burner_assembly));
            }else if(progress==10){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_security_control));
            }else if(progress==15){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_water_level_control));
            }else if(progress==20){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_condensate_tank));
            }else if(progress==25){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_water_pump));
            }else if(progress==30){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_security_test));
            }else if(progress==35){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_body));
            }else if(progress==40){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_electrical_panel_control));
            }else if(progress==45){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_electric_motors));
            }else if(progress==50){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_pipes_accessories));
            }else if(progress==51){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_observations));
            }else if(progress==52){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_recommendation));
            }else if(progress==53){
                progressTextInformation.setText("Cargando Imagenes Seleccionadas");
            }else if(progress==55){
                progressTextInformation.setText("Creando Documento");
            }else if(progress==60){
                progressTextInformation.setText("Añadiendo Metadatos");
            }else if(progress==65){
                progressTextInformation.setText("Creando Tabla");
            }else if(progress==70){
                progressTextInformation.setText("Añadiendo Datos Cliente");
            }else if(progress==75){
                progressTextInformation.setText("Añadiendo Estados");
            }else if(progress==80){
                progressTextInformation.setText("Añadiendo Datos Modulos");
            }else if(progress==85){
                progressTextInformation.setText("Añadiendo Observaciones, Recomendaciones");
            }else if(progress==90){
                progressTextInformation.setText("Añadiendo Datos Tecnico");
            }else if(progress==95){
                progressTextInformation.setText("Añadiendo Tabla");
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPreExecute() {
            progressTextInformation.setText("Generando Bases");
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(100);
            progressBar.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            progressBar.setVisibility(View.GONE);
            progressTextInformation.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(), "Documento Generado", Toast.LENGTH_SHORT).show();
            viewPDF();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(getContext(), "Tarea cancelada!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class addSignature extends AsyncTask<Void, Integer, Boolean> {


        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Boolean doInBackground(Void... params) {
            templatePDF.createDocument();
            publishProgress(10);
            templatePDF.addMetaData(templatePDF.generateNameDocument(), templatePDF.generateExtraSubject(), "Autocald APP");
            publishProgress(20);
            templatePDF.setSignature(signature);
            publishProgress(60);
            table = templatePDF.dataOthers(table);
            publishProgress(80);
            try {
                templatePDF.addTable(table);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            publishProgress(100);
            templatePDF.closeDocument();
            return true;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            if(progress==10){
                progressTextInformation.setText("Cargando Documento");
            }else if(progress==20){
                progressTextInformation.setText("Generando modulo firma");
            }else if(progress==60){
                progressTextInformation.setText("Agregando firma");
            }else if(progress==80){
                progressTextInformation.setText("Agregando modulo a documento");
            }else if(progress==100){
                progressTextInformation.setText("Cerrando documento");
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPreExecute() {
            progressTextInformation.setVisibility(View.VISIBLE);
            progressTextInformation.setText("Abriendo Documento");
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(100);
            progressBar.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            progressBar.setVisibility(View.GONE);
            progressTextInformation.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(), "Documento Actualizado", Toast.LENGTH_SHORT).show();
            viewPDF();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(getContext(), "Tarea cancelada!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private int maxHeight(){
        //get height window
        DisplayMetrics metrics = new DisplayMetrics();
        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pxWindow = metrics.heightPixels; // alto absoluto en pixels
        //get height layout
        int pxLayoutButtons = Math.round(DynamicSizes.convertDpToPixel(50, requireContext()));
        return pxWindow-(pxLayoutButtons*2);
    }

    private void imageWarning(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Advertencia");
        builder.setMessage("Aun no se han seleccionado imagenes.\n\nSe generará el documento sin imagenes")
                .setPositiveButton("Generar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        templatePDF = new TemplatePDF(getContext());
                        new createDocument().execute();
                    }
                }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }
}
