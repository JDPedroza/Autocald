package com.example.autocald.utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.autocald.R;

@SuppressLint("Registered")
public class TemplatePDF extends FileProvider{
    private Context context;
    private Bitmap signature;
    private File pdfFile;
    private Document document;
    private Image[]images;
    //List Format
    private Font fTextGenericTitle = new Font(Font.FontFamily.HELVETICA, 11);
    private Font fTextGenericTitle1 = new Font(Font.FontFamily.HELVETICA, 5);
    private Font fTextGenericTitle2 = new Font(Font.FontFamily.HELVETICA, 10);
    private Font fTextGenericTitle3 = new Font(Font.FontFamily.HELVETICA, 6);
    private Font fTextGenericTitle4 = new Font(Font.FontFamily.HELVETICA, 8);
    private Font fTextGenericTitle5 = new Font(Font.FontFamily.HELVETICA, 6, Font.NORMAL, BaseColor.RED);
    //List Data
    private String[]dataClient;
    private String[]dataTechnical;
    private String[][]dataComputer;
    private String[][]dataBurnerAssembly;
    private String[][]dataControlSecurity;
    private String[][]dataWaterLevelControl;
    private String[][]dataCondensateTank;
    private String[][]dataWaterPump;
    private String[][]dataSecurityTest;
    private String[][]dataBody;
    private String[][]dataElectricalPanelControl;
    private String[][]dataElectricalMotors;
    private String[][]dataPipesAccessories;
    private String[]dataObservations;
    private String[]dataRecommendations;
    //DB
    SharedPreferences dataForm;

    //builder
    public TemplatePDF(Context context){
        this.context=context;
    }
    //metodos
    public String generateNameDocument(){
        String numberService = "0";
        String nameClient = "No definido";
        if(!this.dataClient[3].equals("")){
            nameClient = this.dataClient[3];
        }
        if(!this.dataClient[0].equals("")){
            numberService = this.dataClient[0];
        }
        return numberService+" - "+nameClient+".pdf";
    }
    public String generateExtraSubject(){
        String numberService = "0";
        String nameClient = "No definido";
        String typeService = "";
        if(!this.dataClient[3].equals("")){
            nameClient = this.dataClient[3];
        }
        if(!this.dataClient[0].equals("")){
            numberService = this.dataClient[0];
        }
        typeService =this.dataClient[2];
        return typeService+": "+numberService+" - "+nameClient;
    }
    public void loadData(){
        //1
        this.dataClient = new String[]{"", "", "", "", "", ""};
        //2
        this.dataComputer = new String[][]{
                {"Presion de Vapor", "", ""},
                {"Atomizacion de aire", "", ""},
                {"Atomizacion de vapor", "", ""},
                {"Temperatura de combustible", "", ""},
                {"Presion de combustible.", "", ""},
                {"Presion de Gas Linea", "", ""},
                {"Presion de Gas Tren", "", ""},
                {"Temperatura de Gases", "", ""},
                {"Temperatura de Agua", "", ""},
                {"Nivel de Caldera", "", ""},
                {"Nivel de  Tanq Condensado", "", ""},
                {"Nivel de Tanq Combustible", "", ""},
        };
        //3
        this.dataTechnical = new String[]{"Carlos Martinez - Santiago Sanchez", "0", "0", "0", "Ivan Fernando Camargo"};
        //4
        this.dataBurnerAssembly = new String[][]{
                {"Valvula de Combustible", "", ""},
                {"Ventilador", "", ""},
                {"Trasformador de Ignicion", "", ""},
                {"Electrodos", "", ""},
                {"Cable de Alta", "", ""},
                {"Boquilla de combustible", "", ""},
                {"Reguladora de Combustible", "", ""},
                {"Filtro de combustible", "", ""},
                {"Tuberia de combustible", "", ""},
                {"Manometros de Combustible", "", ""},
                {"Swich de Presion", "", ""}
        };
        //5
        this.dataControlSecurity = new String[][]{
                {"Control de Presion", "", ""},
                {"Termostato", "", ""},
                {"Warrick( Bajo nivel Aux)", "", ""},
                {"Modutrol", "", ""},
                {"Control de Modulacion", "", ""},
                {"Manometros", "", ""},
                {"Valvulas de seguridad", "", ""},
                {"Swich de Aire Ventilador", "", ""},
        };
        //6
        this.dataWaterLevelControl = new String[][]{
                {"Grifos de Purga", "", ""},
                {"Vidrio de nivel", "", ""},
                {"Empaques", "", ""},
                {"Flotador", "", ""},
                {"Ampolletas o Micros", "", ""},
                {"Grifos de nivel", "", ""},
        };
        //6
        this.dataCondensateTank = new String[][]{
                {"Aspecto general", "", ""},
                {"Flotador", "", ""},
                {"Vidrio de nivel", "", ""},
                {"Filtro de Agua", "", ""},
                {"Tuberias", "", ""},
                {"Valvulas ", "", ""},
                {"Termometro", "", ""},
        };
        //7
        this.dataWaterPump = new String[][]{
                {"Aspecto general", "", ""},
                {"Acople", "", ""},
                {"Presion", "", ""},
                {"Accesorios", "", ""},
        };
        //8
        this.dataSecurityTest = new String[][]{
                {"Falla de Llama", "", ""},
                {"Bajo Nivel Macdonell", "", ""},
                {"Bajo Nivel Warrick", "", ""},
                {"Alta Presion", "", ""},
                {"Baja presion", "", ""},
                {"Aire de Combustion", "", ""},
                {"Alta presion de Vapor", "", ""},
                {"Prueba Hidrostatica", "", ""},
                {"Valvulas de seguridad", "", ""},
                {"Temperatura (Alta y Baja)", "", ""},
                {"Otros", "", ""},
        };
        //9
        this.dataBody = new String[][]{
                {"Shell", "", ""},
                {"Camara Humeda", "", ""},
                {"Camara seca", "", ""},
                {"Refractarios", "", ""},
                {"Tuberia interna", "", ""},
                {"Soldaduras", "", ""},
                {"Tapas Frontal y Trasera", "", ""},
                {"Manhole", "", ""},
                {"Handhole", "", ""},
                {"Empaques", "", ""},
                {"Pintura", "", ""},
                {"Aislamiento", "", ""}
        };
        //10
        this.dataElectricalPanelControl = new String[][]{
                {"Programador", "", ""},
                {"Sensor de Llama", "", ""},
                {"Fotocelda", "", ""},
                {"Cableado general", "", ""},
                {"Corazas electricas", "", ""},
                {"Breaker", "", ""},
                {"Fusibles", "", ""},
                {"Contactores", "", ""},
                {"Reles", "", ""},
                {"Terminales", "", ""},
                {"Organización", "", ""},
                {"Placas de señalizacion", "", ""},
                {"Bombillos", "", ""}
        };
        //11
        this.dataElectricalMotors = new String[][]{
                {"Ventilador", "", ""},
                {"Compresor", "", ""},
                {"Bomba de Agua", "", ""},
                {"Bomba de Combustible", "", ""},
                {"Rodamientos", "", ""},
        };
        //12
        this.dataPipesAccessories = new String[][]{
                {"Aspecto general", "", ""},
                {"Soporteria", "", ""},
                {"Aislamiento", "", ""},
                {"Valvulas", "", ""},
                {"Trampas", "", ""},
                {"Cheques", "", ""},
                {"Purgas", "", ""},
                {"Distribuidor", "", ""},
        };
        //13
        this.dataObservations = new String[]{
                "",
                "",
                "",
                "",
                ""
        };
        this.dataRecommendations = new String[]{
                "",
        };
    }
    public void loadDataClient(){
        dataForm = context.getSharedPreferences("M1DataClient", Context.MODE_PRIVATE);
        this.dataClient[0]=dataForm.getString("serviceControl", "");
        this.dataClient[1]=dataForm.getString("serviceDate", "00/00/00");
        this.dataClient[2]=dataForm.getString("serviceTypeText", "Mant General y Correctivo");
        this.dataClient[3]=dataForm.getString("serviceClient", "");
        this.dataClient[4]=dataForm.getString("serviceCapacity", "");
        this.dataClient[5]=dataForm.getString("serviceModel", "");
    }
    public void loadDataComputer(){
        dataForm = context.getSharedPreferences("M2DataComputer", Context.MODE_PRIVATE);
        this.dataComputer[0][1]=dataForm.getString("editText_Vapor_Pressure_Value", "");
        this.dataComputer[0][2]=dataForm.getString("editText_Vapor_Pressure_Observation", "");
        this.dataComputer[1][1]=dataForm.getString("editText_Air_Atomization_Value", "");
        this.dataComputer[1][2]=dataForm.getString("editText_Air_Atomization_Observation", "");
        this.dataComputer[2][1]=dataForm.getString("editText_Steam_Atomization_Value", "");
        this.dataComputer[2][2]=dataForm.getString("editText_Steam_Atomization_Observation", "");
        this.dataComputer[3][1]=dataForm.getString("editText_Fuel_Temperature_Value", "");
        this.dataComputer[3][2]=dataForm.getString("editText_Fuel_Temperature_Observation", "");
        this.dataComputer[4][1]=dataForm.getString("editText_Fuel_Pressure_Value", "");
        this.dataComputer[4][2]=dataForm.getString("editText_Fuel_Pressure_Observation", "");
        this.dataComputer[5][1]=dataForm.getString("editText_Line_Gas_Pressure_Value", "");
        this.dataComputer[5][2]=dataForm.getString("editText_Line_Gas_Pressure_Observation", "");
        this.dataComputer[6][1]=dataForm.getString("editText_Gas_Train_Pressure_Value", "");
        this.dataComputer[6][2]=dataForm.getString("editText_Gas_Train_Pressure_Observation", "");
        this.dataComputer[7][1]=dataForm.getString("editText_Gas_Temperature_Value", "");
        this.dataComputer[7][2]=dataForm.getString("editText_Gas_Temperature_Observation", "");
        this.dataComputer[8][1]=dataForm.getString("editText_Water_Temperature_Value", "");
        this.dataComputer[8][2]=dataForm.getString("editText_Water_Temperature_Observation", "");
        this.dataComputer[9][1]=dataForm.getString("editText_Boiler_Level_Value", "");
        this.dataComputer[9][2]=dataForm.getString("editText_Boiler_Level_Observation", "");
        this.dataComputer[10][1]=dataForm.getString("editText_Condensate_Tank_Level_Value", "");
        this.dataComputer[10][2]=dataForm.getString("editText_Condensate_Tank_Level_Observation", "");
        this.dataComputer[11][1]=dataForm.getString("editText_Fuel_Tank_Level_Value", "");
        this.dataComputer[11][2]=dataForm.getString("editText_Fuel_Tank_Level_Observation", "");
    }
    public void loadDataTechnical(){
        //{"Carlos Martinez - Santiago Sanchez", "0", "0", "0", "Ivan Fernando Camargo"};
        dataForm = context.getSharedPreferences("M3dataTechnical", Context.MODE_PRIVATE);
        this.dataTechnical[0]=dataForm.getString("nameTechnical", "");
        this.dataTechnical[1]=dataForm.getString("timeStart", "0:00");
        this.dataTechnical[2]=dataForm.getString("timeEnd", "0:00");
    }
    public void loadDataBurnerAssembly() {
        String[]namesDataForm={"M4FuelValve", "M4Fan", "M4IgnitionTransformer", "M4Electrodes", "M4HighCable", "M4FuelNozzle", "M4FuelRegulator", "M4FuelFilter", "M4FuelLine", "M4FuelGauges", "M4PressureSwich"};
        for(int i=0; i<namesDataForm.length; i++){
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);

            boolean btnBPress = dataForm.getBoolean("dataBtnB", false);
            boolean btnRPress = dataForm.getBoolean("dataBtnR", false);
            boolean btnMPress = dataForm.getBoolean("dataBtnM", false);

            //validación botones
            if(btnBPress){
                this.dataBurnerAssembly[i][1] = "1";
            }else if(btnRPress){
                this.dataBurnerAssembly[i][1] = "2";
            }else if(btnMPress){
                this.dataBurnerAssembly[i][1] = "3";
            }else {
                this.dataBurnerAssembly[i][1] = "0";
            }

            //validacion observacion
            this.dataBurnerAssembly[i][2] = dataForm.getString("dataObservationText", "NA");
        }
    }
    public void loadDataControlSecurity() {
        String[]namesDataForm={"M5PressureControl", "M5Thermostat", "M5WarrickLowAuxLevel", "M5Modutrol", "M5ModulationControl", "M5PressureGauges", "M5SafetyValves", "M5SwichAirFan"};
        for(int i=0; i<namesDataForm.length; i++){
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);

            boolean btnBPress = dataForm.getBoolean("dataBtnB", false);
            boolean btnRPress = dataForm.getBoolean("dataBtnR", false);
            boolean btnMPress = dataForm.getBoolean("dataBtnM", false);

            //validación botones
            if(btnBPress){
                this.dataControlSecurity[i][1] = "1";
            }else if(btnRPress){
                this.dataControlSecurity[i][1] = "2";
            }else if(btnMPress){
                this.dataControlSecurity[i][1] = "3";
            }else {
                this.dataControlSecurity[i][1] = "0";
            }

            //validacion observacion
            this.dataControlSecurity[i][2] = dataForm.getString("dataObservationText", "NA");
        }
    }
    public void loadDataWaterLevelControl(){
        String[]namesDataForm={"M6BleedTaps", "M6LevelGlass", "M6Packaging", "M6Float", "M6AmpoulesMicros", "M6LevelTaps"};
        for(int i=0; i<namesDataForm.length; i++){
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);

            boolean btnBPress = dataForm.getBoolean("dataBtnB", false);
            boolean btnRPress = dataForm.getBoolean("dataBtnR", false);
            boolean btnMPress = dataForm.getBoolean("dataBtnM", false);

            //validación botones
            if(btnBPress){
                this.dataWaterLevelControl[i][1] = "1";
            }else if(btnRPress){
                this.dataWaterLevelControl[i][1] = "2";
            }else if(btnMPress){
                this.dataWaterLevelControl[i][1] = "3";
            }else {
                this.dataWaterLevelControl[i][1] = "0";
            }

            //validacion observacion
            this.dataWaterLevelControl[i][2] = dataForm.getString("dataObservationText", "NA");
        }
    }
    public void loadDataCondensateTank(){
        String[]namesDataForm={"M7GeneralAspect", "M7FloatModule7", "M7LevelGlass", "M7WaterFilter", "M7Pipelines", "M7Valves", "M7Thermometer"};
        for(int i=0; i<namesDataForm.length; i++){
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);

            boolean btnBPress = dataForm.getBoolean("dataBtnB", false);
            boolean btnRPress = dataForm.getBoolean("dataBtnR", false);
            boolean btnMPress = dataForm.getBoolean("dataBtnM", false);

            //validación botones
            if(btnBPress){
                this.dataCondensateTank[i][1] = "1";
            }else if(btnRPress){
                this.dataCondensateTank[i][1] = "2";
            }else if(btnMPress){
                this.dataCondensateTank[i][1] = "3";
            }else {
                this.dataCondensateTank[i][1] = "0";
            }

            //validacion observacion
            this.dataCondensateTank[i][2] = dataForm.getString("dataObservationText", "NA");
        }
    }
    public void loadDataWaterPump(){
        String[]namesDataForm={"M8GeneralAspectModule8", "M8Coupling", "M8Pressure", "M8Accessories"};
        for(int i=0; i<namesDataForm.length; i++){
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);

            boolean btnBPress = dataForm.getBoolean("dataBtnB", false);
            boolean btnRPress = dataForm.getBoolean("dataBtnR", false);
            boolean btnMPress = dataForm.getBoolean("dataBtnM", false);

            //validación botones
            if(btnBPress){
                this.dataWaterPump[i][1] = "1";
            }else if(btnRPress){
                this.dataWaterPump[i][1] = "2";
            }else if(btnMPress){
                this.dataWaterPump[i][1] = "3";
            }else {
                this.dataWaterPump[i][1] = "0";
            }

            //validacion observacion
            this.dataWaterPump[i][2] = dataForm.getString("dataObservationText", "NA");
        }
    }
    public void loadDataSecurityTest(){
        String[]namesDataForm={"M91", "M92", "M93", "M94", "M95", "M96", "M97", "M98", "M99", "M910", "M911"};
        for(int i=0; i<namesDataForm.length; i++){
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);

            boolean btnBPress = dataForm.getBoolean("dataBtnB", false);
            boolean btnRPress = dataForm.getBoolean("dataBtnR", false);
            boolean btnMPress = dataForm.getBoolean("dataBtnM", false);

            //validación botones
            if(btnBPress){
                this.dataSecurityTest[i][1] = "1";
            }else if(btnRPress){
                this.dataSecurityTest[i][1] = "2";
            }else if(btnMPress){
                this.dataSecurityTest[i][1] = "3";
            }else {
                this.dataSecurityTest[i][1] = "0";
            }

            //validacion observacion
            this.dataSecurityTest[i][2] = dataForm.getString("dataObservationText", "NA");
        }
    }
    public void loadDataBody(){
        String[]namesDataForm={"M10Shell", "M10WetChamber", "M10DryChamber", "M10Refractories", "M10InternalPiping", "M10Welds", "M10FrontBacKCovers", "M10Manhole", "M10Handhole", "M10Packaging", "M10Painting", "M10Isolation"};
        for(int i=0; i<namesDataForm.length; i++){
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);

            boolean btnBPress = dataForm.getBoolean("dataBtnB", false);
            boolean btnRPress = dataForm.getBoolean("dataBtnR", false);
            boolean btnMPress = dataForm.getBoolean("dataBtnM", false);

            //validación botones
            if(btnBPress){
                this.dataBody[i][1] = "1";
            }else if(btnRPress){
                this.dataBody[i][1] = "2";
            }else if(btnMPress){
                this.dataBody[i][1] = "3";
            }else {
                this.dataBody[i][1] = "0";
            }

            //validacion observacion
            this.dataBody[i][2] = dataForm.getString("dataObservationText", "NA");
        }
    }
    public void loadDataElectricalPanelControl(){
        String[]namesDataForm={"M11Programmer", "M11FlameSensor", "M11Photocell", "M11GeneralWiring", "M11ElectricShells", "M11Breaker", "M11Fuses", "M11Contactors", "M11Relays", "M11Terminals", "M11Organization", "M11SignalingPlates", "M11LightBulbs"};
        for(int i=0; i<namesDataForm.length; i++){
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);

            boolean btnBPress = dataForm.getBoolean("dataBtnB", false);
            boolean btnRPress = dataForm.getBoolean("dataBtnR", false);
            boolean btnMPress = dataForm.getBoolean("dataBtnM", false);

            //validación botones
            if(btnBPress){
                this.dataElectricalPanelControl[i][1] = "1";
            }else if(btnRPress){
                this.dataElectricalPanelControl[i][1] = "2";
            }else if(btnMPress){
                this.dataElectricalPanelControl[i][1] = "3";
            }else {
                this.dataElectricalPanelControl[i][1] = "0";
            }

            //validacion observacion
            this.dataElectricalPanelControl[i][2] = dataForm.getString("dataObservationText", "NA");
        }
    }
    public void loadDataElectricMotors(){
        String[]namesDataForm={"M12FanModule12", "M12Compressor", "M12WaterPump", "M12FuelPump", "M12Bearings"};
        for(int i=0; i<namesDataForm.length; i++){
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);

            boolean btnBPress = dataForm.getBoolean("dataBtnB", false);
            boolean btnRPress = dataForm.getBoolean("dataBtnR", false);
            boolean btnMPress = dataForm.getBoolean("dataBtnM", false);

            //validación botones
            if(btnBPress){
                this.dataElectricalMotors[i][1] = "1";
            }else if(btnRPress){
                this.dataElectricalMotors[i][1] = "2";
            }else if(btnMPress){
                this.dataElectricalMotors[i][1] = "3";
            }else {
                this.dataElectricalMotors[i][1] = "0";
            }

            //validacion observacion
            this.dataElectricalMotors[i][2] = dataForm.getString("dataObservationText", "NA");
        }
    }
    public void loadDataPipesAccessories(){
        String[]namesDataForm={"M13GeneralAspectModule13", "M13Support", "M13IsolationModule13", "M13ValvesModule13", "M13Traps", "M13Checks","M13Purges", "M13Distributor"};
        for(int i=0; i<namesDataForm.length; i++){
            dataForm = context.getSharedPreferences(namesDataForm[i], Context.MODE_PRIVATE);

            boolean btnBPress = dataForm.getBoolean("dataBtnB", false);
            boolean btnRPress = dataForm.getBoolean("dataBtnR", false);
            boolean btnMPress = dataForm.getBoolean("dataBtnM", false);

            //validación botones
            if(btnBPress){
                this.dataPipesAccessories[i][1] = "1";
            }else if(btnRPress){
                this.dataPipesAccessories[i][1] = "2";
            }else if(btnMPress){
                this.dataPipesAccessories[i][1] = "3";
            }else {
                this.dataPipesAccessories[i][1] = "0";
            }

            //validacion observacion
            this.dataPipesAccessories[i][2] = dataForm.getString("dataObservationText", "NA");
        }
    }
    public void loadDataObservations(){
        dataForm = context.getSharedPreferences("M14observations", Context.MODE_PRIVATE);
        boolean generateChange = dataForm.getBoolean("generateChange", false);
        if(generateChange){
            int numberChange = dataForm.getInt("numberChange", 1);
            if(numberChange<=5){
                for (int i=0; i<numberChange; i++) {
                    this.dataObservations[i] = dataForm.getString("observationNumber"+i, "");
                }
            }else{
                this.dataObservations = new String[numberChange];
                for (int i=0; i<numberChange; i++) {
                    this.dataObservations[i] = dataForm.getString("observationNumber"+i, "");
                }
            }

        }
    }
    public void loadDataRecommendations(){
        dataForm = context.getSharedPreferences("M15Recommendations", Context.MODE_PRIVATE);
        boolean generateChange = dataForm.getBoolean("generateChange", false);
        if(generateChange){
            int numberChange = dataForm.getInt("numberChange", 1);
            this.dataRecommendations = new String[numberChange];
            for (int i=0; i<numberChange; i++) {
                this.dataRecommendations[i] = dataForm.getString("recommendationNumber"+i, "");
            }
        }
    }
    public void loadImages(Bitmap[] bmFinals, boolean[] photosSelected){
        Image image;
        int contador=0;
        for (boolean b : photosSelected) {
            if (b) {
                contador++;
            }
        }
        this.images = new Image[contador];
        contador=0;
        for(int i=0; i<bmFinals.length; i++){
            if(photosSelected[i]){
                image = generateImage(bmFinals[i]);
                images[contador]=image;
                contador++;
            }
        }
    }
    private Image generateImage(Bitmap bm){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image img = null;
        byte[] byteArray = stream.toByteArray();
        try {
            img = Image.getInstance(byteArray);
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createDocument(){
        createFile();
        try {
            document=new Document(PageSize.A4, 20, 20, 20, 20);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
        }catch (Exception e){
            Log.e("OpenDocument", e.toString());
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createFile(){
        File folder=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "AutocaldPDFs");
        if(!folder.exists()){
            folder.mkdir();
            pdfFile=new File(folder, generateNameDocument());
        }
        pdfFile=new File(folder, generateNameDocument());
    }
    public void closeDocument(){
        document.close();
    }
    public void addMetaData(String title, String subject, String author){
        document.addTitle(title);
        document.addSubject(subject);
        document.addAuthor(author);
    }
    public void addTable(PdfPTable table) throws DocumentException {
        document.add(table);
    }
    public PdfPTable customizeTabla() throws DocumentException {
        PdfPTable table = new PdfPTable(10);
        table.setWidths(new float[] { 17, 3, 3, 3, 17, 20, 3,3,3, 19});
        table.setWidthPercentage(100);
        return table;
    }
    public PdfPCell customizeCell(String text, int font, boolean title, boolean center, int rowSpan, int colSpan){
        PdfPCell cell = new PdfPCell();
        Paragraph paragraph = new Paragraph();
        //add text and format
        if(font==0){
            paragraph = new Paragraph(text, fTextGenericTitle);
        }else if(font==1){
            paragraph = new Paragraph(text, fTextGenericTitle1);
        }else if(font==2){
            paragraph = new Paragraph(text, fTextGenericTitle2);
        }else if(font==3){
            paragraph = new Paragraph(text, fTextGenericTitle3);
        }else if(font==4){
            paragraph = new Paragraph(text, fTextGenericTitle4);
        }else {
            paragraph = new Paragraph(text, fTextGenericTitle5);
        }
        //add center
        if(center){
            paragraph.setAlignment(Element.ALIGN_CENTER);
        }
        //add text
        cell.addElement(paragraph);
        //add title
        if(title){
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        }
        //add rowSpan
        if(rowSpan!=0){
            cell.setRowspan(rowSpan);
        }
        //add colSpan
        if(colSpan!=0){
            cell.setColspan(colSpan);
        }
        cell.setUseAscender(true);
        cell.setUseDescender(true);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        return cell;
    }
    public PdfPTable dataClient(PdfPTable table){
        /*
        0 = private Font fTextGenericTitle = new Font(Font.FontFamily.HELVETICA, 11);
        1 = private Font fTextGenericTitle1 = new Font(Font.FontFamily.HELVETICA, 5);
        2 = private Font fTextGenericTitle2 = new Font(Font.FontFamily.HELVETICA, 10);
        3 = private Font fTextGenericTitle3 = new Font(Font.FontFamily.HELVETICA, 6);
        4 = private Font fTextGenericTitle4 = new Font(Font.FontFamily.HELVETICA, 8);
        5 = private Font fTextGenericTitle5 = new Font(Font.FontFamily.HELVETICA, 6, Font.NORMAL, BaseColor.RED);
         */
        //primera fila
        PdfPCell cell = new PdfPCell();
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.logotype_pdf);
        Image image = generateImage(bm);
        image.scaleAbsolute(258, 30);
        cell.addElement(image);
        cell.setColspan(5);
        cell.setRowspan(4);
        table.addCell(cell);
        table.addCell(customizeCell("CONTROL DE SERVICIO", 4, true, true,0, 0));
        table.addCell(customizeCell("FECHA:(d/m/a)", 1, true, true,0, 3));
        table.addCell(customizeCell("TIPO DE SERVICIO", 0, true, true, 0, 0));

        //segunda fila
        for(int i=0; i<3; i++){
            if(i==0){
                table.addCell(customizeCell(getData(1, i, 0), 0, false, true, 0, 0));
            }
            if(i==1){
                table.addCell(customizeCell(getData(1, i, 0), 2, false, true, 0, 3));
            }
            if(i==2){
                table.addCell(customizeCell(getData(1, i, 0), 3, false, true, 0, 0));
            }
        }

        //tercera fila
        table.addCell(customizeCell("CLIENTE", 0, true, true,0, 0));
        table.addCell(customizeCell("CAPACIDAD", 1, true, true,0, 3));
        table.addCell(customizeCell("MODELO / AÑO", 3, true, true, 0, 0));

        //cuarta fila
        for(int i=3; i<6; i++){
            if(i==3){
                table.addCell(customizeCell(getData(1, i, 0), 0, false, true, 0, 0));
            }
            if(i==4){
                table.addCell(customizeCell(getData(1, i, 0), 0, false, true, 0, 3));
            }
            if(i==5){
                table.addCell(customizeCell(getData(1, i, 0), 4, false, true, 0, 0));
            }
        }
        return table;
    }
    public PdfPTable dataModules(PdfPTable table){

        //6 row
        table = addTitleModule(table,4);
        table = addTitleModule(table,10);
        //7 -17 row
        int cModule2=0;

        int cModule4=0;
        int cModule5=0;
        int cModule6=0;
        int cModule7=0;
        int cModule8=0;
        int cModule9=0;
        int cModule10=0;
        int cModule11=0;
        int cModule12=0;
        int cModule13=0;
        int cModule14=0;
        for(int i=0; i<=51; i++){
            //conjunto quemador
            if(i<=10){
                table = addDataModule(table, 4, cModule4);
                cModule4++;

                table = addDataModule(table, 10, cModule10);
                cModule10++;
            }else if(i==11){
                table = addTitleModule(table,5);

                table = addDataModule(table, 10, cModule10);
                cModule10++;
            }else if(i==12){
                table = addDataModule(table, 5, cModule5);
                cModule5++;

                table = addTitleModule(table,11);
            }else if(i<=19){
                table = addDataModule(table, 5, cModule5);
                cModule5++;

                table = addDataModule(table, 11, cModule11);
                cModule11++;
            }else if(i==20){
                table = addTitleModule(table,6);

                table = addDataModule(table, 11, cModule11);
                cModule11++;
            }else if(i<=25){
                table = addDataModule(table, 6, cModule6);
                cModule6++;

                table = addDataModule(table, 11, cModule11);
                cModule11++;
            }else if(i==26){
                table = addDataModule(table, 6, cModule6);
                cModule6++;

                table = addTitleModule(table,12);
            }else if(i==27){
                table = addTitleModule(table,7);

                table = addDataModule(table, 12, cModule12);
                cModule12++;
            }else if(i<=31){
                table = addDataModule(table, 7, cModule7);
                cModule7++;

                table = addDataModule(table, 12, cModule12);
                cModule12++;
            }else if(i==32){
                table = addDataModule(table, 7, cModule7);
                cModule7++;

                table = addTitleModule(table,13);
            }else if(i<=34){
                table = addDataModule(table, 7, cModule7);
                cModule7++;

                table = addDataModule(table, 13, cModule13);
                cModule13++;
            }else if(i==35){
                table = addTitleModule(table, 8);

                table = addDataModule(table, 13, cModule13);
                cModule13++;
            }else if(i<=39){
                table = addDataModule(table, 8, cModule8);
                cModule8++;

                table = addDataModule(table, 13, cModule13);
                cModule13++;
            }else if(i==40){
                table = addTitleModule(table, 9);

                table = addDataModule(table, 13, cModule13);
                cModule13++;
            }else if(i==41){
                table = addDataModule(table, 9, cModule9);
                cModule9++;

                table.addCell(customizeCell("DATOS  DEL EQUIPO", 3, true, false, 0, 0));
                table.addCell(customizeCell("DATO",3, true, true, 0, 3));
                table.addCell(customizeCell("OBSERVACIONES", 3, true, true, 0, 0 ));
            }else
            //i<=51
            {
                table = addDataModule(table, 9, cModule9);
                cModule9++;

                table = dataComputer(table, cModule2);
                cModule2++;
            }
        }
        return table;
    }
    private PdfPTable dataComputer(PdfPTable table, int counter){

        table.addCell(customizeCell(getData(2, counter,0), 3, false, false, 0, 0));
        table.addCell(customizeCell(getData(2, counter,1), 4, false, true, 0, 3));
        table.addCell(customizeCell(getData(2, counter,2), 3, false, true, 0, 0 ));

        return table;
    }
    public PdfPTable dataObservationsRecommendations(PdfPTable table){
        int cModule2=10;
        int cModule14=0;
        int cModule15=0;
        int rowSpan = 0;
        int cIObservations = 0;
        int cIRecommendations = 0;
        for(int i=52;i<52+getLength(14)+getLength(15)+2; i++){
            if(i==52){
                table.addCell(customizeCell("OBSERVACIONES GENERALES", 3, true, true, 0, 5));

                table = dataComputer(table, cModule2);
                cModule2++;
            }else if(i==53){
                table.addCell(customizeCell(getData(14, cModule14, 0), 3, false, false, 0, 5));
                cModule14++;

                table = dataComputer(table, cModule2);
                cModule2++;
            }else if(i==54){
                table.addCell(customizeCell(getData(14, cModule14, 0), 3, false, false, 0, 5));
                cModule14++;

                table.addCell(customizeCell("DIAGRAMAS", 3, true, false, 0, 5));
            }else if(rowSpan==0){
                table.addCell(customizeCell(getData(14, cModule14, 0), 3, false, false, 0, 5));
                cModule14++;

                rowSpan = ((getLength(14)-(cModule14-1))+getLength(15)+1);
                PdfPCell cell = new PdfPCell();
                Paragraph p = new Paragraph();
                if(images!=null){
                    int contador=1;
                    for(int j=0; j<images.length; j++){
                        Image img = Image.getInstance(images[j]);
                        img.scaleAbsolute(72, 106);
                        p.add(new Chunk(img, 0, 0, true));
                        contador++;
                        if(contador==5){
                            p.setSpacingAfter(5);
                            cell.addElement(p);
                            p = new Paragraph();
                            contador=1;
                        }
                    }
                }
                cell.addElement(p);
                cell.setColspan(5);
                cell.setRowspan(rowSpan);
                table.addCell(cell);
                cIObservations=i;
            }else if(i<(cIObservations+getLength(14))-2){
                table.addCell(customizeCell(getData(14, cModule14, 0), 3, false, false, 0, 5));
                cModule14++;
                cIRecommendations = i;
            }else if(i==cIRecommendations+1){
                table.addCell(customizeCell("Recomendaciones", 3, true, true, 0, 5));
            }else if(i<(cIRecommendations+getLength(15)+2)){
                table.addCell(customizeCell(getData(15, cModule15, 0), 3, false, false, 0, 5));
                cModule15++;
            }
        }
        return table;
    }
    public PdfPTable dataTechnical(PdfPTable table){
        for (int i = 0; i<=3; i++){
            if(i==0){
                table.addCell(customizeCell("Nombre del Tecnico", 3, true, true, 0, 5));
                table.addCell(customizeCell("Hora de Llegada: " + getData(3, i, 1), 0, false, false, 0, 5));
            }else if(i==1){
                table.addCell(customizeCell(getData(3, i, 0),3, false, true, 0, 5));
                table.addCell(customizeCell("Hora de Salida: " + getData(3, i, 2), 0, false, false, 0, 5));
            }else if(i==2){
                table.addCell(customizeCell("Revisado:", 3, true, true, 0, 5));
                table.addCell(customizeCell("Horas Hombre: " + getData(3,i, 3), 0, false,false, 2, 5));
            }else if(i==3){
                table.addCell(customizeCell(getData(3, i, 4),3, false, true, 0, 5));
            }
        }
        return table;
    }
    public PdfPTable dataOthers(PdfPTable table){
        table.addCell(customizeCell("Aceptado:", 3, true, true, 0, 5));
        table.addCell(customizeCell("SELLO DE ACEPTACION", 0, false, false, 0, 5));

        PdfPCell cell = new PdfPCell();
        Bitmap bm = this.signature;
        cell.addElement(generateImage(bm));
        cell.setColspan(5);
        cell.setRowspan(3);
        table.addCell(cell);
        table.addCell(customizeCell("", 0, false, false, 3, 5));
        return table;
    }
    private String getData(int module, int i, int j){
        String[][]data={{}};
        if(module==1){
            return this.dataClient[i];
        }else
        if(module==2){
            return this.dataComputer[i][j];
        }else
        if(module==3){
            return this.dataTechnical[j];
        }else
        if(module==4){
            return this.dataBurnerAssembly[i][j];
        }else
        if(module==5){
            return this.dataControlSecurity[i][j];
        }else
        if(module==6){
            return this.dataWaterLevelControl[i][j];
        }else
        if(module==7){
            return this.dataCondensateTank[i][j];
        }else
        if(module==8){
            return this.dataWaterPump[i][j];
        }else
        if(module==9){
            return this.dataSecurityTest[i][j];
        }else
        if(module==10){
            return this.dataBody[i][j];
        }else
        if(module==11){
            return this.dataElectricalPanelControl[i][j];
        }else
        if(module==12){
            return this.dataElectricalMotors[i][j];
        }else
        if(module==13){
            return this.dataPipesAccessories[i][j];
        }else
        if(module==14){
            return dataObservations[i];
        }else
        if(module==15){
            return dataRecommendations[i];
        }
        return data[i][j];
    }
    private int getLength(int module){
        if(module==14){
            return this.dataObservations.length;
        }else if(module==15){
            return this.dataRecommendations.length;
        }
        return 0;
    }
    private PdfPTable addTitleModule(PdfPTable table, int module){
        module = module-1;
        String[]titles= {"", "DATOS  DEL EQUIPO", "", "CONJUNTO QUEMADOR", "CONTROLES DE SEGURIDAD", "CONTROL DE NIVEL DE AGUA", "TANQUE DE CONDENSADOS", "BOMBA DE AGUA", "PRUEBAS DE SEGURIDAD", "CUERPO", "TABLERO ELECTRICO Y CONTROL", "MOTORES ELECTRICOS", "TUBERIAS - ACCESORIOS", "DATOS  DEL EQUIPO", "OBSERVACIONES GENERALES", "DIAGRAMAS"};
        table.addCell(customizeCell(titles[module], 3, true, false, 0, 0 ));
        table.addCell(customizeCell("B", 4, true, true, 0, 0 ));
        table.addCell(customizeCell("R", 4, true, true, 0, 0 ));
        table.addCell(customizeCell("M", 4, true, true, 0, 0 ));
        table.addCell(customizeCell("OBSERVACIONES", 3, true, true, 0, 0 ));
        return table;
    }
    private PdfPTable addDataModule(PdfPTable table, int module, int counter){
        //subTitle  module
        table.addCell(customizeCell(getData(module, counter, 0), 3, false, false, 0, 0));
        //B,R,M
        switch (getData(module, counter, 1)) {
            case "1":
                table = optionsBRM(1, table);
                break;
            case "2":
                table = optionsBRM(2, table);
                break;
            case "3":
                table = optionsBRM(3, table);
                break;
            default:
                table = optionsBRM(0, table);
                break;
        }
        //observation
        table.addCell(customizeCell(getData(module, counter, 2), 3, false, true, 0, 0));
        return table;
    }
    private PdfPTable optionsBRM(int i, PdfPTable table){
        if(i==1){
            table.addCell(customizeCell("X", 3, false, true, 0, 0));
            table.addCell(customizeCell("", 3, false, true, 0, 0));
            table.addCell(customizeCell("", 3, false, true, 0, 0));
        }else if(i==2){
            table.addCell(customizeCell("", 3, false, true, 0, 0));
            table.addCell(customizeCell("X", 3, false, true, 0, 0));
            table.addCell(customizeCell("", 3, false, true, 0, 0));
        }else if(i==3){
            table.addCell(customizeCell("", 3, false, true, 0, 0));
            table.addCell(customizeCell("", 3, false, true, 0, 0));
            table.addCell(customizeCell("X", 3, false, true, 0, 0));
        }else {
            table.addCell(customizeCell("", 3, false, true, 0, 0));
            table.addCell(customizeCell("", 3, false, true, 0, 0));
            table.addCell(customizeCell("", 3, false, true, 0, 0));
        }
        return table;
    }
    public File getFile() {
        if(pdfFile.exists()){
            return pdfFile;
        }
        return null;
    }
    public void setSignature(Bitmap signature) {
        this.signature = signature;
    }
}
