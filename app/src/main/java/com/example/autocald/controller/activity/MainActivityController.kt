package com.example.autocald.controller.activity

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.autocald.ui.conditionBoilerElements.MainConditionBoilerElements

class MainActivityController {
    companion object {
        fun addFragment(supportFragmentManager: FragmentManager, contenedor: View, fragment: Fragment) {
            supportFragmentManager.beginTransaction().add(contenedor.id, fragment).commit()
        }
        fun replaceFragment(supportFragmentManager: FragmentManager, contenedor: View, fragment: Fragment) {
            supportFragmentManager.beginTransaction().replace(contenedor.id, fragment).commit()
        }
        fun removeFragment(supportFragmentManager: FragmentManager, fragment: Fragment) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
        fun resetFragment(fragment: MainConditionBoilerElements){
            fragment.resetForm();
        }
        fun addPhoto(fragment: MainConditionBoilerElements, path: String){
            fragment.addPhoto(path)
        }
    }
}