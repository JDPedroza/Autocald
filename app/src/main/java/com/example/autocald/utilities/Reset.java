package com.example.autocald.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.util.Objects;

public class Reset {

    private Context context;

    public Reset(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void resetAll(boolean deleteImages){
        String[] nameDataForms={
                "M1DataClient",
                "M2DataComputer",
                "M3dataTechnical",
                "M4FuelValve", "M4Fan", "M4IgnitionTransformer", "M4Electrodes", "M4HighCable", "M4FuelNozzle", "M4FuelRegulator", "M4FuelFilter", "M4FuelLine", "M4FuelGauges", "M4PressureSwich",
                "M5PressureControl", "M5Thermostat", "M5WarrickLowAuxLevel", "M5Modutrol", "M5ModulationControl", "M5PressureGauges", "M5SafetyValves", "M5SwichAirFan",
                "M6BleedTaps", "M6LevelGlass", "M6Packaging", "M6Float", "M6AmpoulesMicros", "M6LevelTaps",
                "M7GeneralAspect", "M7FloatModule7", "M7LevelGlass", "M7WaterFilter", "M7Pipelines", "M7Valves", "M7Thermometer",
                "M8GeneralAspectModule8", "M8Coupling", "M8Pressure", "M8Accessories",
                "M9FlameFailure", "M9MacdonellLowLevel", "M9LowLevelWarrick", "M9High pressure", "M9LowPressure", "M9CombustionAir", "M9HighSteamPressure", "M9HydrostaticTest", "M9SafetyValves", "M9TemperatureHighLow", "M9Others",
                "M10Shell", "M10WetChamber", "M10DryChamber", "M10Refractories", "M10InternalPiping", "M10Welds", "M10FrontBacKCovers", "M10Manhole", "M10Handhole", "M10Packaging", "M10Painting", "M10Isolation",
                "M11Programmer", "M11FlameSensor", "M11Photocell", "M11GeneralWiring", "M11ElectricShells", "M11Breaker", "M11Fuses", "M11Contactors", "M11Relays", "M11Terminals", "M11Organization", "M11SignalingPlates", "M11LightBulbs",
                "M12FanModule12", "M12Compressor", "M12WaterPump", "M12FuelPump", "M12Bearings",
                "M13GeneralAspectModule13", "M13Support", "M13IsolationModule13", "M13ValvesModule13", "M13Traps", "M13Checks","M13Purges", "M13Distributor",
                "M14observations",
                "M15Recommendations",
                "M16PhotoManagement"
        };
        if(deleteImages){
            File folder=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "AutocaldPhotos");
            deleteImages(folder);
        }
        for (String nameDataForm : nameDataForms) {
            SharedPreferences dataForm = context.getSharedPreferences(nameDataForm, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = dataForm.edit();
            editor.clear();
            editor.apply();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void deleteImages(File archivoODirectorio){
        if (archivoODirectorio.isDirectory())
            for (File hijos : Objects.requireNonNull(archivoODirectorio.listFiles()))
                deleteImages(hijos);
        archivoODirectorio.delete();
    }
}
