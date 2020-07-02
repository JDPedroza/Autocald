package com.example.autocald;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.widget.Toast;

import com.example.autocald.controller.activity.MainActivityController;
import com.example.autocald.ui.conditionBoilerElements.MainConditionBoilerElements;
import com.example.autocald.ui.maintenanceData.computerData.ComputerData;
import com.example.autocald.ui.maintenanceData.dataClient.DataClient;
import com.example.autocald.ui.digitalSignature.DigitalSignature;
import com.example.autocald.ui.documentGeneration.DocumentGeneration;
import com.example.autocald.ui.maintenanceData.technicalData.TechnicalData;
import com.example.autocald.ui.photoManagement.PhotoManagement;
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
    private PhotoManagement photoManagement = null;
    private DigitalSignature digitalSignature = null;
    private DocumentGeneration documentGeneration =null;
    private FloatingActionButton fab = null;

    //btn
    File mAbsoluteFile;
    final int PHOTO_CONST = 1;

    //variables PhotoManagement
    private Bitmap[] bmFinals;
    private boolean[] photosSelected;

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
                takePhoto();
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

        MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), dataClient = new DataClient());
        toolbar.setTitle(R.string.menu_customer_data);
        fab.hide();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //dataClient
                if(item.getItemId()==R.id.nav_data_client){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), dataClient = new DataClient());
                    toolbar.setTitle(R.string.menu_customer_data);
                    fab.hide();
                    drawer.closeDrawers();
                }
                //dataComputer
                if(item.getItemId()==R.id.nav_computer_data){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), computerData = new ComputerData());
                    toolbar.setTitle(R.string.menu_equipment_data);
                    fab.hide();
                    drawer.closeDrawers();
                }
                //dataTechnical
                if(item.getItemId()==R.id.nav_technical_data){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), technicalData = new TechnicalData());
                    toolbar.setTitle(R.string.menu_technician_data);
                    fab.hide();
                    drawer.closeDrawers();
                }
                //burnerAssembly
                if(item.getItemId()==R.id.nav_burner_assembly){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(4));
                    toolbar.setTitle(R.string.menu_burner_assembly);
                    fab.show();
                    drawer.closeDrawers();
                }
                //SecurityControl
                if(item.getItemId()==R.id.nav_security_control){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(5));
                    toolbar.setTitle(R.string.menu_security_control);
                    fab.show();
                    drawer.closeDrawers();
                }
                //WaterLevelControl
                if(item.getItemId()==R.id.nav_water_level_control){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(6));
                    toolbar.setTitle(R.string.menu_water_level_control);
                    fab.show();
                    drawer.closeDrawers();
                }
                //CondensateTank
                if(item.getItemId()==R.id.nav_condensate_tank){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(7));
                    toolbar.setTitle(R.string.menu_condensate_tank);
                    fab.show();
                    drawer.closeDrawers();
                }
                //WaterPump
                if(item.getItemId()==R.id.nav_water_pump){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(8));
                    fab.show();
                    toolbar.setTitle(R.string.menu_water_pump);
                    drawer.closeDrawers();
                }
                //SecurityTest
                if(item.getItemId()==R.id.nav_security_test){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(9));
                    fab.show();
                    toolbar.setTitle(R.string.menu_security_test);
                    drawer.closeDrawers();
                }
                //Body
                if(item.getItemId()==R.id.nav_body){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(10));
                    fab.show();
                    toolbar.setTitle(R.string.menu_body);
                    drawer.closeDrawers();
                }else if(item.getItemId()==R.id.nav_electrical_panel_control){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(11));
                    fab.show();
                    toolbar.setTitle(R.string.menu_electrical_panel_control);
                    drawer.closeDrawers();
                }
                //ElectricMotors
                if(item.getItemId()==R.id.nav_electric_motors){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(12));
                    fab.show();
                    toolbar.setTitle(R.string.menu_electric_motors);
                    drawer.closeDrawers();
                }
                //PipesAccessories
                if(item.getItemId()==R.id.nav_pipes_accessories){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), mainConditionBoilerElements = new MainConditionBoilerElements(13));
                    fab.show();
                    toolbar.setTitle(R.string.menu_pipes_accessories);
                    drawer.closeDrawers();
                }
                //Observations
                if(item.getItemId()==R.id.nav_observation){
                    fab.hide();
                    toolbar.setTitle(R.string.menu_observations);
                    drawer.closeDrawers();
                }
                //Recommendations
                if(item.getItemId()==R.id.nav_recommendations){
                    fab.hide();
                    toolbar.setTitle(R.string.menu_recommendation);
                    drawer.closeDrawers();
                }
                //PhotoManagement
                if(item.getItemId()==R.id.nav_photo_management){
                    if(bmFinals!=null){
                        MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), photoManagement = new PhotoManagement(bmFinals, photosSelected));
                    }else {
                        MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), photoManagement = new PhotoManagement());
                    }
                    fab.hide();
                    toolbar.setTitle(R.string.menu_photo_management);
                    drawer.closeDrawers();
                }
                //DocumentGeneration
                if(item.getItemId()==R.id.nav_document_generation){
                    if(bmFinals!=null){
                        MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), documentGeneration = new DocumentGeneration(bmFinals, photosSelected));
                    }else{
                        MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), documentGeneration = new DocumentGeneration());
                    }
                    //initializeButtonGenerate();
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.action_reset_form){
            MainActivityController.Companion.resetFragment(mainConditionBoilerElements);
        }
        if(item.getItemId()==R.id.action_reset_module){

        }
        if(item.getItemId()==R.id.action_reset_all){

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
        MainActivityController.Companion.addFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), digitalSignature = new DigitalSignature());
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
        String timestamp = new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());
        String imageFileName = "imagen "+ timestamp;

        File storageFile=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "PDF");
        File photoFile = File.createTempFile(imageFileName,".png", storageFile);
        this.mAbsoluteFile=photoFile;
        return photoFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PHOTO_CONST && resultCode == RESULT_OK){
            Uri imageUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider", mAbsoluteFile);
            String path = imageUri.toString();
            MainActivityController.Companion.addPhoto(mainConditionBoilerElements, path);
        }
    }

}
