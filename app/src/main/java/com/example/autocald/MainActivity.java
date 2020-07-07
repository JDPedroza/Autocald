package com.example.autocald;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.autocald.controller.activity.MainActivityController;
import com.example.autocald.ui.additionalFeatures.Observations;
import com.example.autocald.ui.additionalFeatures.Recommendations;
import com.example.autocald.ui.conditionBoilerElements.MainConditionBoilerElements;
import com.example.autocald.ui.maintenanceData.computerData.ComputerData;
import com.example.autocald.ui.maintenanceData.dataClient.DataClient;
import com.example.autocald.ui.ending.DigitalSignature;
import com.example.autocald.ui.ending.DocumentGeneration;
import com.example.autocald.ui.maintenanceData.technicalData.TechnicalData;
import com.example.autocald.ui.additionalFeatures.PhotoManagement;
import com.example.autocald.utilities.Reset;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Toolbar toolbar;
    private DrawerLayout drawer;

    //importamos todas las clases de nav
    private DataClient dataClient = null;
    private ComputerData computerData = null;
    private TechnicalData technicalData = null;
    private MainConditionBoilerElements mainConditionBoilerElements = null;
    private Observations observations = null;
    private Recommendations recommendations = null;
    private PhotoManagement photoManagement = null;
    private DigitalSignature digitalSignature = null;
    private DocumentGeneration documentGeneration =null;
    private FloatingActionButton fab = null;

    private Reset reset;

    //btn
    File mAbsoluteFile;
    final int PHOTO_CONST = 1;

    //variables PhotoManagement
    private Bitmap[] bmFinals;
    private boolean[] photosSelected;
    private Menu menu;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)  != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)  != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)  != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},1000);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,},1000);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,},1000);
        }

        //Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ActionButton
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                    positionCameraWarning();
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_data_client)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), dataClient = new DataClient(), "dataClient");
        toolbar.setTitle(R.string.menu_customer_data);
        fab.hide();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //dataClient
                if(item.getItemId()==R.id.nav_data_client){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), dataClient = new DataClient(), "dataClient");
                    toolbar.setTitle(R.string.menu_customer_data);
                    optionsMenuAvailable(1);
                    fab.hide();
                    drawer.closeDrawers();
                }
                //dataComputer
                if(item.getItemId()==R.id.nav_computer_data){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), computerData = new ComputerData(), "computerData");
                    optionsMenuAvailable(1);
                    toolbar.setTitle(R.string.menu_equipment_data);
                    fab.hide();
                    drawer.closeDrawers();
                }
                //dataTechnical
                if(item.getItemId()==R.id.nav_technical_data){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), technicalData = new TechnicalData(), "technicalData");
                    optionsMenuAvailable(1);
                    toolbar.setTitle(R.string.menu_technician_data);
                    fab.hide();
                    drawer.closeDrawers();
                }
                //burnerAssembly
                if(item.getItemId()==R.id.nav_burner_assembly){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(4), "mainConditionBoilerElements");
                    optionsMenuAvailable(2);
                    toolbar.setTitle(R.string.menu_burner_assembly);
                    fab.setImageResource(R.drawable.ic_add_a_photo);
                    fab.show();
                    drawer.closeDrawers();
                }
                //SecurityControl
                if(item.getItemId()==R.id.nav_security_control){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(5), "mainConditionBoilerElements");
                    optionsMenuAvailable(2);
                    toolbar.setTitle(R.string.menu_security_control);
                    fab.setImageResource(R.drawable.ic_add_a_photo);
                    fab.show();
                    drawer.closeDrawers();
                }
                //WaterLevelControl
                if(item.getItemId()==R.id.nav_water_level_control){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(6), "mainConditionBoilerElements");
                    optionsMenuAvailable(2);
                    toolbar.setTitle(R.string.menu_water_level_control);
                    fab.setImageResource(R.drawable.ic_add_a_photo);
                    fab.show();
                    drawer.closeDrawers();
                }
                //CondensateTank
                if(item.getItemId()==R.id.nav_condensate_tank){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(7), "mainConditionBoilerElements");
                    optionsMenuAvailable(2);
                    fab.setImageResource(R.drawable.ic_add_a_photo);
                    toolbar.setTitle(R.string.menu_condensate_tank);
                    fab.show();
                    drawer.closeDrawers();
                }
                //WaterPump
                if(item.getItemId()==R.id.nav_water_pump){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(8), "mainConditionBoilerElements");
                    optionsMenuAvailable(2);
                    fab.setImageResource(R.drawable.ic_add_a_photo);
                    fab.show();
                    toolbar.setTitle(R.string.menu_water_pump);
                    drawer.closeDrawers();
                }
                //SecurityTest
                if(item.getItemId()==R.id.nav_security_test){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(9), "mainConditionBoilerElements");
                    optionsMenuAvailable(2);
                    fab.setImageResource(R.drawable.ic_add_a_photo);
                    fab.show();
                    toolbar.setTitle(R.string.menu_security_test);
                    drawer.closeDrawers();
                }
                //Body
                if(item.getItemId()==R.id.nav_body){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(10), "mainConditionBoilerElements");
                    optionsMenuAvailable(2);
                    fab.setImageResource(R.drawable.ic_add_a_photo);
                    fab.show();
                    toolbar.setTitle(R.string.menu_body);
                    drawer.closeDrawers();
                }else if(item.getItemId()==R.id.nav_electrical_panel_control){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(11), "mainConditionBoilerElements");
                    optionsMenuAvailable(2);
                    fab.setImageResource(R.drawable.ic_add_a_photo);
                    fab.show();
                    toolbar.setTitle(R.string.menu_electrical_panel_control);
                    drawer.closeDrawers();
                }
                //ElectricMotors
                if(item.getItemId()==R.id.nav_electric_motors){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(12), "mainConditionBoilerElements");
                    optionsMenuAvailable(2);
                    fab.setImageResource(R.drawable.ic_add_a_photo);
                    fab.show();
                    toolbar.setTitle(R.string.menu_electric_motors);
                    drawer.closeDrawers();
                }
                //PipesAccessories
                if(item.getItemId()==R.id.nav_pipes_accessories){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(13), "mainConditionBoilerElements");
                    optionsMenuAvailable(2);
                    fab.setImageResource(R.drawable.ic_add_a_photo);
                    fab.show();
                    toolbar.setTitle(R.string.menu_pipes_accessories);
                    drawer.closeDrawers();
                }
                //Observations
                if(item.getItemId()==R.id.nav_observation){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), observations = new Observations(), "observations");
                    optionsMenuAvailable(1);
                    fab.hide();
                    toolbar.setTitle(R.string.menu_observations);
                    drawer.closeDrawers();
                }
                //Recommendations
                if(item.getItemId()==R.id.nav_recommendations){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), recommendations = new Recommendations(), "recommendations");
                    optionsMenuAvailable(1);
                    fab.hide();
                    toolbar.setTitle(R.string.menu_recommendation);
                    drawer.closeDrawers();
                }
                //PhotoManagement
                if(item.getItemId()==R.id.nav_photo_management){
                    if(bmFinals!=null){
                        MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), photoManagement = new PhotoManagement(bmFinals, photosSelected), "photoManagement");
                    }else {
                        MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), photoManagement = new PhotoManagement(), "photoManagement");
                    }
                    optionsMenuAvailable(3);
                    fab.setImageResource(R.drawable.ic_add_a_image);
                    fab.show();
                    toolbar.setTitle(R.string.menu_photo_management);
                    drawer.closeDrawers();
                }
                //DocumentGeneration
                if(item.getItemId()==R.id.nav_document_generation){
                    if(bmFinals!=null){
                        MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), documentGeneration = new DocumentGeneration(bmFinals, photosSelected), "documentGeneration");
                    }else{
                        MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), documentGeneration = new DocumentGeneration(), "documentGeneration");
                    }
                    //initializeButtonGenerate();
                    optionsMenuAvailable(3);
                    fab.hide();
                    toolbar.setTitle(R.string.menu_document_generation);
                    drawer.closeDrawers();
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        this.menu = menu;
        optionsMenuAvailable(1);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.action_reset_form){
            if (dataClient != null && dataClient.isVisible()) {
                MainActivityController.Companion.resetFragment(dataClient);
            }else if(computerData != null && computerData.isVisible()){
                MainActivityController.Companion.resetFragment(computerData);
            }else if(technicalData != null && technicalData.isVisible()){
                MainActivityController.Companion.resetFragment(technicalData);
            }else if(mainConditionBoilerElements != null && mainConditionBoilerElements.isVisible()){
                MainActivityController.Companion.resetFragment(mainConditionBoilerElements);
            }else if(observations != null && observations.isVisible()){
                MainActivityController.Companion.resetFragment(observations);
            }else if(recommendations != null && recommendations.isVisible()){
                MainActivityController.Companion.resetFragment(recommendations);
            }
        }
        if(item.getItemId()==R.id.action_reset_module){
            MainActivityController.Companion.resetModule(mainConditionBoilerElements);
        }
        if(item.getItemId()==R.id.action_reset_all){
            if (dataClient != null && dataClient.isVisible()) {
                MainActivityController.Companion.resetFragment(dataClient);
            }else if(computerData != null && computerData.isVisible()){
                MainActivityController.Companion.resetFragment(computerData);
            }else if(technicalData != null && technicalData.isVisible()){
                MainActivityController.Companion.resetFragment(technicalData);
            }else if(mainConditionBoilerElements != null && mainConditionBoilerElements.isVisible()){
                MainActivityController.Companion.resetFragment(mainConditionBoilerElements);
            }else if(observations != null && observations.isVisible()){
                MainActivityController.Companion.resetFragment(observations);
            }else if(recommendations != null && recommendations.isVisible()){
                MainActivityController.Companion.resetFragment(recommendations);
            }
            deleteImageWarning();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void addFragment(){
        MainActivityController.Companion.addFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), digitalSignature = new DigitalSignature(), "digitalSignature");
    }

    public void removeFragment(Bitmap signature){
        MainActivityController.Companion.removeFragment(getSupportFragmentManager(), digitalSignature);
        documentGeneration.setSignature(signature);
    }

    public void saveDataPhotoManagement(Bitmap[] bmFinals, boolean[] photosSelected){
        this.bmFinals = bmFinals;
        this.photosSelected = photosSelected;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void takePhoto(){

        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePhotoIntent.resolveActivity(getPackageManager())!=null){
            File photoFile = null;
            try{
                photoFile = createPhotoFile();
            }catch (Exception e){
                e.printStackTrace();
            }

            if(photoFile!=null){
                Uri photoUri = FileProvider.getUriForFile(MainActivity.this,BuildConfig.APPLICATION_ID + ".provider",photoFile);
                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                startActivityForResult(takePhotoIntent,PHOTO_CONST);
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private File createPhotoFile() throws IOException {
        @SuppressLint("SimpleDateFormat") String timestamp = new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());
        String imageFileName = "imagen "+ timestamp;

        File storageFile=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "AutocaldPhotos");
        if(!storageFile.exists()) {
            storageFile.mkdir();
        }
        File photoFile = File.createTempFile(imageFileName,".png", storageFile);
        this.mAbsoluteFile=photoFile;
        return photoFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(mainConditionBoilerElements!=null && mainConditionBoilerElements.isVisible()){
            if(requestCode==PHOTO_CONST && resultCode == RESULT_OK){
                Uri imageUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", mAbsoluteFile);
                String path = imageUri.toString();
                MainActivityController.Companion.addPhoto(mainConditionBoilerElements, path);
            }
        }
        if(photoManagement!=null && photoManagement.isVisible()){
            if(resultCode == RESULT_OK){
                Uri imageUri = data.getData();
                String path = imageUri.toString();
                MainActivityController.Companion.addImage(photoManagement, path);
            }
        }
    }

    private void optionsMenuAvailable(int i){
        /*
        0=resetForm
        1=resetModule
        2=resetAll
         */
        if(i==1){
            menu.getItem(0).setEnabled(true);
            menu.getItem(1).setEnabled(false);
            menu.getItem(2).setEnabled(true);
        }else if(i==2){
            menu.getItem(0).setEnabled(true);
            menu.getItem(1).setEnabled(true);
            menu.getItem(2).setEnabled(true);
        }else if(i==3){
            menu.getItem(0).setEnabled(false);
            menu.getItem(1).setEnabled(false);
            menu.getItem(2).setEnabled(false);
        }
    }


    @SuppressLint("IntentReset")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private void loadImage(){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(Intent.createChooser(intent,"seleccione la aplicacion"),10);
    }

    private void positionCameraWarning(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        reset = new Reset(getApplicationContext());
        builder.setTitle("Advertencia");
        builder.setMessage("Recuerde que la foto debe estar en sentido vertical.\n")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(mainConditionBoilerElements!=null && mainConditionBoilerElements.isVisible()){
                            takePhoto();
                        }else if(photoManagement!=null && photoManagement.isVisible()){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                                loadImage();
                            }
                        }
                    }
                }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }

    private void deleteImageWarning(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        reset = new Reset(getApplicationContext());
        builder.setTitle("Advertencia");
        builder.setMessage("¿Desea eliminar imágenes tomadas por la aplicación?\n")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        reset.resetAll(true);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reset.resetAll(false);
            }
        }).show();
    }

}
