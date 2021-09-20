package com.example.mini_proect.Activities

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mini_proect.R
import com.example.mini_proect.DataBase.dbHelper
import kotlinx.android.synthetic.main.activity_login.*


class login : AppCompatActivity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {


        var helper= dbHelper(this)
        var db=helper.readableDatabase

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn.setOnClickListener {

            check_empty_fileds(email_id.text.toString(), password.text.toString())
            if (!admin_check.isChecked) {
                var args = arrayOf(email_id.text.toString())
                var emp_cursor=db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE EMAIL=?", args)
                if(emp_cursor.moveToNext()){
                    var login_details= arrayOf(email_id.text.toString(),password.text.toString())
                    var cursor=db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE EMAIL=? AND PASSWORD=?",login_details)

                    if(cursor.moveToNext()){
                        var pass=cursor.getString(cursor.getColumnIndex("PASSWORD")).toString()
                        if(pass==password.text.toString()){
                            var intent = Intent(this, HomeScreen::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }else{
                        Toast.makeText(this,"Wrong Credentials",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"User Doesn't Exists",Toast.LENGTH_SHORT).show()
                }

            }else{
                var args = arrayOf(email_id.text.toString())
                var admin_cursor=db.rawQuery("SELECT * FROM ADD_ADMIN WHERE EMAIL=?", args)
                if(admin_cursor.moveToNext()){
                    var login_details= arrayOf(email_id.text.toString(),password.text.toString())
                    var cursor=db.rawQuery("SELECT * FROM ADD_ADMIN WHERE EMAIL=? AND PASSWORD=?",login_details)

                    if(cursor.moveToNext()){
                        var pass=cursor.getString(cursor.getColumnIndex("PASSWORD")).toString()
                        if(pass==password.text.toString()){
                            var intent = Intent(this, HomeScreen::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }else{
                        Toast.makeText(this,"Wrong Credentials",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"Wrong Credentials",Toast.LENGTH_SHORT).show()
                }
            }
        }
        Register_btn.setOnClickListener {
            var reg_intent=Intent(this, Register::class.java)
            startActivity(reg_intent)
        }

    }

    override fun onBackPressed() {
        var builder= AlertDialog.Builder(this)
        builder.setTitle("Do you want exit the Inventory app?")
        builder.setPositiveButton("Yes"){ dialogInterface: DialogInterface, i: Int ->
            finish()
        }
        builder.setNegativeButton("No"){ dialogInterface: DialogInterface, i: Int ->

        }
        builder.create()
        builder.setCancelable(true)
        builder.show()
    }

    private fun check_empty_fileds(email: String, pass: String) {
        if(email.isEmpty()){
            material_email.error="Email is Mandatory"
            material_email.isErrorEnabled=true
        }

        else if(!email.endsWith("@gmail.com",true)){
                material_email.error="Enter valid Email"
                material_email.isErrorEnabled=true
        }
        else{
            material_email.isErrorEnabled=false
        }
        if(pass.isEmpty()){
            material_password.error="Please provide Password"
            material_password.isErrorEnabled=true
        }
        else{
            material_password.isErrorEnabled=false
        }
    }
}