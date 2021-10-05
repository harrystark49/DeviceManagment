package com.example.mini_proect.Activities

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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

        var b: Bundle? = intent.extras
        var emails = b?.getString("AdminEmail").toString()



        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_replacer, all_devices("Admin", emails))
            commit()
        }
        setContentView(R.layout.activity_home_screen_admin)

        togglebtn = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(togglebtn)
        togglebtn.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigation_tool.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.admin_all_devices -> {
                    fragmets(all_devices("Admin", emails!!))

                }
                R.id.request_devices -> {
                    fragmets(AdminRequestDevices("Admin", emails!!))
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
                R.id.admin_logout_device -> {
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
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }

    }

    fun fragmets(frag: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_replacer, frag)
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

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        } else {
            alertDialog()
        }
    }

    protected fun alertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.alert)
        builder.setPositiveButton(R.string.yes) { dialogInterface: DialogInterface, i: Int ->
            finishAffinity()
        }
        builder.setNegativeButton(R.string.no) { dialogInterface: DialogInterface, i: Int ->

        }
        builder.create()
        builder.show()
    }
}