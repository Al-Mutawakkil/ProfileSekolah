package com.tugas.profilesekolah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = host.navController
        bottom_nav.setupWithNavController(navController)

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val alertDialog = AlertDialog.Builder(this)
        if (keyCode == event?.keyCode){
            alertDialog.setTitle("Info")
            alertDialog.setMessage("Nungguin ya?")
            alertDialog.setPositiveButton("Yes", {dialog, which ->
                finish()
            })
            alertDialog.setNegativeButton("No", {dialog, which ->
                Toast.makeText(applicationContext, "ape", Toast.LENGTH_SHORT).show()
            })

            alertDialog.show()
        }
        return super.onKeyDown(keyCode, event)
    }
}