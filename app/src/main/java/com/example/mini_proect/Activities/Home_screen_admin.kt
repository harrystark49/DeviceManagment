package com.example.mini_proect.Activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.mini_proect.R
import com.example.mini_proect.fragments.admin.AddNewDevice
import com.example.mini_proect.fragments.admin.AdminRequestDevices
import com.example.mini_proect.fragments.admin.AdminSettings
import com.example.mini_proect.fragments.all_devices
import kotlinx.android.synthetic.main.activity_home_screen_admin.*

class Home_screen_admin : AppCompatActivity() {
    lateinit var togglebtn: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen_admin)

        var b1 = Bundle()
        b1.putString("adminOrEmp","Admin")
        var frag = all_devices()
        frag.arguments = b1
        fragmets(frag)


        var b: Bundle? = intent.extras



        var sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE)
        var edit = sharedPreferences.edit()



        togglebtn = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(togglebtn)
        togglebtn.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigation_tool.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.admin_all_devices -> {
                    var b = Bundle()
                    b.putString("adminOrEmp","Admin")
                    var frag = all_devices()
                    frag.arguments = b
                    fragmets(frag)
                }
                R.id.request_devices -> {
                    fragmets(AdminRequestDevices())
                }
                R.id.add_newDevice -> {
                    fragmets(AddNewDevice())
                }
                R.id.admin_settings -> {
                    var b: Bundle? = intent.extras
                    var email = b?.getString("AdminEmail").toString()
                    var pass = b?.getString("AdminPass").toString()

                    val myFrag = AdminSettings()
                    val mBundle = Bundle()
                    mBundle.putString("AdminEmail", email)
                    mBundle.putString("AdminPass", pass)

                    myFrag.arguments = mBundle
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_replacer, myFrag)
                        commit()
                    }
                }
                R.id.admin_logout -> {
                    edit.clear()
                    edit.commit()
                    startActivity(Intent(this, login::class.java))
                    alertDialog()
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }

    }

    fun fragmets(frag: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_replacer, frag)
            addToBackStack(null)
            commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (togglebtn.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {

        alertDialog()
    }

    protected fun alertDialog() {
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