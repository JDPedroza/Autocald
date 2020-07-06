package com.example.autocald.controller.activity

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.autocald.ui.additionalFeatures.Observations
import com.example.autocald.ui.additionalFeatures.Recommendations
import com.example.autocald.ui.conditionBoilerElements.MainConditionBoilerElements
import com.example.autocald.ui.maintenanceData.computerData.ComputerData
import com.example.autocald.ui.maintenanceData.dataClient.DataClient
import com.example.autocald.ui.maintenanceData.technicalData.TechnicalData

class MainActivityController {
    companion object {
        fun addFragment(supportFragmentManager: FragmentManager, contenedor: View, fragment: Fragment, tag: String) {
            supportFragmentManager.beginTransaction().add(contenedor.id, fragment, tag).commit()
        }
        fun replaceFragment(supportFragmentManager: FragmentManager, contenedor: View, fragment: Fragment, tag: String) {
            supportFragmentManager.beginTransaction().replace(contenedor.id, fragment, tag).commit()
        }
        fun removeFragment(supportFragmentManager: FragmentManager, fragment: Fragment) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
        fun resetFragment(fragment: DataClient){
            fragment.resetForm();
        }
        fun resetFragment(fragment: ComputerData){
            fragment.resetForm();
        }
        fun resetFragment(fragment: TechnicalData){
            fragment.resetForm();
        }
        fun resetFragment(fragment: MainConditionBoilerElements){
            fragment.resetForm();
        }
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        fun resetFragment(fragment: Observations){
            fragment.resetForm();
        }
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        fun resetFragment(fragment: Recommendations){
            fragment.resetForm();
        }
        fun addPhoto(fragment: MainConditionBoilerElements, path: String){
            fragment.addPhoto(path)
        }

        fun resetModule(fragment: MainConditionBoilerElements) {
            fragment.resetModule()
        }
    }
}