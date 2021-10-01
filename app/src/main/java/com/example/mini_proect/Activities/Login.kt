package com.example.mini_proect.Activities

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mini_proect.R
import com.example.mini_proect.DataBase.dbHelper
import kotlinx.android.synthetic.main.activity_login.*


class login : AppCompatActivity() {

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {



        var helper = dbHelper(this)
        var db = helper.readableDatabase

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val anim = AnimationUtils.loadAnimation(this, R.anim.left_to_right)
        c1.startAnimation(anim)
        login_btn.setOnClickListener {


            check_empty_fileds(email_id.text.toString(), password.text.toString())
            if (!admin_check.isChecked) {
                var args = arrayOf(email_id.text.toString())
                var emp_cursor = db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE EMAIL=?", args)
                if (emp_cursor.moveToNext()) {
                    var login_details = arrayOf(email_id.text.toString(), password.text.toString())
                    var cursor = db.rawQuery(
                        "SELECT * FROM ADD_EMPLOYEE WHERE EMAIL=? AND PASSWORD=?",
                        login_details
                    )

                    if (cursor.moveToNext()) {
                        var pass = cursor.getString(cursor.getColumnIndex("PASSWORD")).toString()
                        if (pass == password.text.toString()) {

                            var intent = Intent(this, Home_screen_employee::class.java)
                            intent.putExtra("EmpEmail", email_id.text.toString())
                            intent.putExtra("EmpPass", password.text.toString())
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "User Doesn't Exists", Toast.LENGTH_SHORT).show()
                }

            } else {
                 var s=email_id.text.toString()
                var args = arrayOf(email_id.text.toString())

                Toast.makeText(this, "${email_id.text.toString()}", Toast.LENGTH_SHORT).show()
                var admin_cursor = db.rawQuery("SELECT * FROM ADD_ADMIN", null)
                if (admin_cursor.moveToNext()) {

                    var login_details = arrayOf(email_id.text.toString(), password.text.toString())
                    var cursor = db.rawQuery(
                        "SELECT * FROM ADD_ADMIN WHERE EMAIL=? AND PASSWORD=?",
                        login_details
                    )

                    if (cursor.moveToNext()) {

                        var pass = cursor.getString(cursor.getColumnIndex("PASSWORD")).toString()
                        if (pass == password.text.toString()) {

                            var intent = Intent(this, Home_screen_admin::class.java)
                            intent.putExtra("AdminEmail", email_id.text.toString())
                            intent.putExtra("AdminPass", password.text.toString())
                            startActivity(intent)
                            finish()
                        }
                    } else {
                        Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }
        Register_btn.setOnClickListener {
            var reg_intent = Intent(this, Register::class.java)
            startActivity(reg_intent)
        }

    }

    override fun onBackPressed() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.alert)
        builder.setPositiveButton(R.string.yes) { dialogInterface: DialogInterface, i: Int ->
            finish()
        }
        builder.setNegativeButton(R.string.no) { dialogInterface: DialogInterface, i: Int ->

        }
        builder.create()
        builder.setCancelable(true)
        builder.show()
    }

    private fun check_empty_fileds(email: String, pass: String) {
        if (email.isEmpty()) {
            material_email.error = getString(R.string.EmailisMandatory)
            material_email.isErrorEnabled = true
        } else if (!email.endsWith(getString(R.string.gmailcom), true)) {
            material_email.error = getString(R.string.EntervalidEmail)
            material_email.isErrorEnabled = true
        } else {
            material_email.isErrorEnabled = false
        }
        if (pass.isEmpty()) {
            material_password.error = getString(R.string.PleaseprovidePassword)
            material_password.isErrorEnabled = true
        } else {
            material_password.isErrorEnabled = false
        }
    }
}