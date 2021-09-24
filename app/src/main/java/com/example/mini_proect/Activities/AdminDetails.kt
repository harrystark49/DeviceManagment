package com.example.mini_proect.Activities

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.activity_admin_details.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class AdminDetails : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_details)
        var helper= dbHelper(this)
        var db=helper.readableDatabase

        var cursor=db.rawQuery("SELECT * FROM ADD_ADMIN",null)
        if(cursor.moveToNext()){
            var email=cursor.getString(cursor.getColumnIndex("EMAIL"))
            var name=cursor.getString(cursor.getColumnIndex("NAME"))
            var mobile=cursor.getString(cursor.getColumnIndex("MOBILE"))
            admin_name_replace.setText(name)
            display_email_replace.setText(email)
            mobile_dispaly_replace.setText(mobile)
//            var cal = Calendar.getInstance()
//            var t : String = cal.time.toString()
//            mobile_dispaly_replace.setText(t)
            var currentDateTime=LocalDateTime.now()
            mobile_dispaly_replace.setText(currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"+"\n"+"HH:mm:ss")))
        }

    }
}