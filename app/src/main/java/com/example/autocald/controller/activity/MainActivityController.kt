package com.example.autocald.controller.activity

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.autocald.ui.burnerAssembly.BurnerAssembly

/**
 * Created by alberto on 25/11/17.
 */
class MainActivityController {
    companion object {
        fun addFragment(supportFragmentManager: FragmentManager, contenedor: View, fragment: Fragment) {
            supportFragmentManager.beginTransaction().add(contenedor.id, fragment).commit()
        }

        fun resetFragment(fragment: BurnerAssembly){
            fragment.resetForm();
        }
    }
}