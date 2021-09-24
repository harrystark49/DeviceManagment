package com.example.mini_proect.Activities

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.mini_proect.R
import com.example.mini_proect.fragments.*
import com.example.mini_proect.fragments.emp.My_devices
import com.example.mini_proect.fragments.emp.emp_myhistory
import com.example.mini_proect.fragments.emp.emp_settings
import kotlinx.android.synthetic.main.activity_home_screen_employee.*

class Home_screen_employee : AppCompatActivity() {
    lateinit var toggle:ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen_employee)

        var b:Bundle? = intent.extras
        var emails = b?.getString("EmpEmail")

        toggle= ActionBarDrawerToggle(this,drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        emp_navigation_tool.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.emp_all_devices->{
                    var b:Bundle? = intent.extras
                    var email = b?.getString("EmpEmail")
                   Fragments(all_devices("emp",email!!))
                }
                R.id.emp_mydevices->{
                    Fragments(My_devices())
                }
                R.id.emp_myhistory->{
                    Fragments(emp_myhistory())
                }
                R.id.emp_settings-> {
                    var b: Bundle? = intent.extras
                    var email = b?.getString("EmpEmail").toString()
                    var pass = b?.getString("EmpPass").toString()

                    val myFrag1 = emp_settings()
                    val mBundle = Bundle()
                    mBundle.putString("EmpEmail", email)
                    mBundle.putString("EmpPass", pass)
                    myFrag1.arguments = mBundle
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.emp_fragment_replacer, myFrag1)
                        commit()
                    }
                }
                R.id.emp_logout->{
                    alertDialog()
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true

        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    private fun Fragments(frag:Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.emp_fragment_replacer, frag)
            commit()

        }
    }

    override fun onBackPressed() {
        alertDialog()
    }
    private fun alertDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.alert)
        builder.setPositiveButton(R.string.yes) { dialogInterface: DialogInterface, i: Int ->
            finish()
        }
        builder.setNegativeButton(R.string.no) { dialogInterface: DialogInterface, i: Int ->

        }
        builder.create()
        builder.show()
    }

}