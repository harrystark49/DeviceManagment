package com.example.mini_proect.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.mini_proect.R
import com.example.mini_proect.DataBase.dbHelper
import com.example.mini_proect.save_data
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var user:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val anim = AnimationUtils.loadAnimation(this, R.anim.left_to_right)
        c2.startAnimation(anim)
        var helper= dbHelper(this)
        var db=helper.readableDatabase
        spin.onItemSelectedListener=this
        var arr=arrayOf(getString(R.string.Admin),getString(R.string.Employee))
        var adap= ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,arr)
        spin.adapter=adap

            emp_register_btn.setOnClickListener {

                var cursor=db.rawQuery("SELECT * FROM ADD_ADMIN",null)
                if(cursor.moveToNext() && user==getString(R.string.Admin)){
                    Toast.makeText(this,R.string.AdminalreadyCreated,Toast.LENGTH_SHORT).show()
                    emp_name.setText("")
                    emp_mobile.setText("")
                    emp_id.setText("")
                    emp_email.setText("")
                    emp_id.requestFocus()
                }

                    else{
                        if(Check_for_empty_fields(emp_id.text.toString()
                            ,emp_name.text.toString()
                            ,emp_email.text.toString()
                            ,emp_mobile.text.toString()
                        )){
                        var intent=Intent(this, save_data::class.java)
                        intent.putExtra("ID",emp_id.text.toString())
                        intent.putExtra("NAME",emp_name.text.toString())
                        intent.putExtra("EMAIL",emp_email.text.toString())
                        intent.putExtra("MOBILE",emp_mobile.text.toString())
                        startActivity(intent)
        }}

    }}


    private fun Check_for_empty_fields(id: String,name : String, email: String, mobile: String):Boolean {
        var x=0;
        if(id.isEmpty()){
            material_emp_id.error=getString(R.string.IDcantbeEmpty)
            material_emp_id.isErrorEnabled=true
        }else if(!IsAlphaNumeric(id)){
            material_emp_id.error=getString(R.string.IDshouldbeAlphaNumberic)
            material_emp_id.isErrorEnabled=true
        }
        else {
            material_emp_id.isErrorEnabled=false
            x++
        }

        if(name.isEmpty()){
            material_emp_name.error=getString(R.string.NameisMandatory)
            material_emp_name.isErrorEnabled=true
        }else{
            material_emp_name.isErrorEnabled=false
            x++
        }


        if(email.isEmpty()){
            material_emp_email.error=getString(R.string.EmailisMandatory)
            material_emp_email.isErrorEnabled=true
        }
        else if(!email.contains(getString(R.string.gmailcom))){
            material_emp_email.error=getString(R.string.EntervalidEmail)
            material_emp_email.isErrorEnabled=true
        }else{

            var helper= dbHelper(this)
            var db=helper.readableDatabase

            var cursor=db.rawQuery("SELECT * FROM ADD_EMPLOYEE WHERE EMAIL=?", arrayOf(emp_email.text.toString()))
            if(cursor.moveToNext()){
                material_emp_email.error=getString(R.string.Emailalreadyexists)
                material_emp_email.isErrorEnabled=true
            }else{
                material_emp_email.isErrorEnabled=false
                x++
            }


        }


        if(mobile.isEmpty()){
            material_emp_mobile.error=getString(R.string.MobilenumberisMandatory)
            material_emp_mobile.isErrorEnabled=true
        }else if(! isDigit(mobile)){
            material_emp_mobile.error=getString(R.string.Entervalidmobilenumber)
            material_emp_mobile.isErrorEnabled=true
        }else{
            material_emp_mobile.isErrorEnabled=false
            x++
        }
        if(x==4) {
            return true
        }
        return false
    }

    private fun isDigit(mobile: String): Boolean {
        var test=true
        var mobile_length=0
        for(num in mobile){
            if(num !in '0'..'9'){
                return false
            }
            mobile_length++
        }
        if(mobile_length!=10){
            return true
        }
        return test
    }

    private fun IsAlphaNumeric(id: String): Boolean {
        var x:Boolean=false
        var y:Boolean=false
        var z=true
        for(c in id){
            if(c in 'a'..'z' || c in 'A'..'Z'){
                x=true
            }
            else if(c in '0'..'9'){
                y=true
            }
            else{
                z=false
            }
        }
        if(x && y && z){
            return true
        }
        return false

    }
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        user=p0?.getItemAtPosition(p2).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
