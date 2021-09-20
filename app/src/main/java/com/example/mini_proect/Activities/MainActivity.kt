package com.example.mini_proect.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        project_title.animate().setDuration(1000).rotationYBy(360f)
        app_title.alpha=0f
        app_title.animate().setDuration(3000).alpha(1f).withEndAction {

            var intent= Intent(this, login::class.java)
            startActivity(intent)
            finish()
        }

    }

}
