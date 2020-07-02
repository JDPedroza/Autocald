package com.example.autocald.controller.grid;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;

public class PhotoManagementAdapter extends BaseAdapter {

    private Context context;
    private String[] paths;
    private SharedPreferences dataForm;
    private Bitmap[]bitmaps = {};
    private boolean[] photoSelected;
    private int selectedPosition = -1;

    public PhotoManagementAdapter(Context context){
        this.context = context;
    }
    public void loadImagesBurnerAssembly(){
        String[]namesDataForm={"M4FuelValve", "M4Fan", "M4IgnitionTransformer", "M4Electrodes", "M4HighCable", "M4FuelNozzle", "M4FuelRegulator", "M4FuelFilter", "M4FuelLine", "M4FuelGauges", "M4PressureSwich"};
        for(int i=0; i<namesDataForm.length; i++) {
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);
            boolean addPhoto = dataForm.getBoolean("addPhotoBoolean", false);
            if(addPhoto){
                loadPath(dataForm);
            }
        }
    }
    public void loadImagesSecurityControl(){
        String[]namesDataForm={"M5PressureControl", "M5Thermostat", "M5WarrickLowAuxLevel", "M5Modutrol", "M5ModulationControl", "M5PressureGauges", "M5SafetyValves", "M5SwichAirFan"};
        for(int i=0; i<namesDataForm.length; i++) {
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);
            boolean addPhoto = dataForm.getBoolean("addPhotoBoolean", false);
            if(addPhoto){
                loadPath(dataForm);
            }
        }
    }
    public void loadImagesWaterLevelControl(){
        String[]namesDataForm={"M6BleedTaps", "M6LevelGlass", "M6Packaging", "M6Float", "M6AmpoulesMicros", "M6LevelTaps"};
        for(int i=0; i<namesDataForm.length; i++) {
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);
            boolean addPhoto = dataForm.getBoolean("addPhotoBoolean", false);
            if(addPhoto){
                loadPath(dataForm);
            }
        }
    }
    public void loadImagesCondensateTank(){
        String[]namesDataForm={"M7GeneralAspect", "M7FloatModule7", "M7LevelGlass", "M7WaterFilter", "M7Pipelines", "M7Valves", "M7Thermometer"};
        for(int i=0; i<namesDataForm.length; i++) {
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);
            boolean addPhoto = dataForm.getBoolean("addPhotoBoolean", false);
            if(addPhoto){
                loadPath(dataForm);
            }
        }
    }
    public void loadImagesWaterPump(){
        String[]namesDataForm={"M8GeneralAspectModule8", "M8Coupling", "M8Pressure", "M8Accessories"};
        for(int i=0; i<namesDataForm.length; i++) {
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);
            boolean addPhoto = dataForm.getBoolean("addPhotoBoolean", false);
            if(addPhoto){
                loadPath(dataForm);
            }
        }
    }
    public void loadImagesSecurityTest(){
        String[]namesDataForm={"M9FlameFailure", "M9MacdonellLowLevel", "M9LowLevelWarrick", "M9High pressure", "M9LowPressure", "M9CombustionAir", "M9HighSteamPressure", "M9HydrostaticTest", "M9SafetyValves", "M9TemperatureHighLow", "M9Others"};
        for(int i=0; i<namesDataForm.length; i++) {
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);
            boolean addPhoto = dataForm.getBoolean("addPhotoBoolean", false);
            if(addPhoto){
                loadPath(dataForm);
            }
        }
    }
    public void loadImagesBody(){
        String[]namesDataForm={"M10Shell", "M10WetChamber", "M10DryChamber", "M10Refractories", "M10InternalPiping", "M10Welds", "M10FrontBacKCovers", "M10Manhole", "M10Handhole", "M10Packaging", "M10Painting", "M10Isolation"};
        for(int i=0; i<namesDataForm.length; i++) {
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);
            boolean addPhoto = dataForm.getBoolean("addPhotoBoolean", false);
            if(addPhoto){
                loadPath(dataForm);
            }
        }
    }
    public void loadImagesElectricalPanelControl(){
        String[]namesDataForm={"M11Programmer", "M11FlameSensor", "M11Photocell", "M11GeneralWiring", "M11ElectricShells", "M11Breaker", "M11Fuses", "M11Contactors", "M11Relays", "M11Terminals", "M11Organization", "M11SignalingPlates", "M11LightBulbs"};
        for(int i=0; i<namesDataForm.length; i++) {
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);
            boolean addPhoto = dataForm.getBoolean("addPhotoBoolean", false);
            if(addPhoto){
                loadPath(dataForm);
            }
        }
    }
    public void loadImagesElectricMotors(){
        String[]namesDataForm={"M12FanModule12", "M12Compressor", "M12WaterPump", "M12FuelPump", "M12Bearings"};
        for(int i=0; i<namesDataForm.length; i++) {
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);
            boolean addPhoto = dataForm.getBoolean("addPhotoBoolean", false);
            if(addPhoto){
                loadPath(dataForm);
            }
        }
    }
    public void loadImagesPipesAccessories(){
        String[]namesDataForm={"M13GeneralAspectModule13", "M13Support", "M13IsolationModule13", "M13ValvesModule13", "M13Traps", "M13Checks","M13Purges", "M13Distributor"};
        for(int i=0; i<namesDataForm.length; i++) {
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);
            boolean addPhoto = dataForm.getBoolean("addPhotoBoolean", false);
            if(addPhoto){
                loadPath(dataForm);
            }
        }
    }
    private void loadPath(SharedPreferences dataForm){
        if(this.paths==null){
            int numberPhotos = dataForm.getInt("numberPhotosInt", 0);
            this.paths= new String[numberPhotos];
            for (int i=0; i<numberPhotos; i++){
                this.paths[i] = dataForm.getString("pathPhoto"+(i+1)+"Text", "");
            }
        }else{
            int j=0;
            int lastNumberPhotos = paths.length;
            int numberPhotos = lastNumberPhotos + dataForm.getInt("numberPhotosInt", 0);;
            this.paths= resizeArray(numberPhotos, this.paths);
            for (int i=lastNumberPhotos; i<numberPhotos; i++){
                this.paths[i] = dataForm.getString("pathPhoto"+(j+1)+"Text", "");
                j++;
            }
        }

    }
    public int getPath(){
        return this.paths.length;
    }
    private static String[] resizeArray(int resize, String[] a) {

        String[] b = new String[resize];
        /* 1ºArg: Array origen,
         * 2ºArg: Por donde comienza a copiar en el origen
         * 3ºArg: Array destino
         * 4ºArg: Por donde comienza a pegar en el destino
         * 5ºArg: Numero de elementos que copiara del origen
         */
        System.arraycopy(a, 0, b, 0, a.length);

        return b;
    }
    public void loadBitmaps() throws IOException {
        if(this.paths!=null){
            this.bitmaps= new Bitmap[this.paths.length];
            for(int i=0; i<this.paths.length; i++){
                Bitmap bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(paths[i]));
                bm = Bitmap.createScaledBitmap(bm, 650, 900, true);
                this.bitmaps[i]=bm;
            }
        }
    }
    public void loadBitMaps(Bitmap[] bitmaps){
        this.bitmaps=bitmaps;
    }
    public Bitmap[] getBitmaps(){
        return this.bitmaps;
    }
    public boolean[] getPhotoSelected(){
        return this.photoSelected;
    }
    @Override
    public int getCount() {
        return bitmaps.length;
    }

    @Override
    public Object getItem(int position) {
        return bitmaps[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(bitmaps[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(
                330, //650
                460 //900
        ));
        imageView.setBackgroundColor(Color.WHITE);
        if(!photoSelected[position]){
            imageView.setAlpha((float) 0.5);
            imageView.setBackgroundColor(Color.WHITE);
            imageView.setPadding(30, 30, 30, 30);
        }else{
            imageView.setAlpha((float) 1);
            imageView.setBackgroundColor(Color.parseColor("#EA7F00"));
            imageView.setPadding(10, 10, 10, 10);
        }
        if(position==selectedPosition){
            if(!photoSelected[position]){
                imageView.setAlpha((float) 1);
                imageView.setBackgroundColor(Color.parseColor("#EA7F00"));
                imageView.setPadding(10, 10, 10, 10);
                photoSelected[position]=true;
                selectedPosition=-1;
            }else{
                imageView.setBackgroundColor(Color.WHITE);
                imageView.setAlpha((float) 0.5);
                imageView.setPadding(30, 30, 30, 30);
                photoSelected[position]=false;
                selectedPosition=-1;
            }
        }
        return imageView;
    }

    public void generatePhotoSelected(){
        photoSelected = new boolean[getCount()];
    }

    public void setSelectedPosition(int position){
        selectedPosition = position;
    }

    public void loadPhotoSelected(boolean[] photoSelected) {
        this.photoSelected=photoSelected;
    }
}

