package com.example.mini_proect

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mini_proect.Activities.login
import com.example.mini_proect.DataBase.dbHelper
import kotlinx.android.synthetic.main.activity_save_data.*

class save_data : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_data)
        var helper= dbHelper(this)
        var db=helper.readableDatabase
        var cv=ContentValues()

        var bundle: Bundle? =intent.extras
        var id=bundle?.get("ID").toString()
        var email=bundle?.get("EMAIL").toString()
        var name=bundle?.get("NAME").toString()
        var mobile=bundle?.get("MOBILE").toString()
        cv.put("ID",id)
        cv.put("NAME",name)
        cv.put("EMAIL",email)
        cv.put("MOBILE",mobile)





        emp_submit.setOnClickListener {
           if( pass_check(emp_pass.text.toString())){

             if(emp_pass.text.toString()!=emp_confirm_password.text.toString()){
                Toast.makeText(this,"Password mismatch", Toast.LENGTH_SHORT).show()
            }else{
                cv.put("PASSWORD",emp_pass.text.toString())
                var cursor=db.rawQuery("SELECT * FROM ADD_ADMIN",null)
                if(!cursor.moveToNext()){
                    db.insert("ADD_ADMIN", null, cv)
                    Toast.makeText(this,"Admin successfully Added", Toast.LENGTH_SHORT).show()
                    var intent= Intent(this, login::class.java)
                    startActivity(intent)
                }else{

                db.insert("ADD_EMPLOYEE",null,cv)
                Toast.makeText(this,"Employee successfully Added", Toast.LENGTH_SHORT).show()
                var intent= Intent(this, login::class.java)
                    startActivity(intent)
            }}
        }}
    }



    private fun pass_check(pass: String):Boolean {
        if(pass.isEmpty()){
            material_emp_password.isErrorEnabled=true
            material_emp_password.error="provide password"
        }
        else if(!IsAlphaNumeric(pass)){
            material_emp_password.isErrorEnabled=true
            material_emp_password.error="Password should be alphanumeric"
        }
        else if(!(pass.length >=8 && pass.length <= 10)){
            material_emp_password.isErrorEnabled=true
            material_emp_password.error="Password should be inbetween  8 and 10"
        }

        else{
                material_emp_password.isErrorEnabled=false
                return true
            }
        return false
        }

    private fun IsAlphaNumeric(id: String): Boolean {
        var x:Boolean=false
        var y:Boolean=false
        var z:Boolean = true
        for(c in id){
            if(c in 'a'..'z' || c in 'A'..'Z')
                x=true

            else if(c in '0'..'9')
                y=true

            else
                z=false

        }
        if(x && y&& z){
            return true
        }
        return false
    }
}