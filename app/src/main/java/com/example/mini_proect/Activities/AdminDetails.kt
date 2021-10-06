package com.example.mini_proect.Activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mini_proect.DataBase.DBHelper
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.activity_admin_details.*

class AdminDetails : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_details)

        var helper= DBHelper(this)
        var db=helper.readableDatabase

        var cursor=db.rawQuery("SELECT * FROM ADD_ADMIN",null)
        if(cursor.moveToNext()){
            var email=cursor.getString(cursor.getColumnIndex("EMAIL"))
            var name=cursor.getString(cursor.getColumnIndex("NAME"))
            var mobile=cursor.getString(cursor.getColumnIndex("MOBILE"))
            admin_name_replace.setText(name)
            display_email_replace.setText(email)
            mobile_dispaly_replace.setText(mobile)
        }

    }
}