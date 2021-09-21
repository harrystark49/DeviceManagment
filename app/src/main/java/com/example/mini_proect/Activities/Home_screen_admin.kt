package com.example.mini_proect.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.mini_proect.R
import com.example.mini_proect.add_new_device_fragment
import com.example.mini_proect.fragments.My_devices
import com.example.mini_proect.fragments.admin_request_devices
import com.example.mini_proect.fragments.all_devices
import kotlinx.android.synthetic.main.activity_home_screen_admin.*

class Home_screen_admin : AppCompatActivity() {
    lateinit var togglebtn:ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home_screen_admin)

        togglebtn= ActionBarDrawerToggle(this,drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(togglebtn)
        togglebtn.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigation_tool.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.admin_all_devices ->{
                    fragmets(all_devices())

                }
                R.id.admin_request_devices ->{
                    fragmets(admin_request_devices())
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }

    }

    fun fragmets(frag:Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_replacer,frag)
            commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(togglebtn.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}