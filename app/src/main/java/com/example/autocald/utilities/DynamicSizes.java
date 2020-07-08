package com.example.autocald.utilities;

import android.content.Context;
import android.util.DisplayMetrics;

public class DynamicSizes {

    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
