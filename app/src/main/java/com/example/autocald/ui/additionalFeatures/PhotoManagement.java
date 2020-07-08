package com.example.autocald.ui.additionalFeatures;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autocald.MainActivity;
import com.example.autocald.R;
import com.example.autocald.controller.grid.PhotoManagementAdapter;
import com.example.autocald.utilities.DynamicSizes;

import java.io.IOException;

public class PhotoManagement extends Fragment {

    private PhotoManagementAdapter photoManagementAdapter;
    private ProgressBar progressBar;
    private TextView progressTextInformation;
    private GridView gridViewImage;
    private Bitmap[] bitmaps;
    private boolean[] photoSelected;
    private int numberPhotos=0;
    private SharedPreferences dataForm;
    private ImageView imageView;

    public PhotoManagement() {
    }

    public PhotoManagement(Bitmap[] bitmaps, boolean[] booleans) {
        this.bitmaps=bitmaps;
        this.photoSelected=booleans;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createDateForm();
        return inflater.inflate(R.layout.fragment_photo_management, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridViewImage = view.findViewById(R.id.grid_view_imagenes);
        LinearLayout layoutGrid = view.findViewById(R.id.layoutGrid);
        ViewGroup.LayoutParams params = layoutGrid.getLayoutParams();
        params.height = maxHeight();
        layoutGrid.setLayoutParams(params);
        imageView = view.findViewById(R.id.imageViewSearchImage);
        Button btnApplyChange = view.findViewById(R.id.btn_apply_changes);
        progressBar = view.findViewById(R.id.indeterminateBar);
        progressBar.setVisibility(View.GONE);
        progressTextInformation = view.findViewById(R.id.progress_text_information);
        photoManagementAdapter = new PhotoManagementAdapter(getContext());
        new LoadGrid().execute();
        btnApplyChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap[] bmFinals = photoManagementAdapter.getBitmaps();
                boolean[] photosSelected = photoManagementAdapter.getPhotoSelected();
                ((MainActivity) requireActivity()).saveDataPhotoManagement(bmFinals, photosSelected);
                Toast.makeText(getContext(),"Imágenes Seleccionadas", Toast.LENGTH_LONG).show();
            }
        });
        getDataForm();
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadGrid extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            photoManagementAdapter.loadImagesExtras();
            publishProgress(2);
            photoManagementAdapter.loadImagesBurnerAssembly();
            publishProgress(5);
            photoManagementAdapter.loadImagesSecurityControl();
            publishProgress(10);
            photoManagementAdapter.loadImagesWaterLevelControl();
            publishProgress(15);
            photoManagementAdapter.loadImagesCondensateTank();
            publishProgress(20);
            photoManagementAdapter.loadImagesWaterPump();
            publishProgress(25);
            photoManagementAdapter.loadImagesSecurityTest();
            publishProgress(30);
            photoManagementAdapter.loadImagesBody();
            publishProgress(35);
            photoManagementAdapter.loadImagesElectricalPanelControl();
            publishProgress(40);
            photoManagementAdapter.loadImagesElectricMotors();
            publishProgress(45);
            photoManagementAdapter.loadImagesPipesAccessories();
            publishProgress(50);
            if(bitmaps!=null){
                if(photoManagementAdapter.getPath()==bitmaps.length){
                    photoManagementAdapter.loadBitMaps(bitmaps);
                    photoManagementAdapter.loadPhotoSelected(photoSelected);
                }else{
                    try {
                        publishProgress(75);
                        photoManagementAdapter.loadBitmaps();
                        photoSelected=null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                try {
                    photoManagementAdapter.loadBitmaps();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            publishProgress(100);
            return true;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            if(progress==2){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_photo_management));
            }else if(progress==5){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_security_control));
            }else if(progress==10){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_water_level_control));
            }else if(progress==15){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_condensate_tank));
            }else if(progress==20){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_water_pump));
            }else if(progress==25){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_security_test));
            }else if(progress==30){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_body));
            }else if(progress==35){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_electrical_panel_control));
            }else if(progress==40){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_electric_motors));
            }else if(progress==45){
                progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_pipes_accessories));
            }else if(progress==50){
                progressTextInformation.setText("Cargando Imagenes");
            }else if(progress==75){
                progressTextInformation.setText("Se han leido cambios\nCargando nuevamente");
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPreExecute() {
            imageView.setVisibility(View.INVISIBLE);
            progressTextInformation.setText(getText(R.string.load_data)+"\n"+getText(R.string.menu_burner_assembly));
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(100);
            progressBar.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            progressBar.setVisibility(View.GONE);
            progressTextInformation.setVisibility(View.INVISIBLE);
            gridViewImage.setAdapter(photoManagementAdapter);
            if(photoSelected==null){
                photoManagementAdapter.generatePhotoSelected();
            }
            if(photoManagementAdapter.getChanges()){
                imageView.setVisibility(View.INVISIBLE);
            }else{
                imageView.setVisibility(View.VISIBLE);
            }
            gridViewImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    photoManagementAdapter.setSelectedPosition(position);
                    photoManagementAdapter.notifyDataSetChanged();
                }
            });
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

    public void addImage(String path){
        numberPhotos++;
        SharedPreferences.Editor editor = dataForm.edit();
        editor.putBoolean("addPhotoBoolean", true);
        editor.putString("pathPhoto"+this.numberPhotos+"Text", path);
        editor.putInt("numberPhotosInt", this.numberPhotos);
        editor.apply();
        photoManagementAdapter = new PhotoManagementAdapter(getContext());
        new LoadGrid().execute();
        if(photoManagementAdapter.getChanges()){
            imageView.setVisibility(View.INVISIBLE);
        }else{
            imageView.setVisibility(View.VISIBLE);
        }
    }

    private void createDateForm(){
        dataForm = requireActivity().getSharedPreferences("M16PhotoManagement", Context.MODE_PRIVATE);
    }

    private void getDataForm(){
        this.numberPhotos = dataForm.getInt("numberPhotosInt", 0);
    }
}
