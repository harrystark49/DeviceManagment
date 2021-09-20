package com.example.mini_proect.Activities

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.mini_proect.R
import com.example.mini_proect.fragments.My_devices
import com.example.mini_proect.fragments.all_devices
import com.example.mini_proect.fragments.emp_myhistory
import com.example.mini_proect.fragments.emp_settings
import kotlinx.android.synthetic.main.activity_home_screen_admin.*
import kotlinx.android.synthetic.main.activity_home_screen_admin.drawer_layout
import kotlinx.android.synthetic.main.activity_home_screen_employee.*

class Home_screen_employee : AppCompatActivity() {
    lateinit var toggle:ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen_employee)

        toggle= ActionBarDrawerToggle(this,drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        emp_navigation_tool.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.emp_all_devices->{
                   Fragments(all_devices())
                }
                R.id.emp_mydevices->{
                    Fragments(My_devices())
                }
                R.id.emp_myhistory->{
                    Fragments(emp_myhistory())
                }
                R.id.emp_settings->{
                    Fragments(emp_settings())
                }
                R.id.emp_logout->{
                    alertDialog()
                }
            }
            val b = true
            b
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun Fragments(frag:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.drawer_layout,frag).commit()
    }

    override fun onBackPressed() {
        alertDialog()
    }
    private fun alertDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Do you want to exit Inventory app?")
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
            finish()
        }
        builder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->

        }
        builder.create()
        builder.show()
    }

}