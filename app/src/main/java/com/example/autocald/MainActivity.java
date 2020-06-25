package com.example.autocald;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;

import com.example.autocald.controller.activity.MainActivityController;
import com.example.autocald.ui.burnerAssembly.BurnerAssembly;
import com.example.autocald.ui.dataClient.DataClient;
import com.example.autocald.ui.digitalSignature.DigitalSignature;
import com.example.autocald.ui.documentGeneration.DocumentGeneration;
import com.example.autocald.utilities.TemplatePDF;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Toolbar toolbar;
    private DrawerLayout drawer;

    //importamos todas las clases de nav
    private DataClient dataClient = null;
    private BurnerAssembly burnerAssembly = null;
    private DigitalSignature digitalSignature = null;
    private DocumentGeneration documentGeneration =null;
    private FloatingActionButton fab = null;

    //btn
    Button btnSave=null;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)  != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)  != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},1000);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,},1000);
        }

        //Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ActionButton
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
                    drawer.closeDrawers();
                }
                //dataComputer
                if(item.getItemId()==R.id.nav_computer_data){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), digitalSignature = new DigitalSignature());
                    drawer.closeDrawers();
                    toolbar.setTitle(R.string.menu_customer_data);
                }
                //dataTechnical
                if(item.getItemId()==R.id.nav_technical_data){
                    drawer.closeDrawers();
                    toolbar.setTitle(R.string.menu_customer_data);
                }
                //burnerAssembly
                if(item.getItemId()==R.id.nav_burner_assembly){
                    MainActivityController.Companion.addFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), burnerAssembly = new BurnerAssembly());
                    drawer.closeDrawers();
                    toolbar.setTitle(R.string.menu_burner_assembly);
                    fab.show();
                }
                //SecurityControl
                if(item.getItemId()==R.id.nav_security_control){
                    drawer.closeDrawers();
                    toolbar.setTitle(R.string.menu_customer_data);
                }
                //WaterLevelControl
                if(item.getItemId()==R.id.nav_water_level_control){
                    drawer.closeDrawers();
                    toolbar.setTitle(R.string.menu_customer_data);
                }
                //CondensateTank
                if(item.getItemId()==R.id.nav_condensate_tank){
                    drawer.closeDrawers();
                    toolbar.setTitle(R.string.menu_customer_data);
                }
                //WaterPump
                if(item.getItemId()==R.id.nav_water_pump){
                    drawer.closeDrawers();
                    toolbar.setTitle(R.string.menu_customer_data);
                }
                //SecurityTest
                if(item.getItemId()==R.id.nav_security_test){
                    drawer.closeDrawers();
                    toolbar.setTitle(R.string.menu_customer_data);
                }
                //Body
                if(item.getItemId()==R.id.nav_body){
                    drawer.closeDrawers();
                    toolbar.setTitle(R.string.menu_customer_data);
                }else if(item.getItemId()==R.id.nav_electrical_panel_control){
                    drawer.closeDrawers();
                    toolbar.setTitle(R.string.menu_customer_data);
                }
                //ElectricMotors
                if(item.getItemId()==R.id.nav_electric_motors){
                    drawer.closeDrawers();
                    toolbar.setTitle(R.string.menu_customer_data);
                }
                //PipesAccessories
                if(item.getItemId()==R.id.nav_pipes_accessories){
                    drawer.closeDrawers();
                    toolbar.setTitle(R.string.menu_customer_data);
                }
                //ObservationsRecommendations
                if(item.getItemId()==R.id.nav_observations_recommendations){
                    drawer.closeDrawers();
                    toolbar.setTitle(R.string.menu_customer_data);
                }
                //PhotoManagement
                if(item.getItemId()==R.id.nav_photo_management){
                    drawer.closeDrawers();
                    toolbar.setTitle(R.string.menu_customer_data);
                }
                //DocumentGeneration
                if(item.getItemId()==R.id.nav_document_generation){
                    MainActivityController.Companion.replaceFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), documentGeneration = new DocumentGeneration());
                    MainActivityController.Companion.addFragment(getSupportFragmentManager(), findViewById(R.id.contenedorFragment), digitalSignature = new DigitalSignature());
                    //initializeButtonGenerate();
                    toolbar.setTitle(R.string.menu_burner_assembly);
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
            MainActivityController.Companion.resetFragment(burnerAssembly);
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

    public void removeFragment(Bitmap signature){
        MainActivityController.Companion.removeFragment(getSupportFragmentManager(), digitalSignature);
        documentGeneration.setSignature(signature);
    }

}
