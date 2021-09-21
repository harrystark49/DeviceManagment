package com.example.mini_proect.Activities

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.R
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        var helper = dbHelper(this)
        var db = helper.readableDatabase


        var b: Bundle? = intent.extras
        var empEmail = b?.getString("EmpEmail")
        var empPass = b?.getString("EmpPass")

        var adminEmail = b?.getString("AdminEmail")
        var adminPass = b?.getString("AdminPass")

        submit_button.setOnClickListener {

            var OldPass = old_password.text.toString()
            val NewPass = new_password.text.toString()
            val ConNew = admin_confirm_password.text.toString()

            if (NewPass.equals(ConNew)) {
                if(IsAlphaNumeric(NewPass) ){
                if (empEmail != null && empPass.equals(OldPass)) {
                    if(NewPass.length >=8 && NewPass.length<=10){
                    var cv = ContentValues()
                    cv.put("PASSWORD", NewPass)
                    db.update("ADD_EMPLOYEE", cv, "EMAIL=?", arrayOf(empEmail))
                }
                    else{
                        Toast.makeText(
                            this,
                            "Password should inbetween 8 and 10",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else if (adminEmail != null && adminPass.equals(OldPass)) {
                    var cv = ContentValues()
                    cv.put("PASSWORD", NewPass)
                    db.update("ADD_ADMIN", cv, "EMAIL=?", arrayOf(adminEmail))
                }
            } else{
                    Toast.makeText(
                        this,
                        "Password should be alphanumeric",
                        Toast.LENGTH_SHORT
                    ).show()
            }}else {
                Toast.makeText(
                    this,
                    "new password and confirm password are mismatch",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


        private fun IsAlphaNumeric(id: String): Boolean {
            var x: Boolean = false
            var y: Boolean = false
            var z: Boolean = true
            for (c in id) {
                if (c in 'a'..'z')
                    x = true
                else if (c in '0'..'9')
                    y = true
                else
                    z = false

            }
            if (x && y && z) {
                return true
            }
            return false
        }
    }

