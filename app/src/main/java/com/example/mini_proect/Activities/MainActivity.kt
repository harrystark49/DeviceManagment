package com.example.mini_proect.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mini_proect.DataBase.All_Devices_Entity
import com.example.mini_proect.DataBase.All_Devices_view_Model
import com.example.mini_proect.R
import com.example.mini_proect.fragments.Adapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE)

        project_title.animate().setDuration(1000).rotationYBy(360f)
        app_title.alpha = 0f
        app_title.animate().setDuration(3000).alpha(1f).withEndAction {

            val islooged = sharedPreferences.getBoolean("isLogged", false)
            val adminoremp = sharedPreferences.getString("AdminOrEmp", null)

            if (!islooged) {
                var intent = Intent(this, login::class.java)
                startActivity(intent)
                finish()
            } else if (adminoremp.equals("Employee")) {
                startActivity(Intent(this, Home_screen_employee::class.java))
                finish()
            } else {
                startActivity(Intent(this, Home_screen_admin::class.java))
                finish()
            }
        }
    }
}









